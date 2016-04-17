package com.andrew;

/**
 * Created by andre_000 on 4/17/2016.
 */
public class Life {

    protected int lives;

    public Life(int lives) {
        this.lives = lives;
    }

    public int getLives() { return lives; }
    public void setLives(int newLives) {this.lives = newLives; }
    public String getStringLife() {
        return Integer.toString(lives);
    }
}
