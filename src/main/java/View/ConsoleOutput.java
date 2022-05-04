package View;

import Model.Characters.Hero;

import java.util.Scanner;

public class ConsoleOutput {
    public final static Scanner userInput = new Scanner(System.in);
    public static Scanner choicePrinter(Hero myHero){
        String choices = (myHero.getCharacter_Name() + " select your attack:" +
                "\n1: Special Attack" +
                "\n2: Normal Attack" +
                "\n3: Run Away");
        System.out.println(choices);
        return userInput;
    }
    public static void InvalidInput(){
        System.out.println("Invalid Choice");
    }
}
