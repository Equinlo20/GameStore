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
import org.openjfx.gamestore.models.domain.Purchase;

public class Item_purchaseController implements Initializable {

    @FXML
    private Label datePurchaseLabel;

    @FXML
    private Label idPurchaseLabel;

    @FXML
    private Label numItemsPurchaseLabel;

    @FXML
    private Label totalPurchaseLabel;
    @FXML
    private Label numLabel;

    private Purchase purchase;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setData(Purchase purchase, int num) {
        this.purchase = purchase;

        numLabel.setText(String.valueOf(num));
        datePurchaseLabel.setText(purchase.getDate());
        idPurchaseLabel.setText(String.valueOf(purchase.getId()));
        numItemsPurchaseLabel.setText(String.valueOf(purchase.getNumItems()));
        totalPurchaseLabel.setText("$" + purchase.getTotal());
    }

    @FXML
    void goToViewPurchases(MouseEvent event) {

    }
}
