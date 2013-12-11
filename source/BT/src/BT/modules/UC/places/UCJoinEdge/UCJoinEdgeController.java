/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.UC.places.UCJoinEdge;

import BT.managers.PointsCalculator;
import BT.BT.UCLineType;
import BT.models.CoordinateModel;
import BT.models.LineModel;
import BT.modules.UC.places.UCActor;
import BT.modules.UC.places.UCUseCase;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Objects;

/**
 *
 * @author Karel Hala
 */
public class UCJoinEdgeController extends LineModel{
    private UCLineType joinEdgeType;

    public void setJoinEdgeType(UCLineType joinEdgeType) {
        this.joinEdgeType = joinEdgeType;
    }
    
    /**
     *
     */
    public UCJoinEdgeController ()
    {
        super();
        this.joinEdgeType = UCLineType.ASSOCIATION;
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
        if (this.firstObject instanceof UCActor && object instanceof UCUseCase)
        {
            this.secondObject = this.firstObject;
            this.firstObject = object;
        }
        else
        {
            this.secondObject = object;
        }
    }
    
    /**
     * 
     * @return 
     */
    public UCLineType getJoinEdgeType()
    {
        return this.joinEdgeType;
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
            lineDrawer.drawLine(g);
            setStartCoordinates(startPoint);
            
            setEndPoint(endPoint);
        }
    }
    
    /**
     * 
     * @param other
     * @return 
     */
    @Override
    public boolean equals(Object other)
    {
        if (other instanceof UCJoinEdgeController)
        {
            UCJoinEdgeController object = (UCJoinEdgeController) other;
            if (this.hashCode()==object.hashCode())
            {
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.firstObject);
        hash = 67 * hash + Objects.hashCode(this.secondObject);
        hash = 67 * hash + Objects.hashCode(this.joinEdgeType);
        return hash;
    } 
}
