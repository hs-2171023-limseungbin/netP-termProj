package DrawingClient.Gui;

import java.awt.Color;

public class PaintbtnsManager {
    private DrawPaintManager paint;
    private PaintBtnManager blackbtn;
    private PaintBtnManager redbtn;
    private PaintBtnManager bluebtn;
    private PaintBtnManager greenbtn;
    private PaintBtnManager yellowbtn;
    private PaintBtnManager eraserbtn;

    public void makeButtons() {
        makeButton(Color.BLACK, 10, 415, 60, 40);
        makeButton(Color.RED, 80, 415, 60, 40);
        makeButton(Color.BLUE, 150, 415, 60, 40);
        makeButton(Color.GREEN, 220, 415, 60, 40);
        makeButton(Color.YELLOW, 290, 415, 60, 40);
        makeButton(Color.WHITE, 360, 415, 60, 40);
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
            return "BLACK";
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
