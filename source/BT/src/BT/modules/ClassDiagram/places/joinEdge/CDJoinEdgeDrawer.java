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
            if (cdJoin.getJoinEdgeType() == BT.CDLineType.ASSOCIATION)
            {
                g.drawLine(this.startPoint.x, this.startPoint.y, this.endPoint.x, this.endPoint.y);
            }
            else if (cdJoin.getJoinEdgeType() == BT.CDLineType.AGGREGATION)
            {
                g.drawLine(this.startPoint.x, this.startPoint.y, this.endPoint.x, this.endPoint.y);
                drawArrow(g, this.endPoint, this.startPoint);
                drawString(g, this.endPoint, this.startPoint, "<<aggregation>>");
            }
            else if (cdJoin.getJoinEdgeType() == BT.CDLineType.COMPOSITION)
            {
                g.setStroke(dashed);
                g.drawLine(this.startPoint.x, this.startPoint.y, this.endPoint.x, this.endPoint.y);
                drawArrow(g, this.endPoint, this.startPoint);
                drawString(g, this.endPoint, this.startPoint, "<<composition>>");
            }
            else if (cdJoin.getJoinEdgeType() == BT.CDLineType.GENERALIZATION)
            {
                g.setStroke(dashed);
                g.drawLine(this.startPoint.x, this.startPoint.y, this.endPoint.x, this.endPoint.y);
                drawArrow(g, this.endPoint, this.startPoint);
                drawString(g, this.endPoint, this.startPoint, "<<generalization>>");
            }
            else if (cdJoin.getJoinEdgeType() == BT.CDLineType.REALIZATION)
            {
                g.setStroke(dashed);
                g.drawLine(this.startPoint.x, this.startPoint.y, this.endPoint.x, this.endPoint.y);
                drawArrow(g, this.endPoint, this.startPoint);
                drawString(g, this.endPoint, this.startPoint, "<<realization>>");
            }
            
        }
    }
    
}
