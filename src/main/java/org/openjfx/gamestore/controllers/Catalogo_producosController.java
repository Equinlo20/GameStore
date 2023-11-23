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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import org.openjfx.gamestore.data.LList;
import org.openjfx.gamestore.models.domain.Game;
import org.openjfx.gamestore.models.service.GameService;
import org.openjfx.gamestore.models.service.IGameService;
import org.openjfx.gamestore.models.service.IUserService;
import org.openjfx.gamestore.models.service.IWishListService;
import org.openjfx.gamestore.models.service.UserService;
import org.openjfx.gamestore.models.service.WishListService;
import org.openjfx.gamestore.utils.AlertUtils;
import org.openjfx.gamestore.utils.MyListener;

import org.openjfx.gamestore.utils.Utilities;

public class Catalogo_producosController implements Initializable {
    
    private final IUserService userService = new UserService();
    private final IGameService gameService = new GameService();
    private final IWishListService wsService = new WishListService();

    @FXML
    private VBox gameCard;
    
    @FXML
    private Label SugAgeGameLabel;
    
    @FXML
    private Label amountItemsWSLabel;

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
    
    private Game selectedGame;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadItems();
        loadInfoNumItemsWishList();
    }

    private LList<Game> games = new LList<>();
    
    private void loadItems2() {
        getItems();
        if (!games.isEmpty()) {
            try {
                setChosenItem(games.get(0));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            myListener = new MyListener(){
                @Override
                public void onClickListener(Game game){
                    setChosenItem(game);
                }
            };
        }
        int column = 0;
        int row = 1;

        for (int i = 0; i < games.getSize(); i++) {
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
                try {
                    itemC.setData(games.get(i), myListener);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    }

    private void loadItems() {
        GridPane gridGames = new GridPane();
        getItems();
        if (!games.isEmpty()) {
            try {
                setChosenItem(games.get(0));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            myListener = new MyListener(){
                @Override
                public void onClickListener(Game game){
                    setChosenItem(game);
                }
            };
        }
        int column = 0;
        int row = 1;

        for (int i = 0; i < games.getSize(); i++) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Utilities.getUrlFxmlResource("item"));

                AnchorPane anchorPane = fxmlLoader.load();
                
                ItemController itemC = fxmlLoader.getController();
 
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
                    itemC.setData(games.get(i), myListener);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
        
        this.scroll.setContent(gridGames);
    }
    
    private void setChosenItem(Game game){
        nameGameLabel.setText(game.getName());
        priceGameLabel.setText("$" + String.valueOf(game.getPrice()));
        gameImage.setImage(new Image(Utilities.getUrlImage(game.getImgSrc())));
        SugAgeGameLabel.setText(game.getSuggestedAge());
        createdByGameLabel.setText(game.getCreatedBy());
        descriptionGameLabel.setText(game.getDescription());
        typeGameLabel.setText(game.getType());
        this.selectedGame = game;
    }

    private void getItems() {
        this.games = gameService.getAll();
    }
    
    private void loadInfoNumItemsWishList(){
        amountItemsWSLabel.setText(wsService.getWishList().getNumGames() + " Items");
    }
    
    @FXML
    void addToWishList(MouseEvent event) {
        if (!wsService.gameExists(this.selectedGame.getId())) {
            if (AlertUtils.getAndShowAlertConfirm("Do you want to add the game to the wish list?")) {
                wsService.addGame(selectedGame);
                AlertUtils.showAlertInfo("Game added successfully.");
                loadInfoNumItemsWishList();
            }
        }else{
            AlertUtils.showAlertInfo("Game already added to the wish list.");
        }
    }

}
