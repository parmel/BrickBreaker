package com.github.parmel.BrickBreaker;

/**
 * Created by Emrah Bekir on 5/18/14.
 */
public interface MovebleITems {
    public void move();

    public int[] getYX();

    public void setDirection(Direction direction);
}
