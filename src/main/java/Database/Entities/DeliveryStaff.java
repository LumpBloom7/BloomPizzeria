package Database.Entities;

import java.util.List;

import Database.Database;
import Database.Datamapper;

public class DeliveryStaff {
    public int id;
    public String name;
    public float cuteness;

    public static DeliveryStaff getDeliveryStaff(int id) {
        var rs = Datamapper.mapData(DeliveryStaff.class, "DeliveryStaff", "WHERE ID = id");

        if (rs.isEmpty())
            return null;

        return rs.get(0);
    }

    public List<OrderEntry> currentOrders() {
        return Datamapper.mapData(OrderEntry.class, "OrderEntry", String.format("WHERE DeliveryBy = %d", id));
    }

    public List<OrderEntry> getEligiblePendingOrders() {
        return Datamapper.mapData(OrderEntry.class, Database.executeQuery(String.format("CALL GetDelivery(%d)", id)));
    }

    public boolean selectOrder(int orderID) {
        // Double check to see if we can select it \
        // (other delivery staff may have sniped this person.)
        {
            boolean found = false;
            for (var order : getEligiblePendingOrders()) {
                if (order.ID == orderID)
                    found = true;
                break;
            }

            if (!found)
                return false;
        }

        Database.executeStatement(
                String.format("UPDATE OrderEntry SET DeliveryBy = %d WHERE ID = %d", id, orderID));

        return true;

    }

    public static void main(String[] args) {
        var staff = getDeliveryStaff(1);

        var pendingOrders = staff.getEligiblePendingOrders();

        for (var order : pendingOrders) {
            System.out.println(order);
        }

        staff.selectOrder(pendingOrders.get(0).ID);
    }
}
