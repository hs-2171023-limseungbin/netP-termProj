package DrawingServer.Gui;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import DrawingServer.ServerSocket.ServerSocketProtocol;
//서버 GUI
public class MainGui {
	private JFrame frame;
	private JButton startBtn;
	private JTextArea chatTextArea;
	private JTextField chatTextField;
	
	public void makeMainFrame() {
		createMainFrame();
		createStartBtn();
		createChatTextArea();
		createChatTextField();
		frame.repaint();
	}
	
	private void createMainFrame() {
		frame = new JFrame();
		frame.setTitle("관리자 페이지");
		frame.setSize(610,350);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent windowEvent) {
		        System.exit(0);
		    }
		});
	}
	
	private void createStartBtn() {
		startBtn = new JButton("START");
		startBtn.setBounds(500,20,100,50);
		frame.add(startBtn);
	}
	private void createChatTextArea() {
		chatTextArea = new JTextArea();
		chatTextArea.setBounds(10,25,480,250);
		chatTextArea.setBorder(BorderFactory.createLineBorder(Color.black));
		chatTextArea.setEnabled(false);
		chatTextArea.setDisabledTextColor(Color.black);
		chatTextArea.setCaretPosition(chatTextArea.getDocument().getLength());
		frame.add(chatTextArea);
	}
	private void createChatTextField() {
		chatTextField = new JTextField(); 
		chatTextField.setBounds(10,275,480,30);
		chatTextField.setBorder(BorderFactory.createLineBorder(Color.black,1));
		frame.add(chatTextField);
		chatTextField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					chatTextArea.append("운영자 >> " + chatTextField.getText()+"\n");
					chatTextArea.setCaretPosition(chatTextArea.getDocument().getLength());
					for(int i=0; i<ServerSocketProtocol.List.size(); i++) {
						ServerSocketProtocol.List.get(i).sendMsg("운영자 >> " + chatTextField.getText());
					}
					chatTextField.setText("");
				}
			}
		});
	}

	public JButton getStartBtn() {
		return startBtn;
	}

	public JTextArea getChatTextArea() {
		return chatTextArea;
	}
}
