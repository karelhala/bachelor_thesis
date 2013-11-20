/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.managers.UC;

/**
 *
 * @author Karel Hala
 */
public class DistanceCalculator {
    /**
     * 
     * @param x
     * @return 
     */
    private double sqr(double x) { 
        return x * x;
    }
    
    /**
     * 
     * @param firstPoin
     * @param secondPoint
     * @return 
     */
    private double distanceFromTwoPoints(DoublePoint firstPoin, DoublePoint secondPoint)
    {
        return sqr(firstPoin.x - secondPoint.x) + sqr(firstPoin.y - secondPoint.y);
    }
    
    /**
     * 
     * @param firstPointSegment
     * @param secondPointSegment
     * @param point
     * @return 
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
     * 
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param pointX
     * @param pointY
     * @return 
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
     * Private class that contains x and y in double.
     */
    private class DoublePoint{
        public double x;
        public double y;
        
        public DoublePoint(double x, double y)
        {
            this.x = x;
            this.y = y;
        }
    }
}
