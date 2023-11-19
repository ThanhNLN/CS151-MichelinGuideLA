package edu.sjsu.cs151;

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
//    @FXML
//    private Label appNameTitleText, instructionText;
    @FXML
    private ListView<String> listView;

    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage){
        this.primaryStage = primaryStage;
    }

    @FXML
    protected void handleLocation() {
        listView.setVisible(true);
        updateListView("Location");
    }

    @FXML
    protected void handleCuisine() {
        listView.setVisible(true);
        updateListView("Cuisine");
    }

    @FXML
    protected void handlePrice() {
        listView.setVisible(true);
        updateListView("Price");
    }

    @FXML
    private void handleListViewClick() {
        String selectedOption = listView.getSelectionModel().getSelectedItem();
        if (selectedOption != null) {
            openDetailsScreen(selectedOption);
        }
    }

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

    protected void updateListView(String message){
        ObservableList<String> items = FXCollections.observableArrayList(message);
        items.setAll();


        //add to items
        switch (message) {
            case "Location" -> displayLocations(items);
            case "Cuisine" -> displayCuisine(items);
            case "Price" -> displayPrice(items);
        }

        listView.setItems(items);
    }

    protected void displayLocations(ObservableList<String> items){
        for(int i = 1 ; i < 100; i++){
            items.add("Location "+ i);
        }
    }

    protected void displayCuisine(ObservableList<String> items){
        for(int i = 3 ; i < 100; i++){
            items.add("Cuisine "+ i);
        }
    }

    protected void displayPrice(ObservableList<String> items){
        for(int i = 4 ; i < 100; i++){
            items.add("Price "+ i);
        }
    }
}