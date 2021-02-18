import java.util.*;
/**
 * The class defining a sentence.
 * 
 * @author Max Lee
 * @version 5-28-19
 */
public class Sentence
{
    private ArrayList<Phrase> phrases;

    /**
     * Constructor for Sentences. An ArrayList is used
     * for O(1) retrieval and add times.
     */
    public Sentence()
    {
        phrases = new ArrayList<Phrase>();
    }

    /**
     * Adds a phrase to the end of phrases in O(1) time.
     * @param   phrase1 the phrase to be added
     */
    public void addPhrase(Phrase phrase1)
    {
        phrases.add(phrase1);
    }
    
    /**
     * Makes a copy of phrases in O(n) time.
     * @return  the copy of the phrases ArrayList
     */
    public ArrayList<Phrase> copy()
    {
        ArrayList<Phrase> copy = new ArrayList<Phrase>();
        for (int i = 0; i < phrases.size(); i++)
        {
            copy.add(i, phrases.get(i));
        }
        return copy;
    }
    
    /**
     * ToString method for objects of the sentence class.
     * @return	the string representation of the sentence
     */
    public String toString()
    {
        String s = "";
        for (int i = 0; i < phrases.size(); i++)
        {
            s+=phrases.get(i).toString();
        }
        return s;
    }
    
    /**
     * Returns tokens.
     * @return  the ArrayList of tokens
     */
    public ArrayList<Phrase> getPhrases()
    {
        return phrases;
    }
}
