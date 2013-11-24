/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.UC.places.UCJoinEdge;

import BT.BT;
import BT.managers.DistanceCalculator;
import BT.models.CoordinateModel;
import BT.modules.UC.places.UCActor;
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
        
        this.endPoint = calculateEndPoint();
        
        this.startPoint = calculateStartPoint();
        
        if (this.startPoint !=null && this.endPoint !=null)
        {
            g.setStroke(new BasicStroke(2));    
            drawLine(g);
        }
    }
    
    /**
     * Method for drawing each type of line.
     * @param g 
     */
    private void drawLine(Graphics2D g)
    {
        if (this.joinEdgeController.getJoinEdgeType() == BT.UCLineType.ASSOCIATION)
        {
            g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
        }
        else if (this.joinEdgeController.getJoinEdgeType() == BT.UCLineType.USES)
        {
            g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
            drawArrow(g, this.endPoint, this.startPoint);
        }
        else if (this.joinEdgeController.getJoinEdgeType() == BT.UCLineType.EXTENDS)
        {
            g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
            drawArrow(g, this.endPoint, this.startPoint);
            drawArrow(g, this.startPoint, this.endPoint);   
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
        WidthHeight widthHeight = getObjectWidthAndheight(this.joinEdgeController.getfirstObject());
        calculatedPoint = this.distanceCalculator.getPointOfIntersectionLineSegments(pointA, pointB, widthHeight.width, widthHeight.height);
        if (calculatedPoint !=null)
        {
            return calculatedPoint;
        }
        return this.startPoint;
    }
    
    /**
     * 
     * @param object
     * @return 
     */
    private WidthHeight getObjectWidthAndheight(CoordinateModel object)
    {
        int width;
        int height;
        if (object instanceof UCActor)
        {
            UCActor actor = (UCActor) object;
            width = actor.getMaxWidth();
            height = actor.getMaxHeight();
        }
        else
        {
            width = object.getWidth();
            height = object.getHeight();
        }
        return new WidthHeight(width, height);
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
            WidthHeight widthHeight = getObjectWidthAndheight(this.joinEdgeController.getSecondObject());
            return this.distanceCalculator.getPointOfIntersectionLineSegments(pointA, pointB, widthHeight.width, widthHeight.height);
        }
        return this.endPoint;
    }
    
    /**
     * Method for drawing arrow at end of the line given by 2 points.
     * @param g2D
     * @param A
     * @param B 
     */
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
    
    /**
     * 
     */
    private class WidthHeight{
        public int width;
        public int height;
        
        public WidthHeight()
        {
            
        }
        
        public WidthHeight(int width, int height)
        {
            this.width = width;
            this.height = height;
        }
    }
}
