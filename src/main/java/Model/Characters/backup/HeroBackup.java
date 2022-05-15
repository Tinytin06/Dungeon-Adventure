package Model.Characters.backup;

import Model.Characters.DungeonCharacter;
import Model.RoomType;

import java.awt.*;
import java.util.ArrayList;

public abstract class HeroBackup extends DungeonCharacter {
    private double myChance2Block;
    private double mySpecialSkillChance;
    private int mySpecialSkill_MinDamage;
    private int mySpecialSkill_MaxDamage;
    private boolean myRunAway;
    private int myNumberOfAttacks;
    private int myCharacterLocationX;
    private int myCharacterLocationY;
    private ArrayList<Character> mySatchel = new ArrayList<>();
    private int myHealingPotions;
    private int myVisionPotions;
    private int myCrownPieces;
    private int myPillarPieces;

    private static final int SPECIAL_ATTACK = 1;
    private static final int NORMAL_ATTACK = 2;

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
    protected HeroBackup(String theC_Name,
                   int theC_Health,
                   int theC_AttackSpeed,
                   int theC_MinDamage,
                   int theC_MaxDamage,
                   double theC_Chance2Attack,
                   double theC_Chance2Block,
                   double theC_SpecialSkill_Chance,
                   int theC_SpecialSkill_MinDamage,
                   int theC_SpecialSkill_MaxDamage,
                   int theCharacterX,
                   int theCharacterY) {

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

//        setCharacterLocation(theCharacterX,theCharacterY);
        myCharacterLocationX = theCharacterX;
        myCharacterLocationY = theCharacterY;


        myHealingPotions = 0;
        myVisionPotions = 0;
        myCrownPieces = 0;
        myPillarPieces = 0;


    }

    // Getters


//    /**
//     * This methods sets the health for the hero characters.
//     */
//    protected void setMyHealth(final int theHP) {
//        super.setMyCharacter_HealthPoints(theHP);
//    }


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
    public void healingPotion() {
        super.setMyCharacter_HealthPoints(randomGen.nextInt(20));
    }

    /**
     * This method generates a random value and reduces that value from the
     * character's healthpoints.
     */
    public void heroTakesDamage() {
        super.setMyCharacter_HealthPoints(-randomGen.nextInt(30));
    }

    /**
     * This is an overridden method which makes sure that a hero has
     * a chance to block.
     * @param theC_Damage (The amount of damage that a character takes)
     */
    @Override
    public boolean damageTaken(final int theC_Damage) {
        if (randomGen.nextDouble() <= myChance2Block) {
            System.out.println((getCharacter_Name() + " blocked the attack!\n"));
            return false;

        } else {
            super.damageTaken(theC_Damage);
            return true;

        }
    }

    /**
     * This is a special attack method which is depended on the type of
     * character that decides on using a special attack
     * @param enemy
     */
    protected abstract void specialAttack(final DungeonCharacter enemy);

    /**
     * This method is used for the characters to attack.
     * @param theEnemy (The enemy player)
     */


