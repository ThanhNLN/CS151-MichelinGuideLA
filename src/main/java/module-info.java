module edu.sjsu.cs {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens edu.sjsu.cs151 to javafx.fxml;
    exports edu.sjsu.cs151;
}