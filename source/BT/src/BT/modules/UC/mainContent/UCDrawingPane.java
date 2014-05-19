/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.UC.mainContent;

import BT.modules.UC.places.UCActor;
import BT.modules.UC.places.UCJoinEdge.UCJoinEdgeController;
import BT.managers.PlaceManager;
import BT.models.CoordinateModel;
import BT.models.DrawingPaneModel;
import BT.models.LineModel;
import BT.modules.UC.places.UCUseCase;
import GUI.DrawingJpanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 * This class stores drawing pane for useCase.
 * 
 * @author Karel Hala
 */
public class UCDrawingPane extends DrawingPaneModel {

    /**
     * Drawing pane which draws each object off useCase.
     */
    private final drawing drawPane;

    /**
     * Basic construcotr.
     * 
     * @param UCPlaces PlaceManager which sotres each object off useCase.
     */
    public UCDrawingPane(PlaceManager UCPlaces) {
        super(UCPlaces);
        this.drawPane = new drawing();
    }

    /**
     * Class which draws each object off useCase PlaceManager. it is descendant of DrawingJpanel.
     */
    public class drawing extends DrawingJpanel {

        /**
         * Overrides paintComponent of Jpanel.
         * @param g1 Graphics which will draw each object on Jpanel.
         */
        @Override
        protected void paintComponent(Graphics g1) {
            super.paintComponent(g1);
            Graphics2D g = (Graphics2D) g1.create();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            if (newLine != null) {
                recalculateSize(newLine);
                g.setColor(Color.GREEN);
                ((UCJoinEdgeController) newLine).drawJoinEdge(g);

            }

            for (LineModel joinEdge : places.getJoinEdges()) {
                recalculateSize(joinEdge);
                places.setPaneSize(this.getSize());
                ((UCJoinEdgeController) joinEdge).drawJoinEdge(g);
            }

            for (CoordinateModel object : places.getObjects()) {
                recalculateSize(object);
                places.setPaneSize(this.getSize());
                if (object instanceof UCActor) {
                    ((UCActor) object).drawActor(g);
                } else if (object instanceof UCUseCase) {
                    ((UCUseCase) object).drawUseCase(g);
                }
            }
        }
    }

    /**
     * Returns drawing panel which draws each useCase object.
     * 
     * @return drawing of useCase.
     */
    public drawing getDrawing() {
        return this.drawPane;
    }
}
