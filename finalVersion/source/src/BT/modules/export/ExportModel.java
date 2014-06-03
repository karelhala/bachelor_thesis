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
     * Places that are being exported. It contains each place from useCase diagram, class diagram and from every OOPN of
     * this file each object.
     */
    protected DiagramPlacesManager exportedPlaces;

    /**
     * File location of File that needs to be exported.
     */
    protected File exportToFolder;

    /**
     * Basic constructor. It sets exportedPlaces and exportToFolder.
     *
     * @param exportedPlaces each place from useCase diagram, class diagram and from every OOPN of this file each object
     * @param exportToFolder file location of folder.
     */
    public ExportModel(DiagramPlacesManager exportedPlaces, File exportToFolder) {
        this.exportedPlaces = exportedPlaces;
        this.exportToFolder = new File(exportToFolder.getAbsolutePath() + "/" + exportedPlaces.getFileName());
        this.exportToFolder.mkdir();
    }

    /**
     * Get places that are exported.
     *
     * @return DiagramPlacesManager with everz object of each part of appliacation.
     */
    public DiagramPlacesManager getExportedPlaces() {
        return exportedPlaces;
    }

    /**
     * Get path of file that is being exported.
     * 
     * @return File, complete path.
     */
    public File getExportToFolder() {
        return exportToFolder;
    }

    /**
     * Method for creating new file. If file allready exists, it will create file with number at end.
     *
     * @param fileName name of newly created file.
     * @param extension what extensin should be used.
     * @return File, whole path to file.
     * @throws java.io.IOException when error occures.
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
