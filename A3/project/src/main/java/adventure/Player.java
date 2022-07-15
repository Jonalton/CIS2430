package adventure;

import java.util.ArrayList;

/** Modifies the user's character stats
 *  @author Jonalton Jude Hamilton
 */
public class Player implements java.io.Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 8475986767608325874L;
    /* Private instance variables */
    private ArrayList<Item> itemList;
    private Room currRoom;
    private String name;
    private String gameName;

    /**
     * Constructor method initializes itemList of type ArrayList
     */
    public Player(){
        itemList = new ArrayList<Item>();
    }

    /* Required methods */
    /**
     * @return the user's name
     */
    public String getName(){
        return name;
    }

    /**
     * @return the items the user has in their inventory
     */
    public ArrayList<Item> getInventory(){
        return itemList;
    }

    /**
     * @return the current room the user is in
     */
    public Room getCurrentRoom(){
        return currRoom;
    }

    /**
     * @return the name of the game save file
     */
    public String getSaveGameName(){
        return gameName;
    }


    /* Helper methods*/

    /**
     * sets user name
     * @param input
     */
    public void setName(String input){
        name = input;
    }

    /**
     * sets the user's current room
     * @param theRoom
     */
    public void setCurrentRoom(Room theRoom){
        currRoom = theRoom;
    }

    /**
     * adds items to the user's inventory
     * @param theItem
     */
    public void setInventory(Item theItem){
        itemList.add(theItem);
    }

    public void removeInventory(Item theItem){
        int counter = 0;
        int index=0;
        for(Item tempItem: itemList){
            if (tempItem.getName().equals(theItem.getName())){
                index = counter;
            }
            counter++;
        }
        itemList.remove(index);
    }

    /**
     * set the save file for game
     * @param filename
     */
    public void setSaveGameName(String filename){
        gameName = filename;
    }

    /**
     * @param command
     * @return the next room the user chose to go
     */
    public Room movePlayer(Command command){
        
        //Room nextRoom;
        //nextRoom = currRoom.getConnectedRoom(command.getNoun());
        //System.out.println("hi");
        return currRoom.getConnectedRoom(command.getNoun());
    }

    /**
     * prints the class
     * @return playername and savefile name
     */
    public String toString(){
        return name+ " " + gameName;
    }
}
