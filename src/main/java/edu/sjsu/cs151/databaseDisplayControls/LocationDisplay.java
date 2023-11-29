package edu.sjsu.cs151.databaseDisplayControls;

import edu.sjsu.cs151.JDBC;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LocationDisplay implements SelectionDisplay {
    @Override
    public String displaySelection(ObservableList<String> items) throws SQLException {
        // get the options from database and add it to the ObservableList
        // Locations: (around the LA area)

        //create a statement
        Statement statement = JDBC.conn.createStatement();

        //get the location from location list
        ResultSet rs = statement.executeQuery("SELECT * FROM LOCATIONS");
        while (rs.next()) {
            String location = rs.getString("location");
            //System.out.println("Location: " + location);
            items.add(location);
        }

        return "location";

    }
}
