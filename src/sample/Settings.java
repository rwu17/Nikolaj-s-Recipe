package sample;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class Settings {

    static File createSettings() throws IOException {
        File Settings = new File("Settings.txt");
        Settings.createNewFile();
        return Settings;
    }

    static void LoadSettings(File SettingsFile, TextField pathTextField) {
        try (BufferedReader br = new BufferedReader(new FileReader(SettingsFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                pathTextField.setText(line);

            }
            System.out.print(pathTextField.getText());
            System.out.println("\n---------------------\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
