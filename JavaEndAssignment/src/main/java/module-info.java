module com.example.javaendassignment {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javaendassignment to javafx.fxml;
    exports com.example.javaendassignment;
  exports com.example.javaendassignment.Controllers;
  opens com.example.javaendassignment.Controllers to javafx.fxml;
  opens com.example.javaendassignment.Models to javafx.base;
}