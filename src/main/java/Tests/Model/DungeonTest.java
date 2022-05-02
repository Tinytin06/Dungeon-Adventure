package Tests.Model;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.Assert.*;

class DungeonTest {
    Dungeon myDungeon;
    @Test
    /**
     * Testing if dungeon is being created with the correct size.
     */
    void constructorTest1() {
        myDungeon = new Dungeon(4);

    }

    @Test
    /**
     * Testing if dungeon is being read correctly from .txt file
     */
    void readFromFileTest1() throws FileNotFoundException {
        int myDungeonSize = 5;
        String myFileName = "readFromText1.txt";
        boolean hasDifferentRooms = false;

        myDungeon = new Dungeon(new File(myFileName), myDungeonSize);
        Scanner s = new Scanner(new File(myFileName));

        //Looping through file and the dungeon to ensure the rooms are the same
        for (int i = 0; i < myDungeonSize ; i++) {
            String[] fileRow = s.nextLine().split(" ");
            for (int j = 0; j < myDungeonSize; j++) {
                if(!(fileRow[j] + " ").equals(myDungeon.getRoom(i,j).toString())) {
                    hasDifferentRooms = true;
                }
            }
        }
        assertFalse(hasDifferentRooms);

    }
    @Test
    /**
     * Testing if dungeon is being read correctly from .txt file
     */
    void readFromFileTest2() throws FileNotFoundException {
        int myDungeonSize = 5;
        String myFileName = "readFromText2.txt";
        boolean hasDifferentRooms = false;

        myDungeon = new Dungeon(new File(myFileName), myDungeonSize);
        Scanner s = new Scanner(new File(myFileName));

        //Looping through file and the dungeon to ensure the rooms are the same
        for (int i = 0; i < myDungeonSize ; i++) {
            String[] fileRow = s.nextLine().split(" ");
            for (int j = 0; j < myDungeonSize; j++) {
                if(!(fileRow[j] + " ").equals(myDungeon.getRoom(i,j).toString())) {
                    hasDifferentRooms = true;
                }
            }
        }
        assertFalse(hasDifferentRooms);

    }

    @Test
    /**
     * Testing if dungeon is being read correctly from .txt file
     *
     * This method ensure the test is working by having a
     * single room that is different from others.
     */
    void readFromFileTest3() throws FileNotFoundException {
        int myDungeonSize = 5;
        String myFileName = "readFromText3.txt";
        String myFakeFile = "readFromText4.txt";
        boolean hasDifferentRooms = false;

        myDungeon = new Dungeon(new File(myFileName), myDungeonSize);
        Scanner s = new Scanner(new File(myFakeFile));

        //Looping through file and the dungeon to ensure the rooms are the same
        for (int i = 0; i < myDungeonSize ; i++) {
            String[] fileRow = s.nextLine().split(" ");
            for (int j = 0; j < myDungeonSize; j++) {
                if(!(fileRow[j] + " ").equals(myDungeon.getRoom(i,j).toString())) {
                    hasDifferentRooms = true;
                }
            }
        }
        assertTrue(hasDifferentRooms);

    }
    @Test
    /**
     * Testing if dungeon is being read correctly from .txt file
     *
     * This test checks for invalid input from the text file.
     */
    void readFromFileTest4() throws FileNotFoundException {
        int myDungeonSize = 5;
        String myFileName = "readFromText4.txt";
        boolean caughtError = false;

        try {
            myDungeon = new Dungeon(new File(myFileName), myDungeonSize);
        } catch (IllegalArgumentException e) {
            caughtError = true;
        }

        assertTrue(caughtError);

    }


    @Test
    /**
     * Testing if dungeonbuilder is populating the dungeon properly with rooms only
     * rooms.
     */
    void dungeonBuilderTest() {
        int  myDungeonSize = 5;

        boolean hasRooms = true; //This boolean will false if the dungeon is not filled with rooms
        myDungeon = new Dungeon(myDungeonSize);

        for (int i = 0; i < myDungeonSize; i++) {
            for (int j = 0; j < myDungeonSize; j++) {
                if(myDungeon.getRoom(i, j).getClass() != Room.class) {
                    System.out.println("The room object doesn't exists at Row: " + i +" Column: " + j + "");
                    hasRooms = false;
                    break;
                }
            }
        }
        assertTrue(hasRooms);
    }
    @Test
    /**
     * Testing if dungeonbuilder is populating the dungeon properly with rooms only
     * rooms.
     */
    void dungeonBuilderTest2() {
        int  myDungeonSize = 2;

        boolean hasRoom = true; //This boolean will false if the dungeon is not filled with rooms
        myDungeon = new Dungeon(myDungeonSize);
        System.out.println(myDungeon);

        for (int i = 0; i < myDungeonSize; i++) {
            for (int j = 0; j < myDungeonSize; j++) {
                if(myDungeon.getRoom(i, j).getClass() != Room.class) {
                    System.out.println("The room object doesn't exists at Row: " + i +" Column: " + j + "");
                    hasRoom = false;
                    break;
                }
            }
        }
        assertEquals(true, hasRoom);
    }



    @Test
    /**
     * This method will check if an entrance is created via the enteranceCreator method.
     */
    void entranceCreatorTest1() {
        String myTestFile = "entranceCreatorTest1.txt";
        int myDungeonSize = 5;
        boolean entranceExists = false;
        /*
         * A random dungeon will be created and test will check if
         * an entrance is present in the first row of the dungeon.
         */
        myDungeon = new Dungeon(5);
        for (int i = 0; i < 5; i++) {
            if(!myDungeon.getRoom(0, i).getMyRoomInventory().contains(RoomType.ENTRANCE.type)) {
                entranceExists = true;
                break;
            }
        }
        assertTrue(entranceExists); // Check for multiple entrances// Check for no enterances
    }

    @Test
    void getContent() {
    }

    @Test
    void main() {
    }
}