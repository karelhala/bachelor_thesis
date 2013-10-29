/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.UC;

import GUI.DrawingPane;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;

/**
 *
 * @author Karel
 */
public class UCDrawingListeners extends MouseInputAdapter{
    private DrawingPane.drawing drawing;
    private UCMainContent mainContent;

    UCDrawingListeners(DrawingPane.drawing drawing, UCMainContent mainContent) {
        this.drawing = drawing;
        this.mainContent = mainContent;
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        System.out.println("mouse dragged");
    }

    @Override
    public void mouseReleased(MouseEvent event) {

    }
}
