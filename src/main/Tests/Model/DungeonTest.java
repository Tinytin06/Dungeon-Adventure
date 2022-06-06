package Model;

import org.junit.jupiter.api.Test;

import java.awt.*;
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
        String myFileName = "./src/main/Tests/TestFiles/readFromText1.txt";
        boolean hasDifferentRooms = false;

        myDungeon = new Dungeon(new File(myFileName), myDungeonSize);
        myDungeon.revealAll();
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
        String myFileName = "./src/main/Tests/TestFiles/readFromText2.txt";
        boolean hasDifferentRooms = false;

        myDungeon = new Dungeon(new File(myFileName), myDungeonSize);
        myDungeon.revealAll();
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
        String myFileName = "./src/main/Tests/TestFiles/readFromText3.txt";
        String myFakeFile = "./src/main/Tests/TestFiles/readFromText4.txt";
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
        String myFileName = "./src/main/Tests/TestFiles/readFromText4.txt";
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
        String myTestFile = "./src/main/Tests/TestFiles/entranceCreatorTest1.txt";
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
    /**
     * Testing if the vision potion properly illuminates the 4 rooms around the player.
     *
     * Testing Center of the map
     */
    void visionPotionTest1() throws FileNotFoundException {
        //Map containing Fights in every location.
        String myFileName = "./src/main/Tests/TestFiles/VisionPotionTest1.txt";
        int myDungeonSize = 6;

        myDungeon = new Dungeon(new File(myFileName), myDungeonSize);


        Point playerLocation = new Point(2,2);

        myDungeon.useVisionPotion(playerLocation);


        //for some reason if I true to check if this whole boolean statement is true or false
        // with assertTrue there seems to be an error for some reason.
        boolean east = (myDungeon.getRoom(playerLocation.x, playerLocation.y-1).getMyRoomInventory().contains(RoomType.FIGHT.type)); //East
        boolean west = (myDungeon.getRoom(playerLocation.x, playerLocation.y+1).getMyRoomInventory().contains(RoomType.FIGHT.type)); //West
        boolean north = (myDungeon.getRoom(playerLocation.x-1, playerLocation.y).getMyRoomInventory().contains(RoomType.FIGHT.type)); //North
        boolean south = (myDungeon.getRoom(playerLocation.x+1, playerLocation.y).getMyRoomInventory().contains(RoomType.FIGHT.type)); //South

        assertTrue(east);
        assertTrue(west);
        assertTrue(north);
        assertTrue(south);

    }

    @Test
    /**
     * Testing if the vision potion properly illuminates the 4 rooms around the player.
     *
     * Testing the top left corner of the map.
     */
    void visionPotionTest2() throws FileNotFoundException {
        //Map containing Fights in every location.
        String myFileName = "./src/main/Tests/TestFiles/VisionPotionTest2.txt";
        int myDungeonSize = 6;

        myDungeon = new Dungeon(new File(myFileName), myDungeonSize);


        Point playerLocation = new Point(0,0);

        myDungeon.useVisionPotion(playerLocation);


        //for some reason if I true to check if this whole boolean statement is true or false
        // with assertTrue there seems to be an error for some reason.
        //boolean east = (myDungeon.getRoom(playerLocation.x, playerLocation.y-1).getMyRoomInventory().contains(RoomType.FIGHT.type)); //East
        boolean west = (myDungeon.getRoom(playerLocation.x, playerLocation.y+1).getMyRoomInventory().contains(RoomType.FIGHT.type)); //West
        //boolean north = (myDungeon.getRoom(playerLocation.x-1, playerLocation.y).getMyRoomInventory().contains(RoomType.FIGHT.type)); //North
        boolean south = (myDungeon.getRoom(playerLocation.x+1, playerLocation.y).getMyRoomInventory().contains(RoomType.FIGHT.type)); //South

//        assertTrue(east);
        assertTrue(west);
//        assertTrue(north);
        assertTrue(south);

    }

    @Test
    /**
     * Testing if the vision potion properly illuminates the 4 rooms around the player.
     *
     *  Testing the south wall.
     */
    void visionPotionTest3() throws FileNotFoundException {
        //Map containing Fights in every location.
        String myFileName = "./src/main/Tests/TestFiles/VisionPotionTest3.txt";
        int myDungeonSize = 6;

        myDungeon = new Dungeon(new File(myFileName), myDungeonSize);


        Point playerLocation = new Point(5,3);

        myDungeon.useVisionPotion(playerLocation);


        //for some reason if I true to check if this whole boolean statement is true or false
        // with assertTrue there seems to be an error for some reason.
        boolean east = (myDungeon.getRoom(playerLocation.x, playerLocation.y-1).getMyRoomInventory().contains(RoomType.FIGHT.type)); //East
        boolean west = (myDungeon.getRoom(playerLocation.x, playerLocation.y+1).getMyRoomInventory().contains(RoomType.FIGHT.type)); //West
        boolean north = (myDungeon.getRoom(playerLocation.x-1, playerLocation.y).getMyRoomInventory().contains(RoomType.FIGHT.type)); //North
//        boolean south = (myDungeon.getRoom(playerLocation.x+1, playerLocation.y).getMyRoomInventory().contains(RoomType.FIGHT.type)); //South

        assertTrue(east);
        assertTrue(west);
        assertTrue(north);
//        assertTrue(south);

    }
    @Test
    /**
     * Testing if the vision potion properly illuminates the 4 rooms around the player.
     *
     * Testing the North.
     */
    void visionPotionTest4() throws FileNotFoundException {
        //Map containing Fights in every location.
        String myFileName = "./src/main/Tests/TestFiles/VisionPotionTest4.txt";
        int myDungeonSize = 6;

        myDungeon = new Dungeon(new File(myFileName), myDungeonSize);


        Point playerLocation = new Point(2,2);

        myDungeon.useVisionPotion(playerLocation);


        //for some reason if I true to check if this whole boolean statement is true or false
        // with assertTrue there seems to be an error for some reason.
        boolean east = (myDungeon.getRoom(playerLocation.x, playerLocation.y-1).getMyRoomInventory().contains(RoomType.FIGHT.type)); //East
        boolean west = (myDungeon.getRoom(playerLocation.x, playerLocation.y+1).getMyRoomInventory().contains(RoomType.FIGHT.type)); //West
        boolean north = (myDungeon.getRoom(playerLocation.x-1, playerLocation.y).getMyRoomInventory().contains(RoomType.FIGHT.type)); //North
        boolean south = (myDungeon.getRoom(playerLocation.x+1, playerLocation.y).getMyRoomInventory().contains(RoomType.FIGHT.type)); //South

        assertFalse(east);
        assertFalse(west);
        assertTrue(north);
        assertFalse(south);

    }

    @Test
    /**
     * Testing if the vision potion properly illuminates the 4 rooms around the player.
     *
     * Testing the East.
     */
    void visionPotionTest5() throws FileNotFoundException {
        //Map containing Fights in every location.
        String myFileName = "./src/main/Tests/TestFiles/VisionPotionTest5.txt";
        int myDungeonSize = 6;

        myDungeon = new Dungeon(new File(myFileName), myDungeonSize);


        Point playerLocation = new Point(2,2);

        myDungeon.useVisionPotion(playerLocation);


        //for some reason if I true to check if this whole boolean statement is true or false
        // with assertTrue there seems to be an error for some reason.
        boolean east = (myDungeon.getRoom(playerLocation.x, playerLocation.y-1).getMyRoomInventory().contains(RoomType.FIGHT.type)); //East
        boolean west = (myDungeon.getRoom(playerLocation.x, playerLocation.y+1).getMyRoomInventory().contains(RoomType.FIGHT.type)); //West
        boolean north = (myDungeon.getRoom(playerLocation.x-1, playerLocation.y).getMyRoomInventory().contains(RoomType.FIGHT.type)); //North
        boolean south = (myDungeon.getRoom(playerLocation.x+1, playerLocation.y).getMyRoomInventory().contains(RoomType.FIGHT.type)); //South

        assertTrue(east);
        assertFalse(west);
        assertFalse(north);
        assertFalse(south);

    }

    @Test
    /**
     * Testing if the vision potion properly illuminates the 4 rooms around the player.
     *
     * Testing the South.
     */
    void visionPotionTest6() throws FileNotFoundException {
        //Map containing Fights in every location.
        String myFileName = "./src/main/Tests/TestFiles/VisionPotionTest6.txt";
        int myDungeonSize = 6;

        myDungeon = new Dungeon(new File(myFileName), myDungeonSize);


        Point playerLocation = new Point(2,2);

        myDungeon.useVisionPotion(playerLocation);


        //for some reason if I true to check if this whole boolean statement is true or false
        // with assertTrue there seems to be an error for some reason.
        boolean east = (myDungeon.getRoom(playerLocation.x, playerLocation.y-1).getMyRoomInventory().contains(RoomType.FIGHT.type)); //East
        boolean west = (myDungeon.getRoom(playerLocation.x, playerLocation.y+1).getMyRoomInventory().contains(RoomType.FIGHT.type)); //West
        boolean north = (myDungeon.getRoom(playerLocation.x-1, playerLocation.y).getMyRoomInventory().contains(RoomType.FIGHT.type)); //North
        boolean south = (myDungeon.getRoom(playerLocation.x+1, playerLocation.y).getMyRoomInventory().contains(RoomType.FIGHT.type)); //South

        assertFalse(east);
        assertFalse(west);
        assertFalse(north);
        assertTrue(south);

    }

    @Test
    /**
     * Testing if the vision potion properly illuminates the 4 rooms around the player.
     *
     * Testing the West.
     */
    void visionPotionTest7() throws FileNotFoundException {
        //Map containing Fights in every location.
        String myFileName = "./src/main/Tests/TestFiles/VisionPotionTest7.txt";
        int myDungeonSize = 6;

        myDungeon = new Dungeon(new File(myFileName), myDungeonSize);


        Point playerLocation = new Point(2,2);

        myDungeon.useVisionPotion(playerLocation);


        //for some reason if I true to check if this whole boolean statement is true or false
        // with assertTrue there seems to be an error for some reason.
        boolean east = (myDungeon.getRoom(playerLocation.x, playerLocation.y-1).getMyRoomInventory().contains(RoomType.FIGHT.type)); //East
        boolean west = (myDungeon.getRoom(playerLocation.x, playerLocation.y+1).getMyRoomInventory().contains(RoomType.FIGHT.type)); //West
        boolean north = (myDungeon.getRoom(playerLocation.x-1, playerLocation.y).getMyRoomInventory().contains(RoomType.FIGHT.type)); //North
        boolean south = (myDungeon.getRoom(playerLocation.x+1, playerLocation.y).getMyRoomInventory().contains(RoomType.FIGHT.type)); //South

        assertFalse(east);
        assertTrue(west);
        assertFalse(north);
        assertFalse(south);

    }

    @Test
    void exitMakerTest1() {
        int myDungeonSize = 5;
        boolean exitExists = false;
        /*
         * A random dungeon will be created and test will check if
         * an exit is present in the last row of the dungeon.
         */
        myDungeon = new Dungeon(myDungeonSize);
        for (int i = 0; i < myDungeonSize; i++) {
            if(!myDungeon.getRoom(myDungeonSize-1, i).getMyRoomInventory().contains(RoomType.EXIT.type)) {
                exitExists = true;
                break;
            }
        }
        assertTrue(exitExists); // Check for multiple Exits// Check for no Exits

    }
