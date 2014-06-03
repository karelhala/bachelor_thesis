/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ObjectedOrientedPetriNet.mainContent;

import BT.managers.PlaceManager;
import BT.models.CoordinateModel;
import BT.models.DrawingPaneModel;
import BT.models.LineModel;
import BT.modules.ObjectedOrientedPetriNet.places.PNPlace;
import BT.modules.ObjectedOrientedPetriNet.places.PNTransition;
import BT.modules.ObjectedOrientedPetriNet.places.joinEdge.PNJoinEdgeController;
import GUI.DrawingJpanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 * Class that draws each object of petri nets to Jpanel.
 *
 * @author Karel Hala
 */
public class PNDrawingPane extends DrawingPaneModel {

    /**
     * Drawing panel, that draws each object.
     */
    private final PNDrawingPane.drawing drawPane;

    /**
     * Basic constructor. This constructor will set Places and create new drawing Panel.
     *
     * @param PNplaces places that will be drawn.
     */
    public PNDrawingPane(PlaceManager PNplaces) {
        super(PNplaces);
        this.drawPane = new PNDrawingPane.drawing();
    }

    /**
     * Class for drawing each object. It extends DrawingJpanel, which extends Jpanel.
     */
    public class drawing extends DrawingJpanel {

        /**
         * Basic graphic that handels drawing of objects.
         */
        private Graphics2D graphicsPanel;

        /**
         * Override paintComponent for drawing objects.
         * @param g1 Graphics.
         */
        @Override
        protected void paintComponent(Graphics g1) {
            super.paintComponent(g1);
            graphicsPanel = (Graphics2D) g1.create();
            graphicsPanel.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            if (newLine != null) {
                recalculateSize(newLine);
                graphicsPanel.setColor(Color.GREEN);
                ((PNJoinEdgeController) newLine).drawJoinEdge(graphicsPanel);
            }

            for (LineModel joinEdge : places.getJoinEdges()) {
                recalculateSize(joinEdge);
                places.setPaneSize(this.getSize());
                ((PNJoinEdgeController) joinEdge).drawJoinEdge(graphicsPanel);
            }

            for (CoordinateModel place : places.getObjects()) {
                recalculateSize(place);
                places.setPaneSize(this.getSize());
                if (place instanceof PNPlace) {
                    ((PNPlace) place).drawPlace(graphicsPanel);
                } else if (place instanceof PNTransition) {
                    ((PNTransition) place).drawTransition(graphicsPanel);
                }
            }
        }

        public Graphics2D getGraphicsPanel() {
            return graphicsPanel;
        }
    }

    /**
     * Return drawing panel.
     *
     * @return PNDrawingPane.drawing.
     */
    public PNDrawingPane.drawing getDrawing() {
        return drawPane;
    }
}
