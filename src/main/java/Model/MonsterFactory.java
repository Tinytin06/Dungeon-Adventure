package Model;
import Model.Characters.Monster;
import Model.Characters.Monsters.DungeonMonster;
import org.sqlite.SQLiteDataSource;

import java.sql.*;

public class MonsterFactory {
    private SQLiteDataSource ds = null;
    private ArrayList<Monster> normalMonsters;
    private ArrayList<Monster> guardMonsters;
    private ArrayList<Monster> bossMonsters;



    /**
     * Normal
     * Guard
     * Boss
     */
    public MonsterFactory() {
        ds = null;

        //establish connection (creates db file if it does not exist :-)
        try {
            ds = new SQLiteDataSource();
            ds.setUrl("jdbc:sqlite:MonstersDatabase.db");
        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit(0);
        }
        normalMonsters = getNormalMonsters();
        guardMonsters = getGuardMonsters();
        bossMonsters = getBossMonsters();
    }

    public static void main(String[] args) {
        MonsterFactory test = new MonsterFactory();

        for(int i = 0; i < 10; i++){
            System.out.println(test.getRandomGuardMonster().toString());
            System.out.println("\n\n\n");
        }

    }

    public ArrayList<Monster> getNormalMonsters () {
        ArrayList<Monster> myNormalMonsters = new ArrayList<>();
        String query = "SELECT * FROM Monsters WHERE Type = 'Normal' ";
//        String query = "SELECT * FROM Stock ORDER BY RANDOM()";
        //String query = "SELECT * FROM Stock WHERE Price BETWEEN 700 AND 1000";// These values are inclusive
//        String query = "SELECT * FROM Stock ";

        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement(); ) {

            ResultSet rs = stmt.executeQuery(query);

            while ( rs.next() ) {
//                System.out.println(rs.getString( "Name" ));
//                System.out.println(rs.getString( "Type" ));
//                System.out.println(rs.getString( "Attack Speed" ));
//                System.out.println(rs.getString( "Attack Chance" ));
//                System.out.println(rs.getString( "Min Damage" ));
//                System.out.println(rs.getString( "Max Damage" ));
//                System.out.println(rs.getString( "Heal Chance" ));
//                System.out.println(rs.getString( "Min Heal" ));
//                System.out.println(rs.getString( "Max Heal" ));



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
                myNormalMonsters.add(myMonster);

            }
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }

        return myNormalMonsters;

    }
    public ArrayList<Monster> getGuardMonsters () {
        ArrayList<Monster> myGuardMonsters = new ArrayList<>();
        String query = "SELECT * FROM Monsters WHERE Type = 'Guard' ";
//        String query = "SELECT * FROM Stock ORDER BY RANDOM()";
        //String query = "SELECT * FROM Stock WHERE Price BETWEEN 700 AND 1000";// These values are inclusive
//        String query = "SELECT * FROM Stock ";

        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement(); ) {

            ResultSet rs = stmt.executeQuery(query);

            while ( rs.next() ) {
//                System.out.println(rs.getString( "Name" ));
//                System.out.println(rs.getString( "Type" ));
//                System.out.println(rs.getString( "Attack Speed" ));
//                System.out.println(rs.getString( "Attack Chance" ));
//                System.out.println(rs.getString( "Min Damage" ));
//                System.out.println(rs.getString( "Max Damage" ));
//                System.out.println(rs.getString( "Heal Chance" ));
//                System.out.println(rs.getString( "Min Heal" ));
//                System.out.println(rs.getString( "Max Heal" ));



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
                myGuardMonsters.add(myMonster);

            }
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }

        return myGuardMonsters;

    }
    public ArrayList<Monster> getBossMonsters () {
        ArrayList<Monster> myBossMonsters = new ArrayList<>();
        String query = "SELECT * FROM Monsters WHERE Type = 'Boss' ";
//        String query = "SELECT * FROM Stock ORDER BY RANDOM()";
        //String query = "SELECT * FROM Stock WHERE Price BETWEEN 700 AND 1000";// These values are inclusive
//        String query = "SELECT * FROM Stock ";

        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement(); ) {

            ResultSet rs = stmt.executeQuery(query);

            while ( rs.next() ) {
//                System.out.println(rs.getString( "Name" ));
//                System.out.println(rs.getString( "Type" ));
//                System.out.println(rs.getString( "Attack Speed" ));
//                System.out.println(rs.getString( "Attack Chance" ));
//                System.out.println(rs.getString( "Min Damage" ));
//                System.out.println(rs.getString( "Max Damage" ));
//                System.out.println(rs.getString( "Heal Chance" ));
//                System.out.println(rs.getString( "Min Heal" ));
//                System.out.println(rs.getString( "Max Heal" ));



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
                        myBossMonsters.add(myMonster);

            }
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }

        return myBossMonsters;

    }


    public Monster getRandomNormalMonster() {
        Random r = new Random();
        return normalMonsters.get(r.nextInt(normalMonsters.size()));
    }
    public Monster getRandomGuardMonster() {
        Random r = new Random();
        return guardMonsters.get(r.nextInt(guardMonsters.size()));
    }
    public Monster getRandomBossMonster() {
        Random r = new Random();
        return bossMonsters.get(r.nextInt(bossMonsters.size()));
    }
}
