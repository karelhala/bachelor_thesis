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
import BT.modules.ObjectedOrientedPetriNet.places.joinEdge.PNJoinEdgeController;
import GUI.BasicPetrinetPanel;
import GUI.MethodLabel;
import GUI.PetrinetGuardActionPanel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

/**
 *
 * @author Karel
 */
abstract public class PNMainContentInitializer extends PNMainContentModel {

    /**
     * 
     * @param diagramPlaces 
     */
    public PNMainContentInitializer(DiagramPlacesManager diagramPlaces) {
        super(diagramPlaces);
        createMainPane();
    }

    /**
     * 
     */
    private void createMainPane() {
        PNDrawingPane pnDrawing = (PNDrawingPane) this.mainContent.getDrawingPane();
        DrawingListeners alpha = new DrawingListeners((DrawingClicks) this);
        pnDrawing.getDrawing().addMouseMotionListener(alpha);
        pnDrawing.getDrawing().addMouseListener(alpha);
        setButtonsListeners();
    }

    /**
     *
     */
    public void setButtonsListeners() {
        PNDrawingPane drawingPane = (PNDrawingPane) this.mainContent.getDrawingPane();
        drawingPane.getDrawing().getActionMap().put("removeObject", new AbstractAction() {
            PNDrawingPane drawingPane = (PNDrawingPane) mainContent.getDrawingPane();
            @Override
            public void actionPerformed(ActionEvent ae) {
                places.removeAllSelectedItems();
                deleteNewLine();
                drawingPane.getDrawing().repaint();
            }
        });
        InputMap inputMap = drawingPane.getDrawing().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke("DELETE"), "removeObject");
    }

    /**
     *
     * @param draggedJoinEdge
     */
    protected void removeLineFromArrayListAndSetNewLine(PNJoinEdgeController draggedJoinEdge) {
        this.newJoinEdge = new PNJoinEdgeController();
        this.newJoinEdge.setFirstObject(draggedJoinEdge.getFirstObject());
        this.places.removeJoinEdge(draggedJoinEdge);
    }

    /**
     *
     */
    protected void deleteNewLine() {
        this.newJoinEdge = null;
        this.mainContent.getDrawingPane().setNewLine(newJoinEdge);
        ((PNDrawingPane) this.mainContent.getDrawingPane()).getDrawing().repaint();
    }
    
    /**
     * Method for calling repainting bottom left content model.
     */
    public void repaintBottomLeft()
    {
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
    public void repaintBottomRight()
    {
        this.bottomRightController.setSelectedClass(selectedClass);
        this.bottomRightController.setPetrinetPlaces(places);
        this.bottomRightController.setSelectedMethod(null);
        this.bottomRightController.loadAttributesToComboBox();
    }
    
    /**
     * 
     * @param listenedMethodLabel 
     */
    private void setListenerToMethodLabel(final MethodLabel listenedMethodLabel)
    {
        listenedMethodLabel.setBasicColor();
        listenedMethodLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                listenedMethodLabel.setForeground(Color.RED);
                e.getComponent().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            
            @Override
            public void mouseExited(MouseEvent e)
            {
                listenedMethodLabel.setBasicColor();
            }
            
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if (bottomLeftController.getSelectedmethodLabel() != null)
                {
                    bottomLeftController.getSelectedmethodLabel().selectAndChangeColor(Boolean.FALSE);   
                }
                listenedMethodLabel.selectAndChangeColor(Boolean.TRUE);
                bottomRightController.setSelectedMethod(listenedMethodLabel);
                bottomRightController.loadAttributesToComboBox();
                bottomRightController.setPetrinetPlaces(listenedMethodLabel.getPetriNetFromClassOrMethod());
                setPlacesAndRepaintDrawing(listenedMethodLabel.getPetriNetFromClassOrMethod());
            }
        });
            
    }
    
    /**
     * 
     * @param selectedPlaces 
     */
    private void setPlacesAndRepaintDrawing(PlaceManager selectedPlaces)
    {
        this.places = selectedPlaces;
        this.mainContent.getDrawingPane().setPlaces(selectedPlaces);
        ((PNDrawingPane)this.mainContent.getDrawingPane()).getDrawing().repaint();
    }
    
    /**
     * Initialize rightBottomController with basicPanel and guard action Panel.
     * @param petrinetPanel
     * @param petrinetGuardAction
     * @return this object.
     */
    public PNMainContentInitializer initializeRightController(BasicPetrinetPanel petrinetPanel, PetrinetGuardActionPanel petrinetGuardAction)
    {
        this.bottomRightController = new PNBottomRightController(bottomRightModel, petrinetPanel, petrinetGuardAction);
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
    public void showBasicPanel()
    {
        this.bottomRightModel.replaceAdditionalContent(this.bottomRightController.getBasicPetrinetPanel().getContentPane());
        this.bottomRightModel.hideButtons();
    }
    
    /**
     * Show transition content pane.
     */
    public void showTransitionPanel()
    {
        this.bottomRightModel.replaceAdditionalContent(this.bottomRightController.getPetrinetGuardAction().getContentPane());
        this.bottomRightModel.showButtons();
    }
}
