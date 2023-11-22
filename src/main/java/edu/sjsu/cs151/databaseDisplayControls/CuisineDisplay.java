package edu.sjsu.cs151.databaseDisplayControls;

import javafx.collections.ObservableList;

public class CuisineDisplay implements SelectionDisplay {
    @Override
    public void displaySelection(ObservableList<String> items) {
        // TODO: get the options from database and add it to the ObservableList
        // Cuisines

        // temporary tester for list display
        for(int i = 3 ; i < 100; i++){
            items.add("Cuisine "+ i);
        }
    }
}
