/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.managers;

import BT.managers.PlaceManager;
import BT.models.CoordinateModel;
import BT.models.LineModel;
import java.awt.Point;

/**
 *
 * @author Karel Hala
 */
public class ObjectChecker {
    private final PlaceManager places;
    
    /**
     * 
     * @param places 
     */
    public ObjectChecker(PlaceManager places)
    {
        super();
        this.places = places;
    }
    
    /**
     * 
     * @param mousePoint
     * @return 
     */
    public CoordinateModel getObjectUnderMouse(Point mousePoint)
    {
        CoordinateModel coordModel;
        coordModel = isObjectunderMouse(mousePoint.x, mousePoint.y);
        if (coordModel!=null)
        {
            return coordModel;
        }
        coordModel = isJoinEdgeUnderMouse(mousePoint.x, mousePoint.y);
        if (coordModel!=null)
        {
            return coordModel;
        }

        return null;
    }
     /**
     * 
     * @param x
     * @param y
     * @return 
     */
    private CoordinateModel isObjectunderMouse(int x, int y)
    {
        for (CoordinateModel object : places.getObjects())
        {
            if (object.isObject(x,y))
            {
                return object;
            }
            else
            {
                object.setBasicColor();
            }
        }
        return null;
    }

    /**
     * 
     * @param x
     * @param y
     * @return 
     */
    private LineModel isJoinEdgeUnderMouse(int x, int y)
    {
        for (LineModel joinEdge : places.getJoinEdges())
        {
            if (joinEdge.isInRange(x,y))
            {
                return joinEdge;
            }
            else
            {
                joinEdge.setBasicColor();
            }
        }
        return null;
    }
}
