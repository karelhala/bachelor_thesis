/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.models;

import BT.managers.DistanceCalculator;

/**
 *
 * @author Karel Hala
 */
public class LineModel extends CoordinateModel{
    protected CoordinateModel firstObject;
    protected CoordinateModel secondObject;
    protected DistanceCalculator distanceCalculator;
    
    public LineModel ()
    {
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
}
