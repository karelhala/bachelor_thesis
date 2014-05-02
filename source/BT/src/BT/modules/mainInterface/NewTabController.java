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
 *
 * @author Karel
 */
public class NewTabController {
    
    /**
     * 
     */
    private DiagramPlacesManager diagramPlaces;
    
    /**
     *
     * @param myLayout
     */
    public void addNewTab(final WindowLayoutControler myLayout) {
        UCContentController UCController = new UCContentController();
        UCController.createComponents(diagramPlaces);

        CDContentController CDcontroller = new CDContentController();
        CDcontroller.createComponents(diagramPlaces);

        final PNContentController OOPNContentModel = new PNContentController();
        OOPNContentModel.createComponents(diagramPlaces);
        myLayout.addNewTab(UCController.getUCContent(), CDcontroller.getCdContent(), OOPNContentModel.getPnContent(), diagramPlaces);
        ((JTabbedPane)((CloseTabbedPane)myLayout.getFileTab()).getSelectedComponent()).addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent me) {
                    if (((JTabbedPane)((CloseTabbedPane)myLayout.getFileTab()).getSelectedComponent()).getSelectedIndex() == 2)
                    {
                        if (diagramPlaces.getCdPlaces().getSelectedObject() != null && diagramPlaces.getCdPlaces().getSelectedObject() instanceof CDClass)
                        {
                            if (((CDClass) diagramPlaces.getCdPlaces().getSelectedObject()).getTypeOfClass() != BT.ClassType.INTERFACE)
                            {
                                OOPNContentModel.getPnMain().setPlaces(((CDClass)diagramPlaces.getCdPlaces().getSelectedObject()).getPnNetwork());
                                OOPNContentModel.getPnMain().getMainContent().getDrawingPane().setPlaces(((CDClass)diagramPlaces.getCdPlaces().getSelectedObject()).getPnNetwork());
                                ((PNMainContentInitializer)((PNMainContentInitializer)OOPNContentModel.getPnMain()).setSelectedClass(diagramPlaces.getSelectedClass())).repaintBottomLeft();
                                ((PNMainContentInitializer)((PNMainContentInitializer)OOPNContentModel.getPnMain()).setSelectedClass(diagramPlaces.getSelectedClass())).repaintBottomRight();
                                ((PNDrawingPane)OOPNContentModel.getPnMain().getMainContent().getDrawingPane()).getDrawing().repaint();
                            }
                            else
                            {
                                ((JTabbedPane)((CloseTabbedPane)myLayout.getFileTab()).getSelectedComponent()).setSelectedIndex(1);
                                JOptionPane.showMessageDialog(null, "Selected class is interface, interface can't have petrinet.");
                            }
                        }
                        else
                        {
                            ((JTabbedPane)((CloseTabbedPane)myLayout.getFileTab()).getSelectedComponent()).setSelectedIndex(1);
                            JOptionPane.showMessageDialog(null, "No class has been selected, please select class for objected oriented petrinets.");
                        }
                    }
                }
            });
        this.diagramPlaces.setDiagramNumber(myLayout.getFileTab().getSelectedIndex());
    }

    public DiagramPlacesManager getDiagramPlaces() {
        return diagramPlaces;
    }

    public void setDiagramPlaces(DiagramPlacesManager diagramPlaces) {
        this.diagramPlaces = diagramPlaces;
    }
}
