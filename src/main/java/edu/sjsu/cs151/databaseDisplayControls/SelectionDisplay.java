package edu.sjsu.cs151.databaseDisplayControls;

import edu.sjsu.cs151.Restaurant;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface SelectionDisplay {
    String displaySelection(ObservableList<String> items) throws SQLException;
}
