package edu.sjsu.cs151;

import edu.sjsu.cs151.databaseDisplayControls.DetailsDisplay;
import edu.sjsu.cs151.databaseDisplayControls.SelectionDisplay;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    //private SelectionDisplay selectionDisplay;

    public void setStage(Stage primaryStage) {
        this.stage = primaryStage;
    }

    public void setDetails(String details){
        detailsLabel.setText(details);

        // TODO: fix this selectionDisplay, only temporary to test
        SelectionDisplay selectionDisplay = new DetailsDisplay();

        ObservableList<String> items = FXCollections.observableArrayList();
        selectionDisplay.displaySelection(items);
        //listView.setVisible(true);
        listView.setItems(items);
    }

    public void handleListViewClick() {
        // TODO: handle selection to take to new screen, displaying the restaurant details?

    }

    public void onBackButtonClick() {

        //load the old screen?
        // TODO: fix it so it shows the previous selection on the old screen?
        // * select category, and highlight last chosen item?
        try {
            App.homeScreen(stage);
        } catch (IOException e){
            e.printStackTrace();
        }


    }

}
