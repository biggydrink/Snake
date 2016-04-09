package com.andrew;

import java.awt.*;
import java.util.LinkedList;

import javax.swing.JPanel;

/** This class responsible for displaying the graphics, so the snake, grid, kibble, instruction text and high score
 * 
 * @author Clara
 *
 */
public class DrawSnakeGamePanel extends JPanel {
	
	private static int gameStage = SnakeGame.BEFORE_GAME;  //use this to figure out what to paint
	
	private Snake snake;
	private Kibble kibble;
	private Score score;
	private Wall wall;
	private Colors colors = new Colors("Nokia");

	DrawSnakeGamePanel(GameComponentManager components){
		this.snake = components.getSnake();
		this.kibble = components.getKibble();
		this.score = components.getScore();
		this.wall = components.getWall();
	}
	
	public Dimension getPreferredSize() {
        return new Dimension(SnakeGame.xPixelMaxDimension, SnakeGame.yPixelMaxDimension);
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        /* Where are we at in the game? 4 phases.. 
         * 1. Before game starts
         * 2. During game
         * 3. Game lost aka game over
         * 4. or, game won
         */

        gameStage = SnakeGame.getGameStage();

        switch (gameStage) {
			case SnakeGame.BEFORE_GAME: {
				displayInstructions(g);
				break;
			}
			case SnakeGame.DURING_GAME: {
				displayGame(g);
				break;
			}
			case SnakeGame.GAME_OVER: {
				displayGameOver(g);
				break;
			}
			case SnakeGame.GAME_WON: {
				displayGameWon(g);
				break;
        	}
        }
    }

	private void displayGameWon(Graphics g) {
		// TODO Replace this with something really special!
		g.clearRect(100,100,350,350);
		g.drawString("YOU WON SNAKE!!!", 150, 150);
		
	}
	private void displayGameOver(Graphics g) {

		g.clearRect(100,100,350,350);
		g.drawString("GAME OVER", 150, 150);
		
		String textScore = score.getStringScore();
		String textHighScore = score.getStringHighScore();
		String newHighScore = score.newHighScore();
		
		g.drawString("SCORE = " + textScore, 150, 250);
		
		g.drawString("HIGH SCORE = " + textHighScore, 150, 300);
		g.drawString(newHighScore, 150, 400);
		
		g.drawString("press a key to play again", 150, 350);
		g.drawString("Press q to quit the game",150, 400);
    			
	}

	private void displayGame(Graphics g) {
		displayGameGrid(g);
		displaySnake(g);
		displayKibble(g);
		displayWall(g);
	}

	private void displayGameGrid(Graphics g) {

		int maxX = SnakeGame.xPixelMaxDimension;
		int maxY= SnakeGame.yPixelMaxDimension;
		int squareSize = SnakeGame.squareSize;

		g.setColor(colors.boardColor);
		g.fillRect(0, 0, maxX, maxY);

		g.setColor(colors.gridColor);


		//Draw grid - horizontal lines
		for (int y=0; y <= maxY ; y+= squareSize){			
			g.drawLine(0, y, maxX, y);
		}

		//Draw grid - vertical lines
		for (int x=0; x <= maxX ; x+= squareSize){			
			g.drawLine(x, 0, x, maxY);
		}

	}

	private void displayKibble(Graphics g) {

		//Draw the kibble in kibble Color
		g.setColor(colors.kibbleColor);

		int x = kibble.getKibbleX() * SnakeGame.squareSize;
		int y = kibble.getKibbleY() * SnakeGame.squareSize;

		g.fillRect(x+1, y+1, SnakeGame.squareSize-2, SnakeGame.squareSize-2);
		
	}

	private void displayWall(Graphics g) {

		// Draw the wall in wall Color
		g.setColor(colors.wallColor);

		int x = wall.getWallX() * SnakeGame.squareSize;
		int y = wall.getWallY() * SnakeGame.squareSize;

		g.fillRect(x,y,SnakeGame.squareSize,SnakeGame.squareSize);
	}


	private void displaySnake(Graphics g) {

		int size = SnakeGame.squareSize;

		LinkedList<Square> coordinates = snake.getSnakeSquares();
		
		// Draw head in head color
		g.setColor(colors.snakeHeadColor);
		Square head = coordinates.pop();
		g.fillRect(head.x * size, head.y * size, size, size);
		
		// Draw body in body color
		g.setColor(colors.snakeBodyColor);
		for (Square s : coordinates) {
			g.fillRect(s.x * size, s.y * size , size, size);
		}
	}


	private void displayInstructions(Graphics g) {
        g.drawString("Press any key to begin!",100,200);		
        g.drawString("Press q to quit the game",100,300);		
    	}
}

