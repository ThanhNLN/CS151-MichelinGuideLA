package edu.sjsu.cs151.databaseDisplayControls;

import javafx.collections.ObservableList;

public class DetailsDisplay implements SelectionDisplay{
    @Override
    public void displaySelection(ObservableList<String> items) {
        // TODO: get the options from database and add it to the ObservableList
        // Display the appropriate details
        // may need to make this an interface, and then create each detail display for cuisine, location, price?
        for(int i = 0; i < 10 ; i++){
            items.add("Details " + i);
        }
    }
}
