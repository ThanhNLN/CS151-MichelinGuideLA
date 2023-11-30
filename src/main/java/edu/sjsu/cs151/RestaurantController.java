package edu.sjsu.cs151;

import edu.sjsu.cs151.databaseDisplayControls.RestaurantDisplay;
import edu.sjsu.cs151.databaseDisplayControls.SelectionDisplay;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import java.awt.Desktop;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.Hyperlink;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

public class RestaurantController {

    @FXML
    private Label detailsLabel, restaurantName, cuisine, cost, address, url;
    @FXML
    private Hyperlink resHyperlink;
    private String urlString;
    private HostServices hostServices;
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
            urlString = restaurant.getUrl();
            setResHyperlink(restaurant.getUrl());
        } catch (SQLException e) {
            e.printStackTrace();
            //throw new RuntimeException(e);
        }
    }
    public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices;
    }
    private void setResHyperlink(String link) {
        // Set default text and link
        updateHyperlink("Visit Restaurant Page for more detail", link);
    }
    // Method to update hyperlink text and link
    // Method to update hyperlink text and link
    private void updateHyperlink(String text, String url) {
        resHyperlink.setText(text);
        resHyperlink.setOnAction(e -> handleHyperlinkClick(url));
    }
    @FXML
    private void handleHyperlinkClick() {
        System.out.println("in handle hyperlink");
        Desktop desktop = Desktop.getDesktop();
        if (desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(new URI(urlString));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
                //throw new RuntimeException(e);
            }
        }
//        if (hostServices() != null) {
//            hostServices.showDocument(resHyperlink.getText());
//        }
    }
    private void handleHyperlinkClick(String url) {
        if (hostServices != null) {
            hostServices.showDocument(url);
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
