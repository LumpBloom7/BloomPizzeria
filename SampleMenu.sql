-- Add pizzas --
INSERT INTO Consumable (Name)
VALUES ("Pepperoni Pizza"),
    ("Hawaiian Pizza"),
    ("Margherita Pizza"),
    ("Mushroom Pizza"),
    ("Salami Pizza"),
    ("BBQ chicken Pizza"),
    ("Vegan Pizza"),
    ("Vegan on a Pizza"),
    ("Sardine Pizza"),
    ("None Pizza with Left Beef");

INSERT INTO Pizza
VALUES (1),
    (2),
    (3),
    (4),
    (5),
    (6),
    (7),
    (8),
    (9),
    (10);

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
    ("Salami", 2, FALSE),
    ("Sardines", 3, FALSE);

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
    (7, 10),
    (8, 1),
    (8, 10),
    (9, 1),
    (9, 10),
    (9, 14),
    (10, 4);

-- Add drinks--
INSERT INTO Consumable (Name)
VALUES ("Generic brand Cola"),
    ("Ice Lemon Tea"),
    ("Red Cow");

INSERT INTO Drink
VALUES (11, 2.5),
    (12, 3.5),
    (13, 5);

-- Add desserts--
INSERT INTO Consumable (Name)
VALUES (
        "The sweetest smile from the cutest personnel we have"
    ),
    ("Lava cake"),
    ("Bread sticks");

INSERT INTO Dessert
VALUES (14, 0),
    (15, 8),
    (16, 6);
