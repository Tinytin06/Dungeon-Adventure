package Model;

public enum RoomType {
     FIGHT('F'), PIT('P'), NORMAL('N'), PILLAR('P');

    public final char type;
    RoomType(char theType) {
    type = theType;
    }

    public char getType() {
        return type;
    }
}
