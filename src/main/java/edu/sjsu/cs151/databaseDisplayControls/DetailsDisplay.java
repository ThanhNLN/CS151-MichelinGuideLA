package edu.sjsu.cs151.databaseDisplayControls;

import edu.sjsu.cs151.JDBC;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DetailsDisplay implements SelectionDisplay{
    @Override
    public String displaySelection(ObservableList<String> items) throws SQLException {
        // get the options from database and add it to the ObservableList
        // Display the appropriate details

        // String original options:
        // "location"
        // "cuisine"
        // "cost"
        String columnName = items.get(0);
        String columnID = columnName.toUpperCase() + "_ID";
        String table = columnName + "s";
        String selection = items.get(1);

        items.setAll(); //reset

        //create a statement
        Statement statement = JDBC.conn.createStatement();


        //get the cuisines from cuisine list
        ResultSet rs = statement.executeQuery("SELECT " + columnID + " FROM " + table + " WHERE " + columnName + "='" + selection + "'");

        //get the appropriate ID
        int id = 0;
        if (rs.next()) {
            id = rs.getInt(columnID);
        }

        //get all restaurants with the appropriate id, add to ObservableList
        rs = statement.executeQuery("SELECT name FROM ladatabase WHERE " + columnID + "=" + id);
        while (rs.next()) {
            items.add(rs.getString("name"));
        }

        return "";
    }

}
