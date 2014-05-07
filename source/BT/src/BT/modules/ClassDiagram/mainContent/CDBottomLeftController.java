/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ClassDiagram.mainContent;

import BT.managers.CD.Attribute;
import BT.managers.CD.Method;
import BT.managers.DiagramPlacesManager;
import BT.models.CoordinateModel;
import BT.modules.ClassDiagram.places.CDClass;
import GUI.BottomLeftContentModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * Class for controlling bottom left content. Such as methods and variables.
 *
 * @author Karel
 */
public class CDBottomLeftController {

    /**
     * Model that describes where components are located.
     */
    final private BottomLeftContentModel leftContentmodel;
    /**
     * Drawing pane for additional redrawing and editing.
     */
    final private CDDrawingPane.drawing drawingPane;
    /**
     * Object that is selected is stored in this.
     */
    private CoordinateModel selectedObject;
    /**
     * Every place from file, thisis because of petrinets for methods.
     */
    private DiagramPlacesManager diagramPlaces;

    /**
     * Basic constructor. Sets leftContentmodel, drawingPane.
     *
     * @param leftContentmodel model that describes how components are aligned.
     * @param drawingPane class diagram drawing pane.
     */
    public CDBottomLeftController(BottomLeftContentModel leftContentmodel, CDDrawingPane.drawing drawingPane) {
        this.leftContentmodel = leftContentmodel;
        this.drawingPane = drawingPane;
    }

    /**
     * Get object that is selected for this component.
     */
    public CoordinateModel getSelectedObject() {
        return selectedObject;
    }

    /**
     * Set object that is selected for this component.
     */
    public void setSelectedObject(CoordinateModel selectedobject) {
        this.selectedObject = selectedobject;
    }

    /**
     * Get drawing pane of class diagram.
     */
    public DiagramPlacesManager getDiagramPlaces() {
        return diagramPlaces;
    }

    /**
     * Set all diagram places, this is mainly beacuse inserting new petriNet.
     */
    public void setDiagramPlaces(DiagramPlacesManager diagramPlaces) {
        this.diagramPlaces = diagramPlaces;
    }

    /**
     * If object has been selected, delete all variables and methods. Delete all attributes and repaint it based on
     * selected class.
     */
    public void objectSelected() {
        this.leftContentmodel.getContentPane().removeAll();
        this.leftContentmodel.getContentPane().repaint();
        this.leftContentmodel.getContentPane().revalidate();
        if (this.selectedObject instanceof CDClass) {
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

    /**
     * Method for adding each attribute with delete button.
     *
     * @param insertedAttribute Attribute to be inserted on content Pane.
     */
    private void addAttributeWithButton(final Attribute insertedAttribute) {
        JButton deleteButton = new JButton("x");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                ((CDClass) selectedObject).removeAttribute(insertedAttribute);
                objectSelected();
                if (insertedAttribute instanceof Method) {
                    diagramPlaces.removePnPlace(((Method) insertedAttribute).getPetriNet());
                }
                drawingPane.repaint();
            }
        });
        this.leftContentmodel.addObjectsToPane(insertedAttribute, deleteButton);
    }
}
