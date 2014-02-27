/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.UC.places.UCJoinEdge;

import BT.BT;
import BT.managers.JoinEdgeDrawer;
import BT.models.LineModel;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 *
 * @author Karel Hala
 */
public class UCJoinEdgeDrawer extends JoinEdgeDrawer{
    
    /**
     * 
     * @param joinEdgeController
     * @param startPoint
     * @param endPoint 
     */
    public UCJoinEdgeDrawer(LineModel joinEdgeController, Point startPoint, Point endPoint) 
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
        if (this.joinEdgeController instanceof UCJoinEdgeController)
        {
            UCJoinEdgeController UCjoin = (UCJoinEdgeController) this.joinEdgeController;
            if (UCjoin.getJoinEdgeType() == BT.UCLineType.ASSOCIATION)
            {
                g.drawLine(this.startPoint.x, this.startPoint.y, this.endPoint.x, this.endPoint.y);
            }
            else if (UCjoin.getJoinEdgeType() == BT.UCLineType.INCLUDE)
            {
                g.drawLine(this.startPoint.x, this.startPoint.y, this.endPoint.x, this.endPoint.y);
                drawArrow(g, this.endPoint, this.startPoint);
                drawString(g, this.endPoint, this.startPoint, "<<include>>");
            }
            else if (UCjoin.getJoinEdgeType() == BT.UCLineType.EXTENDS)
            {
                g.setStroke(dashed);
                g.drawLine(this.startPoint.x, this.startPoint.y, this.endPoint.x, this.endPoint.y);
                drawArrow(g, this.endPoint, this.startPoint);
                drawString(g, this.endPoint, this.startPoint, "<<extend>>");
            }
        }
    }
}
