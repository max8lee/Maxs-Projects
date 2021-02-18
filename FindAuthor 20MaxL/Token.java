/**
 * Code for the Token class.
 * @author Max Lee
 * @version 03/01/19
 */
public class Token
{
    private Scanner.TOKEN_TYPE t;
    private String s;
    /**
     * Constructor for Token objects.
     * @param t1 the type of the token
     * @param s1 the string associated with the token
     */
    public Token (Scanner.TOKEN_TYPE t1, String s1)
    {
        s = s1;
        t = t1;
    }

    /**
     * Getter method for the token type
     * @return the type of the token
     */
    public Scanner.TOKEN_TYPE getType()
    {
        return t;
    }

    /**
     * Getter method for the string
     * @return the string associated with token
     */
    public String getString()
    {
        return s;
    }

    /**
     * To-string method for tokens.
     * @return the token expressed as a string
     */
    public String toString()
    {
        return t + ":" + s;
    }

    /**
     * Equals method for tokens.
     * @param token2 the token to be compared with
     * @return true if the tokens are equal; otherwise,
     *         false
     */
    public boolean equals(Token token2)
    {
        return getType() == token2.getType() && 
        getString() == token2.getString();
    }
}