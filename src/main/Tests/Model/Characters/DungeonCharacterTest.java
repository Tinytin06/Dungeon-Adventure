package Model.Characters;

import Model.Characters.Monsters.Skeleton;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class DungeonCharacterTest {

    /**
     * Testing for when the name inputted is valid
     */
    @Test
    void nameValidator() {
        DungeonCharacter test = new Skeleton("test_monster");
        test.nameValidator("test");
        assertEquals(test.getCharacter_Name(), "test");
    }

    /**
     * Testing for when the name inputted is null;
     * Expected: IllegalArgumentException to be thrown
     */
    @Test
    void nameValidator1() {
        DungeonCharacter test = new Skeleton("test_monster");
        assertThrows(IllegalArgumentException.class, ()-> test.nameValidator(null));
    }

    /**
     * Testing for when the name inputted is blank;
     * Expected: IllegalArgumentException to be thrown
     */
    @Test
    void nameValidator2() {
        DungeonCharacter test = new Skeleton("test_monster");
        assertThrows(IllegalArgumentException.class, ()-> test.nameValidator(""));
    }

    /**
     * Testing for when the health inputted is valid.
     */
    @ParameterizedTest
    @ValueSource(ints = {1, 100, Integer.MAX_VALUE})
    void healthValidator(int input) {
        DungeonCharacter test = new Skeleton("test_monster");
        test.healthValidator(input);
        assertEquals(test.getCharacter_HealthPoints(), input);
    }

    /**
     * Testing for when the health inputted is invalid (less than or equal 0).
     * Expected: IllegalArgumentException to be thrown
     */
    @ParameterizedTest
    @ValueSource(ints = {-1, Integer.MIN_VALUE, 0})
    void healthValidator1(int input) {
        DungeonCharacter test = new Skeleton("test_monster");
        assertThrows(IllegalArgumentException.class, ()-> test.healthValidator(input));
    }


    /**
     * Testing for when the min damage is legal ( more than 0).
     */
    @ParameterizedTest
    @ValueSource(ints = {1, 100, Integer.MAX_VALUE})
    void minDamageValidator(int input) {
        DungeonCharacter test = new Skeleton("test_monster");
        test.minDamageValidator(input);
        assertEquals(test.getMyCharacter_MinDamage(), input);
    }

    /**
     * Testing for when the min damage is illegal (less than or equal to 0).
     * Expected: IllegalArgumentException to be thrown
     */
    @ParameterizedTest
    @ValueSource(ints = {-1, Integer.MIN_VALUE, 0})
    void minDamageValidator1(int input) {
        DungeonCharacter test = new Skeleton("test_monster");
        assertThrows(IllegalArgumentException.class, ()-> test.minDamageValidator(input));
    }

    /**
     * Testing for when the max damage is legal ( more than 0).
     */
    @ParameterizedTest
    @ValueSource(ints = {1, 100, Integer.MAX_VALUE})
    void maxDamageValidator(int input) {
        DungeonCharacter test = new Skeleton("test_monster");
        test.maxDamageValidator(input);
        assertEquals(test.getMyCharacter_MaxDamage(), input);
    }

    /**
     * Testing for when the max damage is illegal (less than or equal to 0).
     * Expected: IllegalArgumentException to be thrown
     */
    @ParameterizedTest
    @ValueSource(ints = {-1, Integer.MIN_VALUE, 0})
    void maxDamageValidator1(int input) {
        DungeonCharacter test = new Skeleton("test_monster");
        assertThrows(IllegalArgumentException.class, ()-> test.maxDamageValidator(input));
    }

    /**
     * Testing for when the damage range validator is valid (min < max).
     */
    @ParameterizedTest
    @CsvSource({"0,100", "100,200", "130,191"})
    void damageRangeValidator(int theC_MinDamage, int theC_MaxDamage) {
        DungeonCharacter test = new Skeleton("test_monster");
        assertTrue(test.damageRangeValidator(theC_MinDamage, theC_MaxDamage));
    }

    /**
     * Testing for when the damage range validator is invalid (max < min).
     * Expected: IllegalArgumentException to be thrown
     */
    @ParameterizedTest
    @CsvSource({"1,0", "200,100", "151,121"})
    void damageRangeValidator1(int theC_MinDamage, int theC_MaxDamage) {
        DungeonCharacter test = new Skeleton("test_monster");
        assertThrows(IllegalArgumentException.class, ()-> test.damageRangeValidator(theC_MinDamage, theC_MaxDamage));
    }

    /**
     * Testing for when the attack speed validator is works (attack speed needs to be more than 0).
     */
    @ParameterizedTest
    @ValueSource(ints = {1, 100, Integer.MAX_VALUE})
    void attackSpeedValidator(int input) {
        DungeonCharacter test = new Skeleton("test_monster");
        test.attackSpeedValidator(input);
        assertEquals(test.getCharacter_AttackSpeed(), input);
    }

    /**
     * Testing for when the attack speed validator is doesn't work (attack speed needs less than or equal to 0).
     * Expected: IllegalArgumentException to be thrown
     */
    @ParameterizedTest
    @ValueSource(ints = {-1, Integer.MIN_VALUE, 0})
    void attackSpeedValidator1(int input) {
        DungeonCharacter test = new Skeleton("test_monster");
        assertThrows(IllegalArgumentException.class, ()-> test.attackSpeedValidator(input));
    }

    /**
     * Testing for when the chance to attack validator works (0 <= chance < 1).
     */
    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.2, 0.5, 0.999, 1})
    void chance2AttackValidator(double input) {
        DungeonCharacter test = new Skeleton("test_monster");
        test.chance2AttackValidator(input);
        assertEquals(test.getMyCharacter_AttackDamageProbability(), input);
    }

    /**
     * Testing for when the chance to attack validator doesn't works (0 >= chance or chance > 1).
     * Expected: IllegalArgumentException to be thrown
     */
    @ParameterizedTest
    @ValueSource(doubles = {-0.1, 0, 1.1})
    void chance2AttackValidator1(double input) {
        DungeonCharacter test = new Skeleton("test_monster");
        assertThrows(IllegalArgumentException.class, ()-> test.chance2AttackValidator(input));
    }

    /**
     * Testing for when the generated number is between min and max.
     */
    @ParameterizedTest
    @CsvSource({"0,100", "100,1000"})
    void damageRangeCalculator(int theC_MinDamage, int theC_MaxDamage) {
        int generatedNum = DungeonCharacter.damageRangeCalculator(theC_MinDamage, theC_MaxDamage);
        assertTrue(generatedNum >= theC_MinDamage);
        assertTrue(generatedNum <= theC_MaxDamage);
    }

    /**
     * Making sure alive works but checking characters health is more than 0.
     */
    @Test
    void alive() {
        DungeonCharacter test = new Skeleton("test_monster");
        test.setMyCharacter_HealthPoints(100);
        assertTrue(test.alive());
    }

    /**
     * Making sure alive outputs false when characters health is less than or equal to 0.
     * Default characters health = 100.
     * When damaged taken is 200.
     * Character's health should be less than 0 thus not alive.
     */
    @Test
    void alive1() {
        DungeonCharacter test = new Skeleton("test_monster");
        test.damageTaken(200);
        assertFalse(test.alive());
    }

    /**
     * Making sure setMyCharacter_HealthPoints works by using a new health value and adding to it.
     * Expected health should be previous + added Health.
     */
    @ParameterizedTest
    @CsvSource({"0,30, 100", "40, 40, 100"})
    void setMyCharacter_HealthPoints(int prevHealth, int toAddHealth) {
        DungeonCharacter test = new Skeleton("test_monster");

        test.killCharacter();
        test.setMyCharacter_HealthPoints(prevHealth);
        test.setMyCharacter_HealthPoints(toAddHealth);

        int expectedHealth = prevHealth + toAddHealth;

        assertEquals(test.getCharacter_HealthPoints(), expectedHealth);
    }


    /**
     * Making sure setMyCharacter_HealthPoints works.
     * When you exceed character max health it should just make the health be the character max health.
     */
    @ParameterizedTest
    @CsvSource({"10,90", "20, 200"})
    void setMyCharacter_HealthPoints1(int prevHealth, int toAddHealth) {
        DungeonCharacter test = new Skeleton("test_monster");


        test.killCharacter();
        test.setMyCharacter_HealthPoints(prevHealth);
        test.setMyCharacter_HealthPoints(toAddHealth);

        assertEquals(test.getCharacter_HealthPoints(), test.getMyCharacter_MaxHealthPoints());
    }

    /**
     * Making sure killCharacter works.
     * When called character's health points should be 0.
     */
    @Test
    void killCharacter() {
        DungeonCharacter test = new Skeleton("test_monster");
        test.setMyCharacter_HealthPoints(100);
        assertEquals(test.getCharacter_HealthPoints(), 100);
        test.killCharacter();
        assertEquals(test.getCharacter_HealthPoints(), 0);
    }

    /**
     * Making sure attack throws exception when null is passed in as monster.
     * Expected: IllegalArgumentException to be thrown
     */
    @Test
    void attacks() {
        DungeonCharacter test = new Skeleton("test_monster");
        assertThrows(IllegalArgumentException.class, ()-> test.attacks(null));
    }

    /**
     * Making sure attack returns empty string when character is dead.
     */
    @Test
    void attacks1() {
        DungeonCharacter test = new Skeleton("test_monster");
        DungeonCharacter enemy = new Skeleton("temp");
        assertNotEquals(test.attacks(enemy), "");
        test.killCharacter();
        assertEquals(test.attacks(enemy), "");
    }


    /**
     * Making sure exception is thrown when damage taken is less than 0.
     * Expected: IllegalArgumentException to be thrown
     */
    @Test
    void damageTaken() {
        DungeonCharacter test = new Skeleton("test_monster");
        assertThrows(IllegalArgumentException.class, ()-> test.damageTaken(-1));
    }



    /**
     * Making sure damage taken outputs that monster took damage by comparing its output string.
     * Making sure monster dies when it took more damage than its current health.
     */
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


    /**
     * Making sure damage taken outputs that monster took damage by comparing its output string.
     * Making sure monster takes damage when damage it took doesnt kill him.
     */
    @Test
    void damageTaken2() {
        DungeonCharacter test = new Skeleton("test_monster");
        test.setMyCharacter_HealthPoints(100);
        int damage = 20;

        String output = test.damageTaken(damage);
        String expected = "Has taken <<" + damage + ">> damage";

        assertTrue(output.contains(expected));
        assertNotEquals(test.getCharacter_HealthPoints(), 0);
    }

}