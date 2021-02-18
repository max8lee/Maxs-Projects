import java.awt.*;
import java.util.*;
/**
 * Class for King pieces.
 * 
 * @author Max Lee
 * @version 05-05-19
 */
public class King extends Piece
{
    /**
     * Constructor for objects of class King
     * @param col the color of the king
     * @param fileName the fileName for the piece
     */
    public King(Color col, String fileName)
    {
        super(col, fileName, 1000);
    }

    /**
     * Returns a list of the possible valid destinations for the
     * particular king piece. The king can move one square in all 8
     * directions around it, if the locations are valid.
     * @return an arraylist of the possible destinations
     */
    public ArrayList<Location> destinations()
    {
        ArrayList<Location> dests = new ArrayList<Location>();
        int direction = 0;
        for (int i = 0; i < 8; i++)
        {
            if (isValidDestination(getLocation().getAdjacentLocation(direction))
                || (getBoard().isValid(getLocation().getAdjacentLocation(direction))
                && getBoard().get(getLocation().getAdjacentLocation(direction)) instanceof Piece
                && getBoard().get(getLocation().getAdjacentLocation(direction)).getColor()
                != this.getColor()))
            {
                dests.add(getLocation().getAdjacentLocation(direction));
            }
            direction+=45;
        }
        return dests;
    }
}
