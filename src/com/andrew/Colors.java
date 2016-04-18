package com.andrew;

import java.awt.*;
import java.util.LinkedList;

/**
 * Created by andre_000 on 4/9/2016.
 */
public class Colors {

    private Color boardColor;
    private Color snakeHeadColor;
    private Color snakeBodyColor;
    private Color kibbleColor;

    public Color getBoardColor() {
        return boardColor;
    }
    public Color getSnakeHeadColor() {
        return snakeHeadColor;
    }
    public Color getSnakeBodyColor() {
        return snakeBodyColor;
    }
    public Color getKibbleColor() {
        return kibbleColor;
    }
    public Color getWallColor() {
        return wallColor;
    }
    public Color getGridColor() {
        return gridColor;
    }
    public Color getFontColor() {
        return fontColor;
    }

    private Color wallColor;
    private Color gridColor;
    private Color fontColor;

    private String[] colorList = {"Nokia","Nokia2","Ocean","Mars","Developer"};


    public Colors(String colorFamily) {

        switch (colorFamily) {
            case "Nokia": {
                boardColor = new Color(177,216,123);
                snakeHeadColor = new Color(0,25,0);
                snakeBodyColor = new Color(0,25,0);
                kibbleColor = new Color(121,123,89);
                wallColor = new Color(43,175,225);
                gridColor = boardColor;
                fontColor = new Color(43,175,225);
                break;
            }
            case "Nokia2": {
                boardColor = new Color(119,121,100);
                snakeHeadColor = new Color(5,4,9);
                snakeBodyColor = new Color(5,4,9);
                kibbleColor = new Color(1,1,0);
                wallColor = new Color(200,200,200);
                gridColor = boardColor;
                fontColor = new Color(200,200,200);
                break;
            }
            case "Ocean": {
                boardColor = new Color(0,100,200);
                snakeHeadColor = new Color(5,4,255);
                snakeBodyColor = new Color(50,150,200);
                kibbleColor = new Color(0,200,100);
                wallColor = new Color(164,132,89);
                gridColor = new Color(0,121,200);
                fontColor = new Color(164,132,89);
                break;
            }
            case "Mars": {
                boardColor = new Color(218,125,62);
                snakeHeadColor = new Color(131,99,66);
                snakeBodyColor = new Color(106,76,49);
                kibbleColor = new Color(82,94,110);
                wallColor = new Color(86,54,47);
                gridColor = boardColor;
                fontColor = kibbleColor;
                break;
            }
            case "Developer":

            default: {
                boardColor = Color.WHITE;
                snakeHeadColor = Color.LIGHT_GRAY;
                snakeBodyColor = Color.BLACK;
                kibbleColor = Color.GREEN;
                wallColor = Color.RED;
                gridColor = Color.RED;
                fontColor = Color.RED;
            }
        }
    }

    protected String[] getColorList() { return colorList; }
    //FINDBUG - May expose internal representation by returning Colors.colorList. Made this protected and other
    // variables private.
}
