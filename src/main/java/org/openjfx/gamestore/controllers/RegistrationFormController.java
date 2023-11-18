package org.openjfx.gamestore.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.openjfx.gamestore.App;
import org.openjfx.gamestore.utils.Utilities;

public class RegistrationFormController {

    @FXML
    private AnchorPane pane;

    @FXML
    private void open_login_form(MouseEvent event) {
        try {
            App.stage.getScene().setRoot(Utilities.loadFXML("login"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void close_app(MouseEvent event) {
        System.exit(0);
    }
}