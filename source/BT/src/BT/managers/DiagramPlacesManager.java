/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BT.managers;

import BT.models.CoordinateModel;
import BT.modules.ClassDiagram.places.CDClass;
import java.util.ArrayList;

/**
 *
 * @author Karel
 */
public class DiagramPlacesManager {
    
    /**
     * 
     */
    private String fileName;
    
    /**
     * 
     */
    private int diagramNumber;
    
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
    private ArrayList<PlaceManager> pnPlaces;

    public DiagramPlacesManager() {
        this.ucPlaces = new PlaceManager();
        this.cdPlaces = new PlaceManager();
        this.pnPlaces = new ArrayList<>();
    }

    public PlaceManager getCdPlaces() {
        return cdPlaces;
    }

    public void setCdPlaces(PlaceManager cdPlaces) {
        this.cdPlaces = cdPlaces;
    }

    public ArrayList<PlaceManager> getPnPlaces() {
        return pnPlaces;
    }

    public void setPnPlaces(ArrayList<PlaceManager> pnPlaces) {
        this.pnPlaces = pnPlaces;
    }

    public void addPnPlace(PlaceManager pnPlaceManager)
    {
        this.pnPlaces.add(pnPlaceManager);
    }
    
    public void removePnPlace(PlaceManager pnPlaceManager)
    {
        this.pnPlaces.remove(pnPlaceManager);
    }
    
    public PlaceManager getUcPlaces() {
        return ucPlaces;
    }

    public void setUcPlaces(PlaceManager ucPlaces) {
        this.ucPlaces = ucPlaces;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getDiagramNumber() {
        return diagramNumber;
    }

    public void setDiagramNumber(int diagramNumber) {
        this.diagramNumber = diagramNumber;
    }
    
    /**
     * Method for getting selected class from selected file.
     * @return 
     */
    public CDClass getSelectedClass()
    {
        if (cdPlaces.getSelectedObject() instanceof CDClass)
        {
            return (CDClass) cdPlaces.getSelectedObject();
        }
        return null;
    }
}
