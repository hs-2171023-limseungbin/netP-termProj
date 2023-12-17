package DrawingServer.ServerSocket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class User {
	private Socket client;
	private PrintWriter msg;
	private String id;
	
	public void setSocket(Socket client) {
		this.client = client;
	}
	
	public void createWriter() {
		try {
			msg = new PrintWriter(client.getOutputStream());
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void sendMsg(String message) {
		msg.println(message);
		msg.flush();
	}
	public Socket getClient() {
		return this.client;
	}
	public void setUserId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return this.id;
	}
}
