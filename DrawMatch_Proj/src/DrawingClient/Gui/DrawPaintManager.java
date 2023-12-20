package DrawingClient.Gui;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;

//그림 그리는 영역 관리 클래스
public class DrawPaintManager extends JLabel{
	private static final long serialVersionUID = 1L;
	private int x;
    private int y;
    
    public boolean choiceColor = false; //색상 선택 여부
    public Color color = null;

    @Override
    public void paint(Graphics g) {
        if(choiceColor == true) {
            g.setColor(color);
            g.fillOval(x, y,10,10);
        }else if(choiceColor==false) {
            g.setColor(Color.white);
            g.fillRect(0, 0, 3000, 3000);
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