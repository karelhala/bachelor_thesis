/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ClassDiagram.mainContent;

import BT.BT;
import BT.BT.CDLineType;
import BT.BT.CDObjectType;
import BT.BT.ClassType;
import BT.BT.UCLineType;
import BT.interfaces.DrawingClicks;
import BT.managers.CD.Attribute;
import BT.managers.DiagramPlacesManager;
import BT.managers.ObjectChecker;
import BT.models.CoordinateModel;
import BT.models.LineModel;
import BT.modules.ClassDiagram.places.CDClass;
import BT.modules.ClassDiagram.places.joinEdge.CDJoinEdgeController;
import BT.modules.UC.places.UCActor;
import BT.modules.UC.places.UCJoinEdge.UCJoinEdgeController;
import BT.modules.UC.places.UCUseCase;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

/**
 *
 * @author Karel Hala
 */
public class CDMainContentController extends CDMainContentModel implements DrawingClicks {

    /**
     *
     * @param diagramPlaces
     */
    public CDMainContentController(DiagramPlacesManager diagramPlaces) {
        super(diagramPlaces);
    }

    /**
     *
     * @param e
     * @param dragged
     */
    @Override
    public void drawingMouseDragged(MouseEvent e, CoordinateModel dragged) {
        CDDrawingPane cdDrawing = (CDDrawingPane) this.mainContent.getDrawingPane();
        if (dragged instanceof CDClass) {
            if (this.newJoinEdge != null) {
                this.newJoinEdge = null;
                cdDrawing.setNewLine(null);
            }
            dragged.setX(e.getX());
            dragged.setY(e.getY());
        } else if (dragged != null) {
            CDJoinEdgeController draggedJoinEdge = (CDJoinEdgeController) dragged;
            if (!draggedJoinEdge.isInRange(e.getX(), e.getY()) && this.newJoinEdge == null) {
                removeLineFromArrayListAndSetNewLine(draggedJoinEdge);
                drawingPanecheckMove(e);
            }
        }
        cdDrawing.getDrawing().repaint();
    }

    /**
     *
     * @param evt
     */
    @Override
    public void drawingPaneClicked(MouseEvent evt) {
        JToggleButton selectedItemButton = this.LeftTopContent.getSelectedButton();
        this.places.setAllObjectDiselected();
        if (selectedItemButton != null && CDObjectType.CLASS.name().equals(selectedItemButton.getName())) {
            CDClass newClass = new CDClass(evt.getX(), evt.getY());
            newClass.addNewVariable(new Attribute(BT.AttributeType.PUBLIC, "variable1", "String"));
            newClass.addNewVariable(new Attribute(BT.AttributeType.PROTECTED, "variable1", "String"));
            newClass.addNewMethod(new Attribute("MethodOne()", "void"));
            this.places.addObject(newClass);
        }else if(selectedItemButton != null && CDObjectType.INTERFACE.name().equals(selectedItemButton.getName())){
            CDClass newInterface = new CDClass(evt.getX(), evt.getY());
            newInterface.setTypeOfClass(ClassType.INTERFACE);
            newInterface.setName("interface");
            this.places.addObject(newInterface);
        } else {
            deleteNewLine();
        }
        ((CDDrawingPane) this.mainContent.getDrawingPane()).getDrawing().repaint();
    }

    /**
     *
     * @param evt
     */
    @Override
    public void drawingPanecheckMove(MouseEvent evt) {
        ObjectChecker objectChecker = new ObjectChecker(this.mainContent.getDrawingPane().getPlaces());
        CoordinateModel coordObject = objectChecker.getObjectUnderMouse(evt.getPoint());
        CDDrawingPane UCdrawing = (CDDrawingPane) this.mainContent.getDrawingPane();
        if (this.newJoinEdge != null) {
            if (this.newJoinEdge.getSecondObject() == null) {
                this.newJoinEdge.setEndPoint(evt.getPoint());
            }
            UCdrawing.setNewLine(this.newJoinEdge);
        }
        if (coordObject != null) {
            coordObject.setHowerColor();
        }
        UCdrawing.getDrawing().repaint();
    }

