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
import BT.modules.ObjectedOrientedPetriNet.places.joinEdge.PNJoinEdgeController;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

/**
 *
 * @author Karel
 */
public class PNMainContentController extends PNMainContentInitializer implements DrawingClicks {

    /**
     *
     * @param diagramPlaces
     */
    public PNMainContentController(DiagramPlacesManager diagramPlaces) {
        super(diagramPlaces);  
    }

    /**
     *
     * @param evt
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
     *
     * @param pressedObject
     */
    @Override
    public void drawingPaneDoubleCliked(CoordinateModel pressedObject) {
        if (pressedObject != null && !(pressedObject instanceof LineModel)) {
            String name = (String) JOptionPane.showInputDialog("Enter name of the object", pressedObject.getName());
            if (name != null && !"".equals(name)) {
                pressedObject.setName(name);
            }
            ((PNDrawingPane) this.mainContent.getDrawingPane()).getDrawing().repaint();
        }
    }

    /**
     *
     * @param evt
     */
    @Override
    public void drawingPaneClicked(MouseEvent evt) {
        JToggleButton selectedItemButton = this.LeftTopContent.getSelectedButton();
        if (selectedItemButton != null && "PLACE".equals(selectedItemButton.getName())) {
            this.places.setAllObjectDiselected();
            PNPlace newPlace = new PNPlace(evt.getX(), evt.getY());
            showBasicPanel();
            this.places.addObject(newPlace);
        } else if (selectedItemButton != null && "TRANSITION".equals(selectedItemButton.getName())) {
            this.places.setAllObjectDiselected();
            PNTransition newTrasition = new PNTransition(evt.getX(), evt.getY());
            newTrasition.setGuard("true");
            newTrasition.setAction("x := y+p");
            this.bottomRightModel.showAllItems();
            this.places.addObject(newTrasition);
        } else {
            this.places.setAllObjectDiselected();
            deleteNewLine();
        }
        ((PNDrawingPane) this.mainContent.getDrawingPane()).getDrawing().repaint();
    }

    /**
     *
     * @param e
     * @param dragged
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
     *
     * @param clickedObject
     */
    @Override
    public void setSelectedObject(CoordinateModel clickedObject) {
        this.places.setAllObjectDiselected();
        places.setSelectedLinesOnObject(clickedObject);
        if (clickedObject != null) {
            clickedObject.setSelected(true);
            if (clickedObject instanceof PNTransition)
            {
                this.bottomRightController.setSelectedTransition((PNTransition) clickedObject);
                this.bottomRightController.changeGuardAndAction();
                showTransitionPanel();
            }
            else
            {
                this.bottomRightController.setSelectedTransition(null);
                this.bottomRightController.changeGuardAndAction();
                showBasicPanel();
            }
        }
        else
        {
            showBasicPanel();
        }

        if (this.LeftBottomContent.getSelectedButton() != null || this.newJoinEdge != null) {
            clickedOnObject(clickedObject);
        }
        ((PNDrawingPane) this.mainContent.getDrawingPane()).getDrawing().repaint();
    }

    /**
     *
     */
    @Override
    public void buttonsChanged() {
        if (this.LeftBottomContent.getSelectedButton() == null) {
            deleteNewLine();
        }
    }

    /**
     *
     * @param clickedObject
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
     *
     * @param joinEdge
     * @param clickedObject
     * @return
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
            if (joinEdge.getSecondObject() instanceof PNPlace)
            {
                ((PNPlace) joinEdge.getSecondObject()).addVariable(((PNTransition)joinEdge.getFirstObject()).getActionVariable());
            }
        }
        return joinEdge;
    }

    /**
     *
     * @param clickedObject
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
