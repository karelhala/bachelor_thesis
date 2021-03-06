/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ObjectedOrientedPetriNet.mainContent;

import BT.interfaces.DrawingClicks;
import BT.managers.DiagramPlacesManager;
import BT.managers.ObjectChecker;
import BT.models.CoordinateModel;
import BT.models.LineModel;
import BT.modules.ObjectedOrientedPetriNet.places.PNPlace;
import BT.modules.ObjectedOrientedPetriNet.places.PNTransition;
import BT.modules.ObjectedOrientedPetriNet.places.PetriNetModel;
import BT.modules.ObjectedOrientedPetriNet.places.joinEdge.PNJoinEdgeController;
import java.awt.Checkbox;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

/**
 * Controller for handeling clicks on petri net panel. This class handles calling of mouse clicks and mouse movements.
 * It will create and change objects on petri net drawing panel. It also handles creating and joining objects on petri
 * net drawing panel.
 *
 * @author Karel Hala
 */
public class PNMainContentController extends PNMainContentInitializer implements DrawingClicks {

    /**
     * Basic constructor. It calls paren's constructor with diagramPlacesManager.
     *
     * @param diagramPlaces
     */
    public PNMainContentController(DiagramPlacesManager diagramPlaces) {
        super(diagramPlaces);
    }

    /**
     * When mouse is moved in petriNet panel. Highlight objects on drawing panel.
     *
     * @param evt MouseEvent location of cursor.
     */
    @Override
    public void drawingPanecheckMove(MouseEvent evt) {
        ObjectChecker objectChecker = new ObjectChecker(this.mainContent.getDrawingPane().getPlaces());
        CoordinateModel coordObject = objectChecker.getObjectUnderMouse(evt.getPoint());
        PNDrawingPane PNdrawing = (PNDrawingPane) this.mainContent.getDrawingPane();
        if (this.newJoinEdge != null) {
            if (this.newJoinEdge.getSecondObject() == null) {
                this.newJoinEdge.setEndPoint(evt.getPoint());
            }
            PNdrawing.setNewLine(this.newJoinEdge);
        }
        if (coordObject != null) {
            coordObject.setHowerColor();
        }
        PNdrawing.getDrawing().repaint();
    }

    /**
     * When drawing panel of petriNet is double clicked. Change name of object (for PNPlace or PNTransition) or create
     * dialog for checking variables to be sent through join (for PNJoinEdge).
     *
     * @param pressedObject object that was double clicked.
     */
    @Override
    public void drawingPaneDoubleCliked(CoordinateModel pressedObject) {
        if (pressedObject != null && !(pressedObject instanceof LineModel)) {
            if (pressedObject.isEditable()) {
                String name = (String) JOptionPane.showInputDialog("Enter name of the object", pressedObject.getName());
                if (name != null && !"".equals(name)) {
                    pressedObject.setName(name);
                }
            }
        } else if (pressedObject instanceof LineModel) {
            PNJoinEdgeController clickedLine = (PNJoinEdgeController) pressedObject;
            JPanel dialogPanel = new JPanel(new GridLayout(0, 1));
            JTextField additionalVariable = new JTextField(clickedLine.getAdditionalVariable());
            for (String oneVariable : ((PetriNetModel) clickedLine.getFirstObject()).getVariables()) {
                if (clickedLine.getSelectedVariables().contains(oneVariable)) {
                    dialogPanel.add(new Checkbox(oneVariable, true));
                } else if (oneVariable != null) {
                    dialogPanel.add(new Checkbox(oneVariable));
                }
            }
            dialogPanel.add(additionalVariable);
            int result = JOptionPane.showConfirmDialog(null, dialogPanel,
                    "Please select variables.", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                clickedLine.getSelectedVariables().clear();
                ((PetriNetModel) clickedLine.getSecondObject()).getVariables().clear();
                for (Component oneComponent : dialogPanel.getComponents()) {
                    if (oneComponent instanceof Checkbox) {
                        if (((Checkbox) oneComponent).getState()) {
                            clickedLine.addVariable(((Checkbox) oneComponent).getLabel());
                            ((PetriNetModel) clickedLine.getSecondObject()).addVariable(((Checkbox) oneComponent).getLabel());
                            if (clickedLine.getSecondObject() instanceof PNPlace) {
                                for (LineModel oneJoin : clickedLine.getSecondObject().getOutJoins()) {
                                    ((PNJoinEdgeController) oneJoin).addVariable(((Checkbox) oneComponent).getLabel());
                                }
                            }
                        }
                    }
                }

                if (additionalVariable.getText() != null && !additionalVariable.getText().equals("")) {
                    clickedLine.setAdditionalVariable(additionalVariable.getText());
                    ((PetriNetModel) clickedLine.getSecondObject()).addVariable(additionalVariable.getText());
                    if (clickedLine.getSecondObject() instanceof PNPlace) {
                        for (LineModel oneJoin : clickedLine.getSecondObject().getOutJoins()) {
                            ((PNJoinEdgeController) oneJoin).addVariable(additionalVariable.getText());
                        }
                    }
                }
                else if (additionalVariable.getText().equals(""))
                {
                    clickedLine.setAdditionalVariable(null);
                }
            }
        }
        ((PNDrawingPane) this.mainContent.getDrawingPane()).getDrawing().repaint();
    }

