/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.interfaces;

import BT.models.CoordinateModel;
import java.awt.event.MouseEvent;

/**
 *
 * @author Karel Hala
 */
public interface DrawingClicks {

    public void drawingPanecheckMove(MouseEvent evt);

    public void drawingPaneDoubleCliked(CoordinateModel pressedObject);

    public void drawingPaneClicked(MouseEvent evt);

    public void drawingMouseDragged(MouseEvent e, CoordinateModel dragged);

    public void setSelectedObject(CoordinateModel clickedObject);

    public void buttonsChanged();

    public void rightClick(MouseEvent evt);
}
