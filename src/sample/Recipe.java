package sample;

import javafx.scene.image.Image;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Recipe {
    String name;
    javafx.scene.image.Image ImageLocation; //for image location purpose

    ArrayList<String> ingredientNames;

    ArrayList<String> ingredientAmounts;

    public Recipe(String name, Image imageLocation, ArrayList<String> ingredientNames, ArrayList<String> ingredientAmounts) {
        this.name = name;
        ImageLocation = imageLocation;
        this.ingredientNames = ingredientNames;
        this.ingredientAmounts = ingredientAmounts;
    }

    public Recipe() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public javafx.scene.image.Image getImageLocation() {
        return ImageLocation;
    }

    public void setImageLocation(javafx.scene.image.Image imageLocation) {
        ImageLocation = imageLocation;
    }

    public ArrayList<String> getIngredientNames() {
        return ingredientNames;
    }

    public void setIngredientNames(ArrayList<String> ingredientNames) {
        this.ingredientNames = ingredientNames;
    }

    public ArrayList<String> getIngredientAmounts() {
        return ingredientAmounts;
    }

    public void setIngredientAmounts(ArrayList<String> ingredientAmounts) {
        this.ingredientAmounts = ingredientAmounts;
    }

}
