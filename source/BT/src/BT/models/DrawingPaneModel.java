/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.models;

import BT.managers.PlaceManager;

/**
 *
 * @author Karel Hala
 */
public class DrawingPaneModel {

    protected PlaceManager places;
    protected LineModel newLine;

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
