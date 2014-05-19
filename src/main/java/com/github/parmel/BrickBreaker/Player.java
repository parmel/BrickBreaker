package com.github.parmel.BrickBreaker;

import java.util.Scanner;

/**
 * Created by Emrah Bekir on 5/18/14.
 */
public class Player implements  MovebleITems{
    public int getX() {
        return x;
    }

    public void setX(int x) {
        if(x >= 0 && x + length <= maxX){
            this.x = x;
        }
    }

    private int x;
    private int y;
    private int maxX;

    public int getLength() {
        return length;
    }

    private int length;
    private Scanner input;

    //TODO: add terminal to read input
    public Player(int maxX,int maxY, int length, Scanner input) {
        this.length = length;
        this.maxX = maxX;
        this.x = (maxX / 2) - (length / 2);
        this.y = maxY - 1;
        this.input = input;
    }


    @Override
    public void move() {
        //TODO: add correct move implementation
        String nextline = this.input.nextLine();

        if(nextline.equals("a")){
            this.setX(this.getX() - 1);
        }
        else if(nextline.equals("d")){
            this.setX(this.getX() + 1);
        }
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
