/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.managers;

import java.awt.Point;

/**
 * Model for storing variables and other functions used in Distance Calculator.
 *
 * @author Karel Hala
 */
public class DistanceCalculatorModel {

    /**
     * Point A, often used as start point of line segment.
     */
    protected Point pointA;
    /**
     * Point B, often used as end point of line segment.
     */
    protected Point pointB;
    /**
     * Height of object.
     */
    protected int objectHeight;
    /**
     * Width of object.
     */
    protected int objectWidth;

    /**
     * Method for sqaure of x.
     *
     * @param x
     * @return sqr as double
     */
    protected double sqr(double x) {
        return x * x;
    }

    /**
     * Method for calculating distance between 2 points.
     *
     * @param firstPoin point containing double x and double y
     * @param secondPoint point containing double x and double y
     * @return distance as double
     */
    protected double distanceFromTwoPoints(DoublePoint firstPoin, DoublePoint secondPoint) {
        return sqr(firstPoin.x - secondPoint.x) + sqr(firstPoin.y - secondPoint.y);
    }

    /**
     * Method for calculating magnitude of vector.
     *
     * @param A first point
     * @param B second point
     * @return DoublePoint magnitude of vector
     */
    protected DoublePoint vectorMagnitude(DoublePoint A, DoublePoint B) {
        return new DoublePoint(A.x - B.x, A.y - B.y);
    }

    /**
     * Dot product function used in vectors
     *
     * @param A first point
     * @param B second point
     * @return double calculaation of dot-product function
     */
    protected double dotProductFunction(DoublePoint A, DoublePoint B) {
        return A.x * B.x + A.y * B.y;
    }

    /**
     * Private class that contains x and y in double.
     */
    protected class DoublePoint {

        public double x;
        public double y;

        public DoublePoint() {
        }

        public DoublePoint(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * Private class that contains line segment points. These points are stored
     * as DoublePoints.
     */
    protected class LineSegment {

        public DoublePoint pointA;
        public DoublePoint pointB;

        public LineSegment() {
        }

        public LineSegment(int x1, int y1, int x2, int y2) {
            this(new DoublePoint(x1, y1), new DoublePoint(x2, y2));
        }

        public LineSegment(DoublePoint pointA, DoublePoint pointB) {
            this.pointA = pointA;
            this.pointB = pointB;
        }
    }
}
