package DrawingClient.Gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import DrawingClient.ClientSocket.GameManager;
import DrawingClient.ClientSocket.MsgThread.SendMsg;

public class PaintbtnManager{
	private DrawPaintManager paint;
	private JButton blackBtn;
	private JButton redBtn;
	private JButton blueBtn;
	private JButton greenBtn;
	private JButton yellowBtn;
	private JButton eraseBtn;
	private JButton clearBtn;
	
    
    public void createBtn() {
    	int btnWidth = 60;
    	int btnHeight = 40;
    	int margin = 5;
    	
    	createBtn("BLACK", Color.BLACK, 10, 415, btnWidth, btnHeight);
    	createBtn("RED", Color.RED, 10 + btnWidth + margin, 415, btnWidth, btnHeight);
        createBtn("BLUE", Color.BLUE, 10 + 2 * (btnWidth + margin), 415, btnWidth, btnHeight);
        createBtn("GREEN", Color.GREEN, 10 + 3 * (btnWidth + margin), 415, btnWidth, btnHeight);
        createBtn("YELLOW", Color.YELLOW, 10 + 4 * (btnWidth + margin), 415, btnWidth, btnHeight);
    	createEraseBtn();
    	createClearBtn();
    }
    
    private void createBtn(final String colorCommand, final Color color, int x, int y, int width, int height) {
    	JButton btn = new JButton();
    	btn.setBackground(color);
    	btn.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			if(GameManager.changeTurn == true) {
    				SendMsg.msg.println("Color:"+ colorCommand);
    				SendMsg.msg.flush();
    				paint.setColor(color);
    			}
    		}
    	});
    	setPaintForBtn(paint, btn);
    	setBtnInColorbtns(colorCommand, btn);
    }
    
    private void createEraseBtn() {
    	eraseBtn = new JButton();
    	eraseBtn.setBounds(360,415,60,40);
    	eraseBtn.setText("지우개");
    	eraseBtn.setBackground(Color.white);
        eraseBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (GameManager.changeTurn == true) {
                    SendMsg.msg.println("Color:ERASER");
                    SendMsg.msg.flush();
                    paint.setColor(Color.WHITE); // Assuming eraser is represented by white color
                }
            }
        });
        setPaintForBtn(paint, eraseBtn);
        setBtnInColorbtns("ERASER", eraseBtn);
    }
    
    private void createClearBtn() {
    	clearBtn = new JButton("X");
    	clearBtn.setBackground(Color.white);
    	clearBtn.setBounds(430,415,60,40);
    	clearBtn.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			if (GameManager.changeTurn == true) {
                    SendMsg.msg.println("CLEAR");
                    SendMsg.msg.flush();
                }
    		}
       	});
    }
    
    public void setPaint(DrawPaintManager paint) {
    	this.paint = paint;
    	setPaintForBtn(paint, blackBtn);
    	setPaintForBtn(paint, redBtn);
    	setPaintForBtn(paint, blueBtn);
    	setPaintForBtn(paint, greenBtn);
    	setPaintForBtn(paint, yellowBtn);
    }

	public JButton getBlackBtn() {
		return blackBtn;
	}

	public JButton getRedBtn() {
		return redBtn;
	}

	public JButton getBlueBtn() {
		return blueBtn;
	}

	public JButton getGreenBtn() {
		return greenBtn;
	}

	public JButton getYellowBtn() {
		return yellowBtn;
	}
	
	public JButton getEraseBtn() {
		return eraseBtn;
	}
	public JButton getClearBtn() {
		return clearBtn;
	}
    
	private void setPaintForBtn(DrawPaintManager paint, JButton btn) {
		if(btn != null) {
			btn.putClientProperty("paint", btn);
		}
	}
	
	private void setBtnInColorbtns(String colorCommand, JButton btn) {
		switch(colorCommand) {
		case "BLACK":
			blackBtn = btn;
			break;
		case "RED":
			redBtn = btn;
			break;
		case "BLUE":
			blueBtn = btn;
			break;
		case "GREEN":
			greenBtn = btn;
			break;
		case "YELLOW":
			yellowBtn = btn;
			break;
		case "ERASER":
            eraseBtn = btn;
            break;
		case "CLEAR":
			clearBtn = btn;
			break;
		}
	}
    
}

