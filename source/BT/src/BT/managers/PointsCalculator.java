/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.managers;

import BT.models.CoordinateModel;
import BT.models.MyArrayList;
import BT.modules.UC.places.UCActor;
import java.awt.Point;

/**
 * Class that will calculate star and end points for given object. This class
 * will calculate start and end point by given objects, just pass 2 objects in
 * it and it ethod will handle everzthing else. To get calculated points call
 * for getStartPoint and getEndPoint
 *
 * @author Karel Hala
 */
public class PointsCalculator {

    /**
     * First coordinateModel.
     */
    private CoordinateModel firstObject;
    /**
     * Second coordinateModel.
     */
    private CoordinateModel secondObject;
    /**
     * Distance calculator, for calculating intersection point or distance of
     * point to line.
     */
    private DistanceCalculator distanceCalculator;
    /**
     * Start point of line.
     */
    private Point startPoint;
    /**
     * End point of line.
     */
    private Point endPoint;
    /**
     * Break points of line.
     */
    private MyArrayList<Point> breakPoints;

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    /**
     * Basic constructor. Every part of class will be set even breakPoints of
     * line.
     *
     * @param firstObject coordinate Model.
     * @param secondObject coordinate Model.
     * @param startPoint start point of line.
     * @param endPoint end point of line.
     * @param breakPoints break point of line.
     */
    public PointsCalculator(CoordinateModel firstObject, CoordinateModel secondObject, Point startPoint, Point endPoint, MyArrayList<Point> breakPoints) {
        this.firstObject = firstObject;
        this.secondObject = secondObject;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.distanceCalculator = new DistanceCalculator();
        this.breakPoints = breakPoints;
        calculatePoints();
    }

    /**
     * Basic constructor. Every part of class will be set except breakPoints.
     *
     * @param firstObject coordinate Model.
     * @param secondObject coordinate Model.
     * @param startPoint start point of line.
     * @param endPoint end point of line
     */
    public PointsCalculator(CoordinateModel firstObject, CoordinateModel secondObject, Point startPoint, Point endPoint) {
        this(firstObject, secondObject, startPoint, endPoint, null);
    }

    /**
     * Method wrapping calculating start and end point.
     */
    private void calculatePoints() {
        this.endPoint = calculateEndPoint();

        this.startPoint = calculateStartPoint();

    }

    /**
     * Method for calculating start point of line. First check if first object
     * is set.
     *
     * @return start point of line.
     */
    private Point calculateStartPoint() {
        Point pointA = (this.breakPoints != null && !this.breakPoints.isEmpty()) ? this.breakPoints.getFirst() : this.endPoint;
        Point pointB = this.startPoint;
        Point calculatedPoint;
        WidthHeight widthHeight = getObjectWidthAndheight(this.firstObject);
        if (widthHeight != null) {
            calculatedPoint = this.distanceCalculator.getPointOfIntersectionLineSegments(pointA, pointB, widthHeight.width, widthHeight.height);
            if (calculatedPoint != null) {
                return calculatedPoint;
            }
        }
        return this.startPoint;
    }

    /**
     * Method for setting and calculating width and height of object. Result
     * will be stored in WidthHeight private class.
     *
     * @param object object for whitch this will be calculated.
     * @return WidthHeight object.
     */
    private WidthHeight getObjectWidthAndheight(CoordinateModel object) {
        int width;
        int height;
        if (object != null) {
            if (object instanceof UCActor) {
                UCActor actor = (UCActor) object;
                width = actor.getMaxWidth();
                height = actor.getMaxHeight();
            } else {
                width = object.getWidth();
                height = object.getHeight();
            }
            return new WidthHeight(width, height);
        }
        return null;
    }

    /**
     * Method for calculating end point of object. It will check if second
     * object is not null and by this it will calculate it.
     *
     * @return Point of actual intersection.
     */
    private Point calculateEndPoint() {
        if (this.secondObject != null) {
            Point pointA;
            Point pointB;
            WidthHeight widthHeight = getObjectWidthAndheight(this.secondObject);
            pointB = this.endPoint;
            pointA = (this.breakPoints != null && !this.breakPoints.isEmpty()) ? this.breakPoints.getLast() : this.startPoint;
            return this.distanceCalculator.getPointOfIntersectionLineSegments(pointA, pointB, widthHeight.width, widthHeight.height);
        }
        return this.endPoint;
    }

    /**
     * Private class that sootres width and height of model.
     */
    private class WidthHeight {

        /**
         * Width of object.
         */
        public int width;
        /**
         * Height of object.
         */
        public int height;

        /**
         * Basic constructor. Width and height will be set later.
         */
        public WidthHeight() {
        }

        /**
         * Basic constructor, it will set width and height.
         *
         * @param width
         * @param height
         */
        public WidthHeight(int width, int height) {
            this.width = width;
            this.height = height;
        }
    }
}
