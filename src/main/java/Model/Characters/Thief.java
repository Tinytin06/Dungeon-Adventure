package Model.Characters;/*
 * Varun Parbhakar
 *
 * TCSS-143
 * Heroes VS Monster (Dungeon DLC)
 */
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
     * @param enemy
     */
    protected String specialAttack(final DungeonCharacter enemy) {
        StringBuilder outputHelper = new StringBuilder();
        outputHelper.append("\n");
        outputHelper.append(getCharacter_Name());
        outputHelper.append(" russeled his bones \n");
        if (canUseSpecialAttack()) {
            int specialDamage = specialDamageGenerator();
            outputHelper.append("and has hit ");
            outputHelper.append(enemy.getCharacter_Name());
            outputHelper.append(" with his spine ");
            outputHelper.append(" and caused  ");
            outputHelper.append(specialDamage);
            outputHelper.append(" damage.\n");

            outputHelper.append(enemy.damageTaken(specialDamage));
        } else {
            outputHelper.append("but they failed to put them back together.\n");
        }

        return outputHelper.toString();
    }
}
//END