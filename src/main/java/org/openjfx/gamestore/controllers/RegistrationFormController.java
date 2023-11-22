package org.openjfx.gamestore.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.openjfx.gamestore.App;
import org.openjfx.gamestore.models.domain.User;
import org.openjfx.gamestore.models.service.IUserService;
import org.openjfx.gamestore.models.service.UserService;
import org.openjfx.gamestore.utils.AlertUtils;
import org.openjfx.gamestore.utils.Utilities;

public class RegistrationFormController {

    IUserService userService = new UserService();

    @FXML
    private DatePicker dateOfBirthDateP;

    @FXML
    private TextField nameTextField;

    @FXML
    private AnchorPane pane;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField usernameTextField;

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

    @FXML
    void registerClient(MouseEvent event) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateOfBirth = dateOfBirthDateP.getValue();

        String name = nameTextField.getText();
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        if (name.isEmpty() || name.isBlank()
                || username.isEmpty() || username.isBlank()
                || password.isBlank() || password.isEmpty()
                || dateOfBirth == null) {
            AlertUtils.showAlertError("You must fill out all fields.");
        } else {
            if (!userService.usernameExists(username)) {
                if (userService.save(new User(name, username, password, dateOfBirth.format(dateFormat)))) {
                    AlertUtils.showAlertInfo("User created successfully");
                }
            } else {
                AlertUtils.showAlertError("Username exists, please choose a different username.");
            }
        }
    }
}
