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
-- Delivery personel --
CREATE TABLE DeliveryStaff(
    ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(255) NOT NULL,
    Cuteness FLOAT NOT NULL
);
-- --
CREATE TABLE IF NOT EXISTS OrderEntry(
    ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    Username VARCHAR(255) NOT NULL,
    OrderTime TIMESTAMP NOT NULL,
    Cancelled BOOLEAN,
    DeliveryBy INT,
    Delivered BOOLEAN,
    FOREIGN KEY (Username) REFERENCES User(Username),
    FOREIGN KEY (DeliveryBy) REFERENCES DeliveryStaff(ID)
);
-- --
CREATE TABLE IF NOT EXISTS OrderContents(
    OrderID INT NOT NULL,
    ConsumableID int NOT NULL,
    FOREIGN KEY (OrderID) REFERENCES OrderEntry(ID),
    FOREIGN KEY (ConsumableID) REFERENCES Consumable(ID)
);
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
CREATE TABLE DeliveryArea(
    DeliveryStaffID INT NOT NULL,
    Postcode VARCHAR(255) NOT NULL,
    PRIMARY KEY (DeliveryStaffID, Postcode),
    FOREIGN KEY (DeliveryStaffID) REFERENCES DeliveryStaff(ID)
);
-- --
-- Fancy method to get pending deliveries --
CREATE PROCEDURE GetDelivery(deliveryStaffID INT) BEGIN
SELECT o.*,
    User.Postcode AS Postcode,
    DeliveryArea.DeliveryStaffID AS Deliverer
FROM OrderEntry o
    INNER JOIN User ON o.Username = User.Username
    INNER JOIN DeliveryArea ON User.Postcode = DeliveryArea.Postcode
WHERE Cancelled IS NOT TRUE
    AND o.DeliveryBy IS NULL
    AND DeliveryArea.DeliveryStaffID = deliveryStaffID
ORDER BY o.OrderTime;
END
