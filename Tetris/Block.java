import java.awt.Color;
/**
* class BLock encapsulates a Block abstraction which can be placed into a Gridworld style grid
* You are expected to comment this class according to the style guide.
* @author Max Lee
*/
public class Block
{
    private MyBoundedGrid<Block> grid;
    private Location location;
    private Color color;
	/**
    * constructs a blue block, because blue is the greatest color ever!
    */
    public Block()
    {
        color = Color.BLUE;
        grid = null;
        location = null;
    }
	/**
	* returns the color of the block
	* @return block's color
    */
    public Color getColor()
    {
        return color;
    }
	/**
	* sets block's color to newColor
	* @param newColor new color block is being set to
	*/
    public void setColor(Color newColor)
    {
        color = newColor;
    }
    
	/**
    * Returns the grid
    * @return the grid
    */
    public MyBoundedGrid<Block> getGrid()
    {
        return grid;
    }
    
	/**
	* Returns the location
	* @return the location
	*/
    public Location getLocation()
    {
        if (grid == null)
        {
            return null;
        }
        return location;
    }
    
	/**
	* Removes this block from the grid.
	* @precondition the block is in a grid
	*/
    public void removeSelfFromGrid()
    {
        grid.remove(location);
        grid = null;
        location = null;

    }
    
	/**
	* Puts the block in gr, at loc. If there is already a block there, that block
	* gets removed.
	* @param gr the grid
	* @param loc the location
	*/
    public void putSelfInGrid(MyBoundedGrid<Block> gr, Location loc)
    {
        grid = gr;
        location = loc;
        if (grid.get(location) !=null)
            grid.get(location).removeSelfFromGrid();
        grid.put(location, this);
    }

    /**
	* Moves the block to a new location. If there is already a block at the
	* new location, it gets removed.
	* @param newLocation location the block is being moved to
	*/
    public void moveTo(Location newLocation)
    {
        grid.remove(location);
        location = newLocation;
        putSelfInGrid(grid, newLocation);
    }

    /**
	* returns a string with the location and color of this block
	*/
    public String toString()
    {
        return "Block[location=" + location + ",color=" + color + "]";
    }
}