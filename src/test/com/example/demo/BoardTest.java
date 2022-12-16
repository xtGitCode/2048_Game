package com.example.demo;

import javafx.scene.Group;
import javafx.scene.text.Text;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Array;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void haveEmptyCell() {
        int expectedValue = 0;
        int actualValue = 0;
        int boardSize = 4;
        Cell[][] cells = new Cell[boardSize][boardSize];

        for(int i = 0 ; i < boardSize; i++){
            for(int j = 0; j < boardSize; j++){
                cells[i][j] = new Cell((0 + 0.5) * 2 + (0 + 1) * 0,
                        (0 + 0) * 0 + (0 + 1) * 0, 0, new Group());
            }
        }

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                cells[i][j].setTextClass(new Text("2"));
            }
        }

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (cells[i][j].getNumber() == 0) {
                    actualValue = 1;
                }
            }
        }
        Assertions.assertEquals(expectedValue, actualValue);
    }

    @Test
    void haveSameNumberPass() {
        int boardSize = 4;
        Cell[][] cells = new Cell[boardSize][boardSize];
        Board board = new Board(4, new Group());
        boolean expected = false;

        for(int i = 0 ; i < boardSize; i++){
            for(int j = 0; j < boardSize; j++){
                cells[i][j] = new Cell((0 + 0.5) * 2 + (0 + 1) * 0,
                        (0 + 0) * 0 + (0 + 1) * 0, 0, new Group());
            }
        }
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                cells[i][j].setTextClass(new Text("2"));
            }
        }
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                expected = board.haveSameNumber(i,j);
                if (expected == true){
                    break;
                }
            }
            if (expected == true){
                break;
            }
        }
        Assertions.assertTrue(expected);
    }
    @Test
    void haveSameNumberFail() {
        int boardSize = 4;
        Cell cell_2= new Cell((0 + 0.5) * 2 + (0 + 1) * 0,
                (0 + 0) * 0 + (0 + 1) * 0, 0, new Group());
        cell_2.setTextClass(new Text("2"));
        Cell cell_4= new Cell((0 + 0.5) * 2 + (0 + 1) * 0,
                (0 + 0) * 0 + (0 + 1) * 0, 0, new Group());
        cell_4.setTextClass(new Text("4"));
        Cell cell_8= new Cell((0 + 0.5) * 2 + (0 + 1) * 0,
                (0 + 0) * 0 + (0 + 1) * 0, 0, new Group());
        cell_8.setTextClass(new Text("8"));
        Cell[][] cells = {
                {cell_2,cell_4,cell_2,cell_4},
                {cell_4,cell_8, cell_4,cell_8},
                {cell_8,cell_4,cell_2,cell_4},
                {cell_4,cell_8,cell_4, cell_2}
        };

        Board board = new Board(4, new Group());
        boolean expected = false;

        for (int i = 0; i < boardSize; i++) {
            int number;
            for (int j = 0; j < boardSize; j++) {
                System.out.print(cells[i][j].getNumber() + "\t");
            }
            System.out.println();
        }
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                expected = board.haveSameNumber(i,j);

                if (expected == true){
                    break;
                }
            }
            if (expected == true){
                break;
            }
        }
        Assertions.assertTrue(expected);
    }
}