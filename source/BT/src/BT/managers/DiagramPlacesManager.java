/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BT.managers;

import BT.modules.ClassDiagram.places.CDClass;
import java.io.File;
import java.util.ArrayList;

/**
 * Class that holds every place manager.
 * This class is used to store every place and operations with it.
 * It holds number of diagram (index of tab).
 * File name (name of opened file).
 * UcPlaces actors and actions of useCase diagram.
 * CdPlaces class diagram classes.
 * PnPlaces are stored in arrayList because of methods can have petriNet too.
 * @author Karel Hala
 */
public class DiagramPlacesManager {
    
    /**
     * Absolutepath to file.
     */
    private File absolutePath;
    
    /**
     * Name of opened file.
     */
    private String fileName;
    
    /**
     * Index of tab, easier access to this class.
     */
    private int diagramNumber;
    
    /**
     * UseCase diagram places.
     */
    private PlaceManager ucPlaces;
    
    /**
     * Class diagram places.
     */
    private PlaceManager cdPlaces;
    
    /**
     * ArrayList of petriNets diagrams.
     */
    private ArrayList<PlaceManager> pnPlaces;

    /**
     * Basic constructor for creating every placeManager.
     */
    public DiagramPlacesManager() {
        this.ucPlaces = new PlaceManager();
        this.cdPlaces = new PlaceManager();
        this.pnPlaces = new ArrayList<>();
    }

    /**
     * Getter for class diagram places.
     * @return PlaceManager of class diagram.
     */
    public PlaceManager getCdPlaces() {
        return cdPlaces;
    }

    /**
     * Setter for class diagram places.
     * @param cdPlaces
     */
    public void setCdPlaces(PlaceManager cdPlaces) {
        this.cdPlaces = cdPlaces;
    }

    /**
     * Getter for fetching all petriNet diagrams.
     * @return ArrayList<PlaceManager> of petriNets.
     */
    public ArrayList<PlaceManager> getPnPlaces() {
        return pnPlaces;
    }

    /**
     * Setter for petriNet diagrams.
     * @param pnPlaces ArrayList<PlaceManager> of petriNet diagrams.
     */
    public void setPnPlaces(ArrayList<PlaceManager> pnPlaces) {
        this.pnPlaces = pnPlaces;
    }

    /**
     * Add new petriNet diagram to file.
     * @param pnPlaceManager PlaceManager of inserted petriNet diagram.
     */
    public void addPnPlace(PlaceManager pnPlaceManager) {
        this.pnPlaces.add(pnPlaceManager);
    }

    /**
     * Remove petriNet diagram from opened file.
     * @param pnPlaceManager PlaceManager of deleted petriNet diagram.
     */    
    public void removePnPlace(PlaceManager pnPlaceManager) {
        this.pnPlaces.remove(pnPlaceManager);
    }

    /**
     * Getter for fetching useCase diagram places.
     * @return PlaceManager of useCase diagram.
     */    
    public PlaceManager getUcPlaces() {
        return ucPlaces;
    }

    /**
     * Setter for useCase diagram places.
     * @param ucPlaces PlaceManager of useCase diagram.
     */
    public void setUcPlaces(PlaceManager ucPlaces) {
        this.ucPlaces = ucPlaces;
    }

    /**
     * Getter for fetching name of file.
     * @return String name of file.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Setter for name of file.
     * @param fileName name of file as String.
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    /**
     * Getter for index of diagram.
     * @return int integer of tab.
     */
    public int getDiagramNumber() {
        return diagramNumber;
    }

    /**
     * Setter for index of diagram.
     * @param diagramNumber index of diagram as integer.
     */
    public void setDiagramNumber(int diagramNumber) {
        this.diagramNumber = diagramNumber;
    }
    
    /**
     * Method for getting selected class from selected file.
     * @return CDClass if any class is selected.
     */
    public CDClass getSelectedClass()
    {
        if (cdPlaces.getSelectedObject() instanceof CDClass)
        {
            return (CDClass) cdPlaces.getSelectedObject();
        }
        return null;
    }

    public File getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(File absolutePath) {
        this.absolutePath = absolutePath;
    }
}
