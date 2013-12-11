/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.UC.mainContent;

import BT.managers.UC.UCPlaceManager;
import BT.models.CoordinateModel;
import BT.models.LineModel;
import BT.modules.UC.places.UCActor;
import BT.modules.UC.places.UCJoinEdge.UCJoinEdgeController;
import BT.modules.UC.places.UCUseCase;
import java.awt.Point;

/**
 *
 * @author Karel Hala
 */
public class UCObjectChecker {
    private UCPlaceManager places;
    
    /**
     * 
     * @param places 
     */
    public UCObjectChecker(UCPlaceManager places)
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
            if (object instanceof UCActor)
            {
                if (((UCActor)object).isActor(x,y))
                {
                    return object;
                }
                else
                {
                    ((UCActor)object).setBasicColor();
                }
            }
            else if (object instanceof UCUseCase)
            {
                if (((UCUseCase)object).isUseCase(x,y))
                {
                    return object;
                }
                else
                {
                    ((UCUseCase)object).setBasicColor();
                }
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
