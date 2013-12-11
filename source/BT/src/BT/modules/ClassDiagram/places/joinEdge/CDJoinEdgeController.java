/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ClassDiagram.places.joinEdge;

import BT.BT;
import BT.BT.CDLineType;
import BT.models.CoordinateModel;
import BT.models.LineModel;
import BT.modules.UC.places.UCActor;
import BT.modules.UC.places.UCUseCase;
import java.awt.Point;

/**
 *
 * @author Karel Hala
 */
public class CDJoinEdgeController extends LineModel{
    private CDLineType joinEdgeType;
    
    public void setJoinEdgeType(CDLineType joinEdgeType) {
        this.joinEdgeType = joinEdgeType;
    }
    
    /**
     *
     */
    public CDJoinEdgeController ()
    {
        super();
        this.joinEdgeType = CDLineType.ASSOCIATION;
    }
    
    /**
     * 
     * @param object 
     */
    @Override
    public void setFirstObject(CoordinateModel object)
    {
        setStartCoordinates(new Point(object.getX(), object.getY()));
        setEndPoint(new Point(object.getX(), object.getY()));
        this.firstObject = object;
    }
    
    /**
     * 
     * @param object 
     */
    @Override
    public void setSecondObject(CoordinateModel object)
    {
        if (object !=null)
        {
            setEndPoint(new Point(object.getX(), object.getY()));
        }
        else
        {
            this.secondObject = object;
        }
    }
}
