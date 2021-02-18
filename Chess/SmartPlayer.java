import java.awt.*;
import java.util.*;
/**
 * Class for smart players. A SmartPlayer chooses the move which will
 * give itself the highest score. (score is calculated by summing
 * the point values of the player's pieces and subtracting point
 * values of the opponent's pieces)
 * 
 * @author Max Lee
 * @version 05-05-19
 */
public class SmartPlayer extends Player
{
    /**
     * Constructor for objects of class SmartPlayer
     * @param board the board the smart player is playing on
     * @param name the name of the smart player
     * @param color the color the smart player is playing
     */
    public SmartPlayer(Board board, String name, Color color)
    {
        super (board, name, color);
    }

    /**
     * Method to calculate the score in a configuration of the
     * board. The score is the sum of all the point values of the
     * smart player's pieces with the sum of all point values of the
     * opponent's pieces subtracted.
     * @return an integer representing the score
     */
    public int score()
    {
        ArrayList<Location> occupied = getBoard().getOccupiedLocations();
        ArrayList<Location> pieces = new ArrayList<Location>();
        ArrayList<Location> opponent = new ArrayList<Location>();
        for (int i = 0; i < occupied.size(); i++)
        {
            if (getBoard().get(occupied.get(i)).getColor().equals(getColor()))
            {
                pieces.add(occupied.get(i));
            }
            else
            {
                opponent.add(occupied.get(i));
            }
        }
        int sum = 0;
        for (int i = 0; i < pieces.size(); i++)
        {
            sum += getBoard().get(pieces.get(i)).getValue();
        }
        for (int i = 0; i < opponent.size(); i++)
        {
            sum -= getBoard().get(opponent.get(i)).getValue();
        }
        return sum;
    }
    
    /**
     * Executes all the possible valid moves and calculates the score
     * after each is executed. Finds the move which gives the highest
     * score and executes that move.
     * @return the move with the highest score
     */
    public Move nextMove()
    {
        ArrayList<Move> moves = getBoard().allMoves(getColor());
        int maxScore = Integer.MIN_VALUE;
        int maxIndex = 0;
        for (int i = 0; i < moves.size(); i++)
        {
            getBoard().executeMove(moves.get(i));
            if (score() > maxScore)
            {
                maxScore = score();
                maxIndex = i;
            }
            getBoard().undoMove(moves.get(i));
        }
        return moves.get(maxIndex);
    }
}
