package Control;

import Model.Characters.Hero;
import View.ConsoleOutput;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

import static Control.DungeonAdventure.availableDirectionChoices;

public class UserInputValidate {
    public static int GetChoiceCreator (Hero theHero){
        int selection = 0;
        int selectTooSmall = 0;
        int selectTooBig = 3;
        boolean correctAnswer = false;
        while (!correctAnswer) {
            Scanner input = new Scanner(System.in);;
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
            ConsoleOutput.printString("Please enter a name for your hero: \n");
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
}
