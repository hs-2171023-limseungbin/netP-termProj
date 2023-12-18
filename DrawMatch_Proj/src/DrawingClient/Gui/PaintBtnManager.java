package DrawingClient.Gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import DrawingClient.ClientSocket.GameManager;
import DrawingClient.ClientSocket.MsgThread.SendMsg;


public class PaintBtnManager extends JButton {
    private DrawPaintManager paint;

    public PaintBtnManager(Color color, int x, int y, int width, int height) {
        setBounds(x, y, width, height);
        setBackground(color);
        makeEvent(color);
    }

    private void makeEvent(final Color color) {
        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (GameManager.changeTurn) {
                    SendMsg.msg.println("Color:" + getColorCommand(color));
                    SendMsg.msg.flush();
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
            return "BLACK";
        }
    }

    public void setPaint(DrawPaintManager paint) {
        this.paint = paint;
    }
}
