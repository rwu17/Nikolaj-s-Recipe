package sample;

import java.util.ArrayList;

public class Recipe {
    String name;
    String Image; //for image location purpose

    ArrayList<Item> items;

    public Recipe(String name, String image, ArrayList<Item> items) {
        this.name = name;
        Image = image;
        this.items = items;
    }

    void addItem(Recipe recipe, Item item, int amount) {
        for (int i = 0; i < amount; i++){
            recipe.items.add(item);
        }
    }

    void removeItem(Recipe recipe, Item item, int amount) {
        for (int i = 0; i < amount; i++){
            recipe.items.remove(item);
        }
    }
}
