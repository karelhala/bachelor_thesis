/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BT.managers;

/**
 *
 * @author Karel
 */
public class DiagramPlacesManager {
    
    /**
     * 
     */
    private PlaceManager ucPlaces;
    
    /**
     * 
     */
    private PlaceManager cdPlaces;
    
    /**
     * 
     */
    private PlaceManager pnPlaces;

    public DiagramPlacesManager() {
        this.ucPlaces = new PlaceManager();
        this.cdPlaces = new PlaceManager();
        this.pnPlaces = new PlaceManager();
    }

    public PlaceManager getCdPlaces() {
        return cdPlaces;
    }

    public void setCdPlaces(PlaceManager cdPlaces) {
        this.cdPlaces = cdPlaces;
    }

    public PlaceManager getPnPlaces() {
        return pnPlaces;
    }

    public void setPnPlaces(PlaceManager pnPlaces) {
        this.pnPlaces = pnPlaces;
    }

    public PlaceManager getUcPlaces() {
        return ucPlaces;
    }

    public void setUcPlaces(PlaceManager ucPlaces) {
        this.ucPlaces = ucPlaces;
    }
}
