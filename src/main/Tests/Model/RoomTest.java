package Model;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {



    @Test
    //made public setMyDisplayIcon and constructor for this test
    /**
     * tests to see if too much of a string gets put in that the exception is triggered
     */
    void setMyDisplayIconTooBig() {
        Room roomTest = new Room();
        Boolean caughtExceptonTooBig = false;
        String tooBig = "Too Big";
        try {
            roomTest.setMyDisplayIcon(tooBig);
        } catch (IllegalArgumentException e) {
            caughtExceptonTooBig = true;
        }

        assertTrue(caughtExceptonTooBig);
    }
    @Test
    /**
     * tests to see if too little of a string gets put in that the exception is triggered
     */
    void setMyDisplayIconTooSmall() {
        Room roomTest = new Room();
        Boolean caughtExceptonTooSmall = false;
        String tooSmall = "";
        try {
            roomTest.setMyDisplayIcon(tooSmall);
        } catch (IllegalArgumentException e) {
            caughtExceptonTooSmall = true;
        }

        assertTrue(caughtExceptonTooSmall);
    }

    @Test
    /**
     * tests to see if the Icon Properly gets set
     */
    void setMyDisplayIconProper() {
        Room roomTest = new Room();
        roomTest.setMyDisplayIcon(RoomType.NORMAL.type +" ");
        String normalRoomOutput = "N ";
        assertEquals(normalRoomOutput, roomTest.toString());
    }

    @Test
    /**
     * tests to see if setting the inventory of MyRoomInventory actually gets added to the hashset
     */
    void setMyRoomInventory() {
        for (RoomType roomType:RoomType.values()) {
            Room roomTest = new Room();
            roomTest.setEmptyRoom();
            roomTest.addTo_MyRoomInventory(roomType);
            HashSet roomInventory = roomTest.getMyRoomInventory();
            assertEquals(true, roomInventory.contains(roomType.type));
        }
    }

    @Test
    /**
     * tests to see if the inventory inside the room is the same as what it should be
     */
    void getMyRoomInventoryInside() {
        Room roomTest = new Room(RoomType.NORMAL);
        char[] normalRoomArray =new char[]{RoomType.NORMAL.type};
        HashSet RoomInventory = roomTest.getMyRoomInventory();
        assertEquals(Arrays.toString(normalRoomArray) , RoomInventory.toString());
    }

    @Test
    void removeMyTypes() {
        Room roomTest = new Room(RoomType.PIT);
        roomTest.addTo_MyRoomInventory(RoomType.PLAYER);
        String playerIcon = "* ";
        assertEquals(playerIcon, roomTest.toString());
        roomTest.removeMyTypes(RoomType.PLAYER);
        roomTest.exploreTheRoom();
        String pitIcon = "P ";
        assertEquals(pitIcon, roomTest.toString());
    }

    @Test
    /**
     * tests to see if Player gets priority when toString is called
     */
    void testToStringPlayer() {
        Room roomTest = new Room(RoomType.PIT);
        roomTest.addTo_MyRoomInventory(RoomType.PLAYER);
        String playerIcon = "* ";
        assertEquals(playerIcon, roomTest.toString());
    }

    @Test
    /**
     * tests to see if toString is properly working without Player
     */
    void testToString() {
        Room roomTest = new Room(RoomType.PIT);
        String hiddenRoomIcon = "? ";
        String pitRoomIcon = "P ";
        assertEquals(hiddenRoomIcon,roomTest.toString());
        roomTest.exploreTheRoom();
        assertEquals(pitRoomIcon,roomTest.toString());
    }
    @Test
    void setEmptyRoomOne() {
        Room roomTest = new Room(RoomType.PIT);
        String pitRoomIcon = "P ";
        roomTest.exploreTheRoom();
        assertEquals(pitRoomIcon,roomTest.toString());
        roomTest.setEmptyRoom();
        HashSet RoomInventory = roomTest.getMyRoomInventory();
        assertTrue(RoomInventory.isEmpty());
    }
//    @Test
//    void setEmptyRoom() {
//        Room roomTest = new Room(RoomType.PIT);
//        roomTest.addTo_MyRoomInventory(RoomType.PILLAR);
//        String tooManyRoomIcon = "I ";
//
//        roomTest.exploreTheRoom();
//        assertEquals(tooManyRoomIcon,roomTest.toString());
//        roomTest.setEmptyRoom();
//        HashSet RoomInventory = roomTest.getMyRoomInventory();
//        assertTrue(RoomInventory.isEmpty());
//    }

    @Test
    /**
     * tests if exploring the room reveals the room
     */
    void exploreTheRoom() {
        Room roomTest = new Room(RoomType.PIT);
        assertEquals("? ",roomTest.toString());
        roomTest.exploreTheRoom();
        assertEquals("P ",roomTest.toString());
    }
//    @Test
//    /**
//     * tests if exploring the room reveals the room
//     */
//    void exploreTheRoomPillar() {
//        Room roomTest = new Room(RoomType.PILLAR);
//        assertEquals("? ",roomTest.toString());
//        roomTest.exploreTheRoom();
//        assertEquals("I ",roomTest.toString());
//    }


    @Test
    /**
     * tests if exploring the room reveals the room
     */
    void exploreTheRoomTooMuch() {
        Room roomTest = new Room(RoomType.PIT);
        roomTest.addTo_MyRoomInventory(RoomType.NORMAL);
        String tooMuchIcon = "M ";
        String hiddenIcon = "? ";

        assertEquals(hiddenIcon, roomTest.toString());
        roomTest.exploreTheRoom();
        assertEquals(tooMuchIcon, roomTest.toString());
    }
    @Test
    void setEntrance() {
        Room roomTest = new Room();
        roomTest.setEntrance();
        String entranceIcon = "E ";
        assertEquals(entranceIcon, roomTest.toString());
    }

    @Test
    void setExit() {
        Room roomTest = new Room();
        roomTest.setExit();
        String exitIcon = "X ";
        roomTest.exploreTheRoom();
        assertEquals(exitIcon, roomTest.toString());
    }

    @Test
    void setPillar() {
        Room roomTest = new Room();
        roomTest.setEmptyRoom();
        roomTest.setMonster();

        String pillarIcon = "F ";
        roomTest.exploreTheRoom();
        assertEquals(pillarIcon, roomTest.toString());

    }


    @Test
    /**
     * Testing if the hasRoomItems method works properly and returning the correct output.
     */
    void hasRoomItemTest1() {
        Room myRoom = new Room();
        boolean hasItems = true;
        for(RoomType type : RoomType.values()) {
            myRoom.addTo_MyRoomInventory(type);

        }

        for(RoomType type : RoomType.values()) {
            if(!myRoom.hasRoomType(type)) {
                hasItems = false;
                System.out.println("Room doesn't have " + type);
            }

        }

        assertTrue(hasItems);

    }
    @Test
    /**
     * Testing if the hasRoomItems method works properly and returning the correct output.
     *
     * Checking if the method return false.
     */
    void hasRoomItemTest2() {
        Room myRoom = new Room();

        myRoom.addTo_MyRoomInventory(RoomType.PLAYER);
        myRoom.addTo_MyRoomInventory(RoomType.PIT);

        assertFalse(myRoom.hasRoomType(RoomType.ENTRANCE));

    }
    @Test
    void showRoomInventoryTest(){
        Room myRoom = new Room(RoomType.PLAYER);
        myRoom.addTo_MyRoomInventory(RoomType.PIT);
        HashSet roomInventory = myRoom.showMyRoomInventory();
        assertEquals(true, roomInventory.contains(RoomType.PIT.type));
    }

    @Test
    void showRoomInventoryTest2(){
        Room myRoom = new Room(RoomType.NORMAL);
        myRoom.addTo_MyRoomInventory(RoomType.PIT);
        HashSet roomInventory = myRoom.showMyRoomInventory();
        assertEquals(true, roomInventory.contains(RoomType.PIT.type));
    }
}