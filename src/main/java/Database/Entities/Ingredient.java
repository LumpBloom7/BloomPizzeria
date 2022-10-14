package Database.Entities;

import Database.Datamapper;

public class Ingredient extends IDatabaseEntity {
    public int ID;
    public String ingredientName;
    public float price;
    public boolean vegan;

    @Override
    public String toString() {
        return String.format("(%d, %s, %f, %b)", ID, ingredientName, price, vegan);
    }

    public static Ingredient getIngredient(int ID) {
        var ingredients = Datamapper.mapData(Ingredient.class, "Ingredient", String.format("WHERE ID = %d", ID));

        if (ingredients.isEmpty())
            return null;

        return ingredients.get(0);
    }
}
