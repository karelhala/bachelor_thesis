/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.UC.places.UCJoinEdge;

import BT.BT;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

/**
 *
 * @author Karel Hala
 */
public class UCJoinEdgeDrawer {
    UCJoinEdgeController joinEdgeController;
    Point startPoint;
    Point endPoint;

    public UCJoinEdgeDrawer(UCJoinEdgeController joinEdgeController, Point startPoint, Point endPoint) {
        this.joinEdgeController = joinEdgeController;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
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
        
        if (this.joinEdgeController.getJoinEdgeType() == BT.UCLineType.ASSOCIATION)
        {
            g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
        }
        else if (this.joinEdgeController.getJoinEdgeType() == BT.UCLineType.USES)
        {
            g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
            drawArrow(g, this.endPoint, this.startPoint);
            drawString(g, this.endPoint, this.startPoint, "<<uses>>");
        }
        else if (this.joinEdgeController.getJoinEdgeType() == BT.UCLineType.IMPLEMENTS)
        {
            g.setStroke(dashed);
            g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
            drawArrow(g, this.endPoint, this.startPoint);
            drawString(g, this.endPoint, this.startPoint, "<<implements>>");
        }
    }
    
    /**
     * Method for drawing arrow at end of the line given by 2 points.
     * @param g2D
     * @param A
     * @param B 111111111111111111111111
     */
    private void drawArrow(Graphics2D g2D, Point A, Point B)
    {
        Graphics2D g = (Graphics2D) g2D.create();
        g.setStroke(new BasicStroke(2));
        double dx = B.x - A.x;
        double dy = B.y - A.y;
        double angle = Math.atan2(dy, dx);
        AffineTransform at = AffineTransform.getTranslateInstance(A.x, A.y);
        at.concatenate(AffineTransform.getRotateInstance(angle));
        g.transform(at);

        g.drawLine(0, 0, 0+5, 0+5);
        g.drawLine(0, 0, 0+5, 0-5);
    }
    
    private void drawString(Graphics2D g2D, Point A, Point B, String name)
    {
        Graphics2D g = (Graphics2D) g2D.create();
        g.setStroke(new BasicStroke(2));
        double dx = B.x - A.x;
        double dy = B.y - A.y;
        double angle = Math.atan2(dy, dx);
        int len = (int) Math.sqrt(dx*dx + dy*dy);
        if ((angle<=Math.PI && angle>=Math.PI/2) || (angle>=-Math.PI && angle<=-Math.PI/2))
        {
            len = -len*2;
            angle = angle-Math.PI;
        }
        AffineTransform at = AffineTransform.getTranslateInstance(A.x, A.y);
        at.concatenate(AffineTransform.getRotateInstance(angle));
        g.transform(at);

        g.drawString(name, len/3, -5);
    }
    
}
