/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.UC.places;

import BT.managers.UC.DistanceCalculator;
import BT.modules.UC.places.UCActor;
import BT.models.CoordinateModel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Objects;

/**
 *
 * @author Karel Hala
 */
public class UCJoinEdge extends CoordinateModel{
    private CoordinateModel firstObject;
    private CoordinateModel secondObject;
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private double tolerance;

    public static enum LineType{ASSOCIATION, USES, EXTENDS};
    private LineType joinEdgeType;
    
    /**
     * TODO: make model
     */
    public UCJoinEdge ()
    {
        this.tolerance = 8;
        this.selected = true;
        this.selectedColor = Color.RED;
        this.basicColor = Color.BLACK;
        this.color = Color.BLACK;
        this.joinEdgeType = LineType.ASSOCIATION;
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
     * @return 
     */
    public CoordinateModel getSecondObject()
    {
        return this.secondObject;
    }
    
    /**
     * 
     * @param x
     * @param y 
     */
    public void setMouseCoordinates(int x, int y)
    {
        this.endX = x;
        this.endY = y;
    }
    
    /**
     * 
     * @return 
     */
    public LineType getJoinEdgeType()
    {
        return this.joinEdgeType;
    }
    
    /**
     * 
     * @param g 
     */
    public void drawJoinEdge(Graphics2D g) {      
        if (this.selected)
        {
            g.setColor(selectedColor);
        }
        else
        {
            g.setColor(color);
        }
        if (this.getSecondObject() != null)
        {
            endX = secondObject.getX();
            endY = secondObject.getY();
        }
        startX = firstObject.getX();
        startY = firstObject.getY();
        g.setStroke(new BasicStroke(2));
        if (this.joinEdgeType == LineType.ASSOCIATION)
        {
            g.drawLine(startX, startY, endX, endY);
        }
    }
    
    /**
     * 
     * @param x
     * @param y
     * @return 
     */
    public Boolean isInRange(int x, int y) {
        int firstPointX = this.firstObject.getX();
        int firstPointY = this.firstObject.getY();
        
        int secondPointX = this.secondObject.getX();
        int secondPointY = this.secondObject.getY();
        DistanceCalculator calculateDistance = new DistanceCalculator();
        double distance = calculateDistance.getDistanceOfPointToSegment(firstPointX, firstPointY, secondPointX, secondPointY, x, y);
        if (distance !=-1 && distance < this.tolerance)
        {
            return true;
        }
        return false;
    }
    
    /**
     * 
     */
    public void setBasicColor() {
        this.color = this.basicColor;
    }
    
    /**
     * 
     * @param other
     * @return 
     */
    @Override
    public boolean equals(Object other)
    {
        if (other instanceof UCJoinEdge)
        {
            UCJoinEdge object = (UCJoinEdge) other;
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
