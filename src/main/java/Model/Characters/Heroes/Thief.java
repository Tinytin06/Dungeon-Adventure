package Model.Characters.Heroes;/*
 * Varun Parbhakar
 *
 * TCSS-143
 * Heroes VS Monster (Dungeon DLC)
 */

import Model.Characters.DungeonCharacter;
import Model.Characters.Hero;

/**
 * This is a subclass of Hero
 * @author Varun Parbhakar
 */
public class Thief extends Hero {

    /**
     * This constructor initializes all of the values
     * for the Warrior character
     * @param theC_Name (The character name)
     */
    //ASK TOM ABOUT THIS CLASS BEING PUBLIC,

    //Ask him why does compile fail when this class is set to protected level
    //despite it being imported into the dungeon class.
    public Thief(final String theC_Name) {

        super(  theC_Name,
                75,
                6,
                20,
                40,
                .8,
                .4,
                .40,
                75,
                175,
                0,
                0);

    }
//NEED TO MAKE CONTROL AND VIEW METHODS FOR THIS METHOD
    /**
     * This is a overridden special attack method for this class.
     * @param theEnemy
     */
    protected String specialAttack(final DungeonCharacter theEnemy) {
        StringBuilder outputHelper = new StringBuilder();
        outputHelper.append("\n");
        outputHelper.append(getCharacter_Name());
        outputHelper.append(" prepared a sneak attack. \n");

        if (canUseSpecialAttack()) {

            int specialDamage = specialDamageGenerator();
            outputHelper.append("And has stunned ");
            outputHelper.append(theEnemy.getCharacter_Name()+".\n");
            outputHelper.append("Dealing ");
            outputHelper.append(specialDamage);
            outputHelper.append(" damage.\n");
            outputHelper.append(theEnemy.damageTaken(specialDamage));
            if(theEnemy.alive()) {
                outputHelper.append("attacking another time ");
                outputHelper.append(attacks(theEnemy));
            }

        } else {
            outputHelper.append("But was caught and lost his attack.\n");
        }

        return outputHelper.toString();
    }
}
