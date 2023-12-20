//main 화면 - 게임 화면
package DrawingClient.Gui;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
	private BufferedImage drawField;
	private PaintbtnsManager btn;
	private DrawPaintManager paint;
	
	private JFrame frame;
	private JLabel drawLabel;
	private JTextField inputTextField;
	private JTextArea textArea;
	
	public void makeMainFrame() {
		createMainFrame();
		createDrawFrame();
		createPaint();
		createMouseEvent();
		createPaintBtns();
		createPaintTextArea();
		createPaintInputTextField();
		frame.repaint();
	};
	
	private void createMainFrame() {
		frame = new JFrame();
		frame.setSize(700, 500);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent windowEvent) {
		        System.exit(0);
		    }
		});
	}
	
	private void createDrawFrame() {
		drawField = new BufferedImage(480, 370, BufferedImage.TYPE_3BYTE_BGR);
		drawLabel = new JLabel(new ImageIcon(drawField));
		drawLabel.setBounds(10,80,480,370);
		frame.add(drawLabel);
	}
	private void createPaint() {
		paint = new DrawPaintManager();
		paint.setBounds(10,80,480, 370);
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
	}
		
	private void createPaintTextArea() {
		textArea = new JTextArea();
		textArea.setBounds(500,20,180,400);
		textArea.setEnabled(false);
		textArea.setDisabledTextColor(Color.black);
		textArea.setCaretPosition(textArea.getDocument().getLength());
		frame.add(textArea);
	}
	
	private void createPaintInputTextField() {
		inputTextField = new JTextField();
		inputTextField.setBounds(500,430,180,20);
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
	public JTextArea getTextArea() {
		return textArea;
	}
	
	
}
