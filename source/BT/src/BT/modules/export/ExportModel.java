/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.export;

import BT.managers.DiagramPlacesManager;
import java.io.File;
import java.io.IOException;

/**
 * Class for setting basic model for exports.
 *
 * @author Karel Hala
 */
public class ExportModel {

    /**
     *
     */
    protected DiagramPlacesManager exportedPlaces;

    /**
     *
     */
    protected File exportToFolder;

    public ExportModel(DiagramPlacesManager exportedPlaces, File exportToFolder) {
        this.exportedPlaces = exportedPlaces;
        this.exportToFolder = new File(exportToFolder.getAbsolutePath() + "/" + exportedPlaces.getFileName());
        this.exportToFolder.mkdir();
    }

    public DiagramPlacesManager getExportedPlaces() {
        return exportedPlaces;
    }

    public File getExportToFolder() {
        return exportToFolder;
    }

    /**
     * method for creating new file. If file allready exists, it will create file with number at end.
     *
     * @param fileName
     * @param extension
     * @return
     * @throws java.io.IOException
     */
    public File createNewFile(String fileName, String extension) throws IOException {
        File newFile = new File(fileName + extension);
        if (!newFile.exists()) {
            newFile.createNewFile();
            return newFile;
        }
        return createNewFile(fileName.concat("a"), extension);
    }
}
