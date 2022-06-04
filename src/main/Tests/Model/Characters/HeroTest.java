package Model.Characters;

import Model.Characters.Heroes.Warrior;
import Model.Characters.Monsters.Skeleton;
import Model.RoomType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.awt.*;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;


class HeroTest {

    @Test
    void healingPotion() {
        Hero hero = new Warrior("TempWarrior");
        for(int i = 0; i < 10; i++) {
            int previousHealthPoints = hero.getCharacter_HealthPoints();

            hero.useHealingPotion();

            int newHealthPoints = hero.getCharacter_HealthPoints();

            assertTrue((newHealthPoints - previousHealthPoints) < 20);
            assertTrue((newHealthPoints - previousHealthPoints) >= 0);
        }
    }


    @Test
    void heroTakesDamage() {
        Hero hero = new Warrior("TempWarrior");
        for(int i = 0; i < 10; i++) {
            int previousHealthPoints = hero.getCharacter_HealthPoints();

            hero.heroTakesDamage();

            int newHealthPoints = hero.getCharacter_HealthPoints();


            assertTrue((previousHealthPoints - newHealthPoints) < 30);
            assertTrue((previousHealthPoints - newHealthPoints) >= 0);
        }
    }


    @Test
    void specialAttack() {
        Warrior hero = new Warrior("TempWarrior");
        Monster monster = new Skeleton("TempSkeleton") ;

        if(hero.canAttack(monster)) {
            String specialAttack = hero.specialAttack(monster);

            boolean specialAttackWentThrough = specialAttack.contains("zapped") | specialAttack.contains("NO!");
            assertTrue(specialAttackWentThrough);
        }
    }


    @Test
    void attacks() {
        Hero hero = new Warrior("TempWarrior");
        Monster monster = new Skeleton("TempSkeleton") ;

        String specialAttack = hero.attacks(monster, 1);

        boolean specialAttackWentThrough = specialAttack.contains("zapped") | specialAttack.contains("NO!");
        assertTrue(specialAttackWentThrough);
    }


    @Test
    void attacks1() {
        Hero hero = new Warrior("TempWarrior");
        Monster monster = new Skeleton("TempSkeleton");


        int previousNumberOfAttacks = hero.getNumberOfAttacks();
        hero.attacks(monster, 2);
        int newNumberOfAttacks = hero.getNumberOfAttacks();

        assertEquals(previousNumberOfAttacks-newNumberOfAttacks, 1);
    }


    @Test
    void attacks2() {
        Hero hero = new Warrior("TempWarrior");
        Monster monster = new Skeleton("TempSkeleton");

        int previousNumberOfAttacks = hero.getNumberOfAttacks();
        hero.attacks(monster, 3);
        int newNumberOfAttacks = hero.getNumberOfAttacks();

        assertEquals(previousNumberOfAttacks-newNumberOfAttacks, 1);
    }

    @Test
    void attacks3() {
        Hero hero = new Warrior("TempWarrior");

        assertThrows(IllegalArgumentException.class, ()-> hero.attacks(null, 2));
    }




    @Test
    /**
     * Testing for canAttack when theEnemy is dead (0 health points).
     *
     * Expected: we should only be allowed one attack and then after we decrease the monsters
     * health points to zero, we shouldnt be allowed to attack.
     */
    void canAttack1() {
        Hero hero = new Warrior("TempWarrior");
        Monster monster = new Skeleton("TempSkeleton");

        hero.resetAttackSpeed(monster);
        assertTrue(hero.canAttack(monster));
        monster.setMyCharacter_HealthPoints(-100);

        assertFalse(hero.canAttack(monster));
    }


    @Test
    /**
     * Testing for canAttack when myNumberofAttacks is = to 1;
     *
     * Expected: we should only be allowed one attack and the next one should output false;
     */
    void canAttack2() {
        Hero hero = new Warrior("TempWarrior");
        hero.attackSpeedValidator(1);

        Monster monster = new Skeleton("TempSkeleton");
        monster.attackSpeedValidator(1);

        hero.resetAttackSpeed(monster);
        assertTrue(hero.canAttack(monster));

        hero.attacks(monster, 2);

        assertFalse(hero.canAttack(monster));
    }


    @Test
    /**
     * Testing for canAttack when the hero is dead (0 health points).
     *
     * Expected: we should only be allowed one attack and then after we decrease the heros
     * health points to zero, we shouldnt be allowed to attack.
     */
    void canAttack3() {
        Hero hero = new Warrior("TempWarrior");
        Monster monster = new Skeleton("TempSkeleton");

        hero.resetAttackSpeed(monster);
        assertTrue(hero.canAttack(monster));
        hero.killCharacter();
        assertFalse(hero.canAttack(monster));
    }


