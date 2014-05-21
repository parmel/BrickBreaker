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

    private static void getFirstLevel() {
        int firstLevel = 1;
        byte[][] firstLevelField = GetNextLevel.getLevel(1);
        UI = new UserInterface(firstLevelField[0].length, firstLevelField.length);
        terminal = UI.getTerminal();
        player = new Player(firstLevelField[0].length, firstLevelField.length, 5, firstLevel, terminal,3);
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
        UI.render(field.getField(), player);
        boolean isEndOfGame = true;
        int ballSlower = 0;
        while (true) {

            player.move();
            if (ballSlower++ == 10) {
                ball.move();
                ballSlower = 0;
            }
            UI.render(field.getField(), player);
            isEndOfGame = field.nextMove(player, ball);

            if (isEndOfGame) {
                System.out.println("End of game");
                if (field.isEndLevel()) {
                    startNextLevel(player.getLevel());
                }
                if (field.isBallOut()) {
                    if(player.getLives() > 1){
                        player.setLives(player.getLives() - 1);
                        startNextLevel(player.getLevel() - 1);
                        UI.render(field.getField(), player);
                    }
                    else {
                        //TODO:add some end menu or something else
                    }
                    ///remove lives
                }
            }
            player.setPoints(player.getPoints() + field.getPoints());


            // Sleep in order to maintain reasonable CPU load
            try {
                Thread.sleep(15);
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
