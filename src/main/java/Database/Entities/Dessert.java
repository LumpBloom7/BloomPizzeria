package Database.Entities;

import java.util.ArrayList;

import Database.Datamapper;

public class Dessert extends MenuItem {

    public static ArrayList<Dessert> getAllDesserts() {
        return Datamapper.mapData(Dessert.class, "Menu", "WHERE Type = \"Dessert\"");
    }
}
