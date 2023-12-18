package DrawingClient.Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class LoginGui {
	private int PanelWidth = 200;
	private int PanelHeight = 150;

	private JFrame frame;
	private JLabel idLabel;
	private JTextField idTextField;
	private JButton button;

	private String id = null;
	private String ip = "127.0.0.1";
	
	public void makeLoginFrame() {
		createLoginFrame();
		createidLabel();
		createidTextField();
		createButton();
		frame.repaint();
		setIp();
		setId();
	}
	
	private void createLoginFrame() {
		frame = new JFrame();
		frame.setSize(PanelWidth, PanelHeight);
		frame.setTitle("로그인 화면"); 
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // X버튼을 눌러서 창을 닫았을시 프로그램 종료
		frame.setVisible(true);
		frame.setLayout(null);
	}
	
	private void createidLabel() {
		idLabel = new JLabel();
		idLabel.setText("ID: ");
		idLabel.setBounds(10,30,30,30);
		frame.add(idLabel);
	}
	
	private void createidTextField() {
		idTextField = new JTextField();
		idTextField.setBounds(50,33,120,30);
		idTextField.setHorizontalAlignment(JTextField.CENTER);
		frame.add(idTextField);
	}
	
	private void createButton() {
		button = new JButton();
		button.setText("로그인");
		button.setBounds(0,70,200,30);
		frame.add(button);
	}
	
	private void setId() {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				id = idTextField.getText();
				frame.dispose();
			}
		});
	}
	
	public String getId() {
		return this.id;
	}
	public String getIp() {
		return this.ip;
	}
	
	private void setIp() {
		this.ip = "127.0.0.1";
	}
}
