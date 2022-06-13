
/**
 * UtilityCard extends Card is where the price is dictated by how many UtilityCards they have.
 * Multiply your dice roll by 4 or 10 based on the number of properties and that of the amount you charge.
 *
 * @author Tiffany, Muyi, Anil
 * @version 19 May 2022
 * @author Period: 5
 * 
 * @author Assignment: APCS_Final_Project
 * 
 * @author Sources: None
 */
public class UtilCard extends Card{

    /**
     * Intends to intialize a Utility Card object
     * @param name the name of the UtilCard
     * @param price the orginal cost of the UtilCard
     * @param isOwned whether or not this property is owned
     * @param owner the owner of the UtilCard
     * @param mortgage the mortgage amount
     * @param indexOf the index on the gameboard
     * @param groupIndex the group/color the UtilCard belongs to
     */
    public UtilCard(String name, int price, boolean isOwned, Player owner, int mortgage, int indexOf, int groupIndex)
    {
        super(name, price, isOwned, null, mortgage, indexOf, 0, groupIndex);
    }

    /**
     * Intends to get the mortgage cost of the
     * Util Card
     * @return the mortgage cost
     */
    public int getMortgageCost() {
        return super.getMortgage();
    }



}