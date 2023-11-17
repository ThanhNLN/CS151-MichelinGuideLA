module edu.sjsu.cs.assignment3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.sjsu.cs151 to javafx.fxml;
    exports edu.sjsu.cs151;
}