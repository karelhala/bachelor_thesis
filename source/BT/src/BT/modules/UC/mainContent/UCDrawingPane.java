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
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Karel Hala
 */
public class UCDrawingPane extends DrawingPaneModel {

    /**
     *
     */
    private final drawing drawPane;

    /**
     *
     * @param UCPlaces
     */
    public UCDrawingPane(PlaceManager UCPlaces) {
        super(UCPlaces);
        this.drawPane = new drawing();
    }

    /**
     *
     */
    public class drawing extends JPanel {

        /**
         *
         * @param g1
         */
        @Override
        protected void paintComponent(Graphics g1) {
            super.paintComponent(g1);
            Graphics2D g = (Graphics2D) g1.create();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            if (newLine != null) {
                g.setColor(Color.GREEN);
                ((UCJoinEdgeController) newLine).drawJoinEdge(g);

            }

            for (LineModel joinEdge : places.getJoinEdges()) {
                ((UCJoinEdgeController) joinEdge).drawJoinEdge(g);
            }

            for (CoordinateModel object : places.getObjects()) {
                if (object instanceof UCActor) {
                    ((UCActor) object).drawActor(g);
                } else if (object instanceof UCUseCase) {
                    ((UCUseCase) object).drawUseCase(g);
                }
            }
        }
    }

    /**
     *
     * @return
     */
    public drawing getDrawing() {
        return this.drawPane;
    }
}
