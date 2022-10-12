package Database.Entities;

import java.util.ArrayList;

import Database.Datamapper;

public class Drink extends MenuItem {

    public static ArrayList<Drink> getAllDrinks() {
        return Datamapper.mapData(Drink.class, "Menu", "WHERE Type = \"Drink\"");
    }
}
