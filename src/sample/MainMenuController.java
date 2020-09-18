package sample;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

//import javax.swing.text.html.recipeImage;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.effect.Blend;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

public class MainMenuController {
    @FXML VBox MenuID;
    @FXML HBox topSide;
    @FXML VBox leftSide;
    @FXML TextField searchTextField;
    @FXML Button searchButton;
    @FXML VBox recipeCreation;
    @FXML Button recipeCreate;
    @FXML Button toMainMenu;
    @FXML Button createButton;
    @FXML Button imageButton;
    @FXML ImageView recipeImage;
    @FXML TextField recipeNameTextField;
    @FXML Button addItemButton;
    @FXML Button removeItemButton;
    @FXML TextField ingredientNameTextField;
    @FXML TextField ingredientAmountTextField;
    @FXML Button deleteRecipeButton;
    @FXML TableView<Ingredient> ingredientTable;
    @FXML TableColumn<Ingredient, String> ingredients;
    @FXML TableColumn<Ingredient, String> amount;

    private TableManager<Ingredient> itemsManager;

    ArrayList<Object> Recipes = new ArrayList<>();

    @FXML
    private void initialize(){
        itemsManager = new TableManager<>(ingredientTable);
        itemsManager.setupColumns();
        ingredientTable.getSelectionModel().selectedItemProperty().addListener(this::onItemSelected);

        ingredients.setCellValueFactory(new PropertyValueFactory<>("Name"));
        amount.setCellValueFactory(new PropertyValueFactory<>("Amount"));

        ingredientTable.setEditable(true);

        ingredients.setCellFactory(TextFieldTableCell.forTableColumn());

        amount.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    /*
    public void ingredientAmountValidation(){
        ingredientNameTextField.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                if(!ingredientNameTextField.getText().matches("\\d+()")){
                //if(!ingredientNameTextField.getText().matches("[0-9]\\.[0-9]|6\\.0")){
                    //when it not matches the pattern (1.0 - 6.0)
                    //set the textField empty
                    ingredientNameTextField.setText("");
                }
            }
        });
    }
    */

    public void recipeSearch(){
        if (searchTextField.getText().isEmpty()){
            GUI.warningDialog("Nikolaj's Recipe", "Please enter name of an ingredient or recipe!");
        } else {
            //if the search word exists
            System.out.println(searchTextField.getText());
        }


    }

    public void backToMain(){
        boolean confirmed = Main.gui.showYesNoDialog("Nikolaj's Recipe",
                "Any changes done to this recipe will not be saved, are you sure you want to proceed?");

        if (confirmed){
            recipeCreation.setVisible(false);
            leftSide.setDisable(false);
            topSide.setDisable(false);
        }
    }

    public void createRecipe() throws IOException{

        if (recipeNameTextField.getText().isEmpty()){

            GUI.warningDialog("Nikolaj's Recipe", "Please enter a name for your recipe!");

        } else {

            boolean confirmed = Main.gui.showYesNoDialog("Nikolaj's Recipe",
                    "Are you sure you want to create the recipe?");

            if (confirmed){

                ArrayList<String> ingredientNames = new ArrayList<>();

                ArrayList<String> ingredientAmounts = new ArrayList<>();

                Recipe recipe = new Recipe(recipeNameTextField.getText(), recipeImage.getImage(), ingredientNames, ingredientAmounts);

                for (Object row: ingredientTable.getItems()) {
                    for (TableColumn column : ingredientTable.getColumns()) {
                        if (column.equals(ingredients)){
                            ingredientNames.add(column.getCellObservableValue(row).getValue().toString());

                        } else if (column.equals(amount)){
                            ingredientAmounts.add(column.getCellObservableValue(row).getValue().toString());

                        }
                        
                    }

                }

                try {
                    //PrintWriter pw = new PrintWriter(new File());



                } catch (Exception e){
                    e.printStackTrace();
                }



                BufferedWriter bw = null;

                File recipeFile = new File(recipeNameTextField.getText() + ".csv");
                try {
                    recipeFile.createNewFile();
                } catch (IOException e){
                    e.printStackTrace();
                }


                System.out.println("\n---------------------\n");

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

        Stage stage = (Stage) MenuID.getScene().getWindow();

        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null){
            Image image = new Image(selectedFile.toURI().toString());

            recipeImage.setImage(image);

        }
    }

    /*
    public void centerImage() {
        Image img = recipeImage.getImage();
        if (img != null) {
            double w = 0;
            double h = 0;

            double ratioX = recipeImage.getFitWidth() / img.getWidth();
            double ratioY = recipeImage.getFitHeight() / img.getHeight();

            double reducCoeff = 0;
            if(ratioX >= ratioY) {
                reducCoeff = ratioY;
            } else {
                reducCoeff = ratioX;
            }

            w = img.getWidth() * reducCoeff;
            h = img.getHeight() * reducCoeff;

            recipeImage.setX((recipeImage.getFitWidth() - w) / 2);
            recipeImage.setY((recipeImage.getFitHeight() - h) / 2);

        }
    }
     */

    public void addItem(){

        String ingredientName = null;

        String ingredientAmount = null;

        if (!((ingredientNameTextField.getText().isEmpty()) && (ingredientAmountTextField.getText().isEmpty()))){

            ingredientName = ingredientNameTextField.getText();
            ingredientAmount = ingredientAmountTextField.getText();

            Ingredient ingredient = new Ingredient(ingredientName, ingredientAmount);

            ingredientTable.getItems().add(ingredient);

            ingredientNameTextField.clear();
            ingredientAmountTextField.clear();

            //System.out.println("Name: " + ingredientName + " Amount: " + ingredientAmount);


            // test for tableview
            /*
            ArrayList<String> ingredientNames = new ArrayList<>();

            ArrayList<String> ingredientAmounts = new ArrayList<>();

                for (Object row: ingredientTable.getItems()) {
                    for (TableColumn column : ingredientTable.getColumns()) {
                        if (column.equals(ingredients)){
                            ingredientNames.add(column.getCellObservableValue(row).getValue().toString());

                        } else if (column.equals(amount)){
                            ingredientAmounts.add(column.getCellObservableValue(row).getValue().toString());

                        }

                    }

                }
                System.out.println(ingredientNames);

                System.out.println(ingredientAmounts);
             */


        } else if(((ingredientNameTextField.getText().isEmpty()) || (ingredientAmountTextField.getText().isEmpty()))){
            GUI.warningDialog("Nikolaj's Recipe",
                    "You forgot either the name or the amount of ingredient!");
        }

        else {
            GUI.warningDialog("Nikolaj's Recipe",
                    "You must fill the name and the amount of ingredient!");
        }
    }

    private void onItemSelected(ObservableValue<? extends Ingredient> obs, Ingredient oldSelection, Ingredient newSelection){
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
        boolean confirmed = Main.gui.showYesNoDialog("Nikolaj's Recipe", "Are you sure you want to delete this recipe? The deleted recipe cannot be recovered!");
    }

    public void onEditNameChange(TableColumn.CellEditEvent<Ingredient, String> ingredientStringCellEditEvent) {
        Ingredient ingredient = ingredientTable.getSelectionModel().getSelectedItem();
        ingredient.setName(ingredientStringCellEditEvent.getNewValue());
    }

    public void onEditAmountChange(TableColumn.CellEditEvent<Ingredient, String> ingredientStringCellEditEvent) {
        Ingredient ingredient = ingredientTable.getSelectionModel().getSelectedItem();
        ingredient.setAmount(ingredientStringCellEditEvent.getNewValue());
    }

    public void removeItem(ActionEvent actionEvent) {
        ingredientTable.getItems().removeAll(ingredientTable.getSelectionModel().getSelectedItem());
    }

    public void gotoSettings(ActionEvent actionEvent) throws IOException{
        Main.gui.changeView("SettingMenu");
    }

    public void recipeNew(ActionEvent actionEvent) {
        leftSide.setDisable(true);
        topSide.setDisable(true);
        recipeCreation.setVisible(true);
    }
}
