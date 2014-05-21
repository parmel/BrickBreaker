package com.github.parmel.BrickBreaker;

/**
 * Created by sobieski on 5/18/14.
 */
public class Field {
    private static final byte BALL_INITIALS = -1;
    private static final byte PLAYER_INITIALS = -2;
    private byte[][] field;
    private int ballLastYX[];
    private int playerLastYX[];
    private int playerLength;
    private int points = 0;
    private boolean isBallOut;
    private boolean isEndLevel;

    public Field(int level, Player player, Ball ball) {
//        this.field = GetNextLevel.
//        this.field = new byte[rows][cols];
        this.playerLength = player.getLength();
        addNewPleaces(player, ball);
        putInField(PLAYER_INITIALS, BALL_INITIALS);
    }

    //test constructor
    public Field(byte[][] field, Player player, Ball ball) {
        this.field = field;
        this.playerLength = player.getLength();
        addNewPleaces(player, ball);
        putInField(PLAYER_INITIALS, BALL_INITIALS);
    }

    public int getPoints() {
        int newPoints = points;
        points = 0;
        return newPoints;
    }

    public boolean isBallOut() {
        boolean returnValue = isBallOut;
        isBallOut = false;
        return returnValue;
    }

    public boolean isEndLevel() {
        boolean returnValue = isEndLevel;
        isEndLevel = false;
        return returnValue;
    }

    public byte[][] getField() {
        return field;
    }

    private void putInField(byte player, byte ball) {
        this.field[ballLastYX[0]][ballLastYX[1]] = ball;
        for (int i = playerLastYX[1]; i < playerLastYX[1] + playerLength; i++) {
            this.field[playerLastYX[0]][i] = player;
        }
    }


    public boolean nextMove(Player player, Ball ball) {
        //Clear old place of ball and player
        putInField((byte) 0, (byte) 0);
        addNewPleaces(player, ball);
        putInField(PLAYER_INITIALS, BALL_INITIALS);
        this.isBallOut = checkBall(ball);
        this.isEndLevel = checkIsClearSpace(this.field);
        return isBallOut || isEndLevel;
    }

