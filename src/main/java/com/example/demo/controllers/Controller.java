/**
 * @author Xiao Thung Gan - modified
 */

package com.example.demo.controllers;

import com.example.demo.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.demo.Main.HEIGHT;
import static com.example.demo.Main.WIDTH;

/**
 * Name: Controller Class
 * Purpose: Manage operation of index.fxml (Main Menu)
 */
public class Controller{

    @FXML
    public Label welcomeLabel;
    @FXML
    public Button loginButton;
    @FXML
    public Button playButton;
    @FXML
    public Button logoutButton;
    @FXML
    public Button leadButton;

    /**
     * Name: startButton
     * Purpose: Load Game Mode Interface and display
     * @param event
     * @throws Exception
     */
    @FXML
    public void startButton(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/gameMode.fxml"));
        Scene gameModeScene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(gameModeScene);
        primaryStage.show();
    }

    /**
     * Name: quitButton
     * Purpose: Show alert then quit program if user click 'OK'
     */
    @FXML
    public void quitButton() {
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

    /**
     * Name: loginPageButton
     * Purpose: Load Login Page and display it
     * @param event
     * @throws Exception
     */
    @FXML
    public void loginPageButton(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/login.fxml"));
        Scene loginScene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    /**
     * Name: logoutButton
     * Purpose: Load Menu Page and change elements to non-user display
     * @param event
     * @throws Exception
     */
    @FXML
    public void logoutButton(ActionEvent event) throws Exception{
        Alert a = new Alert(Alert.AlertType.NONE);
        a.setAlertType(Alert.AlertType.CONFIRMATION);
        a.setTitle("Quit Dialog");
        a.setHeaderText("Logout?");
        a.setContentText("Are you sure?");
        a.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/index.fxml"));
                loginButton.setVisible(true);
                logoutButton.setVisible(false);
                playButton.setText("Play as guest");
                Scene indexScene = null;
                try {
                    indexScene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                primaryStage.setScene(indexScene);
                primaryStage.show();
            }
        });
    }

    /**
     * Name: leaderboardButton
     * Purpose: Load leaderboard page and display it
     * @param event
     * @throws Exception
     */
    @FXML
    public void leaderboardButton(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/leaderboard.fxml"));
        Scene leadScene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(leadScene);
        primaryStage.show();
    }

}

