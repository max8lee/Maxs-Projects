import java.util.*;
import java.awt.*;
/**
 * Abstract class player. Superclass for different types of players.
 * 
 * @author Max Lee
 * @version 05-05-19
 */
public abstract class Player
{
    private Board board;
    private String name;
    private Color color;
    /**
     * Constructor for objects of class Player
     * @param board1 the board the player is playing on
     * @param name1 the name of the player
     * @param color1 the color the player is playing
     */
    public Player(Board board1, String name1, Color color1)
    {
        board = board1;
        name = name1;
        color = color1;
    }

    /**
     * Accessor method for the board.
     * @return the board the player is playing on
     */
    public Board getBoard()
    {
        return board;
    }
    
    /**
     * Accessor method for the name.
     * @return the name of the player
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Accessor method for the color.
     * @return the color of the player
     */
    public Color getColor()
    {
        return color;
    }
    
    /**
     * Abstract method to find the player's next move.
     * @return the next move
     */
    public abstract Move nextMove();
}
