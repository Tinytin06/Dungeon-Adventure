// Decouple Exit method from the enteranceExitMaker
//


package Model;



import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * This class manages the dungeon, it is responsible for creating and populating the dungeon
 * along other operation that are exclusive to the dungeon.
 * @author Varun Parbhakar
 * @editor Austin Luu
 */
public class Dungeon {
    private int myDungeonSize;
    private ArrayList<ArrayList<Room>> myDungeon;
    private boolean myCheatEnabled;
    private Point myEntranceLocation;




    /**
     * Constructor is for reading from the text file.
     * @param theFile
     * @throws FileNotFoundException
     */
    Dungeon(final File theFile, final int theDungeonLength) throws FileNotFoundException {

        if(theDungeonLength < 2) {
            throw new IllegalArgumentException("Dungeon size must be greater than or equal to 2");
        }
        myDungeonSize = theDungeonLength;
        readFromFile(theFile);
    }

    /**
     * Returns the entrance of the dungeon.
     * @return
     */
    public Point getEntrancePoint() {
        if(myDungeon.size() <= 0) {
            throw new IllegalStateException("The dungeon doesn't exist, current dungeon size: " + myDungeonSize);
        }
        return myEntranceLocation;
    }

    /**
     * This constructor creates the dungeon with the use of the specified dungeon size from
     * the user.
     * @param theDungeonLength
     *
     */
    public Dungeon(final int theDungeonLength) {
        if(theDungeonLength < 2) {
            throw new IllegalArgumentException("Dungeon size must be greater than or equal to 2");
        }
        myDungeonSize = theDungeonLength;
        myDungeon = new ArrayList<>();
        dungeonBuilder();
        entranceCreator(myDungeon);
        exitCreator(myDungeon);
        pillarSetter(myDungeon);
    }


    /**
     * This method builds a dungeon by creating rooms in the ArrayList
     */

    void dungeonBuilder(){
        for (int i = 0; i < myDungeonSize; i++) {
            ArrayList<Room> row = new ArrayList<>();
            for (int j = 0; j < myDungeonSize; j++) {
                Room myNewRoom = new Room();
                row.add(myNewRoom);
                //myNewRoom.exploreTheRoom();           //
            }
            myDungeon.add(row);
        }
    }

    /**
     * This method is used to return the room of the passed parameters.
     * @param row
     * @param column
     * @return
     */
    Room getRoom(final int row, final int column ) {
        return myDungeon.get(row).get(column);
    }
    /**
     * To string method for the dungeon.
     * @return (String containing the layout of the dungeon)
     */
    public String toString() {

        StringBuilder dungeonPrint = new StringBuilder();
        for (ArrayList row: myDungeon) {
            for (Object myRoom : row) {
                dungeonPrint.append(myRoom.toString());
            }
            dungeonPrint.append("\n");
        }
        return dungeonPrint.toString() ;
    }


    private void entranceCreator(final ArrayList<ArrayList<Room>> theDungeon) {
        Random rand = new Random();
        boolean haveEntrance = false;

        int roomNumber;
        Room roomSetter;

        while (!haveEntrance) {
            if (Math.random() < .1 && !haveEntrance) {
                roomNumber = rand.nextInt(myDungeonSize - 1);
                roomSetter = theDungeon.get(0).get(roomNumber);
                roomSetter.setEntrance();
                myEntranceLocation = new Point(roomNumber, 0);
                haveEntrance = true;
            }
        }

    }

    private void exitCreator(final ArrayList<ArrayList<Room>> theDungeon) {
        Random rand = new Random();
        boolean haveExit = false;

        int roomNumber;
        Room roomSetter;

        while (!haveExit) {
            if (Math.random() < .1 && !haveExit) {
                roomNumber = rand.nextInt(myDungeonSize - 1);
                roomSetter = theDungeon.get(myDungeonSize-1).get(roomNumber);
                roomSetter.setExit();
                haveExit = true;
            }
        }

    }

