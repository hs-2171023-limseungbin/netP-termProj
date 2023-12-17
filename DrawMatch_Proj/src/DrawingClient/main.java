package DrawingClient;

import DrawingClient.ClientSocket.ClientSocketProtocol;
import DrawingClient.Gui.LoginGui;
import DrawingClient.Gui.MainGui;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String id=null;
		int port=0;	
		ClientSocketProtocol Socket = new ClientSocketProtocol();
		MainGui main = new MainGui();
		LoginGui Login = new LoginGui();
		
		Login.makeLoginFrame();
		
		do {
			id = Login.getId();
			System.out.println("");
		}while(id==null || port==0);
		Socket.setIp("127.0.0.1");
		Socket.setPort(8888);
		Socket.setId(id);
		
		main.makeMainFrame();
		Socket.start();		
		Socket.setAnswerTextField(main.getAnswerTextField());
		Socket.setTextArea(main.getTextArea());
		Socket.setPaint(main.getPaint());
		Socket.setDrawField(main.getDrawField());
	}

}
