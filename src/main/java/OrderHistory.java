import Database.Database;
import Database.Datamapper;
import Database.Entities.OrderEntry;
import Database.Entities.User;

public class OrderHistory extends Screen {

    private final User user;

    public OrderHistory(User user) {
        this.user = user;
    }

    public void listOrders() {
        while (true) {
            clearConsole();
            System.out.println("Order history");
            System.out.println("-------------\n");

            var orders = Datamapper.mapData(OrderEntry.class,
                    Database.executeQuery(
                            String.format("SELECT * FROM OrderEntry WHERE Username = \"%s\"", user.username)));

            for (int i = 0; i < orders.size(); ++i)
                System.out.printf("%d. %s\n", i + 1, orders.get(orders.size() - 1 - i).toStringMinimal());

            System.out.println("0. Back");

            var r = requestInput();

            switch (r) {
                case 0:
                    return;
                default:
                    var index = (r - 1);
                    if (index >= orders.size())
                        break;

                    orderDetails(orders.get(orders.size() - 1 - index));
                    break;
            }
        }

    }

    public void orderDetails(OrderEntry entry) {
        clearConsole();
        System.out.println("Order details");
        System.out.println("-------------\n");

        System.out.println(entry);

        System.out.println();

        boolean canCancel = entry.canBeCancelled() && !entry.cancelled;

        if (canCancel)
            System.out.println("1. Cancel\n");

        System.out.println("0. Back");

        switch (requestInput()) {
            case 0:
                return;
            case 1:
                entry.tryCancelOrder();
                return;
        }
    }
}
