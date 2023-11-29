package edu.sjsu.cs151.databaseDisplayControls;

import edu.sjsu.cs151.Restaurant;
import javafx.collections.ObservableList;

public interface SelectionDisplay {
    void displaySelection(ObservableList<Restaurant> items);
}
