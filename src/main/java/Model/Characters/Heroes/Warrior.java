package Model.Characters.Heroes;/*
 * Varun Parbhakar
 *
 * TCSS-143
 * Heroes VS Monster (Dungeon DLC)
 */

import Model.Characters.DungeonCharacter;
import Model.Characters.Hero;

import java.io.Serial;
import java.io.Serializable;

/**
 * This is a subclass of Hero
 * @author Varun Parbhakar
 */
public class Warrior extends Hero implements Serializable {


    @Serial
    private static final long serialVersionUID = -7581078960929808165L;

    /**
     * This constructor initializes all of the values
     * for the Warrior character
     * @param theC_Name (The character name)
     */
    //ASK TOM ABOUT THIS CLASS BEING PUBLIC,

    //Ask him why does compile fail when this class is set to protected level
    //despite it being imported into the dungeon class.
    public Warrior(final String theC_Name) {

        super(  theC_Name,
                125,
                4,
                35,
                60,
                .8,
                .2,
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
    @Override
    public String specialAttack(final DungeonCharacter theEnemy) {
        StringBuilder outputHelper = new StringBuilder();
        outputHelper.append("\n");
        outputHelper.append(getCharacter_Name());
        outputHelper.append(" tried to use the Zeus's Electro-Statico\n");
        if (canUseSpecialAttack()) {
            int specialDamage = specialDamageGenerator();
            outputHelper.append("and has zapped ");
            outputHelper.append(theEnemy.getCharacter_Name());
            outputHelper.append(" with ");
            outputHelper.append(specialDamage);
            outputHelper.append(" electrons.\n");

            outputHelper.append(theEnemy.damageTaken(specialDamage));
        } else {
            outputHelper.append("but Zeus said NO!, special attack has failed.\n");
        }

        return outputHelper.toString();
    }
}
