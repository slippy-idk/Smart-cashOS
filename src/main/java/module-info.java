module cash.sample {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens cash.sample to javafx.fxml;
    exports cash.sample;
}