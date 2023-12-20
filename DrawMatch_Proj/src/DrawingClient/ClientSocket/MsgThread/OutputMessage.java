package DrawingClient.ClientSocket.MsgThread;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
//서버로 메시지를 보내는 스레드 클래스
public class OutputMessage extends Thread{
	private String id;
	private Socket Server;
	static public PrintWriter msg;
	
	public void run() {
		super.run();
		CreateOutputMessage();
		OutputId();
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public void setSocket(Socket Server) {
		this.Server = Server;
	}
	private void CreateOutputMessage() {
		try {
			msg = new PrintWriter(Server.getOutputStream());
		}catch(IOException e) {
			System.out.println("[오류] 발생");
		}
	}
	private void OutputId() {
		msg.println(id);
		msg.flush();
	}
}
