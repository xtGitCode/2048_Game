package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void haveEmptyCell() {
        int expectedValue = 1;
        int actualValue = 0;
        int boardSize = 4;

        /*Cell[][] cells = {
                {2,2,4},
                {0,2,2},
                {2,0,2},
        };

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (cells[i][j].getNumber() == 0) {
                    actualValue = 1;
                }
            }
        }
        Assertions.assertEquals(expectedValue, actualValue);*/
    }

    @Test
    void moveLeft() {
    }

    @Test
    void moveRight() {
    }

    @Test
    void moveUp() {
    }

    @Test
    void moveDown() {
    }

    @Test
    void canNotMove() {
    }
}