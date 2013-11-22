/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.UC;

import BT.managers.UC.UCPlaceManager;
import BT.models.ContentPaneModel;
import BT.modules.UC.mainContent.UCDrawingPane;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Karel Hala
 */
public final class UCMainContent extends ContentPaneModel{
    
    private UCDrawingPane drawingPane;

    public void setDrawingPane(UCDrawingPane drawingPane) {
        this.drawingPane = drawingPane;
    }

    public UCDrawingPane getDrawingPane() {
        return drawingPane;
    }
    
    /**
     * 
     */
    public UCMainContent()
    {
        this(null);
    }
    
    /**
     * 
     */
    public UCMainContent(UCPlaceManager places)
    {
        super();
        this.mainContentPane = new JPanel(new BorderLayout());
        this.drawingPane = new UCDrawingPane(places);
        createMainPane();
    }

    /**
     * 
     */
    private void createMainPane() {
        this.drawingPane.getDrawing().setBackground(Color.WHITE);
        drawingPane.getDrawing().repaint();
        drawingPane.setButtonsListeners();
        JScrollPane myScrollPane = new JScrollPane(drawingPane.getDrawing());
        
        this.mainContentPane.add(myScrollPane, BorderLayout.CENTER);
    }
}
