/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.mainInterface;

import BT.managers.DiagramPlacesManager;
import BT.models.CoordinateModel;
import GUI.MyMenuBar;
import GUI.ToolBarContentModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

/**
 *
 * @author Karel Hala
 */
public class ToolBarContentControler {

    private final ToolBarContentModel toolBarcontent;
    private ActionListener newFileAction;
    private ActionListener closeFileAction;
    private ActionListener openFileAction;
    private ActionListener exportEpsAction;
    private ActionListener exportPdfAction;
    private ActionListener exportXmlAction;
    private ActionListener saveAction;
    private ActionListener saveAsAction;
    private ActionListener exitAction;
    private final ArrayList<DiagramPlacesManager> diagramPlaces;

    /**
     *
     */
    public ToolBarContentControler() {
        this.toolBarcontent = new ToolBarContentModel();
        diagramPlaces = new ArrayList<>();
    }

    /**
     *
     */
    public void addBasicButtons() {
        JPanel myPanel = this.toolBarcontent.getToolBarPane();
        JButton NewFileButton = toolBarcontent.addNewButton("New File", "newFile.png");
        JButton Closebutton = toolBarcontent.addNewButton("Close File", "closeFileButton.png");
        JButton openButton = toolBarcontent.addNewButton("Open File", "openFile.png");
        JButton saveButton = toolBarcontent.addNewButton("Save File", "saveFile.png");
        JButton saveAsButton = toolBarcontent.addNewButton("Save File As..", "saveAs.png");
        JButton exportEps = toolBarcontent.addNewButton("Export to Eps");
        JButton exportPdf = toolBarcontent.addNewButton("Export to PDF");
        JButton exportXml = toolBarcontent.addNewButton("Export to XML");
        JButton exit = toolBarcontent.addNewButton("Exit", "exitApplication.png");
        
        NewFileButton.addActionListener(newFileAction);
        Closebutton.addActionListener(closeFileAction);
        saveButton.addActionListener(saveAction);
        saveAsButton.addActionListener(saveAsAction);
        openButton.addActionListener(openFileAction);
        exportEps.addActionListener(exportEpsAction);
        exportPdf.addActionListener(exportPdfAction);
        exportXml.addActionListener(exportXmlAction);
        exit.addActionListener(exitAction);
        
        myPanel.add(NewFileButton);
        myPanel.add(saveButton);
        myPanel.add(saveAsButton);
        myPanel.add(openButton);
        myPanel.add(Closebutton);
        myPanel.add(new JSeparator(SwingConstants.VERTICAL));
        myPanel.add(exportEps);
        myPanel.add(exportPdf);
        myPanel.add(exportXml);
        myPanel.add(new JSeparator(SwingConstants.VERTICAL));
        myPanel.add(exit);
        this.toolBarcontent.setToolBarPane(myPanel);
    }
    
    /**
     * 
     * @param myLayout
     * @return 
     */
    public ToolBarContentControler setBasicListeners(final WindowLayoutControler myLayout)
    {   
        setCloseAndOpenShortCuts(myLayout);
        return this;
    }

    /**
     *
     * @param myLayout
     */
    public void NewFileButtonMouseClicked(WindowLayoutControler myLayout) {
        NewTabController newTab = new NewTabController();
        newTab.setDiagramPlaces(new DiagramPlacesManager());
        newTab.addNewTab(myLayout);
        this.diagramPlaces.add(newTab.getDiagramPlaces());
    }

    /**
     * 
     * @param openedDiagrams
     * @param myLayout 
     */
    public void openedFile(DiagramPlacesManager openedDiagrams, WindowLayoutControler myLayout)
    {
        NewTabController newTab = new NewTabController();
        newTab.setDiagramPlaces(openedDiagrams);
        newTab.addNewTab(myLayout);
        this.diagramPlaces.add(newTab.getDiagramPlaces());
    }
    
