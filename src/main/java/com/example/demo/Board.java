package com.example.demo;

import javafx.scene.Group;
import javafx.scene.text.Text;

import java.util.Random;

public class Board {
    private final Group root;
    private static int boardSize=8;
    private final static int distanceBetweenCells = 10;
    private static final int cellHEIGHT = 620; //size of tiles
    private Cell[][] cells = new Cell[boardSize][boardSize];
    public static double LENGTH = (cellHEIGHT - ((boardSize + 1) * distanceBetweenCells)) / (double) boardSize;
    double scale; // change board position based on size

    //Constructor
    public Board(int boardSize, Group root) {
        Board.boardSize = boardSize;
        LENGTH = (cellHEIGHT - ((boardSize + 1) * distanceBetweenCells)) / (double) boardSize;
        this.root = root;
        System.out.println(boardSize);
        start();
    }

    private void start(){
        initialize();
        randomFillNumber();
        randomFillNumber();
    }

    public void initialize() {
        if (boardSize==4){
            scale = 1;
        } else if (boardSize==6){
            scale = 1.5;
        } else{
            scale = 2;
        }

        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                cells[row][col] = new Cell((col + 0.5) * LENGTH + (col + 1) * distanceBetweenCells,
                        (row + scale) * LENGTH + (row + 1) * distanceBetweenCells, LENGTH, root);
            }
        }
    }

    int haveEmptyCell() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (cells[i][j].getNumber() == 0)
                    return 1; //empty
                if(cells[i][j].getNumber() == 2048)
                    return 0; //win game
            }
        }
        return -1; //lose game
    }

    private void randomFillNumber() {
        Cell[][] emptyCells = new Cell[boardSize][boardSize];
        int a = 0;
        int b = 0;
        int aForBound=0,bForBound=0;
        outer:
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (cells[i][j].getNumber() == 0) { //check if cells are empty
                    emptyCells[a][b] = cells[i][j];
                    if (b < boardSize-1) {
                        bForBound=b;
                        b++;

                    } else {
                        aForBound=a;
                        a++;
                        b = 0;
                        if(a==boardSize)
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
            text = TextMaker.madeText("2", emptyCells[xCell][yCell].getX(), emptyCells[xCell][yCell].getY(), root);
            emptyCells[xCell][yCell].setTextClass(text);
            root.getChildren().add(text);
            emptyCells[xCell][yCell].setColorByNumber(2);
        } else {
            text = TextMaker.madeText("4", emptyCells[xCell][yCell].getX(), emptyCells[xCell][yCell].getY(), root);
            emptyCells[xCell][yCell].setTextClass(text);
            root.getChildren().add(text);
            emptyCells[xCell][yCell].setColorByNumber(4);
        }
    }

    private void spawnRandom(){
        int haveEmptyCell;
        haveEmptyCell = haveEmptyCell();
        if(haveEmptyCell==1) {
            randomFillNumber();
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
            for (int k = j + 1; k <= boardSize - 1; k++) {
                if (cells[i][k].getNumber() != 0) {
                    coordinate = k - 1;
                    break;
                } else if (k == boardSize - 1) {
                    coordinate = boardSize - 1;
                }
            }
            return coordinate;
        }
        coordinate = i;
        if (direct == 'd') {
            for (int k = i + 1; k <= boardSize - 1; k++) {
                if (cells[k][j].getNumber() != 0) {
                    coordinate = k - 1;
                    break;

                } else if (k == boardSize - 1) {
                    coordinate = boardSize - 1;
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

    void moveLeft() {
        int moved = 0; // moved var to check whether cells moved
        for (int i = 0; i < boardSize; i++) {
            for (int j = 1; j < boardSize; j++) {
                if (cells[i][j].getNumber() != 0) { //added this if loop to ignore empty cells
                    moved += moveHorizontally(i, j, passDestination(i, j, 'l'), -1);
                }
            }
            for (int j = 0; j < boardSize; j++) {
                cells[i][j].setModify(false);
            }
        }
        if(moved>0){ // if any cells moved, spawn random new cell
            spawnRandom();
        }
    }

    void moveRight() {
        int moved = 0;
        for (int i = 0; i < boardSize; i++) {
            for (int j = boardSize - 1; j >= 0; j--) {
                if (cells[i][j].getNumber() != 0) {
                    moved += moveHorizontally(i, j, passDestination(i, j, 'r'), 1);
                }
            }
            for (int j = 0; j < boardSize; j++) {
                cells[i][j].setModify(false);
            }
        }
        if(moved>0){
            spawnRandom();
        }
    }

    void moveUp() {
        int moved = 0;
        for (int j = 0; j < boardSize; j++) {
            for (int i = 1; i < boardSize; i++) {
                if (cells[i][j].getNumber() != 0) {
                    moved += moveVertically(i, j, passDestination(i, j, 'u'), -1);
                }
            }
            for (int i = 0; i < boardSize; i++) {
                cells[i][j].setModify(false);
            }
        }
        if(moved>0){
            spawnRandom();
        }

    }

    public void moveDown() {
        int moved = 0;
        for (int j = 0; j < boardSize; j++) {
            for (int i = boardSize - 1; i >= 0; i--) {
                if (cells[i][j].getNumber() != 0) {
                    moved += moveVertically(i, j, passDestination(i, j, 'd'), 1);
                }
            }
            for (int i = 0; i < boardSize; i++) {
                cells[i][j].setModify(false);
            }
        }
        if(moved>0){
            spawnRandom();
        }

    }

    private boolean isValidDesH(int i, int j, int des, int sign) {
        if (des + sign < boardSize && des + sign >= 0) {
            if (cells[i][des + sign].getNumber() == cells[i][j].getNumber() && !cells[i][des + sign].getModify()
                    && cells[i][des + sign].getNumber() != 0) {
                return true;
            }
        }
        return false;
    }

    private int moveHorizontally(int i, int j, int des, int sign) {
        if (isValidDesH(i, j, des, sign)) {
            sumCellNumbersToScore(i,j); //insert here so when only cells merge, score is calculated
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
        if (des + sign < boardSize && des + sign >= 0)
            if (cells[des + sign][j].getNumber() == cells[i][j].getNumber() && !cells[des + sign][j].getModify()
                    && cells[des + sign][j].getNumber() != 0) {
                return true;
            }
        return false;
    }

    private int moveVertically(int i, int j, int des, int sign) {
        if (isValidDesV(i, j, des, sign)) {
            sumCellNumbersToScore(i,j);
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
        if (i < boardSize - 1 && j < boardSize - 1) {
            if (cells[i + 1][j].getNumber() == cells[i][j].getNumber())
                return true;
            if (cells[i][j + 1].getNumber() == cells[i][j].getNumber())
                return true;
        }
        return false;
    }

    boolean canNotMove() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (haveSameNumber(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    private void sumCellNumbersToScore(int i, int j) {
        long cellNumber = cells[i][j].getNumber();
        GameScene.score += cellNumber * 2;
    }
}
