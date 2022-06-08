package Model;

import Model.Characters.Monster;
import Model.Characters.Monsters.DungeonMonster;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * This is a factory class that is used to generate monsters.
 * @authors Varun Parbhakar, Austin Luu, Yacine Bennour
 * @version 06/07/2022
 */
public class MonsterFactory {
    private SQLiteDataSource ds;
    private final ArrayList<Monster> normalMonsters;
    private final ArrayList<Monster> guardMonsters;
    private final ArrayList<Monster> bossMonsters;


    /**
     * This is contractor for the MonsterFactory class.
     */
    public MonsterFactory() {
        ds = null;

        try {
            ds = new SQLiteDataSource();
            ds.setUrl("jdbc:sqlite:MonstersDatabase1.db");
        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit(0);
        }
        normalMonsters = getNormalMonsters();
        guardMonsters = getGuardMonsters();
        bossMonsters = getBossMonsters();
    }


    /**
     * This method retrieve the normal monsters from the SQL database.
     * @return ArrayList full of normal monsters.
     */
    public ArrayList<Monster> getNormalMonsters () {
        return retrieveMonsters( "Normal");
    }

    /**
     * This method retrieve the guard monsters from the SQL database.
     * @return ArrayList full of guard monsters.
     */
    public ArrayList<Monster> getGuardMonsters () {
        return retrieveMonsters( "Guard");

    }
    /**
     * This method retrieve the boss monsters from the SQL database.
     * @return ArrayList full of boss monsters.
     */
    public ArrayList<Monster> getBossMonsters () {
        return retrieveMonsters( "Boss");

    }

    /**
     * This method returns a random monster from the selection of normal monsters.
     * @return
     */
    public Monster getRandomNormalMonster() {
        Random r = new Random();
        return normalMonsters.get(r.nextInt(normalMonsters.size()));
    }
    /**
     * This method returns a random monster from the selection of Guard monsters.
     * @return
     */
    public Monster getRandomGuardMonster() {
        Random r = new Random();
        return guardMonsters.get(r.nextInt(guardMonsters.size()));
    }
    /**
     * This method returns a random monster from the selection of Boss monsters.
     * @return
     */
    public Monster getRandomBossMonster() {
        Random r = new Random();
        return bossMonsters.get(r.nextInt(bossMonsters.size()));
    }


    /**
     * This method is used for the retrieving the monsters from SQL database.
     * @param theMType (The type of monster)
     * @return
     */
    private ArrayList<Monster> retrieveMonsters (final String theMType) {
        ArrayList<Monster> monsterArray = new ArrayList<>();
        String query = "SELECT * FROM Monsters WHERE Type = '"+ theMType + "'";


        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement(); ) {

            ResultSet rs = stmt.executeQuery(query);

            while ( rs.next() ) {


                String myName = rs.getString( "Name" );
                int myHealth = rs.getInt( "Health" );
                int myAttackSpeed = (rs.getInt( "Attack Speed" ));
                double myAttackChance = (rs.getDouble( "Attack Chance" ));

                int myMinDamage = rs.getInt( "Min Damage" );
                int myMaxDamage = rs.getInt( "Max Damage" );

                double myHealChance = rs.getDouble( "Heal Chance"  );

                int myMinHeal = rs.getInt( "Min Heal"  );
                int myMaxHeal = rs.getInt( "Max Heal" );



                Monster myMonster = new DungeonMonster(
                        myName,
                        myHealth,
                        myAttackSpeed,
                        myMinDamage,
                        myMaxDamage,
                        myAttackChance,
                        myHealChance,
                        myMinHeal,
                        myMaxHeal);

                monsterArray.add(myMonster);

            }
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
        return monsterArray;
    }
}
