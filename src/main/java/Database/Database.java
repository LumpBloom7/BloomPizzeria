package Database;

import java.sql.*;

/*
-   This class is made for testing purposes
 */

public class Database {

    static Connection conn = null;

    static {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/BloomPizzeria", "root", "root");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            System.exit(1);
        }
    }

    public static ResultSet performQuery(String query) {
        try {
            var statement = conn.createStatement();
            var rs = conn.createStatement().executeQuery(query);
            statement.close();
            return rs;
        } 
        catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return null;
    }
}
