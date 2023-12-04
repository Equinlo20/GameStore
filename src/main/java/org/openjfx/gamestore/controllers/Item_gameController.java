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
import org.openjfx.gamestore.models.domain.Game;
import org.openjfx.gamestore.utils.GoToViewGameListener;
import org.openjfx.gamestore.utils.ListenerProvider;

public class Item_gameController implements Initializable {

    @FXML
    private Label createByGameLabel;

    @FXML
    private Label idGameLabel;

    @FXML
    private Label nameGameLabel;

    @FXML
    private Label numLabel;

    @FXML
    private Label priceGameLabel;

    @FXML
    private Label typeGameLabel;
    
    private Game game;
    
    private GoToViewGameListener gtvGameListener;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.gtvGameListener = ListenerProvider.getListenerProvider().getGtvGameListener();
    }
    
    public void setData(Game game, int num){
        this.game = game;
        
        this.numLabel.setText(String.valueOf(num));
        this.idGameLabel.setText(String.valueOf(game.getId()));
        this.nameGameLabel.setText(game.getName());
        this.priceGameLabel.setText("$" + game.getPrice());
        this.typeGameLabel.setText(game.getType());
        this.createByGameLabel.setText(game.getCreatedBy());
    }

    @FXML
    void deleteGame(MouseEvent event) {
        this.gtvGameListener.onClickListenerDeleteGame(game);
    }

    @FXML
    void goToEditGame(MouseEvent event) {
        this.gtvGameListener.onClickListenerGoToGameView(game, "edit_game");
    }

    @FXML
    void goToViewGame(MouseEvent event) {
        this.gtvGameListener.onClickListenerGoToGameView(game, "view_game");
    }

}
