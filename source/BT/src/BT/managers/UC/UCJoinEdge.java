/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.managers.UC;

import BT.managers.CoordinateManager;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Objects;

/**
 *
 * @author Karel Hala
 */
public class UCJoinEdge extends CoordinateManager{
    
    private Boolean selected;
    private CoordinateManager firstObject;
    private CoordinateManager secondObject;
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    public static enum LineType{ASSOCIATION, USES, EXTENDS};
    private LineType joinEdgeType;
    
    public UCJoinEdge ()
    {
        this.selected = true;
        this.selectedColor = Color.RED;
        this.basicColor = Color.BLACK;
        this.joinEdgeType = LineType.ASSOCIATION;
    }
    
    public void setFirstObject(CoordinateManager object)
    {
        
        this.startX = object.getX();
        this.startY = object.getY();
        this.endX = object.getX();
        this.endY = object.getY();
        this.firstObject = object;
    }
    
    public CoordinateManager getfirstObject()
    {
        return this.firstObject;
    }
    
    public void setSecondObject(CoordinateManager object)
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
    
    public CoordinateManager getSecondObject()
    {
        return this.secondObject;
    }
    
    public void setMouseCoordinates(int x, int y)
    {
        this.endX = x;
        this.endY = y;
    }
    
    public void setSelected(Boolean selected)
    {
        this.selected = selected;
    }
    
    public Boolean getSelected()
    {
        return this.selected;
    }
    
    public LineType getJoinEdgeType()
    {
        return this.joinEdgeType;
    }
    
    public void drawJoinEdge(Graphics2D g) {      
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.firstObject);
        hash = 67 * hash + Objects.hashCode(this.secondObject);
        hash = 67 * hash + Objects.hashCode(this.joinEdgeType);
        return hash;
    }
    
}
