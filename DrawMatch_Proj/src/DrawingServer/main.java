package DrawingServer;

import java.net.ServerSocket;

import DrawingServer.Gui.MainGui;
import DrawingServer.ServerSocket.ServerSocketProtocol;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocketProtocol server = new ServerSocketProtocol();
		MainGui main = new MainGui();
		
		main.makeMainFrame();
		server.setStartBtn(main.getStartBtn());
		server.setJoinUserField(main.getJoinUserField());
		server.setChatTextArea(main.getChatTextArea());
		server.setPort(8888);
		server.start();
	}
}
