package Database.Entities;

public abstract class MenuItem {
    public int consumableID;
    public String name;
    public float price;
    public boolean vegan;

    @Override
    public String toString() {
        return String.format("%d, %s, %f, %b", consumableID, name, price, vegan);
    }
}
