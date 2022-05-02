package Model;
import Model.Room;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    //made public setMyDisplayIcon and constructor for this test
    /**
     * tests to see if too much of a string gets put in that the exception is triggered
     */
    void setMyDisplayIconTooBig() {
        Room roomTest = new Room();
        Boolean caughtExcepton = false;
        try {
            roomTest.setMyDisplayIcon("Too Big");
        } catch (IllegalArgumentException e) {
            caughtExcepton = true;
        }

        assertTrue(caughtExcepton);
    }
    @Test
    /**
     * tests to see if too little of a string gets put in that the exception is triggered
     */
    void setMyDisplayIconTooSmall() {
        Room roomTest = new Room();
        Boolean caughtExcepton = false;
        String tooSmall = "";
        try {
            roomTest.setMyDisplayIcon(tooSmall);
        } catch (IllegalArgumentException e) {
            caughtExcepton = true;
        }

        assertTrue(caughtExcepton);
    }

    @Test
    /**
     * tests to see if the Icon Properly gets set
     */
    void setMyDisplayIconProper() {
        Room roomTest = new Room();
        roomTest.setMyDisplayIcon("N ");
        assertEquals("N ", roomTest.toString());
    }

    @Test
    /**
     * tests to see if setting the inventory of MyRoomInventory actually gets added to the hashset
     */
    void setMyRoomInventory() {
        Room roomTest = new Room();
        roomTest.setEmptyRoom();
        roomTest.setMyRoomInventory(RoomType.NORMAL);
        assertEquals(true, roomTest.getMyRoomInventory().contains(RoomType.NORMAL.type));//test looks awful might change it so that there is less ellipses references
    }

    @Test
    /**
     * tests to see if the inventory inside the room is the same as what it should be
     */
    void getMyRoomInventoryInside() {
        Room roomTest = new Room(RoomType.NORMAL);
        assertEquals(new char[]{RoomType.NORMAL.type}.toString() ,roomTest.getMyRoomInventory().toArray().toString());
    }

    @Test
    void removeMyTypes() {
    }

    @Test
    /**
     * tests to see if Player gets priority when toString is called
     */
    void testToStringPlayer() {
        Room roomTest = new Room(RoomType.PIT);
        roomTest.setMyRoomInventory(RoomType.PLAYER);
        assertEquals("* ",roomTest.toString());
    }

    @Test
    /**
     * tests to see if toString is properly working without Player
     */
    void testToString() {
        Room roomTest = new Room(RoomType.PIT);
        assertEquals("? ",roomTest.toString());
        roomTest.exploreTheRoom();
        assertEquals("P ",roomTest.toString());
    }
    @Test
    void setEmptyRoom() {
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
    void exploreTheRoomTooMuch() {
        Room roomTest = new Room(RoomType.PIT);
        roomTest.setMyRoomInventory(RoomType.NORMAL);
        assertEquals("? ",roomTest.toString());
        roomTest.exploreTheRoom();
        assertEquals("M ",roomTest.toString());
    }
    @Test
    void setEntrance() {
    }

    @Test
    void setExit() {
    }

    @Test
    void setPillar() {
    }

    @Test
    void main() {
    }
}