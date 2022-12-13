package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static final int WIDTH = 780;
    public static final int HEIGHT = 780;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Account.createdAccounts("users.txt");       //Initialize existing accounts when program starts

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/index.fxml"));
        Scene menuScene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);

        primaryStage.setTitle("2048");      //Display main menu first when program runs
        primaryStage.setScene(menuScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
