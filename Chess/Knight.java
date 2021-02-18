import java.awt.*;
import java.util.*;
/**
 * Class for knight pieces.
 * 
 * @author Max Lee
 * @version 05-05-19
 */
public class Knight extends Piece
{
    /**
     * Constructor for objects of class Knight
     * @param col the color of the knight
     * @param fileName to display the knight object
     */
    public Knight(Color col, String fileName)
    {
        super(col, fileName, 3);
    }

    /**
     * Finds all possible valid places the knight can move to. The
     * knight can move to any location 2 spaces vertically away and 1
     * space horizontally or 1 space vertically away and 2 spaces
     * horizontally. 
     * @return the list of all possible locations for the given knight
     */
    public ArrayList<Location> destinations()
    {
        ArrayList<Location> dests = new ArrayList<Location>();        
        for (int i = getLocation().getRow()-1; i <= getLocation().getRow()+1; i+=2)
        {            
            for (int j = getLocation().getCol()-2; j <= getLocation().getCol()+2; j+=4)
            {
                if (isValidDestination(new Location(i, j))
                    || (getBoard().isValid(new Location(i, j))
                    && getBoard().get(new Location(i, j)) instanceof Piece
                    && getBoard().get(new Location(i,j)).getColor() != this.getColor()))
                {
                    dests.add(new Location(i, j));
                }
            }
        }
        for (int i = getLocation().getRow()-2; i <= getLocation().getRow()+2; i+=4)
        {
            for (int j = getLocation().getCol()-1; j <= getLocation().getCol()+1; j+=2)
            {
                if (isValidDestination(new Location(i, j))
                    || (getBoard().isValid(new Location(i, j))
                    && getBoard().get(new Location(i, j)) instanceof Piece
                    && getBoard().get(new Location(i,j)).getColor() != this.getColor()))
                {
                    dests.add(new Location(i, j));
                }
            }
        }
        return dests;
    }
}
