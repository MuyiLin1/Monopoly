import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.*;

/**
 * Intends to create a BoardPanelMuyi object and 
 * creates specific methods that are neccesary. 
 * The board panel is a monopoly board that fits 
 * the size of the screen.
 *
 * @author Muyi
 * @version 19 May 2022
 * @author Period: 5
 * 
 * @author Assignment: APCS_Final_Project
 * 
 * @author Sources: None
 */
public class BoardPanelMuyi {

    /**
     * Intends to create a field that stores the board
     */
    public static JFrame boardFrame;
    private JDialog dialog = new JDialog(); 

    /**
     * Intends to create a field that stores the dimension of the board
     */
    public static int dimensionOfBoard;

    /**
     * Intends to create a field that stores the dimension of the screen size
     */
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    /**
     * Intends to intialize the BoardPanelMuyi object
     */
    public BoardPanelMuyi() {
        Insets scnMax = Toolkit.getDefaultToolkit().getScreenInsets(dialog.getGraphicsConfiguration());
        int taskBarSize = scnMax.bottom;
        int macMenuBarSize = scnMax.top;
        final int maxHeight = (int)screenSize.getHeight() - taskBarSize - macMenuBarSize;
        //int[] currentHeight = {maxHeight};
        
        System.out.println("max height" + maxHeight);

        dimensionOfBoard = maxHeight;
    
        JLabel label = new JLabel();
        Dimension labelMaxSize = new Dimension(maxHeight,maxHeight);
        label.setMaximumSize(labelMaxSize);
        
        boardFrame = new JFrame();
        boardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        boardFrame.setResizable(true);
        boardFrame.setUndecorated(true);
        
        boardFrame.setSize(maxHeight,maxHeight);
    
        label.setIcon(new ImageIcon(new ImageIcon("gameboard.png").getImage().getScaledInstance(maxHeight, maxHeight, Image.SCALE_SMOOTH)));
    
        
        boardFrame.setVisible(true);
        boardFrame.add(label);
        boardFrame.pack();
        
    }
    public static void main(String[] args) {
        new BoardPanelMuyi();
    
    }
}