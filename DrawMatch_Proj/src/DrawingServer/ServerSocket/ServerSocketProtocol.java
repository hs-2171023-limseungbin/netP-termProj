package DrawingServer.ServerSocket;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTextArea;

import DrawingServer.Game.GameThread;

public class ServerSocketProtocol {
	//연결된 사용자 리스트
	public static ArrayList<User> List;
	
	private User user;
	private int port = 8888;
	
	private ServerSocket Server;
	private Socket Client;

	private JButton startBtn;
	private JTextArea chatTextArea;
	
	public void start() {
		List = new ArrayList<User>();
		ServerSocket();
		ClientSocket();
		startMethod();
		confirmAdmission();
	}
	public void setPort(int port) {
		this.port = 8888;
	}
	private void ServerSocket() {
		try {
			Server = new ServerSocket(port);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	private void ClientSocket() {
		Client = new Socket();
	}
	private void confirmAdmission() {
		while(true) {
			try {
				Client = Server.accept();
				SThread thread = new SThread();
				user = new User();
				user.setSocket(Client);
				user.PrintWriter();
				thread.setUser(user);
				thread.setChatTextArea(chatTextArea);
				List.add(user);
				thread.start();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	private void startMethod() {
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//게임 시작 인원 조절
				if(checkUserNum()) {
					chatTextArea.append("[운영자]:참여 인원수를 확인하세요\n");
					chatTextArea.setCaretPosition(chatTextArea.getDocument().getLength());
				}else {startGame();}
			}
		});
	}
	private boolean checkUserNum() {
		if(List.size() > 1 && List.size()<5) {
			return false;
		}else {return true;}
	}
	private void startGame() {
		chatTextArea.append("게임 시작.\n");
		chatTextArea.setCaretPosition(chatTextArea.getDocument().getLength());
		GameThread gameThread = new GameThread();
		gameThread.setChatTextArea(chatTextArea);
		gameThread.start();
	}
	public void setChatTextArea(JTextArea chatTextArea) {
		this.chatTextArea = chatTextArea;
	}
	public void setStartBtn(JButton startBtn) {
		this.startBtn = startBtn;
	}
}
