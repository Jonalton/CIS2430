package adventure;



import java.io.FileReader;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
//import java.lang.ClassNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

/** Allows the user to play the game
 *  @author Jonalton Jude Hamilton
 */
public class Game implements java.io.Serializable{

    private static final long serialVersionUID = -8674454946672272063L;
    /* Instance Variables */
    //private static final long serialVersionUID = 
    private Boolean quit;
    private JSONObject jAdventure;
    private Adventure myAdventure;
    private ArrayList<Room> rooms;
    private Room myRoom;
    private Player myPlayer;
    private Parser parse = new Parser();


    /**
     * Constructor method initializes several objects
     */
    public Game(){
        myAdventure = new Adventure();
        rooms = new ArrayList<Room>();
        myRoom = new Room();
        myPlayer = new Player();
        quit = false;
    }

    /**
     * loads user designated file
     * @param filename
     * @return the JSON file
     */
    public JSONObject loadAdventureJson(String filename) {
        JSONObject jsonFile;
        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader(filename)) {
            jsonFile = (JSONObject) parser.parse(reader);
        } catch ( Exception e) {
            System.out.println("File not found");
            jsonFile = null;
        }
        return jsonFile;
    }

    /**
     * loads file from .jar file
     * @param inputStream
     * @return the JSON file
     */
    public JSONObject loadAdventureJson(InputStream inputStream){
        JSONObject jsonFile;
        JSONParser parser = new JSONParser();
        
        try(InputStreamReader reader = new InputStreamReader(inputStream)){
            jsonFile = (JSONObject) parser.parse(reader);
        } catch (Exception e){
            System.out.println("Default file not found");
            jsonFile = null;
        }
        return jsonFile;
    }

    /**
     * parses throught the JSON file and loads all objects
     * @param obj
     * @return the adventure of the game
     */
    public Adventure generateAdventure(JSONObject obj) {
        Adventure theAdventure = new Adventure();
        JSONObject base = (JSONObject) obj.get("adventure");
        if (base != null) {
            JSONArray roomList = (JSONArray) base.get("room");
            loadRoom(roomList,theAdventure,base);
            JSONArray itemList = (JSONArray) base.get("item");
            loadItem(itemList, theAdventure);
        }
       return theAdventure;
    }

    /**
     * loads all rooms into the adventure
     * @param roomList list of JSONArray of the rooms
     * @param theAdventure
     * @param base the json file containing the adventure
     */
    public void loadRoom(JSONArray roomList, Adventure theAdventure,JSONObject base){
        for ( Object currentRoom : roomList) {
            JSONObject room = (JSONObject) currentRoom;
            Room nextRoom = new Room();
            nextRoom = generateRoom(base, room);
            System.out.println(nextRoom.getName());
            theAdventure.setAllRooms(nextRoom);
        }
    }

    /**
     * loads all rooms into the adventure
     * @param itemList list of JSONArray of the rooms
     * @param theAdventure
     */
    public void loadItem(JSONArray itemList,Adventure theAdventure){
        for ( Object currentItem : itemList) {
            JSONObject item = (JSONObject) currentItem;
            Item nextItem = new Item();
            nextItem = generateItem(item);
            theAdventure.setAllItems(nextItem);
        }
    }


    /**
     * loads all the setters for room object
     * @param obj
     * @param room
     * @return each inidividual room
     */
    public Room generateRoom( JSONObject obj,  JSONObject room) {
        Room theRoom = new Room();

        theRoom = setRoomNames(room);
        startState(theRoom, room);
        loadRoomItem(obj,room,theRoom);
        loadRoomEntrances(room, theRoom);
        return theRoom;
    }

    /**
     * loads all items in each room
     * @param obj
     * @param room
     * @param theRoom
     */
    public void loadRoomItem(JSONObject obj,JSONObject room, Room theRoom){
        Item itemInRoom = new Item();
        JSONArray roomItem = (JSONArray) room.get("loot");
        if (roomItem != null) {
           for ( Object currentItem : roomItem) {
                JSONObject item = (JSONObject) currentItem;
                Long itemId = (Long) item.get("id");
                itemInRoom = locateItem(obj, itemId);
                theRoom.setItems(itemInRoom);
           }
        }
    }

    /**
     * loads the possible entrances/exits for each room
     * @param room
     * @param theRoom
     */
    public void loadRoomEntrances(JSONObject room, Room theRoom){
        JSONArray roomEntrances = (JSONArray) room.get("entrance");
        for ( Object currEntrance : roomEntrances) {
            JSONObject entrance = (JSONObject) currEntrance;
            Long entranceId = (Long) entrance.get("id");
            String direction = (String) entrance.get("dir");
            theRoom.setConnectedRoom(entranceId, direction);
        }
    }

    /**
     * checks to see which room is the starting room
     * @param theRoom
     * @param room
     */
    public void startState(Room theRoom, JSONObject room){
        String start = (String) room.get("start");
        if ((start != null) && (start.equals("true"))) {
           theRoom.setStart(true);
        }
        if (start == null) {
           theRoom.setStart(false);
        }
    }

    /**
     * Sets the name, descriptions of the room and the room id
     * @param room
     * @return object Room
     */
    public Room setRoomNames(JSONObject room){
        String name = ((String) room.get("name"));
        String longDesc = ((String) room.get("long_description"));
        String shortDesc = ((String) room.get("short_description"));
        Long id = ((Long) room.get("id"));
        Room theRoom = new Room(name,longDesc,shortDesc,id);
        return theRoom;
    }

    /**
     * 
     * @param obj
     * @param id
     * @return the item for each room
     */
    public Item locateItem( JSONObject obj,  Long id) {
        Item theItem = new Item();
        Long matching;
        JSONArray itemList = (JSONArray) obj.get("item");
        for ( Object currentItem : itemList) {
            JSONObject item = (JSONObject) currentItem;
            matching = (Long) item.get("id");
            if (matching == id) {
               theItem.setLongDescription((String) item.get("desc"));
               theItem.setName((String) item.get("name"));
               // System.out.println(theItem.getName());
            }
        }
        return theItem;
    }

    /**
     * loads all the setters for item object
     * @param item
     * @return each inidividual item
     */
    public Item generateItem( JSONObject item) {
        Item theItem = new Item();
        theItem.setLongDescription((String) item.get("desc"));
        theItem.setName((String) item.get("name"));
        theItem.setId((Long) item.get("id"));
        System.out.println(theItem.getName());
        setSub(item,theItem);
        return theItem;
    }

    public void setSub(JSONObject item,Item theItem){
        checkSmallFood(item, theItem);
        checkFood(item, theItem);
        checkClothing(item, theItem);
        checkBrandedClothing(item, theItem);
        checkSpell(item, theItem);
        checkWeapon(item, theItem);
    }

    public void checkSmallFood(JSONObject item,Item temp){
        if(item.get("edible") == null || item.get("tossable") == null){
            temp.setSmallFood(false);
        } else if ((Boolean) item.get("edible") && (Boolean) item.get("tossable")){
            temp.setSmallFood(true);
        } else{
            temp.setSmallFood(false);
        }
    }

    public void checkFood(JSONObject item,Item temp){
        if(item.get("edible") == null){
            temp.setFood(false);
        } else if ((Boolean) item.get("edible")){
            temp.setFood(true);
        } else{
            temp.setFood(false);
        }
    }

    public void checkClothing(JSONObject item,Item temp){
        if(item.get("wearable") == null){
            temp.setClothing(false);
        } else if ((Boolean) item.get("wearable")){
            temp.setClothing(true);
        } else{
            temp.setClothing(false);
        }
    }

    public void checkBrandedClothing(JSONObject item,Item temp){
        if(item.get("wearable") == null || item.get("readable") == null){
            temp.setBrandedClothing(false);
        } else if ((Boolean) item.get("wearable") && (Boolean) item.get("readable")){
            temp.setBrandedClothing(true);
        } else{
            temp.setBrandedClothing(false);
        }
    }

    public void checkWeapon(JSONObject item,Item temp){
        if(item.get("tossable") == null){
            temp.setWeapon(false);
        } else if ((Boolean) item.get("tossable")){
            temp.setWeapon(true);
        } else{
            temp.setWeapon(false);
        }
    }

    public void checkSpell(JSONObject item,Item temp){
        if(item.get("readable") == null){
            temp.setSmallFood(false);
        } else if ((Boolean) item.get("readable")){
            temp.setSpell(true);
        } else{
            temp.setSpell(false);
        }
    }

    /**
     * Prints room name, short description and item names if it exists
     * @param room
     */
    public void printRoomItem(Room room) {
        ArrayList<Item> items = new ArrayList<Item>();
        System.out.println("************************************");
        System.out.println("You are at " + room.getName());
        System.out.println(room.getShortDescription());
        items = room.listItems();
        if (items.size() > 0) {
            for (Item item: items) {
                System.out.println("Items: " + item.getName());
            }
        }
        System.out.println("************************************\n");
    }


    /**
     * prints the description of the item in the room
     * @param room
     * @param itemName
     */
    public void printItems(Room room,String itemName) {
        String description;
        String temp;
        ArrayList<Item> itemList = new ArrayList<Item>();
        itemList = room.listItems();
        for ( Item item : itemList) {
            temp = item.getName();
            if (temp.equals(itemName)) {
                description = item.getLongDescription();
                System.out.println(description);
            }
        }
    }
    /**
     * @param input
     * @throws InvalidCommandException
     * @return command the user inputted
     */
    public Command parseInput(String input)throws InvalidCommandException{
        return parse.parseUserCommand(input);
    }

    /**
     * ends the game
     */
    public void endGame(){
        quit = true;
    }

    /**
     * decides which command the user entered and performs action
     * @param theCommand
     * @param theGame
     */
    public void followCommands(Command theCommand,Game theGame){
        if(theCommand.getActionWord().equals("go")){
            theGame.myRoom = theGame.myPlayer.movePlayer(theCommand);
        } else if(theCommand.getActionWord().equals("look")){
            System.out.println(theGame.myRoom.getLongDescription());
        } else if(theCommand.getActionWord().contains("look")){
            theGame.printItems(theGame.myRoom, theCommand.getNoun());
        } else if(theCommand.getActionWord().equals("take")){
            theGame.myPlayer.setInventory(theGame.myRoom.takeItem(theCommand));
        } else if(theCommand.getActionWord().equals("inventory")){
            theGame.printInventory();
        } else if(theCommand.getActionWord().equals("quit")){
            theGame.endGame();
        } else if(theCommand.getActionWord().equals("eat")){
            checkEdible(theCommand);
        } else if(theCommand.getActionWord().equals("read")){
            checkReadable(theCommand);
        } else if(theCommand.getActionWord().equals("wear")){
            checkWearable(theCommand);
        } else if(theCommand.getActionWord().equals("toss")){
            checkTossable(theCommand);
        }
    }

    public void checkEdible(Command theCommand){
        String noun = theCommand.getNoun();
        Item theItem = checkItemExists(noun);
        if(theItem != null){
            if (theItem.getFood()){
                Food foodItem = new Food(theItem);
                myPlayer.removeInventory(theItem);
                System.out.println(foodItem.eat());
            } else{
                System.out.println("Item not edible");
            }
        }
    }

    public void checkWearable(Command theCommand){
        String noun = theCommand.getNoun();
        Item theItem = checkItemExists(noun);
        if(theItem != null){
            if (theItem.getClothing()){
                Clothing clothingItem = new Clothing(theItem);
                System.out.println(clothingItem.wear());
            } else{
                System.out.println("Item not wearable");
            }
        }
    }
    public void checkTossable(Command theCommand){
        String noun = theCommand.getNoun();
        Item theItem = checkItemExists(noun);
        checkTossableTwo(theItem);
    }

    public void checkTossableTwo(Item theItem){
        if(theItem != null){
            if(theItem.getSmallFood()){
                tossSmallFood(theItem);
            } else if (theItem.getWeapon()){
                tossWeapon(theItem);
            } else{
                System.out.println("Item not tossable");
            }
        }
    }
    public void tossSmallFood(Item theItem){
        SmallFood smallFoodItem = new SmallFood(theItem);
        myPlayer.removeInventory(theItem);
        myRoom.setItems(theItem);
        System.out.println(smallFoodItem.toss());
    }
    public void tossWeapon(Item theItem){
        Weapon weaponItem = new Weapon(theItem);
        myPlayer.removeInventory(theItem);
        myRoom.setItems(theItem);
        System.out.println(weaponItem.toss());
    }


    public void checkReadable(Command theCommand){
        String noun = theCommand.getNoun();
        Item theItem = checkItemExists(noun);
        checkReadableTwo(theItem);
    }

    public void checkReadableTwo(Item theItem){
        if(theItem != null){
            if (theItem.getBrandedClothing()){
                BrandedClothing brandedItem = new BrandedClothing(theItem);
                System.out.println(brandedItem.read());
            } else if (theItem.getSpell()){
                Spell spellItem = new Spell(theItem);
                System.out.println(spellItem.read());
            } else{
                System.out.println("Item not readable");
            }
        }
    }

    public Item checkItemExists(String itemName){

        for(Item item: myAdventure.listAllItems()){
            if (item.getName().equals(itemName)){
                return item;
            }
        }
        return null;
    }

    /**
     * prints out user's inventory
     */
    public void printInventory(){
        System.out.println("************************************");
        System.out.println("Inventory:\n");
        for (Item item: myPlayer.getInventory()){
            System.out.println(item.getName());
        }
        System.out.println("************************************\n");
    }

    /**
     * Saves the current game state
     * @param filename
     * @param theGame
     */
    public void save(String filename,Game theGame){
        try{
            FileOutputStream outPutStream = new FileOutputStream(filename);
            ObjectOutputStream outPutDest = new ObjectOutputStream(outPutStream);

            outPutDest.writeObject(theGame);

            outPutDest.close();
            outPutStream.close();

            //System.out.println("O");

        } catch(IOException ex){
            System.out.println(ex);
        }
    }

    /**
     * Loads previous game state
     * @param filename
     * @return the state of the game that was previously saved
     */
    public Game load(String filename){
        Game theGame = new Game();

        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));){
            theGame = (Game) in.readObject();
            theGame.quit = false;
            //System.out.println(theGame.myRoom.getName());
            return theGame;
        } catch(IOException ex){
            System.out.println("IOException has been caught " + ex);
        } catch(ClassNotFoundException ex){
            System.out.println("ClassNotFoundException is caught " + ex);
        }
        return theGame;
    }

    /**
     * Scans for user input
     * @return the user's input
     */
    public String scannerInput(){
        Scanner scn = new Scanner(System.in);
        return scn.nextLine();
    }

    /**
     * Sets the room the game starts in
     * @param theGame
     */
    public void setRoomState(Game theGame){
        for ( Room room: theGame.rooms){
            if(room.getStart()){
                theGame.myRoom = room;
                theGame.myPlayer.setCurrentRoom(theGame.myRoom);
            }
        }
    }

    /**
     * sets the Game to be ready for the user
     * @param jAdv
     * @param theGame
     * @param filename
     */
    public void setGame(JSONObject jAdv, Game theGame,String filename){
      //  System.out.println("What is your name?");
       // theGame.myPlayer.setName(scannerInput());
       // System.out.println("\nWelcome to the University of Guelph " + theGame.myPlayer.getName() + "!!\n");
        //InputStream inputStream = Game.class.getClassLoader().getResourceAsStream(filename);
        jAdv = theGame.loadAdventureJson(filename);
        theGame.myAdventure = theGame.generateAdventure(jAdv);
        theGame.rooms = theGame.myAdventure.listAllRooms();
        setRoomState(theGame);
        theGame.myRoom.setRooms(theGame.rooms);
    }

    /**
     * Determines whether to load default adventure, user adventure or saved game
     * @param args
     * @param jAdv
     * @param theGame
     * @return the object of Game
     */
    public Game determineArg(String[] args, JSONObject jAdv,Game theGame){
        if(args.length == 0 || args.length == 1){
            setGame(jAdv, theGame, "my_adventure.json");
        } else{
            if (args[0].equals("-a")){
                setGame(jAdv, theGame, args[1]);
            } else if (args[0].equals("-l")){
                Game tempGame = new Game();
                System.out.println("load");
                theGame = tempGame.load(args[1]);
                theGame.quit = false;
            }
        }
        return theGame;
    }

    public Game loadJSONfile(String filename,Game theGame){
        JSONObject jAdventure = null; 
        jAdventure = theGame.loadAdventureJson(filename);
        theGame.myAdventure = theGame.generateAdventure(jAdventure);
        theGame.rooms = theGame.myAdventure.listAllRooms();
        setRoomState(theGame);
        theGame.myRoom.setRooms(theGame.rooms);
        return theGame;
    }

    public Game loadDefaultJSON(String filename,Game theGame){
        JSONObject jAdventure = null; 
        InputStream inputStream = Game.class.getClassLoader().getResourceAsStream(filename);
        jAdventure = theGame.loadAdventureJson(inputStream);
        theGame.myAdventure = theGame.generateAdventure(jAdventure);
        theGame.rooms = theGame.myAdventure.listAllRooms();
        setRoomState(theGame);
        theGame.myRoom.setRooms(theGame.rooms);
        return theGame;
    }

    /**
     * Determines if the user wants to save
     * @param theGame
     */
    public void determineSave(Game theGame){
        String input;
        System.out.println("Would you like to save the game? (yes or no)");
        input = theGame.scannerInput();
        if(input.equals("yes")){
            System.out.println("Enter filename");
            input = theGame.scannerInput();
            theGame.myPlayer.setSaveGameName(input);
            theGame.save(input,theGame);
        } else if (input.equals("no")){
            System.out.println("Thank you for playing");
        }
    }

    public void saveGame(Game theGame,String input){
        theGame.myPlayer.setSaveGameName(input);
        theGame.save(input,theGame);
    }

    /**
     * Prints message stating game starts
     */
    public void printWelcomeMessage(){
        System.out.println("************************************");
        System.out.println("Get ready to begin your adventure!");
        System.out.println("************************************\n");
    }

    /**
     * The main method to make the game run
     * @param theGame
     * @param nextCommand
     */
    public void runGame(Game theGame,Command nextCommand){
        String input;
        do{ 
            input = theGame.scannerInput();
            System.out.println("");
            try{
                nextCommand = theGame.parseInput(input);
            } catch(InvalidCommandException e){
                System.out.println("Command not found please try again");
                System.out.print(e);
            }
            theGame.followCommands(nextCommand,theGame);
            theGame.printRoomItem(theGame.myRoom);
            theGame.myPlayer.setCurrentRoom(theGame.myRoom);
            theGame.myRoom.setRooms(theGame.rooms);
        }while(!theGame.quit);
    }

    public Game runGameGUI(Game theGame,String input){
        Command nextCommand = null;
        try{
            nextCommand = theGame.parseInput(input);
        } catch(InvalidCommandException e){
            System.out.println("Command not found please try again");
            System.out.print(e);
        }
        theGame.followCommandsGUI(nextCommand,theGame);
        //theGame.printRoomItem(theGame.myRoom);
        theGame.myPlayer.setCurrentRoom(theGame.myRoom);
        theGame.myRoom.setRooms(theGame.rooms);
        return theGame;
    }

    /**
     * decides which command the user entered and performs action
     * @param theCommand
     * @param theGame
     */
    public void followCommandsGUI(Command theCommand,Game theGame){
        if(theCommand.getActionWord().equals("go")){
            theGame.myRoom = theGame.myPlayer.movePlayer(theCommand);
        } else if(theCommand.getActionWord().equals("look")){
            System.out.println(theGame.myRoom.getLongDescription());
        } else if(theCommand.getActionWord().contains("look")){
            theGame.printItems(theGame.myRoom, theCommand.getNoun());
        } else if(theCommand.getActionWord().equals("take")){
            theGame.myPlayer.setInventory(theGame.myRoom.takeItem(theCommand));
        } else if(theCommand.getActionWord().equals("inventory")){
            theGame.printInventory();
        } else if(theCommand.getActionWord().equals("quit")){
            theGame.endGame();
        }  else if(theCommand.getActionWord().equals("quit")){
            theGame.endGame();
        } else if(theCommand.getActionWord().equals("eat")){
            checkEdible(theCommand);
        } else if(theCommand.getActionWord().equals("read")){
            checkReadable(theCommand);
        } else if(theCommand.getActionWord().equals("wear")){
            checkWearable(theCommand);
        } else if(theCommand.getActionWord().equals("toss")){
            checkTossable(theCommand);
        }
    }


    public void changePlayerName(String name){
        myPlayer.setName(name);
    }

    public Player getPlayer(){
        return myPlayer;
    }
    /**
     * Allows the program to run and lets the user play the game
     * @param args
     */
    public static void main(String[] args){

        Command nextCommand = null;
        Game theGame = new Game();
        JSONObject jAdventure = null;     
        theGame = theGame.determineArg(args, jAdventure, theGame);
        theGame.printWelcomeMessage();
        theGame.printRoomItem(theGame.myRoom);
        theGame.runGame(theGame,nextCommand);


        theGame.determineSave(theGame);
    }
}
