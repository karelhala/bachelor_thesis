/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ClassDiagram.mainContent;

import BT.BT.CDLineType;
import BT.BT.CDObjectType;
import BT.BT.ClassType;
import BT.interfaces.DrawingClicks;
import BT.managers.DiagramPlacesManager;
import BT.managers.ObjectChecker;
import BT.models.CoordinateModel;
import BT.models.LineModel;
import BT.modules.ClassDiagram.places.CDClass;
import BT.modules.ClassDiagram.places.joinEdge.CDJoinEdgeController;
import GUI.BottomLeftContentModel;
import GUI.BottomRightContentModel;
import GUI.ClassDiagramAttributesPanel;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

/**
 *
 * @author Karel Hala
 */
public class CDMainContentController extends CDBottomRightController implements DrawingClicks {

    /**
     *
     * @param diagramPlaces
     * @param bottomRightContent
     * @param attributesPanel
     * @param leftContentModel
     */
    public CDMainContentController(
            DiagramPlacesManager diagramPlaces, 
            BottomRightContentModel bottomRightContent, 
            ClassDiagramAttributesPanel attributesPanel, 
            BottomLeftContentModel leftContentModel) {
        
        super(diagramPlaces, bottomRightContent, attributesPanel);
        this.leftBottomController = new CDBottomLeftController(leftContentModel, ((CDDrawingPane) this.mainContent.getDrawingPane()).getDrawing());
        this.leftContentModel = leftContentModel;
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
        this.leftBottomController.setSelectedObject(null);
        this.leftBottomController.objectSelected();
        JToggleButton selectedItemButton = this.LeftTopContent.getSelectedButton();
        this.places.setAllObjectDiselected();
        if (selectedItemButton != null && CDObjectType.CLASS.name().equals(selectedItemButton.getName())) {
            CDClass newClass = new CDClass(evt.getX(), evt.getY());
            this.places.addObject(newClass);
            this.diagramPlaces.addPnPlace(newClass.getPnNetwork());
            newClass.setAssignedObject(newClass);
        }else if(selectedItemButton != null && CDObjectType.INTERFACE.name().equals(selectedItemButton.getName())){
            CDClass newInterface = new CDClass(evt.getX(), evt.getY());
            newInterface.setTypeOfClass(ClassType.INTERFACE);
            newInterface.setName("interface");
            this.places.addObject(newInterface);
            newInterface.setAssignedObject(newInterface);
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
        this.leftBottomController.setSelectedObject(clickedObject);
        this.leftBottomController.objectSelected();
        this.bottomRightContent.hideAdditionalContent();
        if (clickedObject != null) {
            clickedObject.setSelected(true);
            if (clickedObject instanceof CDClass)
            {
                this.bottomRightContent.showAdditionalContent();
            }
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
        if (pressedObject != null && !(pressedObject instanceof LineModel))
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
        } else if (pressedObject instanceof CDJoinEdgeController)
        {
            JPanel dialogPanel = new JPanel(new BorderLayout());
            CDJoinEdgeController selectedLine = (CDJoinEdgeController) pressedObject;
            if (selectedLine.getJoinEdgeType() == CDLineType.USERINPUT)
            {
                String[] actorClass = { CDLineType.AGGREGATION.name(), CDLineType.COMPOSITION.name()};
                JComboBox selectType = new JComboBox(actorClass);
                dialogPanel.add(selectType);
                int result = JOptionPane.showConfirmDialog(null, dialogPanel,
                    "Please select type of this line", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    selectedLine.setJoinEdgeType((selectType.getSelectedItem().equals(CDLineType.COMPOSITION.name()))?CDLineType.COMPOSITION:CDLineType.AGGREGATION);
                }
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
        if (this.LeftBottomContent.getSelectedButton() != null) {
            this.newJoinEdge = CDJoinEdgeManipulator.createJoinEdge((CDJoinEdgeController) this.newJoinEdge, clickedObject,this.LeftBottomContent.getSelectedButton());
        }

        if (!this.newJoinEdge.isLineEmpty()) {
            if (this.newJoinEdge.getFirstObject().equals(clickedObject)) {
                this.newJoinEdge.setSelected(true);
            } else {
                this.newJoinEdge.setSelected(false);
            }
            
            if (((CDJoinEdgeController)this.newJoinEdge).checkBothObjects())
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
                    if (this.LeftBottomContent.getButtonWithName(lineType.name()) != null)
                    {
                        this.LeftBottomContent.getButtonWithName(lineType.name()).setEnabled(false);
                        this.LeftBottomContent.getButtonWithName(lineType.name()).setSelected(false);
                    }
                }
            }
        }
        else
        {
            for (CDLineType lineType : CDLineType.values()) {
                if (this.LeftBottomContent.getButtonWithName(lineType.name()) != null) {
                    this.LeftBottomContent.getButtonWithName(lineType.name()).setEnabled(true);
                }
            }
        }
    }
}
