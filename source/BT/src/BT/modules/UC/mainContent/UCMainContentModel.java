/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.UC.mainContent;

import BT.managers.UC.UCPlaceManager;
import BT.modules.UC.UCLeftBottomContent;
import BT.modules.UC.UCLeftTopContent;
import BT.modules.UC.UCMainContent;
import BT.modules.UC.places.UCJoinEdge.UCJoinEdgeController;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

/**
 *
 * @author Karel Hala
 */
public class UCMainContentModel {
    protected UCMainContent mainContent;
    protected UCPlaceManager places;
    protected UCJoinEdgeController newJoinEdge;
    protected UCLeftBottomContent LeftBottomContent;
    protected UCLeftTopContent LeftTopContent;
    
    public UCMainContentModel()
    {
        this.places = new UCPlaceManager();
        this.mainContent = new UCMainContent(places);
            createMainPane();
    }
    
    /**
     * 
     */
    private void createMainPane()
    {   
        UCDrawingPane UCdrawing = this.mainContent.getDrawingPane();
        UCDrawingListeners alpha = new UCDrawingListeners(UCdrawing.getDrawing(), (UCMainContentController) this);
        UCdrawing.getDrawing().addMouseMotionListener(alpha);
        UCdrawing.getDrawing().addMouseListener(alpha);
        setButtonsListeners();
    }

    public void setMainContent(UCMainContent mainContent) {
        this.mainContent = mainContent;
    }

    public void setPlaces(UCPlaceManager places) {
        this.places = places;
    }

    public void setNewJoinEdge(UCJoinEdgeController newJoinEdge) {
        this.newJoinEdge = newJoinEdge;
    }

    public void setLeftBottomContent(UCLeftBottomContent LeftBottomContent) {
        this.LeftBottomContent = LeftBottomContent;
    }

    public void setLeftTopContent(UCLeftTopContent LeftTopContent) {
        this.LeftTopContent = LeftTopContent;
    }

    public UCMainContent getMainContent() {
        return mainContent;
    }

    public UCPlaceManager getPlaces() {
        return places;
    }

    public UCJoinEdgeController getNewJoinEdge() {
        return newJoinEdge;
    }

    public UCLeftBottomContent getLeftBottomContent() {
        return LeftBottomContent;
    }

    public UCLeftTopContent getLeftTopContent() {
        return LeftTopContent;
    }
    
        /**
     * 
     */
    public void setButtonsListeners()
    {
        UCDrawingPane drawingPane = this.mainContent.getDrawingPane();
        drawingPane.getDrawing().getActionMap().put("removeObject", new AbstractAction() {
            UCDrawingPane drawingPane = mainContent.getDrawingPane();
            @Override
                public void actionPerformed(ActionEvent e) {
                places.removeAllSelectedItems();
                newJoinEdge = null;
                drawingPane.setNewLine(null);
                drawingPane.getDrawing().repaint();
                }
            }
        );
        InputMap inputMap = drawingPane.getDrawing().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke("DELETE"), "removeObject");
    }
}
