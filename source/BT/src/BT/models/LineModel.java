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

    protected CoordinateModel firstObject;
    protected CoordinateModel secondObject;
    protected DistanceCalculator distanceCalculator;
    protected MyArrayList<Point> breakPoints;
    protected int startX;
    protected int startY;
    protected int endX;
    protected int endY;
    protected Color noParentLine;
    protected double tolerance;

    public LineModel() {
        this.tolerance = 8;
        this.selectedColor = Color.RED;
        this.basicColor = Color.BLACK;
        this.noParentLine = Color.GRAY;
        this.color = Color.BLACK;
        this.howerColor = Color.orange;
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
        if (firstObject != null)
        {
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

    /**
     * @param endPoint
     */
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
     *
     * @param x
     * @param y
     * @return
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

    /**
     *
     * @return
     */
    public Point getStartPoint() {
        if (this.firstObject != null)
        {
            return new Point(this.firstObject.getX(), this.firstObject.getY());
        }
        return null;
    }

    /**
     * Method for fetching end point of line based on second object.
     * If line has no second object, return endX and endY of this line.
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
     * @return true if one of these objects is null
     */
    public Boolean isLineEmpty()
    {
        return this.firstObject == null || this.secondObject ==null;
    }
}
