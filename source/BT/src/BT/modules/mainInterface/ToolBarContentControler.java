/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.mainInterface;

import BT.managers.DiagramPlacesManager;
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
 * Stores toolBar content, like buttons and their listeners.
 *
 * @author Karel Hala
 */
public class ToolBarContentControler {

    /**
     * It creates and inserts new button to toolBar.
     */
    private final ToolBarContentModel toolBarcontent;
    /**
     * Action listener to new file.
     */
    private ActionListener newFileAction;
    /**
     * Action listener to close file.
     */
    private ActionListener closeFileAction;
    /**
     * Action listener to open file.
     */
    private ActionListener openFileAction;
    /**
     * Action listener to export to EPS.
     */
    private ActionListener exportEpsAction;
    /**
     * Action listener to export to PFS. Not implemented.
     */
    private ActionListener exportPdfAction;
    /**
     * Action listener to export to XML. Not implemented.
     */
    private ActionListener exportXmlAction;
    /**
     * Action listener to save file.
     */
    private ActionListener saveAction;
    /**
     * Action listener to save file as... .
     */
    private ActionListener saveAsAction;
    /**
     * Action listener to exit application.
     */
    private ActionListener exitAction;
    /**
     * Action listener to show basic help.
     */
    private ActionListener helpAction;
    /**
     * Action listener to sotre each and evry diagram place manager with every object. Here is stored every part of
     * opened and managed files.
     */
    private final ArrayList<DiagramPlacesManager> diagramPlaces;

    /**
     * Basic contructor. It creates toolbar model and set diagramPlaces to new ArrayList.
     */
    public ToolBarContentControler() {
        this.toolBarcontent = new ToolBarContentModel();
        diagramPlaces = new ArrayList<>();
    }

    /**
     * Create buttons for toolbar and insert them. If an icon is found, add button with icon.
     */
    public void addBasicButtons() {
        JPanel myPanel = this.toolBarcontent.getToolBarPane();
        JButton NewFileButton = toolBarcontent.addNewButton("New File", "newFile.png");
        JButton Closebutton = toolBarcontent.addNewButton("Close File", "closeFileButton.png");
        JButton openButton = toolBarcontent.addNewButton("Open File", "openFile.png");
        JButton saveButton = toolBarcontent.addNewButton("Save File", "saveFile.png");
        JButton saveAsButton = toolBarcontent.addNewButton("Save File As..", "saveAs.png");
        JButton exportEps = toolBarcontent.addNewButton("Export to Eps", "printEps.png");
//        JButton exportPdf = toolBarcontent.addNewButton("Export to PDF");
//        JButton exportXml = toolBarcontent.addNewButton("Export to XML");
        JButton help = toolBarcontent.addNewButton("Help", "help.png");
        JButton exit = toolBarcontent.addNewButton("Exit", "exitApplication.png");

        NewFileButton.addActionListener(newFileAction);
        Closebutton.addActionListener(closeFileAction);
        saveButton.addActionListener(saveAction);
        saveAsButton.addActionListener(saveAsAction);
        openButton.addActionListener(openFileAction);
        exportEps.addActionListener(exportEpsAction);
//        exportPdf.addActionListener(exportPdfAction);
//        exportXml.addActionListener(exportXmlAction);
        help.addActionListener(helpAction);
        exit.addActionListener(exitAction);

        myPanel.add(NewFileButton);
        myPanel.add(saveButton);
        myPanel.add(saveAsButton);
        myPanel.add(openButton);
        myPanel.add(Closebutton);
        myPanel.add(new JSeparator(SwingConstants.VERTICAL));
        myPanel.add(exportEps);
        myPanel.add(help);
        myPanel.add(new JSeparator(SwingConstants.VERTICAL));
        myPanel.add(exit);
        this.toolBarcontent.setToolBarPane(myPanel);
    }

    /**
     * Set close and new tab shortcuts (CTRL+W and CTRL+T).
     *
     * @param myLayout WindowLayoutControler which stores every part of window.
     * @return ToolBarContentControler for further use.
     */
    public ToolBarContentControler setBasicListeners(final WindowLayoutControler myLayout) {
        setCloseAndOpenShortCuts(myLayout);
        return this;
    }

    /**
     * When new file is clicked. Create NewTabController, set diagramPlaces for this tab, add new tab to opened files.
     *
     * @param myLayout WindowLayoutControler for storing this frshly created file.
     */
    public void NewFileButtonMouseClicked(WindowLayoutControler myLayout) {
        NewTabController newTab = new NewTabController();
        newTab.setDiagramPlaces(new DiagramPlacesManager());
        newTab.addNewTab(myLayout);
        this.diagramPlaces.add(newTab.getDiagramPlaces());
    }

    /**
     * Open file method. It will create new tab, insert DiagramPlacesManager from file.
     *
     * @param openedDiagrams DiagramPlacesManager from file
     * @param myLayout WindowLayoutControler for storing this freshly created file.
     */
    public void openedFile(DiagramPlacesManager openedDiagrams, WindowLayoutControler myLayout) {
        NewTabController newTab = new NewTabController();
        newTab.setDiagramPlaces(openedDiagrams);
        newTab.addNewTab(myLayout);
        this.diagramPlaces.add(newTab.getDiagramPlaces());
    }

