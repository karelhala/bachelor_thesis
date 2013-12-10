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
}
