/*
 * User Input validation -> Validating Directions, Validating Attack Choices, Validate Hero name, Validate Y&N
 *
 * HeroController -> playerSelectDirection, heroItemPicker,
 */



package Control;
import Model.Characters.Hero;
import Model.Characters.Monster;
import Model.Characters.Skeleton;
import Model.Characters.Warrior;
import Model.Dungeon;
import Model.Room;
import Model.RoomType;
import View.ConsoleOutput;


import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is the main client class for the dungeon adventure game it contains
 * the game player that initiates and continues with the game until the player decides to quit.
 * @author Varun Parbhakar
 */
public class DungeonAdventure {
    /**
     * This main method initializes the dungeon, hero and the monster to prepare the game.
     * @param args
     */
    public static void main(String[] args) {
        //Trying to fix the .class files

        Scanner userInput = new Scanner(System.in);
        ConsoleOutput.introduction();
        if(inputValidatorYN(userInput)) {
            boolean playAgain = false;
            while(!playAgain) {
                String name = userNameValidator(userInput);

                Hero hero = new Warrior(name);
                int myDungeonSize = 5;
                Dungeon myDungeon = new Dungeon(myDungeonSize);


                //Placing the hero into the dungeon entrance
                hero.setCharacterLocation(myDungeon.getEntrancePoint());

                // These are the names needs to be used in order to activate the cheat
                // I dont have a method in the Dungeon Adventure for revealing all of the
                // room, I thought that it better fit for the Dungeon class to have that kind of power.
                if (name.equals("Varun") || name.equals("Bryce")) {
                    myDungeon.setMyCheatEnabled();

                }
                myDungeon.setMyCheatEnabled();//testing purposes

                while(hero.alive()) {
                    driver(userInput, hero, myDungeonSize, myDungeon);

                }
                ConsoleOutput.printString("\nThis is the dungeon fully revealed\n");

                myDungeon.revealAll();

                ConsoleOutput.printString(myDungeon + "\n");
                ConsoleOutput.printString("\nWould you like to play again?\n");

                if (!inputValidatorYN(userInput)) {
                    playAgain = true;

                } else {
                    ConsoleOutput.printString("Thank you for playing!");

                }
            }
        } else {
            ConsoleOutput.printString("A wise choice, now MOVE ALONG!\n");

        }
    }


    /**
     * This method is responsible for making sure the user inputs a name for the hero,
     * this method is also performs input validation.
     *
     * @return String (The name of the hero)
     */
    public static String userNameValidator(final Scanner userInput) {
        boolean correctAnswer = false;
        String heroName = null;

        while (!correctAnswer) {
            ConsoleOutput.printString("Please enter a name for your hero: ");
            if (userInput.hasNextInt() || userInput.hasNextDouble()) {
                ConsoleOutput.printString("Invalid input\n");
                userInput.next();

            } else {
                heroName = userInput.next();
                correctAnswer = true;

            }
        }
        return heroName;

    }

    /**
     * This is the driver method which makes sure that the player is alive and
     * performs multiple room and dungeon check to main the player and room
     * inventory the with the use of other classes.
     * @param theUserInput (Scanner)
     * @param theHero (Hero)
     * @param theDungeonSize (Int Size of the dungeon)
     * @param theDungeon (Dungeon)
     */
    public static void driver(final Scanner theUserInput,
                              final Hero theHero,
                              final int theDungeonSize,
                              final Dungeon theDungeon) {

        // Checks if the cheat is enabled
        if (theDungeon.isCheatEnabled()) {
            theDungeon.revealAll();
        }
        if (theHero.alive()) {
            Point location = theHero.getCharacterLocation();
            Room myRoom = theDungeon.getContent(theHero.getCharacterLocationY(),theHero.getCharacterLocationX());

            myRoom.exploreTheRoom();
            myRoom.addTo_MyRoomInventory(RoomType.PLAYER); //Adding player to the room

            checkHeroSatchel(theHero, theDungeon);
            ConsoleOutput.printString(theDungeon + "\n");

            checkRoom(theHero, myRoom, theDungeon);
            ConsoleOutput.printString("This room has: \n");
            ConsoleOutput.printString(myRoom.showMyRoomInventory() + "\n");

            heroItemPicker(myRoom, theHero);

            //Checking if the hero is at the exit
            if (!isHeroAtExit(myRoom, theHero, theUserInput)){
                if (!theHero.alive()){
                    String output = "You came here with such life yet here you lie\n\t\tlifeless.\nBetter Luck Next time!";
                    ConsoleOutput.printString(output + "\n");

                } else {
                    ConsoleOutput.printString(theHero + "\n");
                    ConsoleOutput.printString(theHero.getCharacter_Name()+ "'s Inventory:");
                    ConsoleOutput.printString(theHero.getHeroSatchel() + "\n\n");

                    //Removing player from the Room
                    myRoom.removeMyTypes(RoomType.PLAYER);

                    //Selecting Player's next move
                    playerSelectDirection(location, theDungeonSize, theHero);

                }
            }


        }
    }

