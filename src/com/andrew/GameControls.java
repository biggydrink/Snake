package com.andrew;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameControls implements KeyListener{

	private static char optionsKey = 'o';
	private static char quitKey = 'q';
	

	public void keyPressed(KeyEvent ev) {
		//keyPressed events are for catching events like function keys, enter, arrow keys

		char keyPressed = ev.getKeyChar();

		//Is game running? No? tell the game to draw grid, start, etc.
		if (SnakeGame.getGameStage() == SnakeGame.BEFORE_GAME && keyPressed != optionsKey){
			SnakeGame.newGame();
			return;
		} else if (SnakeGame.getGameStage() == SnakeGame.GAME_OVER && keyPressed != optionsKey){
			// This is the same at the above if-statement, but it's possible you might want to
			// do something different depending on where you are in the game.
			SnakeGame.newGame();
		}


	}


	@Override
	public void keyReleased(KeyEvent ev) {
		//We don't care about keyReleased events, but are required to implement this method anyway.		
	}


	@Override
	public void keyTyped(KeyEvent ev) {
		//keyTyped events are for user typing letters on the keyboard, anything that makes a character display on the screen
		char keyPressed = ev.getKeyChar();
		if( keyPressed == quitKey){
			System.exit(0);    //quit if user presses the q key.
		} else if (keyPressed == optionsKey) {
			SnakeGame.setGameStage(SnakeGame.BEFORE_GAME);
			SnakeGame.gui.setVisible(true);
		}
	}

}
