/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.mainInterface;

import BT.managers.DiagramPlacesManager;
import BT.modules.export.ExportToEps;
import BT.modules.export.ExportToPdf;
import BT.modules.export.ExportToXml;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Karel Hala
 */
public class MainInterfaceListeners {

    /**
     * Toolbar buttons and their listeners.
     */
    final private ToolBarContentControler toolBarContent;

    /**
     * Basic constructor.
     * It sets toolBarcontet.
     * @param toolBarContent stores each button with different action.
     */
    public MainInterfaceListeners(ToolBarContentControler toolBarContent) {
        this.toolBarContent = toolBarContent;
    }

    /**
     * Method that handles clicking on save button.
     *
     * @param tabId id of selected tab. By this id it will be seleted propriete diagramPlace
     * @return return filename of selected file.
     */
    public String clickedOnSave(int tabId) {
        if (toolBarContent.getDiagramById(tabId) != null) {
            DiagramPlacesManager selectedDiagrams = toolBarContent.getDiagramById(tabId);
            if (selectedDiagrams.getAbsolutePath() == null) {
                selectedDiagrams = loadSaveDialog(selectedDiagrams);
            }
            saveDiagrams(selectedDiagrams);
            return selectedDiagrams.getFileName();
        } else {
            JOptionPane.showMessageDialog(null, "No file to save.");
            return null;
        }
    }

    /**
     * Method for showing save dialog. It will show save dialog and if you confirm it, it will set the path of file to
     * diagramPlacesManager.
     *
     * @param selectedDiagrams diagramPlacesManager that is being saved.
     * @return DiagramPlacesManager with new path to saved file.
     */
    private DiagramPlacesManager loadSaveDialog(DiagramPlacesManager selectedDiagrams) {
        final JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter("xml files (*.xml)", "xml");
        fc.setFileFilter(xmlfilter);
        int returnVal = fc.showSaveDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            int i = file.getName().lastIndexOf('.');
            String extension = "";
            String fileName;
            if (i > 0) {
                extension = file.getName().substring(i + 1);
            }
            if (!extension.equals("xml")) {
                fileName = file.getAbsolutePath() + ".xml";
                selectedDiagrams.setFileName(file.getName());
            } else {
                fileName = file.getAbsolutePath();
                selectedDiagrams.setFileName(file.getName().substring(0, i));
            }
            selectedDiagrams.setAbsolutePath(new File(fileName));
            return selectedDiagrams;
        } else {
            System.out.println("canceled");
            return null;
        }
    }

    /**
     * Method for saving diagrams to diagrams path.
     *
     * @param selectedDiagrams diagrams to be saved.
     */
    private void saveDiagrams(DiagramPlacesManager selectedDiagrams) {
        try {
            try (PrintWriter out = new PrintWriter(selectedDiagrams.getAbsolutePath())) {
                XStream xstream = new XStream(new DomDriver());
                out.println(xstream.toXML(selectedDiagrams));
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error while saving file.");
            System.out.println("Error while saving file.");
        }

    }

    /**
     * Method for handeling clicking on open button.
     *
     * @return
     */
    public DiagramPlacesManager clickedOnOpen() {
        DiagramPlacesManager openedFile = null;
        final JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter("xml files (*.xml)", "xml");
        fc.setFileFilter(xmlfilter);
        int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            openedFile = getDiagramsFromXML(file);
        }
        return openedFile;
    }

    /**
     * Open diagrams from file with XML.
     *
     * @param fromFile open this file and load diagramPlacesManager.
     * @return opened DiagramPlacesManager.
     */
    public DiagramPlacesManager getDiagramsFromXML(File fromFile) {
        XStream xstream = new XStream(new DomDriver());
        return (DiagramPlacesManager) xstream.fromXML(fromFile);
    }

    /**
     * Method for handeling click on export to EPS.
     *
     * @param tabId id of selected tab with file.
     */
    public void clickedOnExportToEPS(int tabId) {
        if (toolBarContent.getDiagramById(tabId) != null) {
            JFileChooser fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int returnVal = fc.showSaveDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                ExportToEps epsExport = new ExportToEps(toolBarContent.getDiagramById(tabId), fc.getSelectedFile());
                epsExport.exportAllToFiles();
            }
            System.out.println("ExportEpsClicked");
        } else {
            JOptionPane.showMessageDialog(null, "No file to export.");
        }
    }

    /**
     * Method for handeling click on export to XML.
     *
     * @param tabId id of selected tab with file.
     */
    public void clickedOnExportToXML(int tabId) {
        if (toolBarContent.getDiagramById(tabId) != null) {
            JFileChooser fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int returnVal = fc.showSaveDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                ExportToXml xmlExporter = new ExportToXml(toolBarContent.getDiagramById(tabId), fc.getSelectedFile());
            }
            JOptionPane.showMessageDialog(null, "Export to XMl not yet implemented. If you want to export to RAW XML, click on save button.");
        } else {
            JOptionPane.showMessageDialog(null, "No file to export.");
        }
    }

    /**
     * Method for handeling click on export to PDF.
     *
     * @param tabId id of selected tab with file.
     */
    public void clickedOnExportToPDF(int tabId) {
        if (toolBarContent.getDiagramById(tabId) != null) {
            JFileChooser fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int returnVal = fc.showSaveDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                ExportToPdf pdfExporter = new ExportToPdf(toolBarContent.getDiagramById(tabId), fc.getSelectedFile());
            }
            JOptionPane.showMessageDialog(null, "Export to PDF not yet implemented.");
        } else {
            JOptionPane.showMessageDialog(null, "No file to export.");
        }
    }

    /**
     * Method for saving opened file as.
     *
     * @param tabId id of selected tab with file.
     * @return saved as file.
     */
    public DiagramPlacesManager clickedOnSaveAs(int tabId) {
        if (toolBarContent.getDiagramById(tabId) != null) {
            System.out.println("save as clicked");
        } else {
            JOptionPane.showMessageDialog(null, "No file to save as.");
        }
        return new DiagramPlacesManager();
    }
}
