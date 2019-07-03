package sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Recipe {
    String name;
    String Image; //for image location purpose

    ArrayList<Ingredient> items;

    public Recipe(String name, String image, ArrayList<Ingredient> items) {
        this.name = name;
        Image = image;
        this.items = items;
    }

    void addItem(Recipe recipe, Ingredient item, int amount) {
        for (int i = 0; i < amount; i++){
            recipe.items.add(item);
        }
    }

    void removeItem(Recipe recipe, Ingredient item, int amount) {
        for (int i = 0; i < amount; i++){
            recipe.items.remove(item);
        }
    }

    static File createRecipeList() throws IOException{
        File recipeList = new File("Recipes.txt");
        recipeList.createNewFile();
        return recipeList;
    }

    static void loadRecipeList(File recipesList, ArrayList<String> recipes) throws  IOException{
        try (BufferedReader br = new BufferedReader(new FileReader(recipesList))){
            String line;
            while ((line = br.readLine()) != null){
                recipes.add(line);
            }
            br.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
