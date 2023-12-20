package DrawingServer.Gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
//서버 GUI
public class MainGui {
	private JFrame frame;
	private JButton startBtn;
	
	public void makeMainFrame() {
		createMainFrame();
		createStartBtn();
		frame.repaint();
	}
	
	private void createMainFrame() {
		frame = new JFrame();
		frame.setSize(125,150);
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
		startBtn.setBounds(10,10,100,100);
		frame.add(startBtn);
	}
	public JButton getStartBtn() {
		return startBtn;
	}
}
