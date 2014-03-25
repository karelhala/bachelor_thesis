/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.UC.mainContent;

import BT.managers.DrawingListeners;
import BT.managers.MainContentController;
import BT.managers.PlaceManager;
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
abstract public class UCMainContentModel extends MainContentController {

    protected UCLeftBottomContent LeftBottomContent;
    protected UCLeftTopContent LeftTopContent;

    public UCMainContentModel() {
        this.places = new PlaceManager();
        this.mainContent = new UCMainContent(places);
        createMainPane();
    }

    /**
     *
     */
    private void createMainPane() {
        UCDrawingPane UCdrawing = (UCDrawingPane) this.mainContent.getDrawingPane();
        DrawingListeners alpha = new DrawingListeners((UCMainContentController) this);
        UCdrawing.getDrawing().addMouseMotionListener(alpha);
        UCdrawing.getDrawing().addMouseListener(alpha);
        setButtonsListeners();
    }

    public void setLeftBottomContent(UCLeftBottomContent LeftBottomContent) {
        this.LeftBottomContent = LeftBottomContent;
    }

    public void setLeftTopContent(UCLeftTopContent LeftTopContent) {
        this.LeftTopContent = LeftTopContent;
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
    public void setButtonsListeners() {
        UCDrawingPane drawingPane = (UCDrawingPane) this.mainContent.getDrawingPane();
        drawingPane.getDrawing().getActionMap().put("removeObject", new AbstractAction() {
            UCDrawingPane drawingPane = (UCDrawingPane) mainContent.getDrawingPane();

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

    /**
     *
     * @param joinEdge
     */
    public void removeLineFromArrayListAndSetNewLine(UCJoinEdgeController joinEdge) {
        this.newJoinEdge = new UCJoinEdgeController();
        this.newJoinEdge.setFirstObject(joinEdge.getFirstObject());
        ((UCJoinEdgeController) this.newJoinEdge).setJoinEdgeType(joinEdge.getJoinEdgeType());
        if (this.LeftBottomContent.getSelectedButton() != null) {
            UCJoinEdgeManipulator.changeLineTypeByButton(this.LeftBottomContent.getSelectedButton(), (UCJoinEdgeController) this.newJoinEdge);
        }
        this.places.removeJoinEdge(joinEdge);
    }
}
