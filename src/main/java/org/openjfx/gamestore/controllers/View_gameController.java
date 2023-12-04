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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.openjfx.gamestore.models.domain.Game;
import org.openjfx.gamestore.utils.GoToViewGameListener;
import org.openjfx.gamestore.utils.ListenerProvider;
import org.openjfx.gamestore.utils.Utilities;

public class View_gameController implements Initializable {

    private GoToViewGameListener gtvGameListener;

    @FXML
    private VBox paneCenter;
    
    @FXML
    private Label idGameLabel;
    
    @FXML
    private Label nameGameLabel;
    
    @FXML
    private Label priceGameLabel;
    
    @FXML
    private Text descriptionGameTxt;
    
    @FXML
    private Label typeGameLabel;
    
    @FXML
    private Label createdByGameLabel;
    
    @FXML
    private Label SuggestedAgeGameLabel;
    
    @FXML
    private ImageView imageGame;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.gtvGameListener = ListenerProvider.getListenerProvider().getGtvGameListener();
    }
    
    public void setData(Game game){
        this.idGameLabel.setText(String.valueOf(game.getId()));
        this.nameGameLabel.setText(game.getName());
        this.descriptionGameTxt.setText(game.getDescription());
        this.priceGameLabel.setText("$" + game.getPrice());
        this.SuggestedAgeGameLabel.setText(game.getSuggestedAge());
        this.createdByGameLabel.setText(game.getCreatedBy());
        this.typeGameLabel.setText(game.getType());
        this.imageGame.setImage(new Image(Utilities.getUrlImage(game.getImgSrc())));
    }

    @FXML
    private void goBack(MouseEvent event) {
        this.gtvGameListener.onClickListenerGoToGameView(null, "game_management");
    }

}
