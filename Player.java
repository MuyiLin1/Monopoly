import java.util.ArrayList;
/**
 * Player stores the information of each person. This includes the amount of money one has, 
 * all the properties that were bought, whether or not you are jailed, the number of jail free 
 * cards you have, your location on the board, and whether or not you are bankrupt. 
 *
 * @author Tiffany, Muyi, Anil
 * @version 19 May 2022
 * @author Period: 5
 * 
 * @author Assignment: APCS_Final_Project
 * 
 * @author Sources: None
 */
public class Player {
    
    private int money;
    private ArrayList<Card> properties;
    private boolean isJailed;
    private int getOutFree;
    private String name;
    private boolean isBankrupt;

    private int currentPosition;
    private int numRoundsInJail;

    private int[] numOfEachGroup = new int[10];
    /**
     * indices          corresponding color
     * 0                        Purple
     * 1                        Light Blue
     * 2                        Pink
     * 3                        Orange
     * 4                        Red
     * 5                        Yellow
     * 6                        Green
     * 7                        Dark Blue
     * 8                        RRCard
     * 9                        UtilCard
     */
    
    /**
     * Intends to intialize the player object
     */
    public Player()
    {
        // Ask user for name
        numRoundsInJail = 0;
        money = 200;
        properties = new ArrayList<Card>();
        isJailed = false;
        getOutFree = 0;
        isBankrupt = false;
        currentPosition = 0;

    }

    /**
     * Intends to get the card that the player
     * landed on
     * @param currentPosition the index on the gameboard
     * @return the name of the current card
     */
    public String getCurrentCard(int currentPosition) {
        if (currentPosition == 0) {
            return "Go";
        }
        else if (currentPosition == 10) {
            return "Visiting Jail";
        }
        else if (currentPosition == 20) {
            return "Free Parking";
        }
        else if (currentPosition == 30) {
            return "In Jail";
        }

        for (int i = 0; i < ListOfCards.listOfCards.size(); i++) {
            if (ListOfCards.listOfCards.get(i).getIndexOfPropertyCard() == currentPosition) {
                return ListOfCards.listOfCards.get(i).getName();
            }
        }

        return "";

    }

    /**
     * Intends to check if the player
     * has a certain card
     * @param card the specific card
     * @return true if the player does, 
     * false otherwise
     */
    public boolean hasProperty(Card card) {
        for (int i = 0; i < properties.size(); i++) {
            if (properties.get(i).getName().equals(card.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Intends to get the number of utility cards
     * within the properties that the player bought
     * @return the number of utility card
     */
    public int numOfUtilCard() {
        int count = 0;
        for (int i = 0; i < properties.size(); i++) {
            if(properties.get(i) instanceof UtilCard)
            {
                count ++;
            }
        }
        return count;
    }

    /**
     * Intends to get the number of railroad
     * cards within the properties that the player
     * bought
     * @return the number of railroad card
     */
    public int numOfRRCard() {
        int count = 0;
        for (int i = 0; i < properties.size(); i++) {
            if(properties.get(i) instanceof RRCard)
            {
                count ++;
            }
        }
        return count;
    }
    
    /**
     * Intends to get the number of rounds the player
     * had been in jail for
     * @return the number of rounds in the jail
     */
    public int getNumRoundsInJail() {
        return this.numRoundsInJail;
    }

    /**
     * Intends to increment the number of rounds
     * the person had been in jail by a certain amount
     * @param i a number
     */
    public void addNumRoundsInJail(int i) {
        numRoundsInJail += i;
    }

    /**
     * Intends to set the number of rounds
     * in jail to be zero. This is uses when the 
     * player had just gotten out of jail.
     */
    public void resetNumRoundsInJail() {
        numRoundsInJail = 0;
    }

    /**
     * Intends to get the curent index position
     * on the gameboard
     * @return the current position
     */
    public int getCurrentPosition()
    {
        return this.currentPosition;
    }

    /**
     * Intends to set the player to another 
     * location on the board
     * @param p the new index/position on the board
     */
    public void setCurrentPosition(int p) {
        this.currentPosition = p;
    }

    /**
     * Intends to add money to the player
     * @param num the amount being added
     */
    public void addMoney(int num)
    {
        this.money += num;
    }

    /**
     * Intends to subtract money from the player
     * @param num the amount being subtracted
     */
    public void subtractMoney(int num)
    {
        this.money -= num;
    }

    /**
     * Intends to add a card to the player's
     * property list
     * @param pc the card
     */
    public void addProperty(Card pc)
    {
        properties.add(pc);
    }

    /**
     * Intends to remove a card from
     * the player's property list
     * @param pc the card
     */
    public void removeProperty(Card pc)
    {
        properties.remove(pc);
    }
    
    /**
     * Intends to check whether or not
     * the card has been bought by the owner
     * @param c the specific card
     * @return true if the player already bought it,
     * false otherwise
     */
    public boolean ownsProperty(Card c)
    {
        return properties.contains(c);
    }

    /**
     * Intends to set the boolean of whether
     * or not the player is jailed to true
     */
    public void toJail()
    {
        this.isJailed = true;
    }

    /**
     * Intends to get the amount of money
     * @return the amount of money
     */
    public int getMoney()
    {
        return this.money;
    }

    /**
     * Intends to check if the player
     * is jailed
     * @return true if the player is jailed,
     * false otherwise
     */
    public boolean isJailed()
    {
        return this.isJailed;
    }

    /**
     * Intends to set jailed to either true 
     * if the person is jailed, or false if the 
     * player has gone out of jail.
     * @param b the boolean
     */
    public void setJailed(boolean b) {
        isJailed = b;
    }

    /**
     * Intend to get the amount of jail
     * free cards the player has
     * @return the amount of jail free cards
     */
    public int getGoF()
    {
        return this.getOutFree;
    }

    /**
     * Intends to increment the amount
     * of the jail free cards by 1
     */
    public void incrementGoF() {
        this.getOutFree++;
    }

    /**
     * Intends to decrement the amount
     * of jail free cards by 1
     */
    public void subtractGoF() {
        this.getOutFree--;
    }

    /**
     * Intends to get the Player's name
     * @return the player's name
     */
    public String getName()
    {
        return this.name;
    }

    /**
    * Intends to set the player's name
    * @param name the name of the player
    */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Intends to get the list of properties 
     * that the Player bought
     * @return the list of properties
     */
    public ArrayList<Card> getProperties() {
        return properties;
    }

    /**
     * Intend to set the bankruptcy of the player
     * @param bankrupt the boolean of whether
     * or not the player is bankrupt
     */
    public void setBankrupt(boolean bankrupt) {
        isBankrupt = bankrupt;
    }

    /**
     * Intends to check if the player is 
     * bankrupt
     * @return true if the player is bankrupt,
     * false otherwise
     */
    public boolean isBankrupt() {
        return isBankrupt;
    }

    /**
     * Intends to increment of a specific group/color
     * of the given card
     * @param card the card
     */
    public void incrementGroup(Card card) {
        int index = card.getGroupIndex();
        numOfEachGroup[index] += 1;
    }

    /**
     * Intends to get all the groups/colors 
     * @return an integer array of the groups
     */
    public int[] getGroups() {
        return this.numOfEachGroup;
    }

    /**
     * Intends to remove a card from the group/color
     * that corresponds to the given card. This 
     * is normally called when the card is mortaged
     * @param c the specific card
     */
    public void removeCardFromGroup(Card c) {
        this.numOfEachGroup[c.getGroupIndex()] -= 1;
    }

}