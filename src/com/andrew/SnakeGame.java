package com.andrew;

import java.util.Timer;

import javax.swing.*;

/** Snake, based on the old Nokia cell phone game. Written by Clara and modified by Andrew, originally for Java
 * class at Minneapolis Community & Technical College.
 */

public class SnakeGame {

	// Set pixels and game grid in squares
	public final static int xPixelMaxDimension = 501;  //Pixels in window. 501 to have 50-pixel squares plus 1 to draw a border on last square
	public final static int yPixelMaxDimension = 501;
	public static int xSquares ;    //How many squares in the grid?
	public static int ySquares ;


	//FINDBUG - Package Protected Start -
	//The fields below should be package protected. But I'm not sure exactly what that means, so leaving this alone for now.
	protected static Snake snake ;
	protected static GameComponentManager componentManager;
	protected static Score score;
	protected static Life life;
	protected static Timer timer;
	protected static GameClock clockTick;

	// Default settings, the below are all changeable in SettingsGUI
	public static int squareSize = 25; // How many pixels the game grid squares are
	protected static int wallCount = 3; // How many walls on the grid
	protected static String colorChosen = "Nokia2"; // board color palette
	protected static int startingLife = 5;
	protected static boolean wrap = false; // if true then snake wraps around board
	protected static int snakeGrowthRate = 1; // how many squares the snake grows with each kibble
	protected static long clockInterval = 200; //controls game speed
	//Every time the clock ticks, the snake moves
	//This is the time between clock ticks, in milliseconds
	//1000 milliseconds = 1 second.

	//FINDBUG - Package Protected End -

	static final int BEFORE_GAME = 1;
	static final int DURING_GAME = 2;
	static final int GAME_OVER = 3;
	static final int GAME_WON = 4;
	//The numerical values of these variables are not important. The important thing is to use the constants
	//instead of the values so you are clear what you are setting. Easy to forget what number is Game over vs. game won
	//Using constant names instead makes it easier to keep it straight. Refer to these variables 
	//using statements such as SnakeGame.GAME_OVER 

	private static int gameStage = BEFORE_GAME;  //use this to figure out what should be happening. 
	//Other classes like Snake and DrawSnakeGamePanel will query this, and change its value

	static JFrame snakeFrame;
	static DrawSnakeGamePanel snakePanel;
	//Framework for this class adapted from the Java Swing Tutorial, FrameDemo and Custom Painting Demo. You should find them useful too.
	//http://docs.oracle.com/javase/tutorial/displayCode.html?code=http://docs.oracle.com/javase/tutorial/uiswing/examples/components/FrameDemoProject/src/components/FrameDemo.java
	//http://docs.oracle.com/javase/tutorial/uiswing/painting/step2.html

	public static final SettingsGUI gui = new SettingsGUI();
	//FINDBUGS - malicious code vulnerability, SnakeGame.gui was not final and should be

	public static void main(String[] args) {

		//Schedule a job for the event-dispatching thread:
		//creating and showing this application's GUI.
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				initializeGame();
				createAndShowGUI();
			}
		});
	}

	protected static void createAndShowGUI() {
		//Create and set up the window.

		snakeFrame = new JFrame();
		snakeFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // application closes when window is closed

		snakeFrame.setSize(xPixelMaxDimension, yPixelMaxDimension);
		snakeFrame.setUndecorated(true); //hide title bar
		snakeFrame.setVisible(true);
		snakeFrame.setResizable(false);

		snakePanel = new DrawSnakeGamePanel(componentManager);

		snakePanel.setFocusable(true);
		snakePanel.requestFocusInWindow(); //required to give this component the focus so it can generate KeyEvents

		snakeFrame.add(snakePanel);

		//Add listeners to listen for key presses
		snakePanel.addKeyListener(new GameControls());
		snakePanel.addKeyListener(new SnakeControls(snake));

		setGameStage(BEFORE_GAME);

		snakeFrame.setVisible(true);
	}

	/** Sets up the basic game data, including the size of the game board, snake and kibble objects, and score. */
	protected static void initializeGame() {

		//set up score, snake and first kibble
		xSquares = xPixelMaxDimension / squareSize;
		ySquares = yPixelMaxDimension / squareSize;

		componentManager = new GameComponentManager();

		snake = new Snake(xSquares, ySquares);
		componentManager.addSnake(snake);

		Wall[] walls = new Wall[wallCount];
		for (int i = 0; i < wallCount; ++i) {
			do {
				walls[i] = new Wall();
			} while (snake.isThisInSnake(walls[i].getSquare()));
		}
		Kibble kibble = new Kibble();
		score = new Score();
		life = new Life(startingLife);

		// Pass snake and kibble to the component manager to deal with them from now on
		componentManager.addKibble(kibble);
		componentManager.addScore(score);
		componentManager.addWalls(walls);
		componentManager.addLife(life);
	}

	/** Runs when a new game starts. This is distinct from when the program starts */
	protected static void newGame() {

		timer = new Timer();
		clockTick = new GameClock(componentManager, snakePanel);
		timer.scheduleAtFixedRate(clockTick, 0, clockInterval); // Sets up schedule for timer, using clockInterval
		gameStage = DURING_GAME; // game has started

		// if the player has lives left, let them continue from where they were
		if (componentManager.getLife().getLives() < startingLife && componentManager.getLife().getLives() >= 0) {
			componentManager.continueGame(); // Sets snake back to where it was 1 position previous
		} else {
			componentManager.newGame(); // Restarts score, snake
		}
	}


	public static int getGameStage() {
		return gameStage;
	}

	public static void setGameStage(int gameStage) {
		SnakeGame.gameStage = gameStage;
	}
}
