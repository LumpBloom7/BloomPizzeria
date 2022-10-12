import java.util.ArrayList;
import java.util.List;

import Database.Datamapper;
import Database.Entities.Dessert;
import Database.Entities.Drink;
import Database.Entities.Pizza;

public class Menu {
    public List<Pizza> pizzas = Pizza.getAllPizzas();
    public List<Drink> drink = Drink.getAllDrinks();
    public List<Dessert> desserts = Dessert.getAllDesserts();
}
