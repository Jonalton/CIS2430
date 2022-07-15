package adventure;

/** Initializes Item
 *  @author Jonalton Jude Hamilton
 */
public class Item implements java.io.Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 6738082622393174758L;
    /* you will need to add some private member variables */
    private Long id;
    private String itemName;
    private String longDescription;
    private Room theRoom;
    private Boolean smallFood;
    private Boolean food;
    private Boolean clothing;
    private Boolean weapon;
    private Boolean spell;
    private Boolean brandedClothing;

    /**
     * creates Item object and initializes room object
     */
    public Item(){
        theRoom = new Room();
    }

    public Item(Item theItem){
        
    }
    /* required public methods */


    /**
     * @return name of item
     */
    public String getName(){
        return itemName;
    }

    /**
     * @return long description of item
     */
    public String getLongDescription(){
        return longDescription;
    }

    /**
     * @return the room the Item is in
     */
    public Room getContainingRoom(){
        //returns a reference to the room that contains the item
        return theRoom;
    }

    /* you may wish to add some helper methods*/

    /**
     * @return the id of the item
     */
    public Long getId(){
        return id;
    }

    /**
     * sets Item name
     * @param newName
     */
    public void setName(String newName){
        itemName = newName;
    }

    /**
     * sets Long description
     * @param newName
     */
    public void setLongDescription(String newName){
        longDescription = newName;
    }

    /**
     * sets item Id
     * @param itemId
     */
    public void setId(Long itemId){
        id = itemId;
    }

    public void setSmallFood(Boolean state){
        smallFood = state;
    }

    public void setFood(Boolean state){
        food = state;
    }

    public void setClothing(Boolean state){
        clothing = state;
    }

    public void setWeapon(Boolean state){
        weapon = state;
    }

    public void setSpell(Boolean state){
        spell = state;
    }

    public void setBrandedClothing(Boolean state){
        brandedClothing = state;
    }

    public Boolean getSmallFood(){
        return smallFood;
    }

    public Boolean getFood(){
        return food;
    }

    public Boolean getClothing(){
        return clothing;
    }

    public Boolean getWeapon(){
        return weapon;
    }

    public Boolean getSpell(){
        return spell;
    }

    public Boolean getBrandedClothing(){
        return brandedClothing;
    }

    /**
     * prints the class
     * @return long description and item name
     */
    public String toString(){
        return longDescription + " " + itemName;
    }
}
