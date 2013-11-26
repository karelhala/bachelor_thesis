/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.UC.mainContent;

import BT.managers.UC.UCPlaceManager;
import BT.models.CoordinateModel;
import BT.modules.UC.places.UCActor;
import BT.modules.UC.places.UCJoinEdge.UCJoinEdgeController;
import BT.modules.UC.places.UCUseCase;
import java.awt.Point;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.event.MouseInputAdapter;

/**
 *
 * @author Karel Hala
 */
public class UCDrawingListeners extends MouseInputAdapter{
    /**
     * 
     */
    private UCMainContentController mainContent;
    /**
     * 
     */
    private CoordinateModel draggedObjec;

    /**
     * 
     * @param drawing
     * @param mainContent 
     */
    UCDrawingListeners(UCDrawingPane.drawing drawing, UCMainContentController mainContent) {
        this.mainContent = mainContent;
    }

    /**
     * 
     * @param evt 
     */
    @Override
    public void mousePressed(java.awt.event.MouseEvent evt) {
        UCObjectChecker objectChecker = new UCObjectChecker(this.mainContent.getPlaces());
        CoordinateModel coordObject = objectChecker.getObjectUnderMouse(evt.getPoint());
        if (coordObject == null)
        {
            this.mainContent.drawingPaneClicked(evt);
        }
        else
        {
            this.draggedObjec = coordObject;
            this.mainContent.setSelectedObject(coordObject);
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
                    this.mainContent.mouseDragged(e, this.draggedObjec);
                    this.draggedObjec = null;
                }
            }
            else
            {
                this.mainContent.mouseDragged(e, this.draggedObjec);
            }
        }
        else
        {
            this.mainContent.deselectAllObjectsAndRepaint();
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
        this.draggedObjec = null;
    }
    
    /**
     * 
     * @param e 
     */
    @Override
    public void mouseClicked(MouseEvent e)
    {
        UCObjectChecker objectChecker = new UCObjectChecker(this.mainContent.getPlaces());
        CoordinateModel clickedObject = objectChecker.getObjectUnderMouse(e.getPoint());
        if (e.getClickCount()%2 == 0)
        {
            if (clickedObject != null && !(clickedObject instanceof UCJoinEdgeController))
            {
                objectDoubleClicked(clickedObject);
            }
        }
        else
        {
            this.mainContent.setSelectedObject(clickedObject);
        }
    }
    
    /**
     * 
     * @param pressedObject 
     */
    public void objectDoubleClicked(CoordinateModel pressedObject) 
    {
        String name = (String) JOptionPane.showInputDialog("Enter name of the object",pressedObject.getName());
        if (name!= null && !"".equals(name))
            pressedObject.setName(name);
        this.mainContent.getMainContent().getDrawingPane().getDrawing().repaint();
    }
}
