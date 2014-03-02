/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BT.modules.ObjectedOrientedPetriNet.mainContent;

import BT.interfaces.DrawingClicks;
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
        System.out.println("Pressed");
    }
    
    @Override
    public void mouseDragged(MouseEvent e){
        System.out.println("Dragged");
    }
    
    @Override
    public void mouseMoved(MouseEvent e){
        System.out.println("Moved");
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
        this.mainContent.drawingPaneClicked(e);   
    }
}
