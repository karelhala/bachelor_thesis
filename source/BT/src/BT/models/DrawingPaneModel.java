/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.models;

import BT.managers.PlaceManager;

/**
 * Model for drawing pane. This class holds drawed places and new line, if any
 * is being drawn.
 *
 * @author Karel Hala
 */
public class DrawingPaneModel {

    /**
     * Places for this pane.
     */
    protected PlaceManager places;
    /**
     * If any new line is being drawn.
     */
    protected LineModel newLine;

    /**
     * Basic constructor that sets places.
     *
     * @param places
     */
    public DrawingPaneModel(PlaceManager places) {
        super();
        this.places = places;
        this.newLine = null;
    }

    public PlaceManager getPlaces() {
        return places;
    }

    public LineModel getNewLine() {
        return newLine;
    }

    public void setPlaces(PlaceManager places) {
        this.places = places;
    }

    public void setNewLine(LineModel newLine) {
        this.newLine = newLine;
    }
}
