package edu.sjsu.cs151;

import edu.sjsu.cs151.databaseDisplayControls.CuisineDisplay;
import edu.sjsu.cs151.databaseDisplayControls.LocationDisplay;
import edu.sjsu.cs151.databaseDisplayControls.PriceDisplay;
import edu.sjsu.cs151.databaseDisplayControls.SelectionDisplay;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Controller {
//    @FXML
//    private Button locationButton, cuisineButton, priceButton;
    @FXML
    private ListView<String> controllerListView;
    private Stage primaryStage;
    private String category = "";
    private SelectionDisplay selectionDisplay;

    public void setPrimaryStage(Stage primaryStage){
        this.primaryStage = primaryStage;
    }

    public void setSelectionDisplay(SelectionDisplay selectionDisplay) {
        this.selectionDisplay = selectionDisplay;
    }

//    public void setCuisineButton(Button cuisineButton) {
//        this.cuisineButton = cuisineButton;
//    }

//    public void setLocationButton(Button locationButton) {
//        this.locationButton = locationButton;
//    }

//    public void setPriceButton(Button priceButton) {
//        this.priceButton = priceButton;
//    }

//    public Button getCuisineButton() {
//        return cuisineButton;
//    }

//    public Button getLocationButton() {
//        return locationButton;
//    }

//    public Button getPriceButton() {
//        return priceButton;
//    }

//    public SelectionDisplay getSelectionDisplay() {
//        return selectionDisplay;
//    }

    // handle category selections:
    // Location, Cuisine, Price
    @FXML
    protected void handleLocation() {
        selectionDisplay = new LocationDisplay();
//        category = "location";
        updateListView();
    }

    @FXML
    protected void handleCuisine() {
        selectionDisplay = new CuisineDisplay();
//        category = "cuisine";
        updateListView();
    }

    @FXML
    protected void handlePrice() {
        selectionDisplay = new PriceDisplay();
//        category = "cost";
        updateListView();
    }

    protected void updateListView(){
        ObservableList<String> items = FXCollections.observableArrayList();

        try {
            category = selectionDisplay.displaySelection(items);

        } catch (SQLException e) {
            //e.printStackTrace();
        }
        controllerListView.setVisible(true);

            controllerListView.setItems(items);
        controllerListView.setItems(items);
    }

    public void updateListView(String category, String item){
        this.category = category;
        updateListView();
        controllerListView.getSelectionModel().select(item);

        if(controllerListView.getSelectionModel().getSelectedIndex() > controllerListView.getItems().size() * 3 / 4){
            controllerListView.scrollTo(controllerListView.getSelectionModel().getSelectedIndex()+2);
        } else if (controllerListView.getSelectionModel().getSelectedIndex() < controllerListView.getItems().size() /4){
            controllerListView.scrollTo(controllerListView.getSelectionModel().getSelectedIndex()-2);
        } else {
            controllerListView.scrollTo(item);
        }


    }


    //handle selection of from category list:
    @FXML
    private void handleListViewClick() {
        String selectedOption = controllerListView.getSelectionModel().getSelectedItem();
        if (selectedOption != null) {
//            if (category.equals("cost"))
//                selectedOption = Integer.toString(selectedOption.length());
            openDetailsScreen(category, selectedOption, null);
        }
    }

    // TODO: possibly make this more SOLID?
    public void openDetailsScreen(String category, String selectedOption, String selectedRestaurant) {
        try {
            this.category = category;

            FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailsScreen.fxml"));
            Parent root = loader.load();

            DetailsController detailsController = loader.getController();
            detailsController.setDetails(category, selectedOption);
            detailsController.setStage(primaryStage);
            detailsController.setSavedSelectionDisplay(selectionDisplay);
            detailsController.updateRestaurantListView(selectedRestaurant);

            Scene scene = new Scene(root, primaryStage.getScene().getWidth(), primaryStage.getScene().getHeight());
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            //e.printStackTrace();
        }
    }


}