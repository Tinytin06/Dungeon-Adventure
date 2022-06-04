package Control;

import Model.Characters.Hero;
import Model.Room;
import Model.RoomType;
import View.ConsoleOutput;

import java.util.ArrayList;
import java.util.Scanner;



//maybe rename this to hero controller
public class HeroController {

    public static void moveCharacter(final Hero theHero, String theHeroHeading) {
        Scanner theUserInput = new Scanner(System.in);
        String direction = theHeroHeading;

        if (direction.equals("k")){
            // Print the legend for dungeon
            ConsoleOutput.printString(RoomType.legend() + "\n");
        } else if (direction.equals(Directions.NORTH.direction)){
            theHero.translateCharacterY(-1);

        } else if (direction.equals(Directions.SOUTH.direction)){
            theHero.translateCharacterY(1);

        } else if (direction.equals(Directions.EAST.direction)){
            theHero.translateCharacterX(1);

        } else if (direction.equals(Directions.WEST.direction)){
            theHero.translateCharacterX(-1);

        }
    }

    /**
     * This method takes the items from the room inventory and deposits the items into the hero's inventory.
     *
     * @param theRoom (The room object)
     * @param theHero (The hero object)
     */
    public static void itemPickUp(final Room theRoom,
                                  final Hero theHero) {


        ArrayList<RoomType> deleteItems = new ArrayList<>();

//There needs to be a way to move the item from the room to the hero's satchel.


        //There is a chance that a single room will contain multiple items, so we use this structure.
        for( Character theItem : theRoom.getMyRoomInventory()) {
            if(RoomType.getMyPotions().contains(theItem)) {

            }


            if (theItem == RoomType.INHERITANCE.type){
                theHero.addItem2Satchel(RoomType.INHERITANCE);
                deleteItems.add(RoomType.INHERITANCE);
                ConsoleOutput.printString("You have collected the Inheritance Pillar!\n");

            } if (theItem == RoomType.ABSTRACTION.type){
                theHero.addItem2Satchel(RoomType.ABSTRACTION);
                deleteItems.add(RoomType.ABSTRACTION);
                ConsoleOutput.printString("You have collected the Abstraction Pillar!\n");

            } if (theItem == RoomType.POLYMORPHISM.type){
                theHero.addItem2Satchel(RoomType.POLYMORPHISM);
                deleteItems.add(RoomType.POLYMORPHISM);
                ConsoleOutput.printString("You have collected the Polymorphism Pillar!\n");

            } if (theItem == RoomType.ENCAPSULATION.type){
                theHero.addItem2Satchel(RoomType.ENCAPSULATION);
                deleteItems.add(RoomType.ENCAPSULATION);
                ConsoleOutput.printString("You have collected the Encapsulation Pillar!\n");

            } if (theItem == RoomType.HEALING.type){
                theHero.addItem2Satchel(RoomType.HEALING);
                deleteItems.add(RoomType.HEALING);
                ConsoleOutput.printString("You have picked up the Healing Potion!\n");

            } if (theItem == RoomType.VISION.type){
                theHero.addItem2Satchel(RoomType.VISION);
                deleteItems.add(RoomType.VISION);
                ConsoleOutput.printString("You have picked up the Vision Potion!\n");

            }
        }

        for(RoomType item : deleteItems) {
            theRoom.removeMyTypes(item);
        }

    }

}