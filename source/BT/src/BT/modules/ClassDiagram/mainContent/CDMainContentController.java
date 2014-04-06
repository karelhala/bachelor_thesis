/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ClassDiagram.mainContent;

import BT.BT;
import BT.BT.CDLineType;
import BT.BT.CDObjectType;
import BT.BT.ClassType;
import BT.interfaces.DrawingClicks;
import BT.managers.CD.Attribute;
import BT.managers.DiagramPlacesManager;
import BT.managers.ObjectChecker;
import BT.models.CoordinateModel;
import BT.models.LineModel;
import BT.modules.ClassDiagram.places.CDClass;
import BT.modules.ClassDiagram.places.joinEdge.CDJoinEdgeController;
import GUI.BottomRightContentModel;
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

    private final CDUseCaseConnector useCaseConnector;
    private final CDUseCaseReactivator useCaseReactivator;
    private final BottomRightContentModel BottomRightContent;
    /**
     *
     * @param diagramPlaces
     * @param BottomRightContent
     */
    public CDMainContentController(DiagramPlacesManager diagramPlaces, BottomRightContentModel BottomRightContent) {
        super(diagramPlaces);
        this.BottomRightContent = BottomRightContent;
        this.useCaseConnector = new CDUseCaseConnector(diagramPlaces.getUcPlaces());
        this.useCaseReactivator = new CDUseCaseReactivator(useCaseConnector, diagramPlaces.getCdPlaces());
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
        if (this.LeftBottomContent.getSelectedButton() != null || this.newJoinEdge != null && !this.newJoinEdge.getFirstObject().equals(clickedObject)) {
            clickedOnObject(clickedObject);
        }
        
        if (clickedObject != null) {
            clickedObject.setSelected(true);
            disableButtons(clickedObject);
        }
        else
        {
            disableButtons(null);
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
            ClassType typeOfClass = ((CDClass) pressedObject).getTypeOfClass();
            ((JRadioButton) ((typeOfClass == ClassType.ACTIVITY) ? activity : (typeOfClass == ClassType.NONE)?none:actor)).setSelected(true);
            if (pressedObject.getInJoins().isEmpty() && pressedObject.getOutJoins().isEmpty() && ((CDClass)pressedObject).getTypeOfClass() != ClassType.INTERFACE)
            {
                dialogPanel.add(actor, BorderLayout.LINE_START);
                dialogPanel.add(activity, BorderLayout.CENTER);
                dialogPanel.add(none, BorderLayout.LINE_END);
            }
            this.useCaseConnector.setSelectedModel(pressedObject);
            this.useCaseReactivator.addButtonsToDialog(dialogPanel);
            int result = JOptionPane.showConfirmDialog(null, dialogPanel,
                    "Please Enter name of class and select type", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                pressedObject.setName(nameInput.getText());
                ((CDClass) pressedObject).setTypeOfClass((actor.isSelected() == true) ? ClassType.ACTOR : (activity.isSelected() == true)?ClassType.ACTIVITY:ClassType.NONE);
                this.useCaseConnector.setSelectedModel(pressedObject);
                this.useCaseConnector.createNewUseCaseObject(nameInput.getText());
            }
        }
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

        if (!this.newJoinEdge.isLineEmpty()) {
            if (this.newJoinEdge.getFirstObject().equals(clickedObject)) {
                this.newJoinEdge.setSelected(true);
            } else {
                this.newJoinEdge.setSelected(false);
            }
            
            if (!((CDJoinEdgeController)this.newJoinEdge).areObjectsOfType(ClassType.NONE) 
                    && !((CDJoinEdgeController)this.newJoinEdge).areObjectsOfType(ClassType.INTERFACE))
            {
                if (this.newJoinEdge.getFirstObject().getAssignedObject() != null && this.newJoinEdge.getSecondObject().getAssignedObject() != null)
                {
                    this.useCaseConnector.setNewline(this.newJoinEdge);
                    this.useCaseConnector.createNewUseCaseJoin();
                }
            }
            else
            {
                this.newJoinEdge.setAssignedObject(this.newJoinEdge);
            }
            this.places.addObject(this.newJoinEdge);
            
            this.newJoinEdge = null;
        }
        CDDrawingPane cdDrawing = (CDDrawingPane) this.mainContent.getDrawingPane();
        cdDrawing.setNewLine(this.newJoinEdge);
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
            else
            {
                this.places.setAllObjectDiselected();
                disableButtons(null);
            }
            drawJoinEdge(clickedObject);
        }
    }
    
    /**
     * Method for disabling or reanabling buttons based on selected object
     * @param selectedObject object that has been selected.
     */
    private void disableButtons(CoordinateModel selectedObject)
    {
        if (selectedObject != null && selectedObject instanceof CDClass && ((CDClass)selectedObject).getTypeOfClass() == ClassType.INTERFACE)
        {
            for (CDLineType lineType : CDLineType.values()) {
                if (lineType != CDLineType.GENERALIZATION)
                {
                    this.LeftBottomContent.getButtonWithName(lineType.name()).setEnabled(false);
                    this.LeftBottomContent.getButtonWithName(lineType.name()).setSelected(false);
                }
            }
        }
        else
        {
            for (CDLineType lineType : CDLineType.values()) {
                this.LeftBottomContent.getButtonWithName(lineType.name()).setEnabled(true);
            }
        }
    }
}
