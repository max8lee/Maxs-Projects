import java.util.*;
import java.awt.*;
/**
 * A player that chooses its next move at random.
 * 
 * @author Max Lee
 * @version 05-05-19
 */
public class RandomPlayer extends Player
{
    /**
     * Constructor for objects of class RandomPlayer
     * @param board the board the random player is playing on
     * @param name the name of the random player
     * @param color the color of the random player
     */
    public RandomPlayer(Board board, String name, Color color)
    {
        super(board, name, color);
    }

    /**
     * Chooses a random move from all the moves possible.
     * @return move the next move the random player will execute
     */
    public Move nextMove()
    {
        ArrayList<Move> moves = getBoard().allMoves(getColor());
        return moves.get((int)(Math.random()*moves.size()));
    }
}
