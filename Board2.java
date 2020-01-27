public class Board2
{
    //Players are 1 and computers are 4s
    private int[][] values = {{3,0,3},{0,0,0},{3,0,3}};
    private int[][] board = {{0,1,2},{0,1,2},{0,1,2}};
    private int turncount =0;

    private boolean first;

    public void setFirst(boolean first)
    {
        this.first = first;
    }

    public void turn(int space)
    {

        if(first==true)
        {

            int col = space % 3;
            int row = space / 3;
            if (board[row][col] == 0)
            {
                board[row][col] = 1;
                values[row][col] = -1;
                turncount += 1;
                aiturn();
            }
            else
            {
                System.out.println("This is an illegal move, choose again");
                return;
            }
        }
    else
        {
            int col = space % 3;
            int row = space / 3;
            if (board[row][col] == 0)
            {
                aiturn();
                board[row][col] = 1;
                values[row][col] = -1;
                turncount += 1;
            }
            else
            {
                System.out.println("This is an illegal move, choose again");
                return;
            }
        }

    }

    private void generatevalues()
    {
        diags();
        for(int x =0; x<9;x++)
        {
            int vcol = x % 3;
            int vrow = x / 3;
            {
                switch(x)
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
    private void aiturn()
    {
        if((turncount == 1 || turncount == 3) && board[2][2] == 0)
        {
            board[2][2] = 4;
            values[2][2] =-1;
            turncount +=1;
        }
        else
        {
            int trow = 0;
            int tcol = 0;
            int biggest = 0;
            generatevalues();
            for (int row = 0; row < 9; row++)
            {
                for (int col = 0; col < 9; col++)
                {
                    if (values[row][col] > biggest)
                    {
                        biggest = values[row][col];
                        trow = row;
                        tcol = col;
                    }
                }
            }
            board[trow][tcol] = 4;
            values[trow][tcol] = -1;
            turncount += 1;
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
        int below = board[1][col] + board[2][col];
        switch(below)
        {
            case 8: values[0][col] = 100;
                break;
            case 2: values[0][col] = 50;
                break;
            case 5: values[0][col] -=3;
                break;
            case 1: values[0][col] -=3;
                break;
            case 4: values[0][col] +=3;
                break;
        }

    }
    //checks above
    private void up(int col)
    {
        int above = board[0][col] + board[1][col];
        switch(above)
        {
            case 8: values[2][col] = 100;
            break;
            case 2: values[2][col] = 50;
            break;
            case 5: values[2][col] -=3;
            break;
            case 1: values[2][col] -=3;
            break;
            case 4: values[2][col] +=3;
            break;
        }
    }
    //checks middle squares for updown
    private void updown(int col)
    {
        int abovebelow = board[1][col] + board[1][col];
        switch (abovebelow)
        {
            case 8:
                values[1][col] = 100;
                break;
            case 2:
                values[1][col] = 50;
                break;
            case 5:
                values[1][col] -= 3;
                break;
            case 1:
                values[1][col] -= 3;
                break;
            case 4:
                values[1][col] += 3;
                break;
        }

    }
    //checks left
    private void left(int row)
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
                values[row][2] -= 3;
                break;
            case 1:
                values[row][2] -= 3;
                break;
            case 4:
                values[row][2] += 3;
                break;


        }
    }
    //checks right
    private void right(int row)
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
                values[row][0] -= 3;
                break;
            case 1:
                values[row][0] -= 3;
                break;
            case 4:
                values[row][0] += 3;
                break;
        }
    }
    //checks middle squares for left right
    private void leftright(int row)
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
                values[row][1] -= 3;
                break;
            case 1:
                values[row][1] -= 3;
                break;
            case 4:
                values[row][1] += 3;
                break;
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
        if (board[0][0] != -1)
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
                    values[0][0] -= 3;
                    break;
                case 1:
                    values[0][0] -= 3;
                    break;
                case 4:
                    values[0][0] += 3;
                    break;


            }
        }

        if (board[0][2] != -1)
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
                    values[0][2] -= 3;
                    break;
                case 1:
                    values[0][2] -= 3;
                    break;
                case 4:
                    values[0][0] += 3;
                    break;

            }
        }
        if (board[2][0] != -1)
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
                    values[2][0] -= 3;
                    break;
                case 1:
                    values[2][0] -= 3;
                    break;
                case 4:
                    values[2][0] += 3;
                    break;

            }
        }
        if (board[2][2] != -1)
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
                    values[2][2] -= 3;
                    break;
                case 1:
                    values[2][2] -= 3;
                    break;
                case 4:
                    values[2][2] += 3;
                    break;

            }
        }
    }
}
