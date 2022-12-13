package com.example.demo;

import com.example.demo.controllers.EndGameController;
import com.example.demo.controllers.LoginController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class GameScene {
    static int counter = 30;
    private long score = 0;


    public void updateScore(long oldScore, long newScore) throws IOException {
        if (newScore > oldScore) {
            File file = new File("users.txt");
            String data = "";
            String stringToReplace = LoginController.username + "," + oldScore;
            String replaceWith = LoginController.username + "," + newScore;
            String line;
            Scanner s = new Scanner(new File("users.txt"));
            while (s.hasNextLine()) {
                line = s.nextLine();
                if (line.contains(stringToReplace))
                {
                    line = line.replace(stringToReplace, replaceWith);
                }
                data = data.concat(line + "\n");
            }
            s.close();
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            out.write(data);
            out.close();
        }
    }

    public void show(Group root, boolean timerMode, Stage primaryStage, long highScore, int size){
        Board BOARD = new Board(size, root);
        if (timerMode){
            Text timerText = new Text();
            root.getChildren().add(timerText);
            timerText.setFont(new Font("Montserrat SemiBold",30));
            timerText.relocate(400, 30);

            Timer timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> {
                        timerText.setText(counter + "");
                        --counter;
                        if (counter == 0) {
                            timer.cancel();
                            try {
                                updateScore(highScore, score);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Alert a = new Alert(Alert.AlertType.NONE);
                            a.setAlertType(Alert.AlertType.INFORMATION);
                            a.setHeaderText("Score Saved");
                            a.show();
                            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/endGame.fxml"));
                            Scene endScene = null;
                            try {
                                endScene = new Scene(fxmlLoader.load(), 780, 780);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            EndGameController endController = fxmlLoader.getController();
                            endController.curScore.setText(score + "");
                            endController.bestScore.setText(highScore + "");
                            primaryStage.setScene(endScene);
                            primaryStage.show();
                            root.getChildren().clear();
                            score = 0;
                        }
                    });
                }
            };
            timer.schedule(timerTask,0,1000);
        }

        Text text = new Text();
        root.getChildren().add(text);
        text.setText("SCORE :");
        text.setFont(new Font("Montserrat SemiBold",30));
        text.relocate(90, 90);
        Text scoreText = new Text();
        root.getChildren().add(scoreText);
        scoreText.relocate(225, 106);
        scoreText.setFont(new Font("Montserrat SemiBold",30));
        scoreText.setText("0");
        Text highScoreText = new Text();
        highScoreText.relocate(505, 106);
        highScoreText.setFont(new Font("Montserrat SemiBold",30));
        highScoreText.setText("BEST: " + highScore);
        root.getChildren().add(highScoreText);
        Text menuText = new Text();
        root.getChildren().add(menuText);
        menuText.relocate(90, 30);
        menuText.setFont(new Font("Montserrat SemiBold",18));
        menuText.setText("Press 'ESC' for main menu");

        Button saveButton = new Button();
        saveButton.setFocusTraversable(false);
        root.getChildren().add(saveButton);
        saveButton.setText("Save Score");
        saveButton.setFont(new Font("Montserrat SemiBold",20));
        saveButton.relocate(500,25);
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    updateScore(highScore,score);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Alert a = new Alert(Alert.AlertType.NONE);
                a.setAlertType(Alert.AlertType.INFORMATION);
                a.setHeaderText("Score Saved");
                a.show();
            }
        });
    }


}
