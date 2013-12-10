/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ClassDiagram.mainContent;

import BT.interfaces.DrawingClicks;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;

/**
 *
 * @author Karel Hala
 */
public class CDDrawingListeners extends MouseInputAdapter{
    private CDMainContentController mainContent;
    
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
        System.out.println("dragged");
    }
    
    @Override
    public void mouseMoved(MouseEvent e){
        
    }
    
    @Override
    public void mouseReleased(MouseEvent e)
    {
        System.out.println("released");
    }
    
    @Override
    public void mouseClicked(MouseEvent e)
    {
        System.out.println("clicked");
    }
}
