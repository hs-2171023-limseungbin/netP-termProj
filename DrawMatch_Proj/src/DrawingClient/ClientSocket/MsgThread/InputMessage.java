package DrawingClient.ClientSocket.MsgThread;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JTextArea;

import DrawingClient.ClientSocket.ClientSocketProtocol;
import DrawingClient.Gui.DrawPaintManager;
//서버로 부터 메시지를 받아 처리하는 클래스
public class InputMessage extends Thread{

	private int x,y;
	private String msg; //서버 수신 메시지
	
	private Socket Server; //서버 연결 소켓
	private BufferedReader in; // 서버 수신 메시지 서버
	
	private JTextArea textArea;
	private DrawPaintManager paint;
	private BufferedImage drawField;
	
	//스레드 시작
	@Override
	public void run() {
		super.run();
		CreateInputMessage();
		GetInputMessage();
	}
	
	public void setSocket(Socket Server) {
		this.Server = Server;
	}
	
	//메시지 버퍼 생성
	private void CreateInputMessage() {
		try {
			in = new BufferedReader(new InputStreamReader(Server.getInputStream()));
		}catch(IOException e) {
			System.out.println("[오류] 발생");
		}
	}
	
	//메시지 수신 및 처리
	private void GetInputMessage() {
		while(true) {
			try {
				msg = in.readLine();
				if(msg.contains(":")) {
					String[] args = msg.split(":");
					if(args[0].equals("Coordinates")) {
						args = args[1].split(",");
						x = Integer.parseInt(args[0]);
						y = Integer.parseInt(args[1]);
						
						paint.setX(x);
						paint.setY(y);
						paint.repaint();
						paint.printAll(drawField.getGraphics());
					//그리기 색상관리
					}else if (args[0].equals("Color")) {
						if (args[1].equals("BLACK")){paint.setColor(Color.black);}
						else if (args[1].equals("BLUE")){paint.setColor(Color.blue);}
						else if (args[1].equals("RED")){paint.setColor(Color.red);}
						else if (args[1].equals("GREEN")){paint.setColor(Color.green);}
						else if (args[1].equals("YELLOW")){paint.setColor(Color.yellow);}
						else if (args[1].equals("WHITE")) {paint.setColor(Color.white);}
					} else if (args[0].equals("Chatting")) {
						//채팅 메시지 출력
						textArea.append(args[1] + "\n");
						textArea.setCaretPosition(textArea.getDocument().getLength());
					} else if (args[0].equals("Login")) {
						// 참가 메시지 출력
						textArea.append(args[1] + " 님 접속\n");
						textArea.setCaretPosition(textArea.getDocument().getLength());
					} else if (args[0].equals("Mode")) {
						if (args[1].equals("Repaint")){RePaintDrawField();}
					} else if (args[0].equals("Setting")) {
						if (args[1].equals("False")) {
							ClientSocketProtocol.changeTurn = false;
						} else if(args[1].equals("True")) {
							ClientSocketProtocol.changeTurn = true;
						}
					}
			}
		}catch(IOException e) {
			System.out.println("[오류] 발생");
			}
		}
	}
	// 게임 진행을 위해 그리기 영역 초기화(지우기)
	private void RePaintDrawField() {
		paint.setChoiceColor(false);
		paint.repaint();
		paint.printAll(drawField.getGraphics());
	}
	
	public void setPaint(DrawPaintManager paint) {
		this.paint = paint;
	}
	public void setDrawField(BufferedImage drawField) {
		this.drawField = drawField;
	}
	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}
}

