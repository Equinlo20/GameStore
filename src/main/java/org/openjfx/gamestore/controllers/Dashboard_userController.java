/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.openjfx.gamestore.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.openjfx.gamestore.App;
import org.openjfx.gamestore.utils.Utilities;

/**
 * FXML Controller class
 *
 * @author PINEDA
 */
public class Dashboard_userController implements Initializable {

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private AnchorPane parent;

    @FXML
    private AnchorPane pane1menu;

    @FXML
    private AnchorPane pane2menu;
    
    @FXML
    private AnchorPane paneContentArea;
    
    @FXML
    private ImageView logoImageMenu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        makeStageDrageable();
        makeMenuAnimation(null, false, 0.1, 0.1, 1, 0, -900);
        Utilities.getEffectTransition(parent, 0.3);
    }

    public void makeStageDrageable() {
        parent.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        parent.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                App.stage.setX(event.getScreenX() - xOffset);
                App.stage.setY(event.getScreenY() - yOffset);
                App.stage.setOpacity(0.7f);
            }
        });

        parent.setOnDragDone((e) -> {
            App.stage.setOpacity(1.0f);
        });

        parent.setOnMouseReleased((e) -> {
            App.stage.setOpacity(1.0f);
        });
    }

    private void makeMenuAnimation(MouseEvent event, boolean paneVisible, double secondsFade, double secondsTranslate, double fromValue, double toValue, double byX) {
        if (event == null && !paneVisible) {
            pane1menu.setVisible(paneVisible);
        } else if (event != null) {
            pane1menu.setVisible(!paneVisible);
        }

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(secondsFade), pane1menu);
        fadeTransition.setFromValue(fromValue);
        fadeTransition.setToValue(toValue);
        fadeTransition.play();

        if (paneVisible) {
            fadeTransition.setOnFinished(e -> {
                pane1menu.setVisible(!paneVisible);
            });
        }

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(secondsTranslate), pane2menu);
        translateTransition.setByX(byX);
        translateTransition.play();
    }
    
    private void changeContentArea(Parent fxml){
        if (!paneContentArea.getChildren().isEmpty()) {
            paneContentArea.getChildren().removeAll();
        }     
        paneContentArea.getChildren().setAll(fxml);
    }
    @FXML
    private void close_app(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void sing_off(MouseEvent event) throws IOException {
        Utilities.changeScene(Utilities.loadFXML("login"), 800, 600);
    }

    @FXML
    private void deployMenu(MouseEvent event) throws IOException {
        boolean paneVisible = pane1menu.isVisible();
        if (paneVisible) {
            makeMenuAnimation(event, paneVisible, 0.5, 0.5, 0.15, 0, -900);
            logoImageMenu.setVisible(true);
        } else {
            makeMenuAnimation(event, paneVisible, 0.5, 0.5, 0, 0.15, +900);
            logoImageMenu.setVisible(false);
            pane2menu.setOpacity(0.8);
        }
    }
    
    @FXML
    private void openCatalogue(MouseEvent event) throws IOException{
        changeContentArea(Utilities.loadFXML("catalogo_producos"));
    }
    
    @FXML
    void openWishList(MouseEvent event) throws IOException {
        changeContentArea(Utilities.loadFXML("wish_list"));
    }
    
    
    @FXML
    void openShoppingCart(MouseEvent event) throws IOException {
        changeContentArea(Utilities.loadFXML("shopping_cart"));
    }

    @FXML
    void openShoppingHistory(MouseEvent event) throws IOException {
        changeContentArea(Utilities.loadFXML("shopping_history"));
    }

    @FXML
    void openUserAccount(MouseEvent event) throws IOException {
        changeContentArea(Utilities.loadFXML("user_account"));
    }
}
