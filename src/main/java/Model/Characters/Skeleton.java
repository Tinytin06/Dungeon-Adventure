package Model.Characters;/*
 * Varun Parbhakar
 *
 * TCSS-143
 * Heroes VS Monster (Dungeon DLC)
 */
/**
 * This is a subclass of Monster.
 * @author Varun Parbhakar
 */

public class Skeleton extends Monster {

    /**
     * This constructor initializes all of the values
     * for the Skeleton character
     * @param theC_Name (The character name)
     */

    //ASK TOM ABOUT THIS CLASS BEING PUBLIC,

    //Ask him why does compile fail when this class is set to protected level
    //despite it being imported into the dungeon class.
    public Skeleton(final String theC_Name) {
        super(theC_Name,
                100,
                3,
                30,
                50,
                .8,
                .3,
                30,
                50);
    }
}
//END