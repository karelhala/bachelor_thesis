/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ObjectedOrientedPetriNet.mainContent;

import BT.interfaces.DrawingClicks;
import BT.managers.DiagramPlacesManager;
import BT.managers.DrawingListeners;
import BT.managers.MainContentController;
import BT.managers.PlaceManager;
import BT.modules.ClassDiagram.places.CDClass;
import BT.modules.ObjectedOrientedPetriNet.PNLeftBottomContent;
import BT.modules.ObjectedOrientedPetriNet.PNLeftTopContent;
import BT.modules.ObjectedOrientedPetriNet.PNMainContent;
import BT.modules.ObjectedOrientedPetriNet.places.joinEdge.PNJoinEdgeController;
import GUI.BasicPetrinetPanel;
import GUI.BottomLeftContentModel;
import GUI.BottomRightContentModel;
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
        setListenerToMethodLabel(this.bottomLeftController.getDrawnClass());
        for (MethodLabel oneLabel : this.bottomLeftController.getMethodLabels()) {
            setListenerToMethodLabel(oneLabel);
        }
    }
    
    /**
     * 
     * @param listenedMethodLabel 
     */
    private void setListenerToMethodLabel(final MethodLabel listenedMethodLabel)
    {
        listenedMethodLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                listenedMethodLabel.setForeground(Color.RED);
                e.getComponent().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            
            @Override
            public void mouseExited(MouseEvent e)
            {
                listenedMethodLabel.setForeground(Color.BLACK);
            }
            
            @Override
            public void mouseClicked(MouseEvent e)
            {
                bottomLeftController.getSelectedmethodLabel().setSelected(Boolean.FALSE);
                listenedMethodLabel.setSelected(Boolean.TRUE);
                bottomRightController.setSelectedMethod(listenedMethodLabel);
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
