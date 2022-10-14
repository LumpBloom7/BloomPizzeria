package Database.Entities;

import java.sql.Timestamp;
import java.util.List;

import Database.Datamapper;

public class OrderEntry {
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

    @Override
    public String toString() {
        return String.format("(%d, %s, %s, %f, %b, %d, %b)", ID, username, orderTime, price, cancelled, deliveryBy,
                delivered);
    }

}
