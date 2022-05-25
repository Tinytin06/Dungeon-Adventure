package Model;

import java.util.ArrayList;
import java.util.Arrays;


public enum RoomType {
    FIGHT('F'),
    PIT('P'),
    NORMAL('N'),
    INHERITANCE('I'),
    ENCAPSULATION('C'),
    POLYMORPHISM('O'),
    ABSTRACTION('A'),
    EXIT('X'),
    ENTRANCE('E'),
    HEALING('H'),
    VISION('V'),
    PLAYER('*');
//    CODING_CROWN_1('K'),
//    CODING_CROWN_2('Q');


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
            //does the same as this
//            if (type == RoomType.FIGHT) {
//                legend.append("F: Monster, ");
//            } else if (type == RoomType.PIT) {
//                legend.append("P: Pit, ");
//            } else if (type == RoomType.NORMAL) {
//                legend.append("N: Empty Room, ");
//            } else if (type == RoomType.PILLAR) {
//                legend.append("P: Pillar, ");
//            } else if (type == RoomType.EXIT) {
//                legend.append("X: Exit, ");
//            } else if (type == RoomType.ENTRANCE) {
//                legend.append("E: Entrance, ");
//            } else if (type == RoomType.HEALING) {
//                legend.append("H: Healing, ");
        }
        return legend.toString() + ".";
    }

}
