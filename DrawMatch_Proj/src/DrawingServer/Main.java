package DrawingServer;

import DrawingServer.Gui.MainGui;
import DrawingServer.ServerSocket.ServerSocketProtocol;
//서버 Main
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocketProtocol server = new ServerSocketProtocol();
		
		MainGui main = new MainGui();
		main.makeMainFrame();
		
		server.setStartBtn(main.getStartBtn());
		server.setChatTextArea(main.getChatTextArea());
		server.setPort(8888);
		server.start();
	}
}
