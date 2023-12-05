/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.openjfx.gamestore.controllers;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import org.openjfx.gamestore.models.domain.Game;
import org.openjfx.gamestore.models.service.GameService;
import org.openjfx.gamestore.models.service.IGameService;
import org.openjfx.gamestore.utils.AlertUtils;
import org.openjfx.gamestore.utils.GoToViewGameListener;
import org.openjfx.gamestore.utils.ListenerProvider;
import org.openjfx.gamestore.utils.Utilities;

public class Edit_gameController implements Initializable {

    @FXML
    private VBox paneCenter;

    @FXML
    private TextField nameTxtField;

    @FXML
    private TextField priceTxtField;

    @FXML
    private TextField descriptionTxtField;

    @FXML
    private TextField typeTxtField;

    @FXML
    private TextField createdByTxtField;

    @FXML
    private TextField sugAgeTxtField;

    @FXML
    private ImageView imageGame;

    @FXML
    private Label pathImage;

    private GoToViewGameListener gtvGameListener;

    private Game game;

    private File fileSelected;

    private final IGameService gameService = new GameService();

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

    public void setData(Game game) {

        this.game = game;

        this.nameTxtField.setText(game.getName());
        this.descriptionTxtField.setText(game.getDescription());
        this.priceTxtField.setText(String.valueOf(game.getPrice()));
        this.sugAgeTxtField.setText(game.getSuggestedAge());
        this.createdByTxtField.setText(game.getCreatedBy());
        this.typeTxtField.setText(game.getType());
        this.imageGame.setImage(new Image(Utilities.getUrlImage(game.getImgSrc())));
    }

    private void resetFields() {
        this.nameTxtField.setText(game.getName());
        this.descriptionTxtField.setText(game.getDescription());
        this.priceTxtField.setText(String.valueOf(game.getPrice()));
        this.sugAgeTxtField.setText(game.getSuggestedAge());
        this.createdByTxtField.setText(game.getCreatedBy());
        this.typeTxtField.setText(game.getType());
        this.imageGame.setImage(new Image(Utilities.getUrlImage(game.getImgSrc())));

        this.pathImage.setText("Path: no new image selected");
        this.fileSelected = null;
    }

    @FXML
    private void goBack(MouseEvent event) {
        this.gtvGameListener.onClickListenerGoToGameView(null, "game_management");
    }

    @FXML
    private void selectImage(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Game Image");

        File file = fileChooser.showOpenDialog(Utilities.getStage());
        if (file != null) {
            try {
                this.pathImage.setText("Path: " + file.getAbsolutePath());
                this.imageGame.setImage(new Image(file.toURI().toURL().toString()));
                this.fileSelected = file;
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }

        } else {
            this.pathImage.setText("Path: no new image selected");
            this.imageGame.setImage(new Image(Utilities.getUrlImage(game.getImgSrc())));
            this.fileSelected = null;
        }
    }

    @FXML
    private void saveGame(MouseEvent event) {
        String nameGame = this.nameTxtField.getText();
        String priceGame = this.priceTxtField.getText();
        String descriptionGame = this.descriptionTxtField.getText();
        String typeGame = this.typeTxtField.getText();
        String createByGame = this.createdByTxtField.getText();
        String sugAgeGame = this.sugAgeTxtField.getText();

        if (nameGame.isBlank() || nameGame.isEmpty()
                || priceGame.isBlank() || priceGame.isEmpty()
                || descriptionGame.isBlank() || descriptionGame.isEmpty()
                || typeGame.isBlank() || typeGame.isEmpty()
                || createByGame.isBlank() || createByGame.isEmpty()
                || sugAgeGame.isBlank() || sugAgeGame.isEmpty()) {

            AlertUtils.showAlertError("You must fill out all fields or select an image.");
        } else {
            if (priceGame.matches("[0-9]+|[0-9]+[.][0-9]+")) {
                if (sugAgeGame.matches("[0-9]{1,2}[+]")) {

                    if (AlertUtils.getAndShowAlertConfirm("Do you want to make the changes?")) {

                        if (gameService.update(new Game(game.getId(), nameGame, game.getImgSrc(), Double.parseDouble(priceGame), descriptionGame, typeGame, createByGame, sugAgeGame), fileSelected)) {
                            AlertUtils.showAlertInfo("Changes made successfully");
                        }

                    } else {
                        resetFields();
                    }

                } else {
                    AlertUtils.showAlertError("You must write a valid value in Suggested Age Field: For example: 12+");
                }

            } else {
                AlertUtils.showAlertError("You must write a valid value in Price Field");
            }

        }
    }

}
