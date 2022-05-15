package Model.Characters;

import Control.Movement;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import java.util.Random;

import static org.junit.Assert.*;

class HeroTest {

    @Test
    void specialDamageGenerator() {
        Hero hero = new Warrior("TestSpecialDamage");
        // For Warrior: mySpecialSkill_MinDamage = 75, mySpecialSkill_MaxDamage = 175
        hero.specialDamageGenerator();

    }

    @Test
    void healingPotion() {
        Hero hero = new Warrior("TempWarrior");
        for(int i = 0; i < 10; i++) {
            int previousHealthPoints = hero.getCharacter_HealthPoints();

            hero.healingPotion();

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
    void damageTaken() {
        Hero hero = new Warrior("TempWarrior");
        // Chance to block is .40

        int herosDamageTaken = 50;

        for(int i = 0; i < 10; i++) {
            int previousHealthPoints = hero.getCharacter_HealthPoints();
            if(hero.damageTaken(herosDamageTaken)){
                int newHealthPoints = hero.getCharacter_HealthPoints();

                if(previousHealthPoints >= 50) {
                    assertEquals(previousHealthPoints - newHealthPoints, herosDamageTaken);
                } else {
                    assertEquals(newHealthPoints, 0);
                }
            }
        }
    }

    @Test
    void specialAttack() {
        Hero hero = new Warrior("TempWarrior");
        // Chance to block is .40

        int herosDamageTaken = 50;

        for(int i = 0; i < 10; i++) {
            int previousHealthPoints = hero.getCharacter_HealthPoints();
            if(hero.damageTaken(herosDamageTaken)){
                int newHealthPoints = hero.getCharacter_HealthPoints();

                System.out.println(previousHealthPoints + " " + newHealthPoints);

                if(previousHealthPoints >= 50) {
                    assertEquals(previousHealthPoints - newHealthPoints, herosDamageTaken);
                } else {
                    assertEquals(newHealthPoints, 0);
                }
            }
        }
    }


    @Test
    void attacks() {
        Hero hero = new Warrior("TempWarrior");
        Monster monster = new Skeleton("TempSkeleton");
        Random randomGen = new Random();

        while(hero.canAttack(monster)) {
            int attackChoice = randomGen.nextInt(4);

            int previousNumberOfAttacks = hero.getNumberOfAttacks();
            hero.attacks(monster, attackChoice);
            int newNumberOfAttacks = hero.getNumberOfAttacks();

            assertEquals(previousNumberOfAttacks-newNumberOfAttacks, 1);
        }

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

        assertTrue(hero.canAttack(monster));

        Random randomGen = new Random();
        hero.attacks(monster, randomGen.nextInt(4));

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

        assertTrue(hero.canAttack(monster));
        hero.setMyCharacter_HealthPoints(-125);

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

        assertTrue(hero.canAttack(monster));
        hero.runAway();

        assertFalse(hero.canAttack(monster));
    }



    @Test
    void runAway() {
        Hero hero = new Warrior("TempWarrior");
        Monster monster = new Skeleton("TempSkeleton");

        assertTrue(hero.getCharacter_HealthPoints() > 0);

        hero.runAway();

        assertEquals(hero.getCharacter_HealthPoints(), 0);


    }

    @Test
    void chance2Block_Validator() {
    }

    @Test
    void specialSkill_Chance_Validator() {
    }

    @Test
    void specialSkill_MinDamage_Validator() {
    }

    @Test
    void specialSkill_MaxDamage_Validator() {
    }

    @Test
    void sSkillDamageRangeValidator() {
    }

    @Test
    void getCharacterLocation() {
    }

    @Test
    void setCharacterLocationY() {
    }

    @Test
    void setCharacterLocationX() {
    }

    @Test
    void getCharacterLocationY() {
    }

    @Test
    void getCharacterLocationX() {
    }

    @Test
    void addItem2Satchel() {
    }

    @Test
    void removeSatchelItem() {
    }

    @Test
    void getHeroSatchel() {
    }

    @Test
    void hasBothCrowns() {
    }

    @Test
    void addCrownPiece() {
    }

    @Test
    void addHealingPotion() {
    }

    @Test
    void addVisionPotion() {
    }

    @Test
    void testToString() {
    }
}