//    @Test
//    /**
//     * Checking if created dungeon has the 2 crowns.
//     */
//    void crownSetterTest() {
//        int myDungeonSize = 5;
//        boolean foundFirstCrown = false;
//        boolean foundSecondCrown = false;
//
//        myDungeon = new Dungeon(myDungeonSize);
//
//
//        for (int i = 0; i < myDungeonSize; i++) {
//            for (int j = 0; j < myDungeonSize; j++) {
//                if(myDungeon.getRoom(i, j).getMyRoomInventory().contains(RoomType.CODING_CROWN_1.type)) {
//                    foundFirstCrown = true;
//                } else if(myDungeon.getRoom(i, j).getMyRoomInventory().contains(RoomType.CODING_CROWN_2.type)) {
//                    foundSecondCrown = true;
//                }
//            }
//        }
//        assertTrue(foundFirstCrown);
//        assertTrue(foundSecondCrown);
//
//    }
//
//    @Test
//    /**
//     * Checking if created dungeon has the 2 crowns.
//     * Checking if there are multiple of the same kind.
//     */
//    void crownSetterTest2() {
//        int myDungeonSize = 5;
//        boolean foundFirstCrown = false;
//        boolean foundSecondCrown = false;
//
//        boolean foundADuplicate = false;
//
//        myDungeon = new Dungeon(myDungeonSize);
//
//
//        for (int i = 0; i < myDungeonSize; i++) {
//            for (int j = 0; j < myDungeonSize; j++) {
//
//                if(myDungeon.getRoom(i, j).getMyRoomInventory().contains(RoomType.CODING_CROWN_1.type)) {
//
//                    if (foundFirstCrown) {
//                        //Found a duplicate
//                        foundADuplicate = true;
//                        break;
//                    } else {
//                        foundFirstCrown = true;
//                    }
//                } else if(myDungeon.getRoom(i, j).getMyRoomInventory().contains(RoomType.CODING_CROWN_2.type)) {
//
//                    if (foundSecondCrown) {
//                        //Found a duplicate
//                        foundADuplicate = true;
//                        break;
//                    } else {
//                        foundSecondCrown = true;
//                    }
//                }
//            }
//        }
//        assertFalse(foundADuplicate);
//
//    }

    @Test
    void getContent() {
    }

    @Test
    void main() {
    }
}