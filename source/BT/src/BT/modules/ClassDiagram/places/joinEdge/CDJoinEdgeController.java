/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ClassDiagram.places.joinEdge;

import BT.BT.CDLineType;
import BT.BT.ClassType;
import BT.managers.PointsCalculator;
import BT.models.CoordinateModel;
import BT.models.LineModel;
import BT.modules.ClassDiagram.places.CDClass;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 *
 * @author Karel Hala
 */
public class CDJoinEdgeController extends LineModel {

    private CDLineType joinEdgeType;
    
    public CDJoinEdgeController(CoordinateModel firstObject, CoordinateModel secondObject)
    {
        super();
        this.firstObject = firstObject;
        this.secondObject = secondObject;
    }

    public void setJoinEdgeType(CDLineType joinEdgeType) {
        this.joinEdgeType = joinEdgeType;
    }

    public CDLineType getJoinEdgeType() {
        return joinEdgeType;
    }

    /**
     *
     */
    public CDJoinEdgeController() {
        super();
        this.joinEdgeType = CDLineType.ASSOCIATION;
    }
    
    /**
     * Method for checking if second object is same type as first object
     * @return true if both objects are same type
     */
    public Boolean isSecondObjectSameType()
    {
        if (!this.isLineEmpty())
        {
            return ((CDClass)this.getSecondObject()).getTypeOfClass() == ((CDClass)this.getFirstObject()).getTypeOfClass();
        }
        return false;
    }
    
    /**
     * Checks if both objects are type of typeOfObjects.
     * @param typeOfObjects selected type
     * @return true if both objects are type of typeOfObjects
     */
    public Boolean areObjectsOfType(ClassType typeOfObjects)
    {
        if (!this.isLineEmpty())
        {
            return ((CDClass)this.secondObject).getTypeOfClass() == typeOfObjects && ((CDClass)this.firstObject).getTypeOfClass() == typeOfObjects;
        }
        return false;
    }

    /**
     *
     * @param g
     */
    public void drawJoinEdge(Graphics2D g) {
        PointsCalculator pointsCaluclator = new PointsCalculator(this.firstObject, this.secondObject, getStartPoint(), getEndPoint(), this.breakPoints);

        CDJoinEdgeDrawer lineDrawer;
        lineDrawer = new CDJoinEdgeDrawer(this, pointsCaluclator.getStartPoint(), pointsCaluclator.getEndPoint());
        Point startPoint = pointsCaluclator.getStartPoint();
        Point endPoint = pointsCaluclator.getEndPoint();
        if (startPoint != null && endPoint != null) {
            g.setStroke(new BasicStroke(2));
            lineDrawer.drawLine(g);
            setStartCoordinates(startPoint);

            setEndPoint(endPoint);
        }
    }
}
