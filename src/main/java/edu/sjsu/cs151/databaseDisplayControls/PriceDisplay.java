package edu.sjsu.cs151.databaseDisplayControls;

import edu.sjsu.cs151.JDBC;
import edu.sjsu.cs151.Restaurant;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PriceDisplay implements SelectionDisplay {
    @Override
    public String displaySelection(ObservableList<String> items) throws SQLException {
        /*
         * get the options from database and add it to the ObservableList
         * TODO: format Prices: $, $$, $$$, ??
         */

        //create a statement
        Statement statement = JDBC.conn.createStatement();

        //get the price from price list
        ResultSet rs = statement.executeQuery("SELECT * FROM COSTS");
        while (rs.next()) {
            String cost = rs.getString("cost");
            //System.out.println("Cost: " + cost);
            items.add(cost);
        }

        return "cost";
    }
}
