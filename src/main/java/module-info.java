module com.example.yehiaticketsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.yehiaticketsystem to javafx.fxml;
    exports com.example.yehiaticketsystem;
}