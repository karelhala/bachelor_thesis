/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ClassDiagram.mainContent;

import BT.managers.PlaceManager;
import BT.models.CoordinateModel;
import BT.models.DrawingPaneModel;
import BT.models.LineModel;
import BT.modules.ClassDiagram.places.CDClass;
import BT.modules.ClassDiagram.places.joinEdge.CDJoinEdgeController;
import GUI.DrawingJpanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 * Class for drawing every object of class diagram to drawing pane. It has one variable from parrent, which is newLine,
 * this draws newLine differently, because it is not stored in places, yet.
 *
 * @author Karel Hala
 */
public class CDDrawingPane extends DrawingPaneModel {

    /**
     * Draing panel, extends Jpenel.
     */
    private final drawing drawPane;

    /**
     * Basic contructor. It calls parents constructor and creates new drawingPanel.
     *
     * @param CDplaces PlaceManager with each class diagram place (object and line).
     */
    public CDDrawingPane(PlaceManager CDplaces) {
        super(CDplaces);
        this.drawPane = new drawing();
    }

    /**
     * Drawing panel, that displays each object of class diagram. Extends from DrawingPanel, which extends from Jpanel.
     */
    public class drawing extends DrawingJpanel {

        /**
         * Constructor, that calls DrawingJpanel.
         */
        public drawing() {
            super();
        }

        /**
         * Method for painting each object of class diagram. New line is drawn with green color. Set's antialiasing.
         *
         * @param g1 Graphics.
         */
        @Override
        protected void paintComponent(Graphics g1) {
            super.paintComponent(g1);
            Graphics2D g = (Graphics2D) g1.create();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            if (newLine != null) {
                recalculateSize(newLine);
                g.setColor(Color.GREEN);
                ((CDJoinEdgeController) newLine).drawJoinEdge(g);
            }

            for (LineModel joinEdge : places.getJoinEdges()) {
                recalculateSize(joinEdge);
                places.setPaneSize(this.getSize());
                ((CDJoinEdgeController) joinEdge).drawJoinEdge(g);
            }

            for (CoordinateModel drawnClass : places.getObjects()) {
                recalculateSize(drawnClass);
                places.setPaneSize(this.getSize());
                if (drawnClass instanceof CDClass) {
                    ((CDClass) drawnClass).drawClass(g);
                }
            }
        }
    }

    /**
     * Get drawing of this class, it is for redrawing.
     *
     * @return classDiagram drawing.
     */
    public CDDrawingPane.drawing getDrawing() {
        return drawPane;
    }
}
