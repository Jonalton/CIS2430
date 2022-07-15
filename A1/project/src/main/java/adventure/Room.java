package adventure;
import java.util.ArrayList;
public class Room{
    /* you will need to add some private member variables */
    Adventure theAdventure = new Adventure();
    ArrayList<Item> items = new ArrayList<Item>();
    ArrayList<Room> theRooms = new ArrayList<Room>();
   // ArrayList<Room> connectingRooms = new ArrayList<Room>();
    Boolean state;
    Long N,W,S,E;
    Long id;
   // Room newRoom = new Room();
    String roomName;
    String longDescription;
    String shortDescription;

    /*public Room(){
        theRooms = theAdventure.listAllRooms();
    }*/
    /* required public methods */
    
    public ArrayList<Item> listItems(){
        //lists all the items in the room
        return items;
    }
    
    public String getName(){
        return roomName;
    }

    public String getLongDescription(){
        return longDescription;
    }

    public Room getConnectedRoom(String direction) {
        int loop = 1;
        while (loop == 1){
            if (direction.equals("go N")){
                //System.out.println("hello");
                for(Room room: theRooms){
                    //System.out.println(N + " " + room.getId());
                    if (room.getId() == N){
                        return room;
                    }
                    else {
                        loop = 0;
                    }
                }
            }
            else if (direction.equals("go W")){
                for(Room room: theRooms){
                    if (room.getId() == W){
                        return room;
                    }
                    else {
                        loop = 0;
                    }
                }
            }
            else if (direction.equals("go S")){
            //System.out.println("goes to south");
                for(Room room: theRooms){
                    //System.out.println(S + " " + room.getId());
                    if (room.getId() == S){
                        return room;
                    }
                    else{
                        loop = 0;
                    }
                }
                
            }
            else if (direction.equals("go E")){
                for(Room room: theRooms){
                    if (room.getId() == E){
                        return room;
                    }
                    else{
                        loop = 0;
                    }
                }
            }
        }
        System.out.println("Enter another direction");
        for(Room room: theRooms){
            if(room.getId() ==this.getId()){
                return room;
            }
        }
        return theRooms.get(0);
    }

    /* you may wish to add some helper methods*/

    public void setConnectedRoom(Long connectingId,String direction){
        if (direction.equals("N")){
            N = connectingId;
        }
        if (direction.equals("W")){
            W = connectingId;
        }
        if (direction.equals("S")){
            S = connectingId;
        }
        if (direction.equals("E")){
            E = connectingId;
        }


    }

    public void setRooms(ArrayList<Room> rooms){
        theRooms = rooms;
    }

    public Boolean getStart(){
        return state;
    }

    public String getShortDescription(){
        return shortDescription;
    }

    public Long getId(){
        return id;
    }
    
    public void setId(Long roomId){
        id = roomId;
    }

    public void setName(String newName){
        roomName = newName;
    }

    public void setLongDescription(String newDescription){
        longDescription = newDescription;
    }

    public void setShortDescription(String newDescription){
        shortDescription = newDescription;
    }

    public void setItems(Item newItem){
        items.add(newItem);
    }

    public void setStart(Boolean start){
       // System.out.println("set true"); 
        state = start;
    }
    

}
