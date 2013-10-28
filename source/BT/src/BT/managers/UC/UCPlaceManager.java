/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.managers.UC;

import BT.managers.CoordinateManager;
import java.util.ArrayList;

/**
 *
 * @author Karel
 */
public class UCPlaceManager {
    private ArrayList<CoordinateManager> places = new ArrayList<>();
    
    public ArrayList<CoordinateManager> getPlaces()
    {
            return places;
    }
    
    public void addPlace(CoordinateManager place) {
        this.places.add(place);
    }
}
