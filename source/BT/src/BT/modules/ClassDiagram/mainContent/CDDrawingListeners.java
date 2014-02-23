/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ClassDiagram.mainContent;

import BT.interfaces.DrawingClicks;
import BT.managers.ObjectChecker;
import BT.models.CoordinateModel;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;

/**
 *
 * @author Karel Hala
 */
public class CDDrawingListeners extends MouseInputAdapter{
    /**
     * 
     */
    private CDMainContentController mainContent;
    
    /**
     * 
     */
    private CoordinateModel selectedModel;
    
    public CDDrawingListeners(DrawingClicks mainContent)
    {
        this.mainContent = (CDMainContentController) mainContent;
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
