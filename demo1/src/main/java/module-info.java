module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.demo1 to javafx.fxml;
    exports com.example.demo1;
    exports com.example.demo1.l1;
    opens com.example.demo1.l1 to javafx.fxml;
    exports com.example.demo1.l2;
    opens com.example.demo1.l2 to javafx.fxml;
    exports com.example.demo1.l3;
    opens com.example.demo1.l3 to javafx.fxml;
    exports com.example.demo1.l4;
    opens com.example.demo1.l4 to javafx.fxml;
}