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

    public static ResultSet executeQuery(String query) {
        try {
            var statement = conn.createStatement();
            var rs = conn.createStatement().executeQuery(query);
            statement.close();
            return rs;
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return null;
    }

    public static boolean executeStatement(String query) {
        try {
            var statement = conn.createStatement();
            statement.execute(query);
            statement.close();
            return true;
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return false;
    }

    public static int getLastInsertIndex() {
        try {
            var rs = executeQuery("SELECT LAST_INSERT_ID()");

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return -1;
    }
}
