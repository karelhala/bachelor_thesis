/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.UC.places.UCJoinEdge;

import BT.BT;
import BT.managers.DistanceCalculator;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

/**
 *
 * @author Karel Hala
 */
public class UCJoinEdgeLineDrawer {
    private UCJoinEdgeController joinEdgeController;
    private DistanceCalculator distanceCalculator;
    private Point startPoint;
    private Point endPoint;
    
    public UCJoinEdgeLineDrawer(UCJoinEdgeController joinEdgeController, Point startPoint, Point endPoint)
    {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.joinEdgeController = joinEdgeController;
        this.distanceCalculator = new DistanceCalculator();
    }
    /**
     * 
     * @param g 
     */
    public void drawJoinEdge(Graphics2D g) {
        if (this.joinEdgeController.getSelected())
        {
            g.setColor(this.joinEdgeController.getSelectedColor());
        }
        else
        {
            g.setColor(this.joinEdgeController.getColor());
        }
        if (this.joinEdgeController.getSecondObject() != null)
        {
            this.endPoint.x = this.joinEdgeController.getSecondObject().getX();
            this.endPoint.y = this.joinEdgeController.getSecondObject().getY();
        }
        this.startPoint.x = this.joinEdgeController.getfirstObject().getX();
        this.startPoint.y = this.joinEdgeController.getfirstObject().getY();
        
        g.setStroke(new BasicStroke(2));
        this.endPoint = calculateEndPoint();
        
        this.startPoint = calculateStartPoint();
        
        drawArrow(g, this.endPoint, this.startPoint);    
        
        if (this.joinEdgeController.getJoinEdgeType() == BT.UCLineType.ASSOCIATION)
        {
            g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
        }
    }
    
    /**
     * 
     * @return 
     */
    private Point calculateStartPoint()
    {
        Point pointA = this.endPoint;
        Point pointB = this.startPoint;
        Point calculatedPoint;
        calculatedPoint = this.distanceCalculator.getPointOfIntersectionLineSegments(pointA, pointB, this.joinEdgeController.getfirstObject().getWidth(), this.joinEdgeController.getfirstObject().getHeight());
        if (calculatedPoint !=null)
        {
            return calculatedPoint;
        }
        return this.startPoint;
    }
    
    /**
     * 
     * @return 
     */
    private Point calculateEndPoint()
    {
        Point pointA = this.startPoint;
        Point pointB = this.endPoint;
        if (this.joinEdgeController.getSecondObject() != null)
        {
            return this.distanceCalculator.getPointOfIntersectionLineSegments(pointA, pointB, this.joinEdgeController.getSecondObject().getWidth(), this.joinEdgeController.getSecondObject().getHeight());
        }
        return this.endPoint;
    }
    
    private void drawArrow(Graphics2D g2D, Point A, Point B)
    {
        Graphics2D g = (Graphics2D) g2D.create();
        double dx = B.x - A.x;
        double dy = B.y - A.y;
        double angle = Math.atan2(dy, dx);
        AffineTransform at = AffineTransform.getTranslateInstance(A.x, A.y);
        at.concatenate(AffineTransform.getRotateInstance(angle));
        g.transform(at);

        g.drawLine(0, 0, 0+5, 0+5);
        g.drawLine(0, 0, 0+5, 0-5);
    }
}
