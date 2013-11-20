/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.UC.mainContent;

import BT.models.CoordinateModel;
import BT.modules.UC.places.UCActor;
import BT.modules.UC.places.UCJoinEdge;
import BT.modules.UC.places.UCUseCase;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;

/**
 *
 * @author Karel Hala
 */
public class UCDrawingListeners extends MouseInputAdapter{
    private UCMainContent mainContent;
    private CoordinateModel draggedObjec;

    /**
     * 
     * @param drawing
     * @param mainContent 
     */
    UCDrawingListeners(UCDrawingPane.drawing drawing, UCMainContent mainContent) {
        this.mainContent = mainContent;
    }

    /**
     * 
     * @param evt 
     */
    @Override
    public void mousePressed(java.awt.event.MouseEvent evt) {
        final UCActor actor = this.mainContent.isActorUnderMouse(evt.getX(), evt.getY());
        final UCUseCase useCase = this.mainContent.isUseCaseUnderMouse(evt.getX(), evt.getY());
        final UCJoinEdge joinEdge = this.mainContent.isJoinEdgeUnderMouse(evt.getX(), evt.getY());
        if (actor == null && useCase == null)
        {
            this.mainContent.drawingPaneClicked(evt);
        }
        else if (actor != null)
        {
            this.draggedObjec = actor;
            this.mainContent.setSelectedObject(actor);
        }
        else if (useCase != null)
        {
            this.draggedObjec = useCase;
            this.mainContent.setSelectedObject(useCase);
        }
        if (joinEdge != null)
        {
            this.draggedObjec = joinEdge;
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
            this.mainContent.mouseDragged(e, this.draggedObjec);
        }
        else
        {
            this.mainContent.deselectAllObjectsAndRepaint();
        }
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
        CoordinateModel clickedObject;
        clickedObject = getModelUnderMouse(e);
        final UCJoinEdge joinEdge = this.mainContent.isJoinEdgeUnderMouse(e.getX(), e.getY());
        if (e.getClickCount()%2 == 0)
        {
            if (clickedObject != null)
            {
                this.mainContent.objectDoubleClicked(clickedObject);
            }
        }
        else
        {
            clickedObject = (clickedObject != null)? clickedObject:((joinEdge != null) ? joinEdge : null);
            this.mainContent.setSelectedObject(clickedObject);
        }
    }
    
    /**
     * 
     * @param e
     * @return 
     */
    private CoordinateModel getModelUnderMouse(MouseEvent e)
    {
        CoordinateModel coordModel;
        final UCActor actor = this.mainContent.isActorUnderMouse(e.getX(), e.getY());
        final UCUseCase useCase = this.mainContent.isUseCaseUnderMouse(e.getX(), e.getY());
        coordModel = (actor != null)? actor:((useCase != null)?useCase:null);
        return coordModel;
    }
}
