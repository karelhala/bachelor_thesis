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
public class LineModel extends CoordinateModel{
    protected CoordinateModel firstObject;
    protected CoordinateModel secondObject;
    protected DistanceCalculator distanceCalculator;
    protected int startX;
    protected int startY;
    protected int endX;
    protected int endY;
    protected double tolerance;
    
    public LineModel ()
    {
        this.tolerance = 8;
        this.selected = true;
        this.selectedColor = Color.RED;
        this.basicColor = Color.BLACK;
        this.color = Color.BLACK;
        this.howerColor = Color.orange;
        this.distanceCalculator = new DistanceCalculator();
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
        this.firstObject = firstObject;
    }

    public void setSecondObject(CoordinateModel secondObject) {
        this.secondObject = secondObject;
    }

    public void setDistanceCalculator(DistanceCalculator distanceCalculator) {
        this.distanceCalculator = distanceCalculator;
    }
    
    /**
     * 
     * @param x
     * @param y 
     */
    public void setEndPoint(Point endPoint)
    {
        this.endX = endPoint.x;
        this.endY = endPoint.y;
    }
    
    public void setStartCoordinates(Point startPoint)
    {
        this.startX = startPoint.x;
        this.startY = startPoint.y;
    }
    
    /**
     * 
     * @param x
     * @param y
     * @return 
     */
    public Boolean isInRange(int x, int y) {
        double distance = this.distanceCalculator.getDistanceOfPointToSegment(this.startX, this.startY, this.endX, this.endY, x, y);
        if (distance !=-1 && distance < this.tolerance)
        {
            return true;
        }
        return false;
    }
}
