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
import GUI.BottomLeftContentModel;
import GUI.MethodLabel;
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
abstract public class PNMainContentModel extends MainContentController {

    /**
     *
     */
    protected PNLeftBottomContent LeftBottomContent;

    /**
     *
     */
    protected PNLeftTopContent LeftTopContent;
    
    /**
     * 
     */
    protected BottomLeftContentModel bottomLeftContentModel;
    
    /**
     * 
     */
    protected CDClass selectedClass;

    /**
     * 
     */
    protected PNBottomLeftController bottomLeftController;
    public PNMainContentModel(DiagramPlacesManager diagramPlaces,BottomLeftContentModel bottomLeftContentModel) {
        this.diagramPlaces = diagramPlaces;
        this.places = diagramPlaces.getPnPlaces();
        this.mainContent = new PNMainContent(this.places);
        this.bottomLeftContentModel = bottomLeftContentModel;
        this.bottomLeftController = new PNBottomLeftController(this.bottomLeftContentModel);
        createMainPane();
    }

    public void setMainContent(PNMainContent mainContent) {
        this.mainContent = mainContent;
    }

    public void setLeftBottomContent(PNLeftBottomContent LeftBottomContent) {
        this.LeftBottomContent = LeftBottomContent;
    }

    public void setLeftTopContent(PNLeftTopContent LeftTopContent) {
        this.LeftTopContent = LeftTopContent;
    }

    public PNMainContentModel setSelectedClass(CDClass selectedClass) {
        this.selectedClass = selectedClass;
        return this;
    }

    public PNLeftBottomContent getLeftBottomContent() {
        return LeftBottomContent;
    }

    public PNLeftTopContent getLeftTopContent() {
        return LeftTopContent;
    }

    public CDClass getSelectedClass() {
        return selectedClass;
    }

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
        }
        );
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
                setPlacesAndRepaintDrawing(listenedMethodLabel.getPetriNetFromClassOrMethod());
            }
        });
            
    }
    
    private void setPlacesAndRepaintDrawing(PlaceManager selectedPlaces)
    {
        this.places = selectedPlaces;
        this.mainContent.getDrawingPane().setPlaces(selectedPlaces);
        ((PNDrawingPane)this.mainContent.getDrawingPane()).getDrawing().repaint();
    }
}
