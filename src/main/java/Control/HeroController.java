package Control;

import Model.Characters.DungeonCharacter;
import Model.Characters.Hero;
import Model.Characters.Warrior;
import View.ConsoleOutput;

import java.util.Scanner;
//maybe rename this to hero controller
public class HeroController {

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
 public void damageTakenHero(Hero theHero, int theDamageTaken){
        if (!theHero.damageTaken(theDamageTaken)){
            ConsoleOutput.printString(theHero.getCharacter_Name() + " blocked the attack!\n");
        }
 }

    public static void main(String[] args) {
        Warrior testWarrior=new Warrior("sup");
        System.out.println(GetChoiceCreator(testWarrior ));
    }
}
