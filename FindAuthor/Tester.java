
/**
 * Final tester for FindAuthor.
 * 
 * @author Max Lee
 * @version 5-28-19
 */
public class Tester 
{
    /**
     * Tests the entire program by finding the author that 
     * matches the linguistic features of the text the closest.
     *
     * @param args arguments from the command line
     */
    public static void main(String [ ] args)
    {   
        AuthorStats authorStats = new AuthorStats();
		double[] authorStatistics = authorStats.findTotals();
		double min = (double) Integer.MAX_VALUE;
		int minIndex = 0;
        // another for loop for linguistic features to the array, find closest match
        for (int i = 1; i < 6; i++)
        {
            MysteryText mystery = new MysteryText("/Users/maxlee/Desktop/MysteryText/"
                            + "mystery" + i + ".txt");
            double total = mystery.findTotal();
            minIndex = 0;
            min = (double) Integer.MAX_VALUE;
            for (int j = 0; j < 13; j++)
            {
				if (Math.abs(authorStatistics[j]-total) < min)
				{
				    min = Math.abs(authorStatistics[j]-total);
				    minIndex = j;
				}
            }
            System.out.println(authorStats.getNames()[minIndex]);
        }
    }

}
