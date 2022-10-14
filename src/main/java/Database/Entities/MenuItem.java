package Database.Entities;

import Database.Datamapper;

public class MenuItem extends IDatabaseEntity {
    public int consumableID;
    public String name;
    public float price;
    public boolean vegan;

    public static MenuItem getMenuItem(int id) {
        var res = Datamapper.mapData(MenuItem.class, "Menu", String.format("WHERE consumableID = %d", id));

        if (res.isEmpty())
            return null;

        return res.get(0);
    }

    @Override
    public boolean equals(Object other) {
        return !(other instanceof MenuItem otherMenuItem) || consumableID == otherMenuItem.consumableID;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(consumableID);
    }
}
