package org.openjfx.gamestore.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.openjfx.gamestore.App;
import org.openjfx.gamestore.models.service.IUserService;
import org.openjfx.gamestore.models.service.UserService;
import org.openjfx.gamestore.utils.AlertUtils;
import org.openjfx.gamestore.utils.Utilities;

public class LoginController implements Initializable {

    IUserService userService = new UserService();

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private Label nameStore;
    @FXML
    private Pane content_area;
    @FXML
    private AnchorPane parent;

    @FXML
    private TextField passwordTxtField;

    @FXML
    private TextField usernameTxtField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        makeStageDrageable();
        Utilities.getEffectTransition(content_area, 1);
    }

    public void makeStageDrageable() {
        parent.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        parent.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                App.stage.setX(event.getScreenX() - xOffset);
                App.stage.setY(event.getScreenY() - yOffset);
                App.stage.setOpacity(0.7f);
            }
        });

        parent.setOnDragDone((e) -> {
            App.stage.setOpacity(1.0f);
        });

        parent.setOnMouseReleased((e) -> {
            App.stage.setOpacity(1.0f);
        });
    }

    @FXML
    private void open_register_form(MouseEvent event) throws IOException {
        Parent fxml = Utilities.loadFXML("registrationForm");
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);
        Utilities.getEffectTransition(content_area, 1);
    }

    @FXML
    private void close_app(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void singinAction(ActionEvent event) throws IOException {
        String username = usernameTxtField.getText();
        String passw = passwordTxtField.getText();
        if (username.isBlank() || username.isEmpty() && passw.isBlank() || passw.isEmpty()) {
            AlertUtils.showAlertError("You must fill out all fields.");
        } else if (userService.getByUsername(username) != null) {
            if (userService.getByUsername(username).getPassword().equals(passw)) {
                AlertUtils.showAlertInfo("User successfully logged in");
                Parent fxml = Utilities.loadFXML("dashboard_user");
                Utilities.changeScene(fxml, 1100, 700);
            }else{
                AlertUtils.showAlertError("Incorrect username or password");
            }
        } else {
            AlertUtils.showAlertError("User does not exist");
        }

    }
}
