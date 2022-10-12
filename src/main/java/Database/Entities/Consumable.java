package Database.Entities;

public class Consumable {
    public int ID = -1;
    public String name = null;

    @Override
    public String toString() {
        return String.format("(%d, %s)", ID, name);
    }
}
