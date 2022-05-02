package Tests.Model;
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
    void setMyRoomInventory() {
    }

    @Test
    void getMyRoomInventory() {
    }

    @Test
    void removeMyTypes() {
    }

    @Test
    void testToString() {
    }

    @Test
    void setEmptyRoom() {
    }

    @Test
    void exploreTheRoom() {
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