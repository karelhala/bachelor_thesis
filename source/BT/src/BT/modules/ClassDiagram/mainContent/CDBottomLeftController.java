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
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        this.leftContentmodel.getContentPane().removeAll();
        this.leftContentmodel.getContentPane().repaint();
        this.leftContentmodel.getContentPane().revalidate();
        if (this.selectedObject instanceof CDClass)
        {
            CDClass selectedClass = (CDClass) this.selectedObject;
            for (Attribute oneVariable : selectedClass.getVariables()) {
                addAttributeWithButton(oneVariable);
            }
            for (Attribute oneMethod : selectedClass.getMethods()) {
                addAttributeWithButton(oneMethod);
            }
            this.leftContentmodel.getContentPane().revalidate();
        }
    }
    
    private void addAttributeWithButton(final Attribute insertedAttribute)
    {
        JButton deleteButton = new JButton("x");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                ((CDClass) selectedObject).removeAttribute(insertedAttribute);
                objectSelected();
                drawingPane.repaint();
            }
        });
        this.leftContentmodel.addObjectsToPane(insertedAttribute.getVisibility().name()+ " " + insertedAttribute.getName() + ":" + insertedAttribute.getType(), deleteButton);
    }
}
