package com.andrew;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by andre_000 on 4/9/2016.
 */
public class Wall {

    private int wallX;
    private int wallY;

    public Wall() {

        setWall();
    }

    private Square setWall() {
        Random rnd = new Random();

        wallX = rnd.nextInt(SnakeGame.xSquares);
        wallY = rnd.nextInt(SnakeGame.ySquares);

        return new Square(wallX,wallY);

    }

    public int getWallX() {
        return wallX;
    }

    public int getWallY() {
        return wallY;
    }

    public Square getSquare() {
        return new Square(wallX,wallY);
    }
}