    /**
     *
     * @param myLayout
     */
    public void CloseButtonMouseClicked(WindowLayoutControler myLayout) {
        if (myLayout.getFileTab().getSelectedIndex()!= -1)
        {
            int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to close selected file?", "Please confirm", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.OK_OPTION)
            {
                removeDiagramPlaceById(myLayout.getFileTab().getSelectedIndex());
                for (DiagramPlacesManager diagramPlacesManager : diagramPlaces) {
                    if (diagramPlacesManager.getDiagramNumber()>myLayout.getFileTab().getSelectedIndex())
                    {
                        diagramPlacesManager.setDiagramNumber(diagramPlacesManager.getDiagramNumber() - 1);
                    }
                }
                myLayout.removeTab(myLayout.getSelectedTab());
            }
        }
    }

    /**
     * Method for removing diagramplaces by it's id
     * @param id 
     */
    public void removeDiagramPlaceById(int id)
    {
        for (Iterator<DiagramPlacesManager> it = this.diagramPlaces.iterator(); it.hasNext();) {
            DiagramPlacesManager model = it.next();
            if (model.getDiagramNumber() == id) {
                it.remove();
            }
        }
    }
    
    /**
     * 
     * @param id
     * @return 
     */
    public DiagramPlacesManager getDiagramById(int id)
    {
        if (id < 0)
        {
            return null;
        }
        return this.diagramPlaces.get(id);
    }
    
    public void setNewFileAction(ActionListener newFileAction) {
        this.newFileAction = newFileAction;
    }

    public void setCloseFileAction(ActionListener closeFileAction) {
        this.closeFileAction = closeFileAction;
    }

    public void setOpenFileAction(ActionListener openFileAction) {
        this.openFileAction = openFileAction;
    }

    public void setExportEpsAction(ActionListener exportEpsAction) {
        this.exportEpsAction = exportEpsAction;
    }

    public void setExportPdfAction(ActionListener exportPdfAction) {
        this.exportPdfAction = exportPdfAction;
    }

    public void setExportXmlAction(ActionListener exportXmlAction) {
        this.exportXmlAction = exportXmlAction;
    }

    public void setSaveAction(ActionListener saveAction) {
        this.saveAction = saveAction;
    }

    public void setSaveAsAction(ActionListener saveAsAction) {
        this.saveAsAction = saveAsAction;
    }
    
    public ToolBarContentModel getToolBarcontent() {
        return this.toolBarcontent;
    }

    public ActionListener getNewFileAction() {
	return newFileAction;
    }

    public ActionListener getCloseFileAction() {
	return closeFileAction;
    }

    public ActionListener getOpenFileAction() {
        return openFileAction;
    }

    public ActionListener getExportEpsAction() {
        return exportEpsAction;
    }

    public ActionListener getExportPdfAction() {
        return exportPdfAction;
    }

    public ActionListener getExportXmlAction() {
        return exportXmlAction;
    }

    public ActionListener getSaveAction() {
        return saveAction;
    }

    public ActionListener getSaveAsAction() {
        return saveAsAction;
    }

    public ActionListener getExitAction() {
        return exitAction;
    }

    public void setExitAction(ActionListener exitAction) {
        this.exitAction = exitAction;
    }
    
    public ArrayList<DiagramPlacesManager> getDiagramPlaces() {
        return diagramPlaces;
    }
    
    /**
     *
     * @param myLayout
     */
    private void setCloseAndOpenShortCuts(final WindowLayoutControler myLayout) {
        myLayout.getFileTab().getActionMap().put("closeTab", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CloseButtonMouseClicked(myLayout);
            }
        }
        );

        myLayout.getFileTab().getActionMap().put("newTab", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewFileButtonMouseClicked(myLayout);
            }
        }
        );

        InputMap inputMap = myLayout.getFileTab().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK), "closeTab");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_MASK), "newTab");
    }
}
