/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.UC.mainContent;

import BT.BT.UCLineType;
import BT.managers.ObjectChecker;
import BT.interfaces.DrawingClicks;
import BT.managers.DiagramPlacesManager;
import BT.models.CoordinateModel;
import BT.models.LineModel;
import BT.modules.UC.places.UCActor;
import BT.modules.UC.places.UCJoinEdge.UCJoinEdgeController;
import BT.modules.UC.places.UCUseCase;
import GUI.BottomRightContentModel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

/**
 * Class for controlling main content of useCase.
 * 
 * @author Karel Hala
 */
public class UCMainContentController extends UCMainContentModel implements DrawingClicks {

    /**
     * Connector between useCase diagram and clas diagram. The connection is being done from useCase.
     */
    private final UCClassDiagramConnector classDiagramConnector;

    /**
     * Content with buttons to reactivate and delete all inactive objects.
     */
    private final BottomRightContentModel bottomRightContent;

    /**
     * Basic constructor that sets diagram places manager and bottom right content.
     *
     * @param diagramPlaces to be specified in UCMainContentModel.
     * @param bottomRightContent bottom right content for buttons for reactivating and deleting inactive objects.
     */
    public UCMainContentController(DiagramPlacesManager diagramPlaces, BottomRightContentModel bottomRightContent) {
        super(diagramPlaces);
        this.bottomRightContent = bottomRightContent;
        this.classDiagramConnector = new UCClassDiagramConnector(diagramPlaces.getCdPlaces(), diagramPlaces.getUcPlaces());
        addButtonClickListeners();
    }

