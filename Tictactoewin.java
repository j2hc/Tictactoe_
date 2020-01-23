import java.util.*;
import java.math.*;
public class Tictactoewin
{
    public static void main(String[] args)
    {
        Board board = new Board();
        Scanner engine = new Scanner(System.in);
        boolean x = false;
        int temp;
        if(Math.random()%2 !=0)
        {
            board.setFirst(false);
        }
        String[] Show = board.getShow();
        System.out.println("_______\n|" + Show[0]+"|"+ Show[1]+"|"+ Show[2]+"|\n|"+Show[3]+"|"+Show[4]+"|"+Show[5]+"|\n|"+Show[6]+"|"+Show[7]+"|"+Show[8]+"|\n_______");
        while(!x)
        {
            System.out.println("Please input a number between 1-9 for the corresponding slot");
            temp = engine.nextInt();
            board.turn(temp);
            Show = board.getShow();
            System.out.println("_______\n|" + Show[0]+"|"+ Show[1]+"|"+ Show[2]+"|\n|"+Show[3]+"|"+Show[4]+"|"+Show[5]+"|\n|"+Show[6]+"|"+Show[7]+"|"+Show[8]+"|\n_______");
            x = board.isWin();
            if(x)
            {
                x = true;
            }
        }












    }
}
