package edu.sjsu.cs151.databaseDisplayControls;

import javafx.collections.ObservableList;

public class LocationDisplay implements SelectionDisplay {
    @Override
    public void displaySelection(ObservableList<String> items) {
        // TODO: get the options from database and add it to the ObservableList
        // Locations: (around the LA area)

        for(int i = 1 ; i < 100; i++){
            items.add("Location "+ i);
        }
    }
}
