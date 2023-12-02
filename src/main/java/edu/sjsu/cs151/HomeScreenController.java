package edu.sjsu.cs151;

import edu.sjsu.cs151.databaseDisplayControls.CuisineDisplay;
import edu.sjsu.cs151.databaseDisplayControls.LocationDisplay;
import edu.sjsu.cs151.databaseDisplayControls.PriceDisplay;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class HomeScreenController {
//    @FXML private Button locationButton, cuisineButton, priceButton; //possibly if we wanted to set button text
    @FXML
    private ListView<String> controllerListView;
    private Stage stage;
    private RememberedSelection rememberedSelection = new RememberedSelection();


    /** Getters/Setters:
     * 1) setStage(Stage)
     */
    public void setStage(Stage stage){
        this.stage = stage;
    }

    /** Handle category button selections:
     *  1) Location
     *  2) Cuisine
     *  3) Price
      */
    @FXML
    protected void handleLocationButtonClick() {
        rememberedSelection.setCategorySelectionDisplay(new LocationDisplay());
        updateCategoryItemsListView();
    }

    @FXML
    protected void handleCuisineButtonClick() {
        rememberedSelection.setCategorySelectionDisplay(new CuisineDisplay());
        updateCategoryItemsListView();
    }

    @FXML
    protected void handlePriceButtonClick() {
        rememberedSelection.setCategorySelectionDisplay(new PriceDisplay());
        updateCategoryItemsListView();
    }

    /** Generating the lists:
     * 1) initial categoryListView (no category item selections yet, (not loaded with back button)
     * 2) category list view if there have been selections, loaded only after back button
     * 3) helper for getting highlighted remembered selection, also used in CategoryItemsController
     */
    // initial categoryListView (no category item selections yet, (not loaded with back button)
    protected void updateCategoryItemsListView(){
        ObservableList<String> categoryItems = FXCollections.observableArrayList();

        try {
            rememberedSelection.setCategoryButton(rememberedSelection.getCategorySelectionDisplay().displaySelection(categoryItems));
        } catch (SQLException e) {
            //e.printStackTrace();
        }

        controllerListView.setVisible(true);
        controllerListView.setItems(categoryItems);
    }

    //category list view if there have been selections, but only after back button
    public void updateCategoryItemsListView(RememberedSelection rememberedSelection){
        this.rememberedSelection = rememberedSelection;
        updateCategoryItemsListView();

        String rememberedCategoryItemSelection = rememberedSelection.getCategoryItemSelection();

        updateRememberedListViewHelper(controllerListView, rememberedCategoryItemSelection);

    }

    // helper for getting highlighted remembered selection, also used in CategoryItemsController
    public static void updateRememberedListViewHelper(ListView<String> listView, String rememberedItemSelection){
        listView.getSelectionModel().select(rememberedItemSelection);

        if(listView.getSelectionModel().getSelectedIndex() > listView.getItems().size() * 3 / 4){
            listView.scrollTo(listView.getSelectionModel().getSelectedIndex()+2); //bottom fourth
        } else if (listView.getSelectionModel().getSelectedIndex() < listView.getItems().size() /4){
            listView.scrollTo(listView.getSelectionModel().getSelectedIndex()-2); //top fourth
        } else {
            listView.scrollTo(rememberedItemSelection); // middle half
        }
    }

    /** handle selection from category item list: */
    @FXML
    private void handleCategoryItemClick() {
        this.rememberedSelection.setCategoryItemSelection(controllerListView.getSelectionModel().getSelectedItem());
        //String selectedOption = controllerListView.getSelectionModel().getSelectedItem();
        if (this.rememberedSelection.getCategoryItemSelection() != null) {
            openSelectedCategoryItemDetailsScreen(rememberedSelection);
        }
    }

    /** opens the category item's list of restaurants screen */
    // update to show new scene
    public void openSelectedCategoryItemDetailsScreen(RememberedSelection rememberedSelection) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RestaurantListScreen.fxml"));
            Parent root = loader.load();

            RestaurantListController restaurantListController = loader.getController();
            restaurantListController.displaySelectedCategoryItemDetailsScreen(root, stage, rememberedSelection);
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }

}