package Model;

public enum RoomType {
    FIGHT('F'),
    PIT('P'),
    NORMAL('N'),
    PILLAR('P'),
    EXIT('X'),
    ENTRANCE('E'),
    HEALING('H'),
    VISION('V');

    public final char type;

    RoomType(char theType) {
        type = theType;
    }

    public char getType() {
        return type;
    }
}
