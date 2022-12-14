/**
 * @author Xiao Thung Gan
 */

package com.example.demo.controllers;

import com.example.demo.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.demo.Main.HEIGHT;
import static com.example.demo.Main.WIDTH;

/**
 * Name: EndGameController Class
 * Purpose: Manage operation of endGame.fxml and winGame.fxml
 */
public class EndGameController {
    /**
     * current score
     */
    @FXML
    public Label curScore;

    /**
     * best score achieved by user
     * (can be current score or past high score)
     */
    @FXML
    public Label bestScore;

    public EndGameController(){

    }

    /**
     * Name: returnMenu
     * Purpose: Load Main Menu and display
     * @param event
     * @throws IOException
     */
    @FXML
    public void returnMenu(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/index.fxml"));
        Scene indexScene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(indexScene);
        primaryStage.show();
    }

    /**
     * Name: quit
     * Purpose: quit program
     */
    @FXML
    public void quit() {
        Alert a = new Alert(Alert.AlertType.NONE);
        a.setAlertType(Alert.AlertType.CONFIRMATION);
        a.setTitle("Quit Dialog");
        a.setHeaderText("Quit from 2048");
        a.setContentText("Are you sure?");
        a.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                Platform.exit();
            }
        });
    }
}
