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

import DrawingClient.ClientSocket.GameManager;
import DrawingClient.ClientSocket.MsgThread.SendMsg;

public class MainGui {
	private int panelWidth = 500;
	private int panelHeight = 500;
	private BufferedImage drawField;
	
	private JFrame frame;
	private JLabel drawLabel;
	private PaintbtnManager btn;
	private PaintManager paint;
	
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
		drawLabel.setBounds(10,10,panelWidth,panelHeight);
		drawLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		frame.add(drawLabel);
	}
	
	private void createPaint() {
		paint = new PaintManager();
		paint.setBounds(10,10,panelWidth, panelHeight);
		paint.repaint();
		paint.printAll(drawField.getGraphics());
		frame.add(paint);
	}
	
	private void createMouseEvent() {
		drawLabel.addMouseMotionListener(new MouseMotionListener() {
			public void mouseDragged(MouseEvent e) {
				if(GameManager.changeTurn == true) {
					SendMsg.msg.flush();
				}
			}
			
			public void mouseMoved(MouseEvent e) {}
		});
	}
	
	private void createPaintBtns() {
		btn = new PaintbtnManager();
		btn.setPaint(paint);
		btn.createBtn();
		frame.add(btn.getBlackBtn());
		frame.add(btn.getBlueBtn());
		frame.add(btn.getRedBtn());
		frame.add(btn.getGreenBtn());
		frame.add(btn.getYellowBtn());
		frame.add(btn.getEraseBtn());
		frame.add(btn.getClearBtn());
	}
	
	private void createAnswerFrame() {
		answerTextField = new JTextField();
		Border listBorder = BorderFactory.createLineBorder(Color.black);
		answerTextField.setBorder(listBorder);
		answerTextField.setEnabled(false);
		answerTextField.setDisabledTextColor(Color.black);
		answerTextField.setHorizontalAlignment(JTextField.CENTER);
		answerTextField.setBounds(500,10,180,60);
		frame.add(answerTextField);
	}
	
	private void createPaintTextArea() {
		textArea = new JTextArea();
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setBounds(500,80,180,350);
		Border textAreaBorder = BorderFactory.createLineBorder(Color.black);
		textArea.setEnabled(false);
		textArea.setDisabledTextColor(Color.black);
		//textArea.setFont(textArea.getFont().deriveFont(style:10));
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
					SendMsg.msg.println("CHAT:" + inputTextField.getText());
					SendMsg.msg.flush();
					inputTextField.setText("");
				}
			}
		});
		frame.add(inputTextField);
	}

	public BufferedImage getDrawField() {
		return drawField;
	}

	public PaintManager getPaint() {
		return paint;
	}

	public JTextField getAnswerTextField() {
		return answerTextField;
	}

	public JTextArea getTextArea() {
		return textArea;
	}
	
	
}
