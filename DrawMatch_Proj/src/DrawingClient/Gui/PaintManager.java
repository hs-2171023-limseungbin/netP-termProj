package DrawingClient.Gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;

public class PaintManager extends JLabel{
    private int x;
    private int y;
    private boolean choiceColor = false;
    private Color color = Color.black;

    public void selectPaint(Graphics g) {
        if(choiceColor == true) {
            g.setColor(color);
            g.fillOval(x-2, y-2,10,20);
        }else if(choiceColor==false) {
            g.setColor(color.white);
            g.fillRect(0, 0, 1000, 1000);
            choiceColor = true;
        }
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setChoiceColor(boolean choiceColor) {
        this.choiceColor = choiceColor;
    }
}

