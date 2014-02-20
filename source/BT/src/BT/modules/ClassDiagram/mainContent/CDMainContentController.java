/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ClassDiagram.mainContent;

import BT.interfaces.DrawingClicks;
import BT.models.CoordinateModel;
import BT.modules.ClassDiagram.places.CDClass;
import java.awt.event.MouseEvent;
import javax.swing.JToggleButton;

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
        System.out.println("dragged");
    }
    
    /**
     * 
     * @param evt 
     */
    @Override
    public void drawingPaneClicked(MouseEvent evt) 
    {
        JToggleButton selectedItemButton = this.LeftTopContent.getSelectedButton();
        if (selectedItemButton != null && "ACTOR".equals(selectedItemButton.getName()))
        {
            this.places.addObject(new CDClass(evt.getX(), evt.getY()));
            this.mainContent.getDrawingPane().getDrawing().repaint();
        }
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
        System.out.println("doublecliked");
    }

    @Override
    public void buttonsChanged() {
        System.out.println("buttons has been changed");
    }
}
