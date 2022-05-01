package Model;

import Model.Characters.Hero;

import java.util.ArrayList;
import java.util.Random;

public class Dungeon {
    private int myDungeonSize;
    private ArrayList<ArrayList<Room>> myDungeon;

    Dungeon(final int theDungeonLength) {
        myDungeonSize = theDungeonLength;
        myDungeon = new ArrayList<>();
        dungeonBuilder();
        entranceCreator(myDungeon);
        createExitEntrance(myDungeon, myDungeonSize);
    }

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

    Room getContent(final int theY, final int theX) {
        return myDungeon.get(theY).get(theX);
    }
    public static void main(String[] args) {
       Dungeon myDungeon= new Dungeon(5);
       System.out.println(myDungeon);
    }

}
