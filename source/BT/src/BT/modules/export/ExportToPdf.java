/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.export;

import BT.managers.DiagramPlacesManager;
import java.io.File;

/**
 * Class for exporting files to PDF. It will take diagramPlacesManager and loop through every object and print it to
 * file.
 *
 * @author Karel
 */
public class ExportToPdf extends ExportModel {

    public ExportToPdf(DiagramPlacesManager diagramPlaces, File exportToFolder) {
        super(diagramPlaces, exportToFolder);
    }
}
