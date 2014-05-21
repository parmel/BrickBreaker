package com.github.parmel.BrickBreaker;

import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;

/**
 * Created by Emrah Bekir on 5/18/14.
 */
public class Player implements MovebleITems {
    private int x;
    private int y;
    private int maxX;
    private int points;
    private int level;
    private int length;
    private Terminal input;

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        if(lives > 0)
        this.lives = lives;
        else {
            throw new IllegalArgumentException("Level must be bigger than zero");
        }
    }

    private int lives;
    //TODO: add terminal to read input
    public Player(int maxX, int maxY, int length, int level, Terminal input, int lives) {
        this.lives = lives;
        this.length = length;
        this.maxX = maxX;
        this.x = (maxX / 2) - (length / 2);
        this.y = maxY - 1;
        this.input = input;
        this.setLevel(level);
    }

    public void newCoordinatsOfPlayer(int maxX, int maxY, int length, int level) {
        this.length = length;
        this.maxX = maxX;
        this.x = (maxX / 2) - (length / 2);
        this.y = maxY - 1;
        this.setLevel(level);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        if (x >= 0 && x + length <= maxX) {
            this.x = x;
        }
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLength() {
        return length;
    }

    @Override
    public void move() {
        //TODO: add correct move implementation
        Key key = this.input.readInput();

        if (key != null) {
            if (key.getKind().equals(Key.Kind.ArrowLeft)) {
                this.setX(this.getX() - 1);
            } else if (key.getKind().equals(Key.Kind.ArrowRight)) {
                this.setX(this.getX() + 1);
            }
        }
        while (key != null) {
            key = this.input.readInput();
        }
//        if(nextline.equals("a")){
//            this.setX(this.getX() - 1);
//        }
//        else if(nextline.equals("d")){
//            this.setX(this.getX() + 1);
//        }
        //if has input
        //choose left or right
        //this.getX() + 1 to move right
        //this.get() - 1 to move left

    }

    @Override
    public int[] getYX() {
        return new int[]{this.y, this.x};
    }

    //Player do not need this implementation
    @Override
    public void setDirection(Direction direction) {

    }
}
