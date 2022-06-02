package Model.Characters;

import Model.Characters.Monsters.Skeleton;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class MonsterTest {

    @Test
    void setMyHealth() {
//        Monster test = new
    }

    @Test
    void healGenerator() {
    }

    @Test
    void damageTaken() {
    }

    @Test
    void heal() {
    }

    @Test
    void attacks() {
    }


    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.2, 0.0, 0.5, 0.999})
    void chance2Heal_Validator(double input) {
        Monster test = new Skeleton("test_monster");
        test.chance2Heal_Validator(input);
        assertEquals(test.getMyChance2Heal(), input);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-0.1, 1.1})
    void chance2Heal_Validator1(double input) {
        Monster test = new Skeleton("test_monster");
        assertThrows(IllegalArgumentException.class, ()-> test.chance2Heal_Validator(input));
    }



    @ParameterizedTest
    @ValueSource(ints = {1, 2, Integer.MAX_VALUE})
    void heal_MinPoints_Validator(int input) {
        Monster test = new Skeleton("test_monster");
        test.heal_MinPoints_Validator(input);
        assertEquals(test.getMyHeal_MinPoints(), input);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, Integer.MIN_VALUE})
    void heal_MinPoints_Validator1(int input) {
        Monster test = new Skeleton("test_monster");
        assertThrows(IllegalArgumentException.class, ()-> test.heal_MinPoints_Validator(input));
    }



    @ParameterizedTest
    @ValueSource(ints = {1, 2, Integer.MAX_VALUE})
    void heal_MaxPoints_Validator(int input) {
        Monster test = new Skeleton("test_monster");
        test.heal_MaxPoints_Validator(input);
        assertEquals(test.getMyHeal_MaxPoints(), input);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, Integer.MIN_VALUE})
    void heal_MaxPoints_Validator1(int input) {
        Monster test = new Skeleton("test_monster");
        assertThrows(IllegalArgumentException.class, ()-> test.heal_MaxPoints_Validator(input));
    }

    @ParameterizedTest
    @CsvSource({"0,100", "100,200", "130,191"})
    void healRangeValidator(int theC_MinDamage, int theC_MaxDamage) {
        Monster test = new Skeleton("test_monster");
        assertTrue(test.healRangeValidator(theC_MinDamage, theC_MaxDamage));
    }

    @ParameterizedTest
    @CsvSource({"1,0", "200,100", "151,121"})
    void healRangeValidator1(int theC_MinDamage, int theC_MaxDamage) {
        Monster test = new Skeleton("test_monster");
        assertThrows(IllegalArgumentException.class, ()-> test.healRangeValidator(theC_MinDamage, theC_MaxDamage));
    }
}