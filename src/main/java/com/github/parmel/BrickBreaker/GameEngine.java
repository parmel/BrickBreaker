package com.github.parmel.BrickBreaker;

import com.googlecode.lanterna.terminal.Terminal;

/**
 * Created by Emrah on 5/15/2014.
 */
public class GameEngine {
    private static UserInterface UI;

    // Get terminal
    private static Terminal terminal;

    // public Player(int maxX,int maxY, int length, Scanner input)
    private static Player player;

    // public Ball(int y, Direction direction, int x)
    private static Ball ball;

    //Field(int rows, int cols, Player player, Ball ball)

    private static Field field;

    // Initiate new Status instance with 0 points and 5 lives
    private static Status status = new Status(0, 3);

    private static void getFirstLevel() {
        int firstLevel = 1;
        byte[][] firstLevelField = GetNextLevel.getLevel(1);
        UI = new UserInterface(firstLevelField[0].length, firstLevelField.length);
        terminal = UI.getTerminal();
        player = new Player(firstLevelField[0].length, firstLevelField.length, 5, firstLevel, terminal);
        ball = new Ball(firstLevelField[0].length, firstLevelField.length, Direction.upRight);
        field = new Field(firstLevelField, player, ball);
    }

    public static void main(String[] args) {
        runEngine();
//    	/*
//    	 * BOF UI Test
//    	 */
//
//    	/*
//    	 * Array legend:
//    	 * 
//    	 * Signed bytes - special meaning
//    	 * 		-1 - ball
//    	 * 		-2 - springboard
//    	 *  
//    	 * Unsigned bytes - bricks (byte number is equal to remaining hits)
//    	 * 		1 - green
//    	 * 		2 - cyan
//    	 * 		3 - magenta
//    	 * 		4 - yellow
//    	 * 		5 - red
//    	 */

    }

    public static void runEngine() {
        getFirstLevel();
        UI.render(field.getField(), status);
        boolean isEndOfGame = true;
        int ballSlower = 0;
        while (true) {

            player.move();
            if (ballSlower++ == 10) {
                ball.move();
                ballSlower = 0;
            }
            UI.render(field.getField(), status);
            UI.render(field.getField(), status);
            UI.render(field.getField(), status);
            isEndOfGame = field.nextMove(player, ball);

            if (isEndOfGame) {
                System.out.println("End of game");
                if (field.isEndLevel()) {
                    startNextLevel(player.getLevel());
                }
                if (field.isBallOut()) {
                    //TODO:add some end menu or something else
                    ///remove lives
                }
            }
            player.setPoints(player.getPoints() + field.getPoints());


//            status.setPoints(status.getPoints() + 1);
//            status.setLives(status.getLives() - 1);
//            UI.render(field.getField(), points);

            // Sleep in order to maintain reasonable CPU load
            try {
                Thread.sleep(25);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private static void startNextLevel(int level) {
        int nextLevel = level + 1;
        byte[][] firstLevelField = GetNextLevel.getLevel(nextLevel);
        ball = new Ball(firstLevelField[0].length, firstLevelField.length, Direction.upRight);
        player.newCoordinatsOfPlayer(firstLevelField[0].length, firstLevelField.length, 5, nextLevel);
        field = new Field(firstLevelField, player, ball);

    }
}
