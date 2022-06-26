package Database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    static final String dbURL = "jdbc:mysql://localhost/attendance";
    static final String dbUsername = "root";
    static final String dbPassword = "";

    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(dbURL,dbUsername,dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}