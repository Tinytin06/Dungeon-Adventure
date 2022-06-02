package Model.Characters;

import Model.Characters.Monsters.Skeleton;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class DungeonCharacterTest {

    @Test
    void nameValidator() {
        DungeonCharacter test = new Skeleton("test_monster");
        test.nameValidator("test");
        assertEquals(test.getCharacter_Name(), "test");
    }

    @Test
    void nameValidator1() {
        DungeonCharacter test = new Skeleton("test_monster");
        assertThrows(IllegalArgumentException.class, ()-> test.nameValidator(null));
    }

    @Test
    void nameValidator2() {
        DungeonCharacter test = new Skeleton("test_monster");
        assertThrows(IllegalArgumentException.class, ()-> test.nameValidator(""));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 100, Integer.MAX_VALUE})
    void healthValidator(int input) {
        DungeonCharacter test = new Skeleton("test_monster");
        test.healthValidator(input);
        assertEquals(test.getCharacter_HealthPoints(), input);
    }


    @ParameterizedTest
    @ValueSource(ints = {-1, Integer.MIN_VALUE, 0})
    void healthValidator1(int input) {
        DungeonCharacter test = new Skeleton("test_monster");
        assertThrows(IllegalArgumentException.class, ()-> test.healthValidator(input));
    }


    @ParameterizedTest
    @ValueSource(ints = {1, 100, Integer.MAX_VALUE})
    void minDamageValidator(int input) {
        DungeonCharacter test = new Skeleton("test_monster");
        test.minDamageValidator(input);
        assertEquals(test.getMyCharacter_MinDamage(), input);
    }


    @ParameterizedTest
    @ValueSource(ints = {-1, Integer.MIN_VALUE, 0})
    void minDamageValidator1(int input) {
        DungeonCharacter test = new Skeleton("test_monster");
        assertThrows(IllegalArgumentException.class, ()-> test.minDamageValidator(input));
    }


    @ParameterizedTest
    @ValueSource(ints = {1, 100, Integer.MAX_VALUE})
    void maxDamageValidator(int input) {
        DungeonCharacter test = new Skeleton("test_monster");
        test.maxDamageValidator(input);
        assertEquals(test.getMyCharacter_MaxDamage(), input);
    }


    @ParameterizedTest
    @ValueSource(ints = {-1, Integer.MIN_VALUE, 0})
    void maxDamageValidator1(int input) {
        DungeonCharacter test = new Skeleton("test_monster");
        assertThrows(IllegalArgumentException.class, ()-> test.maxDamageValidator(input));
    }

    @ParameterizedTest
    @CsvSource({"0,100", "100,200", "130,191"})
    void damageRangeValidator(int theC_MinDamage, int theC_MaxDamage) {
        DungeonCharacter test = new Skeleton("test_monster");
        assertTrue(test.damageRangeValidator(theC_MinDamage, theC_MaxDamage));
    }

    @ParameterizedTest
    @CsvSource({"1,0", "200,100", "151,121"})
    void damageRangeValidator1(int theC_MinDamage, int theC_MaxDamage) {
        DungeonCharacter test = new Skeleton("test_monster");
        assertThrows(IllegalArgumentException.class, ()-> test.damageRangeValidator(theC_MinDamage, theC_MaxDamage));
    }


    @ParameterizedTest
    @ValueSource(ints = {1, 100, Integer.MAX_VALUE})
    void attackSpeedValidator(int input) {
        DungeonCharacter test = new Skeleton("test_monster");
        test.attackSpeedValidator(input);
        assertEquals(test.getCharacter_AttackSpeed(), input);
    }


    @ParameterizedTest
    @ValueSource(ints = {-1, Integer.MIN_VALUE, 0})
    void attackSpeedValidator1(int input) {
        DungeonCharacter test = new Skeleton("test_monster");
        assertThrows(IllegalArgumentException.class, ()-> test.attackSpeedValidator(input));
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.2, 0.5, 0.999, 1})
    void chance2AttackValidator(double input) {
        DungeonCharacter test = new Skeleton("test_monster");
        test.chance2AttackValidator(input);
        assertEquals(test.getMyCharacter_AttackDamageProbability(), input);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-0.1, 0, 1.1})
    void chance2AttackValidator1(double input) {
        DungeonCharacter test = new Skeleton("test_monster");
        assertThrows(IllegalArgumentException.class, ()-> test.chance2AttackValidator(input));
    }

    @ParameterizedTest
    @CsvSource({"0,100", "100,1000"})
    void damageRangeCalculator(int theC_MinDamage, int theC_MaxDamage) {
        assertTrue(DungeonCharacter.damageRangeCalculator(theC_MinDamage, theC_MaxDamage) >= theC_MinDamage);
        assertTrue(DungeonCharacter.damageRangeCalculator(theC_MinDamage, theC_MaxDamage) <= theC_MaxDamage);
    }

    @Test
    void alive() {
        DungeonCharacter test = new Skeleton("test_monster");
        test.setMyCharacter_HealthPoints(100);
        assertTrue(test.alive());
    }

    @Test
    void alive1() {
        DungeonCharacter test = new Skeleton("test_monster");
        test.damageTaken(200);
        assertFalse(test.alive());
    }

    @ParameterizedTest
    @CsvSource({"0,30, 100", "40, 40, 100"})
    void setMyCharacter_HealthPoints(int prevHealth, int toAddHealth, int maxHealth) {
        DungeonCharacter test = new Skeleton("test_monster");
        test.healthValidator(maxHealth);
        test.setMyCharacter_HealthPoints(toAddHealth);
        int expectedHealth = prevHealth + toAddHealth;

        assertEquals(test.getCharacter_HealthPoints(), expectedHealth);
    }


    @ParameterizedTest
    @CsvSource({"10,90, 100", "20, 200, 100"})
    void setMyCharacter_HealthPoints1(int prevHealth, int toAddHealth, int maxHealth) {
        DungeonCharacter test = new Skeleton("test_monster");
        test.healthValidator(maxHealth);
        test.setMyCharacter_HealthPoints(toAddHealth);

        assertEquals(test.getCharacter_HealthPoints(), maxHealth);
    }

    @Test
    void killCharacter() {
        DungeonCharacter test = new Skeleton("test_monster");
        test.setMyCharacter_HealthPoints(100);
        assertEquals(test.getCharacter_HealthPoints(), 100);
        test.killCharacter();
        assertEquals(test.getCharacter_HealthPoints(), 0);
    }

    @Test
    void attacks() {
        DungeonCharacter test = new Skeleton("test_monster");
        assertThrows(IllegalArgumentException.class, ()-> test.attacks(null));
    }

    @Test
    void attacks1() {
        DungeonCharacter test = new Skeleton("test_monster");
        DungeonCharacter enemy = new Skeleton("temp");
        assertNotEquals(test.attacks(enemy), "");
        test.killCharacter();
        assertEquals(test.attacks(enemy), "");
    }


    @Test
    void damageTaken() {
        DungeonCharacter test = new Skeleton("test_monster");
        assertThrows(IllegalArgumentException.class, ()-> test.damageTaken(-1));
    }


    @Test
    void damageTaken1() {
        DungeonCharacter test = new Skeleton("test_monster");
        test.setMyCharacter_HealthPoints(100);
        int damage = 101;

        String expected = "\ntest_monster Has taken <<" + damage + ">> damage\n" +
                "test_monster has fainted\n";
        String output = test.damageTaken(damage);

        assertEquals(output, expected);
        assertEquals(test.getCharacter_HealthPoints(), 0);
    }


    @Test
    void damageTaken2() {
        DungeonCharacter test = new Skeleton("test_monster");
        test.setMyCharacter_HealthPoints(100);
        int damage = 20;

        String output = test.damageTaken(damage);
        String expected = "\ntest_monster Has taken <<" + damage + ">> damage\n" +
                "test_monster's health is now at <<" + (test.getCharacter_HealthPoints()) + ">>\n";

        assertTrue(output.contains(expected));
        assertNotEquals(test.getCharacter_HealthPoints(), 0);
    }

}