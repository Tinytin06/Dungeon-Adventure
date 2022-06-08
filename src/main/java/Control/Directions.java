package Control;

/**
 * This is a Direction enum class made for the use in the dungeon adventure
 * and other control classes.
 * @authors Varun Parbhakar, Austin Luu, Yacine Bennour.
 * @version 06/07/2022
 */
public enum Directions {
    NORTH("n"),SOUTH("s"),EAST("e"),WEST("w");

    public final String direction;

    /**
     * Constructor for the Direction enum class.
     * @param theDirection (name of Direction heading)
     */
    Directions(String theDirection) {
        direction = theDirection;
    }

}
