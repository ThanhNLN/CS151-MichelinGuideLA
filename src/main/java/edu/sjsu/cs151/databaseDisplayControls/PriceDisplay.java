package edu.sjsu.cs151.databaseDisplayControls;

import edu.sjsu.cs151.JDBC;
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
//            String cost = rs.getString("cost");
            String cost = rs.getString("cost");
//            if (rs.getString("cost").equals("1"))
//                cost = "$";
//            else if (rs.getString("cost").equals("2"))
//                cost = "$$";
//            else if (rs.getString("cost").equals("3"))
//                cost = "$$$";
//            else
//                cost = "$$$$";
            //System.out.println("Cost: " + cost);
            items.add(cost);
        }

        return "cost";
    }
}
