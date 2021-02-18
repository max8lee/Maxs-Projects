import java.io.*;
import java.io.BufferedReader;
import java.util.*;
import java.io.IOException;
import java.io.*;
/**
 * The MysteryText class computes the weighted average of linguistic
 * features for the mystery texts.
 * 
 * @author Max Lee
 * @version 5-28-19
 */
public class MysteryText
{
    private String path;
    private Document document;
    private DocumentStatistics docstats;
    private double total;
    private AuthorStats authorstats;
    private double[] stats;
    /**
     * Constructor for a MysteryText
     * @param	path1 the file path to be taken in
     */
    public MysteryText(String path1)
    {  
        path = path1;
		stats = new double[5];
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(path));
            Scanner scanner = new Scanner(br);
            Document document = new Document(scanner);
            document.parseDocument();
            docstats = new DocumentStatistics(document);
            stats[0] = docstats.getAverageWordLength();
            stats[1] = docstats.getTypeTokenRatio();
            stats[2] = docstats.getHapaxLegomenaRatio();
            stats[3] = docstats.getAverageWordsPerSentence();
            stats[4] = docstats.getSentenceComplexity();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("There is no file.");
        }
    }

    /**
     * This method finds the weighted average of the linguistic
     * features of the mystery text object, weighted according 
     * to the presentation.
     * @return	a double representing the weighted average
     */
    public double findTotal()
    {
        double total = 0;
        total = stats[0]*11 + stats[1]*33 + stats[2]*50 + (0.4)*stats[3] + stats[4]*4;
        return total;
    }
}
