package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.MissingResourceException;
import java.util.Optional;
import java.util.ResourceBundle;

public class GUI {
    private Stage window;
    private String currentView;
    private Object currentSelection;

    private String previousView;
    private Object previousSelection;

    public void setWindow(Stage window){
        this.window = window;
        this.window.setScene(new Scene(new VBox()));
    }

    public void changeView(String view) throws IOException {
        previousView = currentView;
        previousSelection = currentSelection;

        currentView = view;
        currentSelection = null;

        //Load the view
        Parent root = FXMLLoader.load(getClass().getResource("/UI/" + currentView + ".fxml"));

        //Display the view
        window.getScene().setRoot(root);
    }

    public void changeView(String view, Object selection, boolean isNew) throws IOException{
        previousView = currentView;
        previousSelection = currentSelection;

        currentView = view;
        currentSelection = selection;

        //Get the view
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/" + currentView + ".fxml"));

        //Load the view
        Parent root = fxmlLoader.load();

        //Get the view's controller
        ISelectionController controller = fxmlLoader.getController();

        //Pass the selected object to the controller
        controller.setSelectedObject(selection, isNew);

        //Display the view
        window.getScene().setRoot(root);
    }

    public void refreshView() throws IOException{
        changeView(currentView);
    }

    public void goToPreviousView() throws IOException{
        if (previousSelection != null)
            changeView(previousView, previousSelection, false);
        else
            changeView(previousView);
    }

    public void openWindow(String view, String titleKey) throws IOException{
        openWindowWithObject(view, titleKey, null);
    }

    public void openWindowWithObject(String view, String titleKey, Object obj) throws IOException{
        //Get the view
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/" + view + ".fxml"));

        //Load the view
        Parent root = fxmlLoader.load();

        if (obj != null) {
            //Get the view's controller
            ISelectionController controller = fxmlLoader.getController();

            //Pass the selected object to the controller
            controller.setSelectedObject(obj, true);
        }

        Scene newScene = new Scene(root);
        newScene.getStylesheets().add("styles/default.css");

        Stage newWindow = new Stage();
        newWindow.setScene(newScene);
        newWindow.initModality(Modality.APPLICATION_MODAL);
        newWindow.setResizable(false);
        newWindow.setTitle((titleKey));

        newWindow.showAndWait();
    }

    public void closeWindow(Stage window)
    {
        window.close();
    }

    public Object getPreviousSelection() {
        return previousSelection;
    }

    public boolean showYesNoDialog(String titleKey, String contentKey) {
        boolean answer = false;

        Alert alert = new Alert(Alert.AlertType.NONE);

        alert.setTitle((titleKey));
        alert.setContentText((contentKey));

        ButtonType no = new ButtonType(("No"), ButtonBar.ButtonData.NO);
        ButtonType yes = new ButtonType(("Yes"), ButtonBar.ButtonData.YES);

        alert.getButtonTypes().setAll(no, yes);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == yes)
            answer = true;
        else if (result.get() == no)
            answer = false;

        return answer;
    }

    public static void warningDialog(String titleKey, String contentKey){
        boolean answer = false;

        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle((titleKey));
        alert.setContentText(contentKey);

        ButtonType okay = new ButtonType(("Okay"), ButtonBar.ButtonData.OK_DONE);

        alert.getButtonTypes().setAll(okay);
        Optional<ButtonType> result = alert.showAndWait();
    }
}