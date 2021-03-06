/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ObjectedOrientedPetriNet.places.joinEdge;

import BT.managers.JoinEdgeDrawer;
import BT.models.LineModel;
import BT.modules.ObjectedOrientedPetriNet.places.PNTransition;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 * Class for drawing petriNet join edges. Extends basic JoinEdgeDrawer with methods for drawing specific shapes and
 * string at different coordinates.
 *
 * @author Karel Hala
 */
public class PNJoinEdgeDrawer extends JoinEdgeDrawer {

    /**
     * Basic constructor. It calls parent's contructor.
     *
     * @param joinEdgeController specified in JoinEdgeDrawer.
     * @param startPoint specified in JoinEdgeDrawer.
     * @param endPoint specified in JoinEdgeDrawer.
     */
    public PNJoinEdgeDrawer(LineModel joinEdgeController, Point startPoint, Point endPoint) {
        super(joinEdgeController, startPoint, endPoint);
    }

    /**
     * Method for drawing each type of line with different end objects. It draws string at correct position, it draws
     * variables of petriNet join edge at correct place.
     *
     * @param g Graphics2D that will draw this join edge.
     */
    public void drawLine(Graphics2D g) {
        if (this.joinEdgeController.getSelected()) {
            g.setColor(this.joinEdgeController.getSelectedColor());
        } else {
            g.setColor(this.joinEdgeController.getColor());
        }

        float dash1[] = {10.0f};
        BasicStroke dashed
                = new BasicStroke(2.0f,
                        BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER,
                        10.0f, dash1, 0.0f);
        if (this.joinEdgeController instanceof PNJoinEdgeController) {
            PNJoinEdgeController pnJoin = (PNJoinEdgeController) this.joinEdgeController;
            Point arrowStartPoint = (pnJoin.getBreakPoints() != null && !pnJoin.getBreakPoints().isEmpty()) ? pnJoin.getBreakPoints().getLast() : this.startPoint;
            setStartEndPointsText(pnJoin);
            if (pnJoin.getJoinEdgeType() == BT.BT.OOPNLineType.JOIN) {
                g.setStroke(new BasicStroke(2));
                drawbreakedLine(g, this.startPoint, this.endPoint, pnJoin.getBreakPoints());
                drawTriangle(g, this.endPoint, arrowStartPoint, Color.BLACK);
                if (pnJoin.getFirstObject() instanceof PNTransition) {
                    if (pnJoin.getSelectedVariables() == null || pnJoin.getSelectedVariables().isEmpty()) {
                        if (pnJoin.getAdditionalVariable() == null || pnJoin.getAdditionalVariable().equals("")) {
                            if (((PNTransition) pnJoin.getFirstObject()).getActionVariable() != null) {
                                drawString(g, ((PNTransition) pnJoin.getFirstObject()).getActionVariable());
                            }
                        } else {
                            drawString(g, pnJoin.getVariablesAsString());
                        }
                    } else {
                        drawString(g, pnJoin.getVariablesAsString());
                    }
                } else {
                    drawString(g, pnJoin.getVariablesAsString());
                }
            }

        }
    }

}
