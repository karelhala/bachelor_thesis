/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.UC.places.UCJoinEdge;

import BT.managers.PointsCalculator;
import BT.BT.UCLineType;
import BT.managers.DistanceCalculator;
import BT.models.CoordinateModel;
import BT.modules.UC.places.UCActor;
import BT.modules.UC.places.UCUseCase;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Objects;

/**
 *
 * @author Karel Hala
 */
public class UCJoinEdgeController extends CoordinateModel{
    private CoordinateModel firstObject;
    private CoordinateModel secondObject;
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private double tolerance;
    private DistanceCalculator distanceCalculator;

    private UCLineType joinEdgeType;

    public void setJoinEdgeType(UCLineType joinEdgeType) {
        this.joinEdgeType = joinEdgeType;
    }
    
    /**
     * TODO: make model
     */
    public UCJoinEdgeController ()
    {
        this.tolerance = 8;
        this.selected = true;
        this.selectedColor = Color.RED;
        this.basicColor = Color.BLACK;
        this.color = Color.BLACK;
        this.joinEdgeType = UCLineType.ASSOCIATION;
        this.distanceCalculator = new DistanceCalculator();
        this.howerColor = Color.orange;
    }
    
    /**
     * 
     * @param object 
     */
    public void setFirstObject(CoordinateModel object)
    {
        
        this.startX = object.getX();
        this.startY = object.getY();
        this.endX = object.getX();
        this.endY = object.getY();
        this.firstObject = object;
    }
    
    /**
     * 
     * @return 
     */
    public CoordinateModel getfirstObject()
    {
        return this.firstObject;
    }
    
    /**
     * 
     * @return 
     */
    public CoordinateModel getSecondObject()
    {
        return this.secondObject;
    }
    
    /**
     * 
     * @param object 
     */
    public void setSecondObject(CoordinateModel object)
    {
        this.endX = object.getX();
        this.endY = object.getY();
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
