package adventure;

public class Item{
    /* you will need to add some private member variables */
    Long id;
    String itemName;
    String longDescription;
    Room theRoom = new Room();
    /* required public methods */

    public String getName(){
        return itemName;
    }

    public String getLongDescription(){
        return longDescription;
    }

    public Room getContainingRoom(){
        //returns a reference to the room that contains the item
        return theRoom;
    }

    /* you may wish to add some helper methods*/
    public Long getId(){
        return id;
    }

    public void setName(String newName){
        itemName = newName;
    }

    public void setLongDescription(String newName){
        longDescription = newName;
    }

    public void setId(Long itemId){
        id = itemId;
    }
}
