//Creates Board
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.util.ArrayList;
import java.awt.Dimension;
import java.awt.Font;

/**
 * Game Mode stores and runs the program. 
 * This class begins with creating a GUI window that asks for the number of participants 
 * and assigns each player to a specific game piece. 
 * It plays the game by having each player roll a dice and moving
 * a certain number of spaces on the game board asking the player 
 * whether or not they would like to buy the property or how much they need 
 * to pay to the owner. When the player does not have enough money, 
 * this class creates another GUI window that allows the player to mortgage their properties.
 * When one player goes bankrupt, the game ends by creating a GUI window that displays the name of the winner.
 * 
 *
 * @author Tiffany, Muyi, Anil
 * @version 19 May 2022
 * @author Period: 5
 * 
 * @author Assignment: APCS_Final_Project
 * 
 * @author Sources: yes no pop-up button: https://www.youtube.com/watch?v=ffVY6HGtxH8&t=518s&ab_channel=KnowledgetoShare 
 *                  lag: https://stackoverflow.com/questions/19416590/how-to-create-lag-in-java 
 */
public class GameMode implements ActionListener{
 
   private static JFrame gameFrame;
   private static JPanel gamePanel;
   private static ArrayList<JRadioButton> mortgageRadioButtons;
 
   private static JButton diceButton;
   private static JLabel diceLabel;
   public static ArrayList<Player> players = new ArrayList<Player>();
   private static JPanel panel;
   private static JFrame frame;
   private static JLabel playerLabel;
   private static JTextField playersText;
   private static JButton enterButton;
   private static JLabel unsuccessful;
   private static boolean finishedSettingUp;
   private static BoardPanelMuyi bp = new BoardPanelMuyi();
   private static PlayerMovement p = new PlayerMovement(bp);

   /**
    * Intends to create a field that stores the rolled number
    * from the dice
    */
   public static int rolledNum;
   
   /**
    * Intends to create a field that stores the number of players
    * that are participating
    */
   public static int numPlayers;
 
   public static JFrame playerPlatformFrame;
   private static JLabel moneyLabel;
   private static JLabel propertiesLabel;
   private static JLabel jailFreeLabel;
   private static JLabel isJailed;
   private static JLabel currentPosition;
   private static JLabel playerNameLabel;
 
   private static boolean dicePerformed = false;
   private static boolean playersPerformed = false;
   private static boolean actuallyRolled = false;
   private static boolean enterMortgageButtonClicked = false;
 
   private static CommunityChest communityChest = new CommunityChest();
   private static Chance chance = new Chance();
   private static ListOfCards list = new ListOfCards();
 
   /**
    * Intends to get the information of each player and
    * displays the it on a GUI.
    * This includes: 
    * - The player's name
    * - The amount of money the player has
    * - A list of all the properties bought
    * - The number of jail free cards
    * - Whether or not the player is in jail
    * - The current Card that the player is on
    * - The dice and the number that was rolled
    * 
    * @param player the player
    */
   public static void gameInfo(Player player) {
 
       // Player Info
       playerNameLabel = new JLabel();
       playerNameLabel.setBounds(10, 20, 200, 25);
       playerNameLabel.setText("Turn: " + player.getName());
 
       moneyLabel = new JLabel();
       moneyLabel.setBounds(10, 50, 200, 25);
       moneyLabel.setText("Money: $" + player.getMoney());
 
       propertiesLabel = new JLabel();
       propertiesLabel.setBounds(10, 80, 700, 25);
       String property = "Properties:\n";
       for (int i = 0; i < player.getProperties().size(); i++) {
           property += player.getProperties().get(i).getName() + ", \n";
       }
       propertiesLabel.setText(property);
 
       jailFreeLabel = new JLabel("Number of Jail Free Cards: " + player.getGoF());
       jailFreeLabel.setBounds(10, 110, 200, 25);
 
       isJailed = new JLabel("Is Jailed: " + player.isJailed());
       isJailed.setBounds(10, 140, 200, 25);
 
       currentPosition = new JLabel("Current Card: " + player.getCurrentCard(player.getCurrentPosition()));
       currentPosition.setBounds(10, 170, 300, 25);
 
 
       // Dice
       dicePerformed = true;
       diceButton = new JButton("Click to Roll");
       diceButton.setEnabled(false);
 
       diceButton.setBounds(250, 220, 200, 50);
       diceButton.addActionListener(new GameMode());
 
       // rolledNum = ((int) (Math.random() * 6 + 1)) + ((int)(Math.random() * 6 + 1));
       diceLabel = new JLabel();
       diceLabel.setBounds(300, 260, 100, 50);
      
 
       gamePanel = new JPanel();
       gamePanel.setLayout(null);
       gamePanel.setSize(700, 400);
       gamePanel.add(moneyLabel);
       gamePanel.add(propertiesLabel);
       gamePanel.add(jailFreeLabel);
       gamePanel.add(isJailed);
       gamePanel.add(playerNameLabel);
       gamePanel.add(diceButton);
       gamePanel.add(currentPosition);
       gamePanel.add(diceLabel);
      
 
 
       gameFrame = new JFrame("Monopoly Game Info");
       gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       gameFrame.setSize(700, 400);
       gameFrame.add(gamePanel);
       
       int x = (int) BoardPanelMuyi.dimensionOfBoard;
       gameFrame.setLocation(x, 0);

       gameFrame.setVisible(true);
 
   }
 
