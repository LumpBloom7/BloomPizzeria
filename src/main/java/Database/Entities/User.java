package Database.Entities;

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
}