    /**
     * This method is used for the characters to attack.
     * @param theEnemy (The enemy player)
     */

// add parameter fo attack choice in here
//    public void attacks(final DungeonCharacter theEnemy) {
//        int attackChoice = 0;
//        int tooLowOfAttackSpeedRatio= 0;
//        int fixesLowRatio = 1;
//        myNumberOfAttacks = (getCharacter_AttackSpeed() / theEnemy.getCharacter_AttackSpeed());
//        if (myNumberOfAttacks == tooLowOfAttackSpeedRatio) {
//            myNumberOfAttacks = fixesLowRatio;
//        }
//
//// WE NEED TO ADD CHANGE THIS WHILE LOOP TO DUNGEON ADVENTURE TOO
//        while (myNumberOfAttacks > 0 && alive() && theEnemy.alive() && !myRunAway) {
//
//// this check is for when we test this method
//            if (theEnemy == null) {
//                throw new IllegalArgumentException("The passed enemy is set to null");
//            }
//
//// ERROR attackChoiceValidator will be moved to the DungeonAdeventure class.
//            attackChoice = attackChoiceValidator();
////            attackChoice = attackChoiceValidator();
//            if (attackChoice == SPECIAL_ATTACK) {
//                specialAttack(theEnemy);
//            } else if (attackChoice == NORMAL_ATTACK) {
//                super.attacks(theEnemy);
//            } else {
//                runAway();
//            }
//            myNumberOfAttacks--;
//
//        }
//
//    }
//
//// this will get moved to dunegon adventure when its ready
//// we will validate the attack there before we call the attack function.
//    /**
//     * This method insures that the user selects the right attack
//     * and also performs input validation.
//     * @return (Corresponding number related to the attack choice)
//     */
//    protected final int attackChoiceValidator() {
//        int selection = 0;
//        //maybe pull out choices string and sout to the view and control parts of the method
//        String choices = (getCharacter_Name() + " select your attack:" +
//                "\n1: Special Attack" +
//                "\n2: Normal Attack" +
//                "\n3: Run Away");
//        while (selection == 0) {
//            System.out.println(choices);
//            if (userInput.hasNextInt()) {
//
//                selection = userInput.nextInt();
//                if (selection <= 0 || selection > 3) {
//                    System.out.println("Invalid Choice");
//                }
//            } else {
//                System.out.println("Invalid Choice");
//                userInput.next();
//            }
//        }
//
//        return selection;
//    }



// add parameter fo attack choice in here

    public void attacks(final DungeonCharacter theEnemy, final int attackChoice) {

// WE NEED TO ADD CHANGE THIS WHILE LOOP TO DUNGEON ADVENTURE TOO
//        while () {

// this check is for when we test this method
        if (theEnemy == null) {
            throw new IllegalArgumentException("The passed enemy is set to null");
        }

// ERROR attackChoiceValidator will be moved to the DungeonAdeventure class.
//            attackChoice = attackChoiceValidator();

        if (attackChoice == SPECIAL_ATTACK) {
            specialAttack(theEnemy);
        } else if (attackChoice == NORMAL_ATTACK) {
            super.attacks(theEnemy);
        } else {
            runAway();
        }
        myNumberOfAttacks--;

    }

    public boolean canAttack(final DungeonCharacter theEnemy){
        int tooLowOfAttackSpeedRatio= 0;
        int fixesLowRatio = 1;
        myNumberOfAttacks = (getCharacter_AttackSpeed() / theEnemy.getCharacter_AttackSpeed());
        if (myNumberOfAttacks == tooLowOfAttackSpeedRatio) {
            myNumberOfAttacks = fixesLowRatio;
        }

//        return (myNumberOfAttacks > 0 && alive() && theEnemy.alive() && !myRunAway);
        return (alive() && theEnemy.alive() && !myRunAway);
    }



// this will get moved to dunegon adventure when its ready
// we will validate the attack there before we call the attack function.
    /**
     * This method insures that the user selects the right attack
     * and also performs input validation.
     * @return (Corresponding number related to the attack choice)
     */
//    protected final int attackChoiceValidator() {
//        int selection = 0;
//        //maybe pull out choices string and sout to the view and control parts of the method
//        String choices = (getCharacter_Name() + " select your attack:" +
//                "\n1: Special Attack" +
//                "\n2: Normal Attack" +
//                "\n3: Run Away");
//        while (selection == 0) {
//            System.out.println(choices);
//            if (userInput.hasNextInt()) {
//
//                selection = userInput.nextInt();
//                if (selection <= 0 || selection > 3) {
//                    System.out.println("Invalid Choice");
//                }
//            } else {
//                System.out.println("Invalid Choice");
//                userInput.next();
//            }
//        }
//
//        return selection;
//    }

    /**
     * This method calculates if the hero has a chance to use
     * its special attack.
     * @return (Returns if user can use a special attack)
     */
    protected final boolean canUseSpecialAttack() {
        return (mySpecialSkillChance >= randomGen.nextDouble());
    }

    /**
     * This method is used to add to number of attacks for
     * each characters.
     * @param theAttacks (The amount of attacks needed to be added)
     */
//    protected void setNumberOfAttacks(final int theAttacks) {
//        myNumberOfAttacks += theAttacks;
//    }