    public static void playerSelectDirection(final Point location, final int theDungeonSize, final Hero theHero) {
        Scanner theUserInput = new Scanner(System.in);
        String direction = directionChecker(theUserInput, location, theDungeonSize);

        if (direction.equals("k")){
            // Print the legend for dungeon
            ConsoleOutput.printString(RoomType.legend() + "\n");
        } else if (direction.equals("n")){
            theHero.translateCharacterY(-1);

        } else if (direction.equals("s")){
            theHero.translateCharacterY(1);

        } else if (direction.equals("e")){
            theHero.translateCharacterX(1);

        } else if (direction.equals("w")){
            theHero.translateCharacterX(-1);

        }
    }


    public static void checkHeroSatchel (final Hero theHero, final Dungeon theDungeon) {
        Scanner theUserInput = new Scanner(System.in);
        // Checking for hero's satchel
        if (theHero.satchelContains(RoomType.VISION) || theHero.satchelContains(RoomType.HEALING)) {
            ConsoleOutput.printString("You have unused potions, you can press 'y' to use your item 'n' for no\n");
            ConsoleOutput.printString("These are your available potions " + theHero.getHeroSatchel() + "\n");

            if (inputValidatorYN(theUserInput)){
                if (theHero.satchelContains(RoomType.VISION)) {
                    theDungeon.useVisionPotion(theHero.getCharacterLocation());
                    theHero.removeSatchelItem(RoomType.VISION);

                }
                if (theHero.satchelContains(RoomType.HEALING)) {
                    theHero.useHealingPotion();
                    theHero.removeSatchelItem(RoomType.HEALING);

                }
            }
        }
    }

    /**
     * This method checks to see if the hero is standing in a room with an exit.
     * This method also asks the player if they would like to exit the dungeon through
     * the door.
     * @param theRoom
     * @param theHero
     * @param theUserInput
     * @return (Boolean, if the player is on the exit or not)
     */
    public static boolean isHeroAtExit(final Room theRoom,
                                       final Hero theHero,
                                       final Scanner theUserInput) {
        if (theRoom.getMyRoomInventory().contains(RoomType.EXIT.type)) {
            ConsoleOutput.printString("You are on the exit!\n");

            //Whenever the two crowns are picked up, the hero is not recognizing that
            if (theHero.hasCollectedEveryPillar()) {
                ConsoleOutput.printString("Would you like to exit the dungeon?\n");

                if (inputValidatorYN(theUserInput)) {
                    ConsoleOutput.printString("\t\t\tYou did great out there!\n");
                    ConsoleOutput.printString("the programmer didn't even notice the missing coding crowns.\n");
                    theHero.killCharacter();
                    return true;

                }
            } else {
                ConsoleOutput.printString("You have to keep looking for more treasure!\n");
                return false;

            }
        }
        return false;
    }

    /**
     * This method asks the user for a yes or a no answer and insures the input is correct.
     * @param theUserInput (Scanner)
     * @return (Boolean YES or No)
     */
    public static boolean inputValidatorYN(final Scanner theUserInput) {
        boolean correctAnswer = false;
        String choice = null;
        while (!correctAnswer) {

            ConsoleOutput.printString("'y' for yes, 'n' for no: ");

            if (theUserInput.hasNext()) {
                choice = theUserInput.next();

                if (choice.equals("n") || choice.equals("y")) {
                    if (choice.equals("y")) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    ConsoleOutput.printString("Please select the correct response\n");

                }
            } else {
                ConsoleOutput.invalidInput();
                theUserInput.next();
            }
        }
        return false;
    }

    /**
     * This method takes the items from the room inventory and deposits the items into the hero's inventory.
     *
     * @param theRoom (The room object)
     * @param theHero (The hero object)
     */
    public static void heroItemPicker(final Room theRoom,
                                      final Hero theHero) {


        ArrayList<RoomType> deleteItems = new ArrayList<>();

//There needs to be a way to move the item from the room to the hero's satchel.


        //There is a chance that a single room will contain multiple items, so we use this structure.
        for( Character theItem : theRoom.getMyRoomInventory()) {
            if(RoomType.getMyPotions().contains(theItem)) {

            }


            if (theItem == RoomType.INHERITANCE.type){
                theHero.addItem2Satchel(RoomType.INHERITANCE);
                deleteItems.add(RoomType.INHERITANCE);
                ConsoleOutput.printString("You have collected the Inheritance Pillar!\n");

            } if (theItem == RoomType.ABSTRACTION.type){
                theHero.addItem2Satchel(RoomType.ABSTRACTION);
                deleteItems.add(RoomType.ABSTRACTION);
                ConsoleOutput.printString("You have collected the Abstraction Pillar!\n");

            } if (theItem == RoomType.POLYMORPHISM.type){
                theHero.addItem2Satchel(RoomType.POLYMORPHISM);
                deleteItems.add(RoomType.POLYMORPHISM);
                ConsoleOutput.printString("You have collected the Polymorphism Pillar!\n");

            } if (theItem == RoomType.ENCAPSULATION.type){
                theHero.addItem2Satchel(RoomType.ENCAPSULATION);
                deleteItems.add(RoomType.ENCAPSULATION);
                ConsoleOutput.printString("You have collected the Encapsulation Pillar!\n");

            } if (theItem == RoomType.HEALING.type){
                theHero.addItem2Satchel(RoomType.HEALING);
                deleteItems.add(RoomType.HEALING);
                ConsoleOutput.printString("You have picked up the Healing Potion!\n");

            } if (theItem == RoomType.VISION.type){
                theHero.addItem2Satchel(RoomType.VISION);
                deleteItems.add(RoomType.HEALING);
                ConsoleOutput.printString("You have picked up the Vision Potion!\n");

            }
        }

        for(RoomType item : deleteItems) {
            theRoom.removeMyTypes(item);
        }

    }

