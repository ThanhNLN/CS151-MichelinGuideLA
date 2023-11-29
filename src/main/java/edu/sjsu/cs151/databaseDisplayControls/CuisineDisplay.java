package edu.sjsu.cs151.databaseDisplayControls;

import edu.sjsu.cs151.JDBC;
import edu.sjsu.cs151.Restaurant;
import javafx.collections.ObservableList;

import java.sql.*;


public class CuisineDisplay implements SelectionDisplay {
    @Override
    public String displaySelection(ObservableList<String> items) throws SQLException{
        // get the options from database and add it to the ObservableList
        // Cuisines


        //create a statement
        Statement statement = JDBC.conn.createStatement();

        //get the cuisines from cuisine list
        ResultSet rs = statement.executeQuery("SELECT * FROM CUISINES");
        while (rs.next()) {
            String cuisine = rs.getString("cuisine");
            //System.out.println("Cuisine: " + cuisine);
            items.add(cuisine);
        }


        return "cuisine";

    }
}
