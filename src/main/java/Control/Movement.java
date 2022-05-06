package Control;

import Model.Characters.Hero;
import Model.Characters.Warrior;
import View.ConsoleOutput;

import java.util.Scanner;

public class Movement   {



    public static int GetChoiceCreator (Hero theHero){
    int selection = 0;
        boolean correctAnswer = false;
        while (!correctAnswer) {
            Scanner input = ConsoleOutput.choicePrinter(theHero);

            if (input.hasNextInt()) {
                selection = input.nextInt();
                if (selection <= 0 || selection > 3) {
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

    public static void main(String[] args) {
        Warrior testWarrior=new Warrior("sup");
        System.out.println(GetChoiceCreator(testWarrior ));
    }
}
