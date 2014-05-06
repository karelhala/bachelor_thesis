/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.models;

import BT.managers.DistanceCalculator;
import java.awt.Color;
import java.awt.Point;

/**
 *
 * @author Karel Hala
 */
public class LineModel extends CoordinateModel {

    /**
     * CoordinateModel with first object.
     */
    protected CoordinateModel firstObject;
    /**
     * CoordinateModel with second object.
     */
    protected CoordinateModel secondObject;
    /**
     * Distance calculator. Used for calculating distance from line, or
     * intersection of lines.
     */
    protected DistanceCalculator distanceCalculator;
    /**
     * Break points of line.
     */
    protected MyArrayList<Point> breakPoints;
    
    /**
     * Coordinate X of start point for this line.
     */
    protected int startX;
    /**
     * Coordinate Y of start point for this line.
     */
    protected int startY;
    /**
     * Coordinate X of end point for this line.
     */
    protected int endX;
    /**
     * Coordinate Y of end point for this line.
     */
    protected int endY;
    /**
     * Basic color of no parent is given.
     * no parent means, no connected object in other part of application.
     */
    protected Color noParentLine;
    /**
     * number of toleranced distance from line.
     */
    protected double tolerance;

    /**
     * Basic constructor.
     * It will set:
     * tolerance to 8.
     * selected color to RED.
     * basicColor to BLACK.
     * noParentLine color to GRAY.
     * normalColor to BLACK.
     * hower color to ORANGE.
     * Create new distanceCalculator.
     * create new breakPoints.
     */
    public LineModel() {
        this.tolerance = 8;
        this.selectedColor = Color.RED;
        this.basicColor = Color.BLACK;
        this.noParentLine = Color.GRAY;
        this.color = Color.BLACK;
        this.howerColor = Color.ORANGE;
        this.distanceCalculator = new DistanceCalculator();
        this.breakPoints = new MyArrayList<>();
    }

    public CoordinateModel getFirstObject() {
        return firstObject;
    }

    public CoordinateModel getSecondObject() {
        return secondObject;
    }

    public DistanceCalculator getDistanceCalculator() {
        return distanceCalculator;
    }

    public void setFirstObject(CoordinateModel firstObject) {
        if (firstObject != null) {
            setStartCoordinates(new Point(firstObject.getX(), firstObject.getY()));
            setEndPoint(new Point(firstObject.getX(), firstObject.getY()));
        }
        this.firstObject = firstObject;
    }

    public void setSecondObject(CoordinateModel secondObject) {
        if (secondObject != null) {
            setEndPoint(new Point(secondObject.getX(), secondObject.getY()));
        }
        this.secondObject = secondObject;
    }

    public void setDistanceCalculator(DistanceCalculator distanceCalculator) {
        this.distanceCalculator = distanceCalculator;
    }

    public void setEndPoint(Point endPoint) {
        this.endX = endPoint.x;
        this.endY = endPoint.y;
    }

    public void setStartCoordinates(Point startPoint) {
        this.startX = startPoint.x;
        this.startY = startPoint.y;
    }

    public MyArrayList<Point> getBreakPoints() {
        return this.breakPoints;
    }

    public void addBreakPoint(Point breakPoint) {
        if (this.breakPoints != null) {
            this.breakPoints.add(breakPoint);
        }
    }

    public void removeBreakPoint(Point breakPoint) {
        if (this.breakPoints != null) {
            this.breakPoints.remove(breakPoint);
        }
    }

    public Color getNoParentLine() {
        return noParentLine;
    }

    public void setNoParentLine(Color noParentLine) {
        this.noParentLine = noParentLine;
    }

    /**
     * Check if line is in range of X and Y.
     * @param x coordinate X of point.
     * @param y coordinate Y of point.
     * @return true if is in range, false if not.
     */
    public Boolean isInRange(int x, int y) {
        MyArrayList<Point> wholeArrayList = new MyArrayList<>();
        if (this.breakPoints != null && !this.breakPoints.isEmpty()) {
            wholeArrayList.addAll(this.breakPoints);
        }
        wholeArrayList.add(new Point(this.endX, this.endY));
        Point startPoint = new Point(this.startX, this.startY);
        for (Point point : wholeArrayList) {
            double distance = this.distanceCalculator.getDistanceOfPointToSegment(startPoint.x, startPoint.y, point.x, point.y, x, y);
            if (distance != -1 && distance <= this.tolerance) {
                return true;
            }
            startPoint.x = point.x;
            startPoint.y = point.y;
        }
        return false;
    }

    public Point getStartPoint() {
        if (this.firstObject != null) {
            return new Point(this.firstObject.getX(), this.firstObject.getY());
        }
        return null;
    }

    /**
     * Method for fetching end point of line based on second object. If line has
     * no second object, return endX and endY of this line.
     *
     * @return Point
     */
    public Point getEndPoint() {
        if (this.secondObject != null) {
            return new Point(this.getSecondObject().getX(), this.getSecondObject().getY());
        }
        return new Point(this.endX, this.endY);
    }

    /**
     * Checks if both objects at the end of line are null
     *
     * @return true if one of these objects is null
     */
    public Boolean isLineEmpty() {
        return this.firstObject == null || this.secondObject == null;
    }

    /**
     * Linemodel to string.
     * @return parentString() from coordinateModel.
     */
    @Override
    public String toString() {
        return parentToString();
    }
}
