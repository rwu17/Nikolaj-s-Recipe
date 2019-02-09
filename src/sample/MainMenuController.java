package sample;

import javafx.fxml.FXML;

//import javax.swing.text.html.ImageView;
//import java.awt.*;
import java.awt.color.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.effect.*;
import javafx.scene.image.ImageView;
import javafx.scene.effect.Blend;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class MainMenuController {
    @FXML ImageView recipeNew;
    @FXML VBox recipeCreation;
    @FXML Button toMainMenu;

    public void recipeCreateMenu(){
        recipeCreation.setVisible(true);
        System.out.println("create a new recipe");
    }

    public void recipeSearch(){
        System.out.println("searching for a existing recipe");
    }

    public void blendImage(){
        Blend blend = new Blend();
        blend.setMode(BlendMode.COLOR_BURN);
        ColorInput blendColorInput = new ColorInput();
        blendColorInput.setPaint(Color.STEELBLUE);
        blendColorInput.setX(0);
        blendColorInput.setY(0);
        blendColorInput.setWidth(95);
        blendColorInput.setHeight(110);
        blend.setTopInput(blendColorInput);
        recipeNew.setEffect(blend);
    }

    public void removeEffect(){
        recipeNew.setEffect(null);
    }

    public void backToMain(){
        recipeCreation.setVisible(false);
    }

    public void initiateRecipes(){
        ArrayList<Recipe> items;


    }

}
