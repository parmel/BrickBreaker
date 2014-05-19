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



//      System.out.println();
//        int[][] arr = {
//                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
//                {0,5,5,0,0,5,5,0,0,0,0,0,0,0,0,15,15,0,0,0},
//                {0,0,0,5,5,0,0,5,5,0,0,0,10,10,0,0,0,15,15,0},
//                {0,0,0,10,10,0,0,0,0,10,10,0,0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,20,20,0,0,0,0,0,0,0,0,20,0,20,0,0},
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
//                {0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0},
//        };

        int[][] testArr = {
                {0,0,1,1,0,0},
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
        };


        Scanner input = new Scanner(System.in);
        // public Player(int maxX,int maxY, int length, Scanner input)
       Player player = new Player(testArr[0].length, testArr.length, 2, input);

        // public Ball(int y, Direction direction, int x)
        Ball ball = new Ball(testArr[0].length , testArr.length, Direction.upRight);

        //Field(int rows, int cols, Player player, Ball ball)

        Field field = new Field(testArr, player, ball);

        print(field.getField());
        while(true){
            player.move();
            ball.move();
            field.nextMove(player, ball);
            print(field.getField());
        }
    }

    private static void print(int[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                System.out.print(field[i][j]);
            }
            System.out.println();
        }
    }
}
