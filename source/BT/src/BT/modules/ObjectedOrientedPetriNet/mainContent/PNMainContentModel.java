/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BT.modules.ObjectedOrientedPetriNet.mainContent;

import BT.interfaces.DrawingClicks;
import BT.managers.DrawingListeners;
import BT.managers.MainContentController;
import BT.managers.PlaceManager;
import BT.modules.ObjectedOrientedPetriNet.PNLeftBottomContent;
import BT.modules.ObjectedOrientedPetriNet.PNLeftTopContent;
import BT.modules.ObjectedOrientedPetriNet.PNMainContent;
import BT.modules.ObjectedOrientedPetriNet.places.joinEdge.PNJoinEdgeController;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

/**
 *
 * @author Karel
 */
abstract public class PNMainContentModel extends MainContentController{
    /**
     * 
     */
    protected PNLeftBottomContent LeftBottomContent;
    
    /**
     * 
     */
    protected PNLeftTopContent LeftTopContent;
    
    public PNMainContentModel()
    {
        this.places = new PlaceManager();
        this.mainContent = new PNMainContent(places);
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

    public PNLeftBottomContent getLeftBottomContent() {
        return LeftBottomContent;
    }

    public PNLeftTopContent getLeftTopContent() {
        return LeftTopContent;
    }
    
    private void createMainPane()
    {   
        PNDrawingPane pnDrawing = (PNDrawingPane) this.mainContent.getDrawingPane();
        DrawingListeners alpha = new DrawingListeners((DrawingClicks) this);
        pnDrawing.getDrawing().addMouseMotionListener(alpha);
        pnDrawing.getDrawing().addMouseListener(alpha);
        setButtonsListeners();
    }
    
    /**
     * 
     */
    public void setButtonsListeners()
    {
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
    protected void deleteNewLine(){
        this.newJoinEdge = null;
        this.mainContent.getDrawingPane().setNewLine(newJoinEdge);
        ((PNDrawingPane)this.mainContent.getDrawingPane()).getDrawing().repaint();
    }
}
