/*
 *
 * HeroController -> playerSelectDirection, heroItemPicker,
 */

package Control;
import Model.Characters.Hero;
import Model.Characters.Monster;
import Model.Characters.Monsters.Skeleton;
import Model.Characters.Heroes.Warrior;
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
//        Testing
        for (int i = 0; i < 10; i++) {
            //Testing
            Hero myHero = new Warrior("Test_Hero");
            Monster myMonster = new Skeleton("Test_Monster");
            Dungeon theDungeon = new Dungeon(5);
            Room myRoom = new Room();
            myRoom.addTo_MyRoomInventory(RoomType.FIGHT);

            DungeonAdventure.initiateFight(myHero, myMonster, theDungeon, myRoom);

        }
//        Testing




        //Trying to fix the .class files

        Scanner userInput = new Scanner(System.in);
        ConsoleOutput.introduction();
        if(UserInputValidate.getYN(userInput)) {
            boolean playAgain = false;
            while(!playAgain) {
                String name = UserInputValidate.heroName(userInput);

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
                System.out.println("CHEATS ARE ACTIVE __ TESTING");

                while(hero.alive()) {
                    gameDriver(userInput, hero, myDungeonSize, myDungeon);

                }
                ConsoleOutput.printString("\nThis is the dungeon fully revealed\n");

                myDungeon.revealAll();

                ConsoleOutput.printString(myDungeon + "\n");
                ConsoleOutput.printString("\nWould you like to play again?\n");

                if (!UserInputValidate.getYN(userInput)) {
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
     * This is the driver method which makes sure that the player is alive and
     * performs multiple room and dungeon check to main the player and room
     * inventory the with the use of other classes.
     * @param theUserInput (Scanner)
     * @param theHero (Hero)
     * @param theDungeonSize (Int Size of the dungeon)
     * @param theDungeon (Dungeon)
     */
    public static void gameDriver(final Scanner theUserInput,
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
            ConsoleOutput.printString("This room has: ");
            ConsoleOutput.printString(myRoom.showMyRoomInventory() + "\n");

            HeroController.itemPickUp(myRoom, theHero);

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
                    ArrayList<String> availableDirections = availableDirections(location, theDungeonSize);
                    String heroHeading = UserInputValidate.heroDirectionHeading(theUserInput, availableDirections);
                    HeroController.moveCharacter(theHero, heroHeading);

                }
            }

        }
    }



    public static void checkHeroSatchel (final Hero theHero, final Dungeon theDungeon) {
        Scanner theUserInput = new Scanner(System.in);
        // Checking for hero's satchel
        if (theHero.satchelContains(RoomType.VISION) || theHero.satchelContains(RoomType.HEALING)) {
            ConsoleOutput.printString("You have unused potions, you can press 'y' to use your item 'n' for no\n");
            ConsoleOutput.printString("These are your available potions " + theHero.getHeroSatchel() + "\n");

            if (UserInputValidate.getYN(theUserInput)){
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

                if (UserInputValidate.getYN(theUserInput)) {
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

            //Make monster factory
            //If hero is at exit then jourmunganda
            //if contains pillars then guard
            //Otherwise normal monster

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

                int attackChoice = UserInputValidate.attackChoice(theHero);
                ConsoleOutput.printString(theHero.attacks(theMonster, attackChoice));
            }

            ConsoleOutput.printString(theMonster.attacks(theHero));
            roundCounter++;
            ConsoleOutput.printString("\nEND OF ROUND, PRESS ANY KEY TO CONTINUE");
            theUserInput.nextLine();

        } if (!theMonster.alive()){
            theRoom.removeMyTypes(RoomType.FIGHT);
            ConsoleOutput.printString(theDungeon.toString());

        }
    }


    /**
     * This method insures that the user's direction choice does not go out of bounds.
     * @param theLocation
     * @param theSize (The size of the dungeon)
     * @return (The list contains the valid directions that a user can take)
     */
    public static ArrayList<String> availableDirections(final Point theLocation,
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
