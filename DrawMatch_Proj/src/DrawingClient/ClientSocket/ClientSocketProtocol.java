package DrawingClient.ClientSocket;

import java.awt.image.BufferedImage;
import java.net.Socket;

import javax.swing.JTextArea;

import DrawingClient.ClientSocket.MsgThread.InputMessage;
import DrawingClient.ClientSocket.MsgThread.OutputMessage;
import DrawingClient.Gui.DrawPaintManager;

public class ClientSocketProtocol {
	static public boolean starGame = true;
	static public boolean changeTurn = true;

	private BufferedImage drawField;
	private DrawPaintManager paint;
	private JTextArea textArea;
	
	private String id = "";
	private String ip = "";
	private int port = 0;

	private Socket Server;

	//메시지 스레드
	private OutputMessage out;
	private InputMessage in;
	
	//서버 연결 시작
	public void start() {
		if(ip != null && port != 0) {connectServer();}
	}

	private void connectServer() {
		try {
			Server = new Socket(ip,port);
		}catch(Exception e) {}
	}
	
	//스레드 생성
	private void clientSocket() {
		out = new OutputMessage();
		in = new InputMessage();
		
		out.setSocket(Server);
		out.setId(id);
		in.setSocket(Server);
		in.setPaint(paint);
		in.setTextArea(textArea);
		in.setDrawField(drawField);
		
		out.start();
		in.start();
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}
	public void setPaint(DrawPaintManager paint) {
		this.paint = paint;
	}
	public void setDrawField(BufferedImage drawField) {
		this.drawField = drawField;
		clientSocket();
	}
}
