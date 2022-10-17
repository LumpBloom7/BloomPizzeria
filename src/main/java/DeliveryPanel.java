import java.util.List;

import Database.Entities.DeliveryStaff;
import Database.Entities.OrderEntry;

public class DeliveryPanel extends Screen {
    private final DeliveryStaff staff;

    public DeliveryPanel(DeliveryStaff staff) {
        this.staff = staff;
    }

    public void mainMenu() {
        while (true) {
            clearConsole();
            System.out.println("Delivery management panel");
            System.out.println("-------------------------\n");

            System.out.printf("Staff name: %s\n\n", staff.name);

            int i = 0;
            System.out.printf("%d. Current orders\n", ++i);
            System.out.printf("%d. Take on pending orders\n", ++i);

            System.out.println("\n");

            System.out.println("0. Exit");

            switch (requestInput()) {
                case 1:
                    currentOrders();
                    break;
                case 2:
                    pendingOrders();
                    break;

                case 0:
                    return;
            }
        }
    }

    private void currentOrders() {
        while (true) {
            clearConsole();
            System.out.println("Current orders");
            System.out.println("--------------\n");

            List<OrderEntry> currentOrders = staff.currentOrders();

            int i = 0;
            for (OrderEntry orderEntry : currentOrders)
                System.out.printf("%d. %s\n", ++i, orderEntry.toStringMinimal());

            System.out.println("\n");

            System.out.println("0. Exit");

            var choice = requestInput();

            switch (choice) {
                case 0:
                    return;
                default:
                    var order = currentOrders.get(choice - 1);
                    if (order != null)
                        selectCurrentOrder(order);
                    break;
            }
        }
    }

    private void selectCurrentOrder(OrderEntry entry) {
        while (true) {
            clearConsole();
            System.out.println("Order information");
            System.out.println("-----------------\n");

            System.out.println(entry);
            System.out.println();

            System.out.println("1. Mark as delivered");
            System.out.println("2. Drop responsibility");

            System.out.println("\n");

            System.out.println("0. Exit");

            var choice = requestInput();

            switch (choice) {
                case 0:
                    return;
                case 1:
                    staff.markOrderAsDelivered(entry.ID);
                    return;
                case 2:
                    staff.dropOrder(entry.ID);
                    return;
            }
        }
    }

    private void pendingOrders() {
        while (true) {
            clearConsole();
            System.out.println("Pending orders");
            System.out.println("--------------\n");

            List<OrderEntry> pendingOrders = staff.getEligiblePendingOrders();

            int i = 0;
            for (OrderEntry orderEntry : pendingOrders)
                System.out.printf("%d. %s\n", ++i, orderEntry.toStringMinimal());

            System.out.println("\n");

            System.out.println("0. Exit");

            var choice = requestInput();

            switch (choice) {
                case 0:
                    return;
                default:
                    var order = pendingOrders.get(choice - 1);
                    if (order != null)
                        staff.selectOrder(order.ID);
                    break;
            }
        }
    }
}
