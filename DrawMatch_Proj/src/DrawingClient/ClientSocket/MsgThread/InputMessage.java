package DrawingClient.ClientSocket.MsgThread;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import DrawingClient.ClientSocket.ClientSocketProtocol;
import DrawingClient.Gui.DrawPaintManager;
//서버로 부터 메시지를 받아 처리하는 클래스
public class InputMessage extends Thread{

	private int x,y;
	private String msg; //서버 수신 메시지
	
	private Socket Server; //서버 연결 소켓
	private BufferedReader in; // 서버 수신 메시지 서버
	
	private JTextArea textArea;
	private JTextField answerTextField;
	private DrawPaintManager paint;
	private BufferedImage drawField;
	
	//스레드 시작
	@Override
	public void run() {
		super.run();
		CreateInputMessage();
		getMsg();
	}
	
	public void setSocket(Socket Server) {
		this.Server = Server;
	}
	
	//메시지 버퍼 생성
	private void CreateInputMessage() {
		try {
			in = new BufferedReader(new InputStreamReader(Server.getInputStream()));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//메시지 수신 및 처리
	private void getMsg() {
		while(true) {
			try {
				msg = in.readLine();
				if(msg.contains(":")) {
					String[] pars = msg.split(":");
					if(pars[0].equals("Position")) {
						pars = pars[1].split(",");
						x = Integer.parseInt(pars[0]);
						y = Integer.parseInt(pars[1]);
						paint.setX(x);
						paint.setY(y);
						paint.repaint();
						paint.printAll(drawField.getGraphics());
					//그리기 색상관리
					}else if (pars[0].equals("Color")) {
						if (pars[1].equals("BLACK"))
							paint.setColor(Color.black);
						else if (pars[1].equals("BLUE"))
							paint.setColor(Color.blue);
						else if (pars[1].equals("RED"))
							paint.setColor(Color.red);
						else if (pars[1].equals("GREEN"))
							paint.setColor(Color.green);
						else if (pars[1].equals("YELLOW"))
							paint.setColor(Color.yellow);
						else if (pars[1].equals("WHITE"))
							paint.setColor(Color.white);
					} else if (pars[0].equals("CHAT")) {
						//채팅 메시지 출력
						textArea.append(pars[1] + "\n");
						textArea.setCaretPosition(textArea.getDocument().getLength());
					} else if (pars[0].equals("JOIN")) {
						// 참가 메시지 출력
						textArea.append(pars[1] + " join the room.\n");
						textArea.setCaretPosition(textArea.getDocument().getLength());
					} else if (pars[0].equals("MODE")) {
						if (pars[1].equals("CLEAR"))
							ClearDrawField();
					} else if (pars[0].equals("SET")) {
						if (pars[1].equals("FALSE")) {
							ClientSocketProtocol.changeTurn = false;
						} else if(pars[1].equals("TRUE")) {
							ClientSocketProtocol.changeTurn = true;
						}
					} else if(pars[0].equals("ANSWER")) {
						//정답 텍스트 필드 설정
						answerTextField.setText(pars[1]);
					}
			}
		}catch(IOException e) {
			e.printStackTrace();}
		}
	}
	// 게임 진행을 위해 그리기 영역 초기화(지우기)
	private void ClearDrawField() {
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

	public void setAnswerTextField(JTextField answerTextField) {
		this.answerTextField = answerTextField;
	}
}

