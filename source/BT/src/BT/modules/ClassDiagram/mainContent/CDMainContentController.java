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
 * This class controlls main content Panel. This class handles calling of mouse clicks and mouse movements. It will
 * create and change objects on class diagram drawing panel. It also handles creating and joining objects on class
 * diagram drawing pane. It handels creating of new usecases by calling right method from CDUseCaseConnector.
 *
 * @author Karel Hala
 */
public class CDMainContentController extends CDBottomRightController implements DrawingClicks {

    /**
     * Basic constructor. It sends to his parent DiagramPlacesManager, BottomRightContentModel,
     * ClassDiagramAttributesPanel and create new leftBottomController, set left content model to
     * BottomLeftContentModel.
     *
     * @param diagramPlaces all places of diagram, this will be used in it's parrent.
     * @param bottomRightContent panel for handeling right part of class diagram.
     * @param attributesPanel leftPanel with adding new variables and methods.
     * @param leftContentModel model for setting bottom left model with reactivate buttons and additional content.
     */
    public CDMainContentController(
            DiagramPlacesManager diagramPlaces,
            BottomRightContentModel bottomRightContent,
            ClassDiagramAttributesPanel attributesPanel,
            BottomLeftContentModel leftContentModel) {

        super(diagramPlaces, bottomRightContent, attributesPanel);
        this.leftBottomController = new CDBottomLeftController(leftContentModel, ((CDDrawingPane) this.mainContent.getDrawingPane()).getDrawing());
        this.leftBottomController.setDiagramPlaces(diagramPlaces);
        this.leftContentModel = leftContentModel;
    }

    /**
     * When mouse is being dragged on drawing pane. This method will check for type of dragged object and change it's
     * coordinates.
     *
     * @param e MouseEvent, storring where mouse is located and what is being done.
     * @param dragged CoordinateModel that is being dragged on drawing pane.
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
     * This method is called when clicked on class diagram drawing. When mouse is clicked (pressed and released), this
     * method will either create new object or select object.
     *
     * @param evt coordinates of clicked mouse.
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
            newClass.getPnNetwork().setAssignedClass(newClass);
            this.diagramPlaces.addPnPlace(newClass.getPnNetwork());
            newClass.setAssignedObject(newClass);
        } else if (selectedItemButton != null && CDObjectType.INTERFACE.name().equals(selectedItemButton.getName())) {
            CDClass newInterface = new CDClass(evt.getX(), evt.getY());
            newInterface.getPnNetwork().setAssignedClass(newInterface);
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
     * This is called when mouse moves on class diagram drawing. It highlights objects when hover over them.
     *
     * @param evt coordinates of mouse.
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
     * When object is selected in class diagram. This method will handle additional components to be shown based on type
     * of selected class or if line is clicked.
     *
     * @param clickedObject selected object (CoordinateModel).
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
        this.bottomRightContent.showAdditionalContent(false);
        if (clickedObject != null) {
            clickedObject.setSelected(true);
            if (clickedObject instanceof CDClass) {
                this.bottomRightContent.showAdditionalContent(true);
            }
            disableButtons(clickedObject);
        } else {
            disableButtons(null);
        }
        ((CDDrawingPane) this.mainContent.getDrawingPane()).getDrawing().repaint();
    }

    /**
     * When object is double clicked on class diagram. This method will bring dialog for setting objects being double
     * clicked.
     *
     * @param pressedObject double clicked object (CoordinateModel).
     */
    @Override
    public void drawingPaneDoubleCliked(CoordinateModel pressedObject) {
        if (pressedObject != null && !(pressedObject instanceof LineModel)) {
            JTextField nameInput = new JTextField();
            JPanel dialogPanel = new JPanel(new BorderLayout());
            dialogPanel.add(nameInput, BorderLayout.PAGE_START);
            nameInput.setText(pressedObject.getName());
            JRadioButton actor = new JRadioButton("actor", false);
            JRadioButton activity = new JRadioButton("activity", false);
            JRadioButton none = new JRadioButton("none", false);
            ButtonGroup classTypeGroup = new ButtonGroup();
            classTypeGroup.add(activity);
            classTypeGroup.add(actor);
            classTypeGroup.add(none);
            if (((CDClass) pressedObject).getTypeOfClass() != ClassType.INTERFACE) {
                ClassType typeOfClass = ((CDClass) pressedObject).getTypeOfClass();
                ((JRadioButton) ((typeOfClass == ClassType.ACTIVITY) ? activity : (typeOfClass == ClassType.NONE) ? none : actor)).setSelected(true);
                if (pressedObject.getInJoins().isEmpty() && pressedObject.getOutJoins().isEmpty() && ((CDClass) pressedObject).getTypeOfClass() != ClassType.INTERFACE) {
                    dialogPanel.add(actor, BorderLayout.LINE_START);
                    dialogPanel.add(activity, BorderLayout.CENTER);
                    dialogPanel.add(none, BorderLayout.LINE_END);
                }
            }
            this.useCaseConnector.setSelectedModel(pressedObject);
            this.useCaseReactivator.addButtonsToDialog(dialogPanel);
            int result = JOptionPane.showConfirmDialog(null, dialogPanel,
                    "Please Enter name of class and select type", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                pressedObject.setName(nameInput.getText());
                ClassType selectedType;
                if (actor.isSelected() == true) {
                    selectedType = ClassType.ACTOR;
                } else if (activity.isSelected() == true) {
                    selectedType = ClassType.ACTIVITY;
                } else if (none.isSelected() == true) {
                    selectedType = ClassType.NONE;
                } else {
                    selectedType = ClassType.INTERFACE;
                }
                ((CDClass) pressedObject).setTypeOfClass(selectedType);
                this.useCaseConnector.setSelectedModel(pressedObject);
                this.useCaseConnector.createNewUseCaseObject(nameInput.getText());
            }
        } else if (pressedObject instanceof CDJoinEdgeController) {
            JPanel dialogPanel = new JPanel(new BorderLayout());
            CDJoinEdgeController selectedLine = (CDJoinEdgeController) pressedObject;
            if (selectedLine.getJoinEdgeType() == CDLineType.USERINPUT) {
                String[] actorClass = {CDLineType.AGGREGATION.name(), CDLineType.COMPOSITION.name()};
                JComboBox selectType = new JComboBox(actorClass);
                dialogPanel.add(selectType);
                int result = JOptionPane.showConfirmDialog(null, dialogPanel,
                        "Please select type of this line", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    selectedLine.setJoinEdgeType((selectType.getSelectedItem().equals(CDLineType.COMPOSITION.name())) ? CDLineType.COMPOSITION : CDLineType.AGGREGATION);
                }
            }
        }
    }

