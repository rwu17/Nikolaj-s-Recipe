package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.prefs.Preferences;

public class SettingMenuController {
    @FXML VBox SettingsID;
    @FXML Button recipeLocationButton;
    @FXML Button saveSettingButton;
    @FXML Button backToMainButton;
    @FXML TextField recipeLocationTextField;

    private Preferences prefs;

    private String recipePath;

    @FXML
    public void initialize() throws IOException{
        //Use previous values for import/export directories
        prefs = Preferences.userNodeForPackage(sample.Main.class);
        //prefs = Preferences.userNodeForPackage(dk.aau.cs.ds308e18.Main.class);
        setDestinationPath(prefs.get("dataExportDestinationDirectory", ""));

    }

    public void browseLocation(ActionEvent event){

        final DirectoryChooser directoryChooser = new DirectoryChooser();

        Stage stage = (Stage) SettingsID.getScene().getWindow();

        File selectedDirectory = directoryChooser.showDialog(stage);

        if (selectedDirectory != null){
            setDestinationPath(selectedDirectory.getAbsolutePath());


        }
    }

    private void setDestinationPath(String path) {
        recipePath = path;
        recipeLocationTextField.setText(path);

        prefs.put("dataExportDestinationDirectory", path);
    }

    public void backToMain(ActionEvent actionEvent) throws IOException {
        Main.gui.changeView("MainMenu");
    }
}
