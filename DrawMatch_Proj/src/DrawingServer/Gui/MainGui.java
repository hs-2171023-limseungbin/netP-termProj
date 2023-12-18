package DrawingServer.Gui;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import DrawingServer.ServerSocket.ServerSocketProtocol;

public class MainGui {
	private JFrame frame;
	private JButton startBtn;
	private JTextArea chatTextArea;
	private JTextField chatTextField;
	private	JTextField joinUserField;
	private JScrollPane scroll;
	
	public void makeMainFrame() {
		createMainFrame();
		createStartBtn();
		createJoinUserField();
		createChatTextArea();
		createChatTextField();
		frame.repaint();
	}
	
	private void createMainFrame() {
		frame = new JFrame();
		frame.setSize(500,500);
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
		startBtn = new JButton("게임 시작");
		startBtn.setBounds(10,10,480,50);
		frame.add(startBtn);
	}
	private void createJoinUserField() {
		joinUserField = new JTextField("접속 유저");
		//joinUserField.setText("접속유저");
		joinUserField.setBounds(10,70,480,30);
		joinUserField.setBorder(BorderFactory.createLineBorder(Color.black,1));
		joinUserField.setDisabledTextColor(Color.black);
		joinUserField.setEnabled(false);
		frame.add(joinUserField);
	}
	private void createChatTextArea() {
		chatTextArea = new JTextArea();
		scroll = new JScrollPane(chatTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		chatTextArea.setBounds(10,110,480,250);
		chatTextArea.setBorder(BorderFactory.createLineBorder(Color.black));
		chatTextArea.setEnabled(false);
		chatTextArea.setDisabledTextColor(Color.black);
		scroll.setBounds(10,110,480,250);
		chatTextArea.setCaretPosition(chatTextArea.getDocument().getLength());
		frame.add(scroll);
	}
	private void createChatTextField() {
		chatTextField = new JTextField(); 
		chatTextField.setBounds(10,360,480,30);
		chatTextField.setBorder(BorderFactory.createLineBorder(Color.black,1));
		frame.add(chatTextField);
		chatTextField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					chatTextArea.append("[운영자(서버)]" + chatTextField.getText()+"\n");
					chatTextArea.setCaretPosition(chatTextArea.getDocument().getLength());
					for(int i=0; i<ServerSocketProtocol.List.size(); i++) {
						ServerSocketProtocol.List.get(i).sendMsg("CHAT:[운영자(서버)]" + chatTextField.getText());
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

	public JTextField getJoinUserField() {
		return joinUserField;
	}
}
