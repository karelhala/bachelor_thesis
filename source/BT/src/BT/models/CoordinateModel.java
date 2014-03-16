/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.models;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Karel Hala
 */
public class CoordinateModel {
    protected int objectWidth;
    protected int objectHeight;
    protected int x;
    protected int y;
    protected String name;
    protected Color basicColor;
    protected Color color;
    protected Color selectedColor;
    protected int width;
    protected int height;
    protected Boolean selected;
    protected Color howerColor;
    protected ArrayList<LineModel> inJoins;
    protected ArrayList<LineModel> outJoins;
    public CoordinateModel()
    {
        this.selected = true;
    }

    public ArrayList<LineModel> getInJoins() {
        return inJoins;
    }

    public ArrayList<LineModel> getOutJoins() {
        return outJoins;
    }
    
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
        this.color = color;
    }
    
    public Color getColor()
    {
        return this.color;
    }

    public int getWidth() 
    {
        return width;
    }

    public int getHeight() 
    {
        return height;
    }
    
    public Color getSelectedColor()
    {
        return this.selectedColor;
    }
    
    public void setSelected(Boolean selected)
    {
        this.selected = selected;
    }
    
    public Boolean getSelected()
    {
        return this.selected;
    }
    
    /**
     * 
     */
    public void setBasicColor() {
        this.color = this.basicColor;
    }
    
    public void setHowerColor()
    {
        this.color = this.howerColor;
    }

    public int getObjectWidth() {
        return objectWidth;
    }

    public int getObjectHeight() {
        return objectHeight;
    }

    public void setObjectWidth(int objectWidth) {
        this.objectWidth = objectWidth;
    }

    public void setObjectHeight(int objectHeight) {
        this.objectHeight = objectHeight;
    }

    public void addInJoin(LineModel inJoins) {
        if (this.inJoins != null)
        {
            this.inJoins.add(inJoins);
        }
    }

    public void addOutJoins(LineModel outJoins) {
        if (this.outJoins != null)
        {
            this.outJoins.add(outJoins);
        }
    }
    
        /**
     * 
     * @param a
     * @param b
     * @return 
     */
    protected int getMax(int a, int b) {
        return (a>b?a:b);
    }
    
    /**
     * 
     * @param x
     * @param y
     * @return 
     */
    public boolean isObject(int x, int y)
    {
        int isX=Math.abs(x-this.x);
        int isY=Math.abs(y-this.y);
        return isX<=getMax(this.objectWidth, this.width)/2 && isY<=getMax(this.objectHeight, this.height)/2;
    }
    
}
