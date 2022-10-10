import java.util.*;

import Database.User;

import java.sql.*;

class LoginScreen{
    static User currentUser;

    public static String inputLoginUsername(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username");
        String usernameInput = scanner.nextLine();
        String[] resultingUsername = usernameInput.split(" ", 2);
        scanner.close();
        return resultingUsername[0];
    }
    /* public String inputLoginPassword(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter password");
        String passwordInput = scanner.nextLine();
        String[] resultingPassword = passwordInput.split(" ", 2);
        scanner.close();
        return resultingPassword[0];
    } */

    public static void LoginSequence(){
        String username = inputLoginUsername();
        if (User.UserExists(username)){
            User user = User.getUser(username);
            currentUser = user;
            System.out.println(user.toString());
        }
        else{
            System.out.println("Username does not exist.");
        }
    }

    public static void main(String[] args){
        LoginSequence();
    }

}