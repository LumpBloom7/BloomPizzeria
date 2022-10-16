import java.util.List;

import Database.Entities.Dessert;
import Database.Entities.Drink;
import Database.Entities.Pizza;
import Database.Entities.User;

public class Menu extends Screen {
    public List<Pizza> pizzas = Pizza.getAllPizzas();
    public List<Drink> drinks = Drink.getAllDrinks();
    public List<Dessert> desserts = Dessert.getAllDesserts();

    private final User user;

    public Menu(User user) {
        this.user = user;
    }

    public void mainMenu() {
        while (true) {
            clearConsole();
            System.out.println("Please choose a category:");
            System.out.println("-------------------------");
            System.out.println("1. Pizzas");
            System.out.println("2. Drinks");
            System.out.println("3. Desserts");

            // if cart > 0
            // add cart

            System.out.println("\n0. Quit");

            switch (requestInput()) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    pizzaMenu();
                    break;
                case 2:
                    drinksMenu();
                    break;
                case 3:
                    dessertMenu();
                    break;
            }
        }
    }

    public int pizzaMenu() {
        clearConsole();
        for (int i = 0; i < pizzas.size(); i++) {
            var pizza = pizzas.get(i);
            System.out.printf("%d. %s\n", i + 1, pizzas.get(i).name);
            System.out.printf("    Price      : %f\n", pizza.price);
            System.out.printf("    Vegan      : %b\n", pizza.vegan);
            System.out.printf("    Ingredients: {\n");

            for (var ingredient : pizza.getIngredients())
                System.out.printf("        %s\n", ingredient.ingredientName);

            System.out.println("    }\n");
        }

        requestInput();

        return -1;
    }

    public int drinksMenu() {
        clearConsole();
        for (int i = 0; i < drinks.size(); i++) {
            var drink = drinks.get(i);
            System.out.printf("%d. %s\n", i + 1, pizzas.get(i).name);
            System.out.printf("    Price      : %f\n", drink.price);
            System.out.printf("    Vegan      : %b\n", drink.vegan);
            System.out.println("    }\n");
        }

        requestInput();

        return -1;
    }

    public int dessertMenu() {
        clearConsole();
        for (int i = 0; i < desserts.size(); i++) {
            var dessert = desserts.get(i);
            System.out.printf("%d. %s\n", i + 1, pizzas.get(i).name);
            System.out.printf("    Price      : %f\n", dessert.price);
            System.out.printf("    Vegan      : %b\n", dessert.vegan);
            System.out.println("    }\n");
        }

        requestInput();

        return -1;
    }

    public static void main(String[] args) {
        var menu = new Menu(User.getUser("Bloom"));
        menu.mainMenu();
    }
}