    /**
     * When buttons are changed in class diagram. When buttons from left content are changed, fire this method. It will
     * change style of join edge between classes.
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
     * Method for drawing join edge on class diagram. It checks if new line can be drawn. If both objects are set for
     * join edge, add this new line to placeManager for class diagram.
     *
     * @param clickedObject clicked class, it will be stored either on first object or second object place for join
     * edge.
     */
    public void drawJoinEdge(CoordinateModel clickedObject) {
        if (this.LeftBottomContent.getSelectedButton() != null) {
            this.newJoinEdge = CDJoinEdgeManipulator.createJoinEdge((CDJoinEdgeController) this.newJoinEdge, clickedObject, this.LeftBottomContent.getSelectedButton());
        }

        if (!this.newJoinEdge.isLineEmpty()) {
            if (this.newJoinEdge.getFirstObject().equals(clickedObject)) {
                this.newJoinEdge.setSelected(true);
            } else {
                this.newJoinEdge.setSelected(false);
            }

            if (((CDJoinEdgeController) this.newJoinEdge).checkBothObjects()) {
                if (this.newJoinEdge.getFirstObject().getAssignedObject() != null && this.newJoinEdge.getSecondObject().getAssignedObject() != null) {
                    this.useCaseConnector.setNewline(this.newJoinEdge);
                    this.useCaseConnector.createNewUseCaseJoin();
                }
            } else {
                this.newJoinEdge.setAssignedObject(this.newJoinEdge);
            }
            this.places.addObject(this.newJoinEdge);

            this.newJoinEdge = null;
        }
        CDDrawingPane cdDrawing = (CDDrawingPane) this.mainContent.getDrawingPane();
        cdDrawing.setNewLine(this.newJoinEdge);
    }

    /**
     * Method fired when clicked on class. When clicked on class, either delete new line or change new line based on
     * clicked object.
     *
     * @param clickedObject object, that was clicked on.
     */
    private void clickedOnObject(CoordinateModel clickedObject) {
        if (clickedObject == null || clickedObject instanceof LineModel) {
            this.newJoinEdge = null;
            this.mainContent.getDrawingPane().setNewLine(null);
        } else {
            if (this.newJoinEdge != null && (this.newJoinEdge.getFirstObject() == null || this.newJoinEdge.getFirstObject().equals(clickedObject))) {
                this.newJoinEdge = null;
            } else {
                this.places.setAllObjectDiselected();
                disableButtons(null);
            }
            drawJoinEdge(clickedObject);
        }
    }

    /**
     * Method for disabling or reanabling buttons based on selected object
     *
     * @param selectedObject object that has been selected.
     */
    private void disableButtons(CoordinateModel selectedObject) {
        if (selectedObject != null && selectedObject instanceof CDClass && ((CDClass) selectedObject).getTypeOfClass() == ClassType.INTERFACE) {
            for (CDLineType lineType : CDLineType.values()) {
                if (lineType != CDLineType.GENERALIZATION) {
                    if (this.LeftBottomContent.getButtonWithName(lineType.name()) != null) {
                        this.LeftBottomContent.getButtonWithName(lineType.name()).setEnabled(false);
                        this.LeftBottomContent.getButtonWithName(lineType.name()).setSelected(false);
                    }
                }
            }
        } else {
            for (CDLineType lineType : CDLineType.values()) {
                if (this.LeftBottomContent.getButtonWithName(lineType.name()) != null) {
                    this.LeftBottomContent.getButtonWithName(lineType.name()).setEnabled(true);
                }
            }
        }
    }
}
