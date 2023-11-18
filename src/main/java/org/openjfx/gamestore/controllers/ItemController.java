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
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import org.openjfx.gamestore.models.domain.ItemGame;
import org.openjfx.gamestore.utils.MyListener;

import org.openjfx.gamestore.utils.Utilities;


public class ItemController implements Initializable {

    @FXML
    private Label nameItemLabel;
    @FXML
    private Label priceItemLabel;
    @FXML
    private ImageView imageItemLabel;

    private MyListener myListener;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    private ItemGame item;

    @FXML
    private void click(MouseEvent event) {
        myListener.onClickListener(item);
    }

    public void setData(ItemGame itemG, MyListener myListener) {
        this.item = itemG;
        this.myListener = myListener;
        nameItemLabel.setText(itemG.getName());
        priceItemLabel.setText("$" + itemG.getPrice());
        Image image = new Image(Utilities.getUrlImage(itemG.getImgSrc()));
        imageItemLabel.setImage(image);
    }

}
