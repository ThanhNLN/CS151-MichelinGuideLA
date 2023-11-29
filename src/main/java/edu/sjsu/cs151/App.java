package edu.sjsu.cs151;

import edu.sjsu.cs151.databaseDisplayControls.SelectionDisplay;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        homeScreen(stage);
    }

    public static void homeScreen(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("welcome.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 520, 440);

        Controller controller = fxmlLoader.getController();
        controller.setPrimaryStage(stage);

        stage.setTitle("Michelin Search Guide LA!");
        stage.setScene(scene);
        stage.show();
    }

    public static void homeScreenRememberSelection(Stage stage, SelectionDisplay selectionDisplay, String selection) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("welcome.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 520, 440);

        Controller controller = fxmlLoader.getController();
        controller.setPrimaryStage(stage);
        controller.setSelectionDisplay(selectionDisplay);
        controller.updateListView(selection);
        //scene.getFocusOwner().setVisible(false);

        stage.setTitle("Michelin Search Guide LA!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}