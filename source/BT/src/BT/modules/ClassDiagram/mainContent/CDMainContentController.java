/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ClassDiagram.mainContent;

import BT.interfaces.DrawingClicks;
import BT.models.CoordinateModel;
import java.awt.event.MouseEvent;

/**
 *
 * @author Karel Hala
 */
public class CDMainContentController extends CDMainContentModel implements DrawingClicks{

    /**
     * 
     */
    public CDMainContentController()
    {
        super();
    }
    
    /**
     * 
     * @param e
     * @param dragged 
     */
    @Override
    public void drawingMouseDragged(MouseEvent e, CoordinateModel dragged)
    {
        
    }
    
    /**
     * 
     * @param evt 
     */
    @Override
    public void drawingPaneClicked(MouseEvent evt) 
    {
        
    }
    
    /**
     * 
     * @param evt 
     */
    @Override
    public void drawingPanecheckMove(MouseEvent evt) 
    {
       
    }

    /**
     * 
     * @param clickedObject 
     */
    @Override
    public void setSelectedObject(CoordinateModel clickedObject) 
    {

    }
    
    /**
     * 
     * @param pressedObject 
     */
    @Override
    public void drawingPaneDoubleCliked(CoordinateModel pressedObject) 
    {

    }

    @Override
    public void buttonsChanged() {
        System.out.println("buttons has been changed");
    }
}
