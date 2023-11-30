package edu.sjsu.cs151;

import java.sql.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;

public class JDBC {

    // instance variables
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";
    private static final String PASSWORD = "password";
    public static Connection conn = null;
    private Statement statement = null;
    private PreparedStatement prepareStatement = null;
    private ResultSet rs = null;

    // default constructor
    JDBC(){}

    // getters
    public Connection getConn() {
        return conn;
    }

    public Statement getStatement() {
        return statement;
    }

    public PreparedStatement getPrepareStatement() {
        return prepareStatement;
    }

    public ResultSet getRs() {
        return rs;
    }

    // setters
    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public void setPrepareStatement(PreparedStatement prepareStatement) {
        this.prepareStatement = prepareStatement;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    public void connect() throws SQLException {
        // Method connects to database
        // Register JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Open a connection
        conn = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
    }

    public void createDatabase() throws SQLException, IOException, ParseException {
        // Method creates databases and tables
        connect();

        // Execute a query
//        Statement statement = conn.createStatement();
        this.statement = conn.createStatement();
        this.statement.executeUpdate("DROP SCHEMA IF EXISTS student");
        this.statement.executeUpdate("CREATE SCHEMA student");
        this.statement.executeUpdate("USE student");

        String createLocationTable = "create table LOCATIONS "
                + "(LOCATION_ID int NOT NULL UNIQUE PRIMARY KEY AUTO_INCREMENT, "
                + "location varchar(255) UNIQUE"
                + ")";

        this.statement.executeUpdate("DROP TABLE IF EXISTS LOCATIONS");
        this.statement.executeUpdate(createLocationTable);

        String createCostTable = "create table COSTS "
                + "(COST_ID int NOT NULL UNIQUE PRIMARY KEY AUTO_INCREMENT, "
                + "cost varchar(255) UNIQUE"
                + ")";

        this.statement.executeUpdate("DROP TABLE IF EXISTS COSTS");
        this.statement.executeUpdate(createCostTable);

        String createCuisineTable = "create table CUISINES "
                + "(CUISINE_ID int NOT NULL UNIQUE PRIMARY KEY AUTO_INCREMENT, "
                + "cuisine varchar(255) UNIQUE"
                + ")";

        this.statement.executeUpdate("DROP TABLE IF EXISTS CUISINES");
        this.statement.executeUpdate(createCuisineTable);

        String createLADatabase = "CREATE TABLE LADATABASE "
                + "(RESTAURANT_ID int NOT NULL UNIQUE PRIMARY KEY AUTO_INCREMENT, "
                + "name varchar(255) UNIQUE, "
                + "url varchar(255), "
                + "LOCATION_ID int, "
                + "COST_ID int, "
                + "CUISINE_ID int, "
                + "address varchar(255), "
                + "FOREIGN KEY (LOCATION_ID) REFERENCES LOCATIONS (LOCATION_ID), "
                + "FOREIGN KEY (COST_ID) REFERENCES COSTS (COST_ID), "
                + "FOREIGN KEY (CUISINE_ID) REFERENCES CUISINES (CUISINE_ID)"
                + ")";

        this.statement.executeUpdate("DROP TABLE IF EXISTS LADATABASE");
        this.statement.executeUpdate(createLADatabase);
    }

    public void jsonToDatabase() throws IOException, SQLException, ParseException {
        // Create method to parse JSON file into database
        JSONParser parser = new JSONParser();

        Object obj = parser.parse(new FileReader("LosAngelesData.json"));

        // Casts obj to JSONArray
        JSONArray jsonArray = (JSONArray) obj;

//        ResultSet rs = null;

        // Iterates jsonArray to insert data into database
        for (Object jsonElement : jsonArray) {
            JSONObject jsonObject = (JSONObject) jsonElement;

            // Inserts locations into locations table
            //PreparedStatement locationStatement = conn.prepareStatement("insert IGNORE into LOCATIONS(location) values (?)");
            this.prepareStatement = conn.prepareStatement("insert IGNORE into LOCATIONS(location) values (?)");

            String location = (String) jsonObject.get("location");
            this.prepareStatement.setString(1, location);
            this.prepareStatement.execute();

            // Inserts costs into costs table
            this.prepareStatement = conn.prepareStatement("insert IGNORE into COSTS (cost) values (?)");
//            String cost = Long.toString((long) jsonObject.get("cost"));
            String cost = (String) jsonObject.get("cost");
            this.prepareStatement.setString(1, cost);
            this.prepareStatement.execute();

            // Inserts cuisines into cuisines table
            this.prepareStatement = conn.prepareStatement("insert IGNORE into CUISINES (cuisine) values (?)");
            String cuisine = (String) jsonObject.get("cuisine");
            this.prepareStatement.setString(1, cuisine);
            this.prepareStatement.execute();

            // Inserts data into Los Angeles database
            this.prepareStatement = conn.prepareStatement("insert IGNORE into LADATABASE "
                    + "(name, url, LOCATION_ID, COST_ID, CUISINE_ID, address) values (?, ?, ?, ?, ?, ?)");

            String name = (String) jsonObject.get("name");
            this.prepareStatement.setString(1, name);

            String url = (String) jsonObject.get("url");
            this.prepareStatement.setString(2, url);

            this.rs = this.statement.executeQuery("SELECT LOCATION_ID FROM LOCATIONS WHERE location=" + "'" + location + "'");
            if (this.rs.next()) {
                int locationID = this.rs.getInt("LOCATION_ID");
                this.prepareStatement.setInt(3, locationID);
            }

            this.rs = this.statement.executeQuery("SELECT COST_ID FROM COSTS WHERE cost=" + "'" + cost + "'");
            if (this.rs.next()) {
                int costID = this.rs.getInt("COST_ID");
                this.prepareStatement.setInt(4, costID);
            }

            this.rs = this.statement.executeQuery("SELECT CUISINE_ID FROM CUISINES WHERE cuisine=" + "'" + cuisine + "'");
            if (this.rs.next()) {
                int cuisineID = this.rs.getInt("CUISINE_ID");
                this.prepareStatement.setInt(5, cuisineID);
            }

            String address = (String) jsonObject.get("address");
            this.prepareStatement.setString(6, address);

            this.prepareStatement.execute();
        }
    }
    public void disconnect() throws SQLException {
        this.rs.close();
        this.statement.close();
    }
}