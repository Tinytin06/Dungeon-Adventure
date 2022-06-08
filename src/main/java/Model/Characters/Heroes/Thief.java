package Model.Characters.Heroes;

import Model.Characters.DungeonCharacter;
import Model.Characters.Hero;

import java.io.Serial;
import java.io.Serializable;

/**
 * This is a subclass of Hero Thief that Multiattacks and has differing
 * base stats than other Heroes
 *
 * @authors Varun Parbhakar, Austin Luu, Yacine Bennour
 * @version 06/07/2022
 * */
public class Thief extends Hero implements Serializable {

    @Serial
    private static final long serialVersionUID = 5280095350427436561L;

    /**
     * This constructor initializes all the values
     * for the Thief character
     * @param theC_Name (The character name)
     */
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
