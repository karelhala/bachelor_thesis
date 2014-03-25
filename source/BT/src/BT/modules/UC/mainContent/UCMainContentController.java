/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.UC.mainContent;

import BT.BT;
import BT.managers.ObjectChecker;
import BT.interfaces.DrawingClicks;
import BT.managers.DiagramPlacesManager;
import BT.models.CoordinateModel;
import BT.modules.ClassDiagram.places.CDClass;
import BT.modules.UC.places.UCActor;
import BT.modules.UC.places.UCJoinEdge.UCJoinEdgeController;
import BT.modules.UC.places.UCUseCase;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

/**
 *
 * @author Karel Hala
 */
public class UCMainContentController extends UCMainContentModel implements DrawingClicks {

    /**
     *
     * @param diagramPlaces
     */
    public UCMainContentController(DiagramPlacesManager diagramPlaces) {
        super(diagramPlaces);
    }

    /**
     *
     * @param e
     * @param dragged
     */
    @Override
    public void drawingMouseDragged(MouseEvent e, CoordinateModel dragged) {
        UCDrawingPane UCdrawing = (UCDrawingPane) this.mainContent.getDrawingPane();
        if (dragged instanceof UCActor || dragged instanceof UCUseCase) {
            if (this.newJoinEdge != null) {
                this.newJoinEdge = null;
                UCdrawing.setNewLine(null);
            }
            dragged.setX(e.getX());
            dragged.setY(e.getY());
        } else if (dragged instanceof UCJoinEdgeController) {
            UCJoinEdgeController draggedJoinEdge = (UCJoinEdgeController) dragged;
            if (!draggedJoinEdge.isInRange(e.getX(), e.getY()) && this.newJoinEdge == null) {
                removeLineFromArrayListAndSetNewLine(draggedJoinEdge);
                drawingPanecheckMove(e);
            }
        }
//        this.mainContent.recalculateSize(dragged);
        UCdrawing.getDrawing().repaint();
    }

    /**
     *
     * @param evt
     */
    @Override
    public void drawingPaneClicked(MouseEvent evt) {
        JToggleButton selectedItemButton = this.LeftTopContent.getSelectedButton();
        if (selectedItemButton != null) {
            UCDrawingPane UCdrawing = (UCDrawingPane) this.mainContent.getDrawingPane();
            CDClass newClass = new CDClass(evt.getX(), evt.getY());
            switch (selectedItemButton.getName()) {
                case "ACTOR":
                    UCActor actor = new UCActor(evt.getX(), evt.getY());
                    actor.setAssignedObject(newClass);
                    newClass.setTypeOfClass(BT.ClassType.ACTOR);
                    newClass.setAssignedObject(actor);
                    this.places.addObject(actor);
//                        this.mainContent.recalculateSize(actor);
                    break;

                case "USECASE":
                    UCUseCase useCase = new UCUseCase(evt.getX(), evt.getY());
                    useCase.setAssignedObject(newClass);
                    newClass.setTypeOfClass(BT.ClassType.ACTIVITY);
                    this.places.addObject(useCase);
                    newClass.setAssignedObject(useCase);
//                        this.mainContent.recalculateSize(useCase);
                    break;
            }
            this.diagramPlaces.getCdPlaces().addObject(newClass);
            UCdrawing.getDrawing().repaint();
        }
    }

    /**
     *
     * @param evt
     */
    @Override
    public void drawingPanecheckMove(MouseEvent evt) {
        ObjectChecker objectUnderMouse = new ObjectChecker(places);
        CoordinateModel coordModel = objectUnderMouse.getObjectUnderMouse(evt.getPoint());
        UCDrawingPane UCdrawing = (UCDrawingPane) this.mainContent.getDrawingPane();
        if (this.newJoinEdge != null) {
            if (this.newJoinEdge.getSecondObject() == null) {
                this.newJoinEdge.setEndPoint(evt.getPoint());
            }
            UCdrawing.setNewLine(this.newJoinEdge);
        }
        if (coordModel != null) {
            coordModel.setHowerColor();
        }
//        this.mainContent.recalculateSize(coordModel);
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
        }

        if (this.LeftBottomContent.getSelectedButton() != null || this.newJoinEdge != null) {
            clickedOnObject(clickedObject);
        }
        ((UCDrawingPane) this.mainContent.getDrawingPane()).getDrawing().repaint();
    }

    /**
     *
     * @param pressedObject
     */
    @Override
    public void drawingPaneDoubleCliked(CoordinateModel pressedObject) {
        String name = (String) JOptionPane.showInputDialog("Enter name of the object", pressedObject.getName());
        if (name != null && !"".equals(name)) {
            pressedObject.setName(name);
        }
        ((UCDrawingPane) this.mainContent.getDrawingPane()).getDrawing().repaint();
    }

    /**
     *
     * @param clickedObject
     */
    private void drawJoinEdge(CoordinateModel clickedObject) {
        this.newJoinEdge = UCJoinEdgeManipulator.createJoinEdge((UCJoinEdgeController) this.newJoinEdge, clickedObject);
        if (this.LeftBottomContent.getSelectedButton() != null) {
            UCJoinEdgeManipulator.changeLineTypeByButton(this.LeftBottomContent.getSelectedButton(), (UCJoinEdgeController) this.newJoinEdge);
        }

        if (this.newJoinEdge.getFirstObject() != null && this.newJoinEdge.getSecondObject() != null) {
            if (this.newJoinEdge.getFirstObject().equals(clickedObject)) {
                this.newJoinEdge.setSelected(true);
            } else {
                this.newJoinEdge.setSelected(false);
            }
            this.places.addObject(this.newJoinEdge);
            this.newJoinEdge = null;
        }
        UCDrawingPane UCdrawing = (UCDrawingPane) this.mainContent.getDrawingPane();
        UCdrawing.setNewLine(newJoinEdge);
    }

    /**
     *
     * @param clickedObject
     */
    private void clickedOnObject(CoordinateModel clickedObject) {
        if (clickedObject == null || clickedObject instanceof UCJoinEdgeController) {
            this.newJoinEdge = null;
            this.mainContent.getDrawingPane().setNewLine(null);
        } else {
            if (this.newJoinEdge != null && this.newJoinEdge.getFirstObject().equals(clickedObject)) {
                this.newJoinEdge = null;
            }
            drawJoinEdge(clickedObject);
        }
    }

    /**
     *
     */
    @Override
    public void buttonsChanged() {
        JToggleButton selectedJoinEdgeButton = this.LeftBottomContent.getSelectedButton();
        UCDrawingPane UCdrawing = (UCDrawingPane) this.mainContent.getDrawingPane();
        if (selectedJoinEdgeButton == null) {
            this.newJoinEdge = null;
            UCdrawing.setNewLine(null);
        } else if (this.newJoinEdge != null) {
            UCJoinEdgeManipulator.changeLineTypeByButton(this.LeftBottomContent.getSelectedButton(), (UCJoinEdgeController) this.newJoinEdge);
        }
        UCdrawing.getDrawing().repaint();
    }
}
