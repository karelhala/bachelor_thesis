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
import static javax.swing.SwingUtilities.isLeftMouseButton;
import javax.swing.event.MouseInputAdapter;

/**
 *
 * @author Karel Hala
 */
public class DrawingListeners extends MouseInputAdapter{
    /**
     * 
     */
    private MainContentController mainContent;
    
    /**
     * 
     */
    private CoordinateModel draggedObject;

    /**
     * 
     * @param mainContent 
     */
    public DrawingListeners(DrawingClicks mainContent) {
            this.mainContent = (MainContentController) mainContent;
    }

    /**
     * 
     * @param evt 
     */
    @Override
    public void mousePressed(java.awt.event.MouseEvent evt) {
        if (SwingUtilities.isRightMouseButton(evt))
        {
            this.mainContent.rightClick(evt);
        }
        else
        {
            ObjectChecker objectChecker = new ObjectChecker(this.mainContent.getPlaces());
            CoordinateModel coordObject = objectChecker.getObjectUnderMouse(evt.getPoint());
            if (coordObject == null)
            {
                this.mainContent.drawingPaneClicked(evt);
            }
            else
            {
                this.draggedObject = coordObject;
                this.mainContent.setSelectedObject(coordObject);
            }
        }
    }
    
    /**
     * 
     * @param e 
     */
    @Override
    public void mouseDragged(MouseEvent e){
        if (draggedObject!= null)
        {
            if (this.draggedObject instanceof LineModel)
            {
                LineModel draggedJoin = (LineModel) this.draggedObject;
                if (!draggedJoin.isInRange(e.getX(), e.getY()))
                {
                    this.mainContent.drawingMouseDragged(e, this.draggedObject);
                    draggedJoin.setSecondObject(null);
                }
            }
            else
            {
                this.mainContent.drawingMouseDragged(e, this.draggedObject);
            }
        }
        else
        {
            this.mainContent.getPlaces().setAllObjectDiselected();
        }
        this.mainContent.drawingPanecheckMove(e);
    }
    
    /**
     * 
     * @param e 
     */
    @Override
    public void mouseMoved(MouseEvent e){
        this.mainContent.drawingPanecheckMove(e);
    }
    
    /**
     * 
     * @param e 
     */
    @Override
    public void mouseReleased(MouseEvent e)
    {
        if (this.draggedObject instanceof LineModel)
        {
            ObjectChecker objectChecker = new ObjectChecker(this.mainContent.getPlaces());
            this.mainContent.setSelectedObject(objectChecker.getObjectUnderMouse(e.getPoint()));
        }
        this.draggedObject = null;
    }
    
    /**
     * 
     * @param e 
     */
    @Override
    public void mouseClicked(MouseEvent e)
    {
        ObjectChecker objectChecker = new ObjectChecker(this.mainContent.getPlaces());
        CoordinateModel clickedObject = objectChecker.getObjectUnderMouse(e.getPoint());
        if (e.getClickCount()%2 == 0)
        {
            if (clickedObject != null && !(clickedObject instanceof LineModel))
            {
                this.mainContent.drawingPaneDoubleCliked(clickedObject);
            }
        }
        else
        {
            this.mainContent.setSelectedObject(clickedObject);
        }
    }
}
