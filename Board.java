import javafx.geometry.Pos;

public class Board
{
    //The computer uses 4s the player uses 1s
    private int[] position = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    private String[] Show = {"-","-","-","-","-","-","-","-","-"};
    private boolean first;
    private boolean win = false;
    private int turncount =0;

    public boolean isWin()
    {
        return win;
    }

    public void setWin(boolean win)
    {
        this.win = win;
    }

    public boolean isFirst()
    {
        return first;
    }

    public void setFirst(boolean first)
    {
        this.first = first;
    }

    private int[] Generate_values()
    {
        int[] values = {0, 0, 0, 0, 0, 0, 0, 0, 0};
        if(turncount == 4)
        {
            if (position[0] + position[8] == 2 || position[2] + position[6] == 2)
            {
               values[1] =50;
               values[3] =50;
               values[5] =50;
               values[7] =50;

            }
        }
        //This checks and blocks a wierd win condition where the player choose two opposite corners
        for (int x = 0; x < 9; x++)
        {

            if (position[x] == 1 || position[x] == 4)
            {
                values[x] = -1;
            }
            // checks if the space is filled
            else
            {
                if (x == 0 || x == 2 || x == 6 || x == 8)
                {
                    values[x] += 3;
                }
                if (x == 4)
                {
                    values[x] += 2;
                    if (!first)
                    {
                        values[x] = 99;
                        return values;
                    }
                    // checks for if you went second and middle is taken
                }
                //checks for corners and adds 2 to the score
                if (x == 0)
                {
                    if (position[x + 1] + position[x + 2] == 8 || position[x + 3] + position[x + 6] == 8 || position[x + 4] + position[x + 8] == 8)
                    {
                        values[x] = 100;
                        return values;
                    }
                    //checks for win
                    if (position[x + 1] + position[x + 2] == 2 || position[x + 3] + position[x + 6] == 2 || position[x + 4] + position[x + 8] == 2)
                    {
                        values[x] += 50;
                    }
                    //checks for block
                    if (position[x + 1] + position[x + 2] == 4)
                    {
                        values[x] += 3;
                    }
                    if (position[x + 3] + position[x + 6] == 4)
                    {
                        values[x] += 3;
                    }

                    if (position[x + 4] + position[x + 8] == 4)
                    {
                        values[x] += 3;
                    }
                    //checks for win setup
                    if (position[x + 1] + position[x + 2] != 4 || position[x + 3] + position[x + 6] != 4 || position[x + 4] + position[x + 8] != 4)
                    {
                        values[x] += -3;
                    }
                    //check for win possible

                } else if (x == 1)
                {
                    if (position[x - 1] + position[x + 1] == 8 || position[x + 3] + position[x + 6] == 8)
                    {
                        values[x] = 100;
                        return values;
                    }
                    //checks for win
                    if (position[x + 1] + position[x - 1] == 2 || position[x + 3] + position[x + 6] == 2)
                    {
                        values[x] += 50;
                    }
                    //checks for block
                    if (position[x + 1] + position[x - 1] == 4)
                    {
                        values[x] += 3;
                    }
                    if (position[x + 3] + position[x + 6] == 4)
                    {
                        values[x] += 3;
                    }

                    //checks for win setup
                    if (position[x + 1] + position[x - 1] != 4 || position[x + 3] + position[x + 6] != 4)
                    {
                        values[x] += -3;
                    }
                    //checks for win possible

                } else if (x == 2)
                {
                    if (position[x - 1] + position[x - 2] == 8 || position[x + 3] + position[x + 6] == 8 || position[x + 2] + position[x + 4] == 8)
                    {
                        values[x] = 100;
                        return values;
                    }
                    //checks for win
                    if (position[x - 1] + position[x - 2] == 2 || position[x + 3] + position[x + 6] == 2 || position[x + 2] + position[x + 4] == 2)
                    {
                        values[x] += 50;
                    }
                    //checks for block
                    if (position[x - 1] + position[x - 2] == 4)
                    {
                        values[x] += 3;
                    }
                    if (position[x + 3] + position[x + 6] == 4)
                    {
                        values[x] += 3;
                    }

                    if (position[x + 2] + position[x + 4] == 4)
                    {
                        values[x] += 3;
                    }
                    //checks for win setup
                    if (position[x - 1] + position[x - 2] != 4 || position[x + 3] + position[x + 6] != 4 || position[x + 2] + position[x + 4] != 4)
                    {
                        values[x] += -3;
                    }
                    //check for win possible

                } else if (x == 3)
                {
                    if (position[x + 1] + position[x + 2] == 8 || position[x + 3] + position[x - 3] == 8)
                    {
                        values[x] = 100;
                        return values;
                    }
                    //checks for win
                    if (position[x + 1] + position[x + 2] == 2 || position[x + 3] + position[x - 3] == 2)
                    {
                        values[x] += 50;
                    }
                    //checks for block
                    if (position[x + 1] + position[x + 2] == 4)
                    {
                        values[x] += 3;
                    }
                    if (position[x + 3] + position[x - 3] == 4)
                    {
                        values[x] += 3;
                    }
                    //checks for win setup
                    if (position[x + 1] + position[x + 2] != 4 || position[x + 3] + position[x - 3] != 4)
                    {
                        values[x] += -3;
                    }
                    //check for win possible


                } else if (x == 5)
                {
                    if (position[x - 1] + position[x - 2] == 8 || position[x + 3] + position[x - 3] == 8)
                    {
                        values[x] = 100;
                        return values;
                    }
                    //checks for win
                    if (position[x - 1] + position[x - 2] == 2 || position[x + 3] + position[x - 3] == 2)
                    {
                        values[x] += 50;
                    }
                    //checks for block
                    if (position[x - 1] + position[x - 2] == 4)
                    {
                        values[x] += 3;
                    }
                    if (position[x + 3] + position[x - 3] == 4)
                    {
                        values[x] += 3;
                    }
                    //checks for win setup
                    if (position[x - 1] + position[x - 2] != 4 || position[x - 3] + position[x + 3] != 4)
                    {
                        values[x] += -3;
                    }
                    //check for win possible

                } else if (x == 6)
                {
                    if (position[x + 1] + position[x + 2] == 8 || position[x - 3] + position[x - 6] == 8 || position[x - 4] + position[x - 2] == 8)
                    {
                        values[x] = 100;
                        return values;
                    }
                    //checks for win
                    if (position[x + 1] + position[x + 2] == 2 || position[x - 3] + position[x - 6] == 2 || position[x - 4] + position[x - 2] == 2)
                    {
                        values[x] += 50;
                    }
                    //checks for block
                    if (position[x + 1] + position[x + 2] == 4)
                    {
                        values[x] += 3;
                    }
                    if (position[x - 3] + position[x - 6] == 4)
                    {
                        values[x] += 3;
                    }

                    if (position[x - 4] + position[x - 2] == 4)
                    {
                        values[x] += 3;
                    }
                    //checks for win setup
                    if (position[x + 1] + position[x + 2] != 4 || position[x - 3] + position[x - 6] != 4 || position[x - 4] + position[x - 2] != 4)
                    {
                        values[x] += -3;
                    }
                    //check for win possible

                } else if (x == 7)
                {
                    if (position[x + 1] + position[x - 1] == 8 || position[x - 3] + position[x - 6] == 8)
                    {
                        values[x] = 100;
                        return values;
                    }
                    //checks for win
                    if (position[x + 1] + position[x - 1] == 2 || position[x - 3] + position[x - 6] == 2)
                    {
                        values[x] += 50;
                    }
                    //checks for block
                    if (position[x + 1] + position[x - 1] == 4)
                    {
                        values[x] += 3;
                    }
                    if (position[x - 3] + position[x - 6] == 4)
                    {
                        values[x] += 3;
                    }
                    //checks for win setup
                    if (position[x + 1] + position[x - 1] != 4 || position[x -6] + position[x - 3] != 4)
                    {
                        values[x] += -3;
                    }
                    //check for win possible

                } else if (x == 8)
                {
                    if (position[x - 1] + position[x - 2] == 8 || position[x - 3] + position[x - 6] == 8 || position[x - 4] + position[x - 8] == 8)
                    {
                        values[x] = 100;
                        return values;
                    }
                    //checks for win
                    if (position[x - 1] + position[x - 2] == 2 || position[x - 3] + position[x - 6] == 2 || position[x - 4] + position[x - 8] == 2)
                    {
                        values[x] += 50;
                    }
                    //checks for block
                    if (position[x - 1] + position[x - 2] == 4)
                    {
                        values[x] += 3;
                    }
                    if (position[x - 3] + position[x - 6] == 4)
                    {
                        values[x] += 3;
                    }
                    if (position[x - 4] + position[x - 8] == 4)
                    {
                        values[x] += 3;
                    }
                    //checks for win setup
                    if (position[x - 1] + position[x - 2] != 4 || position[x - 3] + position[x - 6] != 4 || position[x - 4] + position[x - 8] != 4)
                    {
                        values[x] += -3;
                    }
                    //check for win possible

                }
            }

        }


        return values;
    }
    public void aiturn()
    {
        turncount+=1;
        if(turncount == 9)
        {
            System.out.println("You tied, Resetting board");
            reset();
            return;
        }
        else
        {
            int[] values = Generate_values();
            int temp = -1;
            int temp2 = -1;
            for (int x = 0; x < 9; x++)
            {
                if (values[x] > temp)
                {
                    temp = values[x];
                    if (temp == 100)
                    {
                        win = true;
                        System.out.println("You lost idiot.");
                    }
                    temp2 = x;
                }
            }
            position[temp2] = 4;
            Show[temp2] = "X";
        }
    }

    public String[] getShow()
    {
        return Show;
    }

    public void setShow(String[] show)
    {
        Show = show;
    }

    public void turn(int space)
    {




        if(0<space && space<10)
        {
            if (position[space - 1] == 0)
            {
                turncount += 1;
                if (turncount == 9)
                {
                    System.out.println("You tied, Resetting board.");
                    reset();
                    return;
                } else
                {
                    position[space - 1] = 1;
                    Show[space - 1] = "O";
                    aiturn();
                }

            } else
            {
                System.out.println("No cheating");
            }
        }
        else
        {
            System.out.println("Invalid Index. Please put in a number with inclusive end points 1-9");
        }
    }
    private void reset()
    {
        for (int x = 0; x < 9; x++)
        {
            position[x] = 0;
        }
        for (int x = 0; x < 9; x++)
        {
            Show[x] = "-";
        }
    }

}
