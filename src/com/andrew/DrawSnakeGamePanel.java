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
	private Wall[] walls;
	private Life life;
	//private Colors colors = new Colors("Nokia");
	//private Colors colors = new Colors("Nokia2");
	//private Colors colors = new Colors("default");
	private Colors colors = new Colors(SnakeGame.colorChosen);

	private int fontStartX = SnakeGame.xPixelMaxDimension/10;
	private int fontStartY = SnakeGame.yPixelMaxDimension/8;
	private String preferredFont = "SansSerif";
	private int fontStyle = Font.BOLD;
	private int fontSize = 30;


	DrawSnakeGamePanel(GameComponentManager components){
		this.snake = components.getSnake();
		this.kibble = components.getKibble();
		this.score = components.getScore();
		this.walls = components.getWalls();
		this.life = components.getLife();
	}
	
	public Dimension getPreferredSize() {
        return new Dimension(SnakeGame.xPixelMaxDimension, SnakeGame.yPixelMaxDimension);
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
		g.setFont(new Font(preferredFont,fontStyle,fontSize));

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
		fillEntireBoard(g);
		g.drawString("YOU WON SNAKE!!!", fontStartX, fontStartY);
		
	}
	private void displayGameOver(Graphics g) {

		int textCount = 0;

		g.setColor(colors.boardColor);
		fillEntireBoard(g);

		g.setColor(colors.fontColor);
		g.drawString("GAME OVER", fontStartX, fontStartY+(fontSize*textCount++));
		textCount++; // line break
		
		String textScore = score.getStringScore();
		String textHighScore = score.getStringHighScore();
		String newHighScore = score.newHighScore();
		String lives = life.getStringLife();
		
		g.drawString("Score: " + textScore, fontStartX, fontStartY+(fontSize*textCount++));
		
		g.drawString("High Score: " + textHighScore, fontStartX, fontStartY+(fontSize*textCount++));
		g.drawString(newHighScore, fontStartX, fontStartY+(fontSize*textCount++));
		if (Integer.parseInt(lives) >= 0) {
			g.drawString("Lives left: " + lives,fontStartX,fontStartY+(fontSize*textCount++));
			textCount++; // line break
			g.drawString("Press any key to continue", fontStartX, fontStartY+(fontSize*textCount++));
		} else {
			g.drawString("No lives left!",fontStartX,fontStartY+(fontSize*textCount++));
			textCount++; // line break
			g.drawString("Press any key to play again", fontStartX, fontStartY+(fontSize*textCount++));
		}

		textCount++; // line break
		g.drawString("Press q to quit",fontStartX, fontStartY+(fontSize*textCount++));
		g.drawString("Press o for options",fontStartX, fontStartY+(fontSize*textCount++));
    			
	}

	private void displayGame(Graphics g) {
		displayGameGrid(g);
		displaySnake(g);
		displayKibble(g);
		displayWalls(g);
	}

	private void displayGameGrid(Graphics g) {

		int maxX = SnakeGame.xPixelMaxDimension;
		int maxY= SnakeGame.yPixelMaxDimension;
		int squareSize = SnakeGame.squareSize;

		g.setColor(colors.boardColor);
		fillEntireBoard(g);

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

	private void displayWalls(Graphics g) {

		// Draw the wall in wall Color
		g.setColor(colors.wallColor);

		for (Wall wall : walls) {
			int x = wall.getWallX() * SnakeGame.squareSize;
			int y = wall.getWallY() * SnakeGame.squareSize;

			g.fillRect(x,y,SnakeGame.squareSize,SnakeGame.squareSize);
		}


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
		int textCount = 0;

		g.setColor(colors.boardColor);
		fillEntireBoard(g);

		g.setColor(colors.fontColor);
        g.drawString("Press any key to begin!",fontStartX,fontStartY+(fontSize*textCount++));
        g.drawString("Press q to quit the game",fontStartX,fontStartY+(fontSize*textCount++));
		g.drawString("Press o to see options.",fontStartX,fontStartY+(fontSize*textCount++));
    	}

	private void fillEntireBoard(Graphics g) {
		g.fillRect(0,0,SnakeGame.xPixelMaxDimension,SnakeGame.yPixelMaxDimension);
	}

	private void clearEntireBoard(Graphics g) {
		g.clearRect(0,0,SnakeGame.xPixelMaxDimension,SnakeGame.yPixelMaxDimension);
	}
}

