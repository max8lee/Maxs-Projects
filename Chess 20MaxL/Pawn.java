import java.awt.*;
import java.util.*;
/**
 * Class for pawn pieces.
 * 
 * @author Max Lee
 * @version 05-05-19
 */
public class Pawn extends Piece
{
    /**
     * Constructor for objects of class Pawn
     * @param col the color of the pawn
     * @param fileName the fileName of the pawn to display the object
     */
    public Pawn(Color col, String fileName)
    {
        super(col, fileName, 1);
    }

    /**
     * Returns a list of all possible destinations for the pawn. A pawn
     * can move two spaces in front for its first move, but otherwise it
     * can only move one space forward. However, to capture another
     * piece it can move one space diagonally.
     * @return the list of all possible destinations the pawn can move to
     */
    public ArrayList<Location> destinations()
    {
        ArrayList<Location> dests = new ArrayList<Location>();
        if (this.getColor() == Color.BLACK)
        {
            if (isValidDestination(getLocation().getAdjacentLocation(180)) && 
                    getBoard().get(getLocation().getAdjacentLocation(180)) == null)
            {
                dests.add(getLocation().getAdjacentLocation(180));
                if (getLocation().getRow() == 1
                        && isValidDestination(getLocation().getAdjacentLocation(0).getAdjacentLocation(0)))
                {
                    dests.add(getLocation().getAdjacentLocation(0).getAdjacentLocation(0));
                }
            }
            if (getBoard().isValid(new Location(getLocation().getRow()+1, getLocation().getCol()-1)))
            {
                if (getBoard().get(new Location(getLocation().getRow()+1, getLocation().getCol()-1))
                    instanceof Piece)
                {
                    if (getBoard().get(new Location(getLocation().getRow()+1, 
                        getLocation().getCol()-1)).getColor()
                        != this.getColor())
                    {
                        dests.add(new Location(getLocation().getRow()+1, getLocation().getCol()-1));
                    }
                }
            }
            if (getBoard().isValid(new Location(getLocation().getRow()+1, getLocation().getCol()+1)))
            {
                if (getBoard().get(new Location(getLocation().getRow()+1,
                    getLocation().getCol()+1)) instanceof Piece)
                {
                    if (getBoard().get(new Location(getLocation().getRow()+1,
                        getLocation().getCol()+1)).getColor() != this.getColor())
                    {
                        dests.add(new Location(getLocation().getRow()+1, getLocation().getCol()+1));
                    }
                }
            }
        }
        else
        {
            if (isValidDestination(getLocation().getAdjacentLocation(0)) && 
                    getBoard().get(getLocation().getAdjacentLocation(0)) == null)
            {
                dests.add(getLocation().getAdjacentLocation(0));
                if (getLocation().getRow() == 6
                        && isValidDestination(getLocation().getAdjacentLocation(0).getAdjacentLocation(0)))
                {
                    dests.add(getLocation().getAdjacentLocation(0).getAdjacentLocation(0));
                }
            }
            if (getBoard().isValid(new Location(getLocation().getRow()-1, getLocation().getCol()-1)))
            {
                if (getBoard().get(new Location(getLocation().getRow()-1, getLocation().getCol()-1))
                    instanceof Piece)
                {
                    if (getBoard().get(new Location(getLocation().getRow()-1, 
                        getLocation().getCol()-1)).getColor()
                        != this.getColor())
                    {
                        dests.add(new Location(getLocation().getRow()-1, getLocation().getCol()-1));
                    }
                }
            }
            if (getBoard().isValid(new Location(getLocation().getRow()-1, getLocation().getCol()+1)))
            {
                if (getBoard().get(new Location(getLocation().getRow()-1,
                    getLocation().getCol()+1)) instanceof Piece)
                {
                    if (getBoard().get(new Location(getLocation().getRow()-1,
                        getLocation().getCol()+1)).getColor() != this.getColor())
                    {
                        dests.add(new Location(getLocation().getRow()-1, getLocation().getCol()+1));
                    }
                }
            }
        }
        
       
        
        return dests;
    }
}
