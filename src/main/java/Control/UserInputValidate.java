package Control;

import Model.Characters.Hero;
import View.ConsoleOutput;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserInputValidate {


    /**
     * This method is responsible for making sure the user inputs a name for the hero,
     * this method is also performs input validation.
     *
     * @return String (The name of the hero)
     */
    public static String heroName(final Scanner userInput) {
        boolean correctAnswer = false;
        String heroName = null;

        while (!correctAnswer) {
            ConsoleOutput.printString("\nPlease enter a name for your hero(No Spaces): ");
            heroName = userInput.nextLine();

            if(heroName.matches(".*[^[a-zA-Z]].*")) {
                ConsoleOutput.printString("Invalid input\n");
            } else {
                correctAnswer = true;
            }
        }
        return heroName;

    }

    /**
     * This method asks the user for a yes or a no answer and insures the input is correct.
     * @param theUserInput (Scanner)
     * @return (Boolean YES or No)
     */
    public static boolean getYN(final Scanner theUserInput) {
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
     * This method insures that the user selects only the correct direction present in the allowable
     * direction.
     * @param userInput (Scanner)
     * @return (Validated direction that a user can go into)
     */
    public static String heroDirectionHeading(final Scanner userInput,
                                              final ArrayList<String> availableDirection){
        String choices = "Please select your movement(n for North, s for South, e for East, w for West or k for Map Legend) or type saveGame to save your current game.";
        ArrayList<String> choiceList = availableDirection;
        String direction = null;
        boolean correctAnswer = false;

        // Input Validation
        while (!correctAnswer) {

            ConsoleOutput.printString(choices + "\n");
            ConsoleOutput.printString("These are your available moves " + choiceList + ": ");

            if (userInput.hasNext()) {
                direction = userInput.next();

                if (direction.equals(Directions.NORTH.direction) || direction.equals("saveGame") || direction.equals(Directions.SOUTH.direction)|| direction.equals(Directions.EAST.direction) ||direction.equals(Directions.WEST.direction)|| direction.equals("k")) {
                    if (direction.equals("k")){
                        //When K is return to the main function, it prints the legend of the map
                        return direction; // This needs to be checked

                    }
                    if(direction.equals("saveGame")){
                        return direction;
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
     * This method insures that the user selects the correct attack choice and validates their input.
     * @param theHero
     * @param input
     * @return
     */
    public static int attackChoice(Hero theHero, Scanner input){
        int selection = 0;
        int selectTooSmall = 0;
        int selectTooBig = 3;
        boolean correctAnswer = false;
        while (!correctAnswer) {
            ConsoleOutput.choicePrinter(theHero);
            if (input.hasNextInt()) {
                selection = input.nextInt();
                if (selection <= selectTooSmall || selection > selectTooBig) {
                    ConsoleOutput.invalidInput();
                } else {

                    correctAnswer = true;
                }


            } else {
                ConsoleOutput.invalidInput();
                input.next();
            }

        }
        return selection;
    }

    public static boolean saveGame(final Scanner userInput){
        ConsoleOutput.printString("Would you like to save your game? (y for yes or any other key for no.)");
        return userInput.next().equals("y");
    }

    public static String getFileName(final Scanner userInput){
        boolean correctAnswer = false;
        String inputtedFileName = "";

        ConsoleOutput.printString("\n\nPlease enter a file name.\n");

        while (!correctAnswer) {

            ConsoleOutput.printString("NOTE: File name cant contain: <, >, :, \", /, \\, |, ?, *\n");
            inputtedFileName = userInput.next();
            String regex = "[.<>:\"\\/\\\\\\|\\?*]+";
            Matcher matcher = Pattern.compile(regex).matcher(inputtedFileName);


            if (!matcher.find()) {
                correctAnswer = true;
            } else {
                ConsoleOutput.printString("\n\nPlease try again, inputted file name has wrong keys.");
            }
        }

        return inputtedFileName;
    }

    public static int getLoadNumber(final Scanner userInput, int high){
        boolean correctAnswer = false;
        int loadNumber = 0;


        while (!correctAnswer) {
            ConsoleOutput.printString("\nPlease enter the save number between 0 and " + high + "\n");
            loadNumber = userInput.nextInt();

            if (loadNumber >= 0 && loadNumber <= high) {
                correctAnswer = true;
            } else {
                ConsoleOutput.printString("\n\nPlease try again, inputted file name has wrong keys.\n");
            }
        }

        return loadNumber;
    }

    public static int heroSelector(String name, Scanner input){
        // 1: Thief, 2: Warrior, 3:Priestess
        int selection = 0;
        int selectTooSmall = 0;
        int selectTooBig = 3;
        boolean correctAnswer = false;
        while (!correctAnswer) {
            ConsoleOutput.heroSelection(name);
            if (input.hasNextInt()) {
                selection = input.nextInt();
                if (selection <= selectTooSmall || selection > selectTooBig) {
                    ConsoleOutput.invalidInput();
                } else {
                    correctAnswer = true;
                }
            } else {
                ConsoleOutput.invalidInput();
                input.next();
            }

        }
        return selection;
    }


//    public static boolean loadGame(final Scanner userInput){
//        ConsoleOutput.printString("Would you like to load your saved games? (y for yes or any other key for no.)");
//        if(userInput.next().equals("y")){
//
//
//        } else{
//
//        }
//
//    }
}
//END
