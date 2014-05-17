/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.managers;

import BT.interfaces.DrawingClicks;
import BT.models.ContentPaneModel;
import BT.models.LineModel;
import java.awt.Point;
import java.awt.event.MouseEvent;

/**
 * Main class for managing man content controllers. It is abstract class, that
 * it's childs will implement Drawing clicks.
 *
 * @author Karel Hala
 */
abstract public class MainContentController implements DrawingClicks {

    /**
     * Main content model for storing additional data.
     */
    protected ContentPaneModel mainContent;
    /**
     * Objects that are drawn on jpanel.
     */
    protected PlaceManager places;
    /**
     * Join edge, that is currently drawn.
     */
    protected LineModel newJoinEdge;
    /**
     * All objects of whole application.
     */
    protected DiagramPlacesManager diagramPlaces;

    public ContentPaneModel getMainContent() {
        return mainContent;
    }

    public PlaceManager getPlaces() {
        return places;
    }

    public LineModel getNewJoinEdge() {
        return newJoinEdge;
    }

    public void setMainContent(ContentPaneModel mainContent) {
        this.mainContent = mainContent;
    }

    public void setPlaces(PlaceManager places) {
        this.places = places;
    }

    public void setNewJoinEdge(LineModel newJoinEdge) {
        this.newJoinEdge = newJoinEdge;
    }

    /**
     * Basic acting of right click. It will add new breakPoint to new Join edge.
     *
     * @param evt
     */
    @Override
    public void rightClick(MouseEvent evt) {
        if (this.newJoinEdge != null) {
            this.newJoinEdge.addBreakPoint(new Point(evt.getX(), evt.getY()));
        }
    }

}
