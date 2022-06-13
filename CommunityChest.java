import java.util.LinkedList;
import java.util.Queue;

/**
 * Community Chest contains a queue with all of the possible community chest cards. 
 * Whenever the getMessage method is called, we remove the top card in the queue and
 * move it to the back of the queue and return the card text.
 *
 * @author Tiffany, Muyi, Anil
 * @version 19 May 2022
 * @author Period: 5
 * 
 * @author Assignment: APCS_Final_Project
 * 
 * @author Sources: https://www.monopolyland.com/list-monopoly-chance-community-chest-cards/ 
 */

public class CommunityChest {
    private Queue<String> messages;
    private String msg = "";

    /**
     * Intends to intialize a Community Chest Object
     */
    public CommunityChest() {
        messages = new LinkedList<String>();
        messages.add( "Advance to Go. Collect $200.");
        messages.add( "Bank error in your favor. Collect $200.");
        messages.add( "Doctor's fee. Pay $50.");
        messages.add( "From sale of stock you get $50.");
        messages.add( "Go to Jail. Go directly to jail, do not pass Go, do not collect $200.");
        messages.add( "Holiday fund matures. Recieve $100.");
        messages.add( "Income tax refund. Collect $20.");
        messages.add( "Pay hospital fees of $100.");
        messages.add( "Get Out of Jail Free.");
        messages.add("You inherit $100.");

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