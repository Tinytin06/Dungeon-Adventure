// Decouple Exit method from the enteranceExitMaker
//


package Model;



import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * This class manages the dungeon, it is responsible for creating and populating the dungeon
 * along other operation that are exclusive to the dungeon.
 * @author Varun Parbhakar
 * @editor Austin Luu
 */
public class Dungeon implements Serializable {
    @Serial
    private static final long serialVersionUID = -6822445562500087830L;


    private int myDungeonSize;
    private ArrayList<ArrayList<Room>> myDungeon;
    private boolean myCheatEnabled;
    private Point myEntranceLocation;




    /**
     * Constructor is for reading from the text file.
     * @param theFile
     * @throws FileNotFoundException
     */
    public Dungeon(final File theFile, final int theDungeonLength) throws FileNotFoundException {

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

        roomSetter = theDungeon.get(rand.nextInt(myDungeonSize)).get(rand.nextInt(myDungeonSize));
        //Ensuring the current room is not an entrance or an exit and doesn't contain crown 2.
            if (!roomSetter.hasRoomType(RoomType.ENTRANCE) && //Does it already have the Entrance?
                !roomSetter.hasRoomType(RoomType.EXIT)  ) { //Does it already have an exit?

                roomSetter.addTo_MyRoomInventory(theRoomType);
                return true;
            }
        return false;

    }




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

        }

        for (Point local: currentLocation){
            dummyRoom = myDungeon.get(local.y).get(local.x);
            dummyRoom.exploreTheRoom();
        }
    }
    
    /**
     * This method sets the myCheatEnabled to true;
     */
    public void setMyCheatEnabled(){
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
                }
            }
            myDungeon.add(dungeonRow);
        }

    }

    public int getMyDungeonSize(){
        return myDungeonSize;
    }
}

