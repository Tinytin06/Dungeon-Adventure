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
public class Warrior extends Hero {

    /**
     * This constructor initializes all of the values
     * for the Warrior character
     * @param theC_Name (The character name)
     */
    //ASK TOM ABOUT THIS CLASS BEING PUBLIC,

    //Ask him why does compile fail when this class is set to protected level
    //despite it being imported into the dungeon class.
    protected Warrior(final String theC_Name) {

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
     * @param enemy
     */
    @Override
    protected void specialAttack(final DungeonCharacter enemy) {
        System.out.print(getCharacter_Name() + " tried to use the Zeus's Electro-Statico");
        if (canUseSpecialAttack()) {
            int specialDamage = specialDamageGenerator();
            System.out.println(" and has zapped " + enemy.getCharacter_Name() + " with " + specialDamage + " electrons.");
            enemy.damageTaken(specialDamage);
        } else {
            System.out.println("\nbut Zeus said NO!, special attack has failed.");
        }


    }
}
//END