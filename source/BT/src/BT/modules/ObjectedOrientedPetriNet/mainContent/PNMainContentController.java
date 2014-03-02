/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BT.modules.ObjectedOrientedPetriNet.mainContent;

import BT.interfaces.DrawingClicks;
import BT.models.CoordinateModel;
import java.awt.event.MouseEvent;

/**
 *
 * @author Karel
 */
public class PNMainContentController extends PNMainContentModel implements DrawingClicks{

    /**
     * 
     */
    public PNMainContentController()
    {
        super();
    }
    
    @Override
    public void drawingPanecheckMove(MouseEvent evt) {
        System.out.println("Generated method"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void drawingPaneDoubleCliked(CoordinateModel pressedObject) {
        System.out.println("Generated method"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void drawingPaneClicked(MouseEvent evt) {
        System.out.println("Generated method"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void drawingMouseDragged(MouseEvent e, CoordinateModel dragged) {
        System.out.println("Generated method"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setSelectedObject(CoordinateModel clickedObject) {
        System.out.println("Generated method"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void buttonsChanged() {
        System.out.println("Generated method"); //To change body of generated methods, choose Tools | Templates.
    }
    
}
