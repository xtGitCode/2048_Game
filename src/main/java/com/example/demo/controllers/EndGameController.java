package com.example.demo.controllers;

import com.example.demo.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.demo.controllers.Controller.HEIGHT;
import static com.example.demo.controllers.Controller.WIDTH;


public class EndGameController {
    @FXML
    public Label curScore;

    @FXML
    public Label bestScore;

    public static EndGameController singleInstance = null;
    public EndGameController(){

    }
    public static EndGameController getInstance(){
        if(singleInstance == null)
            singleInstance= new EndGameController();
        return singleInstance;
    }

    @FXML
    public void returnMenu(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/index.fxml"));
        Scene indexScene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(indexScene);
        primaryStage.show();
    }

}
