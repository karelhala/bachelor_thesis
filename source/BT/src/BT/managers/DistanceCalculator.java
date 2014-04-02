/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.managers;

import java.awt.Point;

/**
 *
 * @author Karel Hala
 */
public class DistanceCalculator extends DistanceCalculatorModel {

    /**
     * Method for calculating distance from point to line segment. First check
     * if point is withing line segment. And then calculate distance.
     *
     * @param DoublePoint firstPointSegment first point determining line segment
     * (point A)
     * @param secondPointSegment second point determining line segment (point B)
     * @param DoublePoint point coordinates of clicked point
     * @return double as distance from point to line segment
     * @return -1 if point is not within line segment
     */
    private double getDistancePointSegment(LineSegment line, DoublePoint point) {
        double LineLength = distanceFromTwoPoints(line.pointA, line.pointB);
        double t = ((point.x - line.pointA.x) * (line.pointB.x - line.pointA.x) + (point.y - line.pointA.y) * (line.pointB.y - line.pointA.y)) / LineLength;

        if (t < 0) {
            return -1;
        }
        if (t > 1) {
            return -1;
        }

        double distance = (Math.abs((line.pointB.x - line.pointA.x) * (line.pointA.y - point.y) - (line.pointA.x - point.x) * (line.pointB.y - line.pointA.y)) / Math.sqrt(LineLength));
        return distance;
    }

    /**
     * Method enveloping the calculation of distance from point to line segment
     *
     * @param x1
     * @param y1
     * @param x2 x coordinate of second point determining line segment
     * @param y2 y coordinate of second point determining line segment
     * @param pointX x coordinate of clicked point
     * @param pointY y coordinate of clicked point
     * @return double as distance of point to line segment
     */
    public double getDistanceOfPointToSegment(int x1, int y1, int x2, int y2, int pointX, int pointY) {
        LineSegment line = new LineSegment(x1, y1, x2, y2);
        DoublePoint clickedPoint = new DoublePoint((double) pointX, (double) pointY);

        double finalDistance;
        finalDistance = getDistancePointSegment(line, clickedPoint);
        return finalDistance;
    }

    /**
     *
     * @param pointA
     * @param pointB
     * @param width
     * @param height
     * @return double as distance of point to line segment
     */
    public Point getPointOfIntersectionLineSegments(Point pointA, Point pointB, int width, int height) {
        this.pointA = pointA;
        this.pointB = pointB;
        this.objectHeight = height;
        this.objectWidth = width;
        if (this.pointA != null && this.pointB != null)
        {
            if (this.pointA.x != this.pointB.x && pointA.y != pointB.y)
            {
                return calculatePoints();
            }
        }
        return null;
    }
    
    /**
     * 
     * @return 
     */
    private Point calculatePoints()
    {
        if (diagonalYforX(this.pointB, new Point(this.pointB.x+(objectWidth/2), this.pointB.y-(this.objectHeight/2)), this.pointA.x)<this.pointA.y)
        {
            if (diagonalYforX(this.pointB, new Point(this.pointB.x-this.objectWidth, this.pointB.y-this.objectHeight), this.pointA.x)<this.pointA.y)
            {
                //down
                return calculateBottomPoint();
            }
            else
            {
                //right
                return calculateRightPoint();
            }
        }
        else
        {
            if (diagonalYforX(this.pointB, new Point(this.pointB.x-objectWidth, this.pointB.y-this.objectHeight), this.pointA.x)<this.pointA.y)
            {
                //left
                return calculateLeftPoint();
            }
            else
            {
                //top
                return calculateTopPoint();
            }
        }
    }
    
    /**
     * 
     * @return 
     */
    private Point calculateRightPoint()
    {
        Point resultPoint = new Point();
        double distanceX = Math.abs(this.pointA.x-this.pointB.x);
        if (distanceX > 0)
        {
            double ratio = this.objectWidth/distanceX;
            double distanceY = ratio*Math.abs(this.pointA.y-this.pointB.y);
            if (this.pointA.y > this.pointB.y)
            {
                resultPoint.x = this.pointB.x + this.objectWidth/2;
                resultPoint.y = this.pointB.y + (int)distanceY/2;
            }
            else
            {
                resultPoint.x = this.pointB.x + objectWidth/2;
                resultPoint.y = this.pointB.y - (int)distanceY/2;
            }
        }
        return resultPoint;
    }
    
    /**
     * 
     * @return 
     */
    private Point calculateLeftPoint()
    {
        Point resultPoint = new Point();
        double distanceX = Math.abs(this.pointA.x-this.pointB.x);
        if (distanceX > 0)
        {
            double ratio = objectWidth/distanceX;
            double distanceY = ratio*Math.abs(this.pointA.y-this.pointB.y);
            if (this.pointA.y > this.pointB.y)
            {
                resultPoint.x = this.pointB.x - objectWidth/2;
                resultPoint.y = this.pointB.y + (int)distanceY/2;
            }
            else
            {
                resultPoint.x = this.pointB.x - objectWidth/2;
                resultPoint.y = this.pointB.y - (int)distanceY/2;
            }
        }
        return resultPoint;
    }
    
    /**
     * 
     * @return 
     */
    private Point calculateBottomPoint()
    {
        Point resultPoint = new Point();
        double distanceY = Math.abs(this.pointA.y-this.pointB.y);
        if (distanceY > 0)
        {
            double ratio = objectHeight/distanceY;
            double distanceX = ratio*Math.abs(this.pointA.x-this.pointB.x);
            if (this.pointA.x > this.pointB.x)
            {
                resultPoint.x = this.pointB.x + (int)distanceX/2;
                resultPoint.y = this.pointB.y + this.objectHeight/2;
            }
            else
            {
                resultPoint.x = this.pointB.x - (int)distanceX/2;
                resultPoint.y = this.pointB.y + this.objectHeight/2;
            }
        }
        return resultPoint;
    }
    
    /**
     * 
     * @return 
     */
    private Point calculateTopPoint()
    {
        Point resultPoint = new Point();
        double distanceY = Math.abs(this.pointA.y-this.pointB.y);
        if (distanceY > 0)
        {
            double ratio = this.objectHeight/distanceY;
            double distanceX = ratio*Math.abs(this.pointA.x-this.pointB.x);
            if (this.pointA.x > this.pointB.x)
            {
                resultPoint.x = this.pointB.x + (int)distanceX/2;
                resultPoint.y = this.pointB.y - this.objectHeight/2;
            }
            else
            {
                resultPoint.x = this.pointB.x - (int)distanceX/2;
                resultPoint.y = this.pointB.y - this.objectHeight/2;
            }
        }
        return resultPoint;
    }
    
    /**
     * 
     * @param pointA
     * @param pointB
     * @param x
     * @return 
     */
    private double diagonalYforX(Point pointA, Point pointB,  int x)
    {
        return (double)((pointA.y - pointB.y)) /(double) ((pointA.x - pointB.x))* ( (double)x - (double)pointA.x) + (double)pointA.y;
    }
    
    /**
     * 
     * @param pointA
     * @param pointB
     * @param y
     * @return 
     */
    private double diagonalXforY(Point pointA, Point pointB, int y)
    {
        return (y-(double)pointA.y)/((double)((pointA.y - pointB.y)) /(double) ((pointA.x - pointB.x))) + (double)pointA.x;
    }

}
