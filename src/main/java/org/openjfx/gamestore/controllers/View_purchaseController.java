/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.openjfx.gamestore.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class View_purchaseController implements Initializable {

    @FXML
    private VBox purchasePaneTable;
    
    @FXML
    private Label idPurchaseLabel;
    
    @FXML
    private Label datePurchaseLabel;
    
    @FXML
    private Label numItemsLabel;
    
    @FXML
    private Label totalPaidLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void goBack(MouseEvent event) {
    }
    
}
