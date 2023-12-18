package DrawingClient.Gui;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;

public class DrawPaintManager extends JLabel{
	private int x;
    private int y;
    public boolean choiceColor = false;
    public Color color = Color.black;

    @Override
    public void paint(Graphics g) {
        if(choiceColor == true) {
            g.setColor(color);
            g.fillOval(x, y,10,10);
        }else if(choiceColor==false) {
            g.setColor(Color.white);
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