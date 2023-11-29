package edu.sjsu.cs151.databaseDisplayControls;

import edu.sjsu.cs151.JDBC;
import edu.sjsu.cs151.Restaurant;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RestaurantDisplay{

    public Restaurant displaySelection(ObservableList<String> items) throws SQLException {

        String restaurantName = items.get(0);

        Restaurant restaurant = null;

        //create a statement
        Statement statement = JDBC.conn.createStatement();


        //TODO: fix ' problem
        ResultSet rs = statement.executeQuery("SELECT ladatabase.name, ladatabase.url, ladatabase.address, locations.location, costs.cost, cuisines.cuisine FROM (ladatabase " +
                "JOIN locations ON ladatabase.LOCATION_ID=locations.LOCATION_ID " +
                "JOIN costs ON ladatabase.COST_ID=costs.COST_ID " +
                "JOIN cuisines ON ladatabase.CUISINE_ID=cuisines.CUISINE_ID) " +
                "WHERE name='" + restaurantName + "'");
        
        if (rs.next()){
//            restaurant = new Restaurant(rs.getString("name"),
//                    rs.getString("url"), Integer.toString(rs.getInt("location")), Integer.toString(rs.getInt("COST_ID")),
//                    Integer.toString(rs.getInt("CUISINE_ID")), rs.getString("address"));

            restaurant = new Restaurant(rs.getString("name"),
                    rs.getString("url"), rs.getString("location"), rs.getString("cost"),
                    rs.getString("cuisine"), rs.getString("address"));
        }

//        System.out.println("bestie " + restaurant.toString());

        return restaurant;
    }
}
