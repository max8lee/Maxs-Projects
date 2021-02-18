/**
 * Runs the game tetris.
 * @author max lee
 * @version 3/14/19
 */
public class Tetris implements ArrowListener
{
    private MyBoundedGrid<Block> grid;
    private BlockDisplay display;
    private Tetrad currentTetrad;
    private int numClear;
    private int score;
    private int speed;
    private int rowClear;
    private static boolean lost;
    private static boolean pause;
    
    /**
     * Creates an instance of the game.
     */
    public Tetris()
    {
        lost = false;
        pause = false;
        numClear = 0;
        score = 0;
        rowClear = 0;
        speed = 1000;
        grid = new MyBoundedGrid<Block>(20,10);
        display = new BlockDisplay(grid);
        display.setArrowListener(this);
        currentTetrad = new Tetrad(grid);
        display.setTitle("Tetris " + "Level: " + getLevel() + " Score: " + getScore());
        display.showBlocks();
    }
    
    /**
     * Rotates the current tetrad 90 degrees clockwise when up is pressed.
     */
    public void upPressed()
    {
        currentTetrad.rotate();
        display.showBlocks();
    }

    /**
     * Moves the current tetrad one row down when down is pressed.
     */
    public void downPressed()
    {
        currentTetrad.translate(1, 0);
        display.showBlocks();
    }

    /**
     * Moves the current tetrad one column left when left is pressed.
     */
    public void leftPressed()
    {
        currentTetrad.translate(0, -1);
        display.showBlocks();
    }

    /**
     * Moves the current tetrad one column right when right is pressed.
     */
    public void rightPressed()
    {
        currentTetrad.translate(0, 1);
        display.showBlocks();
    }

    /**
     * When spaced is pressed, the tetrad drops to the bottom of the grid.
     * (or as far down as it can go)
     */
    public void spacePressed()
    {
        while (currentTetrad.translate(1,0))
        {
            display.showBlocks();
        }
    }
    
    /**
     * When p is pressed, the game pauses and the title updates. When
     * it is pressed a second time, the game resumes.
     */
    public void pPressed()
    {
        if (!pause)
        {
            pause = true;
            display.setTitle("PAUSED");
        }
        else if (pause)
        {
            pause = false;
            setScore();
        }
    }
    
    /**
     * Repeatedly pauses for one second and then moves the 
     * tetrad down one row and changes the display if the game
     * is not paused or lost.
     */
    public void play()
    {
        try
        {
            Thread.sleep(speed);
            if (!pause)
            {
                if (!currentTetrad.translate(1, 0))
                {
                    clearCompletedRows();
                    setScore();
                    currentTetrad = new Tetrad(grid);
                    if (currentTetrad.youLost())
                    {
                        display.setTitle("GAME OVER");
                        lost = true;
                        throw new InterruptedException();
                    }
                }
                else
                {
                    currentTetrad.translate(1, 0);
                }
                display.showBlocks();
            }
        }
        catch (InterruptedException e)
        {
            System.out.println("Interrupted"); 
        }
    }
    
    /**
     * Main method to run the game.
     * @param args the arguments in the main method
     */
    public static void main(String [] args)
    {
        Tetris game = new Tetris();
        while (!lost)
        {
            game.play();
        }
    }
    
    /**
     * Determines if row is completely filled.
     * @param row the row being tested
     * @return true if row is filed, false otherwise.
     */
    private boolean isCompletedRow(int row)
    {
        for(int i =0; i <grid.getNumCols(); i++)
        {
            Location loc = new Location(row,i);
            if (grid.get(loc) == null)
            {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Clears a row of blocks and moves the row above down.
     * @param row the row being cleared.
     */
    private void clearRow(int row)
    {
        for(int i =0; i <grid.getNumCols(); i++)
        {
            Location loc = new Location(row,i);
            grid.get(loc).removeSelfFromGrid();
        }
        for(int i = row -1; i > 0; i--)
        {
            for(int j =0; j <grid.getNumCols(); j++)
            {
                if (grid.get(new Location(i,j)) != null)
                {
                    grid.get(new Location(i,j)).moveTo(new Location(i+1,j));
                }
            }
        }
    }
    /**
     * When the tetrad has fallen, this clears all of
     * the completed rows and updates the score
     * and level instance variables.
     */
    public void clearCompletedRows()
    {
        numClear = 0;
        for(int i = 0; i<grid.getNumRows(); i++)
        {
            if(isCompletedRow(i))
            {
                clearRow(i);
                numClear++;
                rowClear++;
                if (rowClear % 10 == 0)
                {
                    if (speed > 10)
                    {
                        speed -= 10;
                    }
                }
            }
        }
        if (numClear == 1)
        {
            score+=40;
        }
        else if (numClear == 2)
        {
            score+=100;
        }
        else if (numClear == 3)
        {
            score+=300;
        }
        else if (numClear == 4)
        {
            score+=1200;
        }
    }
    
    /**
     * Gets the current score (+40 for one row cleared, +100
     * for two rows cleared, +300 for three rows cleared, and 
     * +1200 for four rows cleared).
     * @return the current score
     */
    public int getScore()
    {
        return score;
    }
    
    /**
     * Returns the level. The level increases every time 10 rows are
     * cleared.
     * @return the current level
     */
    public int getLevel()
    {
        return rowClear/10 + 1;
    }
    
    /**
     * Updates the level and score and displays it in the title.
     */
    public void setScore()
    {
        display.setTitle("Tetris " + " Score: " + getScore() + " Level: " + getLevel());
    }
    
}
