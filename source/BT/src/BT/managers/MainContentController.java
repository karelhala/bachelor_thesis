/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.managers;

import BT.interfaces.DrawingClicks;
import BT.models.ContentPaneModel;

/**
 *
 * @author Karel Hala
 */
abstract public class MainContentController implements DrawingClicks{
    protected ContentPaneModel mainContent;
    protected PlaceManager places;

    public ContentPaneModel getMainContent() {
        return mainContent;
    }

    public PlaceManager getPlaces() {
        return places;
    }

    public void setMainContent(ContentPaneModel mainContent) {
        this.mainContent = mainContent;
    }

    public void setPlaces(PlaceManager places) {
        this.places = places;
    }
    
}
