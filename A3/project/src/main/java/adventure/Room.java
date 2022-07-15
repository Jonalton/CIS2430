package adventure;
import java.util.ArrayList;
import java.util.HashMap;

/** Initializes a room in the game
 *  @author Jonalton Jude Hamilton
 */
public class Room implements java.io.Serializable{
    /**
     *
     */
    private static final long serialVersionUID = -4446949828330347028L;
    /* you will need to add some private member variables */
    private ArrayList<Item> items;
    private ArrayList<Room> theRooms;
    private Boolean state;
    private HashMap<String,Long> map; 
    private Long id;
    private String roomName;
    private String longDescription;
    private String shortDescription;


    /**
     * Constructor method initializes objects
     */
    public Room(){
        items = new ArrayList<Item>();
        map = new HashMap<>();
        theRooms = new ArrayList<Room>();
    }

    public Room(String name,String lDesc, String sDesc, Long newId){
        items = new ArrayList<Item>();
        map = new HashMap<>();
        theRooms = new ArrayList<Room>();
        roomName = name;
        longDescription = lDesc;
        shortDescription = sDesc;
        id = newId;
    }
    
    /* required public methods */
    
    /**
     * Gets the items in the room
     * @return An ArrayList representing all the items in the room
     */
    public ArrayList<Item> listItems(){
        //lists all the items in the room
        return items;
    }
    
    /**
     * Gets the room's name
     * @return A string with the room's name
     */
    public String getName(){
        return roomName;
    }

    /**
     * Gets the long description of the room
     * @return a String with the long description of the room
     */
    public String getLongDescription(){
        return longDescription;
    }

    /**
     * Gets the connected room the user wants to move to
     * @param direction
     * @return the connected room the user wants to go to
     */
    public Room getConnectedRoom(String direction) {
        int loop = 1;
        while (loop == 1){
            for (Room room: theRooms){
                if (room.getId() == map.get(direction.toUpperCase())){
                    return room;
                } else{
                    loop = 0;
                }
            }   
        }
        System.out.println("Enter another direction");
        for(Room room: theRooms){
            if(room.getId() == this.getId()){
                return room;
            }
        }
        return null;
    }

    /* you may wish to add some helper methods*/
/*
            if (direction.equals("n")){
                for (Room room: theRooms){
                    if (room.getId() == map.get("N")){
                        return room;
                    } else{
                        loop = 0;
                    }
                }
            } else if (direction.equals("w")){
                for (Room room: theRooms){
                    if (room.getId() == map.get("W")){
                        return room;
                    } else{
                        loop = 0;
                    }
                }
            } else if (direction.equals("s")){
                for (Room room: theRooms){
                    if (room.getId() == map.get("S")){
                        return room;
                    } else{
                        loop = 0;
                    }
                }
            } else if (direction.equals("e")){
                for (Room room: theRooms){
                    if (room.getId() == map.get("E")){
                        return room;
                    } else{
                        loop = 0;
                    }
                }
            } else if (direction.equals("up")){
                for (Room room: theRooms){
                    if (room.getId() == map.get("up")){
                        return room;
                    } else{
                        loop = 0;
                    }
                }
            } else if (direction.equals("down")){
                for (Room room: theRooms){
                    if (room.getId() == map.get("down")){
                        return room;
                    } else{
                        loop = 0;
                    }
                }
            } else{
                loop = 0;
            }

        }
        System.out.println("Enter another direction");
        for(Room room: theRooms){
            if(room.getId() == this.getId()){
                return room;
            }
        }
        return theRooms.get(0);*/
    /**
     * Sets up the HashMap 
     * @param connectingId
     * @param direction
     */
    public void setConnectedRoom(Long connectingId,String direction){
        if (direction.equals("N")){
            map.put(direction,connectingId);
        }
        if (direction.equals("W")){
            map.put(direction,connectingId);
        }
        if (direction.equals("S")){
            map.put(direction,connectingId);
        }
        if (direction.equals("E")){
            map.put(direction,connectingId);
        }
        if (direction.equals("down")){
            map.put(direction.toUpperCase(),connectingId);
        }
        if (direction.equals("up")){
            map.put(direction.toUpperCase(),connectingId);
        }
    }

    /**
     * Sets all the rooms in the game
     * @param rooms
     */
    public void setRooms(ArrayList<Room> rooms){
        theRooms = rooms;
    }

    /**
     * Get the starting room of the game
     * @return true if the room is where the user starts
     */
    public Boolean getStart(){
        return state;
    }

    /**
     * Get the short description of the room
     * @return A string with the short description of the room
     */
    public String getShortDescription(){
        return shortDescription;
    }

    /**
     * Get the id of the room
     * @return Long with the id of the room
     */
    public Long getId(){
        return id;
    }
    
    /**
     * Sets the id of the room
     * @param roomId
     */
    public void setId(Long roomId){
        id = roomId;
    }

    /**
     * Sets the name of the room
     * @param newName
     */
    public void setName(String newName){
        roomName = newName;
    }

    /**
     * Sets the long description of the room
     * @param newDescription
     */
    public void setLongDescription(String newDescription){
        longDescription = newDescription;
    }

    /**
     * Sets the short description of the room
     * @param newDescription
     */
    public void setShortDescription(String newDescription){
        shortDescription = newDescription;
    }

    /**
     * Sets the items in the room to an ArrayList of items
     * @param newItem
     */
    public void setItems(Item newItem){
        items.add(newItem);
    }

    /**
     * Set the start state of the room
     * @param start
     */
    public void setStart(Boolean start){
       // System.out.println("set true"); 
        state = start;
    }

    /**
     * Take the item from the room
     * @param command
     * @return the item the user has taken from the room
     */
    public Item takeItem(Command command){
        boolean itemExists = false;
        int itemNum = 0;
        for (int i = 0; i < items.size(); i++){
            if (items.get(i).getName().toLowerCase().equals(command.getNoun())){
                itemExists = true;
                itemNum = i; 
            }
        } if (itemExists){
            Item tempItem = items.get(itemNum);
            removeItem(itemNum);
            return tempItem;
        } else{
            System.out.println("Item not found");
            return items.get(itemNum);
        }
    }
    /**
     * Remove an item from the list of items
     * @param itemNum
     */
    public void removeItem(int itemNum){
        items.remove(itemNum);
    }

    /**
     * prints the class
     * @return long description and item name
     */
    public String toString(){
        return roomName+ " " +longDescription + " " + shortDescription + " " + id;
    }
}
