package Model;
import Model.Room;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

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
        Room roomTest = new Room();
        roomTest.setEmptyRoom();
        roomTest.setMyRoomInventory(RoomType.NORMAL);
        HashSet roomInventory = roomTest.getMyRoomInventory();
        assertEquals(true, roomInventory.contains(RoomType.NORMAL.type));//test looks awful might change it so that there is less ellipses references
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
        roomTest.setMyRoomInventory(RoomType.PLAYER);
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
        roomTest.setMyRoomInventory(RoomType.PLAYER);
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
    @Test
    void setEmptyRoom() {
        Room roomTest = new Room(RoomType.PIT);
        roomTest.setMyRoomInventory(RoomType.PILLAR);
        String tooManyRoomIcon = "I ";

        roomTest.exploreTheRoom();
        assertEquals(tooManyRoomIcon,roomTest.toString());
        roomTest.setEmptyRoom();
        HashSet RoomInventory = roomTest.getMyRoomInventory();
        assertTrue(RoomInventory.isEmpty());
    }

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
    @Test
    /**
     * tests if exploring the room reveals the room
     */
    void exploreTheRoomPillar() {
        Room roomTest = new Room(RoomType.PILLAR);
        assertEquals("? ",roomTest.toString());
        roomTest.exploreTheRoom();
        assertEquals("I ",roomTest.toString());
    }


    @Test
    /**
     * tests if exploring the room reveals the room
     */
    void exploreTheRoomTooMuch() {
        Room roomTest = new Room(RoomType.PIT);
        roomTest.setMyRoomInventory(RoomType.NORMAL);
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
        roomTest.setPillar();
        String pillarIcon = "I ";
        roomTest.exploreTheRoom();
        assertEquals(pillarIcon, roomTest.toString());
    }


}