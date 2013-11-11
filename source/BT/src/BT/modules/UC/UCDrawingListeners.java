/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.UC;

import BT.managers.CoordinateManager;
import BT.managers.UC.UCActor;
import BT.managers.UC.UCUseCase;
import GUI.DrawingPane;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;

/**
 *
 * @author Karel Hala
 */
public class UCDrawingListeners extends MouseInputAdapter{
    private DrawingPane.drawing drawing;
    private UCMainContent mainContent;
    private CoordinateManager draggedObjec;

    UCDrawingListeners(DrawingPane.drawing drawing, UCMainContent mainContent) {
        this.drawing = drawing;
        this.mainContent = mainContent;
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent evt) {
        final UCActor actor = this.mainContent.isActorUnderMouse(evt.getX(), evt.getY());
        final UCUseCase useCase = this.mainContent.isUseCaseUnderMouse(evt.getX(), evt.getY());
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
    }
    
    @Override
    public void mouseDragged(MouseEvent e){
        if (draggedObjec!= null)
        {
            this.mainContent.mouseDragged(e, this.draggedObjec);
        }
    }
    
    @Override
    public void mouseMoved(MouseEvent e){
        this.mainContent.drawingPanecheckMove(e);
    }
    
    @Override
    public void mouseReleased(MouseEvent e)
    {
        this.draggedObjec = null;

    }
    
    @Override
    public void mouseClicked(MouseEvent e)
    {
        final UCActor actor = this.mainContent.isActorUnderMouse(e.getX(), e.getY());
        final UCUseCase useCase = this.mainContent.isUseCaseUnderMouse(e.getX(), e.getY());
        CoordinateManager clickedObject;
        clickedObject = (actor != null)? actor:((useCase != null)?useCase:null);
        if (e.getClickCount()%2 == 0)
        {
            if (clickedObject != null)
            {
                this.mainContent.objectDoubleClicked(clickedObject);
            }
        }
        else
        {
            this.mainContent.setSelectedObject(clickedObject);
            this.mainContent.clickedOnObject(clickedObject);
        }
    }
}
