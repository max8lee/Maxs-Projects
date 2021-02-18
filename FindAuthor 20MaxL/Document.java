import java.util.*;
/**
 * The class defining a Document.
 * 
 * @author Max Lee
 * @version 5-28-19
 */
public class Document
{
    private ArrayList<Sentence> sentences;
    private Token currentToken;
    private Scanner scanner;

    /**
     * Constructor for Documents. An ArrayList is used for O(1) 
     * add time and O(n) retrieval time.
     * @param	scanner1 the scanner that a Document object takes in
     */
    public Document(Scanner scanner1)
    {
        sentences = new ArrayList<Sentence>();
        scanner = scanner1;
        getNextToken();
    }

    /**
     * Private method to get the next token from the scanner.
     */
    private void getNextToken()
    {
        currentToken = scanner.nextToken();
    }

    /**
     * Private method that gets the next token. Throws an exception if the 
     * current tokens mismatch.
     * @param	token1 the current token
     */
    private void eat(Token token1)
    {
        if (currentToken.equals(token1))
        {
            getNextToken();
        }
        else
        {
            throw new IllegalArgumentException("Token mismatch Expected to see " + currentToken + " found " + token1 + " instead" );
        }
    }

    /**
     * Parses the phrase and adds it to the current phrase.
     * @return	the phrase that was parsed
     */
    public Phrase parsePhrase()
    {
        Phrase currentPhrase = new Phrase();
        while (!currentToken.getType().equals(Scanner.TOKEN_TYPE.END_OF_PHRASE)
        && !currentToken.getType().equals(Scanner.TOKEN_TYPE.END_OF_FILE)
        && !currentToken.getType().equals(Scanner.TOKEN_TYPE.END_OF_SENTENCE))
        {
            if (currentToken.getType().equals(Scanner.TOKEN_TYPE.WORD))
            {
                currentPhrase.addToken(currentToken);

            }
            eat(currentToken);
        }
        if (currentToken.getType().equals(Scanner.TOKEN_TYPE.END_OF_PHRASE))
                       eat(currentToken);
        return currentPhrase;
    }

    /**
     * Calls parsePhrase() to parse the entire sentence. Adds the parsed
     * phrases to the sentence.
     * @return	the sentence that is parsed
     */
    public Sentence parseSentence()
    {
        Sentence sentence1 = new Sentence();
        while (!currentToken.getType().equals(Scanner.TOKEN_TYPE.END_OF_FILE) && 
        !currentToken.getType().equals(Scanner.TOKEN_TYPE.END_OF_SENTENCE))
        {
            sentence1.addPhrase(parsePhrase());
        }
        if (currentToken.getType().equals(Scanner.TOKEN_TYPE.END_OF_SENTENCE))
            eat(currentToken);
        return sentence1;
    }

    /**
     * Calls parseSentence() to parse the entire document. 
     * Adds the parsed sentences to the sentences ArrayList.
     */
    public void parseDocument()
    {
        while (!currentToken.getType().equals(Scanner.TOKEN_TYPE.END_OF_FILE) && !currentToken.getType().equals(Scanner.TOKEN_TYPE.WORD))
                       eat(currentToken);
        while (!currentToken.getType().equals(Scanner.TOKEN_TYPE.END_OF_FILE))
        {
            sentences.add(parseSentence());
        }
    }

    /**
     * Returns the document.
     * @return	the ArrayList of sentences
     */
    public ArrayList<Sentence> getDocument()
    {
        return sentences;
    }
}
