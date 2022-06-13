import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Player Movement creates an instance of BoardPanelMuyi and sets 4 pieces on the board. 
 * The pieces will be removed from the board based on the player number in GameBoard. 
 * It has an ArrayList of all possible center locations on the board for each Monopoly “property”
 * including Go, Jail, and Free Parking. It also has the newPosition class which shifts each player 
 * slightly differently at every property so that all 4 pieces can be on one property at one time. 
 * There is a rotate method that creates an instance of the RotatedIcon class which rotates the image 
 * and it works while it is at GO but it seemingly stops the player icon from moving after I rotate at 
 * different properties.
 *
 * @author Tiffany, Muyi, Anil
 * @version 19 May 2022
 * @author Period: 5
 * 
 * @author Assignment: APCS_Final_Project
 * 
 * @author Sources: RotatedIcon: https://www.javatips.net/api/lichtstrahlen-master/LichtstrahlenSpiel-master/de.bwvaachen.beamoflightgame/src/main/java/de/bwvaachen/beamoflightgame/ui/RotatedIcon.java 
 *                  Remove JLabel from the JFrame: https://stackoverflow.com/questions/11438512/fully-remove-jlabel-from-jpanel-not-setvisiblefalse  
 */
public class PlayerMovement{
 
//ROTATED ICON CLASS IS COPIED FROM https://www.javatips.net/api/lichtstrahlen-master/LichtstrahlenSpiel-master/de.bwvaachen.beamoflightgame/src/main/java/de/bwvaachen/beamoflightgame/ui/RotatedIcon.java
// DOWN - rotated 90 degrees
//  * <li>UP (default) - rotated -90 degrees
//  * <li>UPSIDE_DOWN - rotated 180 degrees
// remove JLabel from the JFrame: https://stackoverflow.com/questions/11438512/fully-remove-jlabel-from-jpanel-not-setvisiblefalse 
 
