import java.util.*;
/**
 * This class calculates various linguistic features of a document.
 * 
 * @author Max Lee
 * @version 5-28-19
 */
public class DocumentStatistics
{
    private Document document;
    /**
     * Constructor for a DocumentStatistics object
     * @param	document1 the document to calculate the statistics of
     */
    public DocumentStatistics(Document document1)
    {
        document = document1;
        document.parseDocument();
    }

    /**
     * This method returns the average length of a word in the 
     * document in O(n) time.
     * 
     * @return	a double representing the average number of
     * 			characters per word
     */
    public double getAverageWordLength()
    {
        ArrayList<Sentence> tempSentence = document.getDocument();
        int total = 0;
        int size = 0;
        for (int i = 0; i < tempSentence.size(); i++)
        {
            ArrayList<Phrase> tempPhrase = tempSentence.get(i).getPhrases();
            for (int j = 0; j < tempPhrase.size(); j++)
            {
                ArrayList<Token> tempToken = tempPhrase.get(j).getTokens();
                for (int k = 0; k < tempToken.size(); k++)
                {
                    size++;
                    Token t = tempToken.get(k);
                    String str = t.getString();
                    total += str.length();
                }
            }
        }
        System.out.println((double)total/size);
        return 1.0*total/size;
    }

    /**
     * This method returns the ratio of unique words 
     * over total words in O(n) time.
     * @return	a double representing the number of unique words
     * 			divided by the number of total words
     */
    public double getTypeTokenRatio()
    {
        Set<String> unique = new HashSet<String>();
        ArrayList<Sentence> text = document.getDocument();
        int size = 0;
        for (int i = 0; i < text.size(); i++)
        {
            ArrayList<Phrase> phrases = text.get(i).getPhrases();
            for (int k = 0; k < phrases.size(); k++)
            {
                ArrayList<Token> words = phrases.get(k).getTokens();
                for (int j = 0; j < words.size(); j++)
                {
                    size++;
                    unique.add(words.get(j).getString());
                }
            }
        }
        System.out.println((double)unique.size()/size);
        return (double) unique.size()/size;
    }

    /**
     * This method returns the ratio of words that only occur 
     * once over the total words in O(n) time.
     * @return	a double representing the number of words appearing
     * 			once divided by the number of total words
     */
    public double getHapaxLegomenaRatio()
    {
        Map<String, Integer> map = new HashMap<String, Integer>();
        ArrayList<Sentence> text = document.getDocument();
        int size = 0;
        int count = 0;
        for (int i = 0; i < text.size(); i++)
        {
            ArrayList<Phrase>phrases = text.get(i).getPhrases();
            for (int k = 0; k < phrases.size(); k++)
            {
                ArrayList<Token>words = phrases.get(k).getTokens();
                for (int j = 0; j < words.size(); j++)
                {
                    size++;
                    if (!map.containsKey(words.get(j).getString()))
                    {
                        map.put(words.get(j).getString(), 1);
                    }
                    else
                    {
                        map.put(words.get(j).getString(), 2);
                    }
                }
            }
        }
        for (Integer int1: map.values())
        {
            if (int1 == 1)
            {
                count++;
            }
        }
        System.out.println((double)count/size);
        return (double) count/size;
    }

    /**
     * This method returns the average words per sentence in O(n) time. 
     * @return	a double representing the average number of words
     * 			in each sentence
     */
    public double getAverageWordsPerSentence()
    {
        ArrayList<Sentence> text = document.getDocument();
        int size = 0;
        for (int i = 0; i < text.size(); i++)
        {
            ArrayList<Phrase>phrases = text.get(i).getPhrases();
            for (int k = 0; k < phrases.size(); k++)
            {                
                ArrayList<Token>words = phrases.get(k).getTokens();
                size+= words.size();
            }
        }
        System.out.println((double)size/text.size());
        return (double) size /text.size();
    }

    /**
     * This method returns the average number of phrases 
     * per sentence in O(n) time.
     * @return	a double representing the average number of phrases
     * 			per sentence
     */
    public double getSentenceComplexity()
    {
        ArrayList<Sentence> text = document.getDocument();
        int size = 0;
        for (int i = 0; i < text.size(); i++)
        {
            ArrayList<Phrase>phrases = text.get(i).getPhrases();
            size+=phrases.size();
        }
        System.out.println((double)size/text.size());
        return (double) size/text.size();
    }
}
