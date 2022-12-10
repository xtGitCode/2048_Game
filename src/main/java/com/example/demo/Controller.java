package com.example.demo;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class Controller{
    static final int WIDTH = 780;
    static final int HEIGHT = 780;
    private Group gameRoot = new Group();
    private Scene gameScene = new Scene(gameRoot, 900, HEIGHT, Color.rgb(189, 177, 92));

    public void setGameRoot(Group gameRoot) {
        this.gameRoot = gameRoot;
    }
    public void setGameScene(Scene gameScene) {
        this.gameScene = gameScene;
    }

    Group endgameRoot = new Group();
    Scene endGameScene = new Scene(endgameRoot, WIDTH, HEIGHT, Color.rgb(250, 20, 100, 0.2));

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

    @FXML
    /*public void startButton(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("gameMode.fxml"));
        Scene gameModeScene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(gameModeScene);
        primaryStage.show();
    }*/

    public void startButton(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("index.fxml"));
        Scene menuScene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Group gameRoot = new Group();
        setGameRoot(gameRoot);
        Scene gameScene = new Scene(gameRoot, WIDTH, HEIGHT, Color.rgb(189, 177, 92)); //bg color game
        setGameScene(gameScene);
        GameScene game = new GameScene();
        game.game(gameScene, gameRoot, primaryStage, endGameScene, endgameRoot, menuScene, LoginController.highScore);
        primaryStage.setScene(gameScene);
        primaryStage.show();
    }

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

    @FXML
    public void loginPageButton(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        Scene loginScene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    @FXML
    public void logoutButton(ActionEvent event) throws Exception{
        Alert a = new Alert(Alert.AlertType.NONE);
        a.setAlertType(Alert.AlertType.CONFIRMATION);
        a.setTitle("Quit Dialog");
        a.setHeaderText("Logout?");
        a.setContentText("Are you sure?");
        a.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("index.fxml"));
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

    @FXML
    public void leaderboardButton(ActionEvent event) throws Exception{

    }

}

