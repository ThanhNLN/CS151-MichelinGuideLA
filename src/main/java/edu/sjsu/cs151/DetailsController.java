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

public class DetailsController {

    @FXML
    private ListView<String> detailsListView;
    @FXML
    private Label detailsLabel;

    private String category;


    private Stage stage;

    private SelectionDisplay savedSelectionDisplay;

    public void setStage(Stage primaryStage) {
        this.stage = primaryStage;
    }

    public void setSavedSelectionDisplay(SelectionDisplay selectionDisplay){
        this.savedSelectionDisplay = selectionDisplay;
    }

    public void setDetails(String category, String details){
//        if (category.equals("cost")) {
//            String price = "";
//            for( int i = 0; i < Integer.valueOf(details); i++)
//                price += "$";
//            detailsLabel.setText(price);
//            System.out.println("price = " + price);
//        }
//        else{
//            detailsLabel.setText(details);
//            System.out.println("detailsLabel.getText() = " + detailsLabel.getText());
//        }
        detailsLabel.setText(details);
        this.category = category;

        SelectionDisplay selectionDisplay = new DetailsDisplay();

        ObservableList<String> items = FXCollections.observableArrayList();
        items.add(category);
        items.add(details);

        try {
            selectionDisplay.displaySelection(items);
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        detailsListView.setItems(items);
    }

    @FXML
    private void handleListViewClick() {
        String selectedOption = detailsListView.getSelectionModel().getSelectedItem();
        if (selectedOption != null){
            openRestaurantDetailsScreen(selectedOption);
        }

    }

    @FXML
    private void onBackButtonClick() {
        // TODO: fix it so it highlights the previous button selection on the old screen?
        try {

            setDetails(category, detailsLabel.getText());

            Controller controller = App.homeScreen(stage);
            controller.setSelectionDisplay(savedSelectionDisplay);

            App.homeScreenRememberSelection(stage, savedSelectionDisplay, category, detailsLabel.getText());

        } catch (IOException e){
            //e.printStackTrace();
        }


    }

    public void updateRestaurantListView(String item){
        detailsListView.getSelectionModel().select(item);
        //System.out.println(item);

        if(detailsListView.getSelectionModel().getSelectedIndex() > detailsListView.getItems().size() * 3 / 4){
            detailsListView.scrollTo(detailsListView.getSelectionModel().getSelectedIndex()+2);
        } else if (detailsListView.getSelectionModel().getSelectedIndex() < detailsListView.getItems().size() /4){
            detailsListView.scrollTo(detailsListView.getSelectionModel().getSelectedIndex()-2);
        } else {
            detailsListView.scrollTo(item);
        }

    }

    private void openRestaurantDetailsScreen(String selectedOption){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RestaurantScreen.fxml"));
            Parent root = loader.load();

            RestaurantController restaurantController = loader.getController();
            restaurantController.setDetails(selectedOption);
            restaurantController.setStage(stage);
            restaurantController.setCategory(category);
//            if (category.equals("cost")) {
//                String price = "";
//                for( int i = 0; i < detailsLabel.getText().length(); i++)
//                    price += "$";
//                restaurantController.setSelectedFromCategory(price);
//                System.out.println("price = " + price);
//            }
//            else{
//                restaurantController.setSelectedFromCategory(detailsLabel.getText());
//                System.out.println("detailsLabel.getText() = " + detailsLabel.getText());
//            }

            restaurantController.setSelectedFromCategory(detailsLabel.getText());
            restaurantController.setSavedSelectionDisplay(savedSelectionDisplay);


            Scene scene = new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight());
            stage.setScene(scene);
            stage.show();

        } catch (IOException e){
            //e.printStackTrace();
        }
    }


}
