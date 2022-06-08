package Model;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * an enumeration class that has all the types of rooms possible
 * @authors Varun Parbhakar, Austin Luu, Yacine Bennour.
 * @version 06/07/2022
 */
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

    /**
     * Constructor: Takes char and assigns it to the public char type.
     */
    RoomType(char theType) {
        type = theType;
    }

    /**
     * @return the type assigned.
     */
    public char getType() {
        return type;
    }


    /**
     * @return the pillar
     */
    public static ArrayList<RoomType> getMyPillars() {
        RoomType[] myPillars = {INHERITANCE, ENCAPSULATION, ABSTRACTION, POLYMORPHISM};
        return new ArrayList<>(Arrays.asList(myPillars));
    }


    /**
     * @return the legend of the map: the room type char with the the room type name.
     */
    public static String legend() {
        StringBuilder legend = new StringBuilder();

        for (RoomType roomType : RoomType.values()) {
            legend.append(roomType.type + ": " + roomType +", ");
        }
        return legend + ".";
    }
}
