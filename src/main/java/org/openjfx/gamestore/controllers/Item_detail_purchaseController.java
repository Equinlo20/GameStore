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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.openjfx.gamestore.models.domain.ItemGame;
import org.openjfx.gamestore.utils.Utilities;

public class Item_detail_purchaseController implements Initializable {

    @FXML
    private Label numItem;
    
    @FXML
    private Label nameGameLabel;
    
    @FXML
    private ImageView imageGame;
    
    @FXML
    private Text descriptionGameTxt;
    
    @FXML
    private Label priceGameLabel;
    
    @FXML
    private Label amountGameLabel;
    
    @FXML
    private Label totalPriceItemLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    public void setData(ItemGame item, int num){
        this.numItem.setText(String.valueOf(num));
        this.nameGameLabel.setText(item.getGame().getName());
        this.descriptionGameTxt.setText(item.getGame().getDescription());
        this.priceGameLabel.setText(String.valueOf(item.getGame().getPrice()));
        this.amountGameLabel.setText(String.valueOf(item.getAmount()));
        this.totalPriceItemLabel.setText("$" + item.getTotalItemPrice());
        this.imageGame.setImage(new Image(Utilities.getUrlImage(item.getGame().getImgSrc())));
    }
}
