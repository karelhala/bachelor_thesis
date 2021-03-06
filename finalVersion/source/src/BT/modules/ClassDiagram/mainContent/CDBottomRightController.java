/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ClassDiagram.mainContent;

import BT.BT.AttributeType;
import BT.managers.CD.Attribute;
import BT.managers.CD.Method;
import BT.managers.DiagramPlacesManager;
import BT.modules.ClassDiagram.places.CDClass;
import BT.modules.ObjectedOrientedPetriNet.places.PNPlace;
import GUI.BottomRightContentModel;
import GUI.ClassDiagramAttributesPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

/**
 * Class for controlling right bottom Panel. This class controlls right bottom panel in class diagram. Sets buttons, and
 * fields in right bottom panel. This class is put between main content controller and it's model because it has lot of
 * to set.
 *
 * @author Karel Hala
 */
abstract public class CDBottomRightController extends CDMainContentModel {

    /**
     * Here is stored drawing pane of class diagram. Used mainly for redrawing.
     */
    private final CDDrawingPane.drawing drawingPane;

    /**
     * Basic constructor. Sets diagram places, bottom right content model, attributes panel. Sets drawing to class
     * diagram drawing pane. Add buttons with listeners to pane.
     * @param diagramPlaces
     * @param bottomRightContent
     * @param attributesPanel
     */
    public CDBottomRightController(DiagramPlacesManager diagramPlaces, BottomRightContentModel bottomRightContent, ClassDiagramAttributesPanel attributesPanel) {
        super(diagramPlaces, bottomRightContent, attributesPanel);
        this.drawingPane = ((CDDrawingPane) this.mainContent.getDrawingPane()).getDrawing();
        addButtonListeners();
    }

    /**
     * Method for adding buttons with listeners to content pane. Delete all inactive, reactivate all inactive, add new
     * cariable, add new method buttons are set.
     */
    private void addButtonListeners() {
        bottomRightContent.getTopButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                places.deleteAllUnassignedObjects();
                drawingPane.repaint();
            }
        });

        bottomRightContent.getBottomButton().addActionListener(new ActionListener() {
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
     * Method for adding new variable to selected class. When button for add new variable to class is pressed this
     * method is called. Checks every string and selectbox if has text in it. Get visibility based on string [-,+,#]. If
     * every field is correct, insert it in class and redraw.
     */
    private void addNewVariableToClass() {
        if (places.getSelectedObject() != null && places.getSelectedObject() instanceof CDClass) {
            CDClass selectedClass = (CDClass) places.getSelectedObject();
            AttributeType selectedAtttributeType = getVisibilityOfAttributeFromString(
                    attributesPanel.getVisibilityVariable().getSelectedItem().toString());
            if (selectedAtttributeType != null && !"".equals(this.attributesPanel.getVariableName().getText())) {
                if (this.attributesPanel.getAttributeTypeVariable().getSelectedItem() != null) {
                    Attribute newAttribute = new Attribute(
                            selectedAtttributeType,
                            this.attributesPanel.getVariableName().getText(),
                            (String) this.attributesPanel.getAttributeTypeVariable().getSelectedItem()
                    );
                    selectedClass.addNewVariable(newAttribute);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "No class selected please select class.");
        }
    }

    /**
     * Method for adding new variable to selected class. If button for inserting new method is pressed, call this
     * method. Get visibility based on string [-,+,#]. If every field is correct, insert it in class, create new petri
     * net this petrinet insert in diagramPlaces, insert new non editable and non removable place "return" to this new
     * petri net.
     */
    private void addNewmethodToClass() {
        if (places.getSelectedObject() != null && places.getSelectedObject() instanceof CDClass) {
            CDClass selectedClass = (CDClass) places.getSelectedObject();
            AttributeType selectedAtttributeType = getVisibilityOfAttributeFromString(
                    attributesPanel.getVisibilityMethod().getSelectedItem().toString());
            if (selectedAtttributeType != null && !"".equals(this.attributesPanel.getMethodName().getText())) {
                String selectedType = (String) this.attributesPanel.getAttributeTypeMethod().getSelectedItem();
                selectedType = (selectedType == null) ? "void" : selectedType;
                Method newMethod = new Method(
                        selectedAtttributeType,
                        this.attributesPanel.getMethodName().getText(),
                        selectedType,
                        selectedClass
                );
                newMethod.getPetriNet().setAssignedmethod(newMethod);
                PNPlace returnPlace = new PNPlace(70, 40);
                returnPlace.setName("return");
                returnPlace.setEditable(Boolean.FALSE);
                returnPlace.setRemovable(Boolean.FALSE);
                newMethod.getPetriNet().addObject(returnPlace);
                newMethod.loadClassAttributes().loadClassMethods();
                selectedClass.addNewMethod(newMethod);
                this.diagramPlaces.addPnPlace(newMethod.getPetriNet());
            }
        } else {
            JOptionPane.showMessageDialog(null, "No class selected please select class.");
        }
    }

    /**
     * This will parse string and return visibility type. Swithc over parsed string and checks if string is "-" or "+"
     * or "#" and return right visibility type.
     *
     * @param stringType visibility string.
     * @return AttributeType[PRIVATE, PUBLIC, PROTECTED].
     */
    private AttributeType getVisibilityOfAttributeFromString(String stringType) {
        AttributeType parsedType = null;
        switch (stringType) {
            case "-":
                parsedType = AttributeType.PRIVATE;
                break;
            case "+":
                parsedType = AttributeType.PUBLIC;
                break;
            case "#":
                parsedType = AttributeType.PROTECTED;
                break;
        }
        return parsedType;
    }
}
