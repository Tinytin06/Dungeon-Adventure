package Model.Characters.Heroes;

import Model.Characters.DungeonCharacter;
import Model.Characters.Hero;

import java.io.Serial;
import java.io.Serializable;

/**
 * This is a subclass of Hero Warrior that does massive damage and has differing
 * base stats than other Heroes
 *
 * @authors Varun Parbhakar, Austin Luu, Yacine Bennour
 * @version 06/07/2022
 * */
public class Warrior extends Hero implements Serializable {


    @Serial
    private static final long serialVersionUID = -7581078960929808165L;

    /**
     * This constructor initializes all the values
     * for the Warrior character
     * @param theC_Name (The character name)
     */

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
