package Model.Characters;/*
 * Varun Parbhakar
 *
 * TCSS-143
 * Heroes VS Monster (Dungeon DLC)
 */
import java.io.Serial;
import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;
/**
 * This abstract class is the parent class to Hero and Monster,
 * this class contains the very basic attributes of a Dungeon Character.
 * @author Varun Parbhakar
 */
public abstract class DungeonCharacter implements Serializable {
    // Scanner and Random number generator for all the subclasses.
    public final static Scanner userInput = new Scanner(System.in);
    protected final static Random randomGen = new Random();
    @Serial
    private static final long serialVersionUID = 8276876816569082427L;

    // Fields
    private String myCharacter_Name;


    private int myCharacter_HealthPoints;
    private int myCharacter_MaxHealthPoints;
    private int myCharacter_AttackSpeed;
    private int myCharacter_MinDamage;
    private int myCharacter_MaxDamage;

    public int getMyCharacter_MaxHealthPoints() {
        return myCharacter_MaxHealthPoints;
    }

    public int getMyCharacter_MinDamage() {
        return myCharacter_MinDamage;
    }

    public int getMyCharacter_MaxDamage() {
        return myCharacter_MaxDamage;
    }

    public double getMyCharacter_AttackDamageProbability() {
        return myCharacter_AttackDamageProbability;
    }

    public boolean isMyWinner() {
        return myWinner;
    }

    private double myCharacter_AttackDamageProbability;
    private boolean myWinner;


//SHOULD PROBABLY NOT HAVE EXCEPTIONS HERE IT IS A SMALL CODE SMELL
    /**
     * This constructor initializes the major fields that are required for a dungeon
     * character.
     * @param theC_Name (Name of the Character)
     * @param theC_Health (The health of the Character)
     * @param theC_AttackSpeed (The attack speed of Character)
     * @param theC_MinDamage (Minimum damage)
     * @param theC_MaxDamage (Max Damage)
     * @param theC_Chance2Attack (Attack probability)
     */
    protected DungeonCharacter(final String theC_Name,
                               final int theC_Health,
                               final int theC_AttackSpeed,
                               final int theC_MinDamage,
                               final int theC_MaxDamage,
                               final double theC_Chance2Attack) {

        nameValidator(theC_Name);
        healthValidator(theC_Health);
        attackSpeedValidator(theC_AttackSpeed);
        chance2AttackValidator(theC_Chance2Attack);

        if (damageRangeValidator(theC_MinDamage, theC_MaxDamage)) {
            minDamageValidator(theC_MinDamage);
            maxDamageValidator(theC_MaxDamage);
        } else {
            throw new IllegalArgumentException("The Minimum damage is greater than the Maximum damage");
        }

    }




    //Validators

    /**
     * This instance method validates the name of the character.
     * @param theC_Name (User passes character name)
     */
    protected final void nameValidator(final String theC_Name) {
        if (theC_Name == null || theC_Name.length() == 0) {
            throw new IllegalArgumentException("The name for this character is not valid.");
        }
        myCharacter_Name = theC_Name;
    }


    /**
     * This instance method validates the health of the character.
     * @param theC_Health (Health of the character)
     */
    protected final void healthValidator(final int theC_Health) {
        if (theC_Health <= 0) {
            throw new IllegalArgumentException("The default character's health has to be greater than 0.");
        }
        myCharacter_HealthPoints = theC_Health;
        myCharacter_MaxHealthPoints = theC_Health;
    }


    /**
     * This instance method validates the minimum damage of the character.
     * @param theC_MinDamage (Minimum damage of the character)
     */
    protected final void minDamageValidator(final int theC_MinDamage) {
        if (theC_MinDamage <= 0) {
            throw new IllegalArgumentException("The minimum damage points set for the character has to be greater than 0.");
        }
        myCharacter_MinDamage = theC_MinDamage;
    }


    /**
     * This instance method validates the maximum damage of the character.
     * @param theC_MaxDamage (Maximum damage of the character)
     */
    protected final void maxDamageValidator(final int theC_MaxDamage) {
        if (theC_MaxDamage <= 0) {
            throw new IllegalArgumentException("The maximum damage points set for the character has to be greater than 0.");
        }
        myCharacter_MaxDamage = theC_MaxDamage;
    }


    /**
     * This instance method ensure the minimum damage is not higher than the maximum damage
     * @param theC_MinDamage
     * @param theC_MaxDamage
     * @return boolean (Returns if the minimum is less than maximum damage)
     */
    protected final boolean damageRangeValidator(final int theC_MinDamage,
                                                 final int theC_MaxDamage) {
        if (theC_MinDamage > theC_MaxDamage) {
            throw new IllegalArgumentException("The minimum damage cannot be greater than the maximum damage.");
        } else {
            return true;
        }
    }


    /**
     * This instance method validates the attack speed of the character.
     * @param theC_AttackSpeed (Attack speed of the character)
     */
    protected final void attackSpeedValidator(final int theC_AttackSpeed) {
        if (theC_AttackSpeed <= 0) {
            throw new IllegalArgumentException("The attack speed of the character cannot be less than 0.");
        }
        myCharacter_AttackSpeed = theC_AttackSpeed;
    }



    /**
     * This instance method validates the chance to attack.
     * @param theC_Chance2Attack (Chance to attack for the character)
     */
    protected final void chance2AttackValidator(final double theC_Chance2Attack) {
        if (theC_Chance2Attack <= 0 || theC_Chance2Attack > 1) {
            throw new IllegalArgumentException("The chance to attack has to be greater than 0 and less than or equal to 1.");
        }
        myCharacter_AttackDamageProbability = theC_Chance2Attack;
    }
    //END OF VALIDATION


