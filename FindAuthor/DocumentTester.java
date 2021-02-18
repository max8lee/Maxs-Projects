import java.io.IOException;
import java.io.*;

/**
 * This is a tester for the Document Class.
 * 
 * @author Max Lee
 * @version 5-28-19
 */
public class DocumentTester 
{
    /**
     * Tests the document class by giving a string to parse.
     *
     * @param args arguments from the command line
     */
    public static void main(String [] args)
    {
        String s = "this is the \nfirst sentence. Isn't\nit? Yes it is";
        s += " This \n\nlast bit :) is also a sentence, but \nwithout a terminator other than ";
        s += "this is the end of the file\n";
        StringReader reader = new StringReader(s);
        Scanner scanner = new Scanner(reader);
        Document document = new Document(scanner);
        System.out.println(document.parseSentence());
        document.parseDocument();
        document.getDocument();
    }

}
