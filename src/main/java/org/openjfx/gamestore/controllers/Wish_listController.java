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
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import org.openjfx.gamestore.data.LList;
import org.openjfx.gamestore.models.domain.Game;
import org.openjfx.gamestore.models.service.IUserService;
import org.openjfx.gamestore.models.service.IWishListService;
import org.openjfx.gamestore.models.service.UserService;
import org.openjfx.gamestore.models.service.WishListService;
import org.openjfx.gamestore.utils.MyListener;

import org.openjfx.gamestore.utils.Utilities;

public class Wish_listController implements Initializable {

    private final IUserService userService = new UserService();
    private final IWishListService wsService = new WishListService();

    @FXML
    private VBox gameCard;

    @FXML
    private Label amountItemsCartLabel;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadItems();
    }

    private LList<Game> items = new LList<>();

    private void loadItems() {
        getItems();
        if (!items.isEmpty()) {
            GridPane gridGames = new GridPane();
            try {
                setChosenItem(items.get(0));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            myListener = new MyListener() {
                @Override
                public void onClickListener(Game item) {
                    setChosenItem(item);
                }
            };

            int column = 0;
            int row = 1;

            for (int i = 0; i < items.getSize(); i++) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(Utilities.getUrlFxmlResource("item_wish_list"));

                    AnchorPane anchorPane = fxmlLoader.load();

                    ItemWishListController itemC = fxmlLoader.getController();

                    if (column == 3) {
                        column = 0;
                        row++;
                    }
                    gridGames.add(anchorPane, column++, row);

                    gridGames.setMinWidth(Region.USE_COMPUTED_SIZE);
                    gridGames.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    gridGames.setMaxWidth(Region.USE_PREF_SIZE);

                    gridGames.setMinHeight(Region.USE_COMPUTED_SIZE);
                    gridGames.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    gridGames.setMaxHeight(Region.USE_PREF_SIZE);

                    GridPane.setMargin(anchorPane, new Insets(6));
                    try {
                        itemC.setData(items.get(i), myListener);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
            this.scroll.setContent(gridGames);
        }else{
            this.scroll.setContent(this.grid);
        }

    }

    private void setChosenItem(Game item) {
        nameGameLabel.setText(item.getName());
        priceGameLabel.setText("$" + String.valueOf(item.getPrice()));
        gameImage.setImage(new Image(Utilities.getUrlImage(item.getImgSrc())));
        SugAgeGameLabel.setText(item.getSuggestedAge());
        createdByGameLabel.setText(item.getCreatedBy());
        descriptionGameLabel.setText(item.getDescription());
        typeGameLabel.setText(item.getType());
    }

    private void getItems() {
        this.items = wsService.getWishList().getGames();
    }

}
