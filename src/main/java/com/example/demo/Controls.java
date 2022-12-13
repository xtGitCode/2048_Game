package com.example.demo;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import static javafx.scene.input.KeyCode.*;

public class Controls{
   /* int haveEmptyCell;
    boolean isValid = false; //to check whether key pressed is valid

    public void keyPressed(KeyEvent e){

        KeyCode keyCode = e.getCode();

        switch (keyCode){
            case DOWN:
                isValid = true;
                GameScene.moveDown();
                break;
            case UP:
                isValid = true;
                GameScene.moveUp();
                break;
            case RIGHT:
                isValid = true;
                GameScene.moveRight();
                break;
            case LEFT:
                isValid = true;
                GameScene.moveLeft();
                break;
            case ESCAPE:
                Alert a = new Alert(Alert.AlertType.NONE);
                a.setAlertType(Alert.AlertType.CONFIRMATION);
                a.setTitle("Return");
                a.setHeaderText("Quit to main menu and lose current progress?");
                a.setContentText("Are you sure?");
                a.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        Stage primaryStage = (Stage) KeyEvent.getScene().getWindow();
                        primaryStage.setScene(menuScene);
                    }
                });
            default:
                break;
        }
    }

    /*gameScene.addEventHandler(KeyEvent.KEY_PRESSED, key ->{
        Platform.runLater(() -> {
            int haveEmptyCell;
            boolean isValid = false; //to check whether key pressed is valid
            if (key.getCode() == KeyCode.DOWN) {
                isValid = true;
                GameScene.this.moveDown();
            } else if (key.getCode() == KeyCode.UP) {
                isValid = true;
                GameScene.this.moveUp();
            } else if (key.getCode() == KeyCode.LEFT) {
                isValid = true;
                GameScene.this.moveLeft();
            } else if (key.getCode() == KeyCode.RIGHT) {
                isValid = true;
                GameScene.this.moveRight();
            }
            if (isValid) {
                scoreText.setText(score + ""); //scoring
                if(score>highScore){
                    highScoreText.setText("BEST: " + score + "");
                    LoginController.highScore = highScore;
                }
                haveEmptyCell = GameScene.this.haveEmptyCell();
                if (haveEmptyCell == -1) {
                    if (GameScene.this.canNotMove()) {
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
                }
            }
            if (key.getCode() == KeyCode.ESCAPE){
                Alert a = new Alert(Alert.AlertType.NONE);
                a.setAlertType(Alert.AlertType.CONFIRMATION);
                a.setTitle("Return");
                a.setHeaderText("Quit to main menu and lose current progress?");
                a.setContentText("Are you sure?");
                a.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        primaryStage.setScene(menuScene);
                    }
                });
            }
        });
    });*/
}
