import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * List Of Cards creates an ArrayList of all property cards on the board not including Go, Go To Jail, Jail,
 *  or Free Parking and initializes all of the card values.
 *
 * @author Tiffany, Muyi, Anil
 * @version 19 May 2022
 * @author Period: 5
 * 
 * @author Assignment: APCS_Final_Project
 * 
 * @author Sources: https://monopoly.fandom.com/wiki/List_of_Monopoly_Properties 
 */

public class ListOfCards {
    /**
     * Creates a listOfCard field that is assessible across classes 
     * within this folder
     */
    public static ArrayList<Card> listOfCards;

    /**
     * Intends to intialize the List of Cards object
     */
    public ListOfCards() {
        listOfCards = new ArrayList<Card>();

        listOfCards.add(new PropertyCard("Mediterranean Avenue", 60, 2, 50, false, 30, 1, 0));
        listOfCards.add(new PropertyCard("Baltic Avenue", 60, 4, 50, false, 30, 3, 0));
        listOfCards.add(new RRCard("Reading RailRoad", 200, 25, false, null, 100, 5, 8));
        listOfCards.add(new PropertyCard("Oriental Avenue", 100, 6, 50, false, 50, 6, 1));
        listOfCards.add(new PropertyCard("Vermont Avenue", 100, 6, 50, false, 50, 8, 1));
        listOfCards.add(new PropertyCard("Connecticut Avenue", 120, 8, 50, false, 60, 9, 1));
        listOfCards.add(new PropertyCard("St. Charles Place", 140, 10, 100, false, 70, 11, 9));
        listOfCards.add(new UtilCard("Electric Company", 150, false, null, 75, 12, 2));
        listOfCards.add(new PropertyCard("States Avenue", 140, 10, 100, false, 70, 13, 2));
        listOfCards.add(new PropertyCard("Virginia Avenue", 160, 12, 100, false, 80, 14, 2));
        listOfCards.add(new RRCard("Pennsylvania RailRoad", 200, 25, false, null, 100, 15, 8));
        listOfCards.add(new PropertyCard("St. James Place", 180, 14, 100, false, 90, 16, 3));
        listOfCards.add(new PropertyCard("Tennessee Avenue", 180, 14, 100, false, 90, 18, 3));
        listOfCards.add(new PropertyCard("New York Avenue", 200, 16, 100, false, 100, 19, 3));
        listOfCards.add(new PropertyCard("Kentucky Avenue", 220, 18, 150, false, 110, 21, 4));
        listOfCards.add(new PropertyCard("Indiana Avenue", 220, 18, 150, false, 110, 23, 4));
        listOfCards.add(new PropertyCard("Illinois Avenue", 240, 20, 150, false, 120, 24, 4));
        listOfCards.add(new RRCard("B&Q RailRoad", 200, 25, false, null, 100, 25, 8));
        listOfCards.add(new PropertyCard("Atlantic Avenue", 260, 22, 150, false, 130, 26, 5));
        listOfCards.add(new PropertyCard("Ventnor Avenue", 260, 22, 150, false, 130, 27, 5));
        listOfCards.add(new UtilCard("Water Works", 150, false, null, 75, 28, 9));
        listOfCards.add(new PropertyCard("Marvin Gardens", 280, 24, 150, false, 140, 29, 5));
        listOfCards.add(new PropertyCard("Pacific Avenue", 300, 26, 200, false, 150, 31, 6));
        listOfCards.add(new PropertyCard("North Carolina Avenue", 300, 26, 200, false, 150, 32, 6));
        listOfCards.add(new PropertyCard("Pennsylvania Avenue", 320, 28, 200, false, 160, 34, 6));
        listOfCards.add(new RRCard("Short Line", 200, 25, false, null, 100, 35, 8));
        listOfCards.add(new PropertyCard("Park Place", 350, 35, 200, false, 175, 37, 7));
        listOfCards.add(new PropertyCard("Boardwalk", 400, 50, 200, false, 200, 39, 7));
    }

    /**
     * Intends to get the card with a given index 
     * that corresponds to the position on the 
     * gameboard
     * @param val the index on the gameboard
     * @return the Card 
     */
    public Card getCard(int val) {
        
        for (int i = 0; i < listOfCards.size(); i++) {
            if (listOfCards.get(i).getIndexOfPropertyCard() == val) {
                return listOfCards.get(i);
            }
        }

        throw new NoSuchElementException();
    }
}
