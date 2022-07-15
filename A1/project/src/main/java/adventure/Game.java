package adventure;

import java.util.ArrayList;
import java.io.FileReader;
import java.io.Reader;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Game{

    /* this is the class that runs the game.
    You may need some member variables */
    

    public static void main( String args[]) {
        // TESTING METHODS
        /*
         * Adventure myAdventure = new Adventure();
         * myAdventure.setCurrentRoomDescription("roomdesc");
         * System.out.println(myAdventure.getCurrentRoomDescription());
         * 
         * Item myItem = new Item(); myItem.setName("itemname");
         * System.out.println(myItem.getName()); myItem.setLongDescription("longdesc");
         * System.out.println(myItem.getLongDescription());
         */

        /*
         * You will need to instantiate an object of type game as we're going to avoid
         * using static methods for this assignment
         */
        JSONObject jAdventure;
        Adventure myAdventure = new Adventure();
        ArrayList<Room> rooms = new ArrayList<Room>();
        int value;
        Room myRoom = new Room();
        String filename = null;
        String input;
        Game theGame = new Game();

        // 1. Print a welcome message to the user

        System.out.println("Welcome to the game");

        // 2. Ask the user if they want to load a json file.
        filename = theGame.decideLoad();
        /*
         * if (filename != null){ System.out.println(filename); }
         */

        /*
         * 3. Parse the file the user specified to create the adventure, or load your
         * default adventure
         */

        jAdventure = theGame.loadAdventureJson(filename);
        myAdventure = theGame.generateAdventure(jAdventure);
        /*
         * if (jAdventure != null){ System.out.println(jAdventure.toString() + "\n\n");
         * }
         */
        rooms = myAdventure.listAllRooms();

        for ( Room room : rooms) {
            if (room.getStart()) {
                myRoom = room;
            }
        }
        myRoom.setRooms(rooms);
        // 4. Print the beginning of the adventure

        System.out.println("Get ready to begin your adventure!");
        Scanner scn = new Scanner(System.in);
        theGame.printRoomItem(myRoom);
        // 5. Begin game loop here

        do {
            // 6. Get the user input. You'll need a Scanner
            input = scn.nextLine();
            value = theGame.parseUserInput(input);
            switch (value) {
                case 1:
                    myRoom = myRoom.getConnectedRoom(input);
                    theGame.printRoomItem(myRoom);
                    break;
                case 2:
                    System.out.println(myRoom.getLongDescription());
                    break;
                case 3:
                    String tmp = input.replaceFirst("look ", "");
                    theGame.printItems(myRoom, tmp);
                    break;
                default:
                    System.out.println("Enter a known command");
                    break;
            }
            //myRoom = newRoom;
            myRoom.setRooms(rooms);
        } while (!input.equals("quit"));

        /*
         * 7+. Use a game instance method to parse the user input to learn what the user
         * wishes to do
         */

        // use a game instance method to execute the users wishes*/

        /*
         * if the user doesn't wish to quit, repeat the steps above
         */
    }

    public int parseUserInput(String input) {
        if ((input.equals("go N")) || (input.equals("go W")) || (input.equals("go S")) || (input.equals("go E"))) {
            return 1;
        } else if (input.equals("look")) {
            return 2;
        } else if (input.contains("look")) {
            return 3;
        }
        return -1;
    }

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

    public void printRoomItem(Room room) {
        ArrayList<Item> items = new ArrayList<Item>();
        System.out.println(room.getName());
        System.out.println(room.getShortDescription());
        items = room.listItems();
        if (items.size() > 0) {
            for (Item item: items) {
                System.out.println(item.getName());
            }
        }
    }

    /*
     * public Room chooseNewRoom(Room myRoom){
     * 
     * }
     */
    /* you must have these instance methods and may need more */

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

    public Adventure generateAdventure(JSONObject obj) {
         Adventure theAdventure = new Adventure();
        // Room nextRoom = new Room();

        JSONObject base = (JSONObject) obj.get("adventure");
        if (base != null) {
             JSONArray roomList = (JSONArray) base.get("room");
            // nextRoom = generateRoom(base);
            // theAdventure.setAllRooms(nextRoom);

            // nextItem = generateItem(base);
            // theAdventure.setAllItems(nextItem);
            for ( Object currentRoom : roomList) {
                 JSONObject room = (JSONObject) currentRoom;
                Room nextRoom = new Room();
                nextRoom = generateRoom(base, room);
                theAdventure.setAllRooms(nextRoom);

            }

             JSONArray itemList = (JSONArray) base.get("item");
            for ( Object currentItem : itemList) {
                 JSONObject item = (JSONObject) currentItem;
                Item nextItem = new Item();
                nextItem = generateItem(item);
                theAdventure.setAllItems(nextItem);
            }

        }
        return theAdventure;
    }

    public Room generateRoom( JSONObject obj,  JSONObject room) {
         Room theRoom = new Room();
        Item itemInRoom = new Item();
        // get the list of rooms from the base which is obj
        // JSONArray roomList = (JSONArray) obj.get("room");
        // iterate through each room the the list of rooms
        // for(Object currentRoom: roomList){
        // JSONObject room = (JSONObject) currentRoom;
        theRoom.setName((String) room.get("name"));
        theRoom.setLongDescription((String) room.get("long_description"));
        theRoom.setShortDescription((String) room.get("short_description"));
        theRoom.setId((Long) room.get("id"));
         String start = (String) room.get("start");
        if ((start != null) && (start.equals("true"))) {
            theRoom.setStart(true);
        }
        if (start == null) {
            theRoom.setStart(false);
        }
        // System.out.println(theRoom.getLongDescription());
         JSONArray roomItem = (JSONArray) room.get("loot");
        if (roomItem != null) {
            for ( Object currentItem : roomItem) {
                 JSONObject item = (JSONObject) currentItem;
                 Long itemId = (Long) item.get("id");
                itemInRoom = locateItem(obj, itemId);
                // System.out.println((Long) item.get("id"));
                theRoom.setItems(itemInRoom);
            }
        }

         JSONArray roomEntrances = (JSONArray) room.get("entrance");
        for ( Object currEntrance : roomEntrances) {
             JSONObject entrance = (JSONObject) currEntrance;
             Long entranceId = (Long) entrance.get("id");
             String direction = (String) entrance.get("dir");
            theRoom.setConnectedRoom(entranceId, direction);
        }
        // }
        return theRoom;
    }

    // this method is for finding the item in each room
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

    public Item generateItem( JSONObject item) {
         Item theItem = new Item();
        // JSONArray itemList = (JSONArray) obj.get("item");
        // for(Object currentItem: itemList){
        // item = (JSONObject) currentItem;
        theItem.setLongDescription((String) item.get("desc"));
        theItem.setName((String) item.get("name"));
        theItem.setId((Long) item.get("id"));
        // System.out.println(theItem.getLongDescription() + " " + theItem.getName());
        // }
        return theItem;
    }

    public String decideLoad() {
        int loop = 0;
        String answer;
        String filename = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to load JSON file?");
        while(loop == 0){
            answer = scanner.nextLine();
            answer = answer.toLowerCase();
            //System.out.println(answer);
            if (answer.equals("yes")){
                System.out.println("Enter file");
                filename = scanner.nextLine();
                loop = 1;
            }
            else if (answer.equals("no")){
                filename = "src/main/resources/my_adventure.json";
                loop = 1;
            }
            else{
                System.out.println("Enter yes or no");
            }
        }
        return filename;
    }

}
