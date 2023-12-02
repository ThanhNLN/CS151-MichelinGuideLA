module edu.sjsu.cs {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;
    requires json.simple;
    requires java.desktop;

    opens edu.sjsu.cs151 to javafx.fxml;
    exports edu.sjsu.cs151;
    exports edu.sjsu.cs151.databaseDisplayControls;
}