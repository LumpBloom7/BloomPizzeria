import java.util.HashMap;

import Database.Database;
import Database.Entities.MenuItem;
import Database.Entities.User;

public class Cart {
    private HashMap<MenuItem, Integer> contents = new HashMap<>();

    public void add(MenuItem item) {
        contents.merge(item, 1, (a, b) -> a + b);
    }

    public void remove(MenuItem item) {
        if (!contents.containsKey(item))
            return;

        contents.merge(item, 1, (a, b) -> a - b);

        if (contents.get(item) <= 0)
            contents.remove(item);
    }

    public void clear() {
        contents.clear();
    }

    public float getTotalPrice() {
        float result = 0;
        for (var pair : contents.entrySet())
            result += pair.getKey().price * pair.getValue();

        return result;
    }

    public void checkout(User user) {
        if (!Database.executeStatement(
                String.format("INSERT INTO OrderEntry (userName, orderTime, price) VALUES (%s, NOW(), %f)",
                        user.username, getTotalPrice())))
            return;

        var index = Database.getLastInsertIndex();
        if (index == -1)
            return;

        String contentStatement = "INSERT INTO OrderContent VALUES";
        int i = 0;
        for (var pair : contents.entrySet()) {
            contentStatement = String.format("%s (%d, %d, %d) %s", contentStatement, index, pair.getKey().consumableID,
                    pair.getValue(), ++i == contents.size() ? ";" : ",");
        }

        if (!Database.executeStatement(contentStatement))
            return;

        contents.clear();
    }
}
