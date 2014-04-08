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
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

/**
 *
 * @author Karel Hala
 */
public class ToolBarContentControler {

    private ToolBarContentModel toolBarcontent;
    private ActionListener newFileAction;
    private ActionListener closeFileAction;
    private ActionListener openFileAction;
    private ActionListener exportEpsAction;
    private ActionListener exportPdfAction;
    private ActionListener exportXmlAction;
    private ActionListener saveAction;
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
        JButton NewFileButton = toolBarcontent.addNewButton("New File");
        JButton Closebutton = toolBarcontent.addNewButton("Close File");
        JButton openButton = toolBarcontent.addNewButton("Open File");
        JButton saveButton = toolBarcontent.addNewButton("Save File");
        JButton exportEps = toolBarcontent.addNewButton("Export to Eps");
        JButton exportPdf = toolBarcontent.addNewButton("Export to PDF");
        JButton exportXml = toolBarcontent.addNewButton("Export to XML");
        
        NewFileButton.addActionListener(newFileAction);
        Closebutton.addActionListener(closeFileAction);
        saveButton.addActionListener(saveAction);
        openButton.addActionListener(openFileAction);
        exportEps.addActionListener(exportEpsAction);
        exportPdf.addActionListener(exportPdfAction);
        exportXml.addActionListener(exportXmlAction);
        
        myPanel.add(NewFileButton);
        myPanel.add(saveButton);
        myPanel.add(openButton);
        myPanel.add(Closebutton);
        myPanel.add(new JSeparator(SwingConstants.VERTICAL));
        myPanel.add(exportEps);
        myPanel.add(exportPdf);
        myPanel.add(exportXml);
        this.toolBarcontent.setToolBarPane(myPanel);
    }
    
    /**
     * 
     * @param myLayout
     * @return 
     */
    public ToolBarContentControler setBasicListeners(final WindowLayoutControler myLayout)
    {
        this.newFileAction = new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		NewFileButtonMouseClicked(myLayout);
	    }
	};
        
        this.closeFileAction = new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		CloseButtonMouseClicked(myLayout);
	    }
	};
        
        setCloseAndOpenShortCuts(myLayout);
        return this;
    }

    /**
     *
     * @param evt
     * @param myLayout
     */
    private void NewFileButtonMouseClicked(WindowLayoutControler myLayout) {
        NewTabController newTab = new NewTabController(myLayout);
        diagramPlaces.add(newTab.getDiagramPlaces());
    }

    /**
     *
     * @param evt
     * @param myLayout
     */
    private void CloseButtonMouseClicked(WindowLayoutControler myLayout) {
        myLayout.removeTab(myLayout.getSelectedTab());
        for (Iterator<DiagramPlacesManager> it = diagramPlaces.iterator(); it.hasNext();) {
            DiagramPlacesManager model = it.next();
            if (model.getDiagramNumber() == myLayout.getFileTab().getSelectedIndex()) {
                it.remove();
            }
        }
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
    
    /**
     *
     * @return
     */
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
    
    /**
     *
     * @param myLayout
     */
    private void setCloseAndOpenShortCuts(final WindowLayoutControler myLayout) {
        final ToolBarContentControler parent = this;
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
