package Model;
import org.sqlite.SQLiteDataSource;

import java.sql.*;

public class MonsterFactory {
    public static void main(String[] args) {
        SQLiteDataSource ds = null;

        //establish connection (creates db file if it does not exist :-)
        try {
            ds = new SQLiteDataSource();
            ds.setUrl("jdbc:sqlite:Test.db");
        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println( "Opened database successfully" );
//        String query = "SELECT * FROM Stock WHERE Price = 20000";
//        String query = "SELECT * FROM Stock ORDER BY RANDOM()";
        String query = "SELECT * FROM Stock WHERE Price BETWEEN 700 AND 1000";// These values are inclusive
//        String query = "SELECT * FROM Stock ";

        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement(); ) {

            ResultSet rs = stmt.executeQuery(query);

            //walk through each 'row' of results, grab data by column/field name
            // and print it
            System.out.println(rs.getString("Name"));
            System.out.println(rs.getString("Price"));
            System.out.println(rs.getString("Owner"));




            while ( rs.next() ) {

                System.out.println(rs.getString( "Name" ));
                System.out.println(rs.getString( "Price" ));
                System.out.println(rs.getString( "Owner" ));
                System.out.println();
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
    }
}