    /**
     * When pane of petriNet was clicked. Create new object (if no object is under mouse). Which object is defined by
     * checked button from leftTopContent.
     *
     * @param evt location of mouse.
     */
    @Override
    public void drawingPaneClicked(MouseEvent evt) {
        JToggleButton selectedItemButton = this.LeftTopContent.getSelectedButton();
        if (selectedItemButton != null && "PLACE".equals(selectedItemButton.getName())) {
            this.places.setAllObjectDiselected();
            PNPlace newPlace = new PNPlace(evt.getX(), evt.getY());
            newPlace.setName("");
            showBasicPanel();
            this.places.addObject(newPlace);
        } else if (selectedItemButton != null && "TRANSITION".equals(selectedItemButton.getName())) {
            this.places.setAllObjectDiselected();
            PNTransition newTrasition = new PNTransition(evt.getX(), evt.getY());
            newTrasition.setName("");
            this.bottomRightModel.showAllItems(true);
            this.places.addObject(newTrasition);
        } else {
            this.places.setAllObjectDiselected();
            deleteNewLine();
        }
        ((PNDrawingPane) this.mainContent.getDrawingPane()).getDrawing().repaint();
    }

    /**
     * When mouse is being dragged. Relocate objects under mouse if any is under.
     *
     * @param e location of mouse.
     * @param dragged Dragged object.
     */
    @Override
    public void drawingMouseDragged(MouseEvent e, CoordinateModel dragged) {
        PNDrawingPane pnDrawing = (PNDrawingPane) this.mainContent.getDrawingPane();
        if (dragged instanceof PNPlace || dragged instanceof PNTransition) {
            if (this.newJoinEdge != null) {
                deleteNewLine();
            }
            dragged.setX(e.getX());
            dragged.setY(e.getY());
        } else if (dragged != null) {
            PNJoinEdgeController draggedJoinEdge = (PNJoinEdgeController) dragged;
            if (!draggedJoinEdge.isInRange(e.getX(), e.getY()) && this.newJoinEdge == null) {
                removeLineFromArrayListAndSetNewLine(draggedJoinEdge);
                drawingPanecheckMove(e);
            }
        }
        pnDrawing.getDrawing().repaint();
    }

