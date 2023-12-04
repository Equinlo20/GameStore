/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.openjfx.gamestore.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Random;
import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.openjfx.gamestore.App;

public class Utilities {
    
    public static Stage getStage(){
        return App.stage;
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    public static FXMLLoader getFXMLoader(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/" + fxml + ".fxml"));
        return fxmlLoader;
    }

    public static URL getUrlFxmlResource(String fxml) throws IOException {
        return App.class.getResource("views/" + fxml + ".fxml");
    }

    public static URL getUrlCssResource(String css) throws IOException {
        return App.class.getResource("views/styles/" + css + ".css");
    }

    public static InputStream getUrlImage(String image) {
        return App.class.getResourceAsStream(image);
    }

    public static void changeScene(Parent fxml, double width, double height) {
        App.stage.setScene(new Scene(fxml, width, height));
        App.stage.centerOnScreen();
    }

    public static void waitForTime(long time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void getEffectTransition(Node node, double time) {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(time), node);
        fadeTransition.setFromValue(0.1);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
    }

    public static long getIdRandom() {
        Random r = new Random();
        int idRandom = r.nextInt(90000) + 10000;
        
        return idRandom;
    }
    
    public static long getIdGameRandom() {
        Random r = new Random();
        int idRandom = r.nextInt(900000) + 100000;
        
        return idRandom;
    }
}
