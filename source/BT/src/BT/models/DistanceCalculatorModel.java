/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.models;

/**
 *
 * @author Karel Hala
 */
public class DistanceCalculatorModel {
    /**
     * Method for sqaure of x.
     * @param double x number to be squared 
     * @return sqr as double
     */
    protected double sqr(double x) { 
        return x * x;
    }
    
        /**
     * Method for calculating distance between 2 points.
     * @param DoublePoint firstPoin point containing double x and double y
     * @param DoublePoint secondPoint point containing double x and double y
     * @return distance as double
     */
    protected double distanceFromTwoPoints(DoublePoint firstPoin, DoublePoint secondPoint)
    {
        return sqr(firstPoin.x - secondPoint.x) + sqr(firstPoin.y - secondPoint.y);
    }
    
        /**
     * Private class that contains x and y in double.
     */
    protected class DoublePoint{
        public double x;
        public double y;
        
        public DoublePoint()
        {
            
        }
        
        public DoublePoint(double x, double y)
        {
            this.x = x;
            this.y = y;
        }
    }
    
    protected class LineSegment
    {
        public DoublePoint pointA;
        public DoublePoint pointB;
        
        public LineSegment()
        {
            
        }
        
        public LineSegment(int x1, int y1, int x2, int y2)
        {
            this(new DoublePoint(x1, y1), new DoublePoint(x2, y2));
        }
        
        public LineSegment(DoublePoint pointA, DoublePoint pointB)
        {
            this.pointA = pointA;
            this.pointB = pointB;
        }
    }
}
