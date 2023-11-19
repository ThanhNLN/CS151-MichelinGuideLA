package edu.sjsu.cs151;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

public class DetailsController {

    @FXML
    private ListView<String> listView;
    @FXML
    private Label detailsLabel;

    private Stage stage;
    
    public void setDetails(String details){
        detailsLabel.setText(details);
    }

    public void handleListViewClick() {
    }

    public void onSelectButtonClick() {
    }

    public void onBackButtonClick() {

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
