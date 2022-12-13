package com.example.demo.controllers;

import com.example.demo.Account;
import com.example.demo.Main;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static com.example.demo.Account.accounts;

public class leadController implements Initializable {
    static int WIDTH = 780;
    static int HEIGHT = 780;
    @FXML
    private TableView<Account> leaderboard;

    @FXML
    private TableColumn<Account, Long> scoreCol;

    @FXML
    private TableColumn<Account, String> usernameCol;

    private ObservableList<Account> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        try {
            Account.createdAccounts("users.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(Account account : accounts){
            data.add(new Account(account.getUserName(),account.getPassword(),Long.toString(account.getScore())));
        }
        usernameCol.setCellValueFactory(col ->new SimpleStringProperty(col.getValue().getUserName()));
        scoreCol.setCellValueFactory(col ->new SimpleLongProperty(col.getValue().getScore()).asObject());
        leaderboard.setItems(data);
    }

    @FXML
    public void backButton(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/index.fxml"));
        Scene indexScene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(indexScene);
        primaryStage.show();
    }
}