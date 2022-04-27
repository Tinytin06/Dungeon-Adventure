package Model;

public enum RoomType {
    FIGHT('F'),
    PIT('P'),
    NORMAL('N'),
    PILLAR('I'),
    EXIT('X'),
    ENTRANCE('E'),
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
}
