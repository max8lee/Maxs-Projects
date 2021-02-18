import java.io.IOException;
import java.io.Reader;

/**
 * A Scanner is responsible for reading an input stream, one character at a
 * time, and separating the input into tokens.  A token is defined as:
 *  1. A 'word' which is defined as a non-empty sequence of characters that 
 *     begins with an alpha character and then consists of alpha characters, 
 *     numbers, the single quote character "'", or the hyphen character "-". 
 *  2. An 'end-of-sentence' delimiter defined as any one of the characters 
 *     ".", "?", "!".
 *  3. An end-of-file token which is returned when the scanner is asked for a
 *     token and the input is at the end-of-file.
 *  4. A phrase separator which consists of one of the characters ",",":" or
 *     ";".
 *  5. A digit.
 *  6. Any other character not defined above.
 * @author Mr. Page
 * @author Max Lee
 * @version 03/01/19
 */

public class Scanner
{
    private Reader in;
    private String currentChar;
    private boolean endOfFile;
    /**
     * The token types are listed below.
     */
    public static enum TOKEN_TYPE
    {
        /**
         * a word starts with letters.
         */
        WORD, 
        /**
         * an end of sentence token is a period, question mark, 
         * or exclamation point
         */
        END_OF_SENTENCE, 
        /**
         * an end of file token is after the final 
         * character in the file
         */
        END_OF_FILE, 
        /**
         * an end of phrase token is a comma, semicolon, or colon
         */
        END_OF_PHRASE, 
        /**
         * a digit is any number
         */
        DIGIT, 
        /**
         * unknown is anything else
         */
        UNKNOWN
    };
    
    /**
     * Constructor for Scanner objects.  The Reader object should be one of
     *  1. A StringReader
     *  2. A BufferedReader wrapped around an InputStream
     *  3. A BufferedReader wrapped around a FileReader
     *  The instance field for the Reader is initialized to the input parameter,
     *  and the endOfFile indicator is set to false.  The currentChar field is
     *  initialized by the getNextChar method.
     * @param in is the reader object supplied by the program constructing
     *        this Scanner object.
     */
    public Scanner(Reader in)
    {
        this.in = in;
        endOfFile = false;
        getNextChar();
    }
    /**
     * The getNextChar method attempts to get the next character from the input
     * stream.  It sets the endOfFile flag true if the end of file is reached on
     * the input stream.  Otherwise, it reads the next character from the stream
     * and converts it to a Java String object.
     * postcondition: The input stream is advanced one character if it is not at
     * end of file and the currentChar instance field is set to the String 
     * representation of the character read from the input stream.  The flag
     * endOfFile is set true if the input stream is exhausted.
     */
    private void getNextChar()
    {
        try
        {
            int inp = in.read();
            if(inp == -1) 
                endOfFile = true;
            else 
                currentChar = "" + (char) inp;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.exit(-1);
        }
    }
    
    /**
     * Gets next character if the input string matches the current character. 
     * Otherwise, throws an exception.
     * @param s input string
     */
    private void eat(String s)
    {
        if (s.equals(currentChar))
        {
            getNextChar();
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }
    
    /**
     * Checks if character is a letter.
     * @param s character being tested
     * @return true if s is a letter; otherwise,
     *         false
     */
    private boolean isLetter(String s)
    {
        return s.toLowerCase().compareTo("a") >= 0 &&
        s.toLowerCase().compareTo("z") <= 0;
    }

    /**
     * Checks if character is a digit
     * @param s character being tested
     * @return true if s is a digit; otherwise,
     *         false
     */
    private boolean isDigit(String s)
    {
        return s.compareTo("0") >= 0 && s.compareTo("9") <= 0;
    }

    /**
     * Checks if character is a special character 
     * @param s character being tested
     * @return true if s is a special character; otherwise,
     *         false
     */
    private boolean isSpecialCharacter(String s)
    {
        return s.equals("-") || s.equals("'");
    }

    /**
     * Checks if character is a phrase terminator
     * @param s character being tested
     * @return true if s is a phrase terminator; otherwise,
     *         false
     */
    private boolean isPhraseTerminator(String s)
    {
        return s.equals(",") || s.equals(":") || s.equals(";");
    }

    /**
     * Checks if character is a sentence terminator
     * @param s character being tested
     * @return true if s is a sentence terminator; otherwise,
     *         false
     */
    private boolean isSentenceTerminator(String s)
    {
        return s.equals(".") || s.equals("?") || s.equals("!");
    }

    /**
     * Checks if character is a white space
     * @param s character being tested
     * @return true if s is a white space; otherwise,
     *         false
     */
    private boolean isWhiteSpace(String s)
    {
        return s.equals(" ") || s.equals("\t") || s.equals("\n") 
                || s.equals("\r");
    }
    
    /**
     * Checks if there is a next token
     * @return true if there is a next token; otherwise,
     *         false
     */
    public boolean hasNextToken()
    {
        return !endOfFile;
    }
    
    /**
     * Returns the next token if it has next token
     * @return the next token with the correct token type and string
     */
    public Token nextToken()
    {
        if (hasNextToken())
        {
            while (hasNextToken() && isWhiteSpace(currentChar))
                eat(currentChar);
            String string = "";
            if (isLetter(currentChar))
            {
                while (hasNextToken() && isLetter(currentChar) 
                       || isDigit(currentChar) 
                       || isSpecialCharacter(currentChar))                  
                {
                    string = string + currentChar.toLowerCase();
                    eat(currentChar);
                }                   
                return new Token(TOKEN_TYPE.WORD, string);
            }
            else if (isDigit(currentChar))
            {
                String char1 = currentChar;
                eat(currentChar);
                return new Token(TOKEN_TYPE.DIGIT, char1);
            }
            else if (isPhraseTerminator(currentChar))
            {
                String char1 = currentChar;
                eat(currentChar);
                return new Token(TOKEN_TYPE.END_OF_PHRASE, char1);
            }
            else if (isSentenceTerminator(currentChar))
            {
                String char1 = currentChar;
                eat(currentChar);
                return new Token(TOKEN_TYPE.END_OF_SENTENCE, char1);
            }
            else if (hasNextToken())
            {
                String char1 = currentChar;
                eat(currentChar);
                return new Token(TOKEN_TYPE.UNKNOWN, char1);
            }
            else
            {
                String char1 = currentChar;
                eat(currentChar);
                return new Token(TOKEN_TYPE.END_OF_FILE, "");
            }
        }
        String char1 = currentChar;
        eat(currentChar);
        return new Token(TOKEN_TYPE.END_OF_FILE, char1);
    }
}