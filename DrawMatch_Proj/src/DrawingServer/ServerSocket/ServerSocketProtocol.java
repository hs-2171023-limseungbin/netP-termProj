package DrawingServer.ServerSocket;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import DrawingServer.Game.GameThread;

public class ServerSocketProtocol {
	public static ArrayList<User> List;
	
	private int port = 0;
	private ServerSocket Server;
	private Socket Client;
	private User user;

	private JButton startBtn;
	private JTextArea chatTextArea;
	
	public void start() {
		if(port != 0) {
			List = new ArrayList<User>();
			createServerSocket();
			createClientSocket();
			StartEvent();
			acceptClient();
		}else {System.out.println("서버 포트를 재설정 필요");}
	}
	public void setPort(int port) {
		this.port = port;
	}
	private void createServerSocket() {
		try {
			Server = new ServerSocket(port);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	private void createClientSocket() {
		Client = new Socket();
	}
	private void acceptClient() {
		while(true) {
			try {
				Client = Server.accept();
				SThread thread = new SThread();
				user = new User();
				user.setSocket(Client);
				user.createWriter();
				thread.setUser(user);
				thread.setChatTextArea(chatTextArea);
				List.add(user);
				thread.start();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	private void StartEvent() {
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(startCheck()) {
					chatTextArea.append("[운영자]: 두사람 이상부터 게임시작이 가능합니다.\n");
					chatTextArea.setCaretPosition(chatTextArea.getDocument().getLength());
				}else {
					startGame();
				}
			}
		});
	}
	private boolean startCheck() {
		if(List.size() > 1) {
			return false;
		}else {return true;}
	}
	private void startGame() {
		chatTextArea.append("[운영자]: 게임을 시작합니다.\n");
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
