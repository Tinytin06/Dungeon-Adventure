package Model.Characters;


import Model.RoomType;

import java.awt.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * This abstract class contains all the necessary instance methods
 * for all the character that inherit from this hero class.
 * @authors Varun Parbhakar, Austin Luu, Yacine Bennour.
 * @version 06/07/2022
 */
public abstract class Hero extends DungeonCharacter implements Serializable {

    @Serial
    private static final long serialVersionUID = -3591781281869975096L;

    private double myChance2Block;
    private double mySpecialSkillChance;
    private int mySpecialSkill_MinDamage;
    private int mySpecialSkill_MaxDamage;
    private boolean myRunAway;
    private int myNumberOfAttacks;
    private int myCharacterLocationX;
    private int myCharacterLocationY;
    private ArrayList<Character> mySatchel;
    private int myHealingPotions;
    private int myVisionPotions;
    private int myPillarPieces;

    private final int SPECIAL_ATTACK = 1;
    private final int NORMAL_ATTACK = 2;

    /**
     * This abstract method initializes the values for all for
     * Warrior, Sorceress, and thief characters.
     * @param theC_Name (Character Name)
     * @param theC_Health (Character Health)
     * @param theC_AttackSpeed (Character Attack Speed)
     * @param theC_MinDamage (Character Minimum Damage)
     * @param theC_MaxDamage (Character Maximum Damage)
     * @param theC_Chance2Attack (Chance to attack)
     * @param theC_Chance2Block (Chance to block)
     * @param theC_SpecialSkill_Chance (Chance for Special Attack)
     * @param theC_SpecialSkill_MinDamage (Minimum Damage for Special Attack)
     * @param theC_SpecialSkill_MaxDamage (Maximum Damage for Special Attack)
     */
    protected Hero(final String theC_Name,
                   final int theC_Health,
                   final int theC_AttackSpeed,
                   final int theC_MinDamage,
                   final int theC_MaxDamage,
                   final double theC_Chance2Attack,
                   final double theC_Chance2Block,
                   final double theC_SpecialSkill_Chance,
                   final int theC_SpecialSkill_MinDamage,
                   final int theC_SpecialSkill_MaxDamage,
                   final int theCharacterX,
                   final int theCharacterY) {

        super(theC_Name,
                theC_Health,
                theC_AttackSpeed,
                theC_MinDamage,
                theC_MaxDamage,
                theC_Chance2Attack);

        chance2Block_Validator(theC_Chance2Block);
        specialSkill_Chance_Validator(theC_SpecialSkill_Chance);

        if (sSkillDamageRangeValidator(theC_SpecialSkill_MinDamage, theC_SpecialSkill_MaxDamage)) {
            specialSkill_MinDamage_Validator(theC_SpecialSkill_MinDamage);
            specialSkill_MaxDamage_Validator(theC_SpecialSkill_MaxDamage);
        }

        myCharacterLocationX = theCharacterX;
        myCharacterLocationY = theCharacterY;
        mySatchel = new ArrayList<>();


        myHealingPotions = 0;
        myVisionPotions = 0;
        myPillarPieces = 0;


    }
    public Hero(){
        super("",0,0,0,0,0);
    }


    /**
     * This method takes in the minimum and maximum damage and calculates
     * a value in between.
     * @return (An integer between the range of minimum and maximum damage)
     */
    protected int specialDamageGenerator() {
        return damageRangeCalculator(mySpecialSkill_MinDamage, mySpecialSkill_MaxDamage);
    }

    /**
     * This method generates a random value and heals the player accordingly.
     */
    public void useHealingPotion() {
        super.setMyCharacter_HealthPoints(randomGen.nextInt(20));
    }

    /**
     * This method generates a random value and reduces that value from the
     * character's health points.
     */
    public void heroTakesDamage() {
        super.setMyCharacter_HealthPoints(-randomGen.nextInt(20)+1);
    }

    /**
     * This is an overridden method which makes sure that a hero has
     * a chance to block.
     * @param theC_Damage (The amount of damage that a character takes)
     */
    @Override
    public String damageTaken(final int theC_Damage) {
        if (randomGen.nextDouble() <= myChance2Block) {
            return ("\n"+ getCharacter_Name() + " blocked the attack!\n");
        } else {
            return super.damageTaken(theC_Damage);
        }
    }

    /**
     * This is a special attack method which is depended on the type of
     * character that decides on using a special attack
     * @param enemy
     */
    protected abstract String specialAttack(final DungeonCharacter enemy);

