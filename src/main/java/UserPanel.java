import Database.Entities.User;

public class UserPanel extends Screen {

    private final User user;

    public UserPanel(User user) {
        this.user = user;
    }

    public void mainMenu() {
        while (true) {
            clearConsole();

            System.out.println("What do you want to do?");
            System.out.println("-----------------------\n");

            System.out.println("1. Order stuff");
            System.out.println("2. Review order history");

            System.out.println();
            System.out.println("0. Quit");

            switch (requestInput()) {
                case 0:
                    return;
                case 1:
                    new Menu(user).mainMenu();
                    break;
                case 2:
                    new OrderHistory(user).listOrders();
                    break;
            }
        }
    }
}
