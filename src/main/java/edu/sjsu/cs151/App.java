package edu.sjsu.cs151;

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
        displayHomeScreen(stage);
    }

    // home screen set up
    public static HomeScreenController displayHomeScreen(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("HomeScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 520, 340);

        HomeScreenController homeScreenController = fxmlLoader.getController();
        homeScreenController.setStage(stage);

        stage.setTitle("Michelin Star Restaurant Search - LA  \\(^w^)/");
        stage.setScene(scene);
        stage.show();

        return homeScreenController;
    }

    // home screen set up, with remembered selection
    public static void displayHomeScreenRememberSelection(Stage stage, RememberedSelection rememberedSelection) throws IOException{
        HomeScreenController homeScreenController = displayHomeScreen(stage);
        homeScreenController.updateCategoryItemsListView(rememberedSelection);
    }

    public static void main(String[] args) throws SQLException, IOException, ParseException {
        JDBC jdbc = new JDBC();
        jdbc.createDatabase();
        jdbc.jsonToDatabase();
        launch();
        jdbc.disconnect();
    }
}