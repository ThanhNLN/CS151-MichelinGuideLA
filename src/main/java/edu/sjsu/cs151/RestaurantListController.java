package edu.sjsu.cs151;

import edu.sjsu.cs151.databaseDisplayControls.DetailsDisplay;
import edu.sjsu.cs151.databaseDisplayControls.SelectionDisplay;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class RestaurantListController {

    @FXML
    private ListView<String> restaurantListView;
    @FXML
    private Label categoryItemLabel; //to update the selected categoryItem name
    private Stage stage;
    private RememberedSelection rememberedSelection = new RememberedSelection();

    /** setting the correct list of restaurants to display */
    public void setRestaurantList(RememberedSelection rememberedSelection){
        this.rememberedSelection = rememberedSelection;

        categoryItemLabel.setText(this.rememberedSelection.getCategoryItemSelection());

        SelectionDisplay detailsDisplay = new DetailsDisplay();

        ObservableList<String> categoryAndSelection = FXCollections.observableArrayList();
        categoryAndSelection.add(this.rememberedSelection.getCategoryButton()); //for determining from what category to display
        categoryAndSelection.add(this.rememberedSelection.getCategoryItemSelection()); //for determining what category item to display

        try {
            detailsDisplay.displaySelection(categoryAndSelection); //return categoryAndSelection's list of restaurants
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        restaurantListView.setItems(categoryAndSelection);
    }

    /** handles when user clicks on a restaurant in the list */
    @FXML
    private void handleRestaurantListViewClick() {
        this.rememberedSelection.setRestaurantSelection(restaurantListView.getSelectionModel().getSelectedItem());
        if (this.rememberedSelection.getRestaurantSelection() != null){
            openRestaurantDetailsScreen(rememberedSelection);
        }
    }

    /** handles to take user back to previous screen, and highlights the previous selection in the categoryItemsList*/
    @FXML
    private void onBackButtonClick() {
        // TODO: fix it so it highlights the previous button selection on the old screen?
        try {
            App.displayHomeScreenRememberSelection(stage, this.rememberedSelection);
        } catch (IOException e){
            //e.printStackTrace();
        }
    }

    /** scrolls to and highlights the user's previous selection when the back button is used on the next page */
    public void updateRestaurantListView(RememberedSelection rememberedSelection){
        HomeScreenController.updateRememberedListViewHelper(restaurantListView, rememberedSelection.getRestaurantSelection());
    }

    /** opens the new screen with the selected restaurant's details*/
    private void openRestaurantDetailsScreen(RememberedSelection rememberedSelection){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RestaurantDetailsScreen.fxml"));
            Parent root = loader.load();

            RestaurantDetailsController restaurantDetailsController = loader.getController();
            restaurantDetailsController.displayRestaurantDetailsScreen(root, stage, rememberedSelection);
        } catch (IOException e){
            //e.printStackTrace();
        }
    }

    /** Displays the category item's list of restaurants screen */
    public void displaySelectedCategoryItemDetailsScreen(Parent root, Stage stage, RememberedSelection rememberedSelection) {
        this.stage = stage;
        this.rememberedSelection = rememberedSelection;

        setRestaurantList(rememberedSelection);
        updateRestaurantListView(rememberedSelection);

        Scene scene = new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight());
        this.stage.setScene(scene);
        this.stage.show();
    }
}
