package Model.Characters.Heroes;

import Model.Characters.DungeonCharacter;
import Model.Characters.Hero;

import java.io.Serial;
import java.io.Serializable;

/**
 * This is a subclass of Hero priestess that heals and has differing
 * base stats than other Heroes
 *
 * @authors Varun Parbhakar, Austin Luu, Yacine Bennour
 * @version 06/07/2022
 * */
public class Priestess extends Hero implements Serializable {

    @Serial
    private static final long serialVersionUID = -7378349643693392625L;

    /**
     * This constructor initializes all the values
     * for the Priestess character
     * @param theC_Name (The character name)
     */

    public Priestess(final String theC_Name) {

        super(  theC_Name,
                75,
                5,
                25,
                45,
                .7,
                .3,
                .40,
                75,
                175,
                0,
                0);

    }

    /**
     * This is a overridden special attack method for this class.
     * @param theEnemy (the dungeon character that the hero is fighting)
     */
    @Override
    protected String specialAttack(final DungeonCharacter theEnemy) {

        StringBuilder outputHelper = new StringBuilder();

        outputHelper.append(getCharacter_Name());
        outputHelper.append(" initiated heal. \n");

        if (canUseSpecialAttack()) {
            int healingPoints = specialDamageGenerator();
            super.setMyCharacter_HealthPoints(healingPoints);
            outputHelper.append(" The gods have blessed her with their healing magic.\n");
            outputHelper.append("healing " + healingPoints + " Health.");

        } else {
            outputHelper.append("\nbut she forgot the spell.\n");
        }

        return outputHelper.toString();
    }
}