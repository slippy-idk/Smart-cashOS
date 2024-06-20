module cash.sample {
    requires javafx.controls;
    requires javafx.fxml;


    opens cash.sample to javafx.fxml;
    exports cash.sample;
}