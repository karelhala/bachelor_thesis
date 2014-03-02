/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BT.modules.ObjectedOrientedPetriNet;

import BT.managers.PlaceManager;
import BT.models.ContentPaneModel;
import BT.modules.ClassDiagram.mainContent.CDDrawingPane;
import BT.modules.ObjectedOrientedPetriNet.mainContent.PNDrawingPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Karel
 */
public class PNMainContent extends ContentPaneModel{
    private PNDrawingPane drawingPane;
    private Dimension area;

    public void setDrawingPane(PNDrawingPane drawingPane) {
        this.drawingPane = drawingPane;
    }

    public PNDrawingPane getDrawingPane() {
        return drawingPane;
    }
    
    /**
     * 
     * @param places
     */
    public PNMainContent(PlaceManager places)
    {
        super();
        this.mainContentPane = new JPanel(new BorderLayout());
        this.drawingPane = new PNDrawingPane(places);
        this.area = new Dimension(0,0);
        createMainPane();
    }
    
    /**
     * 
     */
    private void createMainPane() {
        this.drawingPane.getDrawing().setPreferredSize(this.area);
        this.drawingPane.getDrawing().setBackground(Color.WHITE);
        drawingPane.getDrawing().repaint();
        JScrollPane myScrollPane = new JScrollPane(drawingPane.getDrawing());
        this.mainContentPane.add(myScrollPane, BorderLayout.CENTER);
    }
}
