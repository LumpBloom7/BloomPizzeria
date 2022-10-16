import java.util.List;
import java.util.Map.Entry;

import Database.Entities.Dessert;
import Database.Entities.Drink;
import Database.Entities.MenuItem;
import Database.Entities.Pizza;
import Database.Entities.User;

public class Menu extends Screen {
    public List<Pizza> pizzas = Pizza.getAllPizzas();
    public List<Drink> drinks = Drink.getAllDrinks();
    public List<Dessert> desserts = Dessert.getAllDesserts();

    private final Cart cart = new Cart();

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

            if (cart.hasItems())
                System.out.printf("\n4. Cart (%d items)\n", cart.itemCount());

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
                case 4:
                    if (!cart.hasItems())
                        break;

                    showCart();
                    break;
            }
        }
    }

    public void pizzaMenu() {
        while (true) {
            clearConsole();
            for (int i = 0; i < pizzas.size(); i++) {
                var pizza = pizzas.get(i);
                System.out.printf("%d. %s\n", i + 1, pizza.name);
                System.out.printf("    Price      : %f\n", pizza.price);
                System.out.printf("    Vegan      : %b\n", pizza.vegan);
                System.out.printf("    Ingredients: {\n");

                for (var ingredient : pizza.getIngredients())
                    System.out.printf("        %s\n", ingredient.ingredientName);

                System.out.println("    }\n");
            }

            System.out.println("0. Back");

            int r = requestInput();

            switch (r) {
                case 0:
                    return;
                default:
                    var index = r - 1;
                    if (index >= pizzas.size())
                        break;

                    cart.add(pizzas.get(index));
                    break;
            }
        }
    }

    public void drinksMenu() {
        while (true) {
            clearConsole();
            for (int i = 0; i < drinks.size(); i++) {
                var drink = drinks.get(i);
                System.out.printf("%d. %s\n", i + 1, drink.name);
                System.out.printf("    Price      : %f\n", drink.price);
                System.out.printf("    Vegan      : %b\n", drink.vegan);
                System.out.println("    }\n");
            }

            System.out.println("0. Back");

            int r = requestInput();

            switch (r) {
                case 0:
                    return;
                default:
                    var index = r - 1;
                    if (index >= drinks.size())
                        break;

                    cart.add(drinks.get(index));
                    break;
            }
        }
    }

    public void dessertMenu() {
        while (true) {
            clearConsole();
            for (int i = 0; i < desserts.size(); i++) {
                var dessert = desserts.get(i);
                System.out.printf("%d. %s\n", i + 1, dessert.name);
                System.out.printf("    Price      : %f\n", dessert.price);
                System.out.printf("    Vegan      : %b\n", dessert.vegan);
                System.out.println("    }\n");
            }

            System.out.println("0. Back");

            int r = requestInput();

            switch (r) {
                case 0:
                    return;
                default:
                    var index = r - 1;
                    if (index >= desserts.size())
                        break;

                    cart.add(desserts.get(index));
                    break;
            }
        }
    }

    public void showCart() {
        while (true) {
            clearConsole();
            System.out.println("Cart");
            System.out.println("----\n");

            int i = 0;

            var contents = cart.getItems();
            for (var entry : contents) {
                System.out.printf("%d. %s (x%d)\n", ++i, entry.name, cart.getNumberOf(entry));
            }

            System.out.println("\n0. Back");

            var r = requestInput();

            switch (r) {
                case 0:
                    return;
                default:
                    var index = r - 1;
                    if (index >= contents.length)
                        break;

                    cartEntryDetails(contents[index]);
            }
        }
    }

    public void cartEntryDetails(MenuItem item) {
        while (true) {
            clearConsole();

            System.out.println("Cart entry details");
            System.out.println("------------------\n");

            System.out.println(item);
            System.out.println("Quantity: " + cart.getNumberOf(item));

            System.out.println();
            System.out.println("1. Add another");
            System.out.println("2. Remove one");
            System.out.println("3. Remove all");
            System.out.println("");
            System.out.println("0. Back");

            switch (requestInput()) {
                case 1:
                    cart.add(item);
                    break;
                case 2:
                    cart.remove(item);
                    break;
                case 3:
                    cart.removeAll(item);
                    break;
                case 0:
                    return;
            }
        }
    }

    public static void main(String[] args) {
        var menu = new Menu(User.getUser("Bloom"));
        menu.mainMenu();
    }
}
