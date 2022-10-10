package Database;

import java.sql.*;

/*
-   This class is made for testing purposes
 */

public class User {
    public final String username;
    public final String name;
    public final String address;
    public final String postcode;
    public final String phoneNumber;


    private User(String username, String name, String address, String postcode, String phoneNumber) {
        this.username = username;
        this.name = name;
        this.address = address;
        this.postcode = postcode;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s, %s", username, name, address, postcode, phoneNumber);
    }

    public static User getUser(String username) {
        var rs = Database.performQuery(String.format("SELECT * FROM User WHERE username = \"%s\"", username));

        try {
            if(!rs.next())
                return null;

            return new User(rs.getString("Username"), rs.getString("Name"),rs.getString("address"), rs.getString("postcode"), rs.getString("phoneNumber"));
        }
        catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return null;
    }

    public static boolean UserExists(String username) {
        var rs = Database.performQuery(String.format("SELECT * FROM User WHERE username = \"%s\"", username));

        try {
            if (rs.next())
                return true;
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return false;
    }

    public String getUsername(){
        return username;
    }

    public String getName(){
        return name;
    }

    public String getAddress(){
        return address;
    }

    public String getPostCode(){
        return postcode;        
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }
    
    public static void main(String[] args) {
        var result = getUser("Bloom");
        
        if(result == null)
            System.out.println("User not found");
        else
            System.out.println(result);
    }
}
