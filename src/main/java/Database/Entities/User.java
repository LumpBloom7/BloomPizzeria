package Database.Entities;

import java.sql.SQLException;

import Database.Database;
import Database.Datamapper;

/*
-   This class is made for testing purposes
 */

public class User extends IDatabaseEntity {
    public String username = null;
    public String name = null;
    public String address = null;
    public String postcode = null;
    public String phoneNumber = null;

    public static User getUser(String username) {
        var rs = Datamapper.mapData(User.class, "User", String.format("WHERE Username = \"%s\"", username));
        if (rs.isEmpty())
            return null;
        else
            return rs.get(0);
    }

    public static boolean UserExists(String username) {
        return getUser(username) != null;
    }

    public boolean hasCoupons() {
        var rs = Database.executeQuery(String.format("CALL GetNumberOfCoupons(\"%s\")", username));

        try {
            if (rs.next())
                return rs.getInt(1) > 0;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean registerUser() {
        var rs = Database.executeStatement(String.format(
                "INSERT INTO User(Username, Name, Address, Postcode, Phonenumber) VALUES (\"%s\", \"%s\", \"%s\", \"%s\", \"%s\")",
                username, name, address, postcode, phoneNumber));

        return rs;
    }
}
