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
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import org.openjfx.gamestore.models.domain.Game;
import org.openjfx.gamestore.utils.MyListener;

import org.openjfx.gamestore.utils.Utilities;

public class Catalogo_producosController implements Initializable {

    @FXML
    private VBox gameCard;
    
    @FXML
    private Label SugAgeGameLabel;

    @FXML
    private Label createdByGameLabel;

    @FXML
    private Label descriptionGameLabel;
    
    @FXML
    private Label typeGameLabel;
    
    @FXML
    private Label nameGameLabel;
    @FXML
    private Label priceGameLabel;
    @FXML
    private ImageView gameImage;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    
    private MyListener myListener;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadItems();
    }

    private List<Game> items = new ArrayList<>();//Cambiar luego por lista enlazada, esto es para prueba

    private void loadItems() {
        items.addAll(getItems());
        if (!items.isEmpty()) {
            setChosenItem(items.get(0));
            myListener = new MyListener(){
                @Override
                public void onClickListener(Game item){
                    setChosenItem(item);
                }
            };
        }
        int column = 0;
        int row = 1;

        for (int i = 0; i < items.size(); i++) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Utilities.getUrlFxmlResource("item"));

                AnchorPane anchorPane = fxmlLoader.load();
                
                ItemController itemC = fxmlLoader.getController();
 
                if (column == 3) {
                    column = 0;
                    row++;
                }
                grid.add(anchorPane, column++, row);

                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(6));
                itemC.setData(items.get(i), myListener);
                
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    }
    
    private void setChosenItem(Game item){
        nameGameLabel.setText(item.getName());
        priceGameLabel.setText("$" + String.valueOf(item.getPrice()));
        gameImage.setImage(new Image(Utilities.getUrlImage(item.getImgSrc())));
        SugAgeGameLabel.setText(item.getSuggestedAge());
        createdByGameLabel.setText(item.getCreatedBy());
        descriptionGameLabel.setText(item.getDescription());
        typeGameLabel.setText(item.getType());
    }

    private List<Game> getItems() {
        List<Game> items = new ArrayList<>();
        double initPrice = 49999;
        for (int i = 0; i < 20; i++) {
            initPrice++;
            Game item = new Game();
            item.setName("Halo Combat");
            item.setPrice(initPrice);
            item.setImgSrc("/images/game2.png");
            item.setCreatedBy("Microsoft Xbox");
            item.setDescription("Strategy game in real time, Xbox Exclusive.");
            item.setType("War");
            item.setSuggestedAge("13+");

            items.add(item);
        }

        return items;
    }

}
