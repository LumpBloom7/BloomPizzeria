-- Sample users --
INSERT INTO User
VALUES (
        "Bloom",
        "Derrick Timmermans",
        "Molenstraat 60B",
        "3600",
        "+60102680158"
    ),
    (
        "Bloomie",
        "Derrick Timmermans 2",
        "Molenstraat 60B",
        "36002",
        "+60102680158"
    ),
    (
        "Primrose",
        "Derrick Timmermans 3",
        "Molenstraat 60B",
        "36002",
        "+60102680158"
    );

-- Sample orders --
INSERT INTO OrderEntry (Username, OrderTime, Price)
VALUES("Bloom", NOW(), 420);

INSERT INTO OrderContent
VALUES (1, 1, 5);

INSERT INTO OrderEntry (Username, OrderTime, Price)
VALUES("Bloomie", NOW(), 69);

INSERT INTO OrderContent
VALUES (2, 1, 5);

INSERT INTO OrderEntry (Username, OrderTime, Price)
VALUES("Primrose", NOW(), 68);

INSERT INTO OrderContent
VALUES (3, 1, 1),
    (3, 2, 25);

INSERT INTO OrderEntry (Username, OrderTime, Price)
VALUES("Bloom", NOW(), 25);

INSERT INTO OrderContent
VALUES (4, 1, 56);

-- Delivery staff --
INSERT INTO DeliveryStaff(Name, Cuteness)
VALUES ("Menno", 0.1),
    ("Florescence", 10);

INSERT INTO DeliveryArea
VALUES (1, "3600"),
    (2, "3600"),
    (2, "36002");
