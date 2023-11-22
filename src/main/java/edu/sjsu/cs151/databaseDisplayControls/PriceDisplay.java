package edu.sjsu.cs151.databaseDisplayControls;

import javafx.collections.ObservableList;

public class PriceDisplay implements SelectionDisplay {
    @Override
    public void displaySelection(ObservableList<String> items) {
        /*
         * TODO: get the options from database and add it to the ObservableList
         * Prices: $, $$, $$$, ??
         */

        // temporary tester for the list display
        for(int i = 4 ; i < 10; i++){
            items.add("Price "+ i);
        }
    }
}
