package Model;

import java.util.ArrayList;
import java.util.Arrays;


public enum RoomType {
    FIGHT('F'),
    PIT('X'),
    NORMAL('N'),
    INHERITANCE('I'),
    ENCAPSULATION('E'),
    POLYMORPHISM('P'),
    ABSTRACTION('A'),
    EXIT('O'),
    ENTRANCE('i'),
    HEALING('H'),
    VISION('V'),
    PLAYER('*');



    public final char type;

    RoomType(char theType) {
        type = theType;
    }

    public char getType() {
        return type;
    }

    public static ArrayList<RoomType> getMyPotions() {
        RoomType[] myPotions = {HEALING, VISION};

        return new ArrayList<>(Arrays.asList(myPotions));
    }

    public static ArrayList<RoomType> getMYPillars() {
        RoomType[] myPillars = {INHERITANCE, ENCAPSULATION, ABSTRACTION, POLYMORPHISM};
        return new ArrayList<>(Arrays.asList(myPillars));
    }


    public static String legend() {
        StringBuilder legend = new StringBuilder();

        for (RoomType roomType : RoomType.values()) {
            legend.append(roomType.type + ": " + roomType +", ");

        }
        return legend.toString() + ".";
    }

}
