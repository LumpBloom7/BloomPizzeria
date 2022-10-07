DROP DATABASE Lila2;

CREATE DATABASE Lila2; 

USE Lila2;

CREATE TABLE Ingredients (
    ID int NOT NULL AUTO_INCREMENT, 
    name varchar(64) NOT NULL,
    vegetarian varchar(32) NOT NULL,
    price float NOT NULL,
    PRIMARY KEY (ID)
    );

CREATE TABLE Menu (
    ID int NOT NULL AUTO_INCREMENT, 
    name varchar(64) NOT NULL,
    PRIMARY KEY (ID) 
    );

CREATE TABLE Pizza (
ID int NOT NULL,
FOREIGN KEY (ID) REFERENCES Menu(ID)
);

CREATE TABLE PizzaIng (
P_ID int NOT NULL,
I_ID int NOT NULL,
FOREIGN KEY (P_ID) REFERENCES Pizza(ID),
FOREIGN KEY (I_ID) REFERENCES Ingredients(ID),
PRIMARY KEY(P_ID, I_ID)
);

CREATE TABLE Drinks_and_dessert (
ID int NOT NULL, 
price float NOT NULL,
FOREIGN KEY (ID) REFERENCES Menu(ID)
);

CREATE TABLE Customer ( 
    name varchar(255) NOT NULL,
    phonenumber varchar(50) NOT NULL,
    number_of_pizzas_ordered int NULL,
    postal_code varchar(32) NOT NULL,
    street_housenumber_extra varchar(255) NOT NULL,
    PRIMARY KEY (phonenumber)
    );

CREATE TABLE Orders (
order_ID int NOT NULL AUTO_INCREMENT,
price float NOT NULL,
time varchar(255) NOT NULL,
phonenumber varchar(50) NOT NULL,
FOREIGN KEY (phonenumber) REFERENCES Customer(phonenumber),
PRIMARY KEY (order_ID)
);

CREATE TABLE OrderContent (
order_ID int NOT NULL,
ID int NOT NULL,
FOREIGN KEY (order_ID) REFERENCES Orders(order_ID),
FOREIGN KEY (ID) REFERENCES Menu(ID),
PRIMARY KEY(order_ID, ID)
);

INSERT INTO Menu (name) VALUES ("Margherita");
INSERT INTO Menu (name) VALUES ("Marinara");
INSERT INTO Menu (name) VALUES ("Peperoni");
INSERT INTO Menu (name) VALUES ("Prosciutto e funghi");
INSERT INTO Menu (name) VALUES ("Bufala");
INSERT INTO Menu (name) VALUES ("Diavola");
INSERT INTO Menu (name) VALUES ("Vegetariana");
INSERT INTO Menu (name) VALUES ("Hawaiian");
INSERT INTO Menu (name) VALUES ("Salsiccia e friarielli");
INSERT INTO Menu (name) VALUES ("Salsiccia e patate");
INSERT INTO Menu (name) VALUES ("Water");
INSERT INTO Menu (name) VALUES ("Coca-cola");
INSERT INTO Menu (name) VALUES ("Fanta");
INSERT INTO Menu (name) VALUES ("Sprite");
INSERT INTO Menu (name) VALUES ("Ice tea");
INSERT INTO Menu (name) VALUES ("Heineken");
INSERT INTO Menu (name) VALUES ("Peroni");

INSERT INTO Pizza VALUES (1), (2), (3), (4), (5), (6), (7), (8), (9), (10);


INSERT INTO Drinks_and_dessert VALUES (11, 1);
INSERT INTO Drinks_and_dessert VALUES (12, 2);
INSERT INTO Drinks_and_dessert VALUES (13, 2);
INSERT INTO Drinks_and_dessert VALUES (14, 2);
INSERT INTO Drinks_and_dessert VALUES (15, 2);
INSERT INTO Drinks_and_dessert VALUES (16, 2);
INSERT INTO Drinks_and_dessert VALUES (17, 2);

