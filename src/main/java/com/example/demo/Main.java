package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Scanner;

public class Main extends Application {
    public static final int WIDTH = 780;
    static final int HEIGHT = 780; //gui size (fixed from 900)
    private Group gameRoot = new Group();
    private Scene gameScene = new Scene(gameRoot, WIDTH, HEIGHT, Color.rgb(189, 177, 92));
    private static Scanner input= new Scanner(System.in);

    public void setGameScene(Scene gameScene) {
        this.gameScene = gameScene;
    }
    public void setGameRoot(Group gameRoot) {
        this.gameRoot = gameRoot;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Account.createdAccounts("users.txt"); //Initialize existing accounts when program starts
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/index.fxml"));
        Scene menuScene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        Group gameRoot = new Group();
        setGameRoot(gameRoot);
        Scene gameScene = new Scene(gameRoot, WIDTH, HEIGHT, Color.rgb(189, 177, 92)); //bg color game
        Group menuRoot = new Group();
        Group accountRoot = new Group();
        Scene accountScene = new Scene(accountRoot, WIDTH, HEIGHT, Color.rgb(150, 20, 100, 0.2));
        Group getAccountRoot = new Group();
        Scene getAccountScene = new Scene(getAccountRoot, WIDTH, HEIGHT, Color.rgb(200, 20, 100, 0.2));
        FXMLLoader endGameLoader = new FXMLLoader(Main.class.getResource("fxml/endGame.fxml"));
        Scene endGameScene = new Scene(endGameLoader.load(),WIDTH,HEIGHT);

        primaryStage.setTitle("2048");
        primaryStage.setScene(menuScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
