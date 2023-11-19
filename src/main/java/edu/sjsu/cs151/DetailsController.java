package edu.sjsu.cs151;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class DetailsController {

    @FXML
    private ListView listView;
    @FXML
    private Label detailsLabel;

    private Stage stage;
    
    public void setDetails(String details){
        detailsLabel.setText(details);
    }

    public void handleListViewClick(MouseEvent mouseEvent) {
    }

    public void onSelectButtonClick(ActionEvent actionEvent) {
    }

    public void onBackButtonClick(ActionEvent actionEvent) {

        //load the old screen?
        try {
            App.homeScreen(stage);
        } catch (IOException e){
            e.printStackTrace();
        }


    }

    public void setStage(Stage primaryStage) {
        this.stage = primaryStage;
    }
}