INSERT INTO Ingredients (name, vegetarian, price) VALUES ("pizza dough", "yes", 5);
INSERT INTO Ingredients (name, vegetarian, price) VALUES ("tomato sauce", "yes", 2);
INSERT INTO Ingredients (name, vegetarian, price) VALUES ("mozzarella", "yes", 2);
INSERT INTO Ingredients (name, vegetarian, price) VALUES ("garlic", "yes", 1);
INSERT INTO Ingredients (name, vegetarian, price) VALUES ("oregano", "yes", 1);
INSERT INTO Ingredients (name, vegetarian, price) VALUES ("peperoni", "no", 2);
INSERT INTO Ingredients (name, vegetarian, price) VALUES ("prosciutto", "no", 2);
INSERT INTO Ingredients (name, vegetarian, price) VALUES ("funghi", "yes", 2);
INSERT INTO Ingredients (name, vegetarian, price) VALUES ("bufala cheese", "yes", 3);
INSERT INTO Ingredients (name, vegetarian, price) VALUES ("ventricina (spicy salami)", "no", 2);
INSERT INTO Ingredients (name, vegetarian, price) VALUES ("eggplant", "yes", 1);
INSERT INTO Ingredients (name, vegetarian, price) VALUES ("artichokes", "yes", 1);
INSERT INTO Ingredients (name, vegetarian, price) VALUES ("pineapple", "yes", 2);
INSERT INTO Ingredients (name, vegetarian, price) VALUES ("sausage", "yes", 1);
INSERT INTO Ingredients (name, vegetarian, price) VALUES ("friarielli", "yes", 2);
INSERT INTO Ingredients (name, vegetarian, price) VALUES ("potatoes", "yes", 2);

INSERT INTO PizzaIng (P_ID, I_ID) VALUES (1, 1);
INSERT INTO PizzaIng (P_ID, I_ID) VALUES (1, 2);
INSERT INTO PizzaIng (P_ID, I_ID) VALUES (1, 3);
INSERT INTO PizzaIng (P_ID, I_ID) VALUES (2, 1);
INSERT INTO PizzaIng (P_ID, I_ID) VALUES (2, 2);
INSERT INTO PizzaIng (P_ID, I_ID) VALUES (2, 4);
INSERT INTO PizzaIng (P_ID, I_ID) VALUES (2, 5);
INSERT INTO PizzaIng (P_ID, I_ID) VALUES (3, 1);
INSERT INTO PizzaIng (P_ID, I_ID) VALUES (3, 2);
INSERT INTO PizzaIng (P_ID, I_ID) VALUES (3, 3);
INSERT INTO PizzaIng (P_ID, I_ID) VALUES (3, 6);
INSERT INTO PizzaIng (P_ID, I_ID) VALUES (4, 1);
INSERT INTO PizzaIng (P_ID, I_ID) VALUES (4, 2);
INSERT INTO PizzaIng (P_ID, I_ID) VALUES (4, 3);
INSERT INTO PizzaIng (P_ID, I_ID) VALUES (4, 7);
INSERT INTO PizzaIng (P_ID, I_ID) VALUES (4, 8);
INSERT INTO PizzaIng (P_ID, I_ID) VALUES (5, 1);
INSERT INTO PizzaIng (P_ID, I_ID) VALUES (5, 2);
INSERT INTO PizzaIng (P_ID, I_ID) VALUES (5, 3);
INSERT INTO PizzaIng (P_ID, I_ID) VALUES (6, 1);
INSERT INTO PizzaIng (P_ID, I_ID) VALUES (6, 2);
INSERT INTO PizzaIng (P_ID, I_ID) VALUES (6, 3);
INSERT INTO PizzaIng (P_ID, I_ID) VALUES (6, 10);
INSERT INTO PizzaIng (P_ID, I_ID) VALUES (7, 1);
INSERT INTO PizzaIng (P_ID, I_ID) VALUES (7, 2);
INSERT INTO PizzaIng (P_ID, I_ID) VALUES (7, 3);
INSERT INTO PizzaIng (P_ID, I_ID) VALUES (7, 11);
INSERT INTO PizzaIng (P_ID, I_ID) VALUES (7, 12);
INSERT INTO PizzaIng (P_ID, I_ID) VALUES (8, 1);
INSERT INTO PizzaIng (P_ID, I_ID) VALUES (8, 2);
INSERT INTO PizzaIng (P_ID, I_ID) VALUES (8, 3);
INSERT INTO PizzaIng (P_ID, I_ID) VALUES (8, 7);
INSERT INTO PizzaIng (P_ID, I_ID) VALUES (8, 13);
INSERT INTO PizzaIng (P_ID, I_ID) VALUES (9, 1);
INSERT INTO PizzaIng (P_ID, I_ID) VALUES (9, 2);
INSERT INTO PizzaIng (P_ID, I_ID) VALUES (9, 3);
INSERT INTO PizzaIng (P_ID, I_ID) VALUES (9, 14);
INSERT INTO PizzaIng (P_ID, I_ID) VALUES (9, 15);
INSERT INTO PizzaIng (P_ID, I_ID) VALUES (10, 1);
INSERT INTO PizzaIng (P_ID, I_ID) VALUES (10, 2);
INSERT INTO PizzaIng (P_ID, I_ID) VALUES (10, 14);
INSERT INTO PizzaIng (P_ID, I_ID) VALUES (10, 16);

INSERT INTO Customer (name, phonenumber, postal_code, street_housenumber_extra) VALUES ("birb", 1234567890, "1234AB", "regenstraat 5");