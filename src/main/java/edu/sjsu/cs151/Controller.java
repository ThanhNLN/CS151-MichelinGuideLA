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

public class Controller {
    @FXML
    private ListView<String> listView;
    private Stage primaryStage;

    private SelectionDisplay selectionDisplay;

    public void setPrimaryStage(Stage primaryStage){
        this.primaryStage = primaryStage;
    }

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
        ObservableList<String> items = FXCollections.observableArrayList();
        selectionDisplay.displaySelection(items);
        listView.setVisible(true);
        listView.setItems(items);
    }


    //handle selection of from category list:
    @FXML
    private void handleListViewClick() {
        String selectedOption = listView.getSelectionModel().getSelectedItem();
        if (selectedOption != null) {
            openDetailsScreen(selectedOption);
        }
    }

    // TODO: possibly make this more SOLID?
    private void openDetailsScreen(String selectedOption) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailsScreen.fxml"));
            Parent root = loader.load();

            DetailsController detailsController = loader.getController();
            detailsController.setDetails(selectedOption);
            detailsController.setStage(primaryStage);

            Scene scene = new Scene(root, primaryStage.getScene().getWidth(), primaryStage.getScene().getHeight());
            //primaryStage.setTitle();
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}