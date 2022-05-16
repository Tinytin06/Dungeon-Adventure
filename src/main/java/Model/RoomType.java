package Model;

public enum RoomType {
    FIGHT('F'),
    PIT('P'),
    NORMAL('N'),
    PILLAR('I'),
    INHERITANCE('H'),
    ENCAPSULATION('C'),
    POLYMORPHISM('O'),
    ABSTRACTION('A'),
    EXIT('X'),
    ENTRANCE('E'),
    HEALING('H'),
    VISION('V'),
    PLAYER('*'),
    CODING_CROWN_1('K'),
    CODING_CROWN_2('Q');


    public final char type;

    RoomType(char theType) {
        type = theType;
    }

    public char getType() {
        return type;
    }

    public static String legend() {
        StringBuilder legend = new StringBuilder();

        for (RoomType roomType : RoomType.values()) {
                legend.append(roomType.type + ": " + roomType +", ");
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
//            } else if (type == RoomType.CODING_CROWN_1) {
//                legend.append("K: Coding Crown 1, ");
//            } else if (type == RoomType.CODING_CROWN_2) {
//                legend.append("Q: Coding Crown 2");
//            } else if (type == RoomType.POLYMORPHISM){
//
//            } else if (type == RoomType.ENCAPSULATION){
//
//            } else if (type == RoomType.ABSTRACTION){
//
//            } else if (type == RoomType.INHERITANCE){
//
//            }
        }
        return legend.toString() + ".";

    }
}
