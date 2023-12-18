package DrawingClient.ClientSocket.MsgThread;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import DrawingClient.ClientSocket.GameManager;
import DrawingClient.Gui.DrawPaintManager;

public class GetMsg extends Thread{

	private Socket Server;
	private BufferedReader msgbuff;
	private String msg;
	private DrawPaintManager paint;
	private BufferedImage drawField;
	private int x,y;
	private JTextArea textArea;
	private JTextField answerTextField;
	
	public void run() {
		super.run();
		makeMsgBuff();
		getMsg();
	}
	
	public void setSocket(Socket Server) {
		this.Server = Server;
	}
	
	private void makeMsgBuff() {
		try {
			msgbuff = new BufferedReader(new InputStreamReader(Server.getInputStream()));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	private void getMsg() {
		while(true) {
			try {
				msg = msgbuff.readLine();
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
							paint.setColor(Color.white);
						else if (pars[1].equals("WHITE"))
							paint.setColor(Color.white);
					} else if (pars[0].equals("CHAT")) {
						textArea.append(pars[1] + "\n");
						textArea.setCaretPosition(textArea.getDocument().getLength());
					} else if (pars[0].equals("JOIN")) {
						textArea.append(pars[1] + " join the room.\n");
						textArea.setCaretPosition(textArea.getDocument().getLength());
					} else if (pars[0].equals("MODE")) {
						if (pars[1].equals("CLEAR"))
							ClearDrawField();
					} else if (pars[0].equals("SET")) {
						if (pars[1].equals("FALSE")) {
							GameManager.changeTurn = false;
						} else if(pars[1].equals("TRUE")) {
							GameManager.changeTurn = true;
						}
					} else if(pars[0].equals("ANSWER")) {
						answerTextField.setText(pars[1]);
					}
			}
		}catch(IOException e) {
			e.printStackTrace();}
		}
	}
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