    /**
     * This method creates sets the crown in a random room except for the rooms that contain
     * the entrance or the exit.
     *
     * There is a chance that a two of the crown can be placed into the same room.
     *
     * @param theDungeonMap
     */
    private void pillarSetter(final ArrayList<ArrayList<Room>> theDungeonMap) {
        boolean hasInheritance = false;
        boolean hasPolymorphism = false;
        boolean hasEncapsulation = false;
        boolean hasAbstraction = false;


        while (!hasInheritance || !hasPolymorphism || !hasEncapsulation || !hasAbstraction) {

            //Locating a room for a Inheritance
            if(!hasInheritance) {
                hasInheritance = pillarSetterHelper(theDungeonMap, RoomType.INHERITANCE);
            }
            if(!hasPolymorphism) {
                hasPolymorphism = pillarSetterHelper(theDungeonMap, RoomType.POLYMORPHISM);
            }
            if(!hasEncapsulation) {
                hasEncapsulation = pillarSetterHelper(theDungeonMap, RoomType.ENCAPSULATION);
            }
            if(!hasAbstraction) {
                hasAbstraction = pillarSetterHelper(theDungeonMap, RoomType.ABSTRACTION);
            }



        }
    }
    public boolean pillarSetterHelper(final ArrayList<ArrayList<Room>> theDungeon, final RoomType theRoomType) {
        Random rand = new Random();
        Room roomSetter;
        //Locating a room for Polymorphism
        if (Math.random() < .1) {
            roomSetter = theDungeon.get(rand.nextInt(myDungeonSize - 1)).get(rand.nextInt(myDungeonSize - 1));
            //Ensuring the current room is not an entrance or an exit and doesn't contain crown 2.
            if (
                    !roomSetter.hasRoomType(theRoomType) && //Does it already have the Pillar?
                            !roomSetter.hasRoomType(RoomType.ENTRANCE) && //Does it already have the Entrance?
                            !roomSetter.hasRoomType(RoomType.EXIT)) { //Does it already have an exit?

                roomSetter.addTo_MyRoomInventory(theRoomType);
                return true;
            }

        }
        return false;

    }




// The old create entrance and exit method

//    /**
//     * This method creates and exit, entrance and populates the dungeon with 2 crowns.
//     * @param theDungeon
//     * @param theDungeonSize
//     * need to breakup method cus does too many things
//     */
//    private void createExitEntrance(final ArrayList<ArrayList<Room>> theDungeon, final int theDungeonSize){
//        Random rand = new Random();
//        Room thisRoom = new Room();
////        System.out.println(theDungeon.forEach(ArrayList::contains));
//        boolean haveEntrance = false;//need to check preexisting dungeon to see if they have an entrance or exit this should not fly
//        boolean haveExit = false;
//        int roomNumber;
//        Room roomSetter;
//        while (!haveEntrance || !haveExit) {
//            if (Math.random() < .1 && !haveEntrance) {
//                roomNumber = rand.nextInt(theDungeonSize - 1);
//                roomSetter = theDungeon.get(0).get(roomNumber);
//                roomSetter.setEntrance();
//
//                haveEntrance = true;
//            } if (Math.random() < .1 && !haveExit) {
//                roomSetter = theDungeon.get(theDungeonSize - 1).get(rand.nextInt(theDungeonSize));
//                roomSetter.setExit();
//
//                haveExit = true;
//            }
//        }
//    }

    /**
     * This method returns a room object that is located at the passed location
     * @param theY (The Y coordinate)
     * @param theX (The X coordinate)
     * @return (Room)
     */
    public Room getContent(final int theY, final int theX) {
        return myDungeon.get(theY).get(theX);
    }


    /**
     * This is a cheat method which reveals all the items in the dungeon.
     */
    public void revealAll() {
        Room dummyRoom;
        for(int i = 0; i < myDungeonSize; i++){
            for (int j = 0; j < myDungeonSize; j++) {
                dummyRoom = myDungeon.get(i).get(j);
                dummyRoom.exploreTheRoom();
            }
        }
    }