    // GETTERS

    /**
     * This gets and returns character name.
     * @return (Character's name)
     */
    public final String getCharacter_Name() {
        return myCharacter_Name;

    }
    /**
     * This gets and returns the character's health points.
     * @return
     */
    public final int getCharacter_HealthPoints() {
        return myCharacter_HealthPoints;

    }
    /**
     * This gets and returns the character's attack speed.
     * @return
     */
    public final int getCharacter_AttackSpeed() {
        return myCharacter_AttackSpeed;

    }
    /**
     * This gets and returns if the character has been declared a winner.
     * This method is ran towards the end of the game.
     * @param enemy (Enemy character)
     * @return (Return True if character has defeated the enemy)
     */
    protected final boolean isWinner(DungeonCharacter enemy) {
        determineWinner(enemy);
        return myWinner;
    }


    /**
     * This instance method returns a value between the given minimum and maximum damage.
     * @param theC_MaxDamage (Max Damage)
     * @param theC_MinDamage (Min Damage)
     * @return int (Damage amount between the given range)
     */
    protected static int damageRangeCalculator(final int theC_MinDamage,
                                               final int theC_MaxDamage) {
        return theC_MinDamage + randomGen.nextInt(theC_MaxDamage - theC_MinDamage + 1);
    }
    /**
     * This instance method addition to damageRangeCalculator(),
     * this method simply calls damageRangeCalculator with passing any values.
     * @return int (Damage amount between the given range)
     */
    protected int damageGenerator() {
        return DungeonCharacter.damageRangeCalculator(myCharacter_MinDamage, myCharacter_MaxDamage);
    }

    /**
     * This instance method return if the character is alive or not.
     * @return
     */
    public final boolean alive() {
        return (myCharacter_HealthPoints > 0);
    }
    //END OF GETTERS

    /**
     * This instance method adds to the character's HP.
     * @param theHP (Health points of the character)
     */
    protected void setMyCharacter_HealthPoints(final int theHP) {
        if(myCharacter_HealthPoints + theHP <= myCharacter_MaxHealthPoints)
            myCharacter_HealthPoints += theHP;
        else{
            myCharacter_HealthPoints = myCharacter_MaxHealthPoints;
        }
    }

    /**
     * This instance method kills the character.
     */
    public final void killCharacter() {
        myCharacter_HealthPoints = 0;
    }

//NEEDS TO MAKE CONTROL AND VIEW METHODS FOR THIS METHOD
    /**
     * This instance method runs to check if a cheat is active.
     */
    protected final void isCheatActive(){
        if(getCharacter_Name().equals("Jesus")){
            myCharacter_HealthPoints = 1000101;
            System.out.println("\t-----CHEAT ACTIVATED-----"); //
            System.out.println("\t\tPraise the LORD!!!!");

        }
    }

//NEED TO MAKE CONTROL AND VIEW METHODS FOR THIS METHOD
    // Character Actions
    /**
     * This instance method is used for attacking the enemy player.
     * @param enemy (The enemy player)
     */
    protected String attacks(final DungeonCharacter enemy) {


        StringBuilder outputHelper = new StringBuilder();
        if (alive()) {
            double randomDouble = randomGen.nextDouble();
            if (randomDouble <= myCharacter_AttackDamageProbability) {
                int damageDone = damageGenerator();
                outputHelper.append("\n");
                outputHelper.append(getCharacter_Name());
                outputHelper.append(" attacks ");
                outputHelper.append(enemy.getCharacter_Name());

                outputHelper.append(enemy.damageTaken(damageDone));
            } else {
                outputHelper.append("\n");
                outputHelper.append(getCharacter_Name());
                outputHelper.append("'s attacked on ");
                outputHelper.append(enemy.getCharacter_Name());
                outputHelper.append(" but missed.\n");
            }
        }

        return outputHelper.toString();
    }

//NEED TO MAKE CONTROL AND VIEW METHODS FOR THIS METHOD
    /**
     * This instance method makes sure that damage is dealt to the character.
     * @param theC_Damage (The amount of damage that a character takes)
     */
    public String damageTaken(final int theC_Damage) {
        if (theC_Damage < 0) {
            throw new IllegalArgumentException("The chance to attack has to be greater than 0 and less than or equal to 1.");
        }

        StringBuilder outputHelper = new StringBuilder();

        outputHelper.append("\n");
        outputHelper.append(getCharacter_Name());
        outputHelper.append(" Has taken <<");
        outputHelper.append(theC_Damage);
        outputHelper.append(">> damage");

        myCharacter_HealthPoints -= theC_Damage;

        if (myCharacter_HealthPoints <= 0) {
            killCharacter();
            outputHelper.append("\n");
            outputHelper.append(getCharacter_Name());
            outputHelper.append(" has fainted\n");
        } else {
            outputHelper.append("\n");
            outputHelper.append(getCharacter_Name());
            outputHelper.append("'s health is now at <<");
            outputHelper.append(myCharacter_HealthPoints);
            outputHelper.append(">>\n");
        }

        //"\n" + getCharacter_Name() + " Has taken <<" + theC_Damage + ">> damage \n+ System.out.println(getCharacter_Name() + " has fainted\n");"

//        return true;
        //DOUBLE HCECK THIS RETURN STAMENT!!!
        return outputHelper.toString();
    }

    /**
     * This instance method determine if the character is a winner or not.
     * @param enemy (The enemy character)
     */
    private void determineWinner(final DungeonCharacter enemy) {
        if (!enemy.alive()) {
            myWinner = true;
        }
    }

}
//END
