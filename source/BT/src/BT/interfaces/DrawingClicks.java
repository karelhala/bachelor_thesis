/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.interfaces;

import BT.models.CoordinateModel;
import java.awt.event.MouseEvent;

/**
 * Interface for clicks on drawing pane.
 *
 * @author Karel Hala
 */
public interface DrawingClicks {

    /**
     * Method for handling moving on pane with mouse.
     *
     * @param evt mouseevent
     */
    public void drawingPanecheckMove(MouseEvent evt);

    /**
     * Method for handling double clicks on drawing pane.
     *
     * @param pressedObject if any object is under mouse, this will be sent to method.
     */
    public void drawingPaneDoubleCliked(CoordinateModel pressedObject);

    /**
     * Method for handling if drawing pane was clicked.
     *
     * @param evt mouseevent
     */
    public void drawingPaneClicked(MouseEvent evt);

    /**
     * Method for handling dragging on pane. Send mouse event and object that is being dragged is any.
     *
     * @param e mouse event for this method
     * @param dragged object that is being dragged on pane if is any.
     */
    public void drawingMouseDragged(MouseEvent e, CoordinateModel dragged);

    /**
     * Method for setting clicked object selected.
     *
     * @param clickedObject this object will be set as selected
     */
    public void setSelectedObject(CoordinateModel clickedObject);

    /**
     * Method for handeling if buttons are changed.
     */
    public void buttonsChanged();

    /**
     * Method for handeling right mouse click.
     *
     * @param evt mouse event for this method
     */
    public void rightClick(MouseEvent evt);
}
