//main 화면 - 게임 화면
package DrawingClient.Gui;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import DrawingClient.ClientSocket.ClientSocketProtocol;
import DrawingClient.ClientSocket.MsgThread.OutputMessage;


//게임 화면 GUI
public class MainGui {
	private int panelWidth = 480;
	private int panelHeight = 370;
	private BufferedImage drawField;
	
	private JFrame frame;
	private JLabel drawLabel;
	private PaintbtnsManager btn;
	private DrawPaintManager paint;
	
	private JTextField inputTextField;
	private JTextField answerTextField;
	private JTextArea textArea;
	
	public void makeMainFrame() {
		createMainFrame();
		createDrawFrame();
		createPaint();
		createMouseEvent();
		createPaintBtns();
		createAnswerFrame();
		createPaintTextArea();
		createPaintInputTextField();
		frame.repaint();
	};
	
	private void createMainFrame() {
		frame = new JFrame();
		frame.setSize(700, 500);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	private void createDrawFrame() {
		drawField = new BufferedImage(panelWidth, panelHeight, BufferedImage.TYPE_4BYTE_ABGR);
		drawLabel = new JLabel(new ImageIcon(drawField));
		drawLabel.setBounds(10,80,panelWidth,panelHeight);
		drawLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		frame.add(drawLabel);
	}
	
	private void createPaint() {
		paint = new DrawPaintManager();
		paint.setBounds(10,80,panelWidth, panelHeight);
		paint.repaint();
		paint.printAll(drawField.getGraphics());
		frame.add(paint);
	}
	
	private void createMouseEvent() {
		drawLabel.addMouseMotionListener(new MouseMotionListener() {
			public void mouseDragged(MouseEvent e) {
				if(ClientSocketProtocol.changeTurn == true) {
					OutputMessage.msg.println("Coordinates:"+e.getX()+","+e.getY());
					OutputMessage.msg.flush();
				}
			}
			
			public void mouseMoved(MouseEvent e) {}
		});
	}
	
	private void createPaintBtns() {
		btn = new PaintbtnsManager();
		btn.setPaint(paint);
		btn.makeButtons();
		frame.add(btn.getBlackbtn());
		frame.add(btn.getBluebtn());
		frame.add(btn.getRedbtn());
		frame.add(btn.getGreenbtn());
		frame.add(btn.getYellowbtn());
		frame.add(btn.getEraserbtn());
		//frame.add(btn.getClearbtn());
	}
	
	private void createAnswerFrame() {
		answerTextField = new JTextField();
		Border listBorder = BorderFactory.createLineBorder(Color.black);
		answerTextField.setBorder(listBorder);
		answerTextField.setEnabled(false);
		answerTextField.setDisabledTextColor(Color.black);
		answerTextField.setHorizontalAlignment(JTextField.CENTER);
		answerTextField.setBounds(10,10,250,50);
		frame.add(answerTextField);
	}
	
	private void createPaintTextArea() {
		textArea = new JTextArea();
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setBounds(500,80,180,350);
		Border textAreaBorder = BorderFactory.createLineBorder(Color.black);
		textArea.setEnabled(false);
		textArea.setDisabledTextColor(Color.black);
		textArea.setBorder(textAreaBorder);
		textArea.setCaretPosition(textArea.getDocument().getLength());
		frame.add(scroll);
	}
	
	private void createPaintInputTextField() {
		inputTextField = new JTextField();
		inputTextField.setBounds(500,430,180,20);
		Border inputBorder = BorderFactory.createLineBorder(Color.black);
		inputTextField.setBorder(inputBorder);
		inputTextField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					OutputMessage.msg.println("Chatting:" + inputTextField.getText());
					OutputMessage.msg.flush();
					inputTextField.setText("");
				}
			}
		});
		frame.add(inputTextField);
	}

	public BufferedImage getDrawField() {
		return drawField;
	}

	public DrawPaintManager getPaint() {
		return paint;
	}

	public JTextField getAnswerTextField() {
		return answerTextField;
	}

	public JTextArea getTextArea() {
		return textArea;
	}
	
	
}
