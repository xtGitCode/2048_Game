package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

public class gameModeController implements Initializable {
    @FXML
    private ChoiceBox<String> boardSize;

    private String[] size = {"Classic: 4x4","Wide: 6x6","Large: 8x8"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        boardSize.getItems().addAll(size);
    }

    public void getSize(ActionEvent event){
        String mySize = boardSize.getValue();
    }
}
