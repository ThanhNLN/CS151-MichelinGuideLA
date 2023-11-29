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
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    public Button locationButton, cuisineButton, priceButton;
    @FXML
    private ListView<Restaurant> listView;
    private Stage primaryStage;

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
        updateListView();
    }

    @FXML
    protected void handleCuisine() {
        selectionDisplay = new CuisineDisplay();
        updateListView();
    }

    @FXML
    protected void handlePrice() {
        selectionDisplay = new PriceDisplay();
        updateListView();
    }

    protected void updateListView(){
        ObservableList<Restaurant> items = FXCollections.observableArrayList();
        selectionDisplay.displaySelection(items);
        listView.setVisible(true);
        listView.setItems(items);
    }

    public void updateListView(Restaurant item){
        updateListView();
        listView.getSelectionModel().select(item);

        if(listView.getSelectionModel().getSelectedIndex() > listView.getItems().size() * 3 / 4){
            listView.scrollTo(listView.getSelectionModel().getSelectedIndex()+2);
        } else if (listView.getSelectionModel().getSelectedIndex() < listView.getItems().size() /4){
            listView.scrollTo(listView.getSelectionModel().getSelectedIndex()-2);
        } else {
            listView.scrollTo(item);
        }


    }


    //handle selection of from category list:
    @FXML
    private void handleListViewClick() {
        Restaurant selectedOption = listView.getSelectionModel().getSelectedItem();
        if (selectedOption != null) {
            openDetailsScreen(selectedOption);
        }
    }

    // TODO: possibly make this more SOLID?
    private void openDetailsScreen(Restaurant selectedOption) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailsScreen.fxml"));
            Parent root = loader.load();

            DetailsController detailsController = loader.getController();
            detailsController.setDetails(selectedOption);
            detailsController.setStage(primaryStage);
            detailsController.setSelectionDisplay(selectionDisplay);

            Scene scene = new Scene(root, primaryStage.getScene().getWidth(), primaryStage.getScene().getHeight());
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            //e.printStackTrace();
        }
    }


}