package com.andrew;

/**
 * Created by Clara. Manages game components such as the Snake, Kibble... and their interactions.
 */
public class GameComponentManager {

    private Kibble kibble;
    private Snake snake;
    private Score score;
    private Wall[] walls;
    private Life life;

    /** Called every clock tick. Tell components to interact/update,
     * manage interactions, update score etc.
     * If there were more components - e.g walls, mazes,
     * different types of kibble/prizes, different scoring systems...
     * they could be managed here too
     */
    public void update() {

        snake.previousSnakeSquares = snake.getSnakeSquares();
        snake.moveSnake();



        // Check if snake hit wall
        for (Wall wall : walls) {
            if (snake.isThisInSnake(wall.getSquare())) {
                SnakeGame.setGameStage(SnakeGame.GAME_OVER);
            }
        }
        //Ask the snake if it is on top of the kibble
        if (snake.isThisInSnake(kibble.getSquare())) {

            if (kibble.isHeart()) {
                int currentLives = life.getLives();
                life.setLives(++currentLives);
                kibble.setHeart(false);
            } else {
                //If so, tell the snake that it ate the kibble
                snake.youAteKibble();
            }
            //And, update the kibble - move it to a new square. Got to check to make sure
            //that the new square is not inside the snake.
            Square kibbleLoc;
            do {
                kibbleLoc = kibble.moveKibble();
                if (snake.getSnakeSquares().size() + 1 == (SnakeGame.xPixelMaxDimension * SnakeGame.yPixelMaxDimension)) {
                    break;
                }
            } while (snake.isThisInSnake(kibbleLoc) || isInAnyWalls(kibbleLoc));

            score.increaseScore();
		}

    }

    public void newGame() {

        score.resetScore();
        life.setLives(SnakeGame.startingLife);
        snake.createStartSnake();

    }

    public void continueGame() {
        snake.setSnakeSquares(snake.previousSnakeSquares); // Sets snake to where it was one tick previously

    }


    public boolean isInAnyWalls(Square testSquare) {
        for (Wall wall : walls) {
            if (wall.isThisInWall(testSquare)) {
                return true;
            }
        }
        return false;
    }

    protected void addKibble(Kibble kibble) {
        this.kibble = kibble;
    }
    protected void addSnake(Snake snake) {
        this.snake = snake;
    }
    protected void addScore(Score score) {
        this.score = score;
    }
    protected void addWalls(Wall[] walls) {
        this.walls = walls;
    }
    protected void addLife(Life life) { this.life = life; }

    public Score getScore() {
        return score;
    }
    public Kibble getKibble() {
        return kibble;
    }
    public Snake getSnake() {
        return snake;
    }
    public Wall[] getWalls() {
        return walls;
    }
    public Life getLife() { return life; }

}