    /**
     * This method is used for the characters to attack.
     * @param theEnemy (The enemy player)
     */
    public String attacks(final DungeonCharacter theEnemy, final int attackChoice) {

        if(theEnemy == null) {
            throw new IllegalArgumentException("The passed enemy is set to null");
        }
        StringBuilder outputHelper = new StringBuilder();

        if (attackChoice == SPECIAL_ATTACK) {
            outputHelper.append(specialAttack(theEnemy));
        } else if (attackChoice == NORMAL_ATTACK) {
            outputHelper.append(super.attacks(theEnemy));
        } else {
            runAway();
        }
        myNumberOfAttacks--;

        return outputHelper.toString();
    }

    /**
     * This method is used to check if we can attack the enemy.
     * @param theEnemy (The enemy player)
     */
    public boolean canAttack(final DungeonCharacter theEnemy){
        return (myNumberOfAttacks > 0 && alive() && theEnemy.alive() && !myRunAway);
    }

    /**
     * before a fight if the attackspeed is greater than the 2ce enemy's then update # of attacks
     * @param theEnemy (the enemy that the hero is fighting)
     */
    public void resetAttackSpeed(final DungeonCharacter theEnemy){
        int tooLowOfAttackSpeedRatio = 0;
        int fixesLowRatio = 1;
        myNumberOfAttacks = (getCharacter_AttackSpeed() / theEnemy.getCharacter_AttackSpeed());
        if (myNumberOfAttacks == tooLowOfAttackSpeedRatio) {
            myNumberOfAttacks = fixesLowRatio;
        }
    }
    /**
     * This method calculates if the hero has a chance to use
     * its special attack.
     * @return (Returns if user can use a special attack)
     */
    protected final boolean canUseSpecialAttack() {
        return (mySpecialSkillChance >= randomGen.nextDouble());
    }



    /**
     * This method when called sets the myRunAway variable
     * to true, indicating the hero ran away.
     *
     */
    protected void runAway() {
        myRunAway = true;
        killCharacter();
    }

    /**
     * This method return the chance to block.
     * @return the chance to block
     */
    public double getMyChance2Block() {
        return myChance2Block;
    }

    /**
     * This method return the chance to use a special skill.
     * @return the chance to use a Special skill
     */
    public double getMySpecialSkillChance() {
        return mySpecialSkillChance;
    }

    /**
     * This method return the min damage.
     * @return the min amount of damage that a special skill deals/heals for
     */
    public int getMySpecialSkill_MinDamage() {
        return mySpecialSkill_MinDamage;
    }

    /**
     * This method return the max damage.
     * @return the max amount of damage that a special skill deals/heals for
     */
    public int getMySpecialSkill_MaxDamage() {
        return mySpecialSkill_MaxDamage;
    }

    /**
     * This method validates the chance to block amount.
     * @param theC_Chance2Block
     */
    protected final void chance2Block_Validator(final double theC_Chance2Block) {
        if (theC_Chance2Block > 1.0 || theC_Chance2Block < 0.0) {
            throw new IllegalArgumentException("Chance to block cannot be less 0 or greater than 1");
        }
        myChance2Block = theC_Chance2Block;
    }

    /**
     * This method valid the special skill chance.
     * @param theC_SpecialSkill_Chance
     */
    protected final void specialSkill_Chance_Validator(final double theC_SpecialSkill_Chance) {
        if (theC_SpecialSkill_Chance > 1.0 || theC_SpecialSkill_Chance <= 0.0) {
            throw new IllegalArgumentException("Special Skill cannot be less 0 or greater than 1");
        }
        mySpecialSkillChance = theC_SpecialSkill_Chance;
    }

    /**
     * This method validates special minimum damage.
     * @param theC_SpecialSkill_MinDamage
     */
    protected final void specialSkill_MinDamage_Validator(final int theC_SpecialSkill_MinDamage) {
        if (theC_SpecialSkill_MinDamage <= 0) {
            throw new IllegalArgumentException("The minimum damage points set for the character has to be greater than 0.");
        }
        mySpecialSkill_MinDamage = theC_SpecialSkill_MinDamage;
    }

    /**
     * This method validates the special maximum damage.
     * @param theC_SpecialSkill_MaxDamage
     */
    protected final void specialSkill_MaxDamage_Validator(final int theC_SpecialSkill_MaxDamage) {
        if (theC_SpecialSkill_MaxDamage <= 0) {
            throw new IllegalArgumentException("The maximum damage points set for the character has to be greater than 0.");
        }
        mySpecialSkill_MaxDamage = theC_SpecialSkill_MaxDamage;
    }

