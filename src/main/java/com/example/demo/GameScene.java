package com.example.demo;

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

class GameScene {
    private final static int distanceBetweenCells = 10;
    private static int HEIGHT = 620; //size of tiles
    private static int n = 8;
    private static double LENGTH = (HEIGHT - ((n + 1) * distanceBetweenCells)) / (double) n;
    private TextMaker textMaker = TextMaker.getSingleInstance();
    private Cell[][] cells = new Cell[n][n];
    private Group root;
    private long score = 0;
    static int counter = 30;

    static void setN(int number) {
        n = number;
        LENGTH = (HEIGHT - ((n + 1) * distanceBetweenCells)) / (double) n;
    }

    static double getLENGTH() {
        return LENGTH;
    }

    private void randomFillNumber(int turn) {
        Cell[][] emptyCells = new Cell[n][n];
        int a = 0;
        int b = 0;
        int aForBound=0,bForBound=0;
        outer:
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (cells[i][j].getNumber() == 0) { //check if cells are empty
                    emptyCells[a][b] = cells[i][j];
                    if (b < n-1) {
                        bForBound=b;
                        b++;

                    } else {
                        aForBound=a;
                        a++;
                        b = 0;
                        if(a==n)
                            break outer;
                    }
                }
            }
        }

        Text text;
        Random random = new Random();
        boolean putTwo = true;
        if (random.nextInt() % 2 == 0)
            putTwo = false;
        int xCell, yCell;
            xCell = random.nextInt(aForBound+1);
            yCell = random.nextInt(bForBound+1);
        if (putTwo) {
            text = textMaker.madeText("2", emptyCells[xCell][yCell].getX(), emptyCells[xCell][yCell].getY(), root);
            emptyCells[xCell][yCell].setTextClass(text);
            root.getChildren().add(text);
            emptyCells[xCell][yCell].setColorByNumber(2);
        } else {
            text = textMaker.madeText("4", emptyCells[xCell][yCell].getX(), emptyCells[xCell][yCell].getY(), root);
            emptyCells[xCell][yCell].setTextClass(text);
            root.getChildren().add(text);
            emptyCells[xCell][yCell].setColorByNumber(4);
        }
    }

    private int haveEmptyCell() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (cells[i][j].getNumber() == 0)
                    return 1; //empty
                if(cells[i][j].getNumber() == 2048)
                    return 0; //win game
            }
        }
        return -1; //lose game
    }

    private void spawnRandom(){
        int haveEmptyCell;
        haveEmptyCell = GameScene.this.haveEmptyCell();
        if(haveEmptyCell==1) {
            GameScene.this.randomFillNumber(2);
        }
    }

    private int passDestination(int i, int j, char direct) {
        int coordinate = j;
        if (direct == 'l') {
            for (int k = j - 1; k >= 0; k--) {
                if (cells[i][k].getNumber() != 0) {
                    coordinate = k + 1;
                    break;
                } else if (k == 0) {
                    coordinate = 0;
                }
            }
            return coordinate;
        }
        coordinate = j;
        if (direct == 'r') {
            for (int k = j + 1; k <= n - 1; k++) {
                if (cells[i][k].getNumber() != 0) {
                    coordinate = k - 1;
                    break;
                } else if (k == n - 1) {
                    coordinate = n - 1;
                }
            }
            return coordinate;
        }
        coordinate = i;
        if (direct == 'd') {
            for (int k = i + 1; k <= n - 1; k++) {
                if (cells[k][j].getNumber() != 0) {
                    coordinate = k - 1;
                    break;

                } else if (k == n - 1) {
                    coordinate = n - 1;
                }
            }
            return coordinate;
        }
        coordinate = i;
        if (direct == 'u') {
            for (int k = i - 1; k >= 0; k--) {
                if (cells[k][j].getNumber() != 0) {
                    coordinate = k + 1;
                    break;
                } else if (k == 0) {
                    coordinate = 0;
                }
            }
            return coordinate;
        }
        return -1;
    }

    private void moveLeft() {
        int moved = 0; // moved var to check whether cells moved
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j++) {
                if (cells[i][j].getNumber() != 0) { //added this if loop to ignore empty cells
                    moved += moveHorizontally(i, j, passDestination(i, j, 'l'), -1);
                }
            }
            for (int j = 0; j < n; j++) {
                cells[i][j].setModify(false);
            }
        }
        if(moved>0){ // if any cells moved, spawn random new cell
            spawnRandom();
        }
    }

    private void moveRight() {
        int moved = 0;
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j >= 0; j--) {
                if (cells[i][j].getNumber() != 0) {
                    moved += moveHorizontally(i, j, passDestination(i, j, 'r'), 1);
                }
            }
            for (int j = 0; j < n; j++) {
                cells[i][j].setModify(false);
            }
        }
        if(moved>0){
            spawnRandom();
        }
    }

    private void moveUp() {
        int moved = 0;
        for (int j = 0; j < n; j++) {
            for (int i = 1; i < n; i++) {
                if (cells[i][j].getNumber() != 0) {
                    moved += moveVertically(i, j, passDestination(i, j, 'u'), -1);
                }
            }
            for (int i = 0; i < n; i++) {
                cells[i][j].setModify(false);
            }
        }
        if(moved>0){
            spawnRandom();
        }

    }

    private void moveDown() {
        int moved = 0;
        for (int j = 0; j < n; j++) {
            for (int i = n - 1; i >= 0; i--) {
                if (cells[i][j].getNumber() != 0) {
                    moved += moveVertically(i, j, passDestination(i, j, 'd'), 1);
                }
            }
            for (int i = 0; i < n; i++) {
                cells[i][j].setModify(false);
            }
        }
        if(moved>0){
            spawnRandom();
        }

    }

    private boolean isValidDesH(int i, int j, int des, int sign) {
        if (des + sign < n && des + sign >= 0) {
            if (cells[i][des + sign].getNumber() == cells[i][j].getNumber() && !cells[i][des + sign].getModify()
                    && cells[i][des + sign].getNumber() != 0) {
                return true;
            }
        }
        return false;
    }

    private int moveHorizontally(int i, int j, int des, int sign) {
        if (isValidDesH(i, j, des, sign)) {
            GameScene.this.sumCellNumbersToScore(i,j); //insert here so when only cells merge, score is calculated
            cells[i][j].adder(cells[i][des + sign]);
            cells[i][des].setModify(true);
            return 1;
        } else if (des != j) {
            cells[i][j].changeCell(cells[i][des]);
            return 1;
        }
        return 0;
    }

    private boolean isValidDesV(int i, int j, int des, int sign) {
        if (des + sign < n && des + sign >= 0)
            if (cells[des + sign][j].getNumber() == cells[i][j].getNumber() && !cells[des + sign][j].getModify()
                    && cells[des + sign][j].getNumber() != 0) {
                return true;
            }
        return false;
    }

    private int moveVertically(int i, int j, int des, int sign) {
        if (isValidDesV(i, j, des, sign)) {
            GameScene.this.sumCellNumbersToScore(i,j);
            cells[i][j].adder(cells[des + sign][j]);
            cells[des][j].setModify(true);
            return 1; // to check whether any cells moved
        } else if (des != i) {
            cells[i][j].changeCell(cells[des][j]);
            return 1;
        }
        return 0;
    }

    private boolean haveSameNumber(int i, int j) {
        if (i < n - 1 && j < n - 1) {
            if (cells[i + 1][j].getNumber() == cells[i][j].getNumber())
                return true;
            if (cells[i][j + 1].getNumber() == cells[i][j].getNumber())
                return true;
        }
        return false;
    }

    private boolean canNotMove() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (haveSameNumber(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    private void sumCellNumbersToScore(int i, int j) {
        long cellNumber = cells[i][j].getNumber();
        score += cellNumber * 2;
    }

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

    void game(Scene gameScene, Group root, Stage primaryStage, Scene menuScene, long highScore, int size, boolean timerMode) {
        this.root = root;
        setN(size);
        double scale;
        if (n==4){
            scale = 1;
        } else if (n==6){
            scale = 1.5;
        } else{
            scale = 2;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cells[i][j] = new Cell((j + 0.5) * LENGTH + (j + 1) * distanceBetweenCells,
                        (i + scale) * LENGTH + (i + 1) * distanceBetweenCells, LENGTH, root);
            }
        }
        long oldScore = highScore;

        if (timerMode == true){
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
                            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("endGame.fxml"));
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
                    updateScore(oldScore,score);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Alert a = new Alert(Alert.AlertType.NONE);
                a.setAlertType(Alert.AlertType.INFORMATION);
                a.setHeaderText("Score Saved");
                a.show();
            }
        });

        randomFillNumber(1);
        randomFillNumber(1);

        gameScene.addEventHandler(KeyEvent.KEY_PRESSED, key ->{
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
                                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("endGame.fxml"));
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
            });
    }
}
