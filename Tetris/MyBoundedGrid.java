import java.util.ArrayList;

/**
 * This class will allow us to place objects into a grid.
 * @author max lee
 * @version 3-15-19
 * @param E any element
 */
public class MyBoundedGrid<E>
{
    private int numRows;
    private int numCols;
    private E[][] objectsInGrid;
    
    /**
     * Creates a MyBoundedGrid object.
     * @param rows number or rows
     * @param cols number or columns
     */
    public MyBoundedGrid(int rows, int cols)
    {
        numRows = rows;
        numCols = cols;
        objectsInGrid = (E[][]) new Object[rows][cols];
    }
    
    /**
     * Returns the number or rows.
     * @return numRows
     */
    public int getNumRows()
    {
        return numRows;
    }
    
    /**
     * Returns the number or columns.
     * @return numCols
     */
    public int getNumCols()
    {
        return numCols;
    }
    
    /**
     * Returns whether or not loc is a valid location in the grid.
     * @param loc the location being checked
     * @return true if loc is valid, false otherwise
     */
    public boolean isValid(Location loc)
    {
        return loc.getRow() < getNumRows() && loc.getRow() >= 0 && 
                loc.getCol() < getNumCols() && loc.getCol() >= 0;
    }
    
    /**
     * Puts obj at loc.
     * @param obj object beign added
     * @param loc location being added to
     * @return obj
     */
    public E put(Location loc, E obj)
    {
        E temp = objectsInGrid[loc.getRow()][loc.getCol()];
        objectsInGrid[loc.getRow()][loc.getCol()] = obj;
        return temp;
    }
    
    /**
     * Removes obj at loc, returns it.
     * @param loc location
     * @return obj being stored at loc.
     */
    public E remove(Location loc)
    {
        E obj = objectsInGrid[loc.getRow()][loc.getCol()];
        objectsInGrid[loc.getRow()][loc.getCol()] = null;
        return obj;
    }
    
    /**
     * Gets the value being stored at loc and returns it.
     * @param loc location
     * @return obj at location, null if nothing there
     */
    public E get(Location loc)
    {
        return objectsInGrid[loc.getRow()][loc.getCol()];
    }
    
    /**
     * Finds all occupied locations.
     * @return ArrayList of all occupied locations.
     */
    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> occupied = new ArrayList<Location>();
        for (int i = 0; i < numRows; i++)
        {
            for (int j = 0; j < numCols; j++)
            {
                if (objectsInGrid[i][j] != null)
                {
                    occupied.add(new Location(i,j));
                }
            }
        }
        return occupied;
    }
}
