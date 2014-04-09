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
import javax.swing.JOptionPane;

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
     */
    public void clickedOnSave(int tabId)
    {
        if (toolBarContent.getDiagramById(tabId) != null)
        {
            System.out.println(toolBarContent.getDiagramById(tabId).getCdPlaces().getObjects().size());
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No file to save.");
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
