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
 *
 * @author Karel
 */
public class PNMainContent extends ContentPaneModel {

    private final Dimension area;

    /**
     *
     * @param places
     */
    public PNMainContent(PlaceManager places) {
        super();
        this.mainContentPane = new JPanel(new BorderLayout());
        this.drawingPane = new PNDrawingPane(places);
        this.area = new Dimension(0, 0);
        createMainPane();
    }

    /**
     *
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
