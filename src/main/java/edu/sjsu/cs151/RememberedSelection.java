package edu.sjsu.cs151;

import edu.sjsu.cs151.databaseDisplayControls.SelectionDisplay;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class RememberedSelection {

    private SelectionDisplay categorySelectionDisplay;
    private String categoryButton;
    private String categoryItemSelection;
    private String restaurantSelection;

    public RememberedSelection(){
        this.categorySelectionDisplay = new SelectionDisplay() {
            @Override
            public String displaySelection(ObservableList<String> items) {
                return "";
            }
        };
        this.categoryButton = "";
        this.categoryItemSelection = "";
        this.restaurantSelection = "";
    }

    public SelectionDisplay getCategorySelectionDisplay() {
        return categorySelectionDisplay;
    }

    public void setCategorySelectionDisplay(SelectionDisplay categorySelectionDisplay) {
        this.categorySelectionDisplay = categorySelectionDisplay;
    }

    public String getCategoryButton() {
        return categoryButton;
    }

    public void setCategoryButton(String categoryButton) {
        this.categoryButton = categoryButton;
    }

    public String getCategoryItemSelection() {
        return categoryItemSelection;
    }

    public void setCategoryItemSelection(String categoryItemSelection) {
        this.categoryItemSelection = categoryItemSelection;
    }

    public String getRestaurantSelection() {
        return restaurantSelection;
    }

    public void setRestaurantSelection(String restaurantSelection) {
        this.restaurantSelection = restaurantSelection;
    }
}
