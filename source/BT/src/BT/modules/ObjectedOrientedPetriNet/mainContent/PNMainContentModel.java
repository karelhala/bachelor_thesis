/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BT.modules.ObjectedOrientedPetriNet.mainContent;

import BT.interfaces.DrawingClicks;
import BT.managers.PlaceManager;
import BT.modules.ClassDiagram.places.joinEdge.CDJoinEdgeController;
import BT.modules.ObjectedOrientedPetriNet.PNLeftBottomContent;
import BT.modules.ObjectedOrientedPetriNet.PNLeftTopContent;
import BT.modules.ObjectedOrientedPetriNet.PNMainContent;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

/**
 *
 * @author Karel
 */
public class PNMainContentModel {
    
    protected PNMainContent mainContent;
    protected PNLeftBottomContent LeftBottomContent;
    protected PlaceManager places;
    protected PNLeftTopContent LeftTopContent;
    protected CDJoinEdgeController newJoinEdge;
    
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

    public void setPlaces(PlaceManager places) {
        this.places = places;
    }

    public void setLeftTopContent(PNLeftTopContent LeftTopContent) {
        this.LeftTopContent = LeftTopContent;
    }

    public void setNewJoinEdge(CDJoinEdgeController newJoinEdge) {
        this.newJoinEdge = newJoinEdge;
    }
    
    public PNMainContent getMainContent() {
        return mainContent;
    }

    public PNLeftBottomContent getLeftBottomContent() {
        return LeftBottomContent;
    }

    public PlaceManager getPlaces() {
        return places;
    }

    public PNLeftTopContent getLeftTopContent() {
        return LeftTopContent;
    }

    public CDJoinEdgeController getNewJoinEdge() {
        return newJoinEdge;
    }
    
    private void createMainPane()
    {   
        PNDrawingPane pnDrawing = this.mainContent.getDrawingPane();
        PNDrawingListeners alpha = new PNDrawingListeners((DrawingClicks) this);
        pnDrawing.getDrawing().addMouseMotionListener(alpha);
        pnDrawing.getDrawing().addMouseListener(alpha);
        setButtonsListeners();
    }
    
            /**
     * 
     */
    public void setButtonsListeners()
    {
        PNDrawingPane drawingPane = this.mainContent.getDrawingPane();
        drawingPane.getDrawing().getActionMap().put("removeObject", new AbstractAction() {
            PNDrawingPane drawingPane = mainContent.getDrawingPane();
                @Override
                public void actionPerformed(ActionEvent ae) {
                    System.out.println("Pressed delete");
                }
            }
        );
        InputMap inputMap = drawingPane.getDrawing().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke("DELETE"), "removeObject");
    }
}
