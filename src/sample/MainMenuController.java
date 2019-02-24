package sample;

import javafx.fxml.FXML;

//import javax.swing.text.html.ImageView;
//import java.awt.*;
import java.awt.*;
import java.awt.color.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.effect.*;
import javafx.scene.image.ImageView;
import javafx.scene.effect.Blend;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainMenuController {
    @FXML VBox MenuID;
    @FXML HBox topSide;
    @FXML VBox leftSide;
    @FXML VBox recipeCreation;
    @FXML ImageView recipeNew;
    @FXML Button toMainMenu;
    @FXML Button createButton;
    @FXML TextField recipeNameTextField;

    public void recipeCreateMenu(){
        leftSide.setDisable(true);
        topSide.setDisable(true);
        recipeCreation.setVisible(true);
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
        boolean confirmed = Main.gui.showYesNoDialog("Nikolaj's Recipe",
                "Are you sure you want to cancel this recipe?");

        if (confirmed){
            recipeCreation.setVisible(false);
            leftSide.setDisable(false);
            topSide.setDisable(false);
        }
    }

    public void createRecipe(){
        boolean confirmed = Main.gui.showYesNoDialog("Nikolaj's Recipe",
                "Are you sure you want to create the recipe?");

        if (confirmed){
            //Recipe recipe = new Recipe();
            File recipeFile = new File(recipeNameTextField.getText() + ".csv");
            try {
                recipeFile.createNewFile();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void initiateRecipes(){
        ArrayList<Recipe> items;


    }

    public void addImage(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a picture");

        //final DirectoryChooser directoryChooser = new DirectoryChooser();

        Stage stage = (Stage) MenuID.getScene().getWindow();

        fileChooser.showOpenDialog(stage);

        //File selectedFile = directoryChooser.showDialog(stage);
        /*
        if (selectedFile != null){

        }
        */
    }
}
