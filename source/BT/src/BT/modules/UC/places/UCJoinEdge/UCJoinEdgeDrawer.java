/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.UC.places.UCJoinEdge;

import BT.BT;
import BT.managers.JoinEdgeDrawer;
import BT.models.LineModel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 *
 * @author Karel Hala
 */
public class UCJoinEdgeDrawer extends JoinEdgeDrawer {

    /**
     *
     * @param joinEdgeController
     * @param startPoint
     * @param endPoint
     */
    public UCJoinEdgeDrawer(LineModel joinEdgeController, Point startPoint, Point endPoint) {
        super(joinEdgeController, startPoint, endPoint);
    }

    /**
     * Method for drawing each type of line.
     *
     * @param g
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
        if (this.joinEdgeController instanceof UCJoinEdgeController) {
            UCJoinEdgeController UCjoin = (UCJoinEdgeController) this.joinEdgeController;
            Point arrowStartPoint = (UCjoin.getBreakPoints() != null && !UCjoin.getBreakPoints().isEmpty()) ? UCjoin.getBreakPoints().getLast() : this.startPoint;
            Point textStartPoint = (UCjoin.getBreakPoints() != null && !UCjoin.getBreakPoints().isEmpty()) ? UCjoin.getBreakPoints().getLeftMiddle() : this.startPoint;
            Point textEndPoint = (UCjoin.getBreakPoints() != null && !UCjoin.getBreakPoints().isEmpty() && UCjoin.getBreakPoints().size() > 1) ? UCjoin.getBreakPoints().getRightMiddle() : this.endPoint;
            if (UCjoin.getJoinEdgeType() == BT.UCLineType.ASSOCIATION) {
                g.setStroke(new BasicStroke(2));
                drawbreakedLine(g, this.startPoint, this.endPoint, UCjoin.getBreakPoints());
            } else if (UCjoin.getJoinEdgeType() == BT.UCLineType.INCLUDE) {
                g.setStroke(dashed);
                drawbreakedLine(g, this.startPoint, this.endPoint, UCjoin.getBreakPoints());
                g.setStroke(new BasicStroke(2));
                drawArrow(g, this.endPoint, arrowStartPoint);
                drawString(g, textEndPoint, textStartPoint, "<<include>>");
            } else if (UCjoin.getJoinEdgeType() == BT.UCLineType.EXTENDS) {
                g.setStroke(dashed);
                drawbreakedLine(g, this.startPoint, this.endPoint, UCjoin.getBreakPoints());
                drawArrow(g, this.endPoint, arrowStartPoint);
                g.setStroke(new BasicStroke(2));
                drawString(g, textEndPoint, textStartPoint, "<<extend>>");
            } else if (UCjoin.getJoinEdgeType() == BT.UCLineType.GENERALIZATION) {
                g.setStroke(new BasicStroke(2));
                drawbreakedLine(g, this.startPoint, this.endPoint, UCjoin.getBreakPoints());
                drawTriangle(g, this.endPoint, arrowStartPoint, Color.WHITE);
            }
        }
    }
}
