/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BT.modules.ObjectedOrientedPetriNet.mainContent;

import BT.interfaces.DrawingClicks;
import BT.managers.ObjectChecker;
import BT.models.CoordinateModel;
import BT.models.LineModel;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;

/**
 *
 * @author Karel
 */
public class PNDrawingListeners extends MouseInputAdapter{
    /**
     * 
     */
    private PNMainContentController mainContent;
    
    /**
     * 
     */
    private CoordinateModel selectedModel;
    
    public PNDrawingListeners(DrawingClicks mainContent)
    {
        this.mainContent = (PNMainContentController) mainContent;
    }
    
    /**
     * 
     * @param e 
     */
    @Override
    public void mousePressed(MouseEvent e) {
        ObjectChecker objectChecker = new ObjectChecker(this.mainContent.getMainContent().getDrawingPane().getPlaces());
        CoordinateModel coordObject = objectChecker.getObjectUnderMouse(e.getPoint());
        if (coordObject == null)
        {
            this.mainContent.drawingPaneClicked(e);
        }
        else
        {
            this.mainContent.setSelectedObject(coordObject);
            this.selectedModel = coordObject;
        }
    }
    
    /**
     * 
     * @param e 
     */
    @Override
    public void mouseDragged(MouseEvent e){
        if (selectedModel!= null)
        {
            if (this.selectedModel instanceof LineModel)
            {
                LineModel draggedJoin = (LineModel) this.selectedModel;
                if (!draggedJoin.isInRange(e.getX(), e.getY()))
                {
                    this.mainContent.drawingMouseDragged(e, this.selectedModel);
                    draggedJoin.setSecondObject(null);
                }
            }
            else
            {
                this.mainContent.drawingMouseDragged(e, this.selectedModel);
            }
        }
        else
        {
            this.mainContent.getMainContent().getDrawingPane().getPlaces().setAllObjectDiselected();
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
        if (this.selectedModel instanceof LineModel)
        {
            ObjectChecker objectChecker = new ObjectChecker(this.mainContent.getMainContent().getDrawingPane().getPlaces());
            this.mainContent.setSelectedObject(objectChecker.getObjectUnderMouse(e.getPoint()));
        }
        this.selectedModel = null;
    }
    
    /**
     * 
     * @param e 
     */
    @Override
    public void mouseClicked(MouseEvent e)
    {
        ObjectChecker objectChecker = new ObjectChecker(this.mainContent.getMainContent().getDrawingPane().getPlaces());
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
