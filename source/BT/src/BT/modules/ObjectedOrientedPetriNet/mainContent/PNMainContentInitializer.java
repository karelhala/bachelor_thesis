/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ObjectedOrientedPetriNet.mainContent;

import BT.modules.ObjectedOrientedPetriNet.mainContent.PNBottomRight.PNBottomRightController;
import BT.interfaces.DrawingClicks;
import BT.managers.DiagramPlacesManager;
import BT.managers.DrawingListeners;
import BT.managers.PlaceManager;
import BT.modules.ObjectedOrientedPetriNet.places.PNPlace;
import BT.modules.ObjectedOrientedPetriNet.places.PNTransition;
import BT.modules.ObjectedOrientedPetriNet.places.joinEdge.PNJoinEdgeController;
import GUI.BasicPetrinetPanel;
import GUI.MethodLabel;
import GUI.PetrinetGuardActionPanel;
import GUI.PetrinetPlacePanel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

/**
 * Class that initialize classes and call proper methods for petriNets. This class is usefull for bottom left and right
 * panels, when it shows correct panels and stuff.
 *
 * @author Karel Hala
 */
abstract public class PNMainContentInitializer extends PNMainContentModel {

    /**
     * Basic constructor. Senad to it's parrent diagram places, all objects on petri net panel.
     * @param diagramPlaces DiagramPlacesManager all objects on petri net panel.
     */
    public PNMainContentInitializer(DiagramPlacesManager diagramPlaces) {
        super(diagramPlaces);
        createMainPane();
    }

    /**
     * This will create new drawing pane and sets mouse listenrs and button listeners to drawing pane.
     */
    private void createMainPane() {
        PNDrawingPane pnDrawing = (PNDrawingPane) this.mainContent.getDrawingPane();
        DrawingListeners alpha = new DrawingListeners((DrawingClicks) this);
        pnDrawing.getDrawing().addMouseMotionListener(alpha);
        pnDrawing.getDrawing().addMouseListener(alpha);
        setButtonsListeners();
    }

