/*
 * Multiple pillars are being spwaned in a single room, FIX it so that only 1 pilar spawns at a time.
 *
 * Fix the SQL
 */
package Control;
import Model.Characters.Hero;
import Model.Characters.Heroes.Priestess;
import Model.Characters.Heroes.Thief;
import Model.Characters.Monster;
import Model.Characters.Heroes.Warrior;
import Model.Dungeon;
import Model.MonsterFactory;
import Model.Room;
import Model.RoomType;
import View.ConsoleOutput;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import java.util.regex.Pattern;

import java.awt.*;
import java.util.regex.Matcher;

/**
 * This is the main client class for the dungeon adventure game it contains
 * the game player that initiates and continues with the game until the player decides to quit.
 * @author Varun Parbhakar
 */
public class DungeonAdventure implements Serializable {
    @Serial
    private static final long serialVersionUID = 5586613526209979670L;
//    private static final long serialversionUID =
//            1493124918L;
    /**
     * This main method initializes the dungeon, hero and the monster to prepare the game.
     * @param args
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        //Testing
//        for (int i = 0; i < 10; i++) {
//            //Testing
//            Hero myHero = new Warrior("Test_Hero");
//            MonsterFactory mF = new MonsterFactory();
//            Monster myMonster = mF.getBossMonsters().get(0);
//            Dungeon theDungeon = new Dungeon(5);
//            Room myRoom = new Room();
//
//            myRoom.addTo_MyRoomInventory(RoomType.FIGHT);
//
//            DungeonAdventure.initiateFight(myHero, myMonster, theDungeon, myRoom);
//
//        }
////        Testing


//        if(UserInputValidate.saveGame(userInput)){

//            Hero hero = new Warrior("name");
//            int myDungeonSize = 5;
//            Dungeon myDungeon = new Dungeon(myDungeonSize);
//            myDungeon.setMyCheatEnabled();
//            myDungeon.revealAll();
//        System.out.println(myDungeon);
//        System.out.println(hero);
//
//
//            try {
//                saveGame("may-30", myDungeon , hero);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//
//        myDungeon=null;
//        hero=null;
//
//        System.out.println(myDungeon);
//        System.out.println(hero);
//
////
//        File[] allSaves = loadGame();
//        int userPick = 0;
////        int userPick = userInput.nextInt();
//        if(userPick >= 0 && userPick < allSaves.length) {
//            FileInputStream file = new FileInputStream(allSaves[userPick] + "/hero.bat");
//            ObjectInputStream in = new ObjectInputStream(file);
//            //System.out.println(in.readObject());
//            hero = (Warrior) in.readObject();
//
//
//            file = new FileInputStream(allSaves[userPick] + "/dungeon.bat");
//            in = new ObjectInputStream(file);
//
//            myDungeon = (Dungeon) in.readObject();
//        }
//
//        myDungeon.revealAll();
//        System.out.println(myDungeon);
//        System.out.println(hero);

//        }

        /*
            - save
                -imalmostdone
                    -hero.bin
                    -dun.bin
                -may-25-2020
                    -hero
                    -dun
                -ase
         */

        Scanner userInput = new Scanner(System.in);
        ConsoleOutput.introduction();
        if(UserInputValidate.getYN(userInput)) {
            boolean playAgain = false;
            while(!playAgain) {

                ConsoleOutput.printString("\nWould you like to load a previously saved game?\n");

                Hero hero = null;
                Dungeon myDungeon = null;
                if(UserInputValidate.getYN(userInput)) {

                    File[] allSaves = loadGame();
                    int userPick = UserInputValidate.getLoadNumber(userInput, allSaves.length-1);

                    FileInputStream file = new FileInputStream(allSaves[userPick] + "/hero.bat");
                    ObjectInputStream in = new ObjectInputStream(file);
                    Object obj = in.readObject();

                    if(obj.getClass() == Warrior.class){
                        hero = (Warrior) obj;
                    } else if (obj.getClass() == Thief.class){
                        hero = (Thief) obj;
                    } else {
                        hero = (Priestess) obj;
                    }


                    file = new FileInputStream(allSaves[userPick] + "/dungeon.bat");
                    in = new ObjectInputStream(file);
                    myDungeon = (Dungeon) in.readObject();

                    ConsoleOutput.printString("\n\n Game has succesfully been loaded.\n\n");
                } else {

                    hero = heroSelector();
                    int myDungeonSize = 5;
                    myDungeon = new Dungeon(myDungeonSize);

                    //Placing the hero into the dungeon entrance
                    hero.setCharacterLocation(myDungeon.getEntrancePoint());

                    // These are the names needs to be used in order to activate the cheat
                    // I dont have a method in the Dungeon Adventure for revealing all of the
                    // room, I thought that it better fit for the Dungeon class to have that kind of power.
                    if (hero.getCharacter_Name().equals("Varun") || hero.getCharacter_Name().equals("Bryce")) {
                        myDungeon.setMyCheatEnabled();

                    }
                    myDungeon.setMyCheatEnabled();//testing purposes
                    System.out.println("CHEATS ARE ACTIVE __ TESTING");
                }


                while(hero.alive()) {
                    gameDriver(userInput, hero, myDungeon.getMyDungeonSize(), myDungeon);
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

    public static Hero heroSelector() {
        Scanner userInput = new Scanner(System.in);
        String name = UserInputValidate.heroName(userInput);
        int heroSelection = UserInputValidate.heroSelector(name, userInput);
        Hero myHero;
        if(heroSelection == 1) {
            myHero = new Thief(name);

        } else if(heroSelection == 2) {
            myHero = new Warrior(name);

        } else {
            myHero = new Priestess(name);

        }
        return myHero;

    }




    public static File[] loadGame(){
        File dir = new File("./src/main/java/Saves");
        File[] files = dir.listFiles();


        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    ConsoleOutput.printString("\n" +  i + ") " + files[i].getName() );
                }
            }
        } else {
            ConsoleOutput.printString("There are no saves yet!");
        }
        return files;
    }

