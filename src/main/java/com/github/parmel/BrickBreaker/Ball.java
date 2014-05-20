package com.github.parmel.BrickBreaker;

/**
 * Created by sobieski on 5/18/14.
 */
public class Ball implements MovebleITems {
    public Direction getDirection() {
        return direction;
    }

    private Direction direction;
    private int x;
    private int y;

    public Ball(int x,int y, Direction direction) {
        this.y = y - 2;
        this.direction = direction;
        this.x = x / 2;
    }



    @Override
    public void move() {
//        switch (this.direction) {
//            case upLeft:
//                this.x--;
//                this.y--;
//                break;
//            case upRight:
//                this.x++;
//                this.y--;
//                break;
//            case downLeft:
//                this.x--;
//                this.y++;
//                break;
//            case downRight:
//                this.x++;
//                this.y++;
//                break;
//        }
    }

    @Override
    public int[] getYX() {
        return new int[]{this.y, this.x};
    }

    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