    /**
     * Set listener to escape and delete button.
     */
    public void setButtonsListeners() {
        final PNDrawingPane drawingPane = (PNDrawingPane) this.mainContent.getDrawingPane();
        drawingPane.getDrawing().getActionMap().put("removeObject", new AbstractAction() {
            PNDrawingPane drawingPane = (PNDrawingPane) mainContent.getDrawingPane();

            @Override
            public void actionPerformed(ActionEvent ae) {
                places.removeAllSelectedItems();
                deleteNewLine();
                drawingPane.getDrawing().repaint();
            }
        });

        drawingPane.getDrawing().getActionMap().put("selectionCanceled", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                places.setAllObjectDiselected();
                LeftTopContent.setAllButtonsAvailable();
                LeftTopContent.setAllButtonsDiselected();
                LeftBottomContent.setAllButtonsAvailable();
                LeftBottomContent.setAllButtonsDiselected();
                drawingPane.getDrawing().repaint();
                showBasicPanel();
            }
        });
        InputMap inputMap = drawingPane.getDrawing().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke("DELETE"), "removeObject");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "selectionCanceled");
    }

    /**
     * This will remove line from joins and create new line with ending at the tip of cursor.
     * @param draggedJoinEdge PNJoinEdgeController removed join.
     */
    protected void removeLineFromArrayListAndSetNewLine(PNJoinEdgeController draggedJoinEdge) {
        this.newJoinEdge = new PNJoinEdgeController();
        this.newJoinEdge.setFirstObject(draggedJoinEdge.getFirstObject());
        this.places.removeJoinEdge(draggedJoinEdge);
    }

    /**
     * Delete new line that is being drawn.
     */
    protected void deleteNewLine() {
        this.newJoinEdge = null;
        this.mainContent.getDrawingPane().setNewLine(newJoinEdge);
        ((PNDrawingPane) this.mainContent.getDrawingPane()).getDrawing().repaint();
    }

    /**
     * Method for calling repainting bottom left content model.
     */
    public void repaintBottomLeft() {
        this.bottomLeftController.repaintBottomLeftContent(this.selectedClass);
        this.bottomLeftController.getDrawnClass().setSelected(Boolean.TRUE);
        setListenerToMethodLabel(this.bottomLeftController.getDrawnClass());
        for (MethodLabel oneLabel : this.bottomLeftController.getMethodLabels()) {
            setListenerToMethodLabel(oneLabel);
        }
    }

    /**
     * Load selected class attributes to combobox.
     */
    public void repaintBottomRight() {
        this.bottomRightController.setSelectedClass(selectedClass);
        this.bottomRightController.setPetrinetPlaces(places);
        this.bottomRightController.setSelectedMethod(null);
        this.bottomRightController.loadAttributesToComboBox();
        showPanel();
    }

    /**
     * Listeners to method label are clicked, entered and exited.
     * Clicked: petri net will be redrawn.
     * Enetered: indicate user can click.
     * Exited: change back to original color.
     * @param listenedMethodLabel method label, that has these listeners set up.
     */
    private void setListenerToMethodLabel(final MethodLabel listenedMethodLabel) {
        listenedMethodLabel.setBasicColor();
        listenedMethodLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                listenedMethodLabel.setForeground(Color.RED);
                e.getComponent().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                listenedMethodLabel.setBasicColor();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                bottomLeftController.setAllMethodsNotSelectedAndRepaintAllMethods();
                bottomLeftController.getDrawnClass().setSelected(false);
                bottomLeftController.getDrawnClass().setBasicColor();
                listenedMethodLabel.selectAndChangeColor(Boolean.TRUE);
                bottomRightController.setSelectedMethod(listenedMethodLabel);
                bottomRightController.loadAttributesToComboBox();
                bottomRightController.setPetrinetPlaces(listenedMethodLabel.getPetriNetFromClassOrMethod());
                setPlacesAndRepaintDrawing(listenedMethodLabel.getPetriNetFromClassOrMethod());
                showPanel();
            }
        });

    }

    /**
     * Method for setting petrinet objects and repaint.
     * @param selectedPlaces PlaceManager new objects to be drawn.
     */
    private void setPlacesAndRepaintDrawing(PlaceManager selectedPlaces) {
        this.places = selectedPlaces;
        this.mainContent.getDrawingPane().setPlaces(selectedPlaces);
        ((PNDrawingPane) this.mainContent.getDrawingPane()).getDrawing().repaint();
    }

    /**
     * Initialize rightBottomController with basicPanel and guard action Panel.
     *
     * @param petrinetPanel panel for inserting new variable as place.
     * @param petrinetGuardAction panel with action and guard text field.
     * @param petrinetPlace panel with setting constant to place.
     * @return PNMainContentInitializer.
     */
    public PNMainContentInitializer initializeRightController(BasicPetrinetPanel petrinetPanel, PetrinetGuardActionPanel petrinetGuardAction, PetrinetPlacePanel petrinetPlace) {
        this.bottomRightController = new PNBottomRightController(bottomRightModel, petrinetPanel, petrinetGuardAction, petrinetPlace);
        this.bottomRightController.setSelectedClass(selectedClass);
        this.bottomRightController.setPetrinetDrawingPane((PNDrawingPane) this.mainContent.getDrawingPane());
        this.bottomRightController.setPetrinetPlaces(this.places);
        this.bottomRightController.setClassManager(this.diagramPlaces.getCdPlaces());
        this.bottomRightController.initializeButtonListeners();
        return this;
    }

    /**
     * Show basic panel content pane.
     */
    public void showBasicPanel() {
        this.bottomRightModel.replaceAdditionalContent(this.bottomRightController.getBasicPetrinetPanel().getContentPane());
        this.bottomRightModel.hideButtons();
    }

    /**
     * Show transition content pane.
     */
    public void showTransitionPanel() {
        this.bottomRightModel.replaceAdditionalContent(this.bottomRightController.getPetrinetGuardAction().getContentPane());
        this.bottomRightModel.showButtons();
    }

    /**
     * Show place content pane.
     */
    public void showPlacePanel() {
        this.bottomRightModel.replaceAdditionalContent(this.bottomRightController.getPetrinetPlace().getContentPane());
        this.bottomRightModel.hideButtons();
    }

    /**
     * Method for showing propriete panel, based on selected object.
     */
    public void showPanel() {
        this.bottomRightController.setSelectedObject(places.getSelectedObject());
        if (places.getSelectedObject() instanceof PNPlace) {
            showPlacePanel();
            this.bottomRightController.changeGuardAndAction();
        } else if (places.getSelectedObject() instanceof PNTransition) {
            showTransitionPanel();
            this.bottomRightController.changeGuardAndAction();
        } else {
            showBasicPanel();
            this.bottomRightController.changeGuardAndAction();
        }
    }
}
