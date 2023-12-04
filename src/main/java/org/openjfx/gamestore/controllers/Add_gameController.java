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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import org.openjfx.gamestore.models.domain.Game;
import org.openjfx.gamestore.models.service.GameService;
import org.openjfx.gamestore.models.service.IGameService;
import org.openjfx.gamestore.utils.AlertUtils;
import org.openjfx.gamestore.utils.Utilities;

public class Add_gameController implements Initializable {

    @FXML
    private BorderPane borderPaneArea;

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

    private File fileSelected = null;

    private final IGameService gameService = new GameService();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Hola
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
            this.pathImage.setText("Path: no image selected");
            this.imageGame.setImage(new Image(Utilities.getUrlImage("/images/nocontent.png")));
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

        if (this.fileSelected == null
                || nameGame.isBlank() || nameGame.isEmpty()
                || priceGame.isBlank() || priceGame.isEmpty()
                || descriptionGame.isBlank() || descriptionGame.isEmpty()
                || typeGame.isBlank() || typeGame.isEmpty()
                || createByGame.isBlank() || createByGame.isEmpty()
                || sugAgeGame.isBlank() || sugAgeGame.isEmpty()) {

            AlertUtils.showAlertError("You must fill out all fields or select an image.");
        } else {
            if (priceGame.matches("[0-9]+|[0-9]+[.][0-9]+")) {
                if (sugAgeGame.matches("[0-9]{1,2}[+]")) {
                    if (AlertUtils.getAndShowAlertConfirm("Do you want to save the game?")) {
                        long idGame = Utilities.getIdGameRandom();
                        while (gameService.idExists(idGame)) {
                            idGame = Utilities.getIdGameRandom();
                        }

                        String srcImage = "/images/" + getNewNameImage(String.valueOf(idGame));

                        if (gameService.save(new Game(idGame, nameGame, srcImage, Double.parseDouble(priceGame), descriptionGame, typeGame, createByGame, sugAgeGame), fileSelected)) {
                            AlertUtils.showAlertInfo("Saved game successfully");
                            resetForm();
                        }
                    }
                } else {
                    AlertUtils.showAlertError("You must write a valid value in Suggested Age Field: For example: 12+");
                }

            } else {
                AlertUtils.showAlertError("You must write a valid value in Price Field");
            }

        }

    }

    private String getNameImage() {
        String nameImage = "";

        try {
            String pathSplitImage[] = fileSelected.toURI().toURL().toString().split("/");
            nameImage = pathSplitImage[pathSplitImage.length - 1];
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }

        return nameImage;
    }

    private String getNewNameImage(String idGame) {
        String nameImage = fileSelected.getName();
        String[] nameImageSplit = nameImage.split("\\.");

        String newNameImage = idGame + "." + nameImageSplit[nameImageSplit.length - 1];

        return newNameImage;
    }

    private void resetForm() {
        this.createdByTxtField.setText("");
        this.descriptionTxtField.setText("");
        this.nameTxtField.setText("");
        this.priceTxtField.setText("");
        this.sugAgeTxtField.setText("");
        this.typeTxtField.setText("");

        this.pathImage.setText("Path: no image selected");
        this.imageGame.setImage(new Image(Utilities.getUrlImage("/images/nocontent.png")));
        this.fileSelected = null;
    }

}