    /**
     * When mouse is clicked, this methodis called. hoglight object and change panels based on object type.
     *
     * @param clickedObject object that was clicked on.
     */
    @Override
    public void setSelectedObject(CoordinateModel clickedObject) {
        this.places.setAllObjectDiselected();
        places.setSelectedLinesOnObject(clickedObject);
        if (clickedObject != null) {
            clickedObject.setSelected(true);
            if (clickedObject instanceof PNTransition) {
                this.bottomRightController.setSelectedObject(clickedObject);
                this.bottomRightController.changeGuardAndAction();
                showTransitionPanel();
            } else if (clickedObject instanceof PNPlace) {
                this.bottomRightController.setSelectedObject(clickedObject);
                this.bottomRightController.changeGuardAndAction();
                showPlacePanel();
            } else {
                this.bottomRightController.setSelectedObject(null);
                this.bottomRightController.changeGuardAndAction();
                showBasicPanel();
            }
        } else {
            showBasicPanel();
        }

        if (this.LeftBottomContent.getSelectedButton() != null || this.newJoinEdge != null) {
            clickedOnObject(clickedObject);
        }
        ((PNDrawingPane) this.mainContent.getDrawingPane()).getDrawing().repaint();
    }

    /**
     * Delete new line if no buttom is selected from left bottom cotroller.
     */
    @Override
    public void buttonsChanged() {
        if (this.LeftBottomContent.getSelectedButton() == null) {
            deleteNewLine();
        }
    }

    /**
     * Create new or update new join edge. When both objects are saved in join edge, save it to places. Check if
     * connection is possible.
     *
     * @param clickedObject object that was clicked on.
     */
    public void drawJoinEdge(CoordinateModel clickedObject) {
        this.newJoinEdge = createJoinEdge((PNJoinEdgeController) this.newJoinEdge, clickedObject);

        if (this.newJoinEdge.getFirstObject() != null && this.newJoinEdge.getSecondObject() != null) {
            if (this.newJoinEdge.getFirstObject().equals(clickedObject)) {
                this.newJoinEdge.setSelected(true);
            } else {
                this.newJoinEdge.setSelected(false);
            }
            this.places.addObject(this.newJoinEdge);
            this.newJoinEdge = null;
        }
        PNDrawingPane cdDrawing = (PNDrawingPane) this.mainContent.getDrawingPane();
        cdDrawing.setNewLine(newJoinEdge);
    }

    /**
     * method for checking if connection between two objects is possible.
     * 
     * @param joinEdge eddited join edge.
     * @param clickedObject clicked object.
     * @return PNJoinEdgeController changed join edge.
     */
    private PNJoinEdgeController createJoinEdge(PNJoinEdgeController joinEdge, CoordinateModel clickedObject) {
        if (joinEdge == null) {
            joinEdge = new PNJoinEdgeController();
            joinEdge.setName("");
        }
        if (joinEdge.getFirstObject() == null) {
            joinEdge.setFirstObject(clickedObject);
        } else if (joinEdge.getSecondObject() == null && !joinEdge.getFirstObject().getClass().equals(clickedObject.getClass())) {
            joinEdge.setSecondObject(clickedObject);
        }
        if (joinEdge.getFirstObject() instanceof PNPlace) {
            joinEdge.getSelectedVariables().addAllUnique(((PNPlace) joinEdge.getFirstObject()).getVariables());
        }
        if (joinEdge.getSecondObject() instanceof PNPlace) {
            joinEdge.addVariable(((PNTransition) joinEdge.getFirstObject()).getActionVariable());
            ((PNPlace) joinEdge.getSecondObject()).getVariables().addAllUnique(joinEdge.getSelectedVariables());
        }
        return joinEdge;
    }

    /**
     * When clicked on object delete new line if wrong object under mouse or edit join edge.
     * 
     * @param clickedObject object that was clicked on drawing panel.
     */
    private void clickedOnObject(CoordinateModel clickedObject) {
        if (clickedObject == null || clickedObject instanceof LineModel) {
            deleteNewLine();
        } else {
            if (this.newJoinEdge != null && this.newJoinEdge.getFirstObject().equals(clickedObject)) {
                this.newJoinEdge = null;
            }
            drawJoinEdge(clickedObject);
        }
    }
}
