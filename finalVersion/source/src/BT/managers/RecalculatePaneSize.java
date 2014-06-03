/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.managers;

import BT.models.CoordinateModel;
import BT.models.LineModel;
import java.awt.Dimension;
import java.awt.Point;

/**
 * Class for recalculating drawing pane.
 *
 * @author Karel Hala
 */
public class RecalculatePaneSize {

    /**
     * Method for racalculating pane size based on new object.
     *
     * @param newObject object that is being printed.
     * @param size of pane.
     * @return new size or old one if not changed.
     */
    public static Dimension recalculateSizeofPaneOnObject(CoordinateModel newObject, Dimension size) {
        if (newObject.getX() < 0) {
            newObject.setX(0);
        }
        if (newObject.getY() < 0) {
            newObject.setY(0);
        }
        if (newObject.getX() > size.width) {
            size.width = newObject.getX();
        }
        if (newObject.getY() > size.height) {
            size.height = newObject.getY();
        }
        return size;
    }

    /**
     * Method for racalculating pane size based on new line.
     * @param newLine line that is being printed.
     * @param size of pane.
     * @return new size or old one if not changed.
     */
    public static Dimension recalculateSizeOfPaneByLine(LineModel newLine, Dimension size) {
        if (newLine.getX() > size.width) {
            size.width = newLine.getX();
        }
        if (newLine.getY() > size.height) {
            size.height = newLine.getY();
        }
        for (Point oneBreak : newLine.getBreakPoints()) {
            if (oneBreak.getX() > size.width) {
                size.width = newLine.getX();
            }
            if (oneBreak.getY() > size.height) {
                size.height = newLine.getY();
            }
        }
        return size;
    }
}
