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
public class Priestess extends Hero  {

    /**
     * This constructor initializes all of the values
     * for the Warrior character
     * @param theC_Name (The character name)
     */
    //ASK TOM ABOUT THIS CLASS BEING PUBLIC,

    //Ask him why does compile fail when this class is set to protected level
    //despite it being imported into the dungeon class.
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
//NEED TO MAKE CONTROL AND VIEW METHODS FOR THIS METHOD
    /**
     * This is a overridden special attack method for this class.
     * @param enemy
     */
    @Override
    protected String specialAttack(final DungeonCharacter enemy) {
        StringBuilder outputHelper = new StringBuilder();

        outputHelper.append(getCharacter_Name());
        outputHelper.append(" initiated heal. \n");
        if (canUseSpecialAttack()) {
            int healingPoints = specialDamageGenerator();
            super.setMyCharacter_HealthPoints(healingPoints);
            outputHelper.append(" The gods have blessed her with their healing magic.\n");
            outputHelper.append("healing " +healingPoints+ " Health.");

        } else {
            outputHelper.append("\nbut she forgot the spell.\n");
        }

        return outputHelper.toString();
    }
}
//END