  ArrayList<Dimension> centerLocs;
  BoardPanelMuyi bp;
   private final double propertyWidth = bp.boardFrame.getWidth() * 0.081;
   private final double cornerSize;
   private final double propertyHeight;
   private ImageIcon image1 = new ImageIcon(new ImageIcon("MonopolyShip.png").getImage().getScaledInstance( (int)(propertyWidth/2.5), (int)(propertyWidth/2.5), Image.SCALE_SMOOTH));
   private ImageIcon image2 = new ImageIcon(new ImageIcon("MonopolyDog.png").getImage().getScaledInstance((int)(propertyWidth/2.5), (int)(propertyWidth/2.5), Image.SCALE_SMOOTH));
   private ImageIcon image3 = new ImageIcon(new ImageIcon("MonopolyShoe.png").getImage().getScaledInstance((int)(propertyWidth/2.5), (int)(propertyWidth/2.5), Image.SCALE_SMOOTH));
   private ImageIcon image4 = new ImageIcon(new ImageIcon("MonopolyCar.png").getImage().getScaledInstance((int)(propertyWidth/2.5), (int)(propertyWidth/2.5), Image.SCALE_SMOOTH));
   private ImageIcon image5 = new ImageIcon(new ImageIcon("Transparent.png").getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH));
   private JLabel label5 = new JLabel();
   private JLabel label1 = new JLabel(image1);
   private JLabel label2 = new JLabel(image2);
   private JLabel label3 = new JLabel(image3);
   private JLabel label4 = new JLabel(image4);
   private Dimension size = label1.getPreferredSize();

    
   /**
    * Intends to intialize a Player Movement object
    * @param p the board
    */
   public PlayerMovement(BoardPanelMuyi p){
       bp = p;
       cornerSize = bp.boardFrame.getWidth() * 0.135;
       centerLocs = new ArrayList<Dimension>(40);
       propertyHeight = cornerSize;
       //
       centerLocs.add(0, new Dimension( (int)(propertyWidth * 9 + cornerSize * 1.5), (int)(propertyWidth * 9 + cornerSize * 1.5) ));
       centerLocs.add(1, new Dimension( (int)(propertyWidth * 8.5 + cornerSize * 1), (int)(propertyWidth * 9 + cornerSize * 1.5)));
       centerLocs.add(2, new Dimension( (int)(propertyWidth * 7.5 + cornerSize * 1), (int)(propertyWidth * 9 + cornerSize * 1.5)));
       centerLocs.add(3, new Dimension( (int)(propertyWidth * 6.5 + cornerSize * 1), (int)(propertyWidth * 9 + cornerSize * 1.5)));
       centerLocs.add(4, new Dimension( (int)(propertyWidth * 5.5 + cornerSize * 1), (int)(propertyWidth * 9 + cornerSize * 1.5)));
       centerLocs.add(5, new Dimension( (int)(propertyWidth * 4.5 + cornerSize * 1), (int)(propertyWidth * 9 + cornerSize * 1.5)));
       centerLocs.add(6, new Dimension( (int)(propertyWidth * 3.5 + cornerSize * 1), (int)(propertyWidth * 9 + cornerSize * 1.5)));
       centerLocs.add(7, new Dimension( (int)(propertyWidth * 2.5 + cornerSize * 1), (int)(propertyWidth * 9 + cornerSize * 1.5)));
       centerLocs.add(8, new Dimension( (int)(propertyWidth * 1.5 + cornerSize * 1), (int)(propertyWidth * 9 + cornerSize * 1.5)));
       centerLocs.add(9, new Dimension( (int)(propertyWidth * 0.5 + cornerSize * 1), (int)(propertyWidth * 9 + cornerSize * 1.5)));
       //10 in jail
       //__________________________________________________________________________________________________________________________________________________
       //jail value isnt even being used lmfaoooo
       //__________________________________________________________________________________________________________________________________________________
       centerLocs.add(10, new Dimension( (int)(cornerSize * 0.5), (int)(propertyWidth * 9 + cornerSize * 1.5)));
       //start changing y
       centerLocs.add(11, new Dimension( (int)(cornerSize * 0.5), (int)(propertyWidth * 8.5 + cornerSize )));
       centerLocs.add(12, new Dimension( (int)(cornerSize * 0.5), (int)(propertyWidth * 7.5 + cornerSize )));
       centerLocs.add(13, new Dimension( (int)(cornerSize * 0.5), (int)(propertyWidth * 6.5 + cornerSize )));
       centerLocs.add(14, new Dimension( (int)(cornerSize * 0.5), (int)(propertyWidth * 5.5 + cornerSize )));
       centerLocs.add(15, new Dimension( (int)(cornerSize * 0.5), (int)(propertyWidth * 4.5 + cornerSize )));
       centerLocs.add(16, new Dimension( (int)(cornerSize * 0.5), (int)(propertyWidth * 3.5 + cornerSize )));
       centerLocs.add(17, new Dimension( (int)(cornerSize * 0.5), (int)(propertyWidth * 2.5 + cornerSize )));
       centerLocs.add(18, new Dimension( (int)(cornerSize * 0.5), (int)(propertyWidth * 1.5 + cornerSize )));
       centerLocs.add(19, new Dimension( (int)(cornerSize * 0.5), (int)(propertyWidth * 0.5 + cornerSize )));
       //20 IS EDGE
       centerLocs.add(20, new Dimension( (int)(cornerSize * 0.5), (int)( cornerSize * 0.5)));
       //CHANGE X AGAIN
       centerLocs.add(21, new Dimension( (int)(propertyWidth * 0.5 +cornerSize ), (int)( cornerSize * 0.5)));
       centerLocs.add(22, new Dimension( (int)(propertyWidth * 1.5 +cornerSize ), (int)( cornerSize * 0.5)));
       centerLocs.add(23, new Dimension( (int)(propertyWidth * 2.5 +cornerSize ), (int)( cornerSize * 0.5)));
       centerLocs.add(24, new Dimension( (int)(propertyWidth * 3.5 +cornerSize ), (int)( cornerSize * 0.5)));
       centerLocs.add(25, new Dimension( (int)(propertyWidth * 4.5 +cornerSize ), (int)( cornerSize * 0.5)));
       centerLocs.add(26, new Dimension( (int)(propertyWidth * 5.5 +cornerSize ), (int)( cornerSize * 0.5)));
       centerLocs.add(27, new Dimension( (int)(propertyWidth * 6.5 +cornerSize ), (int)( cornerSize * 0.5)));
       centerLocs.add(28, new Dimension( (int)(propertyWidth * 7.5 +cornerSize ), (int)( cornerSize * 0.5)));
       centerLocs.add(29, new Dimension( (int)(propertyWidth * 8.5 +cornerSize ), (int)( cornerSize * 0.5)));
       //30 IS EDGE
       centerLocs.add(30, new Dimension ((int)(propertyWidth * 9 + cornerSize * 1.5), (int)( cornerSize * 0.5)));
       // CHANGE Y AGAIN
       centerLocs.add(31, new Dimension( (int)(propertyWidth * 9 + cornerSize * 1.5), (int)( propertyWidth * 0.5 + cornerSize )));
       centerLocs.add(32, new Dimension( (int)(propertyWidth * 9 + cornerSize * 1.5), (int)( propertyWidth * 1.5 + cornerSize )));
       centerLocs.add(33, new Dimension( (int)(propertyWidth * 9 + cornerSize * 1.5), (int)( propertyWidth * 2.5 + cornerSize )));
       centerLocs.add(34, new Dimension( (int)(propertyWidth * 9 + cornerSize * 1.5), (int)( propertyWidth * 3.5 + cornerSize )));
       centerLocs.add(35, new Dimension( (int)(propertyWidth * 9 + cornerSize * 1.5), (int)( propertyWidth * 4.5 + cornerSize )));
       centerLocs.add(36, new Dimension( (int)(propertyWidth * 9 + cornerSize * 1.5), (int)( propertyWidth * 5.5 + cornerSize )));
       centerLocs.add(37, new Dimension( (int)(propertyWidth * 9 + cornerSize * 1.5), (int)( propertyWidth * 6.5 + cornerSize )));
       centerLocs.add(38, new Dimension( (int)(propertyWidth * 9 + cornerSize * 1.5), (int)( propertyWidth * 7.5 + cornerSize )));
       centerLocs.add(39, new Dimension( (int)(propertyWidth * 9 + cornerSize * 1.5), (int)( propertyWidth * 8.5 + cornerSize )));
       //
    
        newPosition(1, 0);
        newPosition(2, 0);
        newPosition(3, 0);
        newPosition(4, 0);
    //    label1.setBounds(100,100,image1.getIconWidth(), image1.getIconHeight());
    //    label2.setBounds(100,100,image1.getIconWidth(), image1.getIconHeight());
    //    label3.setBounds(100,100,image1.getIconWidth(), image1.getIconHeight());
    //    label4.setBounds(100,100,image1.getIconWidth(), image1.getIconHeight());

       Dimension size = label1.getPreferredSize();
       
        // GameMode.setUpPlayers();
        

        bp.boardFrame.add(label1);
        bp.boardFrame.add(label2);
        bp.boardFrame.add(label3);
        bp.boardFrame.add(label4);
        bp.boardFrame.add(label5);
        // if(GameMode.players.size() == 2)
        // {
        //     bp.boardFrame.add(label1);
        //     bp.boardFrame.add(label2);
        //     bp.boardFrame.add(label5);
        //     bp.boardFrame.setComponentZOrder(label1, 0);
        //     bp.boardFrame.setComponentZOrder(label2, 0);
        //     bp.boardFrame.setComponentZOrder(label5, 0);
            
        // }
        // else if(GameMode.players.size() == 3)
        // {
        //     bp.boardFrame.add(label1);
        //     bp.boardFrame.add(label2);
        //     bp.boardFrame.add(label3);
        //     bp.boardFrame.add(label5);
        //     bp.boardFrame.setComponentZOrder(label1, 0);
        //     bp.boardFrame.setComponentZOrder(label2, 0);
        //     bp.boardFrame.setComponentZOrder(label3, 0);
        //     bp.boardFrame.setComponentZOrder(label5, 0);
        // }
        // else if(GameMode.players.size() == 4)
        // {
        //     bp.boardFrame.add(label1);
        //     bp.boardFrame.add(label2);
        //     bp.boardFrame.add(label3);
        //     bp.boardFrame.add(label4);
        //     bp.boardFrame.add(label5);
        //     bp.boardFrame.setComponentZOrder(label1, 0);
        //     bp.boardFrame.setComponentZOrder(label2, 0);
        //     bp.boardFrame.setComponentZOrder(label3, 0);
        //     bp.boardFrame.setComponentZOrder(label4, 0);
        //     bp.boardFrame.setComponentZOrder(label5, 0);
        // }
        
       
       bp.boardFrame.setComponentZOrder(label1, 0);
       bp.boardFrame.setComponentZOrder(label2, 0);
       bp.boardFrame.setComponentZOrder(label3, 0);
       bp.boardFrame.setComponentZOrder(label4, 0);
       bp.boardFrame.setComponentZOrder(label5, 0);
       bp.boardFrame.setVisible(true);
       int count = 0;
       
    //    for (Dimension item : centerLocs) {
    //        Dimension player1dim = newPosition(1, count);
    //        Dimension player2dim = newPosition(2, count);
    //        Dimension player3dim = newPosition(3, count);
    //        Dimension player4dim = newPosition(4, count);
    //        label1.setBounds( (int)player1dim.getWidth(), (int)player1dim.getHeight(), size.width,size.height);
    //        label2.setBounds( (int)player2dim.getWidth(), (int)player2dim.getHeight(), size.width,size.height);
    //        label3.setBounds( (int)player3dim.getWidth(), (int)player3dim.getHeight(), size.width,size.height);
    //        label4.setBounds( (int)player4dim.getWidth(), (int)player4dim.getHeight(), size.width,size.height);
    //        count++;
    //        try {
    //            Thread.sleep(1000);
    //        } catch (Exception e) {}
    //    }
       //SOMETHING IS GOING TO BE CALLED HERE AND WE NEED TO FIGURE IT OUT BUT YOU MOVE TO A CERTAIN SPOT
   }

   /**
    * Intends to remove all the players that are not
    * being used
    */
   public void removeUnusedPlayers() {
        if(GameMode.players.size() == 2)
        {
            label1.setVisible(true);
            label2.setVisible(true);
            label3.setVisible(false);
            label4.setVisible(false);
            
        }
        else if(GameMode.players.size() == 3)
        {
            label4.setVisible(false);
        }
   }

    //only case if jailed
    /**
     * Intends to set the player to a specific position 
     * on the jail corner of the board. If the player is just visiting,
     * put the player on the edges, otherwise, put the player
     * inside of jail. 
     * 
     * @param playerNum The player number
     * @param propertyNum The property index on the game board
     * @param isJailed whether or not the person is jailed.
     */
     public void newPosition(int playerNum, int propertyNum, boolean isJailed) {
        
        removeUnusedPlayers();

         Dimension d;
         if (playerNum == 1) {
             //slim part in 3/10 of the width
             d =  new Dimension( (int)( cornerSize*0.32), (int)(cornerSize + propertyWidth*9));
              label1.setBounds( (int)d.getWidth(), (int)d.getHeight(), size.width,size.height);
         }
         else if (playerNum == 2) {
              d =  new Dimension( (int)( cornerSize - size.width), (int)(cornerSize + propertyWidth*9));
              label2.setBounds( (int)d.getWidth(), (int)d.getHeight(), size.width,size.height);
         }

         else if (playerNum == 3) {
             d =  new Dimension( (int)( cornerSize*0.32), (int)(cornerSize*2 + propertyWidth*9 - cornerSize*0.32 - size.height));
              label3.setBounds( (int)d.getWidth(), (int)d.getHeight(), size.width,size.height);
         }

         else if (playerNum == 4) {
             
  d =  new Dimension( (int)( cornerSize - size.width), (int)(cornerSize*2 + propertyWidth*9 - cornerSize*0.32 - size.height));
              label4.setBounds( (int)d.getWidth(), (int)d.getHeight(), size.width,size.height);       }
     }
   
    
    /**
     * Intends to set the player to a specific position on the board.
     * This differs if the player lands on a property card,
     * or the corner pieces. Each piece has it's designated position on each 
     * card. 
     * 
     * @param playerNum the player number
     * @param propertyNum the property index on the game board
     */
   public void newPosition(int playerNum, int propertyNum)
   {
     Dimension d;

        removeUnusedPlayers();

       if(playerNum == 1) // TOP LEFT
       { 
           if ( propertyNum > 0 && propertyNum < 10 ) { // bottom row
            System.out.println("Bottom");
               //x would variate by the width and y variates by height
              d =  new Dimension( (int)( centerLocs.get(propertyNum).getWidth() - propertyWidth/2), (int)(centerLocs.get(propertyNum).getHeight()) );
              label1.setBounds( (int)d.getWidth(), (int)d.getHeight(), size.width,size.height);
           }

            else if ( propertyNum > 20 && propertyNum < 30) { // top row
                System.out.println("Top");
               //x would variate by the width and y variates by height
              d =  new Dimension( (int)( centerLocs.get(propertyNum).getWidth() - propertyWidth/2), (int)(centerLocs.get(propertyNum).getHeight() - propertyHeight/2) );
              label1.setBounds( (int)d.getWidth(), (int)d.getHeight(), size.width,size.height);
           }
           else if ((propertyNum > 10 && propertyNum < 20) ) { // left
               //y would variate by the width and x variates by height
               System.out.println("Left");
              d=  new Dimension( (int)( centerLocs.get(propertyNum).getWidth()- propertyHeight/2 ), (int)(centerLocs.get(propertyNum).getHeight() - propertyWidth / 2) );
              System.out.println("w: " + (int)d.getWidth() + " h: " + (int)d.getHeight());
              label1.setBounds( (int)d.getWidth(), (int)d.getHeight(), size.width,size.height);
           }

           else if ( (propertyNum > 30 && propertyNum < 40)) { //  right
               //y would variate by the width and x variates by height
               System.out.println("Right");
              d=  new Dimension( (int)( centerLocs.get(propertyNum).getWidth() ), (int)(centerLocs.get(propertyNum).getHeight() - propertyWidth / 2) );
              System.out.println("w: " + (int)d.getWidth() + " h: " + (int)d.getHeight());
              label1.setBounds( (int)d.getWidth(), (int)d.getHeight(), size.width,size.height);
           }
           else if (propertyNum == 0 || propertyNum == 20){ //  go and free parking
            System.out.println("Go");
               d = new Dimension( (int)( centerLocs.get(propertyNum).getWidth() - cornerSize/1.75), (int)(centerLocs.get(propertyNum).getHeight() - cornerSize/4) );
               label1.setBounds( (int)d.getWidth(), (int)d.getHeight(), size.width,size.height);
           }
           else { //jail but just visiting
               d = new Dimension( 0, (int) (propertyWidth*9 + cornerSize));
               label1.setBounds( (int)d.getWidth(), (int)d.getHeight(), size.width,size.height);
           }
       }
     
       else if(playerNum == 2) // TOP RIGHT
       { 
           if ( (propertyNum > 0 && propertyNum < 10) ) { //bottom
               //x would variate by the width and y variates by height
               System.out.println("Bottom");
               d = new Dimension( (int)( centerLocs.get(propertyNum).getWidth() + propertyWidth/16 ), (int)(centerLocs.get(propertyNum).getHeight()) );
               label2.setBounds( (int)d.getWidth(), (int)d.getHeight(), size.width,size.height);
           }
             else if ( (propertyNum > 20 && propertyNum < 30)) { //top
               //x would variate by the width and y variates by height
               System.out.println("Top");
              d = new Dimension( (int)( centerLocs.get(propertyNum).getWidth() + propertyWidth/16 ), (int)(centerLocs.get(propertyNum).getHeight() - propertyHeight/2) );
               label2.setBounds( (int)d.getWidth(), (int)d.getHeight(), size.width,size.height);
           }
           else if ((propertyNum > 10 && propertyNum < 20) ) {
               //y would variate by the width and x variates by height
               System.out.println("Left");
               d = new Dimension( (int)( centerLocs.get(propertyNum).getWidth() - propertyHeight/4), (int)(centerLocs.get(propertyNum).getHeight() - propertyWidth/2) );
               label2.setBounds( (int)d.getWidth(), (int)d.getHeight(), size.width,size.height);
           }

           else if ( (propertyNum > 30 && propertyNum < 40)) {
            System.out.println("Right");
               //y would variate by the width and x variates by height
               d = new Dimension( (int)( centerLocs.get(propertyNum).getWidth() + propertyHeight/4), (int)(centerLocs.get(propertyNum).getHeight() - propertyWidth/2) );
               label2.setBounds( (int)d.getWidth(), (int)d.getHeight(), size.width,size.height);
           }
           else if (propertyNum == 0 || propertyNum == 20) { //go and free parking
            System.out.println("Corner");
               d = new Dimension( (int)( centerLocs.get(propertyNum).getWidth() + cornerSize/4), (int)(centerLocs.get(propertyNum).getHeight() - cornerSize/4) );
               label2.setBounds( (int)d.getWidth(), (int)d.getHeight(), size.width,size.height);
           }
           else { //jail but just visiting
                d = new Dimension( 0, (int) (propertyWidth*9 + cornerSize + size.height));
                label2.setBounds( (int)d.getWidth(), (int)d.getHeight(), size.width,size.height);
           }
       }
     
       else if (playerNum == 3) // BOTTOM LEFT
       { 
           if (propertyNum > 0 && propertyNum < 10 ) { //bottom
               //x would variate by the width and y variates by height
               d = new Dimension( (int)( centerLocs.get(propertyNum).getWidth() - propertyWidth/2), (int)(centerLocs.get(propertyNum).getHeight() + propertyHeight/4));
               label3.setBounds( (int)d.getWidth(), (int)d.getHeight(), size.width,size.height);
           }
           else if ( (propertyNum > 20 && propertyNum < 30)) { //top
               //x would variate by the width and y variates by height
               d = new Dimension( (int)( centerLocs.get(propertyNum).getWidth() - propertyWidth/2), (int)(centerLocs.get(propertyNum).getHeight() - propertyHeight/4));
               label3.setBounds( (int)d.getWidth(), (int)d.getHeight(), size.width,size.height);
           }
           else if ((propertyNum > 10 && propertyNum < 20) ) { //left
               //y would variate by the width and x variates by height
               d = new Dimension( (int)( centerLocs.get(propertyNum).getWidth() - propertyHeight/2), (int)(centerLocs.get(propertyNum).getHeight() + propertyWidth/16) );
               label3.setBounds( (int)d.getWidth(), (int)d.getHeight(), size.width,size.height);
           }

           else if ( (propertyNum > 30 && propertyNum < 40)) { //right
               //y would variate by the width and x variates by height
               d = new Dimension( (int)( centerLocs.get(propertyNum).getWidth() ), (int)(centerLocs.get(propertyNum).getHeight() + propertyWidth/16) );
               label3.setBounds( (int)d.getWidth(), (int)d.getHeight(), size.width,size.height);
           }

           else if (propertyNum == 0 || propertyNum == 20) { //go and free parking
               d = new Dimension( (int)( centerLocs.get(propertyNum).getWidth() - cornerSize/1.75), (int)(centerLocs.get(propertyNum).getHeight() + cornerSize/4) );
               label3.setBounds( (int)d.getWidth(), (int)d.getHeight(), size.width,size.height);
           }
           else { //jail but just visiting
                 d = new Dimension( 0, (int) (propertyWidth*9 + cornerSize + size.height*2));
                label3.setBounds( (int)d.getWidth(), (int)d.getHeight(), size.width,size.height);
           }
       }
       //playernumber 4
       else if (playerNum == 4) // BOTTOM RIGHT
       { 
           if ( propertyNum > 0 && propertyNum < 10 ) { //bottom
               //x would variate by the width and y variates by height
               d =  new Dimension( (int)( centerLocs.get(propertyNum).getWidth() + propertyWidth / 16), (int)(centerLocs.get(propertyNum).getHeight()+ propertyHeight/4) );
               label4.setBounds( (int)d.getWidth(), (int)d.getHeight(), size.width,size.height);
           }
           else  if ( (propertyNum > 20 && propertyNum < 30)) { //top
               //x would variate by the width and y variates by height
               d =  new Dimension( (int)( centerLocs.get(propertyNum).getWidth() + propertyWidth / 16), (int)(centerLocs.get(propertyNum).getHeight() - propertyHeight/4) );
               label4.setBounds( (int)d.getWidth(), (int)d.getHeight(), size.width,size.height);
           }
           else if ((propertyNum > 10 && propertyNum < 20)) { //left
               //y would variate by the width and x variates by height
               d = new Dimension( (int)( centerLocs.get(propertyNum).getWidth() - propertyHeight / 4), (int)(centerLocs.get(propertyNum).getHeight() + propertyWidth/16) );
               label4.setBounds( (int)d.getWidth(), (int)d.getHeight(), size.width,size.height);
           }
           else if ( (propertyNum > 30 && propertyNum < 40)) { //right
               //y would variate by the width and x variates by height
               d = new Dimension( (int)( centerLocs.get(propertyNum).getWidth() + propertyHeight / 4), (int)(centerLocs.get(propertyNum).getHeight() + propertyWidth/16) );
               label4.setBounds( (int)d.getWidth(), (int)d.getHeight(), size.width,size.height);
           }
           else if (propertyNum == 0 || propertyNum == 20) { //go and free parking
               d = new Dimension( (int)( centerLocs.get(propertyNum).getWidth() + cornerSize / 4), (int)(centerLocs.get(propertyNum).getHeight()+ cornerSize/4) );
               label4.setBounds( (int)d.getWidth(), (int)d.getHeight(), size.width,size.height);
           }
            else { //jail but just visiting
                 d = new Dimension( 0, (int) (propertyWidth*9 + cornerSize*2 - size.height));
                label4.setBounds( (int)d.getWidth(), (int)d.getHeight(), size.width,size.height);
            }
       }
   }
