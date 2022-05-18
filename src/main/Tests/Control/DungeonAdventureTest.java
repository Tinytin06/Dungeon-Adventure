package Control;

import Model.Characters.Hero;
import Model.Characters.Warrior;

import Model.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DungeonAdventureTest {
    @Test
    void driverTest() throws FileNotFoundException {
        Hero testHero = new Warrior("Test");
        Scanner theUserInput = new Scanner("DriverTestInput.txt");
        int theDungeonSize = 3;
        File testDungeonFile = new File("DriverTest.txt");
        Dungeon testDungeon = new Dungeon(testDungeonFile,3);
        testHero.setCharacterLocation(testDungeon.getEntrancePoint());
        DungeonAdventure.driver(theUserInput,testHero,theDungeonSize,testDungeon);
        DungeonAdventure.driver(theUserInput,testHero,theDungeonSize,testDungeon);

    }
}
