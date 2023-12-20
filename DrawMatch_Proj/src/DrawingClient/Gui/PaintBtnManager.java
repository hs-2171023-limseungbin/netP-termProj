package DrawingClient.Gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import DrawingClient.ClientSocket.ClientSocketProtocol;
import DrawingClient.ClientSocket.MsgThread.OutputMessage;

//색상 버튼 관리(단일)
public class PaintBtnManager extends JButton {
    private DrawPaintManager paint;

    public PaintBtnManager(Color color, int x, int y, int width, int height) {
        setBounds(x, y, width, height);
        setText(getColorCommand(color));
        setOpaque(true);
        setBackground(color);
        colorEvent(color);
    }
    
    private void colorEvent(final Color color) {
        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	// 유저 차례에 색상 변경 가능
                if (ClientSocketProtocol.changeTurn == true) {
                	//색상변경(서버 전송)
                    OutputMessage.out.println("Color:" + getColorCommand(color));
                    OutputMessage.out.flush();
                    paint.setColor(color);
                }
            }
        });
    }

    private String getColorCommand(Color color) {
        if (color.equals(Color.BLACK)) {
            return "BLACK";
        } else if (color.equals(Color.RED)) {
            return "RED";
        } else if (color.equals(Color.BLUE)) {
            return "BLUE";
        } else if (color.equals(Color.GREEN)) {
            return "GREEN";
        } else if (color.equals(Color.YELLOW)) {
            return "YELLOW";
        } else if (color.equals(Color.WHITE)) {
            return "ERASER";
        } else {
            return "ERASER";
        }
    }

    public void setPaint(DrawPaintManager paint) {
        this.paint = paint;
    }
}
