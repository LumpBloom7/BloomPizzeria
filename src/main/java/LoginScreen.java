import Database.Entities.User;

class LoginScreen extends Screen {

    public static String inputLoginUsername() {
        System.out.print("Enter username: ");
        String usernameInput = input.nextLine();
        return usernameInput;
    }
    /*
     * public String inputLoginPassword(){
     * Scanner scanner = new Scanner(System.in);
     * System.out.println("Enter password");
     * String passwordInput = scanner.nextLine();
     * String[] resultingPassword = passwordInput.split(" ", 2);
     * scanner.close();
     * return resultingPassword[0];
     * }
     */

    public static void LoginSequence() {
        clearConsole();

        while (true) {
            System.out.println("\nWelcome to BloomPizzeria!!!\n");
            String username = inputLoginUsername();
            User user = User.getUser(username);

            if (user != null) {
                new UserPanel(user).mainMenu();
            } else {
                clearConsole();
                System.out.println("Username does not exist.");
            }
        }
    }

    public static void main(String[] args) {
        LoginSequence();
    }
}
