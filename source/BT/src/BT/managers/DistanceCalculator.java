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
public class DistanceCalculator extends DistanceCalculatorModel{
    /**
     * Method for calculating distance from point to line segment. 
     * First check if point is withing line segment. And then calculate distance.
     * @param DoublePoint firstPointSegment first point determining line segment (point A)
     * @param secondPointSegment second point determining line segment (point B)
     * @param DoublePoint point coordinates of clicked point
     * @return double as distance from point to line segment
     * @return -1 if point is not within line segment
     */
    private double getDistancePointSegment(LineSegment line, DoublePoint point)
    {
        double LineLength = distanceFromTwoPoints(line.pointA, line.pointB);
        double t = ((point.x - line.pointA.x) * (line.pointB.x - line.pointA.x) + (point.y - line.pointA.y) * (line.pointB.y - line.pointA.y)) / LineLength;
        
        if (t < 0)
        {
            return -1;
        }
        if (t > 1) 
        {
            return -1;
        }
        
        double distance = (Math.abs((line.pointB.x-line.pointA.x)*(line.pointA.y-point.y) - (line.pointA.x-point.x)*(line.pointB.y-line.pointA.y))/Math.sqrt(LineLength));
        return distance;
    }
    
    /**
     * Method enveloping the calculation of distance from point to line segment
     * @param x1
     * @param y1
     * @param x2 x coordinate of second point determining line segment
     * @param y2 y coordinate of second point determining line segment
     * @param pointX x coordinate of clicked point
     * @param pointY y coordinate of clicked point
     * @return double as distance of point to line segment
     */
    public double getDistanceOfPointToSegment(int x1, int y1, int x2, int y2, int pointX, int pointY)
    {
        LineSegment line = new LineSegment(x1, y1, x2, y2);
        DoublePoint clickedPoint = new DoublePoint((double) pointX, (double) pointY);

        double finalDistance;
        finalDistance = getDistancePointSegment(line, clickedPoint);
        return finalDistance;
    }
    
    /**
     * Method for calculating intersect point to object with vertical lines.
     * Vertical lines in object needs to be calculated differently from hotizontal.
     * @param lineA
     * @param lineB
     * @return DoublePoint calculated point if success
     */
    public DoublePoint getIntersectionPointLineVert(LineSegment lineA, LineSegment lineB)
    {
        DoublePoint s1 = vectorMagnitude(lineA.pointB, lineA.pointA);
        DoublePoint s2 = vectorMagnitude(lineB.pointB, lineB.pointA);
        double underline = -s2.x*s1.y+s1.x*s2.y;
        double s = (-s1.y*(lineA.pointA.x-lineB.pointA.x) + s1.x*(lineA.pointA.y-lineB.pointA.y))/underline;
        double t = (s2.x*(lineA.pointA.y-lineB.pointA.y)-s2.y*(lineA.pointA.x-lineB.pointA.x))/underline;
        
        if (s>=0 && s<=1 &&t>=0&& t<=1)
        {
            return new DoublePoint(lineA.pointA.x+(t*s1.x), lineA.pointA.y+(t*s1.y));
        }
        return null;
    }
    
    /**
     * Method for calculating intersect point to object with hrtizontal lines.
     * Horizontal lines in object needs to be calculated differently from vertical.
     * @param lineA
     * @param lineB
     * @return DoublePoint calculated point if success
     */
    public DoublePoint getIntersectionPointLineHoriz(LineSegment lineA, LineSegment lineB)
    {
        DoublePoint s1 = vectorMagnitude(lineA.pointB, lineA.pointA);
        DoublePoint s2 = vectorMagnitude(lineB.pointB, lineB.pointA);
        double underline = s1.x*s2.y-s2.x*s1.y;
        double s = (s1.x*(lineA.pointA.y-lineB.pointA.y)-s1.y*(lineA.pointA.x-lineB.pointA.x))/underline;
        double t = (s2.x*(lineA.pointA.y-lineB.pointA.y)-s2.y*(lineA.pointA.x-lineB.pointA.x))/underline;
        
        if (s>=0 && s<=1 &&t>=0&& t<=1)
        {
            return new DoublePoint(lineA.pointA.x+(t*s1.x), lineA.pointA.y+(t*s1.y));
        }
        return null;
    }
    
    /**
     * 
     * @param pointA
     * @param pointB
     * @param width
     * @param height
     * @return double as distance of point to line segment
     */
    public Point getPointOfIntersectionLineSegments(Point pointA, Point pointB, int width ,int height)
    {
        LineSegment segmentLine;
        if (pointA != null && pointB != null)
        {
            segmentLine = new LineSegment(pointA.x, pointA.y, pointB.x, pointB.y);
        }
        else
        {
            return null;
        }
        
        LineSegment lineA = new LineSegment(pointB.x-width/2, pointB.y+height/2, pointB.x+width/2, pointB.y+height/2);
        LineSegment lineB = new LineSegment(pointB.x+width/2, pointB.y-height/2, pointB.x+width/2, pointB.y+height/2);
        LineSegment lineC = new LineSegment(pointB.x+width/2, pointB.y-height/2, pointB.x-width/2, pointB.y-height/2);
        LineSegment lineD = new LineSegment(pointB.x-width/2, pointB.y-height/2, pointB.x-width/2, pointB.y+height/2);
        DoublePoint intesectionPoint;

        if ((intesectionPoint = getIntersectionPointLineVert(lineB, segmentLine))!=null)
        {
            return new Point((int) intesectionPoint.x, (int) intesectionPoint.y);
        }
        else if ((intesectionPoint = getIntersectionPointLineVert(lineD, segmentLine))!=null)
        {
            return new Point((int) intesectionPoint.x, (int) intesectionPoint.y);
        }
        else if ((intesectionPoint = getIntersectionPointLineHoriz(lineA, segmentLine))!=null)
        {
            return new Point((int) intesectionPoint.x, (int) intesectionPoint.y);
        }
        else if ((intesectionPoint = getIntersectionPointLineHoriz(lineC, segmentLine))!=null)
        {
            return new Point((int) intesectionPoint.x, (int) intesectionPoint.y);
        }
        
        return null;
    }
    
}

