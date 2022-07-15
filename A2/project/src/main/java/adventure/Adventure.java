package adventure;
import java.util.ArrayList;

public class Adventure{
    /* you will need to add some private member variables */
    String currentRoomDescription;
    ArrayList<Room> rooms = new ArrayList<Room>();
    ArrayList<Item> items = new ArrayList<Item>();
    /* ======== Required public methods ========== */
        /* note,  you don't have to USE all of these
        methods but you do have to provide them.
        We will be using them to test your code */

    public ArrayList<Room> listAllRooms(){
        return rooms;
    }

    public ArrayList<Item> listAllItems(){
        return items;
    }

    public String getCurrentRoomDescription(){
        return currentRoomDescription;
    }

    /* you may wish to add additional methods*/

    public void setCurrentRoomDescription(String newName){
        currentRoomDescription = newName;
    }

    public void setAllRooms(Room newRoom){
        rooms.add(newRoom);
    }

    public void setAllItems(Item newItem){
        items.add(newItem);
        //System.out.println("added");
    }
}
