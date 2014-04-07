/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BT.modules.ClassDiagram.mainContent;

import BT.managers.CD.Attribute;
import BT.models.CoordinateModel;
import BT.modules.ClassDiagram.places.CDClass;
import GUI.BottomLeftContentModel;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author Karel
 */
public class CDBottomLeftController {
    final private BottomLeftContentModel leftContentmodel;
    final private CDDrawingPane.drawing drawingPane;
    private CoordinateModel selectedObject;
    
    public CDBottomLeftController (BottomLeftContentModel leftContentmodel, CDDrawingPane.drawing drawingPane)
    {
        this.leftContentmodel = leftContentmodel;
        this.drawingPane = drawingPane;
    }

    public CoordinateModel getSelectedObject() {
        return selectedObject;
    }

    public void setSelectedObject(CoordinateModel selectedobject) {
        this.selectedObject = selectedobject;
    }
    
    /**
     * 
     */
    public void objectSelected()
    {
        if (this.selectedObject instanceof CDClass)
        {
            CDClass selectedClass = (CDClass) this.selectedObject;
            for (Attribute oneVariable : selectedClass.getVariables()) {
                this.leftContentmodel.addObjectsToPane(oneVariable.getVisibility().name() + oneVariable.getName() + oneVariable.getType(), new JButton("+"));
            }
            for (Attribute oneMethod : selectedClass.getMethods()) {
                this.leftContentmodel.addObjectsToPane(oneMethod.getVisibility().name() + oneMethod.getName() + oneMethod.getType(), new JButton("+"));
            }
            this.leftContentmodel.getContentPane().revalidate();
        }
    }
}
