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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.openjfx.gamestore.data.LList;
import org.openjfx.gamestore.models.domain.Game;
import org.openjfx.gamestore.models.service.GameService;
import org.openjfx.gamestore.models.service.IGameService;
import org.openjfx.gamestore.utils.AlertUtils;
import org.openjfx.gamestore.utils.GoToViewGameListener;
import org.openjfx.gamestore.utils.ListenerProvider;
import org.openjfx.gamestore.utils.SetViewListener;
import org.openjfx.gamestore.utils.Utilities;

public class Game_managementController implements Initializable {

    private final IGameService gameService = new GameService();

    @FXML
    private BorderPane borderPaneArea;

    @FXML
    private VBox gamesPaneTable;

    @FXML
    private Label numGamesLabel;

    @FXML
    private VBox paneCenter;

    private SetViewListener svListener;

    private GoToViewGameListener gtvGameListener;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        loadListeners();
        loadGames();
    }

    private void loadGames() {
        this.gamesPaneTable.getChildren().clear();

        LList<Game> games = gameService.getAll();

        for (int i = 0; i < games.getSize(); i++) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Utilities.getUrlFxmlResource("item_game"));

                HBox hBox = fxmlLoader.load();
                if (i % 2 == 0) {
                    hBox.getStyleClass().add("alternative-color");
                }
                Item_gameController itemG = fxmlLoader.getController();

                try {
                    itemG.setData(games.get(i), i + 1);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                gamesPaneTable.getChildren().add(hBox);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        this.numGamesLabel.setText(String.valueOf(games.getSize()));
    }

    private void loadListeners() {
        this.svListener = ListenerProvider.getListenerProvider().getStListener();
        this.gtvGameListener = new GoToViewGameListener() {
            @Override
            public void onClickListenerGoToGameView(Game game, String nameView) {
                if (game != null) {
                    if (nameView.equals("view_game")) {
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            fxmlLoader.setLocation(Utilities.getUrlFxmlResource(nameView));

                            VBox vBox = fxmlLoader.load();

                            View_gameController vgC = fxmlLoader.getController();
                            vgC.setData(game);

                            changeContentCenterPanel(vBox);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            fxmlLoader.setLocation(Utilities.getUrlFxmlResource(nameView));

                            VBox vBox = fxmlLoader.load();

                            Edit_gameController egC = fxmlLoader.getController();
                            egC.setData(game);
                            
                            changeContentCenterPanel(vBox);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }

                } else {
                    changeContentCenterPanel(paneCenter);
                }

            }

            @Override
            public void onClickListenerDeleteGame(Game game) {
                if (AlertUtils.getAndShowAlertConfirm("Do you want delete this game?")) {
                    if (gameService.delete(game)) {
                        AlertUtils.showAlertInfo("Game deleted successfully");
                        loadGames();
                    }
                }
                
            }
        };

        ListenerProvider.getListenerProvider().setGtvGameListener(gtvGameListener);
    }

    private void changeContentCenterPanel(VBox paneCenter) {
        this.borderPaneArea.setCenter(paneCenter);
    }

    @FXML
    void goToAddGame(MouseEvent event) {
        this.svListener.onClickListenerGoToView("add_game");
    }

}
