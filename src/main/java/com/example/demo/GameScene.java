/**
 * @author Xiao Thung Gan - modified
 */

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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.*;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.demo.Main.HEIGHT;
import static com.example.demo.Main.WIDTH;

/**
 * Manages the interface of the game.
 * Display and manage elements like score, timer, text, labels
 */
public class GameScene {
    private static int counter = 30;        //counter for timer mode
    private static long score = 0;       //initialize score to 0 when game starts

    public static long getScore() {
        return score;
    }

    public static void setScore(long score) {
        GameScene.score = score;
    }

    /**
     * Updates score to database (text file)
     * if current score is bigger than past high score
     * @param oldScore score stored in database
     * @param newScore current score in game
     * @throws IOException
     */
    public void updateScore(long oldScore, long newScore) throws IOException {
        if (newScore > oldScore) {
            File file = new File("users.txt");
            String data = "";
            String stringToReplace = LoginController.username + "," + oldScore;
            String replaceWith = LoginController.username + "," + newScore;
            String line;
            Scanner s = new Scanner(new File("users.txt"));     //read text file
            while (s.hasNextLine()) {
                line = s.nextLine();        //store string in file to line
                if (line.contains(stringToReplace)) {
                    line = line.replace(stringToReplace, replaceWith);      //replace line
                }
                data = data.concat(line + "\n");        //store line in data string
            }
            s.close();
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            out.write(data);        //write all data back in file
            out.close();
        }
    }

    /**
     *  This is the main game loop.
     *          display and operate timer,
     *          display all score, high score texts and save score button
     *          add key event input handler to scene
     *              valid moves are:
     *                  UP arrow - move up
     *                  DOWN arrow - move down
     *                  RIGHT arrow - move right
     *                  LEFT arrow - move left
     *                  ESC - quit to menu
     *
     *          manage win, lose or have empty cells condition
     *              win when 2048 is achieved, user can choose to continue or end game
     *              lose when board is filled and no move can be made, end game scene will show
     *              have empty cells then spawn random cells
     *
     * @param gameScene scene for game
     *                  background color and window size initialized in gameModeController
     * @param root root for game
     * @param timerMode whether user chose timer mode or not
     * @param primaryStage current stage
     * @param highScore high score stored in user account
     * @param size user board size choice
     * Return: void
     */
    public void showGame(Scene gameScene, Group root, boolean timerMode, Stage primaryStage, long highScore, int size) {
        Board BOARD = new Board(size, root);        //create board object to generate board and cells

        //implement timer
        if (timerMode) {
            Text timerText = new Text();
            root.getChildren().add(timerText);
            timerText.setFont(new Font("Montserrat SemiBold", 35));
            timerText.setFill(Color.RED);
            timerText.relocate(350, 70);

            Timer timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> {
                        timerText.setText(counter + "");
                        --counter;      //counter - 1 every second
                        if (counter == 0) {         //if counter becomes 0, game ends
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
            timer.schedule(timerTask, 0, 1000);     //runs task every second
        }

        //add following elements to interface
        Text text = new Text();
        root.getChildren().add(text);
        text.setText("SCORE :");
        text.setFont(new Font("Montserrat SemiBold", 30));
        text.relocate(90, 90);
        Text scoreText = new Text();
        root.getChildren().add(scoreText);
        scoreText.relocate(225, 106);
        scoreText.setFont(new Font("Montserrat SemiBold", 30));
        scoreText.setText("0");
        Text highScoreText = new Text();
        highScoreText.relocate(505, 106);
        highScoreText.setFont(new Font("Montserrat SemiBold", 30));
        highScoreText.setText("BEST: " + highScore);
        root.getChildren().add(highScoreText);
        Text menuText = new Text();
        root.getChildren().add(menuText);
        menuText.relocate(90, 30);
        menuText.setFont(new Font("Montserrat SemiBold", 18));
        menuText.setText("Press 'ESC' for main menu");

        Button saveButton = new Button();
        saveButton.setFocusTraversable(false);
        root.getChildren().add(saveButton);
        saveButton.setText("Save Score");
        saveButton.setFont(new Font("Montserrat SemiBold", 20));
        saveButton.relocate(500, 25);
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    updateScore(highScore, score);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Alert a = new Alert(Alert.AlertType.NONE);
                a.setAlertType(Alert.AlertType.INFORMATION);
                a.setHeaderText("Score Saved");
                a.show();
            }
        });

        //add key event handler to game scene
        gameScene.addEventHandler(KeyEvent.KEY_PRESSED, key -> {
            Platform.runLater(() -> {
                int haveEmptyCell;
                boolean isValid = false; //to check whether key pressed is valid
                if (key.getCode() == KeyCode.DOWN) {
                    isValid = true;
                    BOARD.moveDown();
                } else if (key.getCode() == KeyCode.UP) {
                    isValid = true;
                    BOARD.moveUp();
                } else if (key.getCode() == KeyCode.LEFT) {
                    isValid = true;
                    BOARD.moveLeft();
                } else if (key.getCode() == KeyCode.RIGHT) {
                    isValid = true;
                    BOARD.moveRight();
                } else if (key.getCode() == KeyCode.ESCAPE) {
                    Alert a = new Alert(Alert.AlertType.NONE);
                    a.setAlertType(Alert.AlertType.CONFIRMATION);
                    a.setTitle("Return");
                    a.setHeaderText("Quit to main menu and lose current progress?");
                    a.setContentText("Are you sure?");
                    a.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK) {
                            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/index.fxml"));
                            Scene indexScene = null;
                            try {
                                indexScene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            primaryStage.setScene(indexScene);
                            primaryStage.show();
                        }
                    });
                }

                //if key pressed is valid then change score text and check for empty cell, win or lose condition
                if (isValid) {
                    scoreText.setText(score + ""); //scoring
                    if (score > highScore) {
                        highScoreText.setText("BEST: " + score + "");
                        LoginController.highScore = highScore;
                    }
                    haveEmptyCell = BOARD.haveEmptyCell();
                    switch (haveEmptyCell) {
                        case -1:        //no empty cell found
                            if (BOARD.canNotMove()) { //no movement left then end game
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
                            break;
                        case 0:     //2048 cell found then win game
                            Alert a = new Alert(Alert.AlertType.NONE);
                            a.setAlertType(Alert.AlertType.CONFIRMATION);
                            a.getButtonTypes().clear();
                            a.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
                            a.setTitle("Win Game");
                            a.setHeaderText("You Scored 2048!");
                            a.setContentText("Continue playing?");
                            a.showAndWait().ifPresent(response -> {
                                if (response == ButtonType.NO) {
                                    Platform.exit();
                                }
                            });

                    }

                }
            });

        });


    }
}