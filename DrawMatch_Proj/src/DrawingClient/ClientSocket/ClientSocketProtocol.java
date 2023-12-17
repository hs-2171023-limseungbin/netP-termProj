package DrawingClient.ClientSocket;

import java.awt.image.BufferedImage;

import java.net.Socket;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import DrawingClient.ClientSocket.MsgThread.GetMsg;
import DrawingClient.ClientSocket.MsgThread.SendMsg;
import DrawingClient.Gui.PaintManager;

public class ClientSocketProtocol {
	private Socket Server;
	private String ip = null;
	private int port = 0;
	private SendMsg Thread1;
	private GetMsg Thread2;
	private PaintManager paint;
	private BufferedImage drawField;
	private JTextArea textArea;
	private JTextField answerTextField;
	private String id = "test";
	
	public void start() {
		if(ip!=null && port!=0) {
			connectServer();
		}
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	public void setPort(int port) {
		this.port = port;
	}
	private void connectServer() {
		try {
			Server = new Socket(ip,port);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	private void makeThread() {
		Thread1 = new SendMsg();
		Thread2 = new GetMsg();
		Thread1.setSocket(Server);
		Thread1.setId(id);
		Thread2.setAnswerTextField(answerTextField);
		Thread2.setPaint(paint);
		Thread2.setTextArea(textArea);
		Thread2.setDrawField(drawField);
		Thread1.start();
		Thread2.start();
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	public void setPaint(PaintManager paint) {
		this.paint = paint;
	}

	public void setDrawField(BufferedImage drawField) {
		this.drawField = drawField;
		makeThread();
	}

	public void setAnswerTextField(JTextField answerTextField) {
		this.answerTextField = answerTextField;
	}
}
