package edu.sjsu.cs151;

import edu.sjsu.cs151.databaseDisplayControls.RestaurantDisplay;
import javafx.application.HostServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.awt.Desktop;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.Hyperlink;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

public class RestaurantDetailsController {

    @FXML
    private Label restaurantNameLabel, cuisine, cost, address;
    @FXML
    private Hyperlink resHyperlink;
    private String urlString;
    private HostServices hostServices;
    private RememberedSelection rememberedSelection = new RememberedSelection();
    private Stage stage;

    /** set the restaurant details to display */
    public void setRestaurantDetails(RememberedSelection rememberedSelection) {
        this.rememberedSelection = rememberedSelection;

        restaurantNameLabel.setText(rememberedSelection.getRestaurantSelection());

        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.add(rememberedSelection.getRestaurantSelection()); // to pass in which restaurant details to display

        RestaurantDisplay restaurantDisplay = new RestaurantDisplay(); // doesn't inherit from SelectionDisplay :( because we want to return a Restaurant instead of a String
        // if we want to use interface and return string, we could use string parsing? (possibly not use Restaurant class, or use toString() option)

        try {
            Restaurant restaurant = restaurantDisplay.displaySelection(observableList);
            //restaurantName.setText(restaurant.getName()); //name is already at the top (large font)
            cuisine.setText(restaurant.getCuisine());
            cost.setText(restaurant.getCost());
            address.setText(restaurant.getAddress());
            //url.setText(restaurant.getUrl()); //probably delete this?
            urlString = restaurant.getUrl();
            setResHyperlink(urlString);
        } catch (SQLException e) {
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
    private void updateHyperlink(String text, String url) {
        resHyperlink.setText(text);
        resHyperlink.setOnAction(e -> handleHyperlinkClick(url));
    }

    @FXML
    private void handleHyperlinkClick() {
        //System.out.println("in handle hyperlink");
        Desktop desktop = Desktop.getDesktop();
        if (desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(new URI(urlString));
            } catch (IOException | URISyntaxException e) {
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

    /** handles back button to take to previous page containing list of restaurants in a category item */
    @FXML
    private void onBackButtonClick() {
        HomeScreenController homeScreenController = new HomeScreenController();
        homeScreenController.setStage(stage);
        homeScreenController.openSelectedCategoryItemDetailsScreen(rememberedSelection);
    }

    /** displays the restaurant details */
    public void displayRestaurantDetailsScreen(Parent root, Stage stage, RememberedSelection rememberedSelection) {
        this.stage = stage;
        setRestaurantDetails(rememberedSelection);

        Scene scene = new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(scene);
        stage.show();
    }
}
