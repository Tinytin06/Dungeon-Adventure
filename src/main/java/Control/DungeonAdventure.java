/*
 * There is a bug where the player icon appears in the room before the room even has
 * the chance to update the player in, with the changes made to the dungeon class
 * I think it should be fixed but keep it in check
 *
 *
 * Hero satchel needs an update so it gets updated accordingly
 *
 *  Mover method need decoupling and split into Hero Satchel and Hero Mover
 *
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
        Scanner userInput = new Scanner(System.in);
        if(true){   //if(introduction(userInput)) {
            boolean playAgain = false;
            while(!playAgain) {
                //String name = heroName(userInput);
                String name = "test";

                Hero hero = new Warrior(name);
                int myDungeonSize = 5;
                Dungeon myDungeon = new Dungeon(myDungeonSize);

                //Placing the hero into the dungeon entrance
                hero.setCharacterLocation(myDungeon.getEntrancePoint());

                ConsoleOutput.printString("Entrance is at: "+ myDungeon.getEntrancePoint() + "\n");
//                System.out.println("Entrance is at: " + myDungeon.getEntrancePoint());





                // These are the names needs to be used in order to activate the cheat
                // I dont have a method in the Dungeon Adventure for revealing all of the
                // room, I thought that it better fit for the Dungeon class to have that kind of power.
                if (name.equals("Varun") || name.equals("Bryce")) {

                    myDungeon.setMyCheatEnabled();
                }

                //myDungeon.setMyCheatEnabled();// Testing purposes
                while(hero.alive()) {
//                    System.out.println("Hero's current Location: " + hero.getCharacterLocation());
                    ConsoleOutput.printString("Hero's current Location: " + hero.getCharacterLocation() + "\n");

                    driver(userInput, hero, myDungeonSize, myDungeon);

                }
//                System.out.println("\nThis is the dungeon fully revealed");
                ConsoleOutput.printString("\nThis is the dungeon fully revealed\n");

                myDungeon.revealAll();

//                System.out.println(myDungeon);
                ConsoleOutput.printString(myDungeon + "\n");

//                System.out.println("\nWould you like to play again?");
                ConsoleOutput.printString("\nWould you like to play again?\n");

                if (!inputValidatorYN(userInput)) {
                    playAgain = true;
                }

            }
        } else {
//            System.out.println("A wise choice, now MOVE ALONG!");
            ConsoleOutput.printString("A wise choice, now MOVE ALONG!\n");
        }
    }

    /**
     * This introduction method just prints out the introduction of the player
     * and asks them if they are ready to play the game.
     * @param theUserInput (Scanner)
     * @return
     */
