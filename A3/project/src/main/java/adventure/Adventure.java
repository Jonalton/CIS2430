package adventure;
import java.util.ArrayList;

/** Initializes the adventure of the user
 *  @author Jonalton Jude Hamilton
 */
public class Adventure implements java.io.Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 8851196565632997431L;
    /* you will need to add some private member variables */
    private String currentRoomDescription;
    private ArrayList<Room> rooms = new ArrayList<Room>();
    private ArrayList<Item> items = new ArrayList<Item>();

    /**
     * Constructor method initializes object rooms and items of ArrayList
     */
    public Adventure(){
        rooms = new ArrayList<Room>();
        items = new ArrayList<Item>();
    }
    /* ======== Required public methods ========== */
        /* note,  you don't have to USE all of these
        methods but you do have to provide them.
        We will be using them to test your code */

    /**
     * @return list of all the rooms in the game
     */
    public ArrayList<Room> listAllRooms(){
        return rooms;
    }

    /**
     * @return list of all the items in the game
     */
    public ArrayList<Item> listAllItems(){
        return items;
    }

    /**
     * @return current room description
     */
    public String getCurrentRoomDescription(){
        return currentRoomDescription;
    }

    /* you may wish to add additional methods*/

    /**
     * sets current room description
     * @param newName
     */
    public void setCurrentRoomDescription(String newName){
        currentRoomDescription = newName;
    }

    /**
     * adds a room to the ArrayList of rooms
     * @param newRoom
     */
    public void setAllRooms(Room newRoom){
        rooms.add(newRoom);
    }

    /**
     * adds an item to the ArrayList of items
     * @param newItem
     */
    public void setAllItems(Item newItem){
        items.add(newItem);
    }

    /**
     * prints the class
     * @return current room description
     */
    public String toString(){
        return currentRoomDescription;
    }

}
