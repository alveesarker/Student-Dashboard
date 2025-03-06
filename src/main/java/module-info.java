module com.example.studentdashboard {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.studentdashboard to javafx.fxml;
    exports com.example.studentdashboard;
}