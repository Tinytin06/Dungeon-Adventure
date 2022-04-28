package Model;

import Model.Characters.Monster;

import java.util.ArrayList;

public class Dungeon {
    private int myDungeonSize;
    private ArrayList<ArrayList<Room>> myDungeon;

    Dungeon(final int theDungeonLength) {
        myDungeonSize = theDungeonLength;
        myDungeon = new ArrayList<>();
        dungeonBuilder();
    }

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

    Room getContent(final int theY, final int theX) {
        return myDungeon.get(theY).get(theX);
    }
    public static void main(String[] args) {
       Dungeon myDungeon= new Dungeon(5);
        System.out.println(myDungeon);
    }

}
