package sample;

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
import java.util.prefs.Preferences;

public class SettingMenuController {
    @FXML VBox SettingsID;
    @FXML Button recipeLocationButton;
    @FXML Button saveSettingButton;
    @FXML Button backToMainButton;
    @FXML TextField recipeLocationTextField;


    private String recipePath;

    @FXML
    public void initialize() throws IOException{

        File Settings = sample.Settings.createSettings();

        sample.Settings.LoadSettings(Settings, recipeLocationTextField);

    }

    public void browseLocation(ActionEvent actionEvent) throws IOException{

        final DirectoryChooser directoryChooser = new DirectoryChooser();

        Stage stage = (Stage) SettingsID.getScene().getWindow();

        File selectedDirectory = directoryChooser.showDialog(stage);

        if (selectedDirectory != null){

            File Settings = sample.Settings.createSettings();

            BufferedWriter bw = new BufferedWriter(new FileWriter(Settings, true));

            recipeLocationTextField.setText(selectedDirectory.getAbsolutePath());

            bw.append(recipeLocationTextField.getText());
        }
    }

    public void saveSettings(ActionEvent actionEvent) {
        boolean confirm = Main.gui.showYesNoDialog("Nikolaj's Recipe", "Any changes to the settings will be saved, are you sure you want to proceed?");

        if (confirm){
            try {
                Main.gui.changeView("MainMenu");
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }


    public void backToMain(ActionEvent actionEvent) {
        boolean confirm = Main.gui.showYesNoDialog("Nikolaj's Recipe", "Any changes to the settings will not be saved, are you sure you want to go back to the main menu?");

        if (confirm){
            try {
                Main.gui.changeView("MainMenu");
            } catch (IOException e){
                e.printStackTrace();
            }

        }
    }
}
