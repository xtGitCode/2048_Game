package com.example.demo;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class LoginController {
    public static String username;
    public String password;
    public static long highScore;
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
    public void returnMenuButton(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("index.fxml"));
        Scene indexScene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(indexScene);
        primaryStage.show();
    }

    @FXML
    public void signupAccButton(ActionEvent event) throws Exception{
        if (enterUsername.getText().isBlank() == false && enterPassword.getText().isBlank() == false){
            username = enterUsername.getText().toString();
            password = enterPassword.getText().toString();
            if (Account.accountHaveBeenExist(username)==null){ //unique account
                {Account.makeNewAccount(username,password,0);} // add new account
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
            username = enterUsername.getText().toString();
            password = enterPassword.getText().toString();
            if (Account.accountHaveBeenExist(username)==null){ //no record
                loginMessageLabel.setText("Incorrect username. Please try again.");
            } else{
                if (Account.accountHaveBeenExist(username).getPassword().equals(password)){
                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("index.fxml"));
                    highScore = Account.accountHaveBeenExist(username).getScore();
                    Scene indexScene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
                    Controller Controller = fxmlLoader.getController();
                    Controller.welcomeLabel.setText("Welcome " + username + "!");
                    Controller.loginButton.setVisible(false);
                    Controller.logoutButton.setVisible(true);
                    Controller.playButton.setText("Play");
                    Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    primaryStage.setScene(indexScene);
                    primaryStage.show();
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

