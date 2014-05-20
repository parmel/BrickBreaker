package com.github.parmel.BrickBreaker;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * Created by Emrah on 5/15/2014.
 */
public class GameEngine {
    public static void main(String[] args) {

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
//    	byte[][] uiTestArr = {
//                {0,0,0,0,0,0,0,0,0,5,5,0,0,0,0,0,0,0,0,0},
//                {0,1,1,0,0,1,1,0,0,0,0,0,0,0,0,1,1,0,0,0},
//                {0,0,0,1,1,0,0,1,1,0,0,0,2,2,0,0,0,3,3,0},
//                {0,0,0,2,2,0,0,0,0,2,2,0,0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,4,4,0,0,0,0,0,0,0,0,4,0,4,0,0},
//                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,0,0,0,0,-1,0,0,0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,0,0,-2,-2,-2,-2,0,0,0,0,0,0,0,0,0},
//        };
//
//		// Initiate new UI object and set width to 20 and height to 20
//    	UserInterface UI = new UserInterface(20, 20);
//    	
//    	// Render the test array
//    	UI.render(uiTestArr);
//    	
//    	// Get terminal
//    	Terminal terminal = UI.getTerminal();
//    	
//    	/*
//    	 * EOF UI Test
//    	 */

        byte[][] testArr = {
                {0,0,1,1,0,0},
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
        };

		// Initiate new UI object and set width to 6 and height to 4
    	UserInterface UI = new UserInterface(6, 4);

    	// Get terminal
    	Terminal terminal = UI.getTerminal();
    	
        Scanner input = new Scanner(System.in);
        // public Player(int maxX,int maxY, int length, Scanner input)
        Player player = new Player(testArr[0].length, testArr.length, 2, input);

        // public Ball(int y, Direction direction, int x)
        Ball ball = new Ball(testArr[0].length , testArr.length, Direction.upRight);

        //Field(int rows, int cols, Player player, Ball ball)

        Field field = new Field(testArr, player, ball);
        
        // Initiate new Status instance with 0 points and 5 lives
        Status status = new Status(0, 3);

    	// Render the test array
    	UI.render(field.getField(), status);

        while(true){
        	// BOF get key code example
    		Key key = terminal.readInput();
        	if (key != null) {
        		if (key.getCharacter() == 'a') {
        			System.out.print("a");
        		} else if (key.getKind().equals(Key.Kind.ArrowRight) ) {
        			System.out.print("R");
        		}
        	}
        	// EOF get key code example

//			  // This code is commented temporarily in order for the terminal keys
//			  // example to work
//            player.move();
//            ball.move();
//            field.nextMove(player, ball);
//            status.setPoints(status.getPoints() + 1);
//            status.setLives(status.getLives() - 1);
//            UI.render(field.getField(), points);
        }
    }

    private static void print(byte[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                System.out.print(field[i][j]);
            }
            System.out.println();
        }
    }
}