    /**
     * Clicked on close button. Free diagram places, close button and recalculate index of tab's diagram places manager.
     *
     * @param myLayout WindowLayoutControler.
     */
    public void CloseButtonMouseClicked(WindowLayoutControler myLayout) {
        if (myLayout.getFileTab().getSelectedIndex() != -1) {
            int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to close selected file?", "Please confirm", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                removeDiagramPlaceById(myLayout.getFileTab().getSelectedIndex());
                for (DiagramPlacesManager diagramPlacesManager : diagramPlaces) {
                    if (diagramPlacesManager.getDiagramNumber() > myLayout.getFileTab().getSelectedIndex()) {
                        diagramPlacesManager.setDiagramNumber(diagramPlacesManager.getDiagramNumber() - 1);
                    }
                }
                myLayout.removeTab(myLayout.getSelectedTab());
            }
        }
    }

    /**
     * Method for removing diagramplaces by it's id
     *
     * @param id
     */
    public void removeDiagramPlaceById(int id) {
        for (Iterator<DiagramPlacesManager> it = this.diagramPlaces.iterator(); it.hasNext();) {
            DiagramPlacesManager model = it.next();
            if (model.getDiagramNumber() == id) {
                it.remove();
            }
        }
    }

    /**
     * Method for fetching places based on tab index.
     * 
     * @param id index of diagram places.
     * @return diagram places with specific id.
     */
    public DiagramPlacesManager getDiagramById(int id) {
        if (id < 0) {
            return null;
        }
        return this.diagramPlaces.get(id);
    }

    /**
     * Set listener for new file action.
     * @param newFileAction ActionListener.
     */
    public void setNewFileAction(ActionListener newFileAction) {
        this.newFileAction = newFileAction;
    }

    /**
     * Set listener for close file action.
     * @param closeFileAction ActionListener.
     */
    public void setCloseFileAction(ActionListener closeFileAction) {
        this.closeFileAction = closeFileAction;
    }

    /**
     * Set listener for open file action.
     * @param openFileAction ActionListener.
     */
    public void setOpenFileAction(ActionListener openFileAction) {
        this.openFileAction = openFileAction;
    }

    /**
     * Set listener for axport to EPS action.
     * @param exportEpsAction ActionListener
     */
    public void setExportEpsAction(ActionListener exportEpsAction) {
        this.exportEpsAction = exportEpsAction;
    }

    /**
     * Set listener for axport to EPS action.
     * @param exportPdfAction ActionListener
     */
    public void setExportPdfAction(ActionListener exportPdfAction) {
        this.exportPdfAction = exportPdfAction;
    }

    /**
     * Set listener for axport to XML action.
     * @param exportXmlAction ActionListener.
     */
    public void setExportXmlAction(ActionListener exportXmlAction) {
        this.exportXmlAction = exportXmlAction;
    }

    /**
     * Set listener for axport to save file action.
     * @param saveAction ActionListener.
     */
    public void setSaveAction(ActionListener saveAction) {
        this.saveAction = saveAction;
    }

    /**
     * Set listener for axport to save file as.. action.
     * @param saveAsAction ActionListener.
     */
    public void setSaveAsAction(ActionListener saveAsAction) {
        this.saveAsAction = saveAsAction;
    }

    /**
     * Returns toolbar Content.
     * @return toolBarcontent as ToolBarContentModel;
     */
    public ToolBarContentModel getToolBarcontent() {
        return this.toolBarcontent;
    }

    /**
     * Returns new file action.
     * @return newFileAction as ActionListener.
     */
    public ActionListener getNewFileAction() {
        return newFileAction;
    }

    /**
     * Returns close file action.
     * @return closeFileAction as ActionListener.
     */
    public ActionListener getCloseFileAction() {
        return closeFileAction;
    }

    /**
     * Returns open file action.
     * @return openFileAction as ActionListener.
     */
    public ActionListener getOpenFileAction() {
        return openFileAction;
    }

    /**
     * Returns export to EPS action.
     * @return exportEpsAction as ActionListener.
     */
    public ActionListener getExportEpsAction() {
        return exportEpsAction;
    }

    /**
     * Returns export to PDF action.
     * @return exportPdfAction as ActionListener.
     */
    public ActionListener getExportPdfAction() {
        return exportPdfAction;
    }

    /**
     * Returns export to XML action.
     * @return exportXmlAction as ActionListener.
     */
    public ActionListener getExportXmlAction() {
        return exportXmlAction;
    }

    /**
     * Returns save file action.
     * @return saveAction as ActionListener.
     */
    public ActionListener getSaveAction() {
        return saveAction;
    }

    /**
     * Returns save file as.. action.
     * @return saveAsAction as ActionListener.
     */
    public ActionListener getSaveAsAction() {
        return saveAsAction;
    }

    /**
     * Returns exit application action.
     * @return exitAction as ActionListener.
     */
    public ActionListener getExitAction() {
        return exitAction;
    }

    /**
     * Set exit application action.
     * @param exitAction exitAction to be set as ActionListener.
     */
    public void setExitAction(ActionListener exitAction) {
        this.exitAction = exitAction;
    }

    /**
     * Returns all diagrams, all objects and lines as arrayList.
     * @return diagramPlaces as ArrayList<DiagramPlacesManager>.
     */
    public ArrayList<DiagramPlacesManager> getDiagramPlaces() {
        return diagramPlaces;
    }

    /**
     * Get help action.
     * @return helpAction as ActionListener.
     */
    public ActionListener getHelpAction() {
        return helpAction;
    }

    /** 
     * Set help action.
     * @param helpAction helpAction to be set to ActionListener.
     */
    public void setHelpAction(ActionListener helpAction) {
        this.helpAction = helpAction;
    }

    /**
     * Set close and new file short cuts. CTRL+T for new file, CTRL+W for close file.
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
