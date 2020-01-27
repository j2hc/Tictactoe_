import java.util.*;
import java.math.*;
public class Tictactoe2
{
    public static void main(String[] args)
    {
        Board2 board2 = new Board2();
        Scanner engine = new Scanner(System.in);
        boolean x = true;
        while(x)
        {
            System.out.println("Please input a number between 1-9 for the corresponding slot");
            int temp = engine.nextInt();
            board2.turn(temp);


        }
    }
}
