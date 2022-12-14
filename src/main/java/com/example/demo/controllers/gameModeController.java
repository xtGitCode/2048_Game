/**
 * @author Xiao Thung Gan
 */

package com.example.demo.controllers;

import com.example.demo.GameScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.example.demo.Main.WIDTH;
import static com.example.demo.Main.HEIGHT;

/**
 * Name: gameModeController Class
 * Purpose: Manage operation of gameMode.fxml (Game Mode Interface)
 *          Let user choose their choice of theme, board size and game mode
 */
public class gameModeController implements Initializable {

    public void setGameRoot() {
    }
    public void setGameScene() {
    }

    private String mySize;      //store board size that user choose
    private String myTheme;     // store theme that user choose

    @FXML
    private ChoiceBox<String> boardSize;

    @FXML
    private ChoiceBox<String> theme;

    private final String[] size = {"Classic: 4x4", "Wide: 6x6", "Large: 8x8"};
    private final String[] color = {"Default", "Durian", "Banana", "Grape"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        boardSize.getItems().addAll(size);
        theme.getItems().addAll(color);
    }

    public void getSize(ActionEvent event){
        mySize = boardSize.getValue();
    }

    public void getTheme(ActionEvent event){
        myTheme = theme.getValue();
    }

    /**
     * Name: endlessMode
     * Purpose: Load 2038 game scene without timer, pass user choice of board size and theme
     * @param event
     */
    @FXML
    public void endlessMode(ActionEvent event) {
        boolean timer = false;      //no timer
        getSize(event);     // get user board size
        getTheme(event);    // get user theme

        //initialize variables
        int r = 0;
        int g = 0;
        int b = 0;
        int size;

        if (Objects.equals(mySize, "Classic: 4x4")) {
            size = 4;
        } else if (Objects.equals(mySize, "Wide: 6x6")) {
            size = 6;
        } else {
            size = 8;
        }

        if (Objects.equals(myTheme, "Default")){
            r = 201;
            g = 181;
            b = 135;

        }else if(Objects.equals(myTheme, "Durian")){
            r = 76;
            g = 153;
            b = 0;
        } else if (Objects.equals(myTheme, "Banana")){
            r = 201;
            g = 201;
            b = 88;
        } else if (Objects.equals(myTheme, "Grape")){
            r = 204;
            g = 0;
            b = 204;
        }

        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Group gameRoot = new Group();

        setGameRoot();
        Scene gameScene = new Scene(gameRoot, WIDTH, HEIGHT, Color.rgb(r, g, b));
        setGameScene();
        GameScene game = new GameScene();
        game.showGame(gameScene, gameRoot, timer, primaryStage, LoginController.highScore, size);
        primaryStage.setScene(gameScene);
        primaryStage.show();
    }

    /**
     * Name: timerMode
     * Purpose: Load 2038 game scene with timer, pass user choice of board size and theme
     * @param event
     */
    @FXML
    public void timerMode(ActionEvent event) {
        boolean timer = true;
        getSize(event);     // get user board size
        getTheme(event);    // get user theme

        //initialize variables
        int r = 0;
        int g = 0;
        int b = 0;
        int size;

        if (Objects.equals(mySize, "Classic: 4x4")) {
            size = 4;
        } else if (Objects.equals(mySize, "Wide: 6x6")) {
            size = 6;
        } else {
            size = 8;
        }

        if (Objects.equals(myTheme, "Default")){
            r = 201;
            g = 181;
            b = 135;

        }else if(Objects.equals(myTheme, "Durian")){
            r = 76;
            g = 153;
            b = 0;
        } else if (Objects.equals(myTheme, "Banana")){
            r = 201;
            g = 201;
            b = 88;
        } else if (Objects.equals(myTheme, "Grape")){
            r = 204;
            g = 0;
            b = 204;
        }

        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Group gameRoot = new Group();

        setGameRoot();
        Scene gameScene = new Scene(gameRoot, WIDTH, HEIGHT, Color.rgb(r, g, b));
        setGameScene();
        GameScene game = new GameScene();
        game.showGame(gameScene, gameRoot, timer, primaryStage, LoginController.highScore, size);
        primaryStage.setScene(gameScene);
        primaryStage.show();
    }
}