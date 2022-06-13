/**
 * RailroadCard extends Card and is a special property card where the price is dictated by
 * how many other railroad property cards they own.

 *
 * @author Tiffany, Muyi, Anil
 * @version 19 May 2022
 * @author Period: 5
 * 
 * @author Assignment: APCS_Final_Project
 * 
 * @author Sources: None
 */
public class RRCard extends Card
{
    private int rent;

    /**
     * Intends to intialize a Railroad card object
     * @param name the name of the railroad card
     * @param price the orginal cost
     * @param rent the rent cost
     * @param isOwned whether or not the railroad card is owned
     * @param owner the owner
     * @param mortgage the mortgage cost
     * @param indexOf the position on the gameboard
     * @param groupIndex the group/color that the card belongs to
     */
    public RRCard(String name, int price, int rent, boolean isOwned, Player owner, int mortgage, int indexOf, int groupIndex)
    {
        super(name, price, isOwned, null, mortgage, indexOf, rent, groupIndex);
        this.rent = rent;
    }

    /**
     * Intends to get the mortgage cost of the
     * Railroad card
     * @return the mortgage cost
     */
    public int getMortgageCost() {
        return super.getMortgage();
    }

    /**
     * Intends to return the rent of the railroad card
     * @return the rent 
     */
    public int getRent()
    {
        return rent;
    }

}