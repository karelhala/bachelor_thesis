/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.UC.mainContent;

import BT.managers.DiagramPlacesManager;
import BT.managers.DrawingListeners;
import BT.managers.MainContentController;
import BT.modules.UC.UCLeftBottomContent;
import BT.modules.UC.UCLeftTopContent;
import BT.modules.UC.UCMainContent;
import BT.modules.UC.places.UCJoinEdge.UCJoinEdgeController;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

/**
 * This class manages useCae main content data. It also handles mouse and keyboard button listeners.
 *
 * @author Karel Hala
 */
abstract public class UCMainContentModel extends MainContentController {

    /**
     * Left bottom content with buttons to set which line should be used.
     */
    protected UCLeftBottomContent LeftBottomContent;

    /**
     * Left Top content with buttons to set which object should be added.
     */
    protected UCLeftTopContent LeftTopContent;

    /**
     * Basic constructor. It sets DiagramPlacesManager which stores each object off managed file. It also set places to
     * ucPlaces and create new mainContent.
     *
     * @param diagramPlaces DiagramPlacesManager with each object of useCase, class diagram and OOPN.
     */
    public UCMainContentModel(DiagramPlacesManager diagramPlaces) {
        this.diagramPlaces = diagramPlaces;
        this.places = diagramPlaces.getUcPlaces();
        this.mainContent = new UCMainContent(this.places);
        createMainPane();
    }

    /**
     * Create main pane and set it's listeners. Set listeners to keyboard clicks and to mouse clicks.
     */
    private void createMainPane() {
        UCDrawingPane UCdrawing = (UCDrawingPane) this.mainContent.getDrawingPane();
        DrawingListeners alpha = new DrawingListeners((UCMainContentController) this);
        UCdrawing.getDrawing().addMouseMotionListener(alpha);
        UCdrawing.getDrawing().addMouseListener(alpha);
        setButtonsListeners();
    }

    /**
     * Set left bottom content which has buttons for setting type of added join edge.
     *
     * @param LeftBottomContent UCLeftBottomContent with buttons to specify which join edge should be added.
     */
    public void setLeftBottomContent(UCLeftBottomContent LeftBottomContent) {
        this.LeftBottomContent = LeftBottomContent;
    }

    /**
     * Set left top content which has buttons for setting type of added objects.
     *
     * @param LeftTopContent UCLeftTopContent with buttons to specify which object should be added.
     */
    public void setLeftTopContent(UCLeftTopContent LeftTopContent) {
        this.LeftTopContent = LeftTopContent;
    }

    /**
     * Get left bottom content. Which has buttons for setting type of added join edges.
     *
     * @return UCLeftBottomContent with buttons for adding join edges on useCase drawing panel.
     */
    public UCLeftBottomContent getLeftBottomContent() {
        return LeftBottomContent;
    }

    /**
     * Get left top content. Which has buttons for setting type of added objects.
     *
     * @return UCLeftTopContent with buttons for adding objects on useCase drawing panel.
     */
    public UCLeftTopContent getLeftTopContent() {
        return LeftTopContent;
    }

    /**
     * Create listeners to delete and escape keyboard buttons.
     */
    public void setButtonsListeners() {
        final UCDrawingPane drawingPane = (UCDrawingPane) this.mainContent.getDrawingPane();
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

        drawingPane.getDrawing().getActionMap().put("selectionCanceled", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                places.setAllObjectDiselected();
                LeftTopContent.setAllButtonsAvailable();
                LeftTopContent.setAllButtonsDiselected();
                LeftBottomContent.setAllButtonsAvailable();
                LeftBottomContent.setAllButtonsDiselected();
                drawingPane.getDrawing().repaint();
            }
        });
        InputMap inputMap = drawingPane.getDrawing().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke("DELETE"), "removeObject");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "selectionCanceled");
    }

    /**
     * Remove line from array and set new line. Remove desired line from array of objects and create new join edge based
     * on this line.
     *
     * @param joinEdge UCJoinEdgeController line to be removed.
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
