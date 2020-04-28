import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class UserInterface {

    /**
     * Runs the GUI
     */
    public static void play() {
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new MainFrame("Euro Trip Destination Recommender");
                frame.setSize(650, 860);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
        
    }

}