    public static void saveGame(String theSaveName, Dungeon theDungeon, Hero theHero) throws IOException {
        if(theSaveName.equals("")){
            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            theSaveName = dateFormat.format(date);
        }
        String fileDirectory = "./src/main/java/Saves/" + theSaveName;

        if(!Files.exists(Paths.get(fileDirectory))){
            Files.createDirectories(Paths.get(fileDirectory));
        }

        FileOutputStream file = new FileOutputStream(fileDirectory + "/dungeon.bat");
        ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(theDungeon);


        file = new FileOutputStream(fileDirectory + "/hero.bat");
        out = new ObjectOutputStream(file);
        out.writeObject(theHero);

        out.close();
        file.close();
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



            //Checking if the hero is at the exit
            if (!isHeroAtExit(myRoom, theHero, theUserInput)){
                if (!theHero.alive()){
                    String output = "\nYou came here with such life yet here you lie\n\t\t\t\tlifeless.\nBetter Luck Next time!";
                    ConsoleOutput.printString(output + "\n");

                } else {
                    HeroController.itemPickUp(myRoom, theHero);
                    ConsoleOutput.printString(theHero + "\n");
                    ConsoleOutput.printString(theHero.getCharacter_Name()+ "'s Inventory:");
                    ConsoleOutput.printString(theHero.getHeroSatchel() + "\n\n");

                    //Removing player from the Room
                    myRoom.removeMyTypes(RoomType.PLAYER);

                    //Selecting Player's next move
                    ArrayList<String> availableDirections = availableDirections(location, theDungeonSize);
                    String heroHeading = UserInputValidate.heroDirectionHeading(theUserInput, availableDirections);

                    System.out.println(heroHeading);
                    if(heroHeading.equals("saveGame")){
                        try {
                            ConsoleOutput.printString("Saving Game....");
                            String fileName = UserInputValidate.getFileName(new Scanner(System.in));
                            saveGame(fileName, theDungeon, theHero);
                            ConsoleOutput.printString("Thank you for playing the game! The session has been saved to " + fileName);
                            System.exit(0);
                        } catch (IOException e) {
                            ConsoleOutput.printString("ERROR SAVING GAME.");
                        }
                    } else {
                        HeroController.moveCharacter(theHero, heroHeading);
                    }

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

            MonsterFactory monsterFactory = new MonsterFactory();
            Monster monster;
            if(theRoom.getHasPillar()){
                monster = monsterFactory.getRandomGuardMonster();
            } else if(theRoom.hasRoomType(RoomType.EXIT)){
                monster = monsterFactory.getRandomBossMonster();
            } else {
                monster = monsterFactory.getRandomNormalMonster();
            }

            initiateFight(theHero, monster, theDungeon, theRoom);
        }

        if (theRoom.hasRoomType(RoomType.PIT)) {
            ConsoleOutput.printString(theHero.getCharacter_Name() + " fell into a pit\n");
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
            ConsoleOutput.printString("\n\t\t\t Round: " + roundCounter + "\n");
            ConsoleOutput.printString("Player HP: " + theHero.getCharacter_HealthPoints() + "\t\t Monster's HP: " + theMonster.getCharacter_HealthPoints() + "\n");
            theHero.resetAttackSpeed(theMonster);
            int attackChoice = 0;
            while(theHero.canAttack(theMonster)) {
                attackChoice = UserInputValidate.attackChoice(theHero, theUserInput);
                ConsoleOutput.printString(theHero.attacks(theMonster, attackChoice));
                ConsoleOutput.printString(theMonster.attacks(theHero));
            }

//            ConsoleOutput.printString(theMonster.attacks(theHero));
            if(attackChoice == 3) {
                ConsoleOutput.printString("You turned your back on the monster and\n");
                ConsoleOutput.printString("  now your soul is within his grasp.\n");
                break;
            }
            roundCounter++;
            ConsoleOutput.printString("\n----------END OF ROUND----------\n");
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






        if (north) {
            availableChoices.add(Directions.NORTH.direction);

        }if (south) {
            availableChoices.add(Directions.SOUTH.direction);

        }if (east) {
            availableChoices.add(Directions.EAST.direction);

        }if (west) {
            availableChoices.add(Directions.WEST.direction);

        }
        return availableChoices;

    }
}
//END
