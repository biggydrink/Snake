package com.andrew;

import java.awt.*;
import java.util.LinkedList;

/**
 * Created by andre_000 on 4/9/2016.
 */
public class Colors {

    public Color boardColor;
    public Color snakeHeadColor;
    public Color snakeBodyColor;
    public Color kibbleColor;
    public Color wallColor;
    public Color gridColor;
    public Color fontColor;

    public String[] colorList = {"Nokia","Nokia2","Developer"};


    public Colors(String colorFamily) {

        switch (colorFamily) {
            case "Nokia": {
                boardColor = new Color(177,216,123);
                snakeHeadColor = new Color(0,25,0);
                snakeBodyColor = new Color(0,25,0);
                kibbleColor = new Color(121,123,89);
                wallColor = new Color(43,175,225);
                gridColor = new Color(177,216,123);
                fontColor = new Color(43,175,225);
                break;
            }
            case "Nokia2": {
                boardColor = new Color(119,121,100);
                snakeHeadColor = new Color(5,4,9);
                snakeBodyColor = new Color(5,4,9);
                kibbleColor = new Color(1,1,0);
                wallColor = new Color(200,200,200);
                gridColor = new Color(119,121,100);
                fontColor = new Color(200,200,200);
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

    public String[] getColorList() { return colorList; }
}
