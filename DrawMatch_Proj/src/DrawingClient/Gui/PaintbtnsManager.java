package DrawingClient.Gui;

import java.awt.Color;

//색상 버튼 관리(통합)
public class PaintbtnsManager {
    private DrawPaintManager paint;
    private PaintBtnManager blackbtn;
    private PaintBtnManager redbtn;
    private PaintBtnManager bluebtn;
    private PaintBtnManager greenbtn;
    private PaintBtnManager yellowbtn;
    private PaintBtnManager eraserbtn;
    
    public void makeButtons() {
        makeButton(Color.BLACK, 20, 13, 60, 50);
        makeButton(Color.RED, 100, 13, 60, 50);
        makeButton(Color.BLUE, 180, 13, 60, 50);
        makeButton(Color.GREEN, 260, 13, 60, 50);
        makeButton(Color.YELLOW, 340, 13, 60, 50);
        makeButton(Color.WHITE, 420, 13, 60, 50);
    }

    private void makeButton(Color color, int x, int y, int width, int height) {
    	PaintBtnManager button = new PaintBtnManager(color, x, y, width, height);
        button.setPaint(paint);

        switch (getColorCommand(color)) {
            case "BLACK":
                blackbtn = button;
                break;
            case "RED":
                redbtn = button;
                break;
            case "BLUE":
                bluebtn = button;
                break;
            case "GREEN":
                greenbtn = button;
                break;
            case "YELLOW":
                yellowbtn = button;
                break;
            case "ERASER":
                eraserbtn = button;
                break;
        }
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
            return "WHITE";
        }
    }


    public void setPaint(DrawPaintManager paint) {
        this.paint = paint;
    }

    public PaintBtnManager getBlackbtn() {
        return blackbtn;
    }

    public PaintBtnManager getRedbtn() {
        return redbtn;
    }

    public PaintBtnManager getBluebtn() {
        return bluebtn;
    }

    public PaintBtnManager getGreenbtn() {
        return greenbtn;
    }

    public PaintBtnManager getYellowbtn() {
        return yellowbtn;
    }

    public PaintBtnManager getEraserbtn() {
        return eraserbtn;
    }
}