    /**
     *
     * @param clickedObject
     */
    @Override
    public void setSelectedObject(CoordinateModel clickedObject) {
        this.places.setAllObjectDiselected();
        places.setSelectedLinesOnObject(clickedObject);
        if (clickedObject != null) {
            clickedObject.setSelected(true);
            if (((CDClass)clickedObject).getTypeOfClass() == ClassType.INTERFACE)
            {
                this.LeftBottomContent.getButtonWithName(CDLineType.AGGREGATION.name()).setEnabled(false);
                this.LeftBottomContent.getButtonWithName(CDLineType.ASSOCIATION.name()).setEnabled(false);
                this.LeftBottomContent.getButtonWithName(CDLineType.COMPOSITION.name()).setEnabled(false);
                this.LeftBottomContent.getButtonWithName(CDLineType.REALIZATION.name()).setEnabled(false);
            }
            else
            {
                this.LeftBottomContent.getButtonWithName(CDLineType.AGGREGATION.name()).setEnabled(true);
                this.LeftBottomContent.getButtonWithName(CDLineType.ASSOCIATION.name()).setEnabled(true);
                this.LeftBottomContent.getButtonWithName(CDLineType.COMPOSITION.name()).setEnabled(true);
                this.LeftBottomContent.getButtonWithName(CDLineType.REALIZATION.name()).setEnabled(true);
            }
        }
        else
        {
            this.LeftBottomContent.getButtonWithName(CDLineType.AGGREGATION.name()).setEnabled(true);
            this.LeftBottomContent.getButtonWithName(CDLineType.ASSOCIATION.name()).setEnabled(true);
            this.LeftBottomContent.getButtonWithName(CDLineType.COMPOSITION.name()).setEnabled(true);
            this.LeftBottomContent.getButtonWithName(CDLineType.REALIZATION.name()).setEnabled(true);
        }

        if (this.LeftBottomContent.getSelectedButton() != null || this.newJoinEdge != null) {
            clickedOnObject(clickedObject);
        }
        ((CDDrawingPane) this.mainContent.getDrawingPane()).getDrawing().repaint();
    }

    /**
     *
     * @param pressedObject
     */
    @Override
    public void drawingPaneDoubleCliked(CoordinateModel pressedObject) {
        if (pressedObject != null)
        {
            JTextField nameInput = new JTextField();
            JPanel dialogPanel = new JPanel(new BorderLayout());
            dialogPanel.add(nameInput, BorderLayout.PAGE_START);
            nameInput.setText(pressedObject.getName());
            JRadioButton actor = new JRadioButton("actor");
            JRadioButton activity = new JRadioButton("activity");
            JRadioButton none = new JRadioButton("none");
            ButtonGroup classTypeGroup = new ButtonGroup();
            classTypeGroup.add(activity);
            classTypeGroup.add(actor);
            classTypeGroup.add(none);
            if (pressedObject.getInJoins().isEmpty() && pressedObject.getOutJoins().isEmpty() && ((CDClass)pressedObject).getTypeOfClass() != ClassType.INTERFACE)
            {
                ClassType typeOfClass = ((CDClass) pressedObject).getTypeOfClass();
                ((JRadioButton) ((typeOfClass == ClassType.ACTIVITY) ? activity : (typeOfClass == ClassType.NONE)?none:actor)).setSelected(true);
                dialogPanel.add(actor, BorderLayout.LINE_START);
                dialogPanel.add(activity, BorderLayout.CENTER);
                dialogPanel.add(none, BorderLayout.LINE_END);
            }
            int result = JOptionPane.showConfirmDialog(null, dialogPanel,
                    "Please Enter name of class and select type", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                pressedObject.setName(nameInput.getText());
                ((CDClass) pressedObject).setTypeOfClass((actor.isSelected() == true) ? ClassType.ACTOR : (activity.isSelected() == true)?ClassType.ACTIVITY:ClassType.NONE);
                createNewUseCase(pressedObject, nameInput.getText());
            }
        }
    }
    
    /**
     * 
     * @param pressedObject
     * @param useCaseName 
     */
    private void createNewUseCase(CoordinateModel pressedObject, String useCaseName)
    {
        CDClass selectedClass = (CDClass) pressedObject;
        if (selectedClass.getTypeOfClass()!=ClassType.NONE && selectedClass.getAssignedObject() != null)
        {
            if (selectedClass.getTypeOfClass()==ClassType.ACTOR && !(selectedClass.getAssignedObject() instanceof UCActor))
            {
                this.diagramPlaces.getUcPlaces().removePlace(selectedClass.getAssignedObject());
                createNewActor(selectedClass);
            }
            else if (selectedClass.getTypeOfClass()==ClassType.ACTIVITY && !(selectedClass.getAssignedObject() instanceof UCUseCase))
            {
                this.diagramPlaces.getUcPlaces().removePlace(selectedClass.getAssignedObject());
                createNewUseCase(selectedClass);
            }
            selectedClass.getAssignedObject().setName(useCaseName);
        }
        else if (selectedClass.getTypeOfClass()==ClassType.ACTOR && selectedClass.getAssignedObject() == null)
        {
            createNewActor(selectedClass);
            selectedClass.getAssignedObject().setName(useCaseName);
        }
        else if (selectedClass.getTypeOfClass()==ClassType.ACTIVITY && selectedClass.getAssignedObject() == null)
        {
            createNewUseCase(selectedClass);
            selectedClass.getAssignedObject().setName(useCaseName);
        }
        else if (selectedClass.getTypeOfClass()==ClassType.NONE && selectedClass.getAssignedObject() != null)
        {
            selectedClass.getAssignedObject().setAssignedObject(null);
            selectedClass.setAssignedObject(null);
        }
    }
    
