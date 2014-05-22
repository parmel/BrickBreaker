package com.github.parmel.BrickBreaker;

import com.googlecode.lanterna.terminal.Terminal;

/**
 * Created by Emrah on 5/15/2014.
 */
public class GameEngine {

	private static GUI GUI;

    private static Terminal terminal;

    private static Player player;

    private static Ball ball;

    private static Field field;

    public static void main(String[] args) {
        runEngine();
    }

    public static void runEngine() {
        initFirstLevel();
        GUI.render(field.getField(), player);
        boolean isEndOfGame = true;
        int ballSlower = 0;
        while (true) {
            player.move();
            if (ballSlower++ == 10) {
                ball.move();
                ballSlower = 0;
            }
            GUI.render(field.getField(), player);
            isEndOfGame = field.nextMove(player, ball);

            if (isEndOfGame) {
                //System.out.println("End of game");
                if (field.isEndLevel()) {
                    startNextLevel(player.getLevel());
                }
                if (field.isBallOut()) {
                    if(player.getLives() > 1){
                        player.setLives(player.getLives() - 1);
                        startNextLevel(player.getLevel() - 1);
                        GUI.render(field.getField(), player);
                    }
                    else {
                        GUI.reset();
                        GUI.messageBox("Game over!");
                        break;
                    }
                }
            }
            player.setPoints(player.getPoints() + field.getPoints());

            try {
                Thread.sleep(15);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private static void initFirstLevel() {
        int firstLevel = 1;
        byte[][] firstLevelField = GetNextLevel.getLevel(1);
        GUI = new GUI(firstLevelField[0].length, firstLevelField.length);
        terminal = GUI.getTerminal();
        player = new Player(firstLevelField[0].length, firstLevelField.length, 5, firstLevel, terminal,3);
        ball = new Ball(firstLevelField[0].length, firstLevelField.length, Direction.upRight);
        field = new Field(firstLevelField, player, ball);
    }

    private static void startNextLevel(int level) {
        int nextLevel = level + 1;
        byte[][] firstLevelField = GetNextLevel.getLevel(nextLevel);
        ball = new Ball(firstLevelField[0].length, firstLevelField.length, Direction.upRight);
        player.newCoordinatsOfPlayer(firstLevelField[0].length, firstLevelField.length, 5, nextLevel);
        field = new Field(firstLevelField, player, ball);

    }
}
