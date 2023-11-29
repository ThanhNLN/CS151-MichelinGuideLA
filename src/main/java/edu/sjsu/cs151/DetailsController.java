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
import java.sql.SQLException;

public class DetailsController {

    @FXML
    private ListView<String> detailsListView;
    @FXML
    private Label detailsLabel;

    private String category;


    private Stage stage;
    private SelectionDisplay selectionDisplay;

    public void setStage(Stage primaryStage) {
        this.stage = primaryStage;
    }
    public void setSelectionDisplay(SelectionDisplay selectionDisplay) {
        this.selectionDisplay = selectionDisplay;
    }

    public void setDetails(String category, String details){

        detailsLabel.setText(details);
        this.category = category;

        // TODO: fix this selectionDisplay
        SelectionDisplay selectionDisplay = new DetailsDisplay();

        ObservableList<String> items = FXCollections.observableArrayList();
        items.add(category);
        items.add(details);

        try {
            selectionDisplay.displaySelection(items);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        detailsListView.setItems(items);
    }

    public void handleListViewClick() {
        // TODO: handle selection to take to new screen, displaying the restaurant details?

    }

    public void onBackButtonClick() {
        // TODO: fix it so it highlights the previous button selection on the old screen?
        try {
            App.homeScreenRememberSelection(stage, selectionDisplay, category, detailsLabel.getText());
        } catch (IOException e){
            //e.printStackTrace();
        }


    }


}
