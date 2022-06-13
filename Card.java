/**
 * A superclass of PropertyCard, RailroadCard, and UtilityCard. 
 * It possesses methods its subclasses will use such as getting 
 * and setting the price of the property and the owner. 
 *
 * @author Tiffany, Muyi, Anil
 * @version 19 May 2022
 * @author Period: 5
 * 
 * @author Assignment: APCS_Final_Project
 * 
 * @author Sources: None
 */
public class Card
{
    private String name;
    private int price;
    private boolean isOwned;
    private Player owner;
    private int mortgage;
    private int indexOfPropertyCard;
    private int rent;
    private int groupIndex;

    /**
     * Intends to intialize a Card object.
     * @param name the name of the card
     * @param price the orginal price 
     * @param isOwned a boolean of whether or not the card is owned already
     * @param owner the owner the card
     * @param mortgage the mortgage value
     * @param indexOfPropertyCard the index of the card on the game board
     * @param rent the money for rent
     * @param groupIndex the group/color that the card corresponds to
     */
    public Card(String name, int price, boolean isOwned, Player owner, int mortgage, int indexOfPropertyCard, int rent, int groupIndex)
    {
        this.name = name;
        this.price = price;
        this.isOwned = isOwned;
        this.owner = owner;
        this.mortgage = mortgage;
        this.indexOfPropertyCard = indexOfPropertyCard;
        this.rent = rent;
        this.groupIndex = groupIndex;
    }

    /**
     * Intends to est the owner to null
     */
    public void setOwnerToNull() {
        owner = null;
    }

    /**
     * Intends to get the group/Color index
     * @return the index of the group integer array
     */
    public int getGroupIndex() {
        return this.groupIndex;
    }

    /**
     * Intends to get the rent of the card
     * @return the amount of money for rent
     */
    public int getRent()
    {
        return rent;
    }

    /**
     * Intends to get a boolean of whether
     * or not a card is owned
     * @return true if card is owned, 
     * false otherwise
     */
    public boolean getOwned()
    {
        return this.isOwned;
    }

    /**
     * Intends to set the owner of the card
     * to a specific player
     * @param name the new owner
     */
    public void setOwner(Player name)
    {
        this.owner = name;
        this.isOwned = true;
    }

    /**
     * Intends to get the owner of the card
     * @return the Player
     */
    public Player getOwner()
    {
        return this.owner;
    }

    /**
     * Intend to get the name of the Card
     * @return the name 
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Intends to get the original Price
     * of the card
     * @return the price of the card
     */
    public int getPrice()
    {
        return this.price;
    }

    /**
     * Intends to get the mortage of the card
     * @return the mortgage amount
     */
    public int getMortgage()
    {
        return this.mortgage;
    }

    /**
     * Intends to get the index of the card
     * on the game board
     * @return the index
     */
    public int getIndexOfPropertyCard() {
        return this.indexOfPropertyCard;
    }
}