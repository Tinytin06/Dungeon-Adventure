package View;

import Model.Characters.Hero;

import java.util.Scanner;

public class ConsoleOutput {

    public static void choicePrinter(Hero myHero){
        String choices = (myHero.getCharacter_Name() + " select your attack:" +
                "\n1: Special Attack" +
                "\n2: Normal Attack" +
                "\n3: Run Away");
        System.out.println(choices);
    }
    public static void invalidInput(){
        System.out.println("Invalid Choice");
    }
    public static void printString(final String theString) {
        System.out.print(theString);
    }

    public static void introduction(final String theUserInput) {
        System.out.println("------------------ Welcome!!! -------------------");
        System.out.println("---------------- Hear Ye Hear Ye ----------------");
        System.out.println("A long time ago, a warrior had challenged" +
                " the creator, \n the programmer himself, to a game of " +
                "Labyrinth, but \n       he lost and he paid with his life. \n" +
                "\nNo one has challenged him ever again, but you my child,");
        System.out.println("You wish to challenge him?");
    }
}
