/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.managers;

import java.awt.Color;

/**
 *
 * @author Karel Hala
 */
public class CoordinateManager {
    protected int x;
    protected int y;
    protected String name;
    protected Color basicColor;
    protected Color selectedColor;
    protected int width;
    protected int height;
    
    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    public int getX()
    {
        return this.x;
    }
    
    public void setX(int X)
    {
        this.x = X;
    }
    
    public int getY()
    {
        return this.y;
    }
    
    public void setY(int Y)
    {
        this.y = Y;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public void setColor(Color color)
    {
        this.basicColor = color;
    }
    
    public Color getColor()
    {
        return this.basicColor;
    }

    public int getWidth() 
    {
        return width;
    }

    public int getHeight() 
    {
        return height;
    }

    public int getRightX() {
        return this.x - this.height/2;
    }
    
    public int getLeftX() {
        return this.x + this.height/2;
    }

    public int getCalculatedY() {
        return this.y ;
    }
    
    public Color getSelectedColor()
    {
        return this.selectedColor;
    }
    
}
