module com.example.mrmonkey1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.example.mrmonkey1 to javafx.fxml;
    exports com.example.mrmonkey1;
}