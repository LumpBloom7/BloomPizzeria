-- Initialize the database (and wipe previous copy) --
DROP DATABASE BloomPizzeria;
CREATE DATABASE BloomPizzeria;
USE BloomPizzeria;
-- --
-- Create the schemas --
CREATE TABLE IF NOT EXISTS Ingredient(
    ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    IngredientName VARCHAR(255) NOT NULL,
    Price FLOAT NOT NULL,
    Vegan BOOLEAN
);
-- --
CREATE TABLE IF NOT EXISTS Consumable(
    ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(255) NOT NULL UNIQUE
);
-- --
CREATE TABLE IF NOT EXISTS Pizza(
    ID INT NOT NULL PRIMARY KEY,
    FOREIGN KEY (ID) REFERENCES Consumable(ID)
);
-- --
CREATE TABLE IF NOT EXISTS Drink(
    ID INT NOT NULL PRIMARY KEY,
    FOREIGN KEY (ID) REFERENCES Consumable(ID),
    Price FLOAT NOT NULL
);
-- --
CREATE TABLE IF NOT EXISTS Dessert(
    ID INT NOT NULL PRIMARY KEY,
    FOREIGN KEY (ID) REFERENCES Consumable(ID),
    Price FLOAT NOT NULL
);
-- --
CREATE TABLE IF NOT EXISTS PizzaIngredient(
    PizzaID INT NOT NULL,
    IngredientID INT NOT NULL,
    FOREIGN KEY (IngredientID) REFERENCES Ingredient(ID),
    FOREIGN KEY (PizzaID) REFERENCES Pizza(ID)
);

-- --
CREATE TABLE IF NOT EXISTS User(
    Username VARCHAR(255) NOT NULL PRIMARY KEY,
    Name VARCHAR(255) NOT NULL,
    Address VARCHAR(255) NOT NULL,
    Postcode VARCHAR(255) NOT NULL,
    PhoneNumber VARCHAR(255) NOT NULL
);

-- --
CREATE TABLE IF NOT EXISTS OrderEntry(
    ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    Username VARCHAR(255) NOT NULL,
    OrderTime VARCHAR(255) NOT NULL,
    Cancelled BOOLEAN,
    FOREIGN KEY (Username) REFERENCES User(Username)
);

-- --
CREATE TABLE IF NOT EXISTS OrderContents(
    OrderID INT NOT NULL,
    ConsumableID int NOT NULL,
    FOREIGN KEY (OrderID) REFERENCES OrderEntry(ID),
    FOREIGN KEY (ConsumableID) REFERENCES Consumable(ID)
);
-- --


-- --
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
-- --
-- Create price chart for Pizza (includes the 1.4x multiplier) --
CREATE OR REPLACE VIEW PizzaMenu AS
SELECT PizzaIngredient.PizzaID AS ConsumableID,
    Consumable.Name AS Name,
    SUM(Ingredient.Price) * 1.4 AS Price,
    (SUM(Ingredient.Vegan) / COUNT(Ingredient.Vegan)) >= 1 AS Vegan
FROM PizzaIngredient
    INNER JOIN Ingredient ON PizzaIngredient.IngredientID = Ingredient.ID
    INNER JOIN Consumable ON PizzaIngredient.PizzaID = Consumable.ID
GROUP BY PizzaIngredient.PizzaID;
-- --
-- Create price chart for drinks --
CREATE OR REPLACE VIEW DrinkMenu AS
SELECT Drink.ID AS ConsumableID,
    Consumable.Name AS Name,
    Drink.Price AS Price
FROM Drink
    INNER JOIN Consumable ON Drink.ID = Consumable.ID;
-- --
-- Create price chart for desserts --
CREATE OR REPLACE VIEW DessertMenu AS
SELECT Dessert.ID AS ConsumableID,
    Consumable.Name AS Name,
    Dessert.Price AS Price
FROM Dessert
    INNER JOIN Consumable ON Dessert.ID = Consumable.ID;
-- --
-- Create unified price chart --
CREATE OR REPLACE VIEW Menu AS
SELECT *,
    "Pizza" as Type
FROM PizzaMenu
UNION
SELECT *,
    TRUE as Vegan,
    "Drink" as Type
FROM DrinkMenu
UNION
SELECT *,
    TRUE as Vegan,
    "Dessert" as Type
FROM DessertMenu
ORDER BY ConsumableID;

-- --
-- Sample order --

INSERT INTO User VALUES ("Bloom", "Derrick Timmermans", "Molenstraat 60B", "3600", "+60102680158");
INSERT INTO OrderEntry (Username, OrderTime) VALUES("Bloom", "2022-10-07T15:16:53Z");
INSERT INTO OrderContents VALUES (1,1),(1,1),(1,1),(1,1),(1,1) ;
