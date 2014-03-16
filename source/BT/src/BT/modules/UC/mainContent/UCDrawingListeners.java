/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.UC.mainContent;

import BT.managers.ObjectChecker;
import BT.interfaces.DrawingClicks;
import BT.managers.MainContentController;
import BT.models.CoordinateModel;
import BT.models.LineModel;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;

/**
 *
 * @author Karel Hala
 */
public class UCDrawingListeners extends MouseInputAdapter{
    /**
     * 
     */
    private MainContentController UCMainContent;
    
    /**
     * 
     */
    private CoordinateModel draggedObject;

    /**
     * 
     * @param drawing
     * @param mainContent 
     */
    UCDrawingListeners(DrawingClicks mainContent) {
        if (mainContent instanceof UCMainContentController)
        {
            this.UCMainContent = (UCMainContentController) mainContent;
        }
    }

    /**
     * 
     * @param evt 
     */
    @Override
    public void mousePressed(java.awt.event.MouseEvent evt) {
        ObjectChecker objectChecker = new ObjectChecker(this.UCMainContent.getPlaces());
        CoordinateModel coordObject = objectChecker.getObjectUnderMouse(evt.getPoint());
        if (coordObject == null)
        {
            this.UCMainContent.drawingPaneClicked(evt);
        }
        else
        {
            this.draggedObject = coordObject;
            this.UCMainContent.setSelectedObject(coordObject);
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
                    this.UCMainContent.drawingMouseDragged(e, this.draggedObject);
                    draggedJoin.setSecondObject(null);
                }
            }
            else
            {
                this.UCMainContent.drawingMouseDragged(e, this.draggedObject);
            }
        }
        else
        {
            this.UCMainContent.getPlaces().setAllObjectDiselected();
        }
        this.UCMainContent.drawingPanecheckMove(e);
    }
    
    /**
     * 
     * @param e 
     */
    @Override
    public void mouseMoved(MouseEvent e){
        this.UCMainContent.drawingPanecheckMove(e);
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
            ObjectChecker objectChecker = new ObjectChecker(this.UCMainContent.getPlaces());
            this.UCMainContent.setSelectedObject(objectChecker.getObjectUnderMouse(e.getPoint()));
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
        ObjectChecker objectChecker = new ObjectChecker(this.UCMainContent.getPlaces());
        CoordinateModel clickedObject = objectChecker.getObjectUnderMouse(e.getPoint());
        if (e.getClickCount()%2 == 0)
        {
            if (clickedObject != null && !(clickedObject instanceof LineModel))
            {
                this.UCMainContent.drawingPaneDoubleCliked(clickedObject);
            }
        }
        else
        {
            this.UCMainContent.setSelectedObject(clickedObject);
        }
    }
}
