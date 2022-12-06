package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;

public class LoginController {
    static final int WIDTH = 780;
    static final int HEIGHT = 780;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private Label signupMessageLabel;
    @FXML
    private TextField enterUsername;
    @FXML
    private TextField enterPassword;

    @FXML
    public void cancelButton(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("index.fxml"));
        Scene indexScene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(indexScene);
        primaryStage.show();
    }

    @FXML
    public void signupAccButton(ActionEvent event) throws Exception{
        if (enterUsername.getText().isBlank() == false && enterPassword.getText().isBlank() == false){
            String username;
            String password;
            username = enterUsername.getText().toString();
            password = enterPassword.getText().toString();
            if (Account.accountHaveBeenExist(username)==null){ //unique account
                {Account.makeNewAccount(username,password);} // add new account
                signupMessageLabel.setText("Sign up successful! Please login now");
            } else{
                signupMessageLabel.setText("Username taken! Please enter another username.");
            }
        }else {
            signupMessageLabel.setText("Please enter username and password");
        }
    }
    @FXML
    public void loginAccButton(ActionEvent event) throws Exception{
        if (enterUsername.getText().isBlank() == false && enterPassword.getText().isBlank() == false){
            String username;
            String password;
            username = enterUsername.getText().toString();
            password = enterPassword.getText().toString();
            if (Account.accountHaveBeenExist(username)==null){
                loginMessageLabel.setText("Incorrect username. Please try again.");
            } else{
                if (Account.accountHaveBeenExist(username).getPassword().equals(password)){
                    loginMessageLabel.setText("Login Success!");
                } else{
                    loginMessageLabel.setText("Incorrect password. Please try again.");
                }
            }
        }else {
            loginMessageLabel.setText("Please enter username and password");
        }
    }

    @FXML
    public void switchSignUp(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("signup.fxml"));
        Scene signupScene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(signupScene);
        primaryStage.show();
    }

    @FXML
    public void switchLogin(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        Scene loginScene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }


}

