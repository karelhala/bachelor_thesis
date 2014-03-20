/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BT.modules.ClassDiagram.places.joinEdge;

import BT.BT;
import BT.managers.JoinEdgeDrawer;
import BT.models.LineModel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 *
 * @author Karel
 */
public class CDJoinEdgeDrawer extends JoinEdgeDrawer{

    public CDJoinEdgeDrawer(LineModel joinEdgeController, Point startPoint, Point endPoint) 
    {
        super(joinEdgeController, startPoint, endPoint);
    }
    
    /**
     * Method for drawing each type of line.
     * @param g 
     */
    public void drawLine(Graphics2D g)
    {
        if (this.joinEdgeController.getSelected())
        {
            g.setColor(this.joinEdgeController.getSelectedColor());
        }
        else
        {
            g.setColor(this.joinEdgeController.getColor());
        }
        
        float dash1[] = {10.0f};
        BasicStroke dashed =
        new BasicStroke(2.0f,
                        BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER,
                        10.0f, dash1, 0.0f);
        if (this.joinEdgeController instanceof CDJoinEdgeController)
        {
            CDJoinEdgeController cdJoin = (CDJoinEdgeController) this.joinEdgeController;
            Point arrowStartPoint = (cdJoin.getBreakPoints() != null && !cdJoin.getBreakPoints().isEmpty())?cdJoin.getBreakPoints().getLast():this.startPoint;
            Point textStartPoint = (cdJoin.getBreakPoints() != null && !cdJoin.getBreakPoints().isEmpty())?cdJoin.getBreakPoints().getLeftMiddle():this.startPoint;
            Point textEndPoint = (cdJoin.getBreakPoints() != null && !cdJoin.getBreakPoints().isEmpty() && cdJoin.getBreakPoints().size()>1)?cdJoin.getBreakPoints().getRightMiddle():this.endPoint;
            if (cdJoin.getJoinEdgeType() == BT.CDLineType.ASSOCIATION)
            {
                drawbreakedLine(g, this.startPoint, this.endPoint, cdJoin.getBreakPoints());
                drawArrow(g, endPoint, arrowStartPoint);
            }
            else if (cdJoin.getJoinEdgeType() == BT.CDLineType.AGGREGATION)
            {
                drawbreakedLine(g, this.startPoint, this.endPoint, cdJoin.getBreakPoints());
                drawDiamond(g, this.endPoint, arrowStartPoint, Color.WHITE);
                drawString(g, textEndPoint, textStartPoint, "<<aggregation>>");
            }
            else if (cdJoin.getJoinEdgeType() == BT.CDLineType.COMPOSITION)
            {
                drawbreakedLine(g, this.startPoint, this.endPoint, cdJoin.getBreakPoints());
                drawDiamond(g, this.endPoint, arrowStartPoint, Color.BLACK);
                drawString(g, textEndPoint, textStartPoint, "<<composition>>");
            }
            else if (cdJoin.getJoinEdgeType() == BT.CDLineType.GENERALIZATION)
            {
                drawbreakedLine(g, this.startPoint, this.endPoint, cdJoin.getBreakPoints());
                drawTriangle(g, this.endPoint, arrowStartPoint, Color.WHITE);
                drawString(g, textEndPoint, textStartPoint, "<<generalization>>");
            }
            else if (cdJoin.getJoinEdgeType() == BT.CDLineType.REALIZATION)
            {
                drawbreakedLine(g, this.startPoint, this.endPoint, cdJoin.getBreakPoints());
                drawArrow(g, this.endPoint, arrowStartPoint);
                drawString(g, textEndPoint, textStartPoint, "<<realization>>");
            }            
        }
    }
    
}
