
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
import java.nio.file.Path;
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
 * @authors Varun Parbhakar, Austin Luu, Yacine Bennour
 * @version 06/07/2022
 */
public class DungeonAdventure implements Serializable {
    @Serial
    private static final long serialVersionUID = 5586613526209979670L;
    // Fix some validation in File Name
    public static void main(String[] args) throws IOException, ClassNotFoundException {
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


                    hero.setCharacterLocation(myDungeon.getEntrancePoint());

                    if (hero.getCharacter_Name().equals("Varun")  || hero.getCharacter_Name().equals("Austin")) {
                        ConsoleOutput.printString("CHEATS ARE ACTIVE!\n");
                        myDungeon.setMyCheatEnabled();
                    }
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
                    ConsoleOutput.printString("Thank you for playing!");

                } 
            }
        } else {
            ConsoleOutput.printString("A wise choice, now MOVE ALONG!\n");
        }
    }

    /**
     * This method prompts to the user, asking them which hero they would like
     * to choose.
     * @return Hero
     */
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

    /**
     * When called it asks the user which file to reload and reloads
     * that saved game's files from local storage.
     * @return the local game's files
     */
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

    /**
     * This method is used for saving the game with hero's location
     * its attributes.
     * @param theSaveName (Name of the Save File)
     * @param theDungeon (The Dungeon)
     * @param theHero (The Hero Object)
     * @throws IOException
     */
    public static void saveGame(String theSaveName,final Dungeon theDungeon,final Hero theHero) throws IOException {
        if(theSaveName.equals("")){
            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            theSaveName = dateFormat.format(date);
        }
        String fileDirectory = "./src/main/java/Saves/" + theSaveName;

        Path pathOfFile = Path.of(fileDirectory);

        if(!Files.exists(pathOfFile)){
            Files.createDirectories(pathOfFile);
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


        if (theDungeon.isCheatEnabled()) {
            theDungeon.revealAll();
        }

        if (theHero.alive()) {
            Point location = theHero.getCharacterLocation();
            Room myRoom = theDungeon.getContent(theHero.getCharacterLocationY(),theHero.getCharacterLocationX());

            myRoom.exploreTheRoom();
            myRoom.addTo_MyRoomInventory(RoomType.PLAYER);

            checkHeroSatchel(theHero, theDungeon);
            ConsoleOutput.printString(theDungeon + "\n");

            checkRoom(theHero, myRoom, theDungeon);
            ConsoleOutput.printString("This room has: ");
            ConsoleOutput.printString(myRoom.showMyRoomInventory() + "\n");



            if (!isHeroAtExit(myRoom, theHero, theUserInput,theDungeon)){
                if (!theHero.alive()){
                    String output = "\nYou came here with such life yet here you lie\n\t\t\t\tlifeless.\nBetter Luck Next time!";
                    ConsoleOutput.printString(output + "\n");

                } else {
                    HeroController.itemPickUp(myRoom, theHero);
                    ConsoleOutput.printString(theHero + "\n");
                    ConsoleOutput.printString(theHero.getCharacter_Name()+ "'s Inventory:");
                    ConsoleOutput.printString(theHero.getHeroSatchel() + "\n\n");

                    myRoom.removeMyTypes(RoomType.PLAYER);

                    ArrayList<String> availableDirections = availableDirections(location, theDungeonSize);
                    String heroHeading = UserInputValidate.heroDirectionHeading(theUserInput, availableDirections);


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


    /**
     * This method is responsible for checking what is in the hero's satchel.
     * This method prompts the user to use the item from their inventory.
     * @param theHero
     * @param theDungeon
     */
    public static void checkHeroSatchel (final Hero theHero, final Dungeon theDungeon) {
        Scanner theUserInput = new Scanner(System.in);

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
     * @param theDungeon
     * @return (Boolean, if the player is on the exit or not)
     */
    public static boolean isHeroAtExit(final Room theRoom,
                                       final Hero theHero,
                                       final Scanner theUserInput,
                                       final Dungeon theDungeon) {
        if (theRoom.getMyRoomInventory().contains(RoomType.EXIT.type)) {
            ConsoleOutput.printString("You are on the exit!\n");


            if (theHero.hasCollectedEveryPillar()) {
                ConsoleOutput.printString("Would you like to exit the dungeon?\n");

                if (UserInputValidate.getYN(theUserInput)) {
                    ConsoleOutput.printString("\t\t\tYou did great out there!\n");
                    ConsoleOutput.printString("But there is one more challenge you have to face.\n");

                    MonsterFactory monsterFactory = new MonsterFactory();
                    Monster monster = monsterFactory.getRandomBossMonster();

                    ConsoleOutput.printString("\t\t\t\tMeet "+monster.getCharacter_Name()+".\n");
                    initiateFight(theHero, monster, theDungeon, theRoom);

                    ConsoleOutput.printString("\t\t\tCongratulations you have beaten the game!!!\n");
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

    /**
     * This method responsible for initiating the combat between hero and monster.
     * @param theHero
     * @param theMonster
     * @param theDungeon
     * @param theRoom
     */
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
     * @param theLocation (The location of the hero inside the dungeon)
     * @param theSize (The size of the dungeon)
     * @return (The list contains the valid directions that a user can take)
     */
    public static ArrayList<String> availableDirections(final Point theLocation,
                                                        final int theSize) {

        ArrayList<String> availableChoices = new ArrayList<>();

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

