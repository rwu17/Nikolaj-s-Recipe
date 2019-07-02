package sample;

import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;

//import javax.swing.text.html.ImageView;
//import java.awt.*;
import java.awt.*;
import java.awt.color.*;
import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.effect.*;
import javafx.scene.image.ImageView;
import javafx.scene.effect.Blend;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

public class MainMenuController {
    @FXML VBox MenuID;
    @FXML HBox topSide;
    @FXML VBox leftSide;
    @FXML VBox recipeCreation;
    @FXML ImageView recipeNew;
    @FXML Button toMainMenu;
    @FXML Button createButton;
    @FXML TextField recipeNameTextField;
    @FXML Button addItemButton;
    @FXML Button removeItemButton;
    @FXML TextField ingredientNameTextField;
    @FXML TextField ingredientAmountTextField;
    @FXML Button deleteRecipeButton;
    @FXML TableView<Item> ingredientTable;
    @FXML TableColumn<Item, String> ingredients;
    @FXML TableColumn<Item, Integer> amount;

    private TableManager<Item> itemsManager;


    @FXML
    private void initialize(){
        itemsManager = new TableManager<>(ingredientTable);
        itemsManager.setupColumns();
        ingredientTable.getSelectionModel().selectedItemProperty().addListener(this::onItemSelected);
    }

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

    public void createRecipe() throws IOException{
        boolean confirmed = Main.gui.showYesNoDialog("Nikolaj's Recipe",
                "Are you sure you want to create the recipe?");

        if (confirmed){
            //Recipe recipe = new Recipe();

            File recipesList = Recipe.createRecipeList();

            BufferedWriter bw = null;

            File recipeFile = new File(recipeNameTextField.getText() + ".csv");
            try {
                recipeFile.createNewFile();
            } catch (IOException e){
                e.printStackTrace();
            }
            try{
                bw = new BufferedWriter(new FileWriter(recipesList, true));
            } catch (IOException e){
                System.out.println("bw = new bufferedwriter error");
                e.printStackTrace();
            }
            try {
                bw.append(recipeNameTextField.getText()).append("\n");
            } catch (IOException e){
                System.out.println("bw.append error");
                e.printStackTrace();
            }

            ArrayList<String> recipes = new ArrayList<>();

            try (BufferedReader br = new BufferedReader(new FileReader(recipesList))) {
                String line;
                while ((line = br.readLine()) != null) {
                    recipes.add(line);
                }
                System.out.print(recipes);
                System.out.println("\n---------------------\n");
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("\n---------------------\n");
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void initiateRecipes(){
        ArrayList<Recipe> items;


    }

    static void LoadRecipe(File recipe, String recipeName, String sourPath){
        String line = "";
        String csvSplitBy = ";";
        String directory = sourPath + "/" + recipeName + ".csv";

        Path path = Paths.get(directory);

        if (Files.exists(path)){
            try(BufferedReader br = new BufferedReader(new FileReader(directory))){
                while ((line = br.readLine()) != null){
                    String[] Items = line.split(csvSplitBy, -1);

                    //here for whenever ; splits, save the info from split
                    //String ;
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
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

    public void addItem(){

        String ingredientName = ingredientNameTextField.getText();
        String ingredientAmount = ingredientAmountTextField.getText();
        System.out.println("Name: " + ingredientName + " Amount: " + ingredientAmount);

    }

    private void onItemSelected(ObservableValue<? extends Item> obs, Item oldSelection, Item newSelection){
        //selectedItem = newSelection;

        if (oldSelection == newSelection){
            return;
        }

    }

    public void ingredientNameTextField(){
        TextField ingredientName = ingredientNameTextField;
        /*
        ingredientName.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
                if(!newValue.matches("\\d{0,1}([\\.]\\d{0,2})?")){
                    ingredientName.setText(oldValue);
                }
            }


        })
        */
    }

    public void removeRecipe(){

    }

}
