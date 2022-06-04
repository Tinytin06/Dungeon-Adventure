package Control;

public enum Directions {
    NORTH("n"),SOUTH("s"),EAST("e"),WEST("w");

    public final String direction;

    Directions(String theDirection) {
        direction = theDirection;
    }

    public String getDirection() {
        return direction;
    }
}
