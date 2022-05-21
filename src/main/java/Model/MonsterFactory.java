package Model;
import Model.Characters.Monster;
import Model.Characters.Skeleton;
import org.sqlite.SQLiteDataSource;

import java.sql.*;

public class MonsterFactory {
    public static void main(String[] args) {

    }
    public static Monster getRandomNormalMonster() {
        return new Skeleton("Jesus");
    }
}
