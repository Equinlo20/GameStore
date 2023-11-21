/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.openjfx.gamestore.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.openjfx.gamestore.models.service.IUserService;
import org.openjfx.gamestore.models.service.UserService;

public class WelcomeController implements Initializable{
    
    private final IUserService userService = new UserService();

    @FXML
    private Label welcomeUserLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.welcomeUserLabel.setText("Welcome " + userService.getUserInSession().getUsername());
    }
}
