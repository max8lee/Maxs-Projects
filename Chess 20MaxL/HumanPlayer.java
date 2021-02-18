import java.awt.*;
import java.util.*;
/**
 * The human player class. The human player chooses its own move and
 * it is executed in the game.
 * 
 * @author Max Lee
 * @version 05-05-19
 */
public class HumanPlayer extends Player
{
    private BoardDisplay display;

    /**
     * Constructor for objects of class HumanPlayer
     * @param board the board the human player is playing on
     * @param name the name of the human player
     * @param color the color of the human player
     * @param display1 the board display for the human player
     */
    public HumanPlayer(Board board, String name, Color color, BoardDisplay display1)
    {
        super (board, name, color);
        display = display1;
    }

    /**
     * Human player chooses a move. If it is valid it is returned.
     * Otherwise the player continues to choose moves until one of 
     * them is valid.
     * @return move the next move the human player will execute
     */
    public Move nextMove()
    {
        Move move = display.selectMove();
        boolean valid = false;
        ArrayList<Move> moves = getBoard().allMoves(getColor());
        for (int i = 0; i < moves.size(); i++)
        {
            if (moves.get(i).equals(move))
            {
                valid = true;
            }
        }
        if (valid)
        {
            return move;
        }
        else
        {
            while (!valid)
            {
                move = display.selectMove();
                for (int i = 0; i < moves.size(); i++)
                {
                    if (moves.get(i).equals(move))
                    {
                        valid = true;
                    }
                }
            }
            return move;
        }
    }
}