    /**
     * Method for creating listeners to top and bottom buttons. Create listeners for top button as delete all
     * inactiveuseCases and bottom to be reactivating all inactive.
     */
    private void addButtonClickListeners() {
        this.bottomRightContent.getTopButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                places.deleteAllUnassignedObjects();
                ((UCDrawingPane) mainContent.getDrawingPane()).getDrawing().repaint();
            }
        });

        this.bottomRightContent.getBottomButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                classDiagramConnector.reactivateAllObjects();
                ((UCDrawingPane) mainContent.getDrawingPane()).getDrawing().repaint();
            }
        });
    }

    /**
     * When mouse is dragged on drawing panel of useCase. Change coordinates of dragged object based on cursor
     * coordinates. Behave differently when dragged line and when object.
     *
     * @param e MouseEvent of mouse cursor.
     * @param dragged CoordinateModel object that is dragged.
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
        UCdrawing.getDrawing().repaint();
    }

    /**
     * When clicked on drawing panel. Create new object of useCase diagram. Either Actor or useCase and create correct
     * class in class diagram.
     *
     * @param evt MouseEvent of cursor.
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
                    this.diagramPlaces.addPnPlace(this.classDiagramConnector.createNewClassdiagramObject().getPnNetwork());
                    this.places.addObject(actor);
                    break;

                case "USECASE":
                    UCUseCase useCase = new UCUseCase(evt.getX(), evt.getY());
                    this.classDiagramConnector.setSelectedObject(useCase);
                    this.diagramPlaces.addPnPlace(this.classDiagramConnector.createNewClassdiagramObject().getPnNetwork());
                    this.places.addObject(useCase);
                    break;
            }
            UCdrawing.getDrawing().repaint();
        }
    }

    /**
     * Check move on drawing pane of useCase. Highlight each object when hover.
     *
     * @param evt MouseEvent of mouse cursor.
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
        UCdrawing.getDrawing().repaint();
    }

    /**
     * When object was selected on useCase drawing panel. Method for handeling selecting of different object on useCase
     * drawing panel.
     *
     * @param clickedObject CoordinateModel object that was selected.
     */
    @Override
    public void setSelectedObject(CoordinateModel clickedObject) {
        this.places.setAllObjectDiselected();
        places.setSelectedLinesOnObject(clickedObject);
        if (clickedObject != null) {
            clickedObject.setSelected(true);
            if (clickedObject instanceof UCActor) {
                this.LeftBottomContent.getButtonWithName(UCLineType.EXTENDS.name()).setEnabled(false);
                this.LeftBottomContent.getButtonWithName(UCLineType.INCLUDE.name()).setEnabled(false);
            } else {
                this.LeftBottomContent.getButtonWithName(UCLineType.EXTENDS.name()).setEnabled(true);
                this.LeftBottomContent.getButtonWithName(UCLineType.INCLUDE.name()).setEnabled(true);
            }
        } else {
            this.LeftBottomContent.getButtonWithName(UCLineType.EXTENDS.name()).setEnabled(true);
            this.LeftBottomContent.getButtonWithName(UCLineType.INCLUDE.name()).setEnabled(true);
        }

        if (this.LeftBottomContent.getSelectedButton() != null || this.newJoinEdge != null) {
            clickedOnObject(clickedObject);
        }
        ((UCDrawingPane) this.mainContent.getDrawingPane()).getDrawing().repaint();
    }

    /**
     * When double clicked on drawing panel of useCase. When double clicked bring japnel for changing name and
     * reactivating. When clicked on line which needs to be specified bring jpanel for it.
     *
     * @param pressedObject CoordinateModel object that was double clicked.
     */
    @Override
    public void drawingPaneDoubleCliked(CoordinateModel pressedObject) {
        if (!(pressedObject instanceof LineModel)) {
            JPanel dialogPanel = new JPanel(new BorderLayout());
            JTextField nameInput = new JTextField();
            nameInput.setText(pressedObject.getName());
            dialogPanel.add(nameInput, BorderLayout.PAGE_START);
            this.classDiagramConnector.setSelectedObject(pressedObject);
            this.classDiagramConnector.addButtonsToDialog(dialogPanel);
            int result = (int) JOptionPane.showConfirmDialog(null, dialogPanel, "Enter name of the object", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                pressedObject.setName(nameInput.getText());
                pressedObject.getAssignedObject().setName(nameInput.getText());
            }
        } else if (pressedObject instanceof UCJoinEdgeController) {
            JPanel dialogPanel = new JPanel(new BorderLayout());
            UCJoinEdgeController selectedLine = (UCJoinEdgeController) pressedObject;
            if (selectedLine.getJoinEdgeType() == UCLineType.USERINPUT) {
                String[] actorClass = {UCLineType.EXTENDS.name(), UCLineType.INCLUDE.name()};
                JComboBox selectType = new JComboBox(actorClass);
                dialogPanel.add(selectType);
                int result = JOptionPane.showConfirmDialog(null, dialogPanel,
                        "Please select type of this line", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    selectedLine.setJoinEdgeType((selectType.getSelectedItem().equals(UCLineType.EXTENDS.name())) ? UCLineType.EXTENDS : UCLineType.INCLUDE);
                }
            }
        }
        ((UCDrawingPane) this.mainContent.getDrawingPane()).getDrawing().repaint();
    }

    /**
     * Change joind edge type of useCase join. Create new join in class diagram and change and create new line when is
     * necessary.
     *
     * @param clickedObject CoordinateModel object that was clicked on.
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
                if (this.newJoinEdge.getFirstObject().getAssignedObject() != null && this.newJoinEdge.getSecondObject().getAssignedObject() != null) {
                    this.classDiagramConnector.setNewLine(this.newJoinEdge);
                    this.classDiagramConnector.createNewClassJoinEdge();
                }
                this.newJoinEdge = null;
            }
        } else {
            this.newJoinEdge.setSecondObject(null);
        }
        UCDrawingPane UCdrawing = (UCDrawingPane) this.mainContent.getDrawingPane();
        UCdrawing.setNewLine(newJoinEdge);
    }

    /**
     * When clicked on drawing panel for useCase. Edit new join edge between useCase objects, either insert object to
     * LineModel's first object place or to second place.
     *
     * @param clickedObject CoordinateModel object that was clicked on from useCase drawing panel.
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
     * When buttons are changed change new line. Wither delete it entirely or change the type based on changed button
     * type.
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