   /**
    * Intends to take care of mortgage and displays
    * all the properties and mortgage price of each so that
    * the player can choose which one they'd like to mortgage.
    *
    * @param player the player
    * @return true if the mortgage was successful, 
    * false otherwise
    */
   public static boolean takeCareOfMortgage(Player player) {
 
       mortgageRadioButtons = new ArrayList<JRadioButton>();
 
       if (player.getProperties().size() == 0) {
           return false;
       }
 
       ButtonGroup buttonGroup = new ButtonGroup();
 
       JPanel mortgagePanel = new JPanel();
       mortgagePanel.setLayout(null);
       mortgagePanel.setSize(400, 550);
 
       JFrame mortgageFrame = new JFrame("Mortgage");
       mortgageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       mortgageFrame.setSize(400, 550);
       mortgageFrame.add(mortgagePanel);

       int x = (int) BoardPanelMuyi.dimensionOfBoard;
       int y = (int) gameFrame.getHeight() + 23;
       mortgageFrame.setLocation(x, y);
       mortgageFrame.setVisible(true);
 
       for (int i = 0; i < player.getProperties().size(); i++) {
           JRadioButton opt1 = new JRadioButton(player.getProperties().get(i).getName() + " ---- Mortgage Amt: $" + player.getProperties().get(i).getMortgage());
           mortgagePanel.add(opt1);
           buttonGroup.add(opt1);
           opt1.setBounds(30, 64 + (40 * i), 400, 30);
           mortgageRadioButtons.add(opt1);
       }
 
       JButton enterMortgageButton = new JButton("Enter");
       mortgagePanel.add(enterMortgageButton);
       enterMortgageButton.setBounds(30, 64 + (40 * mortgageRadioButtons.size()), 109, 50);
       enterMortgageButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
 
               for (int i = 0; i < mortgageRadioButtons.size(); i++) {
                   if (mortgageRadioButtons.get(i).isSelected()) {
                       System.out.println( mortgageRadioButtons.get(i).getText() + " WAS SELECTED");
                       Card mortgagedCard = player.getProperties().get(i);
                       player.getProperties().remove(mortgagedCard);
                       mortgagedCard.setOwner(null);
                       player.removeCardFromGroup(mortgagedCard);

                       System.out.println("org amt" + player.getMoney());
                      
                       if (mortgagedCard instanceof PropertyCard) {
                           System.out.println("property card");
                           player.addMoney(mortgagedCard.getMortgage() + ((PropertyCard)mortgagedCard).getHouseNum() * ((PropertyCard)mortgagedCard).getHouseCost() + ((PropertyCard)mortgagedCard).getHotelNum() * ((PropertyCard)mortgagedCard).getHotelCost());
                           System.out.println("$" + player.getMoney());
                           ((PropertyCard)mortgagedCard).reset();
                       }
                       else if (mortgagedCard instanceof RRCard) {
                           System.out.println("rrc");
                           player.addMoney(mortgagedCard.getMortgage());
                           System.out.println("$" + player.getMoney());
                       }
                       else if (mortgagedCard instanceof UtilCard) {
                           System.out.println("utilCard");
                           player.addMoney(mortgagedCard.getMortgage());
                           System.out.println("$" + player.getMoney());
                       }
 
                       enterMortgageButtonClicked = true;
                       enterMortgageButton.setEnabled(false);
                       mortgageFrame.dispose();
 
                       System.out.println(mortgagedCard.getOwner());
                   }
               }
           }
       });
 
 
       while (enterMortgageButtonClicked == false) {
           System.out.println();
       }
 
       System.out.println("GOT STUCK IN MORTGAGE METHOD");
       return true;
   }
 
   /**
    * Intends to roll the dice.
    *
    * @return the number that was rolled
    */
   public static int rollDice() {
       diceButton.setEnabled(true);
       dicePerformed = true;
 
       rolledNum = ((int) (Math.random() * 6 + 1)) + ((int)(Math.random() * 6 + 1));
 
       while (actuallyRolled == false) {
           System.out.println();
       }
 
       return rolledNum;
   }
 
   /**
    * Intends to ask fro the number of players that are
    * participating by displaying a GUI with the
    * neccessary information. 
    * 
    * @return the number of players
    */
   public static int setUpPlayers() {
       frame = new JFrame();
       playersPerformed = true;
       // Ask how many players would like to play
       playerLabel = new JLabel("Number of Players");
       playerLabel.setBounds(10, 20, 200, 25);
 
       playersText = new JTextField(20);
       playersText.setBounds(150, 20, 165, 25);
 
       enterButton = new JButton("Enter");
       enterButton.setBounds(320, 20, 80, 25);
       enterButton.addActionListener(new GameMode());
 
       unsuccessful = new JLabel("");
       unsuccessful.setBounds(100, 50, 300, 25);
       unsuccessful.setForeground(Color.RED);
 
       panel = new JPanel();
       panel.setLayout(null);
       panel.add(playerLabel);
       panel.add(playersText);
       panel.add(enterButton);
       panel.add(unsuccessful);
 
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setSize(430, 100);
       frame.add(panel);

       int x = (int) BoardPanelMuyi.dimensionOfBoard;
       frame.setLocation(x, 0);
       frame.setVisible(true);
 
       while (finishedSettingUp == false) {
           System.out.println();
       }
 
 
       System.out.println("finsihed the method");
       return numPlayers;
   }
 
   /**
    * Intends to create a pop up message
    * that asks a player a questions. The
    * player will need to click the yes or no 
    * button. 
    *
    * @param s the question
    * @return yes if the player clicked the yes button,
    * no, if the player closed the pop up or clicked the no button
    * and neither if the player did not do anything (this will not happen)
    */
   public static String askPlayerQuestion(String s) {
 
       int response = JOptionPane.showConfirmDialog(gamePanel, s, "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
      
       if (response == JOptionPane.YES_OPTION) {
           return "yes";
       }
       else if (response == JOptionPane.NO_OPTION || response == JOptionPane.CLOSED_OPTION) {
           return "no";
       }
       return "neither";
 
   }
 
   /**
    * Intends to create a pop up confirm message.
    * 
    * @param s the message
    */
   public static void showConfirmMessage(String s) {
       System.out.println("came into confirm message");
       JOptionPane.showConfirmDialog(gamePanel, s, "Confirm", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
   }
 
   /**
    * Intends to display a community chest pop up
    * and doing whatever the community chest says.
    *
    * @param player the player
    */
   public static void displayCommunityChest(Player player) {
       String msg = communityChest.getMessage();
 
       if (msg.equals("Advance to Go. Collect $200.")) {
           showConfirmMessage("COMMUNITY CHEST\nAdvance to Go. Collect $200.");
           player.setCurrentPosition(0);
           p.newPosition(players.indexOf(player) + 1, player.getCurrentPosition());
           player.addMoney(200);
       }
       else if (msg.equals("Bank error in your favor. Collect $200.")) {
           showConfirmMessage("COMMUNITY CHEST\nBank error in your favor. Collect $200.");
           player.addMoney(200);
       }
       else if (msg.equals("Doctor's fee. Pay $50.")) {
           showConfirmMessage("COMMUNITY CHEST\nDoctor's fee. Pay $50.");
           player.subtractMoney(50);
       }
       else if (msg.equals("From sale of stock you get $50.")) {
           showConfirmMessage("COMMUNITY CHEST\nFrom sale of stock you get $50.");
           player.addMoney(50);
       }
       else if (msg.equals("Go to Jail. Go directly to jail, do not pass Go, do not collect $200.")) {
           showConfirmMessage("COMMUNITY CHEST\nGo to Jail. Go directly to jail, do not pass Go, do not collect $200.");
          
           if (player.getGoF() > 1) {
               int response = JOptionPane.showConfirmDialog(gamePanel, "Do you want to use a Jail Free Card?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
               if (response == JOptionPane.YES_OPTION) {
                   player.subtractGoF();
                   return;
               }
           }
           
           player.setCurrentPosition(10);
           p.newPosition(players.indexOf(player) + 1, player.getCurrentPosition());
           player.addNumRoundsInJail(1);
       }
       else if (msg.equals("Holiday fund matures. Recieve $100.")) {
           showConfirmMessage("COMMUNITY CHEST\nHoliday fund matures. Recieve $100.");
           player.addMoney(100);
       }
       else if (msg.equals("Income tax refund. Collect $20.")) {
           showConfirmMessage("COMMUNITY CHEST\nIncome tax refund. Collect $20.");
           player.addMoney(20);
       }
       else if (msg.equals("Pay hospital fees of $100.")) {
           showConfirmMessage("COMMUNITY CHEST\nPay hospital fees of $100.");
           player.subtractMoney(100);
       }
       else if (msg.equals("Get Out of Jail Free.")) {
           showConfirmMessage("COMMUNITY CHEST\nGet Out of Jail Free.");
           player.incrementGoF();
       }
       else if (msg.equals("You inherit $100.")) {
           showConfirmMessage("COMMUNITY CHEST\nIncome tax refund. Collect $20.");
           player.addMoney(100);
       }
   }
 
   /**
    * Intends to display the pop up Chance card
    * and do whatever the card says
    * @param player the player
    */
   public static void displayChance(Player player){
       String msg = chance.getMessage();
       if(msg.equals("Advance to Boardwalk."))
       {
           showConfirmMessage("CHANCE\nAdvance to Boardwalk.");
           player.setCurrentPosition(39);
           p.newPosition(players.indexOf(player) + 1, player.getCurrentPosition());
       }
       else if(msg.equals("Advance to Illinois Avenue. If you pass 'Go' Collect $200."))
       {
           showConfirmMessage("CHANCE\nAdvance to Illinois Avenue. If you pass 'Go' Collect $200.");
           if(player.getCurrentPosition() <= 24)
           {
               player.setCurrentPosition(24);
               p.newPosition(players.indexOf(player) + 1, player.getCurrentPosition());
           }
           else
           {
               player.addMoney(200);
               player.setCurrentPosition(24);
               p.newPosition(players.indexOf(player) + 1, player.getCurrentPosition());
           }
       }
       else if(msg.equals("Advance to the nearest Railroad. If unowned, you may buy it from the Bank. If owned, pay wonder twice the rental to which they are otherwise entitled.")) {
           showConfirmMessage("CHANCE\nAdvance to the nearest Railroad. If unowned, you may buy it from the Bank. If owned, pay wonder twice the rental to which they are otherwise entitled.");
           int cp = player.getCurrentPosition();
           while(cp != 5 && cp != 15 && cp != 25 && cp != 35)
           {
               cp++;
               player.setCurrentPosition(cp);
           }
           p.newPosition(players.indexOf(player) + 1, player.getCurrentPosition());

           if(list.getCard(cp).getOwner() != null)
           {
               player.subtractMoney(list.getCard(cp).getOwner().numOfRRCard() * 25 * 2);
           }
           else
           {
               askPlayerQuestion("Would you like to buy" + list.getCard(cp).getName());
           }
       }
       else if(msg.equals("Get Out of Jail Free."))
       {
           showConfirmMessage("CHANCE\nGet Out of Jail Free.");
           player.incrementGoF();
       }
       else if(msg.equals("Go to Jail. Go directly to Jail, do not pass Go, do not collect $200."))
       {
           showConfirmMessage("CHANCE\nGo to Jail. Go directly to Jail, do not pass Go, do not collect $200.");
           player.setCurrentPosition(10);
           p.newPosition(players.indexOf(player) + 1, player.getCurrentPosition());
           player.setJailed(true);
       }
       else if(msg.equals("Advance to St. Charles Place. If you pass 'Go' Collect $200."))
       {
           showConfirmMessage("CHANCE\nAdvance to St. Charles Place. If you pass 'Go' Collect $200.");
           if(player.getCurrentPosition() <= 11)
           {
               player.setCurrentPosition(11);
               p.newPosition(players.indexOf(player) + 1, player.getCurrentPosition());
           }
           else
           {
               player.addMoney(200);
               player.setCurrentPosition(11);
               p.newPosition(players.indexOf(player) + 1, player.getCurrentPosition());
           }
       }
       else if(msg.equals("Advance token to nearest Utility. If unowned, you may buy it from the Bank. If owned, throw dice and pay owner a total ten times amount thrown.")) {
           showConfirmMessage("CHANCE\nAdvance token to nearest Utility. If unowned, you may buy it from the Bank. If owned, throw dice and pay owner a total ten times amount thrown.");
           int cp = player.getCurrentPosition();
           while(cp != 12 && cp != 28)
           {
               cp++;
               player.setCurrentPosition(cp);
           }
           p.newPosition(players.indexOf(player) + 1, player.getCurrentPosition());

           if(list.getCard(cp).getOwned())
           {
               int num = list.getCard(cp).getOwner().numOfUtilCard();
               int roll;

               if(num == 1)
               {
                   roll = rollDice();
                   player.subtractMoney(roll * 4 * 10);
                   list.getCard(cp).getOwner().addMoney(roll * 4 * 10);
               }
               else
               {
                   roll = rollDice();
                   player.subtractMoney(roll * 10 * 10);
                   list.getCard(cp).getOwner().addMoney(roll * 10 * 10);
               }
           }
           else
           {
               askPlayerQuestion("Would you like to buy " + list.getCard(cp).getName());
           }
       }
       else if (msg.equals("Take a trip to Reading Railroad. If you pass Go, collect $200."))
       {
           showConfirmMessage("CHANCE\nTake a trip to Reading Railroad. If you pass Go, collect $200.");
           if(player.getCurrentPosition() <= 5)
           {
               player.setCurrentPosition(5);
               p.newPosition(players.indexOf(player) + 1, player.getCurrentPosition());
           }
           else
           {
               player.addMoney(200);
               player.setCurrentPosition(5);
               p.newPosition(players.indexOf(player) + 1, player.getCurrentPosition());
           }
       }
       else if (msg.equals("Your building loan matures. Collect $150."))
       {
           showConfirmMessage("CHANCE\nYour building loan matures. Collect $150.");
           player.addMoney(150);
       }
       else if(msg.equals("Advance to Go. Collect $200."))
       {
           showConfirmMessage("CHANCE\nAdvance to Go. Collect $200.");
           player.setCurrentPosition(0);
           p.newPosition(players.indexOf(player) + 1, player.getCurrentPosition());
           player.addMoney(200);
       }
   }
  
  
   /**
    * In general, this method does a specific action
    * when a button is clicked
    * 
    * if the dice was rolled, the GUI would display
    * the number that was rolled and would enable the dice button
    * 
    * Intends to ask how many players would like to play
    * and if the number is not in between 2 and 5 inclusive, then
    * the GUI will display a message that would ask the player to enter a 
    * valid number. If a number was entered successfully,
    * the method will create the player objects.
    * 
    * @param e an action event of some sort
    */
    @Override
   public void actionPerformed(ActionEvent e)
   {
       // TODO Auto-generated method stub
 
       if (dicePerformed == true) {
           diceLabel.setText("You rolled a " + rolledNum);
           diceButton.setEnabled(false);
           dicePerformed = false;
           actuallyRolled = true;
       }
       else if (playersPerformed == true){
           String strPlayers = playersText.getText();
      
           try {
               numPlayers = Integer.parseInt(strPlayers);
  
               if (numPlayers < 2 || numPlayers > 4) {
                   unsuccessful.setText("Please enter a number betweem 2-4");
               }
               else {
                   frame.dispose();
  
                   System.out.print("num Players = " + numPlayers);
                //    p.getNumPlayers(players.size());

                   //create the different players
                   for (int i = 0; i < numPlayers; i++) {
                       players.add(new Player());
                   }
  
                   playersPerformed = false;
                   finishedSettingUp = true;
                   System.out.println(finishedSettingUp);
                   System.out.println("size of players list: " + players.size());

                //    p = new PlayerMovement(bp);
  
               }
           }
           catch (NumberFormatException ex) {
               unsuccessful.setText("Please enter a number betweem 2-4");
           }
       }
     
   }

   /**
    * Intends to get the winner of the game.
    * This method only runs if one person goes bankrup.
    * This method will count the money, property, houses, and castles
    * of each player and return the player with the most money.
    *
    * @return the winner
    */
   public static Player getWinner() {
 
       int greatestAmtOfMoney = -1;
       Player playerWiththeMostMoney = null;
 
       for (int i = 0; i < players.size(); i++) {
           if (players.get(i).isBankrupt()) {
               continue;
           }
           int moneyValue = players.get(i).getMoney();
          
           // Loop through property cards to get money
           for (int k = 0; k < players.get(i).getProperties().size(); k++) {
               Card card = players.get(i).getProperties().get(k);
               if (card instanceof PropertyCard) {
                   moneyValue += card.getMortgage() + ((PropertyCard)card).getHouseNum() * ((PropertyCard)card).getHouseCost() + ((PropertyCard)card).getHotelNum() * ((PropertyCard)card).getHotelNum();
               }
               else if (card instanceof RRCard || card instanceof UtilCard) {
                   moneyValue += card.getMortgage();
               }
           }
 
           if (moneyValue > greatestAmtOfMoney) {
               greatestAmtOfMoney = moneyValue;
               playerWiththeMostMoney = players.get(i);
           }
       }

       // Winner GUI

       JLabel winnerPlayer = new JLabel();
       winnerPlayer.setText(playerWiththeMostMoney.getName().toUpperCase() + " WINS!");
       winnerPlayer.setHorizontalAlignment(SwingConstants.CENTER);
       winnerPlayer.setFont(new Font( "Serif", Font.BOLD, 60));

      
       JFrame winnerFrame = new JFrame("Winner");       
       winnerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       winnerFrame.setSize(600, 500);
       winnerFrame.setLocation((int)(BoardPanelMuyi.screenSize.getWidth() / 2)-300,(int)(BoardPanelMuyi.screenSize.getHeight() / 2)-250);
       winnerFrame.add(winnerPlayer,JLabel.CENTER);
       winnerFrame.setVisible(true);
       
       return playerWiththeMostMoney;
   }
 
   /**
    * Intends to check if the player has enough money.
    * This is normally called when the player wants to purchase 
    * a card.
    * 
    * @param p the player
    * @param rent the cost of the rent
    * @return true if the player has enough money,
    * false otherwise
    */
   public static boolean hasEnoughMoney(Player p, int rent)
   {
       return (p.getMoney() >= rent);
   }
 
   /**
    * Intends to check if the owner of the card
    * bought all the groups/colors. 
    * 
    * @param p the owner of the card
    * @param card the card
    * @return true, if the player has to pay the owner double the rent
    * false otherwise
    */
   public static boolean payDoubleRent(Player p, Card card) {
       int index = card.getGroupIndex();
       int[] groups = p.getGroups();
 
       for (int i = 0; i < groups.length; i++) {
           if (i == index) {
               if (i == 0 || i == 7) {
                   if (groups[i] == 2) {
                       return true;
                   }
               }
               else if (i < 9) {
                   if (groups[i] == 4) {
                       return true;
                   }
               }
           }
       }
 
       return false;
   }
 
   /**
    * Intends to play the game by rolling the dice,
    * moving the player to a specific location on the board,
    * chcekc to see if the property is owned. If owned,
    * then pay the owner the neccesary amount and if the player 
    * does not have enough money, take care of the mortage. If the 
    * property is not owned, as if the player would like to buy it.
    * Keep going through each player until one person goes bankrupt. 
    * 
    * @return The winner
    */
   public static Player playGame() {

       System.out.println("GOT INTO PLAYGAME()");
       int whoseTurn = 0;

        // p = new PlayerMovement(bp);

       while (true) {
 
           gameInfo(players.get(whoseTurn));
 
           Player player = players.get(whoseTurn);
 
           // get out of jail
           if (player.isJailed()) {
               player.addNumRoundsInJail(1);
               if (player.getNumRoundsInJail() == 3) {
                   player.setJailed(false);
                   gameFrame.dispose();
                   gameInfo(players.get(whoseTurn));
               }
               else {
                   showConfirmMessage("You are in Jail.");
                   whoseTurn = (whoseTurn + 1) % players.size();
                   gameFrame.dispose();
                   dicePerformed = false;
                   actuallyRolled = false;
                   continue;
               }
           }
 
           System.out.printf("It's %s's turn!!\n", player.getName());
 
           rolledNum = rollDice();
 
           //lag
           try{
               Thread.sleep(2000);
           }
           catch(Exception e) {
 
           }
 
           System.out.printf("You rolled a %d\n", rolledNum);
           int playerPrevPositon = player.getCurrentPosition();          
           player.setCurrentPosition((player.getCurrentPosition() + rolledNum) % 40);

           // Player movement
            int currentLocationOnGameBoard = player.getCurrentPosition();
            if (currentLocationOnGameBoard != 30) {
                p.newPosition(whoseTurn + 1, currentLocationOnGameBoard);
            }
            System.out.println("property number " + currentLocationOnGameBoard);

            
            //


            
            // checking if it moves around a corner


            // for (int i = 0; i < 40; i=i+10) {
            //     if (playerPrevPositon < i && currentLocationOnGameBoard> i) {
            //         p.rotate(whoseTurn + 1);
            //         break;
                    
            //     }
            // }
            

        //    Dimension d = p.newPosition(players.indexOf(player), player.getCurrentPosition() + rolledNum);
        //    if(players.indexOf(player) == 0)
        //    {
        //        p.label1.setBounds( (int)d.getWidth(), (int)d.getHeight(), p.size.width,p.size.height);
        //    }
        //    else if(players.indexOf(player) == 1)
        //    {
        //        p.label2.setBounds( (int)d.getWidth(), (int)d.getHeight(), p.size.width,p.size.height);
        //    }
        //    else if(players.indexOf(player) == 2)
        //    {
        //        p.label3.setBounds( (int)d.getWidth(), (int)d.getHeight(), p.size.width,p.size.height);
        //    }
        //    else{
        //        p.label4.setBounds( (int)d.getWidth(), (int)d.getHeight(), p.size.width,p.size.height);
        //    }



           // CHECK CURRENT POSITION
           if (currentLocationOnGameBoard == 0 || (playerPrevPositon + rolledNum >= 40)) { // GO
               showConfirmMessage("You passed Go. Collect $200.");
               player.addMoney(200);
           }
          
           if (currentLocationOnGameBoard == 4) { // INCOME TAX
               showConfirmMessage("You landed on Income Tax. Pay 10% or $200.");
               if (player.getMoney() < 200) {
                   if (player.getMoney() == 0 && player.getProperties().size() == 0) {
                       player.setBankrupt(true);
                   }
                   else {
                       player.subtractMoney(player.getMoney() / 10);
                   }
               }
               else {
                   player.subtractMoney(200);
               }
           }
           else if (currentLocationOnGameBoard == 38) {// LUXURY TAX
               showConfirmMessage("You landed on Luxury Tax. Pay $75.");
               if (player.getMoney() < 75 && player.getProperties().size() == 0) {
                    player.setBankrupt(true);
                }
                else {
                    player.subtractMoney(75);
                }
               
           }
           else if (currentLocationOnGameBoard == 10) { // JAIL
               if(player.isJailed())
               {
                JOptionPane.showConfirmDialog(gamePanel, "You are in Jail.", "Confirm", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                   
                   player.addNumRoundsInJail(1);
                   // continue; // skip turn
               }
               showConfirmMessage("You are just visiting.");
           }
           else if (currentLocationOnGameBoard == 20) { // FREE PARKING
               showConfirmMessage("You landed on Free Parking.");
               // continue;
           }
           else if (currentLocationOnGameBoard == 30) { // GO TO JAIL
               JOptionPane.showConfirmDialog(gamePanel, "Go directly to Jail. Do not collect $200.", "Confirm", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
              
               if (player.getGoF() > 1) {
                   int response = JOptionPane.showConfirmDialog(gamePanel, "Do you want to use a Jail Free Card?", "Confirm", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                   if (response == JOptionPane.YES_OPTION) {
                       player.subtractGoF();
                       whoseTurn = (whoseTurn + 1) % players.size();
                       gameFrame.dispose();
                       dicePerformed = false;
                       actuallyRolled = false;
                       continue;
                   }
               }

               player.setCurrentPosition(10);
               player.setJailed(true);
               p.newPosition(whoseTurn + 1, 10, player.isJailed());
               player.addNumRoundsInJail(1);
               // continue;
           }
           else if (currentLocationOnGameBoard == 2 || currentLocationOnGameBoard == 17 || currentLocationOnGameBoard == 33) { // COMMUNITY CHEST
               displayCommunityChest(player);
           }     
           else if (currentLocationOnGameBoard == 7 || currentLocationOnGameBoard == 22 || currentLocationOnGameBoard == 36) { // CHANCE  
               displayChance(player);
           }
           else if (currentLocationOnGameBoard != 0) { // Cards
               Card card = list.getCard(currentLocationOnGameBoard);
 
               // check if it's owned
               if (card.getOwner() != null)
               {
                   int rent;
                   if (card instanceof PropertyCard) {
                       if(player.equals(card.getOwner()))
                       {
                           if(((PropertyCard)card).getHouseNum() >= 5)
                           {
                               String s = askPlayerQuestion("Would you like to build a hotel on "+ card.getName());
                               if(s.equals("yes"))
                               {
                                   if(hasEnoughMoney(player, ((PropertyCard)card).getHotelCost()))
                                   {
                                       ((PropertyCard)card).addHotel();
                                       player.subtractMoney(((PropertyCard)card).getHotelCost());
                                   }
                                   else
                                   {
                                       if (!hasEnoughMoney(player, card.getPrice())) {
                                           int response = JOptionPane.showConfirmDialog(gamePanel, "Sorry, you do you not have enough money.\nWould you like to mortage your properties?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                                           if (response == JOptionPane.YES_OPTION) {
                                               while (!hasEnoughMoney(player, card.getPrice())) {
                                                   if (takeCareOfMortgage(player) == false) { // means no more property to mortgage
                                                       JOptionPane.showConfirmDialog(gamePanel, "Sorry, you do you not have enough money.\nTRAMNSACTION FAILED", "Confirm", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                                                       break;
                                                   }
                                                   System.out.println("Amt of money " + player.getMoney());
                                                   System.out.println("CAME INTO THIS LOOP AND GOT STUCK");
                                                   gameFrame.dispose();
                                                   gameInfo(players.get(whoseTurn));
                                                   enterMortgageButtonClicked = false;
                                               }
              
                                               if (hasEnoughMoney(player, card.getPrice())) {
                                                   JOptionPane.showConfirmDialog(gamePanel, "TRANSACTION SUCCESSFUL", "Confirm", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                                                   card.setOwner(player);
                                                   player.addProperty(card);
                                                   player.subtractMoney(card.getPrice());
                                               }
                                           }
                                       }
                                   }  
                               }
                           }
                           else{
                               String s = askPlayerQuestion("Would you like to build a house on "+ card.getName());
                               if(s.equals("yes"))
                               {
                                   if(hasEnoughMoney(player, ((PropertyCard)card).getHouseCost()))
                                   {
                                       ((PropertyCard)card).addHouse();
                                       player.subtractMoney(((PropertyCard)card).getHouseCost());
                                   }
                                   else
                                   {
                                       if (!hasEnoughMoney(player, card.getPrice())) {
                                           int response = JOptionPane.showConfirmDialog(gamePanel, "Sorry, you do you not have enough money.\nWould you like to mortage your properties?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                                           if (response == JOptionPane.YES_OPTION) {
                                               while (!hasEnoughMoney(player, card.getPrice())) {
                                                   if (takeCareOfMortgage(player) == false) { // means no more property to mortgage
                                                       JOptionPane.showConfirmDialog(gamePanel, "Sorry, you do you not have enough money.\nTRANSACTION FAILED", "Confirm", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                                                       break;
                                                   }
                                                   System.out.println("AMt of money " + player.getMoney());
                                                   System.out.println("CAME INTO THIS LOOP AND GOT STUCK");
                                                   gameFrame.dispose();
                                                   gameInfo(players.get(whoseTurn));
                                                   enterMortgageButtonClicked = false;
                                               }
              
                                               if (hasEnoughMoney(player, card.getPrice())) {
                                                   JOptionPane.showConfirmDialog(gamePanel, "TRANSACTION SUCCESSFUL", "Confirm", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                                                   card.setOwner(player);
                                                   player.addProperty(card);
                                                   player.subtractMoney(card.getPrice());
                                               }
                                           }
                                       }
                                   } 
                               }
                           }
                       }
 
                       if (payDoubleRent(player, card)) {
                           rent = ((PropertyCard)card).getRent() * 2;
                           JOptionPane.showConfirmDialog(gamePanel, "Rent is Doubled.\n Owner bought a whole set of this property.", "Confirm", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                       }
                       else {
                           rent = ((PropertyCard)card).getRent();
                       }
 
                       while(!hasEnoughMoney(player, rent))
                       {
                           if (takeCareOfMortgage(player) == false) {
                               player.setBankrupt(true);
                               break;
                           }
                       }

                       // Only subtract money to player if they have enough money
                       if (!player.isBankrupt()) {
                           showConfirmMessage("Pay " + card.getOwner().getName() + " $" + (rent));
                           card.getOwner().addMoney(rent);
                           player.subtractMoney(rent);
                       }
                   }
                   else if(card instanceof UtilCard)
                   {
                       rent =  rolledNum * 4;
                       while(!hasEnoughMoney(player, rent))
                       {
                           if (takeCareOfMortgage(player) == false) {
                               player.setBankrupt(true);
                               break;
                           }
                       }
                       // count number of
                       if (card.getOwner().getGroups()[9] == 1) {
                           showConfirmMessage("Pay " + card.getOwner().getName() + " $" + rent);
                           card.getOwner().addMoney(rent);
                           player.subtractMoney(rent);
                       }
                       else
                       {
                           rent =  rolledNum * 10;
                           while(!hasEnoughMoney(player, rent))
                           {
                               if (takeCareOfMortgage(player) == false) {
                                   player.setBankrupt(true);
                                   break;
                               }
                           }

                           // Only subtract money to player if they have enough money
                           if (!player.isBankrupt()) {
                               showConfirmMessage("Pay " + card.getOwner().getName() + " $" + rent);
                               card.getOwner().addMoney(rent);
                               player.subtractMoney(rent);
                           }
                       }
                   }
                   else if (card instanceof RRCard) {
                       if (card.getOwner().getGroups()[8] == 1) {
                           rent = 25;
                           while(!hasEnoughMoney(player, rent))
                           {
                               if (takeCareOfMortgage(player) == false) {
                                   player.setBankrupt(true);
                                   break;
                               }
                           }

                           // Only subtract money to player if they have enough money
                           if (!player.isBankrupt()) {
                               showConfirmMessage("Pay " + card.getOwner().getName() + " $" + rent);
                               card.getOwner().addMoney(25);
                               player.subtractMoney(25);
                           }
                       }
                       else if (card.getOwner().getGroups()[8] == 2) {
                           rent = 50;
                           while(!hasEnoughMoney(player, rent))
                           {
                               if (takeCareOfMortgage(player) == false) {
                                   player.setBankrupt(true);
                                   break;
                               }
                           }

                           // Only subtract money to player if they have enough money
                           if (!player.isBankrupt()) {
                               showConfirmMessage("Pay " + card.getOwner().getName() + " $" + rent);
                               card.getOwner().addMoney(rent);
                               player.subtractMoney(rent);
                           }
                       }
                       else if (card.getOwner().getGroups()[8] == 3) {
                           rent = 100;
                           while(!hasEnoughMoney(player, rent))
                           {
                               if (takeCareOfMortgage(player) == false) {
                                   player.setBankrupt(true);
                                   break;
                               }
                           }

                           // Only subtract money to player if they have enough money
                           if (!player.isBankrupt()) {
                               showConfirmMessage("Pay " + card.getOwner().getName() + " $" + rent);
                               card.getOwner().addMoney(rent);
                               player.subtractMoney(rent);
                           }

                       }
                       else {
                           rent = 200;
                           while(!hasEnoughMoney(player, rent))
                           {
                               if (takeCareOfMortgage(player) == false) {
                                   player.setBankrupt(true);
                                   break;
                               }
                           }

                           // Only subtract money to player if they have enough money
                           if (!player.isBankrupt()) {
                               showConfirmMessage("Pay " + card.getOwner().getName() + " $" + rent);
                               card.getOwner().addMoney(rent);
                               player.subtractMoney(rent);
                           }
                       }
                   }
               }
               else {
                   // ASK IF PLAYER WANTS TO BUY IT (WINDOW POP UP)
                   String answer = askPlayerQuestion("Would you like to buy " + card.getName() + "?");
                   if (answer.equals("yes")) {
                      
                       // CHECK IF PLAYER HAS ENOUGH MONEY
                       if (!hasEnoughMoney(player, card.getPrice())) {
                           int response = JOptionPane.showConfirmDialog(gamePanel, "Sorry, you do you not have money.\nWould you like to mortage your properties?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                           if (response == JOptionPane.YES_OPTION) {
                               while (!hasEnoughMoney(player, card.getPrice())) {
                                   if (takeCareOfMortgage(player) == false) { // means no more property to mortgage
                                       JOptionPane.showConfirmDialog(gamePanel, "Sorry, you do you not have enough money.\n TRANSACTION FAILED", "Confirm", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                                       break;
                                   }
                                   System.out.println("AMT of money " + player.getMoney());
                                   System.out.println("CAME INTO THIS LOOP AND GOT STUCK");
                                   gameFrame.dispose();
                                   gameInfo(players.get(whoseTurn));
                                   enterMortgageButtonClicked = false;
                               }
 
                               if (hasEnoughMoney(player, card.getPrice())) {
                                   JOptionPane.showConfirmDialog(gamePanel, "TRANSACTION SUCCESSFUL", "Confirm", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                                   card.setOwner(player);
                                   player.addProperty(card);
                                   player.subtractMoney(card.getPrice());
                                   player.incrementGroup(card);
                               }
                           }
                       }
                       else {
                           card.setOwner(player);
                           player.addProperty(card);
                           player.subtractMoney(card.getPrice());
                           player.incrementGroup(card);
                       }
 
                   }

                   // increment the player's group
                   player.incrementGroup(card);
                    
               }
           }

           // check bankruptcy
           if (player.isBankrupt()) {
               gameFrame.dispose();
               System.out.println("Winner is... " + getWinner().getName());
               return getWinner();
            }

           whoseTurn = (whoseTurn + 1) % players.size();
           gameFrame.dispose();
           dicePerformed = false;
           actuallyRolled = false;
       }
   }
 
 
   public static void main(String[] args) {       
 
    //    new BoardPanelMuyi();
       int num = setUpPlayers();

       for (int i = 0; i < players.size(); i++) {
           if (i == 0) {
               players.get(i).setName("Ship");
           }
           else if (i == 1) {
               players.get(i).setName("Dog");
           }
           else if (i == 3) {
               players.get(i).setName("Car");
           }
           else {
               players.get(i).setName("Shoe");
           }
       }
       System.out.println("Go");
       
       
        
       System.out.println("MOVED ON");
       playGame();
    //    System.out.println(playGame().getName());
 
   }  
}
 
 
 
