/**
 * @author Xiao Thung Gan
 */
package com.example.demo.controllers;

import com.example.demo.Account;
import com.example.demo.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import static com.example.demo.Main.HEIGHT;
import static com.example.demo.Main.WIDTH;

/**
 * Name: LoginController Class
 * Purpose: Manage operation of login.fxml and signup.fxml
 */
public class LoginController {
    public static String username;
    public String password;
    public static long highScore;

    @FXML
    private Label loginMessageLabel;
    @FXML
    private Label signupMessageLabel;
    @FXML
    private TextField enterUsername;
    @FXML
    private TextField enterPassword;

    /**
     * Name: returnMenuButton
     * Purpose: load main menu page and display
     * @param event
     * @throws Exception
     */
    @FXML
    public void returnMenuButton(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/index.fxml"));
        Scene indexScene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(indexScene);
        primaryStage.show();
    }

    /**
     * Name: signupAccButton
     * Purpose: create and store account if username not in database record
     * @throws Exception
     */
    @FXML
    public void signupAccButton() throws Exception{
        if (!enterUsername.getText().isBlank() && !enterPassword.getText().isBlank()){      //make sure both fields are filled
            username = enterUsername.getText().toString();
            password = enterPassword.getText().toString();
            if (Account.accountHaveBeenExist(username)==null){      //unique account
                {Account.makeNewAccount(username,password,0);}      // add new account
                signupMessageLabel.setText("Sign up successful! Please login now");
            } else{
                signupMessageLabel.setText("Username taken! Please enter another username.");
            }
        }else {
            signupMessageLabel.setText("Please enter username and password");
        }
    }

    /**
     * Name: loginAccButton
     * Purpose: check if username and password is correct, then load main menu and display for user
     * @param event
     * @throws Exception
     */
    @FXML
    public void loginAccButton(ActionEvent event) throws Exception{
        if (!enterUsername.getText().isBlank() && !enterPassword.getText().isBlank()){
            username = enterUsername.getText().toString();
            password = enterPassword.getText().toString();
            if (Account.accountHaveBeenExist(username)==null){ //no record
                loginMessageLabel.setText("Incorrect username. Please try again.");
            } else{
                if (Account.accountHaveBeenExist(username).getPassword().equals(password)){
                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/index.fxml"));
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

    /**
     * Name: switchSignUp
     * Purpose: switch scene to sign up page
     * @param event
     * @throws Exception
     */
    @FXML
    public void switchSignUp(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/signup.fxml"));
        Scene signupScene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(signupScene);
        primaryStage.show();
    }

    /**
     * Name: switchLogin
     * Purpose: switch scene to login page
     * @param event
     * @throws Exception
     */
    @FXML
    public void switchLogin(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/login.fxml"));
        Scene loginScene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }




}

