import java.awt.*;
import java.util.*;

/**
 * Represents a rectangular game board, containing Piece objects.
 * 
 * @author Eileen Li
 * @version 04/13/17
 */
public class Board extends BoundedGrid<Piece>
{
    /**
	 * Constructs a new Board with given dimensions.
	 */
	public Board()
	{
		super(8, 8);
	}

	/**
	 * @precondition move has already been made on the board
	 * @postcondition piece has moved back to its source, and any
	 *                captured piece is returned to its location
	 * @param move the move to be undone
	 */
	public void undoMove(Move move)
	{
		Piece piece = move.getPiece();
		Location source = move.getSource();
		Location dest = move.getDestination();
		Piece victim = move.getVictim();

		piece.moveTo(source);

		if (victim != null)
			victim.putSelfInGrid(piece.getBoard(), dest);
	}
	
	/**
	 * Finds all possible valid moves for pieces of a given color.
	 * @param color the color to find all the moves for
	 * @return an arraylist of all the moves possible for the given color
	 */
	public ArrayList<Move> allMoves(Color color)
	{
	    ArrayList<Move> moves = new ArrayList<Move>();
	    ArrayList<Location> occupied = getOccupiedLocations();
	    for (int i = 0; i < occupied.size(); i++)
	    {
	        if (get(occupied.get(i)) != null && get(occupied.get(i)).getColor().equals(color))
	        {
	            ArrayList<Location> dests = get(occupied.get(i)).destinations();
	            for (int j = 0; j < dests.size(); j++)
	            {
	                moves.add(new Move(get(occupied.get(i)), dests.get(j)));
	            }
	        }
	    }
	    return moves;
	}
	
	/**
	 * Moves the piece (that the move regards) to its new location.
	 * @param move the move to be executed
	 */
	public void executeMove(Move move)
	{
	    move.getPiece().moveTo(move.getDestination());
	}
}