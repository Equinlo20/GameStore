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
import org.openjfx.gamestore.models.domain.Purchase;
import org.openjfx.gamestore.utils.ListenerProvider;
import org.openjfx.gamestore.utils.ViewPurchaseListener;

public class View_purchaseController implements Initializable {
    
    private ViewPurchaseListener vPListener;

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
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.vPListener = ListenerProvider.getListenerProvider().getvPListener();
    }    

    @FXML
    private void goBack(MouseEvent event) {
        this.vPListener.onClickListenerGoView(null, "shopping_history");
    }
    
    public void setData(Purchase purchase){
        this.idPurchaseLabel.setText(String.valueOf(purchase.getId()));
        this.datePurchaseLabel.setText(purchase.getDate());
        this.numItemsLabel.setText(String.valueOf(purchase.getNumItems()));
        this.totalPaidLabel.setText("$" + purchase.getTotal());
    }
    
}
