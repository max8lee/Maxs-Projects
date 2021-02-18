import java.util.*;
import java.awt.*;
/**
 * Class for queen pieces.
 * 
 * @author Max Lee
 * @version 05-05-19
 */
public class Queen extends Piece
{
    /**
     * Constructor for objects of class Queen
     * @param col the color of the queen
     * @param fileName the fileName to display the queen object
     */
    public Queen(Color col, String fileName)
    {
        super(col, fileName, 9);
    }

    /**
     * Finds all possible valid locations the queen can move to. A
     * queen can move to any space in all eight directions around it.
     * @return the list of all possible destinations for the queen
     */
    public ArrayList<Location> destinations()
    {
        ArrayList<Location> dests = new ArrayList<Location>();
        
        sweep (dests, 0);
        sweep (dests, 45);
        sweep (dests, 90);
        sweep (dests, 135);
        sweep (dests, 180);
        sweep (dests, 225);
        sweep (dests, 270);
        sweep (dests, 315);
        
        return dests;
    }
}
