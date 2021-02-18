import java.util.ArrayList;


import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * This class compares 2 DNA sequences using an array to find
 * common strands of bases in both sequences. It does this by 
 * finding the largest number in the array.
 * 
 * The Big O for each array search is O(n*m), where n and m are
 * the lengths of the two strings being compared.
 * 
 * After comparing, the program will find each LCS until it finds
 * every LCS that is longer than 4 characters, and it will return
 * a JFrame with these common substrings highlighted in both sequences.
 * 
 * The program runs the O(n*m) program every time it finds a common
 * substring, since the program has to return the substring and
 * wouldn't handle detecting and returning multiple substrings at once well.
 * The program is more simple and still pretty efficient.
 * 
 * Difficult cases:
 * 
 * When a new LCS is made by concatenating unmod1 or unmod2. RESOLVED
 * 
 * When the LCS occurs multiple times in one strand and a 
 * different amount of times in the other, how to detect and
 * color every instance. (USE LOOP?)
 * 
 * When a new LCS is made by concat, but it combines
 * with one that's supposed to exist by itself.
 * 
 * CURRENTLY: program highlights LCS that are part of bigger
 * strands. If this needs to be fixed, it can be by changing
 * what the program sets to 0.
 * 
 * 
 * 
 * @author Max Lee
 * @version 7-17-19
 *
 */
public class bruteForce
{
    private String sequence1; //first sequence
    
    private String sequence2; //second sequence
    
    private String finalBothSequencesColored; //final String
    
    public bruteForce(String s1, String s2)
    {
        sequence1 = s1;
        sequence2 = s2;
        finalBothSequencesColored = "";
        
    }
    
    public void bruteForceAlgorithm()
    {
       
        ArrayList<String> LCS = longestCommonAlgorithm(sequence1, sequence2);
        
        for(String s: LCS)
        {
        
        int ind1 = sequence1.indexOf(s);
        int ind2 = sequence2.indexOf(s);

        if(ind1 != -1 && ind2 != -1)
        {
            
            sequence1 = sequence1.substring(0, ind1) + "<font color=\"#990000\">" 
                    + sequence1.substring(ind1, ind1 + s.length()) + 
                    "</font>" + sequence1.substring(ind1 + s.length()); 
            //color the LCS
            
            sequence2 = sequence2.substring(0, ind2) + "<font color=\"#990000\">" 
                    + sequence2.substring(ind2, ind2 + s.length()) + 
                    "</font>" + sequence2.substring(ind2 + s.length()); 
            //color the LCS
            
            ind1 = sequence1.indexOf(s, ind1+29+s.length()); 
            //account for extra occurrences
            ind2 = sequence2.indexOf(s, ind2+29+s.length()); 
            //account for extra occurrences
        
            while(ind1 != -1)
            {
                sequence1 = sequence1.substring(0, ind1) + "<font color=\"#990000\">" 
                        + sequence1.substring(ind1, ind1 + s.length()) + 
                        "</font>" + sequence1.substring(ind1 + s.length()); 
                ind1 = sequence1.indexOf(s, ind1+29+s.length());
                
            }
            
            while(ind2 != -1)
            {
                sequence2 = sequence2.substring(0, ind2) + "<font color=\"#990000\">" 
                        + sequence2.substring(ind2, ind2 + s.length()) + 
                        "</font>" + sequence2.substring(ind2 + s.length()); 
                ind2 = sequence2.indexOf(s, ind2+29+s.length());
            }

        }
        }
    }
    
    
    /**
     * Finds and returns the longest common substrings of 
     * Strings X and Y 4 characters long or more.
     * @param X first DNA sequence
     * @param Y second DNA sequence
     * @return Longest common substring of X and Y
     */
    public ArrayList<String> longestCommonAlgorithm(String X, String Y) 
    { 
        ArrayList<String> LCS = new ArrayList<String>(); 
        
        boolean stopAlgorithm = false; //triggered when while loop needs to finish
        
        int m = X.length();
        int n = Y.length();
        /* Create a table storing all of the lengths of longest
        common suffixes of substrings. The first row and first
        column have no meaning, they are there to make the program
        more simple. */
        int[][] longestCommonSuff = new int[m + 1][n + 1]; 
  
        int len = 0; //length of longest common substring
  
        int row = 0, col = 0; //index of max value, to help build string at the end
  
            //make the array
            for (int i = 0; i <= m; i++) { 
                for (int j = 0; j <= n; j++) { 
                    if (i == 0 || j == 0) 
                        longestCommonSuff[i][j] = 0; 
  
                    else if (X.charAt(i - 1) == Y.charAt(j - 1)) { 
                        longestCommonSuff[i][j] = longestCommonSuff[i - 1][j - 1] + 1; 
                        if (len < longestCommonSuff[i][j]) { 
                            len = longestCommonSuff[i][j]; 
                            row = i; 
                            col = j; 
                        } 
                    } 
                    else
                    {
                        longestCommonSuff[i][j] = 0; 
                    }
                } 
            } 
            
            while(!stopAlgorithm)
            {
                for (int i = 0; i <= m; i++) { 
                    for (int j = 0; j <= n; j++) { 
                        if (len < longestCommonSuff[i][j]) { 
                            len = longestCommonSuff[i][j]; 
                            row = i; 
                            col = j; 
                        } 
                    }
                }
                
            /* if the length is below 4 it's just a coincidence, 
            so no common substring exists. Program returns */
            if (len < 4) { 
                stopAlgorithm = true; 
            } 
            
            else
            {

                // allocate space for the longest common substring 
                String resultStr = ""; 
  
                // make resultStr become the longest common substring
                while (longestCommonSuff[row][col] != 0) { 
            
                    resultStr = X.charAt(row - 1) + resultStr; // or Y[col-1]
            
                    longestCommonSuff[row][col] = 0; //kill it to find second longest
            
                    --len; 
  
                    // move diagonally up to previous cell 
                    row--; 
                    col--; 
                } 

                // required longest common substring 
                if(LCSContains(resultStr, LCS))
                {
                LCS.add(resultStr);
                System.out.println(resultStr);
                }
        
            }
        }
        return LCS;
    } 
    
    public boolean LCSContains(String resultStr, ArrayList<String> LCS)
    {
        for (String s: LCS)
        {
            if (sequence1.indexOf(resultStr)>(sequence1.indexOf(s)-resultStr.length()) && 
                    sequence1.indexOf(resultStr)<(sequence1.indexOf(s)+s.length()))
            {
                return false;
            }
            if (sequence2.indexOf(resultStr)>(sequence2.indexOf(s)-resultStr.length()) && 
                    sequence2.indexOf(resultStr)<(sequence2.indexOf(s)+s.length()))
            {
                return false;
            }
        }
        return true;
    }
  
    public void makeFinalString()
    {
        finalBothSequencesColored = sequence1 + " " + sequence2;
    }
    
    public String getFinal()
    {
        return finalBothSequencesColored;
    }
    
    public static void main(String [] args)
    {
        bruteForce b = new bruteForce("acttcgtaccgtactcgggggtaccgttcccggtgccagtgaaacaatag", 
                "ctaggaataggagtacggtccaccgtactcgggggtaccgttccctggtgaca");
        b.bruteForceAlgorithm();
        b.makeFinalString();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label = new JLabel("<html>" + b.getFinal() + "</html>");
        frame.add(label);
        frame.pack();
        frame.setVisible(true);
    }
}
