import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.*;
 
 
public class BoardPanelMuyi {
public static JFrame boardFrame;
private JDialog dialog = new JDialog(); 
public static int dimensionOfBoard;


public BoardPanelMuyi() {
     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
     Insets scnMax = Toolkit.getDefaultToolkit().getScreenInsets(dialog.getGraphicsConfiguration());
     int taskBarSize = scnMax.bottom;
     int macMenuBarSize = scnMax.top;
     final int maxHeight = (int)screenSize.getHeight() - taskBarSize - macMenuBarSize;
     //int[] currentHeight = {maxHeight};
    
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