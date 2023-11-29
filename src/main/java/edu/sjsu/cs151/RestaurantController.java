package edu.sjsu.cs151;

import edu.sjsu.cs151.databaseDisplayControls.SelectionDisplay;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class RestaurantController {
    @FXML
    private Label detailsLabel;

    private Stage stage;

    private String category;
    private String selectedFromCategory;

    private String selectedRestaurant;
    private SelectionDisplay savedSelectionDisplay;

    public void setSavedSelectionDisplay(SelectionDisplay selectionDisplay){
        this.savedSelectionDisplay = selectionDisplay;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public void setSelectedRestaurant(String selectedRestaurant){
        this.selectedRestaurant = selectedRestaurant;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setDetails(String selectedOption) {
        detailsLabel.setText(selectedOption);
    }

    public void setSelectedFromCategory(String selectedFromCategory) {
        this.selectedFromCategory = selectedFromCategory;
    }

    @FXML
    private void onBackButtonClick(ActionEvent actionEvent) {
        // TODO: finish this

        Controller controller = null;
        try {
            controller = App.homeScreen(stage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        controller.setSelectionDisplay(savedSelectionDisplay);
        controller.openDetailsScreen(category, selectedFromCategory, selectedRestaurant);

    }


}
