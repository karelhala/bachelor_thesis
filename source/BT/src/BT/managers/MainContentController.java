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
 *
 * @author Karel Hala
 */
abstract public class MainContentController implements DrawingClicks{
    /**
     * 
     */
    protected ContentPaneModel mainContent;
    
    /**
     * 
     */
    protected PlaceManager places;
    
    /**
     * 
     */
    protected LineModel newJoinEdge;

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
     * 
     * @param evt 
     */
    @Override
    public void rightClick(MouseEvent evt)
    {
        if (this.newJoinEdge != null)
        {
            this.newJoinEdge.addBreakPoint(new Point(evt.getX(), evt.getY()));
            System.out.println(this.newJoinEdge.getBreakPoints());
        }
    }
}