// THIS IS IN CONSOLE OUTPUT ALREADY!
//    public static boolean introduction(final Scanner theUserInput) {
//        System.out.println("------------------ Welcome!!! -------------------");
//        System.out.println("---------------- Hear Ye Hear Ye ----------------");
//        System.out.println("A long time ago, a warrior had challenged" +
//                " the creator, \n the programmer himself, to a game of " +
//                "Labyrinth, but \n       he lost and he paid with his life. \n" +
//                "\nNo one has challenged him ever again, but you my child,");
//        System.out.println("You wish to challenge him?");
//        return (yesORNo(theUserInput));
//    }
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
//            System.out.print("Please enter a name for your hero: ");
            ConsoleOutput.printString("Please enter a name for your hero: \n");

            if (userInput.hasNextInt() || userInput.hasNextDouble()) {
//                System.out.println("Invalid input");
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

//            // Checking for hero's satchel
//            if (theHero.getHeroSatchel().contains("Vision Potion") || theHero.getHeroSatchel().contains("Healing Potion")) {
//                String potionChoice = ("You have unused potions, you can press 'y' to use your item 'n' for no");
////                System.out.println(potionChoice);
//                ConsoleOutput.printString(potionChoice + "\n");
//
////                System.out.print("These are your available potions " + theHero.getHeroSatchel());
//                ConsoleOutput.printString("These are your available potions " + theHero.getHeroSatchel() + "\n");
//
//                if (yesORNo(theUserInput)){
//                    if (theHero.getHeroSatchel().contains("Vision Potion")) {
//                        theDungeon.deployVisionPotion(theHero.getCharacterLocation());
//                        theHero.removeSatchelItem("Vision Potion");
//                    }
//                    if (theHero.getHeroSatchel().contains("Healing Potion")) {
//                        theHero.healingPotion();
//                        theHero.removeSatchelItem("Healing Potion");
//                    }
//                    if (theHero.getHeroSatchel().contains("Vision Potion") && theHero.getHeroSatchel().contains("Healing Potion")) {
//                        theHero.removeSatchelItem("Healing Potion");
//                        theHero.removeSatchelItem("Vision Potion");
//                    }
//                }
//            }


//            myRoom.setisPlayerinRoom(true);

//            System.out.println(theDungeon);
            ConsoleOutput.printString(theDungeon + "\n");

            checkRoom(theHero, myRoom, theDungeon);

//            System.out.print("This room has: "); // Dont print if room doesn't have anything
            ConsoleOutput.printString("This room has: \n");

//            System.out.println(myRoom.getMyRoomInventory());// Room has a method for printing
            ConsoleOutput.printString(myRoom.showMyRoomInventory() + "\n");

            heroItemPicker(myRoom, theHero);


            //Checking if the hero is at the exit


            if (!isHeroAtExit(myRoom, theHero, theUserInput)){
                if (!theHero.alive()){
                    String output = "You came here with such life yet here you lie\n\t\tlifeless.\nBetter Luck Next time!";
                    ConsoleOutput.printString(output + "\n");

//                    System.out.println("You came here with such life yet here you lie");
//                    System.out.println("               lifeless.");
//                    System.out.println("        Better Luck Next time!");
                } else {
//                    System.out.println(theHero);
                    ConsoleOutput.printString(theHero + "\n");

//                    System.out.print(theHero.getCharacter_Name()+ "'s Inventory: ");
                    ConsoleOutput.printString(theHero.getCharacter_Name()+ "'s Inventory:");

//                    System.out.println(theHero.getHeroSatchel());
                    ConsoleOutput.printString(theHero.getHeroSatchel() + "\n");

                    //Removing player from the Room

                    myRoom.removeMyTypes(RoomType.PLAYER);

                    //Selecting Player's next move
                    playerSelectDirection(location, theDungeonSize, theHero);

//                    myRoom.setisPlayerinRoom(false);
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
//            System.out.println("You have unused potions, you can press 'y' to use your item 'n' for no");
//            System.out.print("These are your available potions " + theHero.getHeroSatchel());
            ConsoleOutput.printString("These are your available potions " + theHero.getHeroSatchel() + "\n");
            if (inputValidatorYN(theUserInput)){
                if (theHero.satchelContains(RoomType.VISION)) {
                    theDungeon.deployVisionPotion(theHero.getCharacterLocation());
                    theHero.removeSatchelItem(RoomType.VISION);
                }
                if (theHero.satchelContains(RoomType.HEALING)) {
                    theHero.healingPotion();
                    theHero.removeSatchelItem(RoomType.HEALING);
                }
//                if (theHero.getHeroSatchel().contains("Vision Potion") && theHero.getHeroSatchel().contains("Healing Potion")) {
//                    theHero.removeSatchelItem("Healing Potion");
//                    theHero.removeSatchelItem("Vision Potion");
//                }
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
//            System.out.println("You are on the exit!");
            ConsoleOutput.printString("You are on the exit!\n");

            //Whenever the two crowns are picked up, the hero is not recognizing that
            if (theHero.hasBothCrowns()) {
//                System.out.println("Would you like to exit the dungeon?");
                ConsoleOutput.printString("Would you like to exit the dungeon?\n");

                if (inputValidatorYN(theUserInput)) {
//                    System.out.println("               You did great out there!");
                    ConsoleOutput.printString("\t\t\tYou did great out there!\n");

//                    System.out.println("the programmer didn't even notice the missing coding crowns.");
                    ConsoleOutput.printString("the programmer didn't even notice the missing coding crowns.\n");

                    theHero.killCharacter();
                    return true;
                }
            } else {
//                System.out.println("You have to keep looking for more treasure!");
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

            //System.out.print("'y' for yes, 'n' for no: ");
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
//                    System.out.println("Please select the correct response");
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

//There needs to be a way to move the item from the room to the hero's satchel.
//theRoom.pickUP(theItem, theHero); removes it from Room inventory and moves it to hero inventory



        for( Character theItem : theRoom.getMyRoomInventory()) {
//Dont replace it with Switch statement just yet
            if (theItem == RoomType.CODING_CROWN_1.type){
                //theRoom.pickUP(theItem, theHero);

//                System.out.println("You have picked up the Coding Crown!");
                ConsoleOutput.printString("You have picked up the Coding Crown!\n");

                theHero.addItem2Satchel(RoomType.CODING_CROWN_1);

            }else if (theItem == RoomType.CODING_CROWN_2.type){
//                theRoom.pickUP(theItem, theHero);
//                theHero.addCrownPiece();

//                System.out.println("You have picked up the Second Coding Crown!");
                theHero.addItem2Satchel(RoomType.CODING_CROWN_2);
                ConsoleOutput.printString("You have picked up the Second Coding Crown!\n");



            } else if (theItem == RoomType.HEALING.type){

                theHero.addItem2Satchel(RoomType.HEALING);
//                theRoom.pickUP(theItem, theHero);
//                theHero.addHealingPotion();

//                System.out.println("You have picked up the Healing Potion!");
                ConsoleOutput.printString("You have picked up the Healing Potion!\n");



            }else if (theItem == RoomType.VISION.type){

                theHero.addItem2Satchel(RoomType.VISION);
//                theRoom.pickUP(theItem, theHero);
//                theHero.addVisionPotion();

//                System.out.println("You have picked up the Vision Potion!");
                ConsoleOutput.printString("You have picked up the Vision Potion!\n");


            }


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
//            System.out.println("\t\t\t Round: " + roundCounter + "\n");
//            System.out.println("Player HP: " + theHero.getCharacter_HealthPoints() + "\t\t Monster's HP: " + theMonster.getCharacter_HealthPoints() + "\n");
            ConsoleOutput.printString("\t\t\t Round: " + roundCounter + "\n");
            ConsoleOutput.printString("Player HP: " + theHero.getCharacter_HealthPoints() + "\t\t Monster's HP: " + theMonster.getCharacter_HealthPoints() + "\n");


//            while(Movement.GetChoiceCreator(theHero)) {
            while(theHero.canAttack(theMonster)) {
                int attackChoice = Movement.GetChoiceCreator(theHero);
                theHero.attacks(theMonster, attackChoice);
            }



            theMonster.attacks(theHero);
            roundCounter++;

//            System.out.print("END OF ROUND, PRESS ANY KEY TO CONTINUE");
            ConsoleOutput.printString("END OF ROUND, PRESS ANY KEY TO CONTINUE");

            theUserInput.nextLine();
        } if (!theMonster.alive()){
            theRoom.removeMyTypes(RoomType.FIGHT);

//            System.out.println(theDungeon);
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
//            System.out.println(choices);
//            System.out.print("These are your available moves " + choiceList + ": ");

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
//                    System.out.println("Please select the correct direction");
                    ConsoleOutput.printString("Please select the correct direction\n");


                }

            } else {
//                System.out.println("Invalid Input\n");
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
        boolean north = theLocation.y > 0;
        boolean south = theLocation.y < theSize-1;
        boolean west = theLocation.x > 0;
        boolean east = theLocation.x < theSize-1;
        if (north) {
            availableChoices.add("n");
        }if (south) {
            availableChoices.add("s");
        }if (east) {
            availableChoices.add("e");
        }if (west) {
            availableChoices.add("w");
        }
        return availableChoices;

    }
}
//END