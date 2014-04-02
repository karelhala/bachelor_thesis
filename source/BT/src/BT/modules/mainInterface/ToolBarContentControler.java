/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.mainInterface;

import BT.managers.DiagramPlacesManager;
import BT.managers.PlaceManager;
import BT.modules.ClassDiagram.CDContentController;
import BT.modules.ClassDiagram.places.CDClass;
import BT.modules.ObjectedOrientedPetriNet.PNContentController;
import BT.modules.ObjectedOrientedPetriNet.mainContent.PNDrawingPane;
import BT.modules.UC.UCContentController;
import GUI.CloseTabbedPane;
import GUI.MainContentModel;
import GUI.ToolBarContentModel;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;

/**
 *
 * @author Karel Hala
 */
public class ToolBarContentControler {

    private ToolBarContentModel toolBarcontent;
    private MouseAdapter newFileMouseClicked;

    /**
     *
     */
    public ToolBarContentControler() {
        this.toolBarcontent = new ToolBarContentModel();
    }

    /**
     *
     * @param myLayout
     */
    public void addBasicButtons(final WindowLayoutControler myLayout) {
        JPanel myPanel = this.toolBarcontent.getToolBarPane();
        JButton NewFileButton = toolBarcontent.addNewButton("New File");
        JButton Closebutton = toolBarcontent.addNewButton("Close File");

        this.newFileMouseClicked = new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                NewFileButtonMouseClicked(evt, myLayout);
            }
        };
        NewFileButton.addMouseListener(this.newFileMouseClicked);

        Closebutton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CloseButtonMouseClicked(evt, myLayout);
            }
        });
        myPanel.add(NewFileButton);
        myPanel.add(Closebutton);
        this.toolBarcontent.setToolBarPane(myPanel);
        setCloseAndOpenShortCuts(myLayout);
    }

    /**
     *
     * @param evt
     * @param myLayout
     */
    private void NewFileButtonMouseClicked(MouseEvent evt, WindowLayoutControler myLayout) {
        addNewTab(myLayout);
    }

    /**
     *
     * @param myLayout
     */
    private void addNewTab(final WindowLayoutControler myLayout) {
        final DiagramPlacesManager diagramPlaces = new DiagramPlacesManager();
        UCContentController UCController = new UCContentController();
        UCController.createComponents(diagramPlaces);

        CDContentController CDcontroller = new CDContentController();
        CDcontroller.createComponents(diagramPlaces);

        final PNContentController OOPNContentModel = new PNContentController();
        OOPNContentModel.createComponents(diagramPlaces);
        myLayout.addNewTab(UCController.getUCContent(), CDcontroller.getCdContent(), OOPNContentModel.getPnContent());
        ((JTabbedPane)((CloseTabbedPane)myLayout.getFileTab()).getSelectedComponent()).addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent me) {
                    if (((JTabbedPane)((CloseTabbedPane)myLayout.getFileTab()).getSelectedComponent()).getSelectedIndex() == 2)
                    {
                        if (diagramPlaces.getCdPlaces().getSelectedObject() != null && diagramPlaces.getCdPlaces().getSelectedObject() instanceof CDClass)
                        {
                            OOPNContentModel.getPnMain().setPlaces(((CDClass)diagramPlaces.getCdPlaces().getSelectedObject()).getPnNetwork());
                            OOPNContentModel.getPnMain().getMainContent().getDrawingPane().setPlaces(((CDClass)diagramPlaces.getCdPlaces().getSelectedObject()).getPnNetwork());
                            ((PNDrawingPane)OOPNContentModel.getPnMain().getMainContent().getDrawingPane()).getDrawing().repaint();
                        }
                        else
                        {
                            ((JTabbedPane)((CloseTabbedPane)myLayout.getFileTab()).getSelectedComponent()).setSelectedIndex(1);
                            JOptionPane.showMessageDialog(null, "No class has been selected, please select class for objected oriented petrinets.");
//                            OOPNContentModel.getPnMain().getMainContent().getDrawingPane().setPlaces(diagramPlaces.getPnPlaces());
//                            OOPNContentModel.getPnMain().setPlaces(diagramPlaces.getPnPlaces());
//                            ((PNDrawingPane)OOPNContentModel.getPnMain().getMainContent().getDrawingPane()).getDrawing().repaint();
                        }
                    }
                }
            });
    }

    /**
     *
     * @param evt
     * @param myLayout
     */
    private void CloseButtonMouseClicked(MouseEvent evt, WindowLayoutControler myLayout) {
        myLayout.removeTab(myLayout.getSelectedTab());
    }

    /**
     *
     * @return
     */
    public ToolBarContentModel getToolBarcontent() {
        return this.toolBarcontent;
    }

    /**
     *
     * @return
     */
    public MouseAdapter getNewFileMouseClicked() {
        return this.newFileMouseClicked;
    }

    /**
     *
     * @param myLayout
     */
    private void setCloseAndOpenShortCuts(final WindowLayoutControler myLayout) {
        myLayout.getFileTab().getActionMap().put("closeTab", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myLayout.removeTab(myLayout.getSelectedTab());
            }
        }
        );

        myLayout.getFileTab().getActionMap().put("newTab", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewTab(myLayout);
            }
        }
        );

        InputMap inputMap = myLayout.getFileTab().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK), "closeTab");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_MASK), "newTab");
    }
}
