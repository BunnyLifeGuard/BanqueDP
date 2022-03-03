package sample.ConnectionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class connectionBase extends Configs {

    private static Connection con;

    public static Connection getConnection() throws ClassNotFoundException, SQLException {

        if (con == null) {
            Class.forName("com.mysql.jdbc.Driver");

            String str = "jdbc:mysql://" + Configs.dbhost + ":" + Configs.dbport + "/" + Configs.dbname + "?serverTimezone=UTC";

            con = DriverManager.getConnection(str , Configs.dbuser , Configs.dbpass);

        }


        return con;
    }

}
