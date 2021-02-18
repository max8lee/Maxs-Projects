import java.io.*;
import java.io.BufferedReader;
import java.util.*;
import java.io.IOException;
import java.io.*;

/**
 * Computes the statistics for each of the authors.
 * 
 * @author Max Lee
 * @version 5-28-19
 */
public class AuthorStats
{
    private double [][] values;
    private double[] linguisticStats;
    private String path;
    private String [] names = {"agatha.christie", "alexandre.dumas", "brothers.grim", "charles.dickens", 
            "douglas.adams", "emily.bronte", "fyodor.dostoevsky", "james.joyce", 
            "jane.austen", "lewis.caroll", "mark.twain", "sir.arthur.conan.doyle",
            "william.shakespeare"};
    /**
     * Constructs an AuthorStats object.
     */
    public AuthorStats()
    {
        values = new double [13][5];
        for (int i = 0; i < 13; i++)
        {
            try
            {
                BufferedReader br = new BufferedReader(new FileReader("/Users/maxlee/Desktop/SignatureFiles/"
                            + names[i] + ".stats"));

                try
                {
                    br.readLine();
                    for (int j = 0; j < 5; j++)
                    {
                        values[i][j] = Double.parseDouble(br.readLine());
                    }
                }
                catch(Exception e)
                {
                    System.out.println("There is an error.");
                }
            }
            catch(FileNotFoundException e)
            {
                System.out.println("There is no file.");
            }
        }
    }

    /**
     * This method finds the final weighted number for 
     * each author.
     * @return	an array of size 13 that has each author's stats.
     */
    public double[] findTotals()
    {
        linguisticStats = new double[13];
        for (int i = 0; i < 13; i++)
        {
            double total = 0;
            total = values[i][0]*11 + values[i][1]*33 + values[i][2]*50 + values[i][3]*.4 + values[i][4]*4;
            linguisticStats[i] = total;
        }
        return linguisticStats;
    }

    /**
     * Returns the names array.
     * @return	the array of the names of the authors
     */
    public String[] getNames()
    {
        return names;
    }
    
    /**
     * Returns the linguisticStats array.
     * @return	the weighted average for each author's stats
     */
    public double[] getTotals()
    {
        return linguisticStats;
    }
}