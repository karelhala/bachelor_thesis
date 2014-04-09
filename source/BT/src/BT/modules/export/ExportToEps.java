/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BT.modules.export;

import BT.managers.DiagramPlacesManager;

/**
 * Class for exporting selected file to EPS.
 * It will take diagramPlacesManager and loop through every object and print it to file.
 * @author Karel
 */
public class ExportToEps extends ExportModel{
    
    public ExportToEps(DiagramPlacesManager diagramPlaces)
    {
        super(diagramPlaces);
    }
}
