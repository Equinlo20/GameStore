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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.openjfx.gamestore.data.LList;
import org.openjfx.gamestore.models.domain.ItemGame;
import org.openjfx.gamestore.models.domain.Purchase;
import org.openjfx.gamestore.models.service.IPurchaseService;
import org.openjfx.gamestore.models.service.PurchaseService;
import org.openjfx.gamestore.utils.AlertUtils;
import org.openjfx.gamestore.utils.CartListener;
import org.openjfx.gamestore.utils.Utilities;

public class Shopping_cartController implements Initializable {

    @FXML
    private Button btnBuy;

    @FXML
    private Button btnCancel;
    
    @FXML
    private Label numItemsLabel;

    @FXML
    private VBox itemsPaneTable;

    @FXML
    private Label numItemsSummaryLabel;

    @FXML
    private Label totalToPaySummaryLabel;

    private CartListener cartListener;

    private final IPurchaseService purchaseService = new PurchaseService();

    private LList<ItemGame> items = new LList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadItems();
    }

    private void loadItems() {

        getItems();
        
        if (!items.isEmpty()) {
            disableButtoms(false);
            numItemsLabel.setText(String.valueOf(this.items.getSize()));
            this.numItemsSummaryLabel.setText(String.valueOf(this.items.getSize()));
            this.totalToPaySummaryLabel.setText("$" + purchaseService.getCurrentPurchase().getTotal());

            this.cartListener = new CartListener() {
                @Override
                public void onClickUpdateListener() {
                    resetTotalToPay();
                }

                @Override
                public void onClickDeleteItemGameListener(ItemGame item) {
                    if (AlertUtils.getAndShowAlertConfirm("Do you want to remove this item from the shopping cart?")) {
                        purchaseService.deleteItemPurchase(item);
                        loadItems();
                    }

                }
            };

            this.itemsPaneTable.getChildren().clear();

            for (int i = 0; i < items.getSize(); i++) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(Utilities.getUrlFxmlResource("item_cart"));

                    HBox hbox = fxmlLoader.load();

                    Item_cartController itemC = fxmlLoader.getController();

                    try {
                        itemC.setData(items.get(i), i + 1, this.cartListener);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    this.itemsPaneTable.getChildren().add(hbox);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        } else {
            resetValues();
        }

    }

    private void getItems() {
        Purchase purchase = purchaseService.getCurrentPurchase();
        if (purchase != null) {
            this.items = purchaseService.getCurrentPurchase().getItems();
        }else{
            this.items = new LList<>();
        }
    }

    private void resetValues() {
        this.itemsPaneTable.getChildren().clear();
        this.numItemsLabel.setText("0");
        this.numItemsSummaryLabel.setText("0");
        this.totalToPaySummaryLabel.setText("$0");
        disableButtoms(true);
    }
    
    private void disableButtoms(boolean disabled){
        this.btnBuy.setDisable(disabled);
        this.btnCancel.setDisable(disabled);
    }

    private void resetTotalToPay() {
        this.totalToPaySummaryLabel.setText("$" + purchaseService.getCurrentPurchase().getTotal());
    }

    @FXML
    void cancelPurchase(MouseEvent event) {
        if (AlertUtils.getAndShowAlertConfirm("Do you want to cancel the purchase?")) {
            this.purchaseService.deletePurchase();
            loadItems();
            AlertUtils.showAlertInfo("Purchase canceled");
        }
    }

    @FXML
    void makePurchase(MouseEvent event) {
        if (AlertUtils.getAndShowAlertConfirm("Do you want to make the purchase?")) {
            this.purchaseService.makePurchase();
            loadItems();
            AlertUtils.showAlertInfo("Successful purchase");
        }
    }

}
