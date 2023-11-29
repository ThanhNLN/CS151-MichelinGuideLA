package edu.sjsu.cs151;

import edu.sjsu.cs151.databaseDisplayControls.RestaurantDisplay;
import edu.sjsu.cs151.databaseDisplayControls.SelectionDisplay;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class RestaurantController {

    @FXML
    private Label detailsLabel, restaurantName, cuisine, cost, address, url;

    private Stage stage;

    private String category;
    private String selectedFromCategory;

//    private Restaurant restaurant;

    private ObservableList<String> observableList = FXCollections.observableArrayList();

    private String selectedRestaurant; //add to list
    private SelectionDisplay savedSelectionDisplay;

//    private RestaurantDisplay restaurantDisplay;

    public void setSavedSelectionDisplay(SelectionDisplay selectionDisplay){
        this.savedSelectionDisplay = selectionDisplay;
    }

    public void setCategory(String category){
        this.category = category;
    }


    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setDetails(String selectedOption) {
        selectedRestaurant = selectedOption;

        detailsLabel.setText(selectedRestaurant);

        observableList.add(selectedRestaurant);

        //System.out.println(observableList.get(0));

        RestaurantDisplay restaurantDisplay = new RestaurantDisplay();

        try {
            Restaurant restaurant = restaurantDisplay.displaySelection(observableList);
            restaurantName.setText(restaurant.getName());
            cuisine.setText(restaurant.getCuisine());
            cost.setText(restaurant.getCost());
            address.setText(restaurant.getAddress());
            url.setText(restaurant.getUrl());
        } catch (SQLException e) {
            e.printStackTrace();
            //throw new RuntimeException(e);
        }


    }

    public void setSelectedFromCategory(String selectedFromCategory) {
        this.selectedFromCategory = selectedFromCategory;
    }

    @FXML
    private void onBackButtonClick() {

        Controller controller;
        try {
            controller = App.homeScreen(stage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        controller.setSelectionDisplay(savedSelectionDisplay);
        controller.openDetailsScreen(category, selectedFromCategory, selectedRestaurant);

    }


}
