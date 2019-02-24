package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static GUI gui;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws IOException {
        gui = new GUI();
    }

    @Override
    public void start(Stage primaryStage) throws IOException{

        gui.setWindow(primaryStage);

        //Parent root = FXMLLoader.load(getClass().getResource("../../resources/UI/MainMenu.fxml"));
        primaryStage.setTitle("Nikolaj's Recipes");

        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(600);
        //primaryStage.setScene(new Scene(root, 800, 600));
        Screen screen = Screen.getPrimary();
        double scale = screen.getOutputScaleX();
        /*
        if (scale <= 1.25){
            primaryStage.setMinWidth(1500);
            primaryStage.setMinHeight(860);
        }
        */
        gui.changeView("MainMenu");
        primaryStage.show();
    }
}
