package Database.Entities;

import java.util.ArrayList;
import Database.Datamapper;

public class Pizza extends MenuItem {
    private ArrayList<Ingredient> ingredients;

    public ArrayList<Ingredient> getIngredients() {
        if (ingredients == null) {
            ingredients = new ArrayList<>();

            var iids = Datamapper.mapData(IngredientIDWrapper.class, "PizzaIngredient",
                    String.format("WHERE PizzaID = %d", consumableID));

            for (var iid : iids) {
                ingredients.add(Ingredient.getIngredient(iid.IngredientID));
            }
        }

        return ingredients;
    }

    @Override
    public String toString() {
        String ing = "[";
        for (var Ingredient : getIngredients()) {
            ing = ing + Ingredient.ingredientName + ", ";
        }
        ing = ing + "]";
        return super.toString() + " " + ing;
    }

    public static ArrayList<Pizza> getAllPizzas() {
        return Datamapper.mapData(Pizza.class, "Menu", "WHERE Type = \"Pizza\"");
    }
}