    /**
     * This method when called sets the myRunAway variable
     * to true, indicating the hero ran away.
     *
     */
    protected void runAway() {
        myRunAway = true;
        killCharacter();
        //System.out.println(getCharacter_Name() + " has ran away.");
    }



    public double getMyChance2Block() {
        return myChance2Block;
    }

    public double getMySpecialSkillChance() {
        return mySpecialSkillChance;
    }

    public int getMySpecialSkill_MinDamage() {
        return mySpecialSkill_MinDamage;
    }

    public int getMySpecialSkill_MaxDamage() {
        return mySpecialSkill_MaxDamage;
    }
    // Validators

    /**
     * This method validates the chance to block amount.
     * @param theC_Chance2Block
     */
    protected final void chance2Block_Validator(final double theC_Chance2Block) {
//        double greaterThan100Percent = 1.0;
//        double lessThan0Percent = 0.0;

// this next check if good for when we test the method.
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
// this next check if good for when we test the method.
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
// this next check if good for when we test the method.
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
        //Check param
        myCharacterLocationY += theY;
    }

    /**
     * This method sets the X coordinate of the dungeon character.
     * @param theX
     */
    public void translateCharacterX(final int theX) {
        //Check param
        myCharacterLocationX += theX;
    }

    /**
     * This method return the Y coordinate of the character.
     * @return
     */
    public int getCharacterLocationY() {
        return myCharacterLocationY;
    }

    /**
     * This method return the X coordinate of the character.
     * @return
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
     * This method adds the passed item to the hero's inventory.
     * @param theItem
     */
    public void addItem2Satchel(final RoomType theItem) {
        if(theItem.type == RoomType.CODING_CROWN_1.type
                ||theItem.type == RoomType.CODING_CROWN_2.type ) {
            addCrownPiece();
        } if(theItem.type == RoomType.HEALING.type) {
            addHealingPotion();
        } if(theItem.type == RoomType.VISION.type) {
            addVisionPotion();
        } if(theItem.type == RoomType.PILLAR.type) {
            addPillarPiece();
        }


        mySatchel.add(theItem.type);

    }

    public boolean satchelContains(RoomType theItem) {
        return mySatchel.contains(theItem.type);
    }




    /**
     * This method removes the passed item from the hero's inventory.
     * @param theItem
     */
    public void removeSatchelItem(final RoomType theItem) {
        mySatchel.remove(Character.valueOf(theItem.type));


    }

    /**
     * This method return the hero's inventory.
     * @return
     */
    public ArrayList<Character> getHeroSatchel(){
        return mySatchel;
    }





    /**
     * This method return a boolean to indicate if the hero has both of the crowns or not.
     * @return
     */
    public boolean hasBothCrowns() {
        return (mySatchel.contains(RoomType.CODING_CROWN_1.type) && mySatchel.contains(RoomType.CODING_CROWN_2.type));
    }

    /**
     * This methods adds 1 to the crown piece field.
     */
    protected void addCrownPiece(){
        myCrownPieces++;

    }

    /**
     * This methods adds 1 to the crown piece field.
     */
    protected void addPillarPiece(){
        myPillarPieces++;

    }

    /**
     * This methods adds 1 to the healing potion field.
     */
    protected void addHealingPotion() {
        myHealingPotions++;
    }

    /**
     * This methods adds 1 to the vision potion field.
     */
    protected void addVisionPotion(){
        myVisionPotions++;
    }



    public int getMyHealingPotions() {
        return myHealingPotions;
    }

    public int getMyVisionPotions() {
        return myVisionPotions;
    }

    public int getMyCrownPieces() {
        return myCrownPieces;
    }

    public int getMyPillarPieces() {
        return myPillarPieces;
    }



    public int getNumberOfAttacks(){
        return myNumberOfAttacks;
    }

    /**
     * This is toString method for the hero which prints out the health, vision potion,
     * healing potions and coding crown.
     * @return String (Stats)
     */
// send this to console output when we are ready.
    public String toString() {
        String stats = ("Name: " + getCharacter_Name()
                + "\nHealth: " + super.getCharacter_HealthPoints()
                + "\nVision Potions found: " + myVisionPotions
                + "\nHealing Potions found: " + myHealingPotions
                + "\nCoding Crowns found: " + myCrownPieces);
        return stats;
    }
}
