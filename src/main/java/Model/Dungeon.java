package Model;

import Model.Characters.Hero;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

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

    /**
     * This constructor creates the dungeon with the use of the specified dungeon size from
     * the user.
     * @param theDungeonLength
     *
     */
    Dungeon(final int theDungeonLength) {
        myDungeonSize = theDungeonLength;
        myDungeon = new ArrayList<>();
        dungeonBuilder();
        entranceCreator(myDungeon);
        createExitEntrance(myDungeon, myDungeonSize);
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
     * To string method for the dungeon.
     * @return (String containing the layout of the dungeon)
     */
    public String toString() {

        String dungeonPrint = "\n";
        for (ArrayList row: myDungeon) {
            for (Object myRoom : row) {
                dungeonPrint += myRoom.toString();
            }
            dungeonPrint += "\n";
        }
        return dungeonPrint ;
    }

    private void entranceCreator(final ArrayList<ArrayList<Room>> theDungeon) {
        Random rand = new Random();
        boolean haveEntrance = false;
        boolean haveCrown = false;
        boolean have2ndCrown = false;

        int roomNumber;
        Room roomSetter;

        while (!haveEntrance) {
            if (Math.random() < .1 && !haveEntrance) {
                roomNumber = rand.nextInt(myDungeonSize - 1);
                roomSetter = theDungeon.get(0).get(roomNumber);
                roomSetter.setEntrance();
                haveEntrance = true;
            }
        }

    }

    /**
     * This method creates and exit, entrance and populates the dungeon with 2 crowns.
     * @param theDungeon
     * @param theDungeonSize
     * need to breakup method cus does too many things
     */
    private void createExitEntrance(final ArrayList<ArrayList<Room>> theDungeon, final int theDungeonSize){
        Random rand = new Random();
        Room thisRoom = new Room();
//        System.out.println(theDungeon.forEach(ArrayList::contains));
        boolean haveEntrance = false;//need to check preexisting dungeon to see if they have an entrance or exit this should not fly
        boolean haveExit = false;
        int roomNumber;
        Room roomSetter;
        while (!haveEntrance || !haveExit) {
            if (Math.random() < .1 && !haveEntrance) {
                roomNumber = rand.nextInt(theDungeonSize - 1);
                roomSetter = theDungeon.get(0).get(roomNumber);
                roomSetter.setEntrance();

                haveEntrance = true;
            } if (Math.random() < .1 && !haveExit) {
                roomSetter = theDungeon.get(theDungeonSize - 1).get(rand.nextInt(theDungeonSize));
                roomSetter.setExit();

                haveExit = true;
            }
        }
    }

    /**
     * This method returns a room object that is located at the passed location
     * @param theY (The Y coordinate)
     * @param theX (The X coordinate)
     * @return (Room)
     */
    Room getContent(final int theY, final int theX) {
        return myDungeon.get(theY).get(theX);
    }
    /**
     * This is a cheat method which reveals all of the items in the dungeon.
     */

    protected void revealAll() {
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
    protected void visionPotionUser(final Point theLocation) {
        Room dummyRoom = null;
        ArrayList<Point> currentLocation = new ArrayList<>();
        Point dummyPoint = (Point)(theLocation.clone());

        //dont know if this is needed but helps with magic numbers I guess
        int indexOfLastSize = myDungeonSize-1;
        int firstIndex = 0;

        boolean north = (dummyPoint.y - 1 >= firstIndex);
        boolean south = (dummyPoint.y + 1 <= indexOfLastSize);
        boolean west = (dummyPoint.x - 1 >= firstIndex);
        boolean east = (dummyPoint.x + 1 <= indexOfLastSize);
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


        for (Point local: currentLocation){
            dummyRoom = myDungeon.get(local.y).get(local.x);
            dummyRoom.exploreTheRoom();
        }
    }
    
    /**
     * This method sets the myCheatEnabled to true;
     */
    protected void setMyCheatEnabled(){
        System.out.println("Cheat has been enabled!");
        myCheatEnabled = true;
    }

    /**
     * This method returns the status of the myCheatEnabled field.
     * @return
     */
    protected boolean getMyCheat(){
        return myCheatEnabled;
    }
    public static void main(String[] args) {
       Dungeon myDungeon= new Dungeon(5);
       System.out.println(myDungeon);
    }

}
