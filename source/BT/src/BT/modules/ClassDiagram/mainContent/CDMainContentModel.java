/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ClassDiagram.mainContent;

import BT.interfaces.DrawingClicks;
import BT.managers.MainContentController;
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
abstract class CDMainContentModel extends MainContentController{
    protected CDLeftBottomContent LeftBottomContent;
    protected CDLeftTopContent LeftTopContent;
    protected CDJoinEdgeController newJoinEdge;

    public CDMainContentModel()
    {
        this.places = new PlaceManager();
        this.mainContent = new CDMainContent(places);
        createMainPane();
    }

    public CDLeftBottomContent getLeftBottomContent() {
        return LeftBottomContent;
    }

    public CDLeftTopContent getLeftTopContent() {
        return LeftTopContent;
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
        CDDrawingPane UCdrawing = (CDDrawingPane) this.mainContent.getDrawingPane();
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
        CDDrawingPane drawingPane = (CDDrawingPane) this.mainContent.getDrawingPane();
        drawingPane.getDrawing().getActionMap().put("removeObject", new AbstractAction() {
            CDDrawingPane drawingPane = (CDDrawingPane) mainContent.getDrawingPane();
            @Override
                public void actionPerformed(ActionEvent e) {
                    mainContent.getDrawingPane().getPlaces().removeAllSelectedItems();
                    deleteNewLine();
                }
            }
        );
        InputMap inputMap = drawingPane.getDrawing().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke("DELETE"), "removeObject");
    }
    
    /**
     * 
     */
    protected void deleteNewLine(){
        this.newJoinEdge = null;
        this.mainContent.getDrawingPane().setNewLine(newJoinEdge);
        ((CDDrawingPane)this.mainContent.getDrawingPane()).getDrawing().repaint();
    }
    
    /**
     * 
     * @param joinEdge 
     */
    public void removeLineFromArrayListAndSetNewLine(CDJoinEdgeController joinEdge) 
    {
        this.newJoinEdge = new CDJoinEdgeController();
        this.newJoinEdge.setFirstObject(joinEdge.getFirstObject());
        this.newJoinEdge.setJoinEdgeType(joinEdge.getJoinEdgeType());
        if (this.LeftBottomContent.getSelectedButton() != null)
        {
            CDJoinEdgeManipulator.changeLineTypeByButton(this.LeftBottomContent.getSelectedButton(),this.newJoinEdge);
        }
        this.places.removeJoinEdge(joinEdge);
    }
}
