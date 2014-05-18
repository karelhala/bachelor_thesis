/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ObjectedOrientedPetriNet;

import BT.managers.PlaceManager;
import BT.models.ContentPaneModel;
import BT.modules.ObjectedOrientedPetriNet.mainContent.PNDrawingPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Here every part of main content is stored and created.
 *
 * @author Karel Hala
 */
public class PNMainContent extends ContentPaneModel {

    /**
     * Area of drawing panel.
     */
    private final Dimension area;

    /**
     * Basic constructor. It will create new content Pane, drawing pane and set the area to 0 0.
     *
     * @param places PlaceManager with places of petriNet.
     */
    public PNMainContent(PlaceManager places) {
        super();
        this.mainContentPane = new JPanel(new BorderLayout());
        this.drawingPane = new PNDrawingPane(places);
        this.area = new Dimension(0, 0);
        createMainPane();
    }

    /**
     * Creates main panel. It will set preferred size to area, background color to white, repaint it and position it in 
     * JscrollPanel.
     */
    private void createMainPane() {
        PNDrawingPane pnDrawingPane = (PNDrawingPane) this.drawingPane;
        pnDrawingPane.getDrawing().setPreferredSize(this.area);
        pnDrawingPane.getDrawing().setBackground(Color.WHITE);
        pnDrawingPane.getDrawing().repaint();
        JScrollPane myScrollPane = new JScrollPane(pnDrawingPane.getDrawing());
        this.mainContentPane.add(myScrollPane, BorderLayout.CENTER);
    }
}
