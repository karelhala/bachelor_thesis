/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BT.modules.ClassDiagram.mainContent;

import BT.BT.AttributeType;
import BT.managers.CD.Attribute;
import BT.managers.DiagramPlacesManager;
import BT.modules.ClassDiagram.places.CDClass;
import GUI.BottomRightContentModel;
import GUI.ClassDiagramAttributesPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

/**
 *
 * @author Karel
 */
abstract public class CDBottomRightController extends CDMainContentModel {
    private final CDDrawingPane.drawing drawingPane;
    
    public CDBottomRightController(DiagramPlacesManager diagramPlaces, BottomRightContentModel bottomRightContent, ClassDiagramAttributesPanel attributesPanel)
    {
        super(diagramPlaces, bottomRightContent, attributesPanel);
        this.drawingPane = ((CDDrawingPane) this.mainContent.getDrawingPane()).getDrawing();
        addButtonListeners();
    }
    
    /**
     * Method for adding button listeners to every component.
     */
    private void addButtonListeners()
    {
        bottomRightContent.getDeleteAllNonValidButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                places.deleteAllUnassignedObjects();
                drawingPane.repaint();
            }
        });
        
        bottomRightContent.getReactivateAllButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                useCaseReactivator.reactivateAllEmpty();
                drawingPane.repaint();
            }
        });
        
        this.attributesPanel.getAddMethod().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                addNewmethodToClass();
                leftBottomController.setSelectedObject(places.getSelectedObject());
                leftBottomController.objectSelected();
                drawingPane.repaint();
            }
        });
        
        this.attributesPanel.getAddVariable().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                addNewVariableToClass();
                leftBottomController.setSelectedObject(places.getSelectedObject());
                leftBottomController.objectSelected();
                drawingPane.repaint();
            }
        });
        
        this.attributesPanel.getAttributeTypeMethod().addPopupMenuListener(new PopupMenuListener() {

            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent pme) {
                attributesPanel.removeAllObjectsFromAttributeType();
                attributesPanel.addObjectsToAttributeType(places.getObjects());
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent pme) {
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent pme) {
                attributesPanel.removeAllObjectsFromAttributeType();
            }
        });
        
        this.attributesPanel.getAttributeTypeVariable().addPopupMenuListener(new PopupMenuListener() {

            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent pme) {
                attributesPanel.removeAllObjectsFromAttributeType();
                attributesPanel.addObjectsToAttributeType(places.getObjects());
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent pme) {
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent pme) {
                attributesPanel.removeAllObjectsFromAttributeType();
            }
        });
    }
    
    /**
     * Method for adding new variable to selected class.
     */
    private void addNewVariableToClass()
    {
        
        if (places.getSelectedObject() != null && places.getSelectedObject() instanceof CDClass)
        {
            CDClass selectedClass = (CDClass) places.getSelectedObject();
            AttributeType selectedAtttributeType = null;
            if (attributesPanel.getVisibilityVariable().getSelectedItem() == "-")
            {
                selectedAtttributeType = AttributeType.PRIVATE;
            }
            else if (attributesPanel.getVisibilityVariable().getSelectedItem() == "+")
            {
                selectedAtttributeType = AttributeType.PUBLIC;
            }
            else if (attributesPanel.getVisibilityVariable().getSelectedItem() == "#")
            {
                selectedAtttributeType = AttributeType.PROTECTED;
            }
            if (selectedAtttributeType != null)
            {
                Attribute newAttribute = new Attribute(selectedAtttributeType, this.attributesPanel.getVariableName().getText(), (String) this.attributesPanel.getAttributeTypeVariable().getSelectedItem());
                selectedClass.addNewVariable(newAttribute);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No class selected please select class.");
        }
    }
    
    /**
     * Method for adding new variable to selected class.
     */
    private void addNewmethodToClass()
    {
        
        if (places.getSelectedObject() != null && places.getSelectedObject() instanceof CDClass)
        {
            CDClass selectedClass = (CDClass) places.getSelectedObject();
            AttributeType selectedAtttributeType = null;
            if (attributesPanel.getVisibilityMethod().getSelectedItem() == "-")
            {
                selectedAtttributeType = AttributeType.PRIVATE;
            }
            else if (attributesPanel.getVisibilityMethod().getSelectedItem() == "+")
            {
                selectedAtttributeType = AttributeType.PUBLIC;
            }
            else if (attributesPanel.getVisibilityMethod().getSelectedItem() == "#")
            {
                selectedAtttributeType = AttributeType.PROTECTED;
            }
            if (selectedAtttributeType != null)
            {
                Attribute newAttribute = new Attribute(selectedAtttributeType, this.attributesPanel.getMethodName().getText()+"()", (String) this.attributesPanel.getAttributeTypeMethod().getSelectedItem());
                selectedClass.addNewMethod(newAttribute);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No class selected please select class.");
        }
    }
}