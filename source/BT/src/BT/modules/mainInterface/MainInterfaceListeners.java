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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Karel
 */
public class MainInterfaceListeners {
    final private ToolBarContentControler toolBarContent;
    
    public MainInterfaceListeners(ToolBarContentControler toolBarContent)
    {
        this.toolBarContent = toolBarContent;
    }
    
    /**
     * Method that handles clicking on save button.
     * @param tabId id of selected tab. By this id it will be seleted propriete diagramPlace
     * @return return filename of selected file.
     */
    public String clickedOnSave(int tabId)
    {
        if (toolBarContent.getDiagramById(tabId) != null)
        {
            DiagramPlacesManager selectedDiagrams = toolBarContent.getDiagramById(tabId);
            XStream xstream = new XStream(new DomDriver());
            String xml = xstream.toXML(selectedDiagrams);
            if (selectedDiagrams.getAbsolutePath() == null)
            {
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
                        extension = file.getName().substring(i+1);
                    }
                    if (!extension.equals("xml"))
                    {
                        fileName = file.getAbsolutePath() + ".xml";
                        selectedDiagrams.setFileName(file.getName());
                    }
                    else
                    {
                        fileName = file.getAbsolutePath();
                        selectedDiagrams.setFileName(file.getName().substring(0,i));
                    }
                    try {
                        try (PrintWriter out = new PrintWriter(fileName)) {
                            selectedDiagrams.setAbsolutePath(new File(fileName));
                            out.println(xml);
                        }
                    } catch (FileNotFoundException ex) {
                        JOptionPane.showMessageDialog(null, "Error while saving file.");
                        System.out.println("Error while saving file.");
                    }
                } else {
                    System.out.println("canceled");
                }
            }
            else
            {
                try {
                    try (PrintWriter out = new PrintWriter(selectedDiagrams.getAbsolutePath())) {
                        out.println(xml);
                    }
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Error while saving file.");
                    System.out.println("Error while saving file.");
                }
            }
            return selectedDiagrams.getFileName();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No file to save.");
            return null;
        }
    }
    
    /**
     * Method for handeling clicking on open button.
     * @return 
     */
    public DiagramPlacesManager clickedOnOpen()
    {
        System.out.println("Open clicked");
        DiagramPlacesManager openedFile = new DiagramPlacesManager();
        return openedFile;
    }
    
    /**
     * Method for handeling click on export to EPS.
     * @param tabId id of selected tab with file.
     */
    public void clickedOnExportToEPS(int tabId)
    {
        if (toolBarContent.getDiagramById(tabId) != null)
        {
            ExportToEps epsExport = new ExportToEps(toolBarContent.getDiagramById(tabId));
            System.out.println("ExportEpsClicked");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No file to export.");
        }
    }
    
    /**
     * Method for handeling click on export to XML.
     * @param tabId id of selected tab with file.
     */
    public void clickedOnExportToXML(int tabId)
    {
        if (toolBarContent.getDiagramById(tabId) != null)
        {
            ExportToXml xmlExporter = new ExportToXml(toolBarContent.getDiagramById(tabId));
            System.out.println("ExportXmlClicked");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No file to export.");
        }
    }
    
    /**
     * Method for handeling click on export to PDF.
     * @param tabId id of selected tab with file. 
    */
    public void clickedOnExportToPDF(int tabId)
    {
        if (toolBarContent.getDiagramById(tabId) != null)
        {
            ExportToPdf pdfExporter = new ExportToPdf(toolBarContent.getDiagramById(tabId));
            System.out.println("ExoprtToPdfClicked");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No file to export.");
        }
    }
    
    /**
     * Method for saving opened file as.
     * @param tabId id of selected tab with file. 
     * @return saved as file.
     */
    public DiagramPlacesManager clickedOnSaveAs(int tabId)
    {
        if (toolBarContent.getDiagramById(tabId) != null)
        {
            System.out.println("save as clicked");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No file to save as.");
        }
        return new DiagramPlacesManager();
    }
}
