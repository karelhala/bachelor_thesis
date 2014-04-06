/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.UC.mainContent;

import BT.BT;
import BT.BT.CDLineType;
import BT.BT.UCLineType;
import BT.managers.ObjectChecker;
import BT.interfaces.DrawingClicks;
import BT.managers.DiagramPlacesManager;
import BT.models.CoordinateModel;
import BT.modules.ClassDiagram.places.CDClass;
import BT.modules.ClassDiagram.places.joinEdge.CDJoinEdgeController;
import BT.modules.UC.places.UCActor;
import BT.modules.UC.places.UCJoinEdge.UCJoinEdgeController;
import BT.modules.UC.places.UCUseCase;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

/**
 *
 * @author Karel Hala
 */
public class UCMainContentController extends UCMainContentModel implements DrawingClicks {

    private final UCClassDiagramConnector classDiagramConnector;
    /**
     *
     * @param diagramPlaces
     */
    public UCMainContentController(DiagramPlacesManager diagramPlaces) {
        super(diagramPlaces);
        this.classDiagramConnector = new UCClassDiagramConnector(diagramPlaces.getCdPlaces(), diagramPlaces.getUcPlaces());
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
            switch (selectedItemButton.getName()) {
                case "ACTOR":
                    UCActor actor = new UCActor(evt.getX(), evt.getY());
                    this.classDiagramConnector.setSelectedObject(actor);
                    this.classDiagramConnector.createNewClassdiagramObject();
                    this.places.addObject(actor);
//                        this.mainContent.recalculateSize(actor);
                    break;

                case "USECASE":
                    UCUseCase useCase = new UCUseCase(evt.getX(), evt.getY());
                    this.classDiagramConnector.setSelectedObject(useCase);
                    this.classDiagramConnector.createNewClassdiagramObject();
                    this.places.addObject(useCase);
//                        this.mainContent.recalculateSize(useCase);
                    break;
            }
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
            if (clickedObject instanceof UCActor)
            {
                this.LeftBottomContent.getButtonWithName(UCLineType.EXTENDS.name()).setEnabled(false);
                this.LeftBottomContent.getButtonWithName(UCLineType.INCLUDE.name()).setEnabled(false);
            }
            else
            {
                this.LeftBottomContent.getButtonWithName(UCLineType.EXTENDS.name()).setEnabled(true);
                this.LeftBottomContent.getButtonWithName(UCLineType.INCLUDE.name()).setEnabled(true);
            }
        }
        else
        {
            this.LeftBottomContent.getButtonWithName(UCLineType.EXTENDS.name()).setEnabled(true);
            this.LeftBottomContent.getButtonWithName(UCLineType.INCLUDE.name()).setEnabled(true);
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
        JPanel dialogPanel = new JPanel(new BorderLayout());
        JTextField nameInput = new JTextField();
        nameInput.setText(pressedObject.getName());
        dialogPanel.add(nameInput, BorderLayout.PAGE_START);
        this.classDiagramConnector.setSelectedObject(pressedObject);
        this.classDiagramConnector.addButtonsToDialog(dialogPanel);
        int result = (int) JOptionPane.showConfirmDialog(null, dialogPanel, "Enter name of the object", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION)
        {
            pressedObject.setName(nameInput.getText());
            pressedObject.getAssignedObject().setName(nameInput.getText());
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

            if (this.newJoinEdge.getFirstObject() != null && this.newJoinEdge.getSecondObject() != null) {
                if (this.newJoinEdge.getFirstObject().equals(clickedObject)) {
                    this.newJoinEdge.setSelected(true);
                } else {
                    this.newJoinEdge.setSelected(false);
                }
                this.places.addObject(this.newJoinEdge);
                if (this.newJoinEdge.getFirstObject().getAssignedObject() != null && this.newJoinEdge.getSecondObject().getAssignedObject() != null)
                {
                    this.classDiagramConnector.setNewLine(this.newJoinEdge);
                    this.classDiagramConnector.createNewClassJoinEdge();
                }
                this.newJoinEdge = null;
            }
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
            if (this.newJoinEdge != null && this.newJoinEdge.getFirstObject() != null && this.newJoinEdge.getFirstObject().equals(clickedObject)) {
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
