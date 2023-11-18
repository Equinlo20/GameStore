module org.openjfx.gamestore {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens org.openjfx.gamestore to javafx.fxml;
    opens org.openjfx.gamestore.controllers to javafx.fxml;
    exports org.openjfx.gamestore;
}
