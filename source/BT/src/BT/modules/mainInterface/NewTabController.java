/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.mainInterface;

import BT.BT;
import BT.managers.DiagramPlacesManager;
import BT.modules.ClassDiagram.CDContentController;
import BT.modules.ClassDiagram.places.CDClass;
import BT.modules.ObjectedOrientedPetriNet.PNContentController;
import BT.modules.ObjectedOrientedPetriNet.mainContent.PNDrawingPane;
import BT.modules.ObjectedOrientedPetriNet.mainContent.PNMainContentInitializer;
import BT.modules.UC.UCContentController;
import GUI.CloseTabbedPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

/**
 * Class for creating new tab of application
 *
 * @author Karel Hala
 */
public class NewTabController {

    /**
     * Each place, object and line is stored here.
     */
    private DiagramPlacesManager diagramPlaces;

    /**
     * Creates new tab. Creates controllers for UseCase, class diagram and petriNets. Add new tab and forbids to change
     * the tab to petriNet if no class or interface is selected. Also sets correct places for petriNet.
     *
     * @param myLayout WindowLayoutControler stores other parts of application. Like toolbar, menu and most importany
     * tabs.
     */
    public void addNewTab(final WindowLayoutControler myLayout) {
        UCContentController UCController = new UCContentController();
        UCController.createComponents(diagramPlaces);

        CDContentController CDcontroller = new CDContentController();
        CDcontroller.createComponents(diagramPlaces);

        final PNContentController OOPNContentModel = new PNContentController();
        OOPNContentModel.createComponents(diagramPlaces);
        myLayout.addNewTab(UCController.getUCContent(), CDcontroller.getCdContent(), OOPNContentModel.getPnContent(), diagramPlaces);
        ((JTabbedPane) ((CloseTabbedPane) myLayout.getFileTab()).getSelectedComponent()).addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                if (((JTabbedPane) ((CloseTabbedPane) myLayout.getFileTab()).getSelectedComponent()).getSelectedIndex() == 2) {
                    if (diagramPlaces.getCdPlaces().getSelectedObject() != null && diagramPlaces.getCdPlaces().getSelectedObject() instanceof CDClass) {
                        if (((CDClass) diagramPlaces.getCdPlaces().getSelectedObject()).getTypeOfClass() != BT.ClassType.INTERFACE) {
                            OOPNContentModel.getPnMain().setPlaces(((CDClass) diagramPlaces.getCdPlaces().getSelectedObject()).getPnNetwork());
                            OOPNContentModel.getPnMain().getMainContent().getDrawingPane().setPlaces(((CDClass) diagramPlaces.getCdPlaces().getSelectedObject()).getPnNetwork());
                            ((PNMainContentInitializer) ((PNMainContentInitializer) OOPNContentModel.getPnMain()).setSelectedClass(diagramPlaces.getSelectedClass())).repaintBottomLeft();
                            ((PNMainContentInitializer) ((PNMainContentInitializer) OOPNContentModel.getPnMain()).setSelectedClass(diagramPlaces.getSelectedClass())).repaintBottomRight();
                            ((PNDrawingPane) OOPNContentModel.getPnMain().getMainContent().getDrawingPane()).getDrawing().repaint();
                        } else {
                            ((JTabbedPane) ((CloseTabbedPane) myLayout.getFileTab()).getSelectedComponent()).setSelectedIndex(1);
                            JOptionPane.showMessageDialog(null, "Selected class is interface, interface can't have petrinet.");
                        }
                    } else {
                        ((JTabbedPane) ((CloseTabbedPane) myLayout.getFileTab()).getSelectedComponent()).setSelectedIndex(1);
                        JOptionPane.showMessageDialog(null, "No class has been selected, please select class for objected oriented petrinets.");
                    }
                }
            }
        });
        this.diagramPlaces.setDiagramNumber(myLayout.getFileTab().getSelectedIndex());
    }

    /**
     * Return diagram places for newly created tab.
     * @return diagramPlaces as DiagramPlacesManager.
     */
    public DiagramPlacesManager getDiagramPlaces() {
        return diagramPlaces;
    }

    /**
     * Sets diagram places for new tab. This is important hence the new tab will handle based on this variable.
     * @param diagramPlaces diagramPlaces will be set as DiagramPlacesManager.
     */
    public void setDiagramPlaces(DiagramPlacesManager diagramPlaces) {
        this.diagramPlaces = diagramPlaces;
    }
}