    /**
     * This method takes in the range of minimum and maximum value
     * and return the value in between.
     * @param theC_SMinDamage
     * @param theC_SMaxDamage
     * @return (the value in the given range)
     */
    protected final boolean sSkillDamageRangeValidator(final int theC_SMinDamage,
                                                       final int theC_SMaxDamage) {
        if (theC_SMinDamage > theC_SMaxDamage) {
            throw new IllegalArgumentException("The minimum special damage cannot be greater than the maximum special damage.");
        }

        return true;
    }

    /**
     * This method return the location of the character as a point object.
     * @return
     */
    public Point getCharacterLocation() {
        return new Point(myCharacterLocationX, myCharacterLocationY);
    }

    /**
     * This method sets the Y coordinate of the dungeon character.
     * @param theY
     */
    public void translateCharacterY(final int theY) {
        if(theY == -1 || theY == 1) {
            myCharacterLocationY += theY;
        } else {
            throw new IllegalArgumentException("Passed in Parameter is invalid (" + theY + "); range must be -1 or 1.");
        }
    }

    /**
     * This method sets the X coordinate of the dungeon character.
     * @param theX
     */
    public void translateCharacterX(final int theX) {
        if(theX == -1 || theX == 1) {
            myCharacterLocationX += theX;
        } else {
            throw new IllegalArgumentException("Passed in Parameter is invalid (" + theX + "); range must be -1 or 1.");
        }
    }

    /**
     * This method return the Y coordinate of the character.
     * @return the Y coordinate of the character
     */
    public int getCharacterLocationY() {
        return myCharacterLocationY;
    }

    /**
     * This method return the X coordinate of the character.
     * @return the X coordinate of the character
     */
    public int getCharacterLocationX() {
        return myCharacterLocationX;
    }


    /**
     * This method sets the location of the character.
     */
    public void setCharacterLocation(final Point theCharacterLocation ) {
        myCharacterLocationY = theCharacterLocation.y;
        myCharacterLocationX = theCharacterLocation.x;
    }

    /**
     * the method increments the healing potion values
     */
    public void incrementHealingPotion() { myHealingPotions++;}
    /**
     * the method increments the vision potion values
     */
    public void incrementVisionPotion() {
        myVisionPotions++;
    }

    /**
     * This method adds the passed item to the hero's inventory.
     * @param theItem
     */
    public void addItem2Satchel(final RoomType theItem) {
        for (RoomType type : RoomType.getMyPillars()) {
            if(type.type == theItem.type) {
                incrementPillars();
            }
        }
        if(theItem.type == RoomType.HEALING.type) {
            incrementHealingPotion();
        } else if(theItem.type == RoomType.VISION.type) {
            incrementVisionPotion();
        }

        mySatchel.add(theItem.type);
    }

    /**
     * returns if the character has a certain item in the inventory
     * @param theItem (the item that is being looked for)
     * @return if the item was in the inventory True else False
     */
    public boolean satchelContains(final RoomType theItem) { return mySatchel.contains(theItem.type);}

    /**
     * This method removes the passed item from the hero's inventory.
     * @param theItem (the item that we are trying to remove)
     */
    public void removeSatchelItem(final RoomType theItem) { mySatchel.remove(Character.valueOf(theItem.type));}


    /**
     * This method return the hero's inventory.
     * @return whatever is the hero's inventory
     */
    public ArrayList<Character> getHeroSatchel(){
        return mySatchel;
    }


    /**
     * This method return a boolean to indicate if the hero has collected all the pillars.
     * @return if the hero has collected all 4 Pillars of OO
     */
    public boolean hasCollectedEveryPillar() {
        return myPillarPieces == 4;
    }

    /**
     * returns the total # of healing potions we have found
     * @return total # of healing potions
     */
    public int getMyHealingPotions() { return myHealingPotions;}

    /**
     * returns the total # of vision potions we have found
     * @return total # of vision potions
     */
    public int getMyVisionPotions() {
        return myVisionPotions;
    }

    /**
     * returns the total # of attacks we have made
     * @return total # of attacks
     */
    public int getNumberOfAttacks(){
        return myNumberOfAttacks;
    }

    /**
     * This is toString method for the hero which prints out the health, vision potion,
     * healing potions and coding crown.
     * @return String (Stats)
     */
    public String toString() {
        String stats = ("Name: " + getCharacter_Name()
                + "\nHealth: " + super.getCharacter_HealthPoints()
                + "\nVision Potions found: " + myVisionPotions
                + "\nHealing Potions found: " + myHealingPotions
                + "\nPillars of OOP found: " + myPillarPieces);
        return stats;
    }

    /**
     * the method increments the pillars found
     */
    public void incrementPillars() {
        myPillarPieces++;
    }
}
