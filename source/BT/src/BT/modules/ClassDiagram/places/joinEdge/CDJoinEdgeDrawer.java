/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ClassDiagram.places.joinEdge;

import BT.BT;
import BT.BT.ClassType;
import BT.managers.JoinEdgeDrawer;
import BT.models.LineModel;
import BT.modules.ClassDiagram.places.CDClass;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 * Class for drawing join egdes between classes in class diagram.
 * 
 * @author Karel Hala
 */
public class CDJoinEdgeDrawer extends JoinEdgeDrawer {

    /**
     * Basic constructor.
     * Sets joinEdgeController, startPoint, endPoint.
     * @param joinEdgeController controller for join edges.
     * @param startPoint of join edge.
     * @param endPoint of joind edge.
     */
    public CDJoinEdgeDrawer(LineModel joinEdgeController, Point startPoint, Point endPoint) {
        super(joinEdgeController, startPoint, endPoint);
    }

    /**
     * Method for drawing each type of line.
     * First create dashed stroke.
     * Calculate arrow start point based on breakPoints in line.
     * Set start and end points of line.
     * Draw each line.
     *
     * @param g Graphics2D.
     */
    public void drawLine(Graphics2D g) {
        if (this.joinEdgeController.getSecondObject() == null)
        {
            setBasicColors(g, false);
        }
        else
        {
            setBasicColors(g);
        }

        float dash1[] = {10.0f};
        BasicStroke dashed
                = new BasicStroke(2.0f,
                        BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER,
                        10.0f, dash1, 0.0f);
        if (this.joinEdgeController instanceof CDJoinEdgeController) {
            CDJoinEdgeController cdJoin = (CDJoinEdgeController) this.joinEdgeController;
            Point arrowStartPoint = (cdJoin.getBreakPoints() != null && !cdJoin.getBreakPoints().isEmpty()) ? cdJoin.getBreakPoints().getLast() : this.startPoint;
            setStartEndPointsText(cdJoin);
            if (cdJoin.getJoinEdgeType() == BT.CDLineType.ASSOCIATION) {
                g.setStroke(new BasicStroke(2));
                drawbreakedLine(g, this.startPoint, this.endPoint, cdJoin.getBreakPoints());
                drawArrow(g, endPoint, arrowStartPoint);
            } else if (cdJoin.getJoinEdgeType() == BT.CDLineType.AGGREGATION) {
                g.setStroke(new BasicStroke(2));
                drawbreakedLine(g, this.startPoint, this.endPoint, cdJoin.getBreakPoints());
                drawDiamond(g, this.endPoint, arrowStartPoint, Color.WHITE);
                drawString(g, "aggregation");
            } else if (cdJoin.getJoinEdgeType() == BT.CDLineType.COMPOSITION) {
                g.setStroke(new BasicStroke(2));
                drawbreakedLine(g, this.startPoint, this.endPoint, cdJoin.getBreakPoints());
                drawDiamond(g, this.endPoint, arrowStartPoint, Color.BLACK);
                drawString(g, "composition");
            } else if (cdJoin.getJoinEdgeType() == BT.CDLineType.GENERALIZATION) {
                g.setStroke(new BasicStroke(2));
                drawbreakedLine(g, this.startPoint, this.endPoint, cdJoin.getBreakPoints());
                drawTriangle(g, this.endPoint, arrowStartPoint, Color.WHITE);
                drawString(g, "generalization");
            } else if (cdJoin.getJoinEdgeType() == BT.CDLineType.REALIZATION) {
                g.setStroke(dashed);
                drawbreakedLine(g, this.startPoint, this.endPoint, cdJoin.getBreakPoints());
                drawArrow(g, this.endPoint, arrowStartPoint);
                drawString(g, "realization");
            } else if (cdJoin.getJoinEdgeType() == BT.CDLineType.USERINPUT) {
                g.setStroke(dashed);
                drawbreakedLine(g, this.startPoint, this.endPoint, cdJoin.getBreakPoints());
                drawArrow(g, this.endPoint, arrowStartPoint);
                drawString(g, "please specify");
            }
        }
    }
    
    /**
     * Method for checking type of class.
     * @param cdJoin LineModel of join.
     */ 
    private boolean checkClassTypes(LineModel cdJoin)
    {
        if (cdJoin.getFirstObject() != null && cdJoin.getSecondObject() != null)
        {
            return ((CDClass)cdJoin.getFirstObject()).getTypeOfClass() != ClassType.NONE &&  ((CDClass)cdJoin.getSecondObject()).getTypeOfClass() != ClassType.NONE;
        }
        return false;
    }

}
