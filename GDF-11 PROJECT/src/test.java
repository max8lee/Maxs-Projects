import javax.swing.JFrame;
import javax.swing.JLabel;


public class test
{
   private String s;
    
    public test()
    {
        
        s = "<html>hello</html>";
        
    }
    
    public void mod()
    {
        s = "greetings";
    }
    
    public static void main(String [] args)
    {
        test t = new test();
        t.mod();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JFrame frame2 = new JFrame();
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label = new JLabel("<html>hello hello <font color=\"#00FF00\">hello</font></html>");
        JLabel label2 = new JLabel("ji");

        // JLabel label = new JLabel("<html>this is something I want people to <font color=\"#00FF00\">NOTICE</font></html>");//will be shown on single line
        
        frame.add(label);
        

        frame.pack();
        frame.setVisible(true);
        
        frame2.add(label2);
        frame2.pack();
        frame2.setVisible(true);
    }
}
