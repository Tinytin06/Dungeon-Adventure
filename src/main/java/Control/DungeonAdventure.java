//package Control;/*
// * Varun Parbhakar
// *
// * TCSS-143
// * Heroes VS Monster (Dungeon DLC)
// */
//import Model.Characters.Hero;
//import Model.Characters.Monster;
//import Model.Characters.Skeleton;
//import Model.Characters.Warrior;
//import Model.Dungeon;
//import View.ConsoleOutput;
//
//
//import java.awt.*;
//import java.util.ArrayList;
//import java.util.Scanner;
//
///**
// * This is the main client class for the dungeon adventure game it contains
// * the game player that initiates and continues with the game until the player decides to quit.
// * @author Varun Parbhakar
// */
//public class DungeonAdventure {
//    /**
//     * This main method initializes the dungeon, hero and the monster to prepare the game.
//     * @param args
//     */
//    public static void main(String[] args) {
//        Scanner userInput = new Scanner(System.in);
//        if(introduction(userInput)) {
//            boolean playAgain = false;
//            while(!playAgain) {
//                String name = heroName(userInput);
//
//                Hero hero = new Warrior(name);
//                Monster monster = new Skeleton("Null Pointer");
//                int myDungeonSize = 5;
//                Dungeon myDungeon = new Dungeon(myDungeonSize, hero);
//                // These are the names needs to be used in order to activate the cheat
//                // I dont have a method in the Dungeon Adventure for revealing all of the
//                // room, I thought that it better fit for the Dungeon class to have that kind of power.
//                if (name.equals("Varun") || name.equals("Bryce")) {
//                    myDungeon.setMyCheatEnabled();
//                }
//
//
//                while(hero.alive()) {
//                    mover(userInput, hero, myDungeonSize, myDungeon, monster);
//
//                }
//                System.out.println("\nThis is the dungeon fully revealed");
//                myDungeon.revealAll();
//                System.out.println(myDungeon);
//                System.out.println("\nWould you like to play again?");
//                if (!yesORNo(userInput)) {
//                    playAgain = true;
//                }
//
//            }
//        } else {
//            System.out.println("A wise choice, now MOVE ALONG!");
//        }
//    }
//
//    /**
//     * This introduction method just prints out the introduction of the player
//     * and asks them if they are ready to play the game.
//     * @param theUserInput (Scanner)
//     * @return
//     */
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
//    /**
//     * This method is responsible for making sure the user inputs a name for the hero,
//     * this method is also performs input validation.
//     *
//     * @return String (The name of the hero)
//     */
//    public static String heroName(final Scanner userInput) {
//        boolean correctAnswer = false;
//        String heroName = null;
//
//        while (!correctAnswer) {
//            System.out.print("Please enter a name for your hero: ");
//            if (userInput.hasNextInt() || userInput.hasNextDouble()) {
//                System.out.println("Invalid input");
//                userInput.next();
//            } else {
//                heroName = userInput.next();
//                correctAnswer = true;
//            }
//
//        }
//        return heroName;
//
//    }
//
//    /**
//     * This is the driver method which makes sure that the player is alive and
//     * performs multiple room and dungeon check to main the player and room
//     * inventory the with the use of other classes.
//     * @param theUserInput (Scanner)
//     * @param theHero (Hero)
//     * @param theDungeonSize (Int Size of the dungeon)
//     * @param theDungeon (Dungeon)
//     * @param theMonster (Monster)
//     */
//    public static void mover(final Scanner theUserInput,
//                             final Hero theHero,
//                             final int theDungeonSize,
//                             final Dungeon theDungeon,
//                             final Monster theMonster) {
//
//        // Checks if the cheat is enabled
//        if (theDungeon.getMyCheat()) {
//            theDungeon.revealAll();
//        }
//        if (theHero.alive()) {
//            Point location = theHero.getCharacterLocation();
//            Room myRoom = theDungeon.getContent(theHero.getCharacterLocationY(),theHero.getCharacterLocationX());
//            myRoom.exploreTheRoom();
//            ArrayList<String> roomItemList = (ArrayList)(myRoom.getMyRoomInventory()).clone();
//            if (theHero.getHeroSatchel().contains("Vision Potion") || theHero.getHeroSatchel().contains("Healing Potion")) {
//                String potionChoice = ("You have unused potions, you can press 'y' to use your item 'n' for no");
//                System.out.println(potionChoice);
//                System.out.print("These are your available potions " + theHero.getHeroSatchel());
//                if (yesORNo(theUserInput)){
//                    if (theHero.getHeroSatchel().contains("Vision Potion")) {
//                        theDungeon.visionPotionUser(theHero.getCharacterLocation());
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
//
//            myRoom.setisPlayerinRoom(true);
//            System.out.println(theDungeon);
//            roomItemList = checkRoom(theHero, roomItemList, theMonster, theUserInput, theDungeon);
//            System.out.print("This room has: ");
//            System.out.println(roomItemList);
//
//            heroItemPicker(roomItemList, myRoom, theHero);
//
//            if (!isHeroAtExit(myRoom, theHero, theUserInput)){
//                if (!theHero.alive()){
//                    System.out.println("You came here with such life yet here you lie");
//                    System.out.println("               lifeless.");
//                    System.out.println("        Better Luck Next time!");
//                } else {
//                    System.out.println(theHero);
//                    System.out.print(theHero.getCharacter_Name()+ "'s Inventory: ");
//                    System.out.println(theHero.getHeroSatchel());
//                    String direction = directionChecker(theUserInput, location, theDungeonSize);
//                    if (direction.equals("k")){
//                        for (String key : theDungeon.mapLegend()) {
//                            System.out.println(key);
//                        }
//                    }
//                    if (direction.equals("n")){
//                        theHero.setCharacterLocationY(-1);
//                    }
//                    if (direction.equals("s")){
//                        theHero.setCharacterLocationY(1);
//                    }
//                    if (direction.equals("e")){
//                        theHero.setCharacterLocationX(1);
//                    }
//                    if (direction.equals("w")){
//                        theHero.setCharacterLocationX(-1);
//                    }
//                    myRoom.setisPlayerinRoom(false);
//                }
//            }
//        }
//    }
//
//    /**
//     * This method checks to see if the hero is standing in a room with an exit.
//     * This method also asks the player if they would like to exit the dungeon through
//     * the door.
//     * @param theRoom
//     * @param theHero
//     * @param theUserInput
//     * @return (Boolean, if the player is on the exit or not)
//     */
//    public static boolean isHeroAtExit(final Room theRoom,
//                                       final Hero theHero,
//                                       final Scanner theUserInput) {
//        if (theRoom.getHasExit()) {
//            ConsoleOutput.printString("You are on the exit!\n");
//            System.out.println("You are on the exit!");
//            if (theHero.hasBothCrowns()) {
//                System.out.println("Would you like to exit the dungeon?");
//                if (yesORNo(theUserInput)) {
//                    System.out.println("               You did great out there!");
//                    System.out.println("the programmer didn't even notice the missing coding crowns.");
//                    theHero.killCharacter();
//                    return true;
//                }
//            } else {
//                System.out.println("You have to keep looking for more treasure!");
//                return false;
//            }
//        }
//        return false;
//    }
//
//    /**
//     * This method asks the user for a yes or a no answer and insures the input is correct.
//     * @param theUserInput (Scanner)
//     * @return (Boolean YES or No)
//     */
//    public static boolean yesORNo(final Scanner theUserInput) {
//        boolean correctAnswer = false;
//        String choice = null;
//        while (!correctAnswer) {
//            ConsoleOutput.printString("'y' for yes, 'n' for no: ");
//            //System.out.print("'y' for yes, 'n' for no: ");
//
//            if (theUserInput.hasNext()) {
//                choice = theUserInput.next();
//
//                if (choice.equals("n") || choice.equals("y")) {
//                    if (choice.equals("y")) {
//                        return true;
//                    } else {
//                        return false;
//                    }
//                } else {
//                    System.out.println("Please select the correct response");
//                }
//            } else {
//                ConsoleOutput.invalidInput();
//                theUserInput.next();
//            }
//        }
//        return false;
//    }
//
//    /**
//     * This method takes the items from the room inventory and deposits the items into the hero's inventory.
//     * @param theRoomItems (Items present in the room)
//     * @param theRoom (The room object)
//     * @param theHero (The hero object)
//     */
//    public static void heroItemPicker(final ArrayList<String> theRoomItems,
//                                      final Room theRoom,
//                                      final Hero theHero) {
//        for( String theItem : theRoomItems) {
//            if (theItem.equals("Coding Crown")){
//                theRoom.pickUP(theItem, theHero);
//                System.out.println("You have picked up the Coding Crown!");
//                theHero.addCrownPiece();
//                continue;
//
//            }if (theItem.equals("Second Coding Crown")){
//                theRoom.pickUP(theItem, theHero);
//                theHero.addCrownPiece();
//                System.out.println("You have picked up the Second Coding Crown!");
//                continue;
//
//            }
//            if (theItem.equals("Healing Potion")){
//                theRoom.pickUP(theItem, theHero);
//                theHero.addHealingPotion();
//                System.out.println("You have picked up the Healing Potion!");
//                continue;
//
//            }
//            if (theItem.equals("Vision Potion")){
//                theRoom.pickUP(theItem, theHero);
//                theHero.addVisionPotion();
//                System.out.println("You have picked up the Vision Potion!");
//
//                continue;
//            }
//
//
//        }
//
//    }
//
//    /**
//     * This method checks the room for monster and pits, if a monster is present
//     * this method initiates a fight, if there is a pit then this method reduces player's
//     * health to accommodate for the fall.
//     * @param theHero
//     * @param theRoomItems
//     * @param theMonster
//     * @param theUserInput
//     * @param theDungeon
//     * @return (Returns an updated list of the room)
//     */
//    public static ArrayList<String> checkRoom(final Hero theHero,
//                                              final ArrayList<String> theRoomItems,
//                                              final Monster theMonster,
//                                              final Scanner theUserInput,
//                                              final Dungeon theDungeon) {
//        ArrayList<String> copyList = (ArrayList)(theRoomItems.clone());
//        for (String theItem: copyList) {
//            if (theItem.equals("Monster!")){
//                int roundCounter = 1;
//                while (theHero.alive() && theMonster.alive()) {
//                    System.out.println("\t\t\t Round: " + roundCounter + "\n");
//                    System.out.println("Player HP: " + theHero.getCharacter_HealthPoints() + "\t\t Monster's HP: " + theMonster.getCharacter_HealthPoints() + "\n");
//                    theHero.attacks(theMonster);
//                    theMonster.attacks(theHero);
//                    roundCounter++;
//                    System.out.print("END OF ROUND, PRESS ANY KEY TO CONTINUE");
//                    theUserInput.nextLine();
//                } if (!theMonster.alive()){
//                    theRoomItems.remove("Monster!");
//                    System.out.println(theDungeon);
//                }
//            } if (theItem.equals("Pit!")) {
//                theHero.heroTakesDamage();
//            }
//
//        }
//        return theRoomItems;
//
//    }
//
//    /**
//     * This method takes in the user input and validates the direction that the user picks.
//     * @param userInput (Scanner)
//     * @param theLocation (The location of the player)
//     * @param theDungeonSize (The size of the dungeon)
//     * @return (Validated direction that a user can go into)
//     */
//    public static String directionChecker(final Scanner userInput,
//                                          final Point theLocation,
//                                          final int theDungeonSize){
//        String choices = "Please select your movement(n for North, s for South, e for East, w for West or k for Map Legend)";
//        ArrayList<String> choiceList = availableChoices(theLocation, theDungeonSize);
//        String direction = null;
//        boolean correctAnswer = false;
//
//        // Input Validation
//        while (!correctAnswer) {
//            System.out.println(choices);
//            System.out.print("These are your available moves " + choiceList + ": ");
//
//            if (userInput.hasNext()) {
//                direction = userInput.next();
//
//                if (direction.equals("n") || direction.equals("s") || direction.equals("w") || direction.equals("e") || direction.equals("k")) {
//                    if (direction.equals("k")){
//                        return direction;
//                    }
//                    if (choiceList.contains(direction)){
//                        correctAnswer = true;
//                    }
//
//                } else {
//                    System.out.println("Please select the correct direction");
//
//                }
//
//            } else {
//                System.out.println("Invalid Input\n");
//                userInput.next();
//            }
//
//        }
//        return direction;
//    }
//
//    /**
//     * This method insures that the user's direction choice does not go out of bounds.
//     * @param theLocation
//     * @param theSize (The size of the dungeon)
//     * @return (The list contains the valid directions that a user can take)
//     */
//    public static ArrayList<String> availableChoices(final Point theLocation,
//                                                     final int theSize) {
//        ArrayList<String> availableChoices = new ArrayList<>();
//        boolean north = theLocation.y > 0;
//        boolean south = theLocation.y < theSize-1;
//        boolean west = theLocation.x > 0;
//        boolean east = theLocation.x < theSize-1;
//        if (north) {
//            availableChoices.add("n");
//        }if (south) {
//            availableChoices.add("s");
//        }if (east) {
//            availableChoices.add("e");
//        }if (west) {
//            availableChoices.add("w");
//        }
//        return availableChoices;
//
//    }
//}
////END