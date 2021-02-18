/**
 * A class that defines a single playing card.
 * 
 * @author Max Lee 
 * @version 11-5-18
 */
public class Card
{
    private int rank;
    private String suit; 
    private boolean isFaceUp;
    
    /**
     * Creates an instance of the card class.
     * @param myRank number of the card (ace through king)
     * @param mySuit suit of the card (clubs through spades)
     */
    public Card(int myRank, String mySuit)
    {
        rank = myRank;
        suit = mySuit;
        isFaceUp = false;
    }

    /**
     * Gets the rank of a card.
     * 
     * @return the rank of the card 
     */
    public int getRank()
    {
        return rank;
    }
    
    /**
     * Gets the suit of a card.
     * @return the suit of the card
     */
    public String getSuit()
    {
        return suit;
    }
    
    /**
     * Checks if card is red.
     * @return true if red, false otherwise
     */
    public boolean isRed()
    {
        return suit.equals("d") || suit.equals("h");
    }
    
    /**
     * Checks if card is facing up.
     * @return true if upright, false otherwise
     */
    public boolean isFaceUp()
    {
        return isFaceUp;
    }
    
    /**
     * Turns a card up if it is not already up.
     * @postcondition   card is face up
     */
    public void turnUp()
    {
        isFaceUp = true;
    }
    
    /**
     * Turns a card down if it is not already down.
     * @postcondition   card is face down
     */
    public void turnDown()
    {
        isFaceUp = false;
    }
    
    /**
     * Gets the file name for a card.
     * @return file name of card
     */
    public String getFileName()
    {
        String basepath = "/Users/maxlee/eclipse-workspace/Solitaire/src/cards";
        if (!isFaceUp())
        {
            return basepath + "/edgyback.png";
        }
        else
        {
            if (rank == 1)
            {
                return basepath + "//" + "a" + suit + ".gif";
            }
            else if (rank == 10)
            {
                return basepath + "//" + "t" + suit + ".gif";
            }
            else if (rank == 11)
            {
                return basepath + "//" + "j" + suit + ".gif";
            }
            else if (rank == 12)
            {
                return basepath + "//" + "q" + suit + ".gif";
            }
            else if (rank == 13)
            {
                return basepath + "//" + "k" + suit + ".gif";
            }
            else
            {
                return basepath + "//" + rank + suit + ".gif";
            }
        }
    }
}