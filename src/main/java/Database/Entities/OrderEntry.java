package Database.Entities;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import Database.Database;
import Database.Datamapper;

public class OrderEntry extends IDatabaseEntity {
    public int ID;
    public String username;
    public Timestamp orderTime;
    public float price;
    public boolean cancelled;
    public int deliveryBy;
    public boolean delivered;

    private List<OrderContent> orderContents;

    public List<OrderContent> getOrderContents() {
        if (orderContents == null) {
            orderContents = Datamapper.mapData(OrderContent.class, "OrderContent",
                    String.format("WHERE OrderID = %d", ID));
        }

        return orderContents;
    }

    public static OrderEntry getOrder(int orderID) {
        var rs = Datamapper.mapData(OrderEntry.class, "OrderEntry", String.format("WHERE ID = %d", orderID));

        if (rs.isEmpty())
            return null;

        return rs.get(0);
    }

    public boolean tryCancelOrder() {
        if (orderTime.toInstant().plusSeconds(1800).isBefore(Instant.now()))
            return false;

        return Database.executeStatement(String.format("UPDATE OrderEntry SET Cancelled = TRUE WHERE ID = %d", ID));
    }

    public static void main(String[] args) {
        var order = getOrder(1);
        order.tryCancelOrder();
    }
}
