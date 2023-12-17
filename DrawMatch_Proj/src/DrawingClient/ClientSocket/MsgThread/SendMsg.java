package DrawingClient.ClientSocket.MsgThread;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class SendMsg extends Thread{
	private Socket Server;
	static public PrintWriter msg;
	private String id;
	
	public void run() {
		super.run();
		createSender();
		sendId();
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public void setSocket(Socket Server) {
		this.Server = Server;
	}
	private void createSender() {
		try {
			msg = new PrintWriter(Server.getOutputStream());
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	private void sendId() {
		msg.println(id);
		msg.flush();
	}
}