/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.managers;

import BT.interfaces.DrawingClicks;
import BT.models.CoordinateModel;
import BT.models.LineModel;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;

/**
 * Class for managing clicks on drawing pane.
 * @author Karel Hala
 */
public class DrawingListeners extends MouseInputAdapter {

    /**
     * Controller that holds function for controlling these actions.
     */
    private MainContentController mainContent;

    /**
     * Object, that is being dragged on drawing pane.
     */
    private CoordinateModel draggedObject;

    /**
     * Basic constructor for creating drawing listeners.
     * @param mainContent this class reacts to messages.
     */
    public DrawingListeners(DrawingClicks mainContent) {
        this.mainContent = (MainContentController) mainContent;
    }

    /**
     * This will be called when mouse pressed.
     * When right click call mainContent.rightClick.
     * When left click get object under mouse, if no object call mainContent.drawingPaneClicked.
     * When object under mouse, call mainContent.setSelectedObject.
     * @param evt
     */
    @Override
    public void mousePressed(java.awt.event.MouseEvent evt) {
        if (SwingUtilities.isRightMouseButton(evt)) {
            this.mainContent.rightClick(evt);
        } else {
            ObjectChecker objectChecker = new ObjectChecker(this.mainContent.getPlaces());
            CoordinateModel coordObject = objectChecker.getObjectUnderMouse(evt.getPoint());
            if (coordObject == null) {
                this.mainContent.drawingPaneClicked(evt);
            } else {
                this.draggedObject = coordObject;
                this.mainContent.setSelectedObject(coordObject);
            }
        }
    }

    /**
     * This method is called when mouse is dragged.
     * Check if any object is dragged.
     * Check if dragged line, then dragg it only if join in range
     * otherwise call mainContent.drawingMouseDragged
     * If no object under mouse, diselect all objects.
     * @param e
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        if (draggedObject != null) {
            if (this.draggedObject instanceof LineModel) {
                LineModel draggedJoin = (LineModel) this.draggedObject;
                if (!draggedJoin.isInRange(e.getX(), e.getY())) {
                    this.mainContent.drawingMouseDragged(e, this.draggedObject);
                    draggedJoin.setSecondObject(null);
                }
            } else {
                this.mainContent.drawingMouseDragged(e, this.draggedObject);
            }
        } else {
            this.mainContent.getPlaces().setAllObjectDiselected();
        }
        this.mainContent.drawingPanecheckMove(e);
    }

    /**
     * This method is called when mouse is moving on pane.
     * @param e
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        this.mainContent.drawingPanecheckMove(e);
    }

    /**
     * When you release mouse, set dragged object to null.
     * @param e
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            if (this.draggedObject instanceof LineModel) {
                ObjectChecker objectChecker = new ObjectChecker(this.mainContent.getPlaces());
                this.mainContent.setSelectedObject(objectChecker.getObjectUnderMouse(e.getPoint()));
            }
            this.draggedObject = null;
        }
    }

    /**
     * This method is called when mouse clicked (pressed and released).
     * Check double click or call mainContent.setSelectedObject
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            ObjectChecker objectChecker = new ObjectChecker(this.mainContent.getPlaces());
            CoordinateModel clickedObject = objectChecker.getObjectUnderMouse(e.getPoint());
            if (e.getClickCount() % 2 == 0) {
                if (clickedObject != null) {
                    this.mainContent.drawingPaneDoubleCliked(clickedObject);
                }
            } else {
                this.mainContent.setSelectedObject(clickedObject);
            }
        }
    }
}
