/**
 * @author Xiao Thung Gan - modified
 */

package com.example.demo;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Name: TextMaker Class
 * Purpose: create, position and change text in cells
 */
class TextMaker {
    private static TextMaker singleInstance = null;

    private TextMaker() {

    }

    static TextMaker getSingleInstance() {
        if (singleInstance == null)
            singleInstance = new TextMaker();
        return singleInstance;
    }

    /**
     * Name: madeText
     * Purpose: create number text and position to cell
     * @param input text to create
     * @param xCell horizontal position of cell
     * @param yCell vertical position of cell
     * @return text that is created
     */
    static Text madeText(String input, double xCell, double yCell) {
        double length = Board.getLENGTH();
        double fontSize = (2 * length) / 7.0;
        Text text = new Text(input);
        text.setFont(Font.font(fontSize));
        text.relocate((xCell + (1.2)* length / 7.0), (yCell + 2 * length / 7.0));
        text.setFill(Color.WHITE);

        return text;
    }

    /**
     * Name: changeTwoText
     * Purpose: change current cell number to new number
     * @param first current cell number
     * @param second new cell number
     * Return: void
     */
    static void changeTwoText(Text first, Text second) {
        String temp;
        temp = first.getText();
        first.setText(second.getText());
        second.setText(temp);

        double tempNumber;
        tempNumber = first.getX();
        first.setX(second.getX());
        second.setX(tempNumber);

        tempNumber = first.getY();
        first.setY(second.getY());
        second.setY(tempNumber);

    }

}