    /**
     * This method checks the room for monster and pits, if a monster is present
     * this method initiates a fight, if there is a pit then this method reduces player's
     * health to accommodate for the fall.
     * @param theHero
     * @param theRoom
     * @param theDungeon
     * @return (Returns an updated list of the room)
     */
    public static void checkRoom(final Hero theHero,
                                 final Room theRoom,
                                 final Dungeon theDungeon) {


        if (theRoom.hasRoomType(RoomType.FIGHT)){

            //SQL INJECTTION HEEERRE

            Monster monster = new Skeleton("Null Pointer");
            initiateFight(theHero, monster, theDungeon, theRoom);
        }

        if (theRoom.hasRoomType(RoomType.PIT)) {
            ConsoleOutput.printString(theHero.getCharacter_Name() + " fell into a pit");
            theHero.heroTakesDamage();
        }





    }

    public static void initiateFight(final Hero theHero,
                                     final Monster theMonster,
                                     final Dungeon theDungeon,
                                     final Room theRoom) {

        Scanner theUserInput = new Scanner(System.in);
        int roundCounter = 1;

        while (theHero.alive() && theMonster.alive()) {
            ConsoleOutput.printString("\t\t\t Round: " + roundCounter + "\n");
            ConsoleOutput.printString("Player HP: " + theHero.getCharacter_HealthPoints() + "\t\t Monster's HP: " + theMonster.getCharacter_HealthPoints() + "\n");

            while(theHero.canAttack(theMonster)) {
                int attackChoice = HeroController.GetChoiceCreator(theHero);
                ConsoleOutput.printString(theHero.attacks(theMonster, attackChoice));
            }

            ConsoleOutput.printString(theMonster.attacks(theHero));
            roundCounter++;
            ConsoleOutput.printString("END OF ROUND, PRESS ANY KEY TO CONTINUE");
            theUserInput.nextLine();

        } if (!theMonster.alive()){
            theRoom.removeMyTypes(RoomType.FIGHT);
            ConsoleOutput.printString(theDungeon.toString());

        }
    }

    /**
     * This method takes in the user input and validates the direction that the user picks.
     * @param userInput (Scanner)
     * @param theLocation (The location of the player)
     * @param theDungeonSize (The size of the dungeon)
     * @return (Validated direction that a user can go into)
     */
    public static String directionChecker(final Scanner userInput,
                                          final Point theLocation,
                                          final int theDungeonSize){
        String choices = "Please select your movement(n for North, s for South, e for East, w for West or k for Map Legend)";
        ArrayList<String> choiceList = availableDirectionChoices(theLocation, theDungeonSize);
        String direction = null;
        boolean correctAnswer = false;

        // Input Validation
        while (!correctAnswer) {

            ConsoleOutput.printString(choices + "\n");
            ConsoleOutput.printString("These are your available moves " + choiceList + ": ");

            if (userInput.hasNext()) {
                direction = userInput.next();

                if (direction.equals("n") || direction.equals("s") || direction.equals("w") || direction.equals("e") || direction.equals("k")) {
                    if (direction.equals("k")){
                        //When K is return to the main function, it prints the legend of the map
                        return direction; // This needs to be checked

                    }
                    if (choiceList.contains(direction)){
                        correctAnswer = true;

                    }
                } else {
                    ConsoleOutput.printString("Please select the correct direction\n");

                }

            } else {
                ConsoleOutput.printString("Invalid Input\n\n");
                userInput.next();

            }
        }
        return direction;

    }

    /**
     * This method insures that the user's direction choice does not go out of bounds.
     * @param theLocation
     * @param theSize (The size of the dungeon)
     * @return (The list contains the valid directions that a user can take)
     */
    public static ArrayList<String> availableDirectionChoices(final Point theLocation,
                                                              final int theSize) {
        ArrayList<String> availableChoices = new ArrayList<>();
        //readability is awful here ask tom to see if there is an easier way to improve it
        boolean north = theLocation.y > 0;
        boolean south = theLocation.y < theSize-1;
        boolean west = theLocation.x > 0;
        boolean east = theLocation.x < theSize-1;


        String direction_North = "n";
        String direction_South = "s";
        String direction_East = "e";
        String direction_West = "w";




        if (north) {
            availableChoices.add(direction_North);

        }if (south) {
            availableChoices.add(direction_South);

        }if (east) {
            availableChoices.add(direction_East);

        }if (west) {
            availableChoices.add(direction_West);

        }
        return availableChoices;

    }
}
//END
