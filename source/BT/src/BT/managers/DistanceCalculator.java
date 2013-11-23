/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.managers;

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
     * @param int x1 x coordinate of first point determining line segment
     * @param int y1 y coordinate of first point determining line segment
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
     * 
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param width1
     * @param height1
     * @param width2
     * @param height2
     * @return double as distance of point to line segment
     */
    public double getPointOfIntersectionLineSegments(int x1, int y1, int x2, int y2, int width1, int height1, int width2, int height2)
    {
        LineSegment lineA = new LineSegment(x1-width1/2, y1+height1, x1+width1/2, y1+height1);
        LineSegment lineB = new LineSegment(x1+width1/2, y1+height1, x1+width1/2, y1-height1);
        LineSegment lineC = new LineSegment(x1+width1/2, y1-height1, x1-width1/2, y1-height1);
        LineSegment lineD = new LineSegment(x1-width1/2, y1-height1, x1-width1/2, y1+height1);
        return -1;
    }
    
}

