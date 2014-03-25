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

    private CoordinateModel firstObject;
    private CoordinateModel secondObject;
    private DistanceCalculator distanceCalculator;
    private Point startPoint;
    private Point endPoint;
    private MyArrayList<Point> breakPoints;

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public PointsCalculator(CoordinateModel firstObject, CoordinateModel secondObject, Point startPoint, Point endPoint, MyArrayList<Point> breakPoints) {
        this.firstObject = firstObject;
        this.secondObject = secondObject;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.distanceCalculator = new DistanceCalculator();
        this.breakPoints = breakPoints;
        calculatePoints();
    }

    public PointsCalculator(CoordinateModel firstObject, CoordinateModel secondObject, Point startPoint, Point endPoint) {
        this.firstObject = firstObject;
        this.secondObject = secondObject;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.distanceCalculator = new DistanceCalculator();
        calculatePoints();
    }

    /**
     *
     */
    private void calculatePoints() {
        this.endPoint = calculateEndPoint();

        this.startPoint = calculateStartPoint();

    }

    /**
     *
     * @return
     */
    private Point calculateStartPoint() {
        Point pointA = (this.breakPoints != null && !this.breakPoints.isEmpty()) ? this.breakPoints.getFirst() : this.endPoint;
        Point pointB = this.startPoint;
        Point calculatedPoint;
        WidthHeight widthHeight = getObjectWidthAndheight(this.firstObject);
        calculatedPoint = this.distanceCalculator.getPointOfIntersectionLineSegments(pointA, pointB, widthHeight.width, widthHeight.height);
        if (calculatedPoint != null) {
            return calculatedPoint;
        }
        return this.startPoint;
    }

    /**
     *
     * @param object
     * @return
     */
    private WidthHeight getObjectWidthAndheight(CoordinateModel object) {
        int width;
        int height;
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

    /**
     *
     * @return
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
     *
     */
    private class WidthHeight {

        public int width;
        public int height;

        public WidthHeight() {

        }

        public WidthHeight(int width, int height) {
            this.width = width;
            this.height = height;
        }
    }
}
