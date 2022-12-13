package com.example.demo.controllers;

import com.example.demo.GameScene;
import com.example.demo.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.demo.Main.WIDTH;
import static com.example.demo.controllers.Controller.HEIGHT;

public class gameModeController implements Initializable {

    private Group gameRoot = new Group();
    private Scene gameScene = new Scene(gameRoot, 900, HEIGHT, Color.rgb(189, 177, 92));

    public void setGameRoot(Group gameRoot) {
        this.gameRoot = gameRoot;
    }
    public void setGameScene(Scene gameScene) {
        this.gameScene = gameScene;
    }

    private String mySize;
    @FXML
    private ChoiceBox<String> boardSize;

    private String[] size = {"Classic: 4x4","Wide: 6x6","Large: 8x8"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        boardSize.getItems().addAll(size);
    }

    public void getSize(ActionEvent event){
        mySize = boardSize.getValue();
    }

    @FXML
    public void endlessMode(ActionEvent event) throws IOException {
        boolean timer = false;
        getSize(event);
        int size;
        if (mySize == "Classic: 4x4"){
            size = 4;
        } else if (mySize == "Wide: 6x6"){
            size = 6;
        } else {
            size = 8;
        }
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/index.fxml"));
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Group gameRoot = new Group();

        setGameRoot(gameRoot);
        Scene gameScene = new Scene(gameRoot, WIDTH, HEIGHT, Color.rgb(189, 177, 92));
        setGameScene(gameScene);
        GameScene game = new GameScene();
        game.show(gameScene, gameRoot, timer, primaryStage, LoginController.highScore, size);
        primaryStage.setScene(gameScene);
        primaryStage.show();
    }

    @FXML
    public void timerMode(ActionEvent event) throws IOException{
        boolean timer = true;
        getSize(event);
        int size;
        if (mySize == "Classic: 4x4"){
            size = 4;
        } else if (mySize == "Wide: 6x6"){
            size = 6;
        } else {
            size = 8;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/index.fxml"));
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Group gameRoot = new Group();

        setGameRoot(gameRoot);
        Scene gameScene = new Scene(gameRoot, WIDTH, HEIGHT, Color.rgb(189, 177, 92));
        setGameScene(gameScene);
        GameScene game = new GameScene();
        game.show(gameScene, gameRoot, timer, primaryStage, LoginController.highScore, size);
        primaryStage.setScene(gameScene);
        primaryStage.show();
    }
}