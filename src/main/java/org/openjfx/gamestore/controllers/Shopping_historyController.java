/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.openjfx.gamestore.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.openjfx.gamestore.models.domain.Purchase;
import org.openjfx.gamestore.utils.Utilities;

public class Shopping_historyController implements Initializable {

    @FXML
    private Label numPurchasesLabel;
    
    @FXML
    private VBox purchasePaneTable;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadPurchases();
    }

    private void loadPurchases() {
        List<Purchase> purchases = getPurchases();
        for (int i = 0; i < purchases.size(); i++) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Utilities.getUrlFxmlResource("item_purchase"));
                
                HBox hBox = fxmlLoader.load();
                if (i%2==0) {
                    hBox.getStyleClass().add("alternative-color");
                }
                Item_purchaseController itemC = fxmlLoader.getController();
                
                itemC.setData(purchases.get(i), i+1);
                
                purchasePaneTable.getChildren().add(hBox);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
        numPurchasesLabel.setText(purchases.size() + " PURCHASES");
    }

    private List<Purchase> getPurchases() {
        List<Purchase> purchases = new ArrayList<>();
        double initPrice = 49999;
        for (int i = 0; i < 20; i++) {
            initPrice++;
            Purchase item = new Purchase();
            item.setId(151515);
            item.setDate("11/11/2023");
            item.setNumItems(5);
            item.setTotal(250000);

            purchases.add(item);
        }

        return purchases;
    }

}
