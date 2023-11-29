package edu.sjsu.cs151;

import edu.sjsu.cs151.databaseDisplayControls.SelectionDisplay;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.sql.SQLException;


public class App extends Application {

    // initial launch
    @Override
    public void start(Stage stage) throws IOException {
        homeScreen(stage);
    }

    // home screen set up
    public static Controller homeScreen(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("welcome.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 520, 440);

        Controller controller = fxmlLoader.getController();
        controller.setPrimaryStage(stage);

        stage.setTitle("Michelin Star Restaurant Search - LA  \\(^w^)/");
        stage.setScene(scene);
        stage.show();

        return controller;
    }

    // home screen set up, with remembered selection
    public static void homeScreenRememberSelection(Stage stage, SelectionDisplay selectionDisplay, String category, String selection) throws IOException{
        Controller controller = homeScreen(stage);

        controller.setSelectionDisplay(selectionDisplay);
        controller.updateListView(category, selection);
    }

    public static void main(String[] args) throws SQLException, IOException, ParseException {
        JDBC jdbc = new JDBC();
        jdbc.createDatabase();
        launch();
    }
}