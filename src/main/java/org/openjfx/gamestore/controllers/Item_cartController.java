/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.openjfx.gamestore.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.openjfx.gamestore.models.domain.ItemGame;
import org.openjfx.gamestore.models.service.IPurchaseService;
import org.openjfx.gamestore.models.service.PurchaseService;
import org.openjfx.gamestore.utils.CartListener;
import org.openjfx.gamestore.utils.Utilities;

/**
 * FXML Controller class
 *
 */
public class Item_cartController implements Initializable {

    @FXML
    private Label descriptionGameLabel;

    @FXML
    private ImageView imageGame;

    @FXML
    private Label nameGameLabel;

    @FXML
    private Label numItem;

    @FXML
    private Spinner<Integer> numItemsSpinner;

    @FXML
    private Label priceGameLabel;

    @FXML
    private Label totalPriceItemLabel;

    private ItemGame itemGame;

    private CartListener cartListener;
    
    private final IPurchaseService purchaseService = new PurchaseService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setData(ItemGame itemGame, int numItem, CartListener cartListener) {
        this.itemGame = itemGame;
        
        this.cartListener = cartListener;
        this.numItem.setText(String.valueOf(numItem));
        this.nameGameLabel.setText(itemGame.getGame().getName());
        this.descriptionGameLabel.setText(itemGame.getGame().getDescription());
        this.priceGameLabel.setText(String.valueOf(itemGame.getGame().getPrice()));
        this.totalPriceItemLabel.setText("$" + itemGame.getTotalItemPrice());
        initSpinner((int) itemGame.getAmount());
        Image image = new Image(Utilities.getUrlImage(itemGame.getGame().getImgSrc()));
        imageGame.setImage(image);
    }

    private void initSpinner(int amout) {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100);
        valueFactory.setValue(amout);
        numItemsSpinner.setValueFactory(valueFactory);
        
        this.numItemsSpinner.valueProperty().addListener(new ChangeListener<Integer>(){
        @Override
        public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2){
            purchaseService.UpdateItem(itemGame.getGame().getId(), numItemsSpinner.getValue());
            totalPriceItemLabel.setText("$" + itemGame.getTotalItemPrice());
            cartListener.onClickUpdateListener();
        }
        
    });
    }

    @FXML
    void deleteItem(MouseEvent event) {
        this.cartListener.onClickDeleteItemGameListener(this.itemGame);
    }
}
