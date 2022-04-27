package Model;

public enum Directions {
    NORTH('n'),SOUTH('s'),EAST('e'),WEST('w');

    public final char direction;

    Directions(char theDirection) {
        direction = theDirection;
    }

    public char getDirection() {
        return direction;
    }
}
