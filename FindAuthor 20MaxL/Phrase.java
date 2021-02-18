import java.util.*;
/**
 * The class defining a phrase.
 * 
 * @author Max Lee
 * @version 5-28-19
 */
public class Phrase
{
    private ArrayList<Token> tokens;

    /**
     * Constructor for Phrases. An ArrayList is used for O(1) 
     * add time and O(n) retrieval time.
     */
    public Phrase()
    {
        tokens = new ArrayList<Token>();
    }

    /**
     * Adds a token to the end of tokens in O(1) time.
     * @param	t1 the token to be added
     */
    public void addToken(Token t1)
    {
        tokens.add(t1);
    }
    
    /**
     * Makes a copy of tokens in O(n) time.
     * @return	the copy of the tokens ArrayList
     */
    public ArrayList<Token> copy()
    {
        ArrayList<Token> copy = new ArrayList<Token>();
        for (int i = 0; i < tokens.size(); i++)
        {
            copy.add(i, tokens.get(i));
        }
        return copy;
    }
    
    /**
     * ToString method for objects of the phrase class.
     * @return	the string representation of a phrase
     */
    public String toString()
    {
        String s = "";
        for (int i = 0; i < tokens.size(); i++)
        {
            s+=tokens.get(i);
        }
        return s;
    }
    
    /**
     * Returns tokens.
     * @return	the ArrayList of tokens
     */
    public ArrayList<Token> getTokens()
    {
        return tokens;
    }
}