    /**
     * This method searches for the possible directions and reveals the room accordingly.
     * @param theLocation (Location of the hero)
     */
    public void useVisionPotion(final Point theLocation) {
        Room dummyRoom = null;
        ArrayList<Point> currentLocation = new ArrayList<>();
        Point dummyPoint = (Point)(theLocation.clone());

        //Checking where the vision potion will illuminate the room.
        boolean north = (dummyPoint.y - 1 >= 0);
        boolean south = (dummyPoint.y + 1 <= myDungeonSize-1);
        boolean west = (dummyPoint.x - 1 >= 0);
        boolean east = (dummyPoint.x + 1 <= myDungeonSize-1);
        //Ask Tom if this structure can be improved



        if (north) {
            dummyPoint.translate(0,-1);
            currentLocation.add(dummyPoint);
            dummyPoint = (Point)(theLocation.clone());
        }if (south) {
            dummyPoint.translate(0,1);
            currentLocation.add(dummyPoint);
            dummyPoint = (Point)(theLocation.clone());
        }if (east) {
            dummyPoint.translate(1,0);
            currentLocation.add(dummyPoint);
            dummyPoint = (Point)(theLocation.clone());
        }if (west) {
            dummyPoint.translate(-1,0);
            currentLocation.add(dummyPoint);
            dummyPoint = (Point)(theLocation.clone());
        }

        //An improvement this might be instead of storing all the room in an array list, we simply
        //reveal the contents of the room as we translate to it, In 4 lines above this.
        for (Point local: currentLocation){
            dummyRoom = myDungeon.get(local.y).get(local.x);
            dummyRoom.exploreTheRoom();
        }
    }
    
    /**
     * This method sets the myCheatEnabled to true;
     */
    public void setMyCheatEnabled(){
        System.out.println("Cheat has been enabled!");
        myCheatEnabled = true;
    }

    /**
     * This method returns the status of the myCheatEnabled field.
     * @return
     */
    public boolean isCheatEnabled(){
        return myCheatEnabled;
    }

    protected void readFromFile(final File theFile) throws FileNotFoundException {
        Scanner s = new Scanner(theFile);
        myDungeon = new ArrayList<>();

        for (int i = 0; i < myDungeonSize; i++) {
            ArrayList<Room> dungeonRow = new ArrayList<>();
            String[] row = s.nextLine().split(" ");

            for (int j = 0; j < myDungeonSize; j++) {
                Room myNewRoom;

                //Add code from room and just

//                for (RoomType roomTypes : RoomType.values()) {
//                    if (myRoomInventory.contains(roomTypes.type)) {
//                        setMyDisplayIcon(roomTypes.type + " ");
//                    }
//                }
                int singularChar = 0;
                boolean hasRoomType = false;
                for(RoomType roomType : RoomType.values()){

                    if(row[j].charAt(singularChar)==roomType.type){
                        myNewRoom = new Room(roomType);
                        dungeonRow.add(myNewRoom);
                        hasRoomType=true;
                        break;
                    }
                }
                if(!hasRoomType){
                    throw new IllegalArgumentException(row[j] + " is not a correct room.");
                }//need to figure out default case


//                switch (row[j]){
//                    case("F"):
//                        myNewRoom = new Room(RoomType.FIGHT);
//                        dungeonRow.add(myNewRoom);
//                        break;
//
//                    case("P"):
//                        myNewRoom = new Room(RoomType.PIT);
//                        dungeonRow.add(myNewRoom);
//
//                        break;
//
//                    case("N"):
//                        myNewRoom = new Room(RoomType.NORMAL);
//                        dungeonRow.add(myNewRoom);
//
//                        break;
//
//                    case("I"):
//                        myNewRoom = new Room(RoomType.PILLAR);
//                        dungeonRow.add(myNewRoom);
//
//                        break;
//
//                    case("X"):
//                        myNewRoom = new Room(RoomType.EXIT);
//                        dungeonRow.add(myNewRoom);
//                        break;
//
//                    case("E"):
//                        myNewRoom = new Room(RoomType.ENTRANCE);
//                        dungeonRow.add(myNewRoom);
//
//                        break;
//
//                    case("H"):
//                        myNewRoom = new Room(RoomType.HEALING);
//                        dungeonRow.add(myNewRoom);
//
//                        break;
//
//                    case("V"):
//                        myNewRoom = new Room(RoomType.VISION);
//                        dungeonRow.add(myNewRoom);
//
//                        break;
//
//                    case("*"):
//                        myNewRoom = new Room(RoomType.PLAYER);
//                        dungeonRow.add(myNewRoom);
//
//                        break;
//
//                    case("K"):
//                        myNewRoom = new Room(RoomType.CODING_CROWN_1);
//                        dungeonRow.add(myNewRoom);
//
//                        break;
//
//                    case("Q"):
//                        myNewRoom = new Room(RoomType.CODING_CROWN_2);
//                        dungeonRow.add(myNewRoom);
//
//                        break;
//
//                    default:
//                        throw new IllegalArgumentException(row[j] + " is not a correct room.");
//
//                }
                //myNewRoom.exploreTheRoom();           //
            }
            myDungeon.add(dungeonRow);
        }

    }

}
