-- Add pizzas --
INSERT INTO Consumable (Name)
VALUES ("Pepperoni"),
    ("Hawaiian"),
    ("Margherita"),
    ("Mushroom"),
    ("Salami"),
    ("BBQ chicken"),
    ("Vegan");
-- --
INSERT INTO Pizza
VALUES (1),
    (2),
    (3),
    (4),
    (5),
    (6),
    (7);
-- --
INSERT INTO Ingredient (IngredientName, Price, Vegan)
VALUES ("Mozarella", 1.25, FALSE),
    ("Pineapple", 2, TRUE),
    ("Pepperoni", 2, FALSE),
    ("Pulled beef", 3, FALSE),
    ("Chicken", 1.5, FALSE),
    ("Olives", 1, TRUE),
    ("Tapioca balls", 6, NULL),
    ("Ham", 3, FALSE),
    ("Mushroom", 1.25, TRUE),
    ("Tomato sauce", 1.25, TRUE),
    ("BBQ sauce", 1.25, TRUE),
    ("Pumpkin spice", 5, TRUE),
    ("Salami", 2, FALSE);
-- --
-- Associate pizzas with their ingredients--
INSERT INTO PizzaIngredient
VALUES (1, 1),
    (1, 3),
    (1, 10),
    (2, 1),
    (2, 2),
    (2, 5),
    (2, 10),
    (3, 1),
    (3, 10),
    (4, 1),
    (4, 9),
    (4, 10),
    (5, 1),
    (5, 10),
    (5, 12),
    (6, 1),
    (6, 5),
    (6, 11),
    (7, 6),
    (7, 9),
    (7, 10);
-- --
-- Add drinks--
INSERT INTO Consumable (Name)
VALUES ("Generic brand Cola"),
    ("Ice Lemon Tea"),
    ("Red Cow");
INSERT INTO Drink
VALUES (8, 2.5),
    (9, 3.5),
    (10, 5);
-- --
-- Add desserts--
INSERT INTO Consumable (Name)
VALUES (
        "The sweetest smile from the cutest personnel we have"
    ),
    ("Lava cake"),
    ("Bread sticks");
-- --
INSERT INTO Dessert
VALUES (11, 0),
    (12, 8),
    (13, 6);
