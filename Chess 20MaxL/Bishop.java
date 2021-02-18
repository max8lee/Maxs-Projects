import java.awt.*;
import java.util.*;
/**
 * Class for bishop pieces.
 * 
 * @author Max Lee
 * @version 05-05-19
 */
public class Bishop extends Piece
{
    /**
     * Creates instances of Bishop objects
     * @param col the color of the bishop
     * @param fileName where the bishop is on my computer
     */
    public Bishop(Color col, String fileName)
    {
        super(col, fileName, 3);
    }

    /**
     * Finds all possible valid destinations the bishop can move to.
     * (A bishop can move to any space diagonally away from it)
     * @return the list of all locations the bishop can move to
     */
    public ArrayList<Location> destinations()
    {
        ArrayList<Location> dests = new ArrayList<Location>();
        sweep (dests, 45);
        sweep (dests, 135);
        sweep (dests, 225);
        sweep (dests, 315);
        
        return dests;
    }
}
