import java.util.*;
public class Board2
{
    //Players are 1 and computers are 4s
    private int[][] values = {{3,0,3},{0,0,0},{3,0,3}};
    private int[][] board = {{0,0,0},{0,0,0},{0,0,0}};
    private char[] Show = {'-','-','-','-','-','-','-','-','-'};
    private int turncount =0;
    private boolean loop = true;
    private Scanner engine = new Scanner(System.in);
    //the player is first or second
    private boolean pfirst;
    //This can be run from another class to run the tictactoe game
    public void main()
    {
        displayboard();
        System.out.println("Input 1 for first 2 for second.");
        int temp = engine.nextInt();
        if (temp == 1)
        {
            pfirst = true;
        } else
        {
            pfirst = false;
        }
        while (loop)
        {
            if(turncount ==9)
            {
                System.out.println("It's a tie");
                loop =false;

            }
            if (pfirst)
            {
                System.out.println("Please input a number between 1-9 for the corresponding slot");
                temp = engine.nextInt() - 1;
                int dcol = temp % 3;
                int drow = temp / 3;
                if (board[drow][dcol] == 0)
                {
                    turn(temp);
                    loop =aiturn();
                    displayboard();
                    resetval();
                    if(!loop)
                    {
                        System.out.println("GG EZ");
                    }

                } else
                {
                    System.out.println("This is an illegal move, choose again");
                }
            } else
            {
                loop=aiturn();
                displayboard();
                if(!loop)
                {
                    System.out.println("GG EZ");
                }
                boolean y = true;
                while (y)
                {
                    System.out.println("Please input a number between 1-9 for the corresponding slot");
                    temp = engine.nextInt() - 1;
                    int dcol = temp % 3;
                    int drow = temp / 3;
                    if (board[drow][dcol] == 0)
                    {
                        turn(temp);
                        resetval();
                        y = false;
                    }
                    else
                    {
                        System.out.println("This is an illegal move, please choose again");
                    }
                }

            }


        }
    }
    //This updates the board with the players input
    private void turn(int space)
    {
        turncount += 1;
        int drow =space/3;
        int dcol = space%3;
        board[drow][dcol] = 1;
        values[drow][dcol] = -1;

    }
    //This resets the value of values array for each turn cycle, except taken squares
    private void resetval()
    {
        //this if checks and blocks a set of win conditions that i dont know how to figure out
        if(board[1][1] ==1)
        {
            values = new int[][]{{3, 0, 3}, {0, 0, 0}, {3, 0, 3}};
            for (int x = 0; x < 9; x++)
            {
                int col = x % 3;
                int row = x / 3;
                if (board[row][col] != 0)
                {
                    values[row][col] = -1;

                }
            }
        }
        else
        {
            for (int x = 0; x < 9; x++)
            {
                int col = x % 3;
                int row = x / 3;
                if (values[row][col] != -1)
                {
                    values[row][col] = 0;

                }
            }
        }
    }
    //This iterates over the number board and determines values for the blank squares
    private void generatevalues()
    {
        diags();
        for(int x =0; x<9;x++)
        {
            int vrow = x / 3;
            int vcol = x % 3;
            if(values[vrow][vcol] != -1)
            {
                {
                    switch (x)
                    {
                        case 0:
                            down(vcol);
                            right(vrow);
                            break;

                        case 1:
                            down(vcol);
                            leftright(vrow);
                            break;
                        case 2:
                            down(vcol);
                            left(vrow);
                            break;
                        case 3:
                            updown(vcol);
                            right(vrow);
                            break;
                        case 5:
                            left(vrow);
                            updown(vcol);
                            break;
                        case 6:
                            right(vrow);
                            up(vcol);
                            break;
                        case 7:
                            leftright(vrow);
                            up(vcol);
                            break;
                        case 8:
                            left(vrow);
                            up(vcol);
                            break;
                    }
                }
            }
        }
    }
    //This is the function that takes the ai's turn, adds 1 to turncount
    private boolean aiturn()
    {
        if((turncount == 1 || turncount == 2) && board[1][1] == 0)
        {
            board[1][1] = 4;
            values[1][1] = -1;

        }
        else
        {
            int trow = -100;
            int tcol = -100;
            int biggest = -100;
            generatevalues();
            for (int row = 0; row < 3; row++)
            {
                for (int col = 0; col < 3; col++)
                {
                    if (values[row][col] > biggest)
                    {
                        biggest = values[row][col];
                        trow = row;
                        tcol = col;
                        if(biggest>=100)
                        {
                            board[trow][tcol] = 4;
                            values[trow][tcol] = -1;
                            return false;
                        }
                    }
                }
            }
            board[trow][tcol] = 4;
            values[trow][tcol] = -1;
            turncount+=1;
        }
        return true;
    }
    //This just prints out the visual board
    public void displayboard()
    {
        updateboard();
        System.out.println("_______\n|" +Show[0]+"|"+ Show[1]+"|"+ Show[2]+"|\n|"+Show[3]+"|"+Show[4]+"|"+Show[5]+"|\n|"+Show[6]+"|"+Show[7]+"|"+Show[8]+"|\n_______");
    }
    //This updates the visual board from the number board
    private void updateboard()
    {
        for(int x =0; x<9;x++)
        {
            if(board[x/3][x%3] == 4)
            {
                Show[x] = 'x';
            }
            else
                if(board[x/3][x%3]== 1)
            {
                Show[x] = '0';
            }
        }
    }
    /*
        Row/Col/
        1. They check for a win
        2. They check for a block
        3. They check for if a win is possible in that row/col with 2 switches,
        4. They check for if the square sets up for a win
    */
    //checks downward
    private void down(int col)
    {
        if(values[0][col] != 100)
        {
        int below = board[1][col] + board[2][col];
            switch (below)
            {
                case 8:
                    values[0][col] = 100;
                    break;
                case 2:
                    values[0][col] = 50;
                    break;
                case 5:
                    values[0][col] -= 2;
                    break;
                case 1:
                    values[0][col] -= 2;
                    break;
                case 4:
                    values[0][col] += 2;
                    break;
            }
        }

    }
    //checks above
    private void up(int col)
    {
        if(values[2][col] != 100)
        {
            int above = board[0][col] + board[1][col];
            switch (above)
            {
                case 8:
                    values[2][col] = 100;
                    break;
                case 2:
                    values[2][col] = 50;
                    break;
                case 5:
                    values[2][col] -= 2;
                    break;
                case 1:
                    values[2][col] -= 2;
                    break;
                case 4:
                    values[2][col] += 2;
                    break;
            }
        }
    }
    //checks middle squares for updown
    private void updown(int col)
    {
        if(values[1][col] != 100)
        {
            int abovebelow = board[0][col] + board[2][col];
            switch (abovebelow)
            {
                case 8:
                    values[1][col] = 100;
                    break;
                case 2:
                    values[1][col] = 50;
                    break;
                case 5:
                    values[1][col] -= 2;
                    break;
                case 1:
                    values[1][col] -= 2;
                    break;
                case 4:
                    values[1][col] += 2;
                    break;
            }
        }
    }
    //checks left
    private void left(int row)
    {
        if(values[row][2] != 100)
        {
            int left = board[row][1] + board[row][0];
            switch (left)
            {
                case 8:
                    values[row][2] = 100;
                    break;
                case 2:
                    values[row][2] = 50;
                    break;
                case 5:
                    values[row][2] -= 2;
                    break;
                case 1:
                    values[row][2] -= 2;
                    break;
                case 4:
                    values[row][2] += 2;
                    break;


            }
        }
    }
    //checks right
    private void right(int row)
    {
        if(values[row][0] != 100)
        {
            int right = board[row][1] + board[row][2];
            switch (right)
            {
                case 8:
                    values[row][0] = 100;
                    break;
                case 2:
                    values[row][0] = 50;
                    break;
                case 5:
                    values[row][0] -= 2;
                    break;
                case 1:
                    values[row][0] -= 2;
                    break;
                case 4:
                    values[row][0] += 2;
                    break;
            }
        }
    }
    //checks middle squares for left right
    private void leftright(int row)
    {
        if(values[row][1] != 100)
        {
            int leftright = board[row][0] + board[row][2];
            switch (leftright)
            {
                case 8:
                    values[row][1] = 100;
                    break;
                case 2:
                    values[row][1] = 50;
                    break;
                case 5:
                    values[row][1] -= 2;
                    break;
                case 1:
                    values[row][1] -= 2;
                    break;
                case 4:
                    values[row][1] += 2;
                    break;
            }
        }

    }
    //checks the diagonals
    private void diags()
    {

        //this method adds the diag win conditions in dl and dr
        int dr = board[0][0] + board[1][1] + board[2][2];
        int dl = board[0][2] + board[1][1] + board[2][0];
        //each of these if checks if the top right, top left, bottom right, then bottom left(in that order),
        //are taken and if they arent a value determiner is run the same as the other value gen methods

        if(values[0][0] != 100)
        {
            if (board[0][0] == 0)
            {
                switch (dr)
                {
                    case 8:
                        values[0][0] = 100;
                        break;
                    case 2:
                        values[0][0] = 50;
                        break;
                    case 5:
                        values[0][0] -= 2;
                        break;
                    case 1:
                        values[0][0] -= 2;
                        break;
                    case 4:
                        values[0][0] += 2;
                        break;


                }
            }
        }

        if(values[0][2] != 100)
        {
            if (board[0][2] == 0)
            {
                switch (dl)
                {
                    case 8:
                        values[0][2] = 100;
                        break;
                    case 2:
                        values[0][2] = 50;
                        break;
                    case 5:
                        values[0][2] -= 2;
                        break;
                    case 1:
                        values[0][2] -= 2;
                        break;
                    case 4:
                        values[0][2] += 2;
                        break;

                }
            }
        }
        if(values[2][0] != 100)
        {
            if (board[2][0] == 0)
            {
                switch (dl)
                {
                    case 8:
                        values[2][0] = 100;
                        break;
                    case 2:
                        values[2][0] = 50;
                        break;
                    case 5:
                        values[2][0] -= 2;
                        break;
                    case 1:
                        values[2][0] -= 2;
                        break;
                    case 4:
                        values[2][0] += 2;
                        break;

                }
            }
        }
        if(values[2][2] != 100)
        {
            if (board[2][2] == 0)
            {
                switch (dr)
                {
                    case 8:
                        values[2][2] = 100;
                        break;
                    case 2:
                        values[2][2] = 50;
                        break;
                    case 5:
                        values[2][2] -= 2;
                        break;
                    case 1:
                        values[2][2] -= 2;
                        break;
                    case 4:
                        values[2][2] += 2;
                        break;

                }
            }
        }
    }
}
