/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.export;

import BT.managers.DiagramPlacesManager;
import java.io.File;

/**
 * To be implemented. Class to export selected file to XML. It will take diagramPlacesManager and loop through every object and print it to
 * file.
 *
 * @author Karel Hala
 */
public class ExportToXml extends ExportModel {

    /**
     * 
     * @param diagramPlaces
     * @param exportToFolder 
     */
    public ExportToXml(DiagramPlacesManager diagramPlaces, File exportToFolder) {
        super(diagramPlaces, exportToFolder);
    }
}
