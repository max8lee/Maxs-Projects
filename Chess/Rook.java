import java.awt.*;
import java.util.*;
/**
 * Class for rook pieces.
 * 
 * @author Max Lee
 * @version 05-05-19
 */
public class Rook extends Piece
{
    /**
     * Constructor for objects of class Rook
     * @param col the color of the rook
     * @param fileName the fileName to display the rook
     */
    public Rook(Color col, String fileName)
    {
        super(col, fileName, 5);
    }

    /**
     * Finds all possible destinations for the rook. A rook can move
     * anywhere as long as the location is directly horizontal or 
     * vertical to its current location.
     * @return the list of locations the rook can move to
     */
    public ArrayList<Location> destinations()
    {
        ArrayList<Location> dests = new ArrayList<Location>();
        
        sweep (dests, 0);
        sweep (dests, 270);
        sweep (dests, 90);
        sweep (dests, 180);
        return dests;
    }
}
