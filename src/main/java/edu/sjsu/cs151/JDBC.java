package edu.sjsu.cs151;

import java.sql.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;

public class JDBC {
    public void createDatabase() throws SQLException, IOException, ParseException {
        JSONParser parser = new JSONParser();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Register JDBC driver
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Open a connection
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "password");

        // Execute a query
        Statement statement = conn.createStatement();
        statement.executeUpdate("DROP SCHEMA IF EXISTS student");
        statement.executeUpdate("CREATE SCHEMA student");
        statement.executeUpdate("USE student");
        //statement.executeUpdate()

        String createLocationTable = "create table LOCATIONS "
                + "(LOCATION_ID int NOT NULL UNIQUE PRIMARY KEY AUTO_INCREMENT, "
                + "location varchar(255) UNIQUE"
                + ")";

        statement.executeUpdate("DROP TABLE IF EXISTS LOCATIONS");
        statement.executeUpdate(createLocationTable);

        String createCostTable = "create table COSTS "
                + "(COST_ID int NOT NULL UNIQUE PRIMARY KEY AUTO_INCREMENT, "
                + "cost varchar(255) UNIQUE"
                + ")";

        statement.executeUpdate("DROP TABLE IF EXISTS COSTS");
        statement.executeUpdate(createCostTable);

        String createCuisineTable = "create table CUISINES "
                + "(CUISINE_ID int NOT NULL UNIQUE PRIMARY KEY AUTO_INCREMENT, "
                + "cuisine varchar(255) UNIQUE"
                + ")";

        statement.executeUpdate("DROP TABLE IF EXISTS CUISINES");
        statement.executeUpdate(createCuisineTable);

        String createLADatabase = "CREATE TABLE LADATABASE "
                + "(RESTAURANT_ID int NOT NULL UNIQUE PRIMARY KEY AUTO_INCREMENT, "
                + "name varchar(255), "
                + "url varchar(255), "
                + "LOCATION_ID int, "
                + "COST_ID int, "
                + "CUISINE_ID int, "
                + "address varchar(255), "
                + "FOREIGN KEY (LOCATION_ID) REFERENCES LOCATIONS (LOCATION_ID), "
                + "FOREIGN KEY (COST_ID) REFERENCES COSTS (COST_ID), "
                + "FOREIGN KEY (CUISINE_ID) REFERENCES CUISINES (CUISINE_ID)"
                + ")";
//        statement.executeUpdate("USE student");

        statement.executeUpdate("DROP TABLE IF EXISTS LADATABASE");
        statement.executeUpdate(createLADatabase);

        Object obj = parser.parse(new FileReader("LosAngelesData.json"));

        JSONArray jsonArray = (JSONArray) obj;

        for (Object jsonElement : jsonArray) {
            JSONObject jsonObject = (JSONObject) jsonElement;

            PreparedStatement locationStatement = conn.prepareStatement("insert IGNORE into LOCATIONS(location) values (?)");

            PreparedStatement costStatement = conn.prepareStatement("insert IGNORE into COSTS (cost) values (?)");
            PreparedStatement cuisineStatement = conn.prepareStatement("insert IGNORE into CUISINES (cuisine) values (?)");
            PreparedStatement databaseStatement = conn.prepareStatement("insert into LADATABASE (name, url, LOCATION_ID, COST_ID, CUISINE_ID, address) values (?, ?, ?, ?, ?, ?)");

            String name = (String) jsonObject.get("name");
            // Parameters start with 1
            System.out.println(name);
            databaseStatement.setString(1, name);

            String url = (String) jsonObject.get("url");
            System.out.println(url);
            databaseStatement.setString(2, url);


            String address = (String) jsonObject.get("address");
            System.out.println(address);
            databaseStatement.setString(6, address);


            String location = (String) jsonObject.get("location");
            System.out.println(location);
            locationStatement.setString(1, location);
            locationStatement.execute();

            String cost = Long.toString((long) jsonObject.get("cost"));
            System.out.println(cost);
            costStatement.setString(1, cost);
            costStatement.execute();

            String cuisine = (String) jsonObject.get("cuisine");
            System.out.println(cuisine);
            cuisineStatement.setString(1, cuisine);
            cuisineStatement.execute();


            ResultSet rs = statement.executeQuery("SELECT LOCATION_ID FROM LOCATIONS WHERE location=" + "'" + location + "'");
            if (rs.next()) {
                int locationID = rs.getInt("LOCATION_ID");
                System.out.println("Location ID:" + locationID);
                databaseStatement.setInt(3, locationID);
            }


            rs = statement.executeQuery("SELECT COST_ID FROM COSTS WHERE cost=" + "'" + cost + "'");
            if (rs.next()) {
                int costID = rs.getInt("COST_ID");
                System.out.println("Cost ID:" + costID);
                databaseStatement.setInt(4, costID);
            }

            rs = statement.executeQuery("SELECT CUISINE_ID FROM CUISINES WHERE cuisine=" + "'" + cuisine + "'");
            if (rs.next()) {
                int cuisineID = rs.getInt("CUISINE_ID");
                System.out.println("Cuisine ID:" + cuisineID);
                databaseStatement.setInt(5, cuisineID);
            }
            databaseStatement.execute();

        }
        statement.close();
    }
}