import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class UserInterface {

    /**
     * This is the main method from which the GUI is launched
     * @param args
     */
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new MainFrame("Euro Trip Destination Recommender");
                frame.setSize(650, 725);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
        
    }

}
