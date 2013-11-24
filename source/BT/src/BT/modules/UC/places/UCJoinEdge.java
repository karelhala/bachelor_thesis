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
    private DistanceCalculator distanceCalculator;

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
        this.distanceCalculator = new DistanceCalculator();
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
        Point endPoint = calculateEndPoint();
        
        Point startPoint = calculateStartPoint();
        
        drawUseArrow(g, endPoint);        
        
        if (this.joinEdgeType == UCLineType.ASSOCIATION)
        {
            g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
        }
    }
    
    /**
     * 
     * @return 
     */
    private Point calculateStartPoint()
    {
        Point pointA = new Point(endX, endY);
        Point pointB = new Point(this.firstObject.getX(), this.firstObject.getY());
        Point startPoint;
        startPoint = this.distanceCalculator.getPointOfIntersectionLineSegments(pointA, pointB, this.firstObject.getWidth(), this.firstObject.getHeight());
        if (startPoint !=null)
        {
            return startPoint;
        }
        return new Point(startX, startY);
    }
    
    /**
     * 
     * @return 
     */
    private Point calculateEndPoint()
    {
        Point pointA = new Point(startX, startY);
        Point pointB = new Point(endX, endY);
        if (this.secondObject != null)
        {
            return this.distanceCalculator.getPointOfIntersectionLineSegments(pointA, pointB, this.secondObject.getWidth(), this.secondObject.getHeight());
        }
        return new Point(endX, endY);
    }
    
    /**
     * 
     * @param g
     * @param endPoint 
     */
    private void drawUseArrow(Graphics2D g, Point endPoint)
    {
        if (endPoint !=null )
        {
            drawArrow(g, endPoint, new Point(this.firstObject.getX(), this.firstObject.getY()));
        }
    }
    
    private void drawArrow(Graphics2D g2D, Point A, Point B)
    {
        Graphics2D g = (Graphics2D) g2D.create();
        double dx = B.x - A.x;
        double dy = B.y - A.y;
        double angle = Math.atan2(dy, dx);
        AffineTransform at = AffineTransform.getTranslateInstance(A.x, A.y);
        at.concatenate(AffineTransform.getRotateInstance(angle));
        g.transform(at);

        g.drawLine(0, 0, 0+5, 0+5);
        g.drawLine(0, 0, 0+5, 0-5);
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
        double distance = this.distanceCalculator.getDistanceOfPointToSegment(firstPointX, firstPointY, secondPointX, secondPointY, x, y);
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
