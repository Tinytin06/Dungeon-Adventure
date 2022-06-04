package Model;

import Model.Characters.Monster;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class MonsterFactoryTest {

    /**
     * Checking for Normal Monsters
     */
    @Test

    void getNormalMonsters() {
        MonsterFactory mf = new MonsterFactory();

        List<String> myMonsters = new ArrayList<>();
        List<String> monsterNames = Arrays.asList("Gremlin", "Skeleton", "Ogre");



        // Gremlin, Skeleton, Ogre
        // Jormungandr
        // Hermes, Poseiden, Zeus, Kratos
        boolean validMonster = true;
        for(Monster monster :  mf.getNormalMonsters()) {
            myMonsters.add(monster.getCharacter_Name());
        }
        Collections.sort(monsterNames);
        Collections.sort(myMonsters);
        assertEquals(monsterNames, myMonsters);

    }

    /**
     * Checking for the Boss monster
     */
    @Test
    void getBossMonsters() {
        MonsterFactory mf = new MonsterFactory();

        List<String> myMonsters = new ArrayList<>();
        List<String> monsterNames = Arrays.asList("Jormungandr");



        // Gremlin, Skeleton, Ogre
        // Jormungandr
        // Hermes, Poseiden, Zeus, Kratos
        boolean validMonster = true;
        for(Monster monster :  mf.getBossMonsters()) {
            myMonsters.add(monster.getCharacter_Name());
        }
        Collections.sort(monsterNames);
        Collections.sort(myMonsters);
        assertEquals(monsterNames, myMonsters);

    }
    /**
     * Checking for Guard Monsters
     */
    @Test

    void getGuardMonsters() {
        MonsterFactory mf = new MonsterFactory();

        List<String> myMonsters = new ArrayList<>();
        List<String> monsterNames = Arrays.asList("Hermes", "Poseiden", "Zeus", "Kratos");


        boolean validMonster = true;
        for(Monster monster :  mf.getGuardMonsters()) {
            myMonsters.add(monster.getCharacter_Name());
        }
        Collections.sort(monsterNames);
        Collections.sort(myMonsters);
        assertEquals(monsterNames, myMonsters);

    }

    /**
     * Testing for when the monster does not exists.
     */
    @Test
    void getNoMonsters4() {
        MonsterFactory mf = new MonsterFactory();

        List<String> myMonsters = new ArrayList<>();
        List<String> monsterNames = Arrays.asList("Yacine", "Monster that is not in the DB", "Batman");



        // Gremlin, Skeleton, Ogre
        // Jormungandr
        // Hermes, Poseiden, Zeus, Kratos
        boolean validMonster = true;
        for(Monster monster :  mf.getBossMonsters()) {
            myMonsters.add(monster.getCharacter_Name());
        }
        Collections.sort(monsterNames);
        Collections.sort(myMonsters);
        assertNotEquals(monsterNames, myMonsters);

    }

    @Test
    /**
     * Checking for the random normal monster
     */
    void getRandomNormalMonster() {
        MonsterFactory mf = new MonsterFactory();

        Monster myMonster = mf.getRandomNormalMonster();
        List<String> monsterNames = Arrays.asList("Gremlin", "Skeleton", "Ogre");

        assertTrue(monsterNames.contains(myMonster.getCharacter_Name()));

    }

    @Test
    /**
     * Checking for the random Guard monster
     */
    void getRandomGuardMonster() {
        MonsterFactory mf = new MonsterFactory();

        Monster myMonster = mf.getRandomGuardMonster();
        List<String> monsterNames = Arrays.asList("Hermes", "Poseiden", "Zeus", "Kratos");

        assertTrue(monsterNames.contains(myMonster.getCharacter_Name()));

    }
    @Test
    /**
     * Checking for the random Boss monster
     */
    void getRandomBossMonster() {
        MonsterFactory mf = new MonsterFactory();

        Monster myMonster = mf.getRandomBossMonster();


        List<String> monsterNames = List.of("Jormungandr");

        assertTrue(monsterNames.contains(myMonster.getCharacter_Name()));

    }


}