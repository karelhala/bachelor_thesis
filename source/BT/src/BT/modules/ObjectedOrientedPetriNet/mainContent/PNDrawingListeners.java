/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BT.modules.ObjectedOrientedPetriNet.mainContent;

import BT.interfaces.DrawingClicks;
import BT.managers.ObjectChecker;
import BT.models.CoordinateModel;
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
    
    @Override
    public void mouseDragged(MouseEvent e){
        this.mainContent.drawingMouseDragged(e, this.selectedModel);
    }
    
    @Override
    public void mouseMoved(MouseEvent e){
        this.mainContent.drawingPanecheckMove(e);
    }
    
    @Override
    public void mouseReleased(MouseEvent e)
    {
        this.selectedModel = null;
    }
    
    @Override
    public void mouseClicked(MouseEvent e)
    {
        ObjectChecker objectChecker = new ObjectChecker(this.mainContent.getMainContent().getDrawingPane().getPlaces());
        CoordinateModel coordObject = objectChecker.getObjectUnderMouse(e.getPoint());
        if (e.getClickCount()%2 == 0)
        {
            this.mainContent.drawingPaneDoubleCliked(coordObject);
        }
        else
        {
            this.mainContent.drawingPaneClicked(e);   
        }
    }
}
