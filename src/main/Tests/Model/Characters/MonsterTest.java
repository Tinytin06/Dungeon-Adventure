package Model.Characters;

import Model.Characters.Monsters.Skeleton;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class MonsterTest {

    /**
     * Monster cant heal when its dead thus should be an empty string.
     */
    @Test
    void heal() {
        Monster monster = new Skeleton("test_monster");
        monster.killCharacter();
        assertEquals(monster.heal(), "");
    }

    /**
     * Monster can heal when its alive thus should be a string containing it has or tried to heal.
     */
    @Test
    void heal1() {
        Monster monster = new Skeleton("test_monster");
        String output = monster.heal();
        assertTrue(output.contains("has healed himself") || output.contains("tried to heal"));
    }

    /**
     * Cant name monster null.
     * Expected: IllegalArgumentException to be thrown
     */
    @Test
    void nameValidator() {
        Monster monster = new Skeleton("test_monster");
        assertThrows(IllegalArgumentException.class, ()-> monster.nameValidator(null));
    }

    /**
     * Chance to heal should be set.
     * Expected: chance to heal value should be our input.
     */
    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.2, 0.0, 0.5, 0.999})
    void chance2Heal_Validator(double input) {
        Monster test = new Skeleton("test_monster");
        test.chance2Heal_Validator(input);
        assertEquals(test.getMyChance2Heal(), input);
    }

    /**
     * Testing for when input chance to heal is negative.
     * Expected: IllegalArgumentException to be thrown
     */
    @ParameterizedTest
    @ValueSource(doubles = {-0.1, 1.1})
    void chance2Heal_Validator1(double input) {
        Monster test = new Skeleton("test_monster");
        assertThrows(IllegalArgumentException.class, ()-> test.chance2Heal_Validator(input));
    }



    /**
     * Min heal points should be set.
     * Expected: Min heal points value should be our input.
     */
    @ParameterizedTest
    @ValueSource(ints = {1, 2, Integer.MAX_VALUE})
    void heal_MinPoints_Validator(int input) {
        Monster test = new Skeleton("test_monster");
        test.heal_MinPoints_Validator(input);
        assertEquals(test.getMyHeal_MinPoints(), input);
    }

    /**
     * Min heal points should throw exception when its 0 or negative.
     * Expected: IllegalArgumentException to be thrown
     */
    @ParameterizedTest
    @ValueSource(ints = {0, -1, Integer.MIN_VALUE})
    void heal_MinPoints_Validator1(int input) {
        Monster test = new Skeleton("test_monster");
        assertThrows(IllegalArgumentException.class, ()-> test.heal_MinPoints_Validator(input));
    }


    /**
     * Max heal points should be set.
     * Expected: Max heal points value should be our input.
     */
    @ParameterizedTest
    @ValueSource(ints = {1, 2, Integer.MAX_VALUE})
    void heal_MaxPoints_Validator(int input) {
        Monster test = new Skeleton("test_monster");
        test.heal_MaxPoints_Validator(input);
        assertEquals(test.getMyHeal_MaxPoints(), input);
    }

    /**
     * Max heal points should throw exception when its 0 or negative.
     * Expected: IllegalArgumentException to be thrown
     */
    @ParameterizedTest
    @ValueSource(ints = {0, -1, Integer.MIN_VALUE})
    void heal_MaxPoints_Validator1(int input) {
        Monster test = new Skeleton("test_monster");
        assertThrows(IllegalArgumentException.class, ()-> test.heal_MaxPoints_Validator(input));
    }


    /**
     * Testing for when the heal range validator is valid (min < max).
     */
    @ParameterizedTest
    @CsvSource({"0,100", "100,200", "130,191"})
    void healRangeValidator(int theC_MinDamage, int theC_MaxDamage) {
        Monster test = new Skeleton("test_monster");
        assertTrue(test.healRangeValidator(theC_MinDamage, theC_MaxDamage));
    }


    /**
     * Testing for when the heal range validator isn't valid (max < min).
     */
    @ParameterizedTest
    @CsvSource({"1,0", "200,100", "151,121"})
    void healRangeValidator1(int theC_MinDamage, int theC_MaxDamage) {
        Monster test = new Skeleton("test_monster");
        assertThrows(IllegalArgumentException.class, ()-> test.healRangeValidator(theC_MinDamage, theC_MaxDamage));
    }
}