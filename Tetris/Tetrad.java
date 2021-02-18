import java.awt.Color;
import java.util.concurrent.Semaphore;

/**
 * This class contains all of the blocks for Tetris.
 * @author max lee
 * @version 3/14/19
 *
 */
public class Tetrad
{
    private Block[] blocks;
    private MyBoundedGrid<Block> grid;
    private Semaphore lock;
    private boolean lost;
    
    /**
     * Creates a Tetrad object.
     * @param grid1 the grid being used by tetris.
     */
    public Tetrad(MyBoundedGrid<Block> grid1)
    {
        lost = false;
        lock = new Semaphore(1, true);
        blocks = new Block[4];
        for (int i = 0; i < blocks.length; i++)
        {
            blocks[i] = new Block();
        }
        grid = grid1;
        int rand = (int) (Math.random()*7);
        int mid = 4;
        Location[]locs = new Location [4];
        if (rand == 0)
        {
            for (int i = 0; i < blocks.length; i++)
            {
                blocks[i].setColor(Color.RED);
            }
            locs[0] = new Location(1, mid);
            locs[1] = new Location(0, mid);
            locs[2] = new Location(2, mid);
            locs[3] = new Location(3, mid);
        }
        else if (rand == 1)
        {
            for (int i = 0; i < blocks.length; i++)
            {
                blocks[i].setColor(Color.GRAY);
            }
            locs[0] = new Location(0, mid);
            locs[1] = new Location(0, mid-1);
            locs[2] = new Location(0, mid+1);
            locs[3] = new Location(1, mid);
        }
        else if (rand == 2)
        {
            for (int i = 0; i < blocks.length; i++)
            {
                blocks[i].setColor(Color.CYAN);
            }
            locs[0] = new Location(0, mid);
            locs[1] = new Location(0, mid+1);
            locs[2] = new Location(1, mid);
            locs[3] = new Location(1, mid+1);
        }
        else if (rand == 3)
        {
            for (int i = 0; i < blocks.length; i++)
            {
                blocks[i].setColor(Color.YELLOW);
            }
            locs[0] = new Location(1, mid);
            locs[1] = new Location(0, mid);
            locs[2] = new Location(2, mid);
            locs[3] = new Location(2, mid+1);
        }
        else if (rand == 4)
        {
            for (int i = 0; i < blocks.length; i++)
            {
                blocks[i].setColor(Color.MAGENTA);
            }
            locs[0] = new Location(1, mid);
            locs[1] = new Location(0, mid);
            locs[2] = new Location(2, mid);
            locs[3] = new Location(2, mid-1);
        }
        else if (rand == 5)
        {
            for (int i = 0; i < blocks.length; i++)
            {
                blocks[i].setColor(Color.BLUE);
            }
            locs[0] = new Location(0, mid);
            locs[1] = new Location(1, mid);
            locs[2] = new Location(0, mid+1);
            locs[3] = new Location(1, mid-1);
        }
        else
        {
            for (int i = 0; i < blocks.length; i++)
            {
                blocks[i].setColor(Color.GREEN);
            }
            locs[0] = new Location(0, mid);
            locs[1] = new Location(1, mid);
            locs[2] = new Location(0, mid-1);
            locs[3] = new Location(1, mid+1);
        }
        addToLocations(grid1, locs);
    }
    
    /**
     * Adds the blocks into the given locations in the grid.
     * @param gr the grid being used
     * @param locs the locations to add to
     * @precondition blocks are not in any grid; locs.length = 4
     * @postcondition the locations of the blocks match locs and
     *                blocks have been put in the grid
     */
    private void addToLocations(MyBoundedGrid<Block> gr, Location[]locs)
    {
        for (int i = 0; i < 4; i++)
        {
            if (gr.get(locs[i]) != null)
            {
                lost = true;
            }
            blocks[i].putSelfInGrid(gr, locs[i]);
        }
    }
    
    /**
     * Returns if the game is over or not.
     * @return true if the game is over, false
     * otherwise.
     */
    public boolean youLost()
    {
        return lost;
    }
    
    /**
     * Removes the blocks from the grid, old locations returned.
     * @precondition blocks are in the grid
     * @postcondition returns old locations of blocks; blocks have
     *                been removed from grid
     * @return the array of the old locations of the blocks
     */
    private Location[] removeBlocks()
    {
        Location[] locs = new Location[4];
        for (int i = 0; i < 4; i++)
        {
            locs[i] = blocks[i].getLocation();
            blocks[i].removeSelfFromGrid();
        }
        return locs;
    }
    
    /**
     * Checks if the locations of the blocks are valid and empty.
     * @param gr the grid being used
     * @param locs the array of locations to test 
     * @return returns true if each of the locations in locs is 
     *         valid and empty; otherwise false
     */
    private boolean areEmpty(MyBoundedGrid<Block> gr, Location[] locs)
    {
        for (int i = 0; i < locs.length; i++)
        {
            if (!gr.isValid(locs[i]) || gr.get(locs[i]) != null)
            {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Translates the tetrad down deltaRow rows and right
     * deltaCol columns.
     * @param deltaRow amount of rows translated
     * @param deltaCol amount of columns translated
     * @postcondition tetrad is translated,
     * if possible. Otherwise it stays the same.
     * @return true if tetrad translates successfully; otherwise,
     *         false
     */
    public boolean translate(int deltaRow, int deltaCol)
    {
        try
        {
            lock.acquire();
            Location[] location = removeBlocks();
            Location[] newLocs = new Location[4];
            for (int i = 0; i < 4; i++)
            {
                newLocs[i] = new Location(location[i].getRow()+deltaRow, 
                        location[i].getCol()+deltaCol);
            }
            if (areEmpty(grid, newLocs))
            {
                addToLocations(grid, newLocs);
                return true;
            }
            addToLocations(grid, location);
            return false;
        }
        catch (InterruptedException e)
        {
            System.out.println("Did not modify tetrad");
            return false;
        }
        finally
        {
            lock.release();
        }     
    }
    
    /**
     * Rotates the tetrad 90 degrees clockwise.
     * @postcondition tetrad is rotated,
     * if possible. Otherwise it stays the same.
     * @return true if tetrad roates successfully; otherwise,
     *         false
     */
    public boolean rotate()
    {
        try
        {
            lock.acquire();
            Location[] location = removeBlocks();
            Location[] newLocs = new Location[4];
            for (int i = 0; i < 4; i++)
            {
                newLocs[i] = new Location(location[0].getRow()-location[0].getCol()
                        +location[i].getCol(), location[0].getRow()+location[0].getCol()
                        -location[i].getRow());
            }
            if (areEmpty(grid, newLocs))
            {
                addToLocations(grid, newLocs);
                return true;
            }
            addToLocations(grid, location);
            return false;
        }
        catch (InterruptedException e)
        {
            System.out.println("Did not modify tetrad");
            return false;
        }
        finally
        {
            lock.release();
        } 
    }
}