    @Test
    /**
     * Testing for canAttack when the hero is ran away.
     *
     * Expected: we should only be allowed one attack and then after we run away, we shouldnt be able to attack.
     */
    void canAttack4() {
        Hero hero = new Warrior("TempWarrior");
        Monster monster = new Skeleton("TempSkeleton");

        hero.resetAttackSpeed(monster);
        assertTrue(hero.canAttack(monster));
        hero.runAway();

        assertFalse(hero.canAttack(monster));
    }



    @Test
    void runAway() {
        Hero hero = new Warrior("TempWarrior");

        assertTrue(hero.getCharacter_HealthPoints() > 0);

        hero.runAway();

        assertEquals(hero.getCharacter_HealthPoints(), 0);
    }



    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.3, 0.4, 0.5, 0.77, 0.99, 0.12})
    void chance2Block_Validator(double currentChance2Block) {
        Hero hero = new Warrior("TempWarrior");

        hero.chance2Block_Validator(currentChance2Block);

        assertTrue(Double.compare(hero.getMyChance2Block(), currentChance2Block) == 0);
    }


    @ParameterizedTest
    @ValueSource(doubles = {-111.0, -0.1, -1.0, 1.1, 1.2, 10.1, 10.0, 100.0})
    void chance2Block_Validator2(double currentChance2Block) {
        Hero hero = new Warrior("TempWarrior");

        assertThrows(IllegalArgumentException.class, ()-> hero.chance2Block_Validator(currentChance2Block));
    }


    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.3, 0.4, 0.5, 0.77, 0.99, 0.12})
    void specialSkill_Chance_Validator(double specialSkillChance) {
        Hero hero = new Warrior("TempWarrior");

        hero.specialSkill_Chance_Validator(specialSkillChance);

        assertTrue(Double.compare(hero.getMySpecialSkillChance(), specialSkillChance) == 0);
    }


    @ParameterizedTest
    @ValueSource(doubles = {0.0, -0.1, -1.0, 1.1, 1.2, 10.1, 10.0, 100.0})
    void specialSkill_Chance_Validator2(double specialSkillChance) {
        Hero hero = new Warrior("TempWarrior");

        assertThrows(IllegalArgumentException.class, ()-> hero.specialSkill_Chance_Validator(specialSkillChance));
    }



    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5})
    void specialSkill_MinDamage_Validator(int specialSkill_MinDamage) {
        Hero hero = new Warrior("TempWarrior");

        hero.specialSkill_MinDamage_Validator(specialSkill_MinDamage);

        assertTrue(hero.getMySpecialSkill_MinDamage() == specialSkill_MinDamage);
    }


    @ParameterizedTest
    @ValueSource(ints = {0,-1,-2,-3,-4,-5})
    void specialSkill_MinDamage_Validator2(int specialSkill_MinDamage) {
        Hero hero = new Warrior("TempWarrior");


        assertThrows(IllegalArgumentException.class, ()-> hero.specialSkill_MinDamage_Validator(specialSkill_MinDamage));
    }





    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5})
    void specialSkill_MaxDamage_Validator(int specialSkill_MaxDamage) {
        Hero hero = new Warrior("TempWarrior");

        hero.specialSkill_MaxDamage_Validator(specialSkill_MaxDamage);

        assertTrue(hero.getMySpecialSkill_MaxDamage() == specialSkill_MaxDamage);
    }


    @ParameterizedTest
    @ValueSource(ints = {0,-1,-2,-3,-4,-5})
    void specialSkill_MaxDamage_Validator2(int specialSkill_MaxDamage) {
        Hero hero = new Warrior("TempWarrior");


        assertThrows(IllegalArgumentException.class, ()-> hero.specialSkill_MaxDamage_Validator(specialSkill_MaxDamage));
    }



    @ParameterizedTest
    @CsvSource({"0,1", "1,2", "4,5"})
    void sSkillDamageRangeValidator(int theC_SMinDamage, int theC_SMaxDamage) {
        Hero hero = new Warrior("TempWarrior");

        assertTrue(hero.sSkillDamageRangeValidator(theC_SMinDamage, theC_SMaxDamage));
    }


    @ParameterizedTest
    @CsvSource({"1,0", "10,0", "6,5"})
    void sSkillDamageRangeValidator2(int theC_SMinDamage, int theC_SMaxDamage) {
        Hero hero = new Warrior("TempWarrior");

        assertThrows(IllegalArgumentException.class, ()-> hero.sSkillDamageRangeValidator(theC_SMinDamage, theC_SMaxDamage));
    }

    @Test
    void translateCharacterX(){
        Hero hero = new Warrior("TempWarrior");
        assertEquals(0,  hero.getCharacterLocationX());
        hero.translateCharacterX(1);
        assertEquals(1,  hero.getCharacterLocationX());
    }

    @Test
    void translateCharacterX1(){
        Hero hero = new Warrior("TempWarrior");
        assertEquals(0,  hero.getCharacterLocationX());
        hero.translateCharacterX(-1);
        assertEquals(-1,  hero.getCharacterLocationX());
    }



    @ParameterizedTest
    @ValueSource(ints = {Integer.MIN_VALUE, -2, 0, 2, Integer.MAX_VALUE})
    void translateCharacterX2(int input){
        Hero hero = new Warrior("TempWarrior");
        assertEquals(0,  hero.getCharacterLocationX());
        assertThrows(IllegalArgumentException.class, ()-> hero.translateCharacterX(input));
    }



    @Test
    void translateCharacterY(){
        Hero hero = new Warrior("TempWarrior");
        assertEquals(0,  hero.getCharacterLocationY());
        hero.translateCharacterY(1);
        assertEquals(1,  hero.getCharacterLocationY());
    }

    @Test
    void translateCharacterY1(){
        Hero hero = new Warrior("TempWarrior");
        assertEquals(0,  hero.getCharacterLocationY());
        hero.translateCharacterY(-1);
        assertEquals(-1,  hero.getCharacterLocationY());
    }



    @ParameterizedTest
    @ValueSource(ints = {Integer.MIN_VALUE, -2, 0, 2, Integer.MAX_VALUE})
    void translateCharacterY2(int input){
        Hero hero = new Warrior("TempWarrior");
        assertEquals(0,  hero.getCharacterLocationY());
        assertThrows(IllegalArgumentException.class, ()-> hero.translateCharacterY(input));
    }



    @Test
    void getCharacterLocation() {
        Hero hero = new Warrior("TempWarrior");

        assertEquals(new Point(0,0),  hero.getCharacterLocation());
    }


    @Test
    void getCharacterLocationX() {
        Hero hero = new Warrior("TempWarrior");

        assertEquals(0,  hero.getCharacterLocationX());
    }

    @Test
    void getCharacterLocationY() {
        Hero hero = new Warrior("TempWarrior");

        assertEquals(0,  hero.getCharacterLocationY());
    }

    @ParameterizedTest
    @EnumSource(RoomType.class)
    void addItem2Satchel(RoomType roomType) {
        Hero hero = new Warrior("TempWarrior");

        assertFalse(hero.satchelContains(roomType));

        hero.addItem2Satchel(roomType);

        assertTrue(hero.satchelContains(roomType));
    }

    @Test
    void removeSatchelItem() {
        Hero hero = new Warrior("TempWarrior");

        RoomType roomType = RoomType.ABSTRACTION;

        hero.addItem2Satchel(roomType);

        assertTrue(hero.satchelContains(roomType));

        hero.removeSatchelItem(roomType);

        assertFalse(hero.satchelContains(roomType));
    }

    @Test
    void getHeroSatchel() {
        Hero hero = new Warrior("TempWarrior");

        RoomType roomType1 = RoomType.ABSTRACTION;
        RoomType roomType2 = RoomType.ENCAPSULATION;
        RoomType roomType3 = RoomType.HEALING;

        hero.addItem2Satchel(roomType1);
        hero.addItem2Satchel(roomType2);
        hero.addItem2Satchel(roomType3);

        ArrayList<Character> expectedSatchel = new ArrayList<>();
        expectedSatchel.add(roomType1.type);
        expectedSatchel.add(roomType2.type);
        expectedSatchel.add(roomType3.type);

        assertArrayEquals(hero.getHeroSatchel().toArray(), expectedSatchel.toArray());
    }


    @Test
    void addHealingPotion() {
        Hero hero = new Warrior("TempWarrior");

        assertTrue(hero.getMyHealingPotions() == 0);
        hero.incrementHealingPotion();

        assertTrue(hero.getMyHealingPotions() == 1);
    }


    @Test
    void addVisionPotion() {
        Hero hero = new Warrior("TempWarrior");

        assertTrue(hero.getMyVisionPotions() == 0);

        hero.incrementVisionPotion();

        assertTrue(hero.getMyVisionPotions() == 1);
    }

    @Test
    void testToString() {
        Hero hero = new Warrior("TempWarrior");

        hero.incrementHealingPotion();
        hero.incrementVisionPotion();
        hero.incrementPillars();


        String expected = "Name: " + "TempWarrior"
                + "\nHealth: " + 125
                + "\nVision Potions found: " + 1
                + "\nHealing Potions found: " + 1
                + "\nPillars of OOP found: " + 1;

        assertTrue(hero.toString().equals(expected));
    }
}