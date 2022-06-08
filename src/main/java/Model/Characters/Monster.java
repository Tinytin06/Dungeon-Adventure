package Model.Characters;

import java.io.Serial;

/**
 * This abstract class contains all the necessary instance methods
 * for all the characters that inherit from this Monster class.
 * @authors Varun Parbhakar, Austin Luu, Yacine Bennour.
 */
public abstract class Monster extends DungeonCharacter {

    @Serial
    private static final long serialVersionUID = 6586368167250317279L;

    private double myChance2Heal;
    private int myHeal_MinPoints;
    private int myHeal_MaxPoints;

    /**
     * returns the value of chance to heal when called
     * @return the value of chance to heal
     */
    public double getMyChance2Heal() {
        return myChance2Heal;
    }

    /**
     * Returns the minimum health points
     * @return the value of chance to heal
     */
    public int getMyHeal_MinPoints() {
        return myHeal_MinPoints;
    }

    /**
     * Returns the maximum health points
     * @return the value of chance to heal
     */
    public int getMyHeal_MaxPoints() {
        return myHeal_MaxPoints;
    }



    /**
     * This abstract method initializes the values for all for
     * Ogre, Skeleton, and Gremlin characters.
     * @param theC_Name (Character Name)
     * @param theC_Health (Character Health)
     * @param theC_AttackSpeed (Character Attack Speed)
     * @param theC_MinDamage (Character Minimum Damage)
     * @param theC_MaxDamage (Character Maximum Damage)
     * @param theC_Chance2Attack (Chance to attack)
     * @param theC_Chance2Heal (Chance to block)
     * @param theC_Heal_MinPoints (Minimum Damage for Special Attack)
     * @param theC_Heal_MaxPoints (Maximum Damage for Special Attack)
     */
    protected Monster(final String theC_Name,
                      final int theC_Health,
                      final int theC_AttackSpeed,
                      final int theC_MinDamage,
                      final int theC_MaxDamage,
                      final double theC_Chance2Attack,
                      final double theC_Chance2Heal,
                      final int theC_Heal_MinPoints,
                      final int theC_Heal_MaxPoints) {

        super(theC_Name,
                theC_Health,
                theC_AttackSpeed,
                theC_MinDamage,
                theC_MaxDamage,
                theC_Chance2Attack);

        chance2Heal_Validator(theC_Chance2Heal);
        if (healRangeValidator(theC_Heal_MinPoints, theC_Heal_MaxPoints)) {
            heal_MinPoints_Validator(theC_Heal_MinPoints);
            heal_MaxPoints_Validator(theC_Heal_MaxPoints);
        }
    }

    /**
     * This methods sets the health for the Monster characters.
     */
    protected void setMyHealth(final int theHP) {
        super.setMyCharacter_HealthPoints(theHP);
    }



    /**
     * This method takes in the minimum and maximum points and calculates
     * a value in between.
     * @return (An integer between the range of minimum and maximum points)
     */
    protected int healGenerator() {
        return damageRangeCalculator(myHeal_MinPoints, myHeal_MaxPoints);
    }

    /**
     * This is an overridden method which makes sure that a monster has
     * a chance to heal.
     * @param theC_Damage (The amount of damage that a character takes)
     */
    @Override
    public String damageTaken(final int theC_Damage) {
        StringBuilder outputHelper = new StringBuilder();
        outputHelper.append(super.damageTaken(theC_Damage));
        outputHelper.append(heal());
        return outputHelper.toString();
    }


    /**
     * This method calculates the chances of the monster
     * succeeding at healing himself.
     * @return String (How much the monster has healed himself)
     */
    protected String heal() {

        StringBuilder outputHelper = new StringBuilder();

        if (alive()) {
            if(myChance2Heal >= randomGen.nextDouble()) {
                outputHelper.append("\n");
                outputHelper.append("The ");
                outputHelper.append(getCharacter_Name());
                outputHelper.append(" has healed himself.");
                outputHelper.append("\n");
                setMyHealth(healGenerator());

            } else {
                outputHelper.append("\n");
                outputHelper.append(getCharacter_Name());
                outputHelper.append(" tried to heal themselves but failed.");
                outputHelper.append("\n");
            }

            return outputHelper.toString();
        }

        return "";
    }

    /**
     * This method is used for the characters to attack.
     * @param theEnemy (The enemy player)
     */
    @Override
    public String attacks(final DungeonCharacter theEnemy){
        if (theEnemy == null) {
            throw new IllegalArgumentException("The passed enemy is set to null");
        }

        StringBuilder myReturn = new StringBuilder();
        int myNumberOfAttacks = (getCharacter_AttackSpeed() / theEnemy.getCharacter_AttackSpeed());
        if (myNumberOfAttacks == 0) {
            myNumberOfAttacks = 1;
        }



        while (myNumberOfAttacks > 0 && theEnemy.alive() ) {
            myReturn.append(super.attacks(theEnemy));
            myNumberOfAttacks--;

        }
        return myReturn.toString();
    }



    /**
     * This method validates the chance to heals.
     * @param theC_Chance2Heal
     */
    protected final void chance2Heal_Validator(final double theC_Chance2Heal) {
        if (theC_Chance2Heal > 1.0 || theC_Chance2Heal < 0.0) {
            throw new IllegalArgumentException("Chance to heal cannot be less 0 or greater than 1");
        }
        myChance2Heal = theC_Chance2Heal;
    }


    /**
     * This method validates the minimum special heal points.
     * @param theC_Heal_MinPoints
     */
    protected final void heal_MinPoints_Validator(final int theC_Heal_MinPoints) {
        if (theC_Heal_MinPoints <= 0.0) {
            throw new IllegalArgumentException("The minimum special set for the character has to be greater than 0.");
        }
        myHeal_MinPoints = theC_Heal_MinPoints;
    }

    /**
     * This method validates the maximum special heal points
     * @param theC_Heal_MaxPoints
     */
    protected final void heal_MaxPoints_Validator(final int theC_Heal_MaxPoints) {
        if (theC_Heal_MaxPoints <= 0.0) {
            throw new IllegalArgumentException("The maximum special points set for the character has to be greater than 0.");
        }
        myHeal_MaxPoints = theC_Heal_MaxPoints;
    }

    /**
     * This method validates the health regeneration range.
     * @param theC_SMinDamage
     * @param theC_SMaxDamage
     * @return
     */
    protected final boolean healRangeValidator(final int theC_SMinDamage,
                                               final int theC_SMaxDamage) {
        if (theC_SMinDamage > theC_SMaxDamage) {
            throw new IllegalArgumentException("The minimum special heal points cannot be greater than the maximum special heal points.");
        }
        return true;
    }
}

