/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.managers;

import BT.models.LineModel;
import BT.modules.UC.places.UCJoinEdge.UCJoinEdgeController;

/**
 *
 * @author Karel Hala
 */
public class ObjectChackerManager {
    protected PlaceManager places;
    
    /**
     * 
     * @param places 
     */
    public ObjectChackerManager(PlaceManager places)
    {
        this.places = places;
    }

    /**
     * 
     * @param x
     * @param y
     * @return 
     */
    private UCJoinEdgeController isJoinEdgeUnderMouse(int x, int y)
    {
        for (LineModel joinEdge : places.getJoinEdges())
        {
            if (((UCJoinEdgeController)joinEdge).isInRange(x,y))
            {
                return ((UCJoinEdgeController)joinEdge);
            }
            else
            {
                ((UCJoinEdgeController)joinEdge).setBasicColor();
            }
        }
        return null;
    }
}
