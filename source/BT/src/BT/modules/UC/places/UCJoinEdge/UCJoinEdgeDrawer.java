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
 * Class for drawing join edges of useCase diagram.
 * 
 * @author Karel Hala
 */
public class UCJoinEdgeDrawer extends JoinEdgeDrawer {

    /**
     * Basic constructor. It sets joinEdgeController, startPoint, endPoint based on JoinEdgeDrawer.
     * @param joinEdgeController explained in JoinEdgeDrawer.
     * @param startPoint explained in JoinEdgeDrawer.
     * @param endPoint explained in JoinEdgeDrawer.
     */
    public UCJoinEdgeDrawer(LineModel joinEdgeController, Point startPoint, Point endPoint) {
        super(joinEdgeController, startPoint, endPoint);
    }

    /**
     * Method for drawing each type of line.
     *
     * @param g Graphics2D for drawing join edges.
     */
    public void drawLine(Graphics2D g) {
        setBasicColors(g);
        float dash1[] = {10.0f};
        BasicStroke dashed
                = new BasicStroke(2.0f,
                        BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER,
                        10.0f, dash1, 0.0f);
        if (this.joinEdgeController instanceof UCJoinEdgeController) {
            UCJoinEdgeController UCjoin = (UCJoinEdgeController) this.joinEdgeController;
            Point arrowStartPoint = (UCjoin.getBreakPoints() != null && !UCjoin.getBreakPoints().isEmpty()) ? UCjoin.getBreakPoints().getLast() : this.startPoint;
            setStartEndPointsText(UCjoin);
            if (UCjoin.getJoinEdgeType() == BT.UCLineType.ASSOCIATION) {
                g.setStroke(new BasicStroke(2));
                drawbreakedLine(g, this.startPoint, this.endPoint, UCjoin.getBreakPoints());
            } else if (UCjoin.getJoinEdgeType() == BT.UCLineType.INCLUDE) {
                g.setStroke(dashed);
                drawbreakedLine(g, this.startPoint, this.endPoint, UCjoin.getBreakPoints());
                g.setStroke(new BasicStroke(2));
                drawArrow(g, this.endPoint, arrowStartPoint);
                drawString(g, "<<include>>");
            } else if (UCjoin.getJoinEdgeType() == BT.UCLineType.EXTENDS) {
                g.setStroke(dashed);
                drawbreakedLine(g, this.startPoint, this.endPoint, UCjoin.getBreakPoints());
                drawArrow(g, this.endPoint, arrowStartPoint);
                g.setStroke(new BasicStroke(2));
                drawString(g, "<<extend>>");
            } else if (UCjoin.getJoinEdgeType() == BT.UCLineType.GENERALIZATION) {
                g.setStroke(new BasicStroke(2));
                drawbreakedLine(g, this.startPoint, this.endPoint, UCjoin.getBreakPoints());
                drawTriangle(g, this.endPoint, arrowStartPoint, Color.WHITE);
            } else if (UCjoin.getJoinEdgeType() == BT.UCLineType.USERINPUT) {
                g.setStroke(dashed);
                drawbreakedLine(g, this.startPoint, this.endPoint, UCjoin.getBreakPoints());
                drawArrow(g, this.endPoint, arrowStartPoint);
                drawString(g, "please specify");
            }
        }
    }
}
