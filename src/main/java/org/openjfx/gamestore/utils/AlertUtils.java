/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.openjfx.gamestore.utils;

import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;

public class AlertUtils {
    
    public static void showAlertError(String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("ERROR");
        alert.setResizable(false);
        alert.setContentText(contentText);
        
        DialogPane dialogPane = alert.getDialogPane();
        
        try {
            dialogPane.getStylesheets().add(Utilities.getUrlCssResource("custom_alert").toExternalForm());
            dialogPane.getStyleClass().add("my-dialog");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        alert.showAndWait();
    }

    public static void showAlertInfo(String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("INFO");
        alert.setResizable(false);
        alert.setContentText(contentText);
        
        DialogPane dialogPane = alert.getDialogPane();
        
        try {
            dialogPane.getStylesheets().add(Utilities.getUrlCssResource("custom_alert").toExternalForm());
            dialogPane.getStyleClass().add("my-dialog");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        alert.showAndWait();
    }
}