//input to whose turn field in this method

   /**
    * Intends to rotate the image
    * @param num the player number
    */
   public void rotate(int num) {
       if (num == 1) {
           RotatedIcon r1 = new RotatedIcon(image1, RotatedIcon.Rotate.DOWN);
           label1 = new JLabel(r1);
    //    RotatedIcon r2 = new RotatedIcon(image1, RotatedIcon.Rotate.DOWN);
    //    RotatedIcon r3 = new RotatedIcon(image1, RotatedIcon.Rotate.DOWN);
    //    RotatedIcon r4 = new RotatedIcon(image1, RotatedIcon.Rotate.DOWN);
       } 
        else if (num == 2) {
           RotatedIcon r2 = new RotatedIcon(image2, RotatedIcon.Rotate.DOWN);
           label2 = new JLabel(r2);
           
       }

       else if (num == 3) {
           RotatedIcon r3 = new RotatedIcon(image3, RotatedIcon.Rotate.DOWN);
           label3 = new JLabel(r3);
       }

        //num is 4
       else if (num == 4){
           RotatedIcon r4 = new RotatedIcon(image4, RotatedIcon.Rotate.DOWN);
           label4 = new JLabel(r4);
       }

   }
   public static void main(String[] args) {
       new PlayerMovement(new BoardPanelMuyi());
   }
}
