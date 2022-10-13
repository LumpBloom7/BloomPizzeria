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
--Sample orders--
INSERT INTO OrderEntry (Username, OrderTime)
VALUES("Bloom", NOW());
INSERT INTO OrderContents
VALUES (1, 1),
    (1, 1),
    (1, 1),
    (1, 1),
    (1, 1);
INSERT INTO OrderEntry (Username, OrderTime)
VALUES("Bloomie", NOW());
INSERT INTO OrderContents
VALUES (2, 1),
    (2, 1),
    (2, 1),
    (2, 1),
    (2, 1);
INSERT INTO OrderEntry (Username, OrderTime)
VALUES("Primrose", NOW());
INSERT INTO OrderContents
VALUES (3, 1),
    (3, 1),
    (3, 1),
    (3, 1),
    (3, 1);
INSERT INTO OrderEntry (Username, OrderTime)
VALUES("Bloom", NOW());
INSERT INTO OrderContents
VALUES (4, 1),
    (4, 1),
    (4, 1),
    (4, 1),
    (4, 1);
-- Delivery staff --
INSERT INTO DeliveryStaff(Name, Cuteness)
VALUES ("Menno", 0.1)
VALUES ("Florescence", 10);
-- --
INSERT INTO DeliveryArea
VALUES (1, "3600"),
    (2, "3600"),
    (2, "36002");
