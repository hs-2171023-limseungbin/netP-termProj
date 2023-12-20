package DrawingServer.ServerSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import DrawingServer.Game.GameManager;

//각 클라이언트로부터 메시지를 받은 스레드 클래스
public class OutputMessage extends Thread {
	private User user;
	private Socket Client;
	private BufferedReader in;
	private String msg;
	private String id;

	public void run() {
		super.run();
		setClient();
		userOutputStream();
		LoginGame();
		try {
			waitResponse();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setUser(User user) {
		this.user = user;
	}

	private void userOutputStream() {
		try {
			in = new BufferedReader(new InputStreamReader(Client.getInputStream()));
		} catch (IOException e) {
			System.out.println("[에러발생]");
		}
	}

	// 클라이언트 응답 대기
	private void waitResponse() throws IOException {
		while (true) {
			try {
				msg = in.readLine();
				if (msg.contains("Chatting:")) {
					msg += " ";
					String[] args = msg.split(":");
					System.out.println(args[1]);
					if (args[1].equals(GameManager.answer + " ") && GameManager.turnToAnswer == false
							&& (!(this.id).equals(GameManager.Id))) {
						GameManager.turnToAnswer = true;
						GameManager.userCorrect(id);
					}
					if (args[0].equals("Chatting")) {
						args[1] += " ";
						msg = "Chatting:" + "[" + id + "]" + args[1];
					}
				}
				chatOnAllUserMsg();
			} catch (IOException e) {
				ServerSocketProtocol.List.remove(user);
				Client.close();
				break;
			}
		}
	}

	private void LoginGame() {
		try {
			id = in.readLine();
			user.setUserId(id);
		} catch (IOException e) {
			System.out.println("[에러발생]");
		}
		for (int i = 0; i < ServerSocketProtocol.List.size(); i++) {
			ServerSocketProtocol.List.get(i).outputMessage("Login:" + id);
		}
	}

	// 입장한 모든 사용자에게 메시지 전송
	private void chatOnAllUserMsg() {
		for (int i = 0; i < ServerSocketProtocol.List.size(); i++) {
			ServerSocketProtocol.List.get(i).outputMessage(msg);
		}
	}

	private void setClient() {
		this.Client = user.getClient();
	}
}