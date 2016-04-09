package com.andrew;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by clara. Handles key presses that affect the snake.
 */
public class SnakeControls implements KeyListener {

    Snake snake;

    SnakeControls(Snake s){
        this.snake = s;
    }

    @Override
    public void keyPressed(KeyEvent ev) {

        if (ev.getKeyCode() == KeyEvent.VK_DOWN) {
            snake.snakeDown(); // snake moves down if not already moving down or up
        }
        if (ev.getKeyCode() == KeyEvent.VK_UP) {
            snake.snakeUp(); // snake moves up if not already moving up or down
        }
        if (ev.getKeyCode() == KeyEvent.VK_LEFT) {
            snake.snakeLeft(); // snake moves left if not already moving left or right
        }
        if (ev.getKeyCode() == KeyEvent.VK_RIGHT) {
            snake.snakeRight(); // snake moves right if not already moving right or left
        }

    }

    @Override
    public void keyTyped(KeyEvent ev) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
