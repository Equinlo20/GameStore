/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.openjfx.gamestore.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.openjfx.gamestore.data.LList;
import org.openjfx.gamestore.models.domain.Purchase;
import org.openjfx.gamestore.models.service.IPurchaseService;
import org.openjfx.gamestore.models.service.PurchaseService;
import org.openjfx.gamestore.utils.ListenerProvider;
import org.openjfx.gamestore.utils.Utilities;
import org.openjfx.gamestore.utils.ViewPurchaseListener;

public class Shopping_historyController implements Initializable {
    
    private ViewPurchaseListener vPListener;
    
    @FXML
    private BorderPane borderPane;

    @FXML
    private Label numPurchasesLabel;
    
    @FXML
    private VBox paneCenterInitVbox;
    
    @FXML
    private VBox purchasePaneTable;
    
    private final IPurchaseService purchaseService = new PurchaseService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadListeners();
        loadPurchases();
    }

    private void loadPurchases() {
        LList<Purchase> purchases = purchaseService.getAllPurchases();
        for (int i = 0; i < purchases.getSize(); i++) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Utilities.getUrlFxmlResource("item_purchase"));
                
                HBox hBox = fxmlLoader.load();
                if (i%2==0) {
                    hBox.getStyleClass().add("alternative-color");
                }
                Item_purchaseController itemC = fxmlLoader.getController();
                
                try {
                    itemC.setData(purchases.get(i), i+1);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                
                purchasePaneTable.getChildren().add(hBox);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
        numPurchasesLabel.setText(purchases.getSize() + " PURCHASES");
    }
    
    private void loadListeners(){
        this.vPListener = new ViewPurchaseListener(){
            @Override
            public void onClickListenerGoView(Purchase purchase,String nameView){
                if (purchase != null) {
                    try {
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            fxmlLoader.setLocation(Utilities.getUrlFxmlResource(nameView));

                            VBox vBox = fxmlLoader.load();

                            View_purchaseController vpC = fxmlLoader.getController();
                            vpC.setData(purchase);

                            changeContentCenterPanel(vBox);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                }else{
                    changeContentCenterPanel(paneCenterInitVbox);
                }
            }
        };
        ListenerProvider.getListenerProvider().setvPListener(vPListener);
    }
    
    private void changeContentCenterPanel(VBox paneCenter) {
        this.borderPane.setCenter(paneCenter);
    }

}
