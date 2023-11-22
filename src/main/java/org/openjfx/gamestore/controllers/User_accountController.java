/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.openjfx.gamestore.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.openjfx.gamestore.models.domain.User;
import org.openjfx.gamestore.models.service.IUserService;
import org.openjfx.gamestore.models.service.UserService;
import org.openjfx.gamestore.utils.AlertUtils;

public class User_accountController implements Initializable {

    private final IUserService userService = new UserService();

    private User user;

    @FXML
    private Button btn_save;

    @FXML
    private DatePicker dateBirthTxtField;

    @FXML
    private TextField emailTxtField;

    @FXML
    private TextField nameTxtField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField phoneTxtField;

    @FXML
    private TextField usernameTxtField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.user = userService.getUserInSession();
        init();
    }

    private void init() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        dateBirthTxtField.setValue(LocalDate.parse(this.user.getDateOfBirth(), dateFormat));

        emailTxtField.setText(user.getEmail() == null? "" : user.getEmail());
        nameTxtField.setText(user.getName());
        passwordField.setText(user.getPassword());
        phoneTxtField.setText(user.getPhone() == null? "" : user.getPhone());
        usernameTxtField.setText(user.getUsername());

        disableFields(true);
    }

    private void disableFields(boolean enabled) {
        dateBirthTxtField.setDisable(enabled);
        emailTxtField.setDisable(enabled);
        nameTxtField.setDisable(enabled);
        passwordField.setDisable(enabled);
        phoneTxtField.setDisable(enabled);
        usernameTxtField.setDisable(enabled);
        btn_save.setDisable(enabled);
    }

    @FXML
    void enableEditing(MouseEvent event) {
        if (dateBirthTxtField.isDisabled()) {
            disableFields(false);
        } else {
            disableFields(true);
        }

    }

    @FXML
    void saveChanges(MouseEvent event) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String name = nameTxtField.getText();
        String username = usernameTxtField.getText();
        String password = passwordField.getText();
        LocalDate dateBirth = dateBirthTxtField.getValue();
        String phone = phoneTxtField.getText();
        String email = emailTxtField.getText();
        if (name.isEmpty() || name.isBlank()
                || username.isEmpty() || username.isBlank()
                || password.isBlank() || password.isEmpty()
                || phone.isBlank() || phone.isEmpty()
                || email.isBlank() || email.isEmpty()
                || dateBirth == null) {
            AlertUtils.showAlertError("You must fill out all fields.");
        } else {
            boolean usernameIsValid = username.equals(this.user.getUsername())? true : !userService.usernameExists(username);
            if (usernameIsValid) {
                if (AlertUtils.getAndShowAlertConfirm("Do you want to make the changes?")) {
                    User newUser = new User(name, username, password, dateBirth.format(dateFormat), phone, email);
                    if (userService.update(this.user, newUser)) {
                        this.user = newUser;
                        AlertUtils.showAlertInfo("User updated successfully");
                    }
                }
                init();

            } else {
                AlertUtils.showAlertError("Username already exists, please choose a different username.");
            }
        }

    }

}
