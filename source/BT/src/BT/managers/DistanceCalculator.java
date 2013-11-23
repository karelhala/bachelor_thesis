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
    private double getDistancePointSegment(DoublePoint firstPointSegment, DoublePoint secondPointSegment, DoublePoint point)
    {
        double LineLength = distanceFromTwoPoints(firstPointSegment, secondPointSegment);
        double t = ((point.x - firstPointSegment.x) * (secondPointSegment.x - firstPointSegment.x) + (point.y - firstPointSegment.y) * (secondPointSegment.y - firstPointSegment.y)) / LineLength;
        
        if (t < 0)
        {
            return -1;
        }
        if (t > 1) 
        {
            return -1;
        }
        
        double distance = (Math.abs((secondPointSegment.x-firstPointSegment.x)*(firstPointSegment.y-point.y) - (firstPointSegment.x-point.x)*(secondPointSegment.y-firstPointSegment.y))/Math.sqrt(LineLength));
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
        DoublePoint firstLineA = new DoublePoint((double) x1, (double) y1);
        DoublePoint firstLineB = new DoublePoint((double) x2, (double) y2);
        DoublePoint clickedPoint = new DoublePoint((double) pointX, (double) pointY);

        double finalDistance;
        finalDistance = getDistancePointSegment(firstLineA, firstLineB, clickedPoint);
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

