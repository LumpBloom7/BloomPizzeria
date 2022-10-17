import Database.Entities.DeliveryStaff;
import Database.Entities.User;

class LoginScreen extends Screen {

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

            System.out.println("Login");
            System.out.println("-----\n");

            System.out.print("Enter your username or type \"Back\" to return: ");
            String username = input.nextLine();

            if (username.toLowerCase().equals("back"))
                return;

            User user = User.getUser(username);

            if (user != null) {
                new UserPanel(user).mainMenu();
                clearConsole();
            } else {
                clearConsole();
                System.out.println("Username does not exist.");
            }
        }
    }

    public static void RegistrationSequence() {
        clearConsole();
        while (true) {

            System.out.println("Register");
            System.out.println("--------\n");

            System.out.println("Type \"Back\" at any time to cancel.\n");

            var newUser = new User();
            System.out.print("Enter your username: ");
            newUser.username = input.nextLine();
            if (newUser.username.toLowerCase().equals("back"))
                return;

            System.out.print("Enter your name: ");
            newUser.name = input.nextLine();
            if (newUser.name.toLowerCase().equals("back"))
                return;

            System.out.print("Enter your address: ");
            newUser.address = input.nextLine();
            if (newUser.address.toLowerCase().equals("back"))
                return;

            System.out.print("Enter your postcode: ");
            newUser.postcode = input.nextLine();
            if (newUser.postcode.toLowerCase().equals("back"))
                return;

            System.out.print("Enter your phone number: ");
            newUser.phoneNumber = input.nextLine();
            if (newUser.phoneNumber.toLowerCase().equals("back"))
                return;

            if (newUser.registerUser()) {
                new UserPanel(newUser).mainMenu();
                clearConsole();
            } else {
                clearConsole();
                System.out.println("Registration failed, pls try again");
            }
        }
    }

    public static void DelivererLogin() {
        clearConsole();
        while (true) {
            System.out.println("Deliverer login");
            System.out.println("---------------\n");

            System.out.println("Enter deliverer ID (type -1 to return)");

            var rs = requestInput();
            switch (rs) {
                case -1:
                    return;
                default:
                    DeliveryStaff staff = DeliveryStaff.getDeliveryStaff(rs);
                    if (staff == null) {
                        clearConsole();
                        System.out.println("Delivery staff ID is incorrect.");
                        break;
                    }
                    new DeliveryPanel(staff).mainMenu();
                    clearConsole();
            }
        }
    }

    public static void mainMenu() {
        while (true) {
            clearConsole();
            System.out.println("Welcome to BloomPizzeria");
            System.out.println("------------------------\n");

            System.out.println("1. Login as existing user");
            System.out.println("2. Create new user");
            System.out.println("3. Login as delivery staff");

            System.out.println("\n0. Exit");

            switch (requestInput()) {
                case 0:
                    return;
                case 1:
                    LoginSequence();
                    break;
                case 2:
                    RegistrationSequence();
                    break;
                case 3:
                    DelivererLogin();
                    break;
            }

        }
    }

    public static void main(String[] args) {
        mainMenu();
    }
}
