import java.util.LinkedList;
import java.util.Queue;

/**
 * Chance contains a queue with all of the possible chance cards. 
 * Whenever the getMessage method is called, we remove the top card in the queue 
 * and move it to the back of the queue and return the card text.
 *
 * @author Tiffany, Muyi, Anil
 * @version 19 May 2022
 * @author Period: 5
 * 
 * @author Assignment: APCS_Final_Project
 * 
 * @author Sources: https://www.monopolyland.com/list-monopoly-chance-community-chest-cards/ 
 */
public class Chance {
    private Queue<String> messages;
    private String msg = "";

    /**
     * Intends to intialize a Chance object
     */
    public Chance() {
        messages = new LinkedList<String>();
        messages.add("Advance to Boardwalk.");
        messages.add("Advance to Illinois Avenue. If you pass 'Go' Collect $200.");
        messages.add("Advance to the nearest Railroad. If unowned, you may buy it from the Bank. If owned, pay wonder twice the rental to which they are otherwise entitled.");
        messages.add("Get Out of Jail Free.");
        messages.add("Go to Jail. Go directly to Jail, do not pass Go, do not collect $200.");
        messages.add("Advance to St. Charles Place. If you pass Go, collect $200.");
        messages.add("Advance token to nearest Utility. If unowned, you may buy it from the Bank. If owned, throw dice and pay owner a total ten times amount thrown.");
        messages.add("Take a trip to Reading Railroad. If you pass Go, collect $200.");
        messages.add("Your building loan matures. Collect $150.");
        messages.add("Advance to Go. Collect $200.");
       
    }

    /**
     * Intends to get the message
     * @return the message
     */
    public String getMessage() {
        msg = messages.remove();
        messages.add(msg);
        return msg;
    }
    
}