/**
 * Created by sobieski on 5/18/14.
 */
public class Field {
    public int[][] getField() {
        return field;
    }

    private int field[][];
    private int ballLastYX[];
    private int playerLastYX[];
    private int playerLength;
    public static final int BALL_INITIALS = 5;
    public static final int PLAYER_INITIALS = 1;

    public Field(int rows, int cols, Player player, Ball ball) {
        this.field = new int[rows][cols];
        this.playerLength = player.getLength();
        addNewPleaces(player, ball);
        putInField(PLAYER_INITIALS, BALL_INITIALS);
    }

    //test constructor
    public Field(int[][] field, Player player, Ball ball){
        this.field = field;
        this.playerLength = player.getLength();
        addNewPleaces(player, ball);
        putInField(PLAYER_INITIALS, BALL_INITIALS);
    }

    private void putInField(int player, int ball) {
        this.field[ballLastYX[0]][ballLastYX[1]] = ball;
        for (int i = playerLastYX[1]; i < playerLastYX[1] + playerLength; i++) {
            this.field[playerLastYX[0]][i] = player;
        }
    }


    public boolean nextMove(Player player, Ball ball) {
        //Clear old place of ball and player
        putInField(0, 0);
        addNewPleaces(player, ball);
        putInField(PLAYER_INITIALS, BALL_INITIALS);
        boolean isBallOut = checkBall(ball);

        //TODO: add check is have more items in array
        return isBallOut;
    }

    private boolean checkBall(Ball ball) {
        if (ballLastYX[0] == this.field.length - 1) {
            return false;
        }
        else if(ballLastYX[0] + 1 ==  field.length - 1 && field[ballLastYX[0] + 1][ballLastYX[1]] == 1) {
            changeDir(ball);
            int a = 5;
        }else {
            if(ballLastYX[0] == 0 ||
                    ballLastYX[1] == 0 ||
                    ballLastYX[0] == field.length -1 ||
                    ballLastYX[1] == field[0].length -1){
                changeDir(ball);
            }
            else if (ball.getDirection() == Direction.upRight) {
                if(field[ballLastYX[0] + 1][ballLastYX[1] - 1] != 0) {
                    field[ballLastYX[0]][ballLastYX[1]] -= 5;
                    changeDir(ball);
                }
            }else if (ball.getDirection() == Direction.upLeft) {
                if(field[ballLastYX[0] - 1][ballLastYX[1] - 1] != 0) {
                    field[ballLastYX[0]][ballLastYX[1]] -= 5;
                    changeDir(ball);
                }
            }else if (ball.getDirection() == Direction.downRight) {
                if(field[ballLastYX[0] + 1][ballLastYX[1] + 1] != 0) {
                    field[ballLastYX[0]][ballLastYX[1]] -= 5;
                    changeDir(ball);
                }
            }else if (ball.getDirection() == Direction.downLeft) {
                if(field[ballLastYX[0] - 1][ballLastYX[1] + 1] != 0) {
                    field[ballLastYX[0]][ballLastYX[1]] -= 5;
                    changeDir(ball);
                }
            }
        }

        return true;
    }

    private void changeDir(Ball ball) {
        if (ball.getDirection() == Direction.downLeft) {
            if (canGoOutside() && field[ballLastYX[0] - 1][ballLastYX[1] - 1] == 0 ) {
                ball.setDirection(Direction.upLeft);
            } else {
                ball.setDirection(Direction.upRight);
            }
        } else if(ball.getDirection() == Direction.downRight) {
            if (canGoOutside() && field[ballLastYX[0] + 1][ballLastYX[1] - 1] == 0) {
                ball.setDirection(Direction.upRight);
            } else {
                ball.setDirection(Direction.upLeft);
            }
        } else if(ball.getDirection() == Direction.upLeft) {
            if (canGoOutside() && field[ballLastYX[0] - 1][ballLastYX[1] + 1] == 0) {
                ball.setDirection(Direction.downLeft);
            } else {
                ball.setDirection(Direction.downRight);
            }
        }        else if(ball.getDirection() == Direction.upRight) {
            if (canGoOutside() && field[ballLastYX[0] + 1][ballLastYX[1] - 1] == 0) {
                ball.setDirection(Direction.downRight);
            } else {
                ball.setDirection(Direction.downLeft);
            }
        }
    }

    private boolean canGoOutside(){
        if(ballLastYX[0] > 0 &&
                ballLastYX[1] > 0 &&
                ballLastYX[0] < field.length -1 &&
                ballLastYX[1] < field[0].length -1){
            return true;
        }
        else{
            return false;
        }
    }

    private void addNewPleaces(Player player, Ball ball) {
        this.ballLastYX = ball.getYX();
        this.playerLastYX = player.getYX();
    }


}