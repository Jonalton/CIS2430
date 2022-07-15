package adventure;

import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.Before;


public class RoomTest{
    private Room testRoom;

@Before
public void setup(){
    testRoom = new Room();

}

@Test
public void testSetNameWithValidInput(){
    System.out.println("Testing setName with valid name");
    String roomName = "one";
    testRoom.setName(roomName);
    assertTrue(testRoom.getName().equals(roomName));

}

@Test
public void testListItems(){
    System.out.println("Testing listItems with a few items");
    Item itemOne = new Item();
    Item itemTwo = new Item();
    Item itemThree = new Item();
    testRoom.setItems(itemOne);
    testRoom.setItems(itemTwo);
    testRoom.setItems(itemThree);
    int numItems;
    int exepectedItems = 3;
    numItems = testRoom.listItems().size();
    assertEquals(exepectedItems, numItems);
}

@Test
public void testLongDesc(){
    System.out.println("Testing getLongDescription");
    String desc = "This is Long Description";
    testRoom.setLongDescription(desc);
    assertTrue(testRoom.getLongDescription().equals(desc));
}

@Test
public void testGetConnectedRooms(){
    System.out.println("Testing getConnectedRooms");
    ArrayList<Room> roomList = new ArrayList<Room>();
    Long[] id = {(long) 100, (long) 103, (long) 102, (long) 101, (long) 1, (long) 2};
    Room r1 = new Room();
    Room r2 = new Room();
    Room r3 = new Room();
    Room r4 = new Room();
    Room r5 = new Room();
    Room r6 = new Room();
    r1.setId(id[0]);
    r2.setId(id[1]);
    r3.setId(id[2]);
    r4.setId(id[3]);
    r5.setId(id[4]);
    r6.setId(id[5]);
    roomList.add(r1);
    roomList.add(r2);
    roomList.add(r3);
    roomList.add(r4);
    roomList.add(r5);
    roomList.add(r6);




    testRoom.setRooms(roomList);

   


    testRoom.setConnectedRoom(id[0], "N");
    testRoom.setConnectedRoom(id[1], "S");
    testRoom.setConnectedRoom(id[2], "W");
    testRoom.setConnectedRoom(id[3], "E");
    testRoom.setConnectedRoom(id[4], "up");
    testRoom.setConnectedRoom(id[5], "down");

    Room resultRoom = new Room();
    resultRoom = testRoom.getConnectedRoom("e");
    //In this test the expected id should be 101
    assertEquals(r4.getId(),resultRoom.getId());


}



}