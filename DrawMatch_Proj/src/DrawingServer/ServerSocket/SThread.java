package DrawingServer.ServerSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import javax.swing.JTextArea;
import DrawingServer.Game.GameManager;

//각 클라이언트로부터 메시지를 받은 스레드 클래스
public class SThread extends Thread{
	private User user;
	private Socket Client;
	private BufferedReader enterUser;
	private String msg;
	private String id;
	
	private JTextArea chatTextArea;
	
	public void run() {
		super.run();
		setClient();
		createUserBuffer();
		joinChat();
		waitRes();
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	private void createUserBuffer() {
		try {
			enterUser = new BufferedReader(new InputStreamReader(Client.getInputStream()));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	//클라이언트 응답 대기
	private void waitRes() {
		while(true) {
			try {
				msg = enterUser.readLine();
				if(msg.contains("CHAT:")){
					msg += " ";
					String[] pars = msg.split(":");
					System.out.println(pars[1]);
					if(pars[1].equals(GameManager.answer+" ")&& GameManager.turnToAnswer == false && (!(this.id).equals(GameManager.Id))) {
						GameManager.turnToAnswer = true;
						GameManager.gotAnswerRight(id);
					}
					if(pars[0].equals("CHAT")) {
						pars[1] += " ";
						msg = "CHAT:" + "[" + id + "]" + pars[1];
					}
				}
				chatOnAllUserMsg();
			}catch(IOException e) {
				msg = "CHAT:" + id + " out the room.";
				chatOnAllUserMsg();
				ServerSocketProtocol.List.remove(user);
				
				break;
			}
		}
	}
	private void joinChat() {
		try {
			id = enterUser.readLine();
			user.setUserId(id);
			chatTextArea.append(id + "님이 입장하셨습니다.\n");
		}catch(IOException e) {
		}for(int i=0; i<ServerSocketProtocol.List.size(); i++) {
			ServerSocketProtocol.List.get(i).sendMsg("입장:" + id);
		}
	}
	//입장한 모든 사용자에게 메시지 전송
	private void chatOnAllUserMsg() {
		for(int i=0; i<ServerSocketProtocol.List.size(); i++) {
			ServerSocketProtocol.List.get(i).sendMsg(msg);
		}
		if(msg.contains("CHAT:")) {
			String pars[] = msg.split(":");
			chatTextArea.append(pars[1]+"\n");
			chatTextArea.setCaretPosition(chatTextArea.getDocument().getLength());
		}
	}
	private void setClient() {
		this.Client = user.getClient();
	}

	public void setChatTextArea(JTextArea chatTextArea) {
		this.chatTextArea = chatTextArea;
	}
}