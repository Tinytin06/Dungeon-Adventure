package View;

import Model.Characters.Hero;

/**
 * This is the console output class in charge of printing to the console (MVC).
 * @authors Varun Parbhakar, Austin Luu, Yacine Bennour.
 * @version 06/07/2022
 */
public class ConsoleOutput {

    /**
     * Prints the attack choices to the user.
     */
    public static void choicePrinter(Hero myHero){
        String choices = ("\n" + myHero.getCharacter_Name() + " select your attack:" +
                "\n1: Special Attack" +
                "\n2: Normal Attack" +
                "\n3: Run Away");
        System.out.println(choices);
    }


    /**
     * Prints the different hero choices they can select to the user.
     */
    public static void heroSelection(String myHero) {
        String choices = ("\n" + myHero + ", What kind of hero do you want to be? " +
                "\n1: Thief" +
                "\n2: Warrior" +
                "\n3: Priestess\n" +
                "Please choose from the selection: ");
        System.out.print(choices);
    }

    /**
     * Prints the Invalid choice to the user.
     */
    public static void invalidInput(){
        System.out.println("Invalid Choice");
    }

    /**
     * Prints the inputted string to the user.
     * @param  theString: string to output to user.
     */
    public static void printString(final String theString) {
        System.out.print(theString);
    }

    /**
     * Prints the introduction to the user.
     */
    public static void introduction() {
        System.out.println("------------------ Welcome!!! -------------------");
        System.out.println("---------------- Hear Ye Hear Ye ----------------");
        System.out.println("A long time ago, a warrior had challenged" +
                " the creator, \n the programmer himself, to a game of " +
                "Labyrinth, but \n       he lost and he paid with his life. \n" +
                "\nNo one has challenged him ever again, but you my child,");
        System.out.println("You wish to challenge him?");
    }
}