    private boolean checkIsClearSpace(byte[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if (field[i][j] > 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkBall(Ball ball) {
        if (ballLastYX[0] == this.field.length - 1) {
            return true;
        } else if (ballLastYX[0] == field.length - 2 && field[ballLastYX[0] + 1][ballLastYX[1]] == -2) {
            changeDir(ball);
        }else if(ballLastYX[0] == 0){
            reverseDirection(ball);
        }
        else {
            if (ballLastYX[0] == 0 ||
                    ballLastYX[1] == 0 ||
                    ballLastYX[1] == field[0].length - 1) {
                changeDir(ball);
            } else if (ball.getDirection() == Direction.upRight) {
                if (field[ballLastYX[0] - 1][ballLastYX[1] + 1] != 0) {
                    field[ballLastYX[0] - 1][ballLastYX[1] + 1] -= 5;
                    points += 5;
                    changeDir(ball);
                }
            } else if (ball.getDirection() == Direction.upLeft) {
                if (field[ballLastYX[0] - 1][ballLastYX[1] - 1] != 0) {
                    field[ballLastYX[0] - 1][ballLastYX[1] - 1] -= 5;
                    points += 5;
                    changeDir(ball);
                }
            } else if (ball.getDirection() == Direction.downRight) {
                if (field[ballLastYX[0] + 1][ballLastYX[1] + 1] != 0) {
                    field[ballLastYX[0] + 1][ballLastYX[1] + 1] -= 5;
                    points += 5;
                    changeDir(ball);
                }
            } else if (ball.getDirection() == Direction.downLeft) {
                if (field[ballLastYX[0] - 1][ballLastYX[1] + 1] != 0) {
                    field[ballLastYX[0] - 1][ballLastYX[1] + 1] -= 5;
                    points += 5;
                    changeDir(ball);
                }
            }
        }

        return false;
    }

    private void reverseDirection(Ball ball) {
        if(ball.getDirection() == Direction.downLeft){
            ball.setDirection(Direction.upRight);
        }else if(ball.getDirection() == Direction.upRight){
            ball.setDirection(Direction.downLeft);
        }else if(ball.getDirection() == Direction.upLeft){
            ball.setDirection(Direction.downLeft);
        }else if(ball.getDirection() == Direction.downLeft){
            ball.setDirection(Direction.upLeft);
        }
    }

    private void changeDir(Ball ball) {
        if (ball.getDirection() == Direction.downLeft) {
            downLeftDirection(ball);
        } else if (ball.getDirection() == Direction.downRight) {
            downRightDirection(ball);
        } else if (ball.getDirection() == Direction.upLeft) {
            upLeftDirection(ball);
        } else if (ball.getDirection() == Direction.upRight) {
            upRightDirection(ball);
        }
    }

    private void downLeftDirection(Ball ball) {
        if (canGoDownRight(ball)) {
            return;
        } else if (canGoUpLeft(ball)) {
            return;
        } else if (canGoUpRight(ball)) {
            return;
        }
    }

    private void downRightDirection(Ball ball) {
        if (canGoDownLeft(ball)) {
            return;
        } else if (canGoUpRight(ball)) {
            return;
        } else if (canGoUpLeft(ball)) {
            return;
        }
    }


    private void upLeftDirection(Ball ball) {
        if (canGoDownLeft(ball)) {
            return;
        } else if (canGoUpRight(ball)) {
            return;
        } else if (canGoDownRight(ball)) {
            return;
        } else {
            //TODO:Test it
        }
    }

    //Moving upRright direction
    private void upRightDirection(Ball ball) {
        if (canGoDownRight(ball)) {
            return;
        } else if (canGoUpLeft(ball)) {
            return;
        } else if (canGoUpLeft(ball)) {
            return;
        } else {
            //TODO:Test it
        }
    }

    private boolean canGoDownLeft(Ball ball) {
        if (ballLastYX[0] + 1 >= 0 &&
                ballLastYX[0] + 1 < this.field.length &&
                ballLastYX[1] - 1 >= 0 &&
                ballLastYX[1] - 1 < this.field[0].length &&
                field[ballLastYX[0] + 1][ballLastYX[1] - 1] == 0) {

            ball.setDirection(Direction.downLeft);
            return true;
        } else {
            return false;
        }
    }

    private boolean canGoDownRight(Ball ball) {
        if (ballLastYX[0] + 1 >= 0 &&
                ballLastYX[0] + 1 < this.field.length &&
                ballLastYX[1] + 1 >= 0 &&
                ballLastYX[1] + 1 < this.field[0].length &&
                field[ballLastYX[0] + 1][ballLastYX[1] + 1] == 0) {

            ball.setDirection(Direction.downRight);
            return true;
        } else {
            return false;
        }
    }

    private boolean canGoUpLeft(Ball ball) {
        if (ballLastYX[0] - 1 >= 0 &&
                ballLastYX[0] - 1 < this.field.length &&
                ballLastYX[1] - 1 >= 0 &&
                ballLastYX[1] - 1 < this.field[0].length &&
                this.field[ballLastYX[0] - 1][ballLastYX[1] - 1] == 0) {

            ball.setDirection(Direction.upLeft);
            return true;
        } else {
            return false;
        }
    }

    private boolean canGoUpRight(Ball ball) {
        if (ballLastYX[0] - 1 >= 0 &&
                ballLastYX[0] - 1 < this.field.length &&
                ballLastYX[1] + 1 >= 0 &&
                ballLastYX[1] + 1 < this.field[0].length &&
                field[ballLastYX[0] - 1][ballLastYX[1] + 1] == 0) {

            ball.setDirection(Direction.upRight);
            return true;
        } else {
            return false;
        }
    }

    private boolean canGoOutside() {
        if (ballLastYX[0] > 0 &&
                ballLastYX[1] > 0 &&
                ballLastYX[0] < field.length - 1 &&
                ballLastYX[1] < field[0].length - 1) {
            return true;
        } else {
            return false;
        }
    }

    private void addNewPleaces(Player player, Ball ball) {
        this.ballLastYX = ball.getYX();
        this.playerLastYX = player.getYX();
    }


}
