/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BT.modules.ClassDiagram.mainContent;

import BT.BT.AttributeType;
import BT.managers.CD.Attribute;
import BT.managers.PlaceManager;
import BT.modules.ClassDiagram.places.CDClass;
import GUI.BottomRightContentModel;
import GUI.ClassDiagramAttributesPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

/**
 *
 * @author Karel
 */
public class CDBottomRightController {
    private final CDUseCaseReactivator reactivator;
    private final PlaceManager cdPlaces;
    private final BottomRightContentModel bottomRightContent;
    private final ClassDiagramAttributesPanel attributesPanel;
    private final CDDrawingPane.drawing drawingPane;
    
    public CDBottomRightController(CDUseCaseReactivator reactivator, PlaceManager cdPlaces, BottomRightContentModel bottomRightContent, ClassDiagramAttributesPanel attributesPanel, CDDrawingPane.drawing drawingPane)
    {
        this.reactivator = reactivator;
        this.cdPlaces = cdPlaces;
        this.bottomRightContent = bottomRightContent;
        this.attributesPanel = attributesPanel;
        this.drawingPane = drawingPane;
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
                cdPlaces.deleteAllUnassignedObjects();
                drawingPane.repaint();
            }
        });
        
        bottomRightContent.getReactivateAllButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                reactivator.reactivateAllEmpty();
                drawingPane.repaint();
            }
        });
        
        this.attributesPanel.getAddMethod().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                addNewmethodToClass();
                drawingPane.repaint();
            }
        });
        
        this.attributesPanel.getAddVariable().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                addNewVariableToClass();
                drawingPane.repaint();
            }
        });
        
        this.attributesPanel.getAttributeTypeMethod().addPopupMenuListener(new PopupMenuListener() {

            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent pme) {
                attributesPanel.removeAllObjectsFromAttributeType();
                attributesPanel.addObjectsToAttributeType(cdPlaces.getObjects());
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
                attributesPanel.addObjectsToAttributeType(cdPlaces.getObjects());
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
        
        if (cdPlaces.getSelectedObject() != null && cdPlaces.getSelectedObject() instanceof CDClass)
        {
            CDClass selectedClass = (CDClass) cdPlaces.getSelectedObject();
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
                Attribute newAttribute = new Attribute(selectedAtttributeType, this.attributesPanel.getVariableName().getText()+"()", (String) this.attributesPanel.getAttributeTypeVariable().getSelectedItem());
                selectedClass.addNewVariable(newAttribute);
            }
        }
    }
    
    /**
     * Method for adding new variable to selected class.
     */
    private void addNewmethodToClass()
    {
        
        if (cdPlaces.getSelectedObject() != null && cdPlaces.getSelectedObject() instanceof CDClass)
        {
            CDClass selectedClass = (CDClass) cdPlaces.getSelectedObject();
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
                Attribute newAttribute = new Attribute(selectedAtttributeType, this.attributesPanel.getMethodName().getText(), (String) this.attributesPanel.getAttributeTypeMethod().getSelectedItem());
                selectedClass.addNewMethod(newAttribute);
            }
        }
    }
}
