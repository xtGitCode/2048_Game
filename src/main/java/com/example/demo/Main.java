/**
 * @author Xiao Thung Gan - modified
 */
package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Main class starts program by loading main menu to interface
 */
public class Main extends Application {
    public static final int WIDTH = 780;
    public static final int HEIGHT = 780;

    /**
     * starts program by playing music and loading main menu to stage
     * @param primaryStage main stage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        //Play background music when program runs
        Media sound;
        sound = new Media(Objects.requireNonNull(getClass().getResource("Sounds/HappyParrotMusic.mp3")).toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();

        Account.createdAccounts("users.txt");       //Initialize existing accounts when program starts

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/index.fxml"));  //load main menu fxml
        Scene menuScene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        primaryStage.setTitle("2048");      //Display main menu first when program runs
        primaryStage.setScene(menuScene);
        primaryStage.show();
    }

    /**
     * launches program
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
