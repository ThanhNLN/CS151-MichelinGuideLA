package edu.sjsu.cs151;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.sql.SQLException;


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

    public static void main(String[] args) throws SQLException, IOException, ParseException {
        JDBC jdbc = new JDBC();
        jdbc.createDatabase();
        launch();
    }
}