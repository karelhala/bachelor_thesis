/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.UC.places;

import BT.BT.UCLineType;
import BT.managers.DistanceCalculator;
import BT.models.CoordinateModel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
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

    private UCLineType joinEdgeType;
    
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
        this.joinEdgeType = UCLineType.ASSOCIATION;
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
    public UCLineType getJoinEdgeType()
    {
        return this.joinEdgeType;
    }
    
    /**
     * 
     * @param g 
     */
    public void drawJoinEdge(Graphics2D g) {
        DistanceCalculator calculateDistance = new DistanceCalculator();
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
        
        if (this.secondObject != null)
        {
            Point intersectionPoint = calculateDistance.getPointOfIntersectionLineSegments(startX, startY, endX, endY, this.secondObject.getWidth(), this.secondObject.getHeight());
            if (intersectionPoint !=null )
            {
                drawArrow(g, intersectionPoint, new Point(this.firstObject.getX(), this.firstObject.getY()));
                endX = intersectionPoint.x;
                endY = intersectionPoint.y;
            }
        }
        else
        {
            drawArrow(g, new Point(endX, endY), new Point(this.firstObject.getX(), this.firstObject.getY()));
        }
        
        if (this.firstObject != null)
        {
            Point intersectionPoint = calculateDistance.getPointOfIntersectionLineSegments(endX, endY, this.firstObject.getX(), this.firstObject.getY(), this.firstObject.getWidth(), this.firstObject.getHeight());
            if (intersectionPoint !=null )
            {
                startX = intersectionPoint.x;
                startY = intersectionPoint.y;
            }
        }
        
        if (this.joinEdgeType == UCLineType.ASSOCIATION)
        {
            g.drawLine(startX, startY, endX, endY);
        }
    }
    
    private void drawArrow(Graphics2D g2D, Point A, Point B)
    {
        Graphics2D g = (Graphics2D) g2D.create();
        double dx = B.x - A.x;
        double dy = B.y - A.y;
        double angle = Math.atan2(dy, dx);
        System.out.println(angle);
        AffineTransform at = AffineTransform.getTranslateInstance(A.x, A.y);
        at.concatenate(AffineTransform.getRotateInstance(angle));
        g.transform(at);

        g.drawLine(0, 0, 0+10, 0+10);
        g.drawLine(0, 0, 0+10, 0-10);
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
