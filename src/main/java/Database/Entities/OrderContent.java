package Database.Entities;

// Literally only used to find the consumables in anorder
public class OrderContent {
    public int consumableID;
    public int quantity;

    private MenuItem menuItem;

    public MenuItem getMenuItem() {
        if (menuItem == null)
            menuItem = MenuItem.getMenuItem(consumableID);

        return menuItem;
    }
}
