/**
 * @author Xiao Thung Gan
 */
package com.example.demo;

import javafx.scene.Group;
import javafx.scene.text.Text;
import javafx.scene.media.AudioClip;
import java.util.Random;

/**
 * Generate board based on board size and handles all cell operations including movement and spawning
 */
public class Board {
    private final Group root;
    private static int boardSize=8;     //size of board, initialize to 8
    private final static int distanceBetweenCells = 10;
    private static final int cellHEIGHT = 620;      //size of tiles
    private Cell[][] cells = new Cell[boardSize][boardSize];        //cells array
    private static double LENGTH = (cellHEIGHT - ((boardSize + 1) * distanceBetweenCells)) / (double) boardSize;     //cell size
    private double scale;       //change board position based on size of board
    private double isWin;         // check win condition

    public double getIsWin() {
        return isWin;
    }

    public static double getLENGTH() {
        return LENGTH;
    }

    //sound effect when cells merge together
    private final AudioClip click = new AudioClip(getClass().getResource("Sounds/cellMerge.mp3").toExternalForm());

    /**
     * Board (the constructor)
     * @param boardSize size of board (rows of board * columns of board)
     * @param root root of game
     */
    public Board(int boardSize, Group root) {
        Board.boardSize = boardSize;
        LENGTH = (cellHEIGHT - ((boardSize + 1) * distanceBetweenCells)) / (double) boardSize;
        this.root = root;
        System.out.println(boardSize);
        start();
    }

    /**
     * calls methods to generate board and spawn 2 random cells
     */
    private void start(){
        initialize();
        randomFillNumber();
        randomFillNumber();
    }

