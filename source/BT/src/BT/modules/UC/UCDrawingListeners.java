/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.UC;

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
    }
    
    @Override
    public void mouseDragged(MouseEvent e){
        this.mainContent.mouseDragged(e);
    }
    
    @Override
    public void mouseMoved(MouseEvent e){
        this.mainContent.drawingPanecheckMove(e);
    }
}
