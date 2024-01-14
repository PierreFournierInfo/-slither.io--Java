module com.poo.slither {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.poo.slither to javafx.fxml;
    exports com.poo.slither;

    opens com.poo.slither.network to javafx.fxml;
    exports com.poo.slither.network;
}