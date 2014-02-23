/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ClassDiagram.places.joinEdge;

import BT.BT.CDLineType;
import BT.managers.PointsCalculator;
import BT.models.CoordinateModel;
import BT.models.LineModel;
import BT.modules.UC.places.UCJoinEdge.UCJoinEdgeDrawer;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
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
    
    
        /**
     * 
     * @param g 
     */
    public void drawJoinEdge(Graphics2D g) {
        if (this.secondObject != null)
        {
            this.endX = this.secondObject.getX();
            this.endY = this.secondObject.getY();
        }
        this.startX = this.firstObject.getX();
        this.startY = this.firstObject.getY();
        PointsCalculator pointsCaluclator = new PointsCalculator(this.firstObject, this.secondObject, new Point(this.startX, this.startY), new Point(this.endX, this.endY));  
        
        UCJoinEdgeDrawer lineDrawer = new UCJoinEdgeDrawer(this, pointsCaluclator.getStartPoint(), pointsCaluclator.getEndPoint());
        Point startPoint = pointsCaluclator.getStartPoint();
        Point endPoint = pointsCaluclator.getEndPoint();
        if (startPoint !=null && endPoint !=null)
        {
            g.setStroke(new BasicStroke(2));
//            lineDrawer.drawLine(g);
            setStartCoordinates(startPoint);
            
            setEndPoint(endPoint);
        }
    }
}
