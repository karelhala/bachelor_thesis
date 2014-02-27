/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ClassDiagram.mainContent;

import BT.interfaces.DrawingClicks;
import BT.managers.PlaceManager;
import BT.modules.ClassDiagram.CDLeftBottomContent;
import BT.modules.ClassDiagram.CDLeftTopContent;
import BT.modules.ClassDiagram.CDMainContent;
import BT.modules.ClassDiagram.places.joinEdge.CDJoinEdgeController;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

/**
 *
 * @author Karel Hala
 */
class CDMainContentModel {
    protected CDMainContent mainContent;
    protected CDLeftBottomContent LeftBottomContent;
    protected PlaceManager places;
    protected CDLeftTopContent LeftTopContent;
    protected CDJoinEdgeController newJoinEdge;

    public CDMainContentModel()
    {
        this.places = new PlaceManager();
        this.mainContent = new CDMainContent(places);
        createMainPane();
    }
    
    public CDMainContent getMainContent() {
        return mainContent;
    }

    public CDLeftBottomContent getLeftBottomContent() {
        return LeftBottomContent;
    }

    public CDLeftTopContent getLeftTopContent() {
        return LeftTopContent;
    }

    public void setMainContent(CDMainContent mainContent) {
        this.mainContent = mainContent;
    }

    public void setLeftBottomContent(CDLeftBottomContent LeftBottomContent) {
        this.LeftBottomContent = LeftBottomContent;
    }

    public void setLeftTopContent(CDLeftTopContent LeftTopContent) {
        this.LeftTopContent = LeftTopContent;
    }

    public void setNewJoinEdge(CDJoinEdgeController newJoinEdge) {
        this.newJoinEdge = newJoinEdge;
    }

    public CDJoinEdgeController getNewJoinEdge() {
        return newJoinEdge;
    }
    
    private void createMainPane()
    {   
        CDDrawingPane UCdrawing = this.mainContent.getDrawingPane();
        CDDrawingListeners alpha = new CDDrawingListeners((DrawingClicks) this);
        UCdrawing.getDrawing().addMouseMotionListener(alpha);
        UCdrawing.getDrawing().addMouseListener(alpha);
        setButtonsListeners();
    }
    
        /**
     * 
     */
    public void setButtonsListeners()
    {
        CDDrawingPane drawingPane = this.mainContent.getDrawingPane();
        drawingPane.getDrawing().getActionMap().put("removeObject", new AbstractAction() {
            CDDrawingPane drawingPane = mainContent.getDrawingPane();
            @Override
                public void actionPerformed(ActionEvent e) {
                    mainContent.getDrawingPane().getPlaces().removeAllSelectedItems();
                    mainContent.getDrawingPane().getDrawing().repaint();
                }
            }
        );
        InputMap inputMap = drawingPane.getDrawing().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke("DELETE"), "removeObject");
    }
    
    /**
     * 
     * @param joinEdge 
     */
    public void removeLineFromArrayListAndSetNewLine(CDJoinEdgeController joinEdge) 
    {
        this.newJoinEdge = new CDJoinEdgeController();
        this.newJoinEdge.setFirstObject(joinEdge.getFirstObject());
        CDJoinEdgeManipulator.changeLineTypeByButton(this.LeftBottomContent.getSelectedButton(),this.newJoinEdge);
        this.places.removeJoinEdge(joinEdge);
    }
}
