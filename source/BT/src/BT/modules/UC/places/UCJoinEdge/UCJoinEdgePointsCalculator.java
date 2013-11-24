/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.UC.places.UCJoinEdge;

import BT.managers.DistanceCalculator;
import BT.models.CoordinateModel;
import BT.modules.UC.places.UCActor;
import java.awt.Point;

/**
 *
 * @author Karel Hala
 */
public class UCJoinEdgePointsCalculator {
    private UCJoinEdgeController joinEdgeController;
    private DistanceCalculator distanceCalculator;
    private Point startPoint;
    private Point endPoint;

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }
    
    public UCJoinEdgePointsCalculator(UCJoinEdgeController joinEdgeController, Point startPoint, Point endPoint)
    {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.joinEdgeController = joinEdgeController;
        this.distanceCalculator = new DistanceCalculator();
        calculatePoints();
    }

    
    private void calculatePoints() {
        if (this.joinEdgeController.getSecondObject() != null)
        {
            this.endPoint.x = this.joinEdgeController.getSecondObject().getX();
            this.endPoint.y = this.joinEdgeController.getSecondObject().getY();
        }
        this.startPoint.x = this.joinEdgeController.getfirstObject().getX();
        this.startPoint.y = this.joinEdgeController.getfirstObject().getY();
        
        this.endPoint = calculateEndPoint();
        
        this.startPoint = calculateStartPoint();
        
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