    /**
     * generate cells in board
     * Return: void
     */
    public void initialize() {
        //get scale based on board size to position in scene
        if (boardSize==4){
            scale = 1;
        } else if (boardSize==6){
            scale = 1.5;
        } else{
            scale = 2;
        }

        //generate cells
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                cells[row][col] = new Cell((col + 0.5) * LENGTH + (col + 1) * distanceBetweenCells,
                        (row + scale) * LENGTH + (row + 1) * distanceBetweenCells, LENGTH, root);
            }
        }
    }

    /**
     * check if board is in any of these conditions
     *              lose - no empty cells left in board
     *              have empty - board contains at lease one empty cell
     * @return integer (1 for empty, -1 for lose)
     */
    public int haveEmptyCell() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (cells[i][j].getNumber() == 0)
                    return 1; //empty
            }
        }
        return -1; //lose game
    }

    /**
     * get empty cells position
     *          generate cells either 2 or 4 randomly to an empty cell
     * Return: void
     */
    private void randomFillNumber() {
        Cell[][] emptyCells = new Cell[boardSize][boardSize];
        int a = 0;
        int b = 0;
        int aForBound=0,bForBound=0;
        outer:
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (cells[i][j].getNumber() == 0) {         //check if cells are empty
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
            text = TextMaker.madeText("2", emptyCells[xCell][yCell].getX(), emptyCells[xCell][yCell].getY());
            emptyCells[xCell][yCell].setTextClass(text);
            root.getChildren().add(text);
            emptyCells[xCell][yCell].setColorByNumber(2);
        } else {
            text = TextMaker.madeText("4", emptyCells[xCell][yCell].getX(), emptyCells[xCell][yCell].getY());
            emptyCells[xCell][yCell].setTextClass(text);
            root.getChildren().add(text);
            emptyCells[xCell][yCell].setColorByNumber(4);
        }
    }

    /**
     * check if empty cell exist in board
     *          if yes then call method to spawn random new cell
     * Return: void
     */
    private void spawnRandom(){
        int haveEmptyCell;
        haveEmptyCell = haveEmptyCell();
        if(haveEmptyCell==1) {
            randomFillNumber();
        }
    }

    /**
     * get coordinate for cells to move to
     *          eg. find the leftest most position for cell to move to if move left method
     * @param i cell array row position
     * @param j cell array column positin
     * @param direct direction - left(l), right(r), up(u), down(d)
     * @return coordinate for cell to move to
     */
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

    /**
     * call method and pass paramters to move cells left
     *          only move cells that have value (not empty)
     *          check if any cells moved, then call spawn new cell method
     * Return: void
     */
    void moveLeft() {
        int moved = 0;      //moved var to check whether cells moved
        for (int i = 0; i < boardSize; i++) {
            for (int j = 1; j < boardSize; j++) {
                if (cells[i][j].getNumber() != 0) {         //added if loop to ignore empty cells
                    moved += moveHorizontally(i, j, passDestination(i, j, 'l'), -1);
                }
            }
            for (int j = 0; j < boardSize; j++) {
                cells[i][j].setModify(false);
            }
        }
        if(moved>0){        //if any cells moved, spawn random new cell
            spawnRandom();
        }
    }

    /**
     * call method and pass parameters to move cells right
     *          only move cells that have value (not empty)
     *          check if any cells moved, then call spawn new cell method
     * Return: void
     */
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

    /**
     * call method and pass parameters to move cells up
     *          only move cells that have value (not empty)
     *          check if any cells moved, then call spawn new cell method
     * Return: void
     */
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

    /**
     * call method and pass parameters to move cells down
     *          only move cells that have value (not empty)
     *          check if any cells moved, then call spawn new cell method
     * Return: void
     */
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

    /**
     * Check whether moved cell can merge with other cells
     * @param i cell array row position
     * @param j cell array column position
     * @param des destination coordinaton
     * @param sign integer to check whether destination is at most side position
     * @return true if can merge
     *         false if can't merge
     */
    private boolean isValidDesH(int i, int j, int des, int sign) {
        if (des + sign < boardSize && des + sign >= 0) {
            if (cells[i][des + sign].getNumber() == cells[i][j].getNumber() && !cells[i][des + sign].getModify()
                    && cells[i][des + sign].getNumber() != 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * call functions that merge cell and change position of cell
     * @param i cell array row position
     * @param j cell array column position
     * @param des destination coordination
     * @param sign integer to check whether destination is at most side position
     * @return 1 if cells moved or merge
     *         0 if cells did not move at all
     */
    private int moveHorizontally(int i, int j, int des, int sign) {
        if (isValidDesH(i, j, des, sign)) {     //can merge
            sumCellNumbersToScore(i,j);         //only when cells merge, score is calculated
            cells[i][j].adder(cells[i][des + sign]);
            cells[i][des].setModify(true);
            if (isWin < cells[i][des + sign].getNumber()){
                isWin = cells[i][des + sign].getNumber();
            }
            return 1;
        } else if (des != j) {
            cells[i][j].changeCell(cells[i][des]);      //cells didn't merge but change position
            return 1;
        }
        return 0;       //cells did not change position
    }

    /**
     * Check whether moved cell can merge with other cells
     * @param i cell array row position
     * @param j cell array column position
     * @param des destination coordinaton
     * @param sign integer to check whether destination is at most side position
     * @return true if can merge
     *         false if can't merge
     */
    private boolean isValidDesV(int i, int j, int des, int sign) {
        if (des + sign < boardSize && des + sign >= 0)
            if (cells[des + sign][j].getNumber() == cells[i][j].getNumber() && !cells[des + sign][j].getModify()
                    && cells[des + sign][j].getNumber() != 0) {
                return true;
            }
        return false;
    }

    /**
     * call functions that merge cell and change position of cell
     * @param i cell array row position
     * @param j cell array column position
     * @param des destination coordination
     * @param sign integer to check whether destination is at most side position
     * @return 1 if cells moved or merge
     *         0 if cells did not move at all
     */
    private int moveVertically(int i, int j, int des, int sign) {
        if (isValidDesV(i, j, des, sign)) {
            sumCellNumbersToScore(i,j);
            cells[i][j].adder(cells[des + sign][j]);
            cells[des][j].setModify(true);
            if (isWin < cells[j][des + sign].getNumber()){
                isWin = cells[j][des + sign].getNumber();
            }
            return 1; // to check whether any cells moved
        } else if (des != i) {
            cells[i][j].changeCell(cells[des][j]);
            return 1;
        }
        return 0;
    }

    /**
     * check if any same number cell is above, under or next to current cell
     * @param i cell array row position
     * @param j cell array column position
     * @return true if have same number cells
     *         false if no same number cells
     */
    private boolean haveSameNumber(int i, int j) {
        if (i < boardSize - 1 && j < boardSize - 1) {
            if (cells[i + 1][j].getNumber() == cells[i][j].getNumber())
                return true;
            if (cells[i][j + 1].getNumber() == cells[i][j].getNumber())
                return true;
        }
        return false;
    }

    /**
     * to check whether any movement can be made in current board
     * @return true if no movement can be made (no same number cells next to each other)
     *         false if movement can be made (there are same number cells next to each other)
     */
    public boolean canNotMove() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (haveSameNumber(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * add cell number by itself and add sum to score
     * @param i cell array row position
     * @param j cell array column position
     * Return: void
     */
    private void sumCellNumbersToScore(int i, int j) {
        click.play();       //play sound effect
        long cellNumber = cells[i][j].getNumber();
        long score = GameScene.getScore();
        GameScene.setScore(score + cellNumber * 2);
    }
}
