package com.andrew;

import java.util.Random;

/**
 * Walls are objects that snake can't go through. Similar to kibbles, except that the game ends if snake touches one.
 */

// TODO make sure walls are not created on top of kibble or snake

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

    public boolean isThisInWall(Square testSquare) {
        if (this.wallX == testSquare.x && this.wallY == testSquare.y) {
            return true;
        } else {
            return false;
        }
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
