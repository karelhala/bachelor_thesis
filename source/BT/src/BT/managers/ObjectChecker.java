/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.managers;

import BT.models.CoordinateModel;
import BT.models.LineModel;
import java.awt.Point;

/**
 * Class for checking objects under mouse. It will check basic objects and even lines.
 *
 * @author Karel Hala
 */
public class ObjectChecker {

    /**
     * PlaceManager with each line and object.
     */
    private final PlaceManager places;

    /**
     * Basic constructor, it sets places.
     *
     * @param places
     */
    public ObjectChecker(PlaceManager places) {
        super();
        this.places = places;
    }

    /**
     * Method for checking if any object is under mouse. It will check if basic object is under mouse and this will be
     * returned. Or if line is under mouse, and this line will be returned.
     *
     * @param mousePoint Poit of mouse.
     * @return Coordinate object, either object or line.
     */
    public CoordinateModel getObjectUnderMouse(Point mousePoint) {
        CoordinateModel coordModel;
        coordModel = isObjectunderMouse(mousePoint.x, mousePoint.y);
        if (coordModel != null) {
            return coordModel;
        }
        coordModel = isJoinEdgeUnderMouse(mousePoint.x, mousePoint.y);
        if (coordModel != null) {
            return coordModel;
        }

        return null;
    }

    /**
     * Checks if basic object is under mouse.
     *
     * @param x coordinate X of mouse.
     * @param y coordinate Y of mouse.
     * @return object if any is under mouse.
     */
    private CoordinateModel isObjectunderMouse(int x, int y) {
        for (CoordinateModel object : places.getObjects()) {
            if (object.isObject(x, y)) {
                return object;
            } else {
                object.setBasicColor();
            }
        }
        return null;
    }

    /**
     * Checks if line is under mouse.
     *
     * @param x coordinate X of mouse.
     * @param y coordinate Y of mouse.
     * @return join edge if any is under mouse.
     */
    private LineModel isJoinEdgeUnderMouse(int x, int y) {
        for (LineModel joinEdge : places.getJoinEdges()) {
            if (joinEdge.isInRange(x, y)) {
                return joinEdge;
            } else {
                joinEdge.setBasicColor();
            }
        }
        return null;
    }
}
