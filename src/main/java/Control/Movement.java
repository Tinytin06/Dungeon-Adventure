package Control;

import Model.Characters.DungeonCharacter;
import Model.Characters.Hero;
import Model.Characters.Warrior;
import View.ConsoleOutput;

import java.util.Scanner;

public class Movement   {

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


    void setDamageTaken(DungeonCharacter theDungeonCharacter, int damageAmount){
        if(theDungeonCharacter.damageTaken(damageAmount)){

        }
    }

    public static void main(String[] args) {
        Warrior testWarrior=new Warrior("sup");
        System.out.println(GetChoiceCreator(testWarrior ));
    }
}
