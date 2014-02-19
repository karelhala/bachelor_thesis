/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ClassDiagram.mainContent;

import BT.interfaces.DrawingClicks;
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
     * @param evt 
     */
    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Pressed");
    }
    
    @Override
    public void mouseDragged(MouseEvent e){
        this.mainContent.drawingMouseDragged(e, null);
    }
    
    @Override
    public void mouseMoved(MouseEvent e){
        this.mainContent.drawingPanecheckMove(e);
    }
    
    @Override
    public void mouseReleased(MouseEvent e)
    {
        System.out.println("released");
    }
    
    @Override
    public void mouseClicked(MouseEvent e)
    {
        this.mainContent.drawingPaneClicked(e);
        if (e.getClickCount()%2 == 0)
        {
            this.mainContent.drawingPaneDoubleCliked(null);
        }
    }
}
