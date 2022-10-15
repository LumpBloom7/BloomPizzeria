import java.util.List;
import java.util.Scanner;

import Database.Entities.Dessert;
import Database.Entities.Drink;
import Database.Entities.Pizza;

public class Menu extends Screen {
    public List<Pizza> pizzas = Pizza.getAllPizzas();
    public List<Drink> drinks = Drink.getAllDrinks();
    public List<Dessert> desserts = Dessert.getAllDesserts();

    private Scanner input = new Scanner(System.in);

    public void mainMenu() {
        while (true) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
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
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
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
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
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
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
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
        var menu = new Menu();
        menu.mainMenu();
    }
}
