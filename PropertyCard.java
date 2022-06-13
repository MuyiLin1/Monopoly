/**
 * PropertyCard extends Card and is a basic property card. It just has a name and price.
 * The price you pay in rent at these properties are based on the number of houses. 
 * PropertyCard also has specific prices for houses and hotels.
 *
 * @author Tiffany, Muyi, Anil
 * @version 19 May 2022
 * @author Period: 5
 * 
 * @author Assignment: APCS_Final_Project
 * 
 * @author Sources: None
 */
public class PropertyCard extends Card{
    private int rent;
    private int houseCost, hotelCost;
    private int numOfHouses;
    private int numOfHotels;
    
    /**
     * Intends to intialize the property card object
     * @param name1 the name of the property card
     * @param price1 the original cost of the card
     * @param rent1 the rent cost
     * @param housePrice the cost of a house
     * @param isOwned1 whether or not the card is owned
     * @param mortgage the mortage cost
     * @param indexOf the postion on the gameboard
     * @param groupIndex the group/color the card corresponds to
     */
    public PropertyCard(String name1, int price1, int rent1, int housePrice, boolean isOwned1, int mortgage, int indexOf, int groupIndex)
    {
        super(name1, price1, isOwned1, null, mortgage, indexOf, rent1, groupIndex);
        this.rent = rent1;
        houseCost = housePrice;
        hotelCost = housePrice * 2;
        numOfHotels = 0;
        numOfHouses = 0;
    }

    /**
     * Intends to get the mortage cost of the card
     * @return the mortgage cost
     */
    public int getMortgageCost() {
        return super.getMortgage();
    }

    /**
     * Intends to get the number of houses that was built
     * @return the number of houses
     */
    public int getHouseNum()
    {
        return numOfHouses;
    }

    /**
     * Intends to get the number of hotels that was built
     * @return the number of hotels
     */
    public int getHotelNum()
    {
        return numOfHotels;
    }

    /**
     * Intends to get the rent of the card
     * @return the rent cost
     */
    public int getRent()
    {
        int rentAmt = this.rent;
        int returnVal;
        if(numOfHouses == 0 && numOfHotels == 0)
        {
            returnVal = rentAmt;
            return returnVal;
        }
        else if(numOfHouses > 0 && numOfHotels == 0)
        {
            returnVal = rentAmt * (numOfHouses+1);
            return returnVal;
        }
        else
        {
            returnVal = rentAmt * 5;

            return returnVal;
        }
    }

    /**
     * Intends to add a house
     */
    public void addHouse()
    {
        numOfHouses ++;
    }

    /**
     * Intends to add a hotel
     */
    public void addHotel()
    {
        numOfHotels ++;
        numOfHouses = 0;
    }

    /**
     * Intends to get the cost of a house
     * @return the cost of a house
     */
    public int getHouseCost()
    {
        return houseCost;
    }

    /**
     * Intends to get the cost of hotel
     * @return the cost of a hotel
     */
    public int getHotelCost()
    {
        return hotelCost;
    }

    /**
     * Intends to reset the card. 
     * This occurs when the player decides to
     * mortgage the property.
     */
    public void reset() {
        numOfHotels = 0;
        numOfHouses = 0;
        super.setOwnerToNull();
    }
}