package DrawingClient;

import DrawingClient.ClientSocket.ClientSocketProtocol;
import DrawingClient.Gui.LoginGui;
import DrawingClient.Gui.MainGui;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String id=null;
		
		MainGui main = new MainGui();
		LoginGui Login = new LoginGui();
		ClientSocketProtocol Socket = new ClientSocketProtocol();
	
		//로그인 창 생성
		Login.makeLoginFrame();
		
		for (id = Login.getId(); id == null; id = Login.getId()) {
		    System.out.println("");
		}
		
		Socket.setIp("127.0.0.1");
		Socket.setPort(8888);
		Socket.setId(id);
		
		main.makeMainFrame();
		
		Socket.start();		
		Socket.setTextArea(main.getTextArea());
		Socket.setPaint(main.getPaint());
		Socket.setDrawField(main.getDrawField());
	}

}
