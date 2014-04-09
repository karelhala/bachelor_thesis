/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BT.modules.export;

import BT.managers.DiagramPlacesManager;

/**
 * Class for setting basic model for exports.
 * @author Karel
 */
public class ExportModel {
    protected DiagramPlacesManager exportedPlaces;
    
    public ExportModel(DiagramPlacesManager exportedPlaces)
    {
        this.exportedPlaces = exportedPlaces;
    }

    public DiagramPlacesManager getExportedPlaces() {
        return exportedPlaces;
    }
}