    /**
     * 
     * @param selectedClass 
     */
    private void createNewActor(CDClass selectedClass)
    {
        UCActor newActor = new UCActor(selectedClass.getX(), selectedClass.getY());
        this.diagramPlaces.getUcPlaces().addObject(newActor);
        selectedClass.setAssignedObject(newActor);
        selectedClass.getAssignedObject().setAssignedObject(selectedClass);
    }
    
    /**
     * 
     * @param selectedClass 
     */
    private void createNewUseCase(CDClass selectedClass)
    {
        UCUseCase newUsecase = new UCUseCase(selectedClass.getX(), selectedClass.getY());
        this.diagramPlaces.getUcPlaces().addObject(newUsecase);
        selectedClass.setAssignedObject(newUsecase);
        selectedClass.getAssignedObject().setAssignedObject(selectedClass);
    }

    /**
     *
     */
    @Override
    public void buttonsChanged() {
        JToggleButton selectedJoinEdgeButton = this.LeftBottomContent.getSelectedButton();
        CDDrawingPane cdDrawing = (CDDrawingPane) this.mainContent.getDrawingPane();
        if (selectedJoinEdgeButton == null) {
            this.newJoinEdge = null;
            cdDrawing.setNewLine(null);
        } else if (this.newJoinEdge != null) {
            CDJoinEdgeManipulator.changeLineTypeByButton(this.LeftBottomContent.getSelectedButton(), (CDJoinEdgeController) this.newJoinEdge);
        }
        cdDrawing.getDrawing().repaint();
    }

    /**
     *
     * @param clickedObject
     */
    public void drawJoinEdge(CoordinateModel clickedObject) {
        this.newJoinEdge = CDJoinEdgeManipulator.createJoinEdge((CDJoinEdgeController) this.newJoinEdge, clickedObject);
        if (this.LeftBottomContent.getSelectedButton() != null) {
            CDJoinEdgeManipulator.changeLineTypeByButton(this.LeftBottomContent.getSelectedButton(), (CDJoinEdgeController) this.newJoinEdge);
        }

        if (this.newJoinEdge.getFirstObject() != null && this.newJoinEdge.getSecondObject() != null) {
            if (this.newJoinEdge.getFirstObject().equals(clickedObject)) {
                this.newJoinEdge.setSelected(true);
            } else {
                this.newJoinEdge.setSelected(false);
            }
            this.places.addObject(this.newJoinEdge);
            if (((CDClass)this.newJoinEdge.getSecondObject()).getTypeOfClass() != ClassType.NONE && ((CDClass)this.newJoinEdge.getFirstObject()).getTypeOfClass() != ClassType.NONE)
            {
                createNewUseCaseJoin((CDJoinEdgeController) this.newJoinEdge);
            }            
            this.newJoinEdge = null;
        }
        CDDrawingPane cdDrawing = (CDDrawingPane) this.mainContent.getDrawingPane();
        cdDrawing.setNewLine(newJoinEdge);
    }
    
    /**
     * 
     * @param cdJoin 
     */
    private void createNewUseCaseJoin(CDJoinEdgeController cdJoin)
    {
        UCJoinEdgeController newLine = new UCJoinEdgeController(cdJoin.getFirstObject().getAssignedObject(), cdJoin.getSecondObject().getAssignedObject());
        if (cdJoin.getJoinEdgeType() == CDLineType.ASSOCIATION)
        {
            newLine.setJoinEdgeType(UCLineType.ASSOCIATION);
        }
        else if (cdJoin.getJoinEdgeType() == CDLineType.GENERALIZATION)
        {
            newLine.setJoinEdgeType(UCLineType.GENERALIZATION);
        }
        else if (cdJoin.getJoinEdgeType() == CDLineType.AGGREGATION)
        {
            System.out.println("aggregation");
        }
        else if (cdJoin.getJoinEdgeType() == CDLineType.COMPOSITION)
        {
            System.out.println("composition");
        }
        else if (cdJoin.getJoinEdgeType() == CDLineType.REALIZATION)
        {
            System.out.println("realization");
        }
        newLine.setAssignedObject(cdJoin);
        cdJoin.setAssignedObject(newLine);
        this.diagramPlaces.getUcPlaces().addObject(newLine);
    }

    /**
     *
     * @param clickedObject
     */
    private void clickedOnObject(CoordinateModel clickedObject) {
        if (clickedObject == null || clickedObject instanceof LineModel) {
            this.newJoinEdge = null;
            this.mainContent.getDrawingPane().setNewLine(null);
        } else {
            if (this.newJoinEdge != null && (this.newJoinEdge.getFirstObject()==null || this.newJoinEdge.getFirstObject().equals(clickedObject))) {
                this.newJoinEdge = null;
            }
            drawJoinEdge(clickedObject);
        }
    }
}
