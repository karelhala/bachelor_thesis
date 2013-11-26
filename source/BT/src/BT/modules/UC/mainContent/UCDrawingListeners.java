/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.UC.mainContent;

import BT.interfaces.DrawingClicks;
import BT.models.CoordinateModel;
import BT.modules.UC.places.UCJoinEdge.UCJoinEdgeController;
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
    private UCMainContentController UCMainContent;
    
    /**
     * 
     */
    private CoordinateModel draggedObjec;

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
        UCObjectChecker objectChecker = new UCObjectChecker(this.UCMainContent.getPlaces());
        CoordinateModel coordObject = objectChecker.getObjectUnderMouse(evt.getPoint());
        if (coordObject == null)
        {
            this.UCMainContent.drawingPaneClicked(evt);
        }
        else
        {
            this.draggedObjec = coordObject;
            this.UCMainContent.setSelectedObject(coordObject);
        }
    }
    
    /**
     * 
     * @param e 
     */
    @Override
    public void mouseDragged(MouseEvent e){
        if (draggedObjec!= null)
        {
            if (this.draggedObjec instanceof UCJoinEdgeController)
            {
                UCJoinEdgeController draggedJoin = (UCJoinEdgeController) this.draggedObjec;
                if (!draggedJoin.isInRange(e.getX(), e.getY()))
                {
                    this.UCMainContent.drawingMouseDragged(e, this.draggedObjec);
                    this.draggedObjec = null;
                }
            }
            else
            {
                this.UCMainContent.drawingMouseDragged(e, this.draggedObjec);
            }
        }
        else
        {
            this.UCMainContent.deselectAllObjectsAndRepaint();
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
        this.draggedObjec = null;
    }
    
    /**
     * 
     * @param e 
     */
    @Override
    public void mouseClicked(MouseEvent e)
    {
        UCObjectChecker objectChecker = new UCObjectChecker(this.UCMainContent.getPlaces());
        CoordinateModel clickedObject = objectChecker.getObjectUnderMouse(e.getPoint());
        if (e.getClickCount()%2 == 0)
        {
            if (clickedObject != null && !(clickedObject instanceof UCJoinEdgeController))
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
