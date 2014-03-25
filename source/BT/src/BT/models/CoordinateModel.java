/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.models;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author Karel Hala
 */
public class CoordinateModel {

    /**
     * 
     */
    protected BasicStroke dashedStroke;
    /**
     *
     */
    protected int objectWidth;
    /**
     *
     */
    protected int objectHeight;
    /**
     *
     */
    protected int x;
    /**
     *
     */
    protected int y;
    /**
     *
     */
    protected String name;
    /**
     *
     */
    protected Color basicColor;
    /**
     *
     */
    protected Color color;
    /**
     *
     */
    protected Color selectedColor;
    /**
     *
     */
    protected int width;
    /**
     *
     */
    protected int height;
    /**
     *
     */
    protected Boolean selected;
    /**
     *
     */
    protected Color howerColor;
    /**
     *
     */
    protected ArrayList<LineModel> inJoins;
    /**
     *
     */
    protected ArrayList<LineModel> outJoins;
    /**
     * 
     */
    protected CoordinateModel assignedObject;
    /**
     * 
     */
    protected int textSize;

    /**
     *
     */
    public CoordinateModel() {
        this.selected = true;
        float dash1[] = {10.0f};
        this.dashedStroke
                = new BasicStroke(2.0f,
                        BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER,
                        10.0f, dash1, 0.0f);
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

    public int getX() {
        return this.x;
    }

    public void setX(int X) {
        this.x = X;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int Y) {
        this.y = Y;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Color getSelectedColor() {
        return this.selectedColor;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public Boolean getSelected() {
        return this.selected;
    }

    public void setBasicColor() {
        this.color = this.basicColor;
    }

    public void setHowerColor() {
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
        if (this.inJoins != null) {
            this.inJoins.add(inJoins);
        }
    }

    public CoordinateModel getAssignedObject() {
        return assignedObject;
    }

    public void setAssignedObject(CoordinateModel assignedObject) {
        this.assignedObject = assignedObject;
    }

    public void addOutJoins(LineModel outJoins) {
        if (this.outJoins != null) {
            this.outJoins.add(outJoins);
        }
    }

    public void removeInJoin(LineModel inJoin) {
        this.inJoins.remove(inJoin);
    }

    public void removeOutJoin(LineModel outJoin) {
        this.outJoins.remove(outJoin);
    }

    /**
     *
     * @param a
     * @param b
     * @return
     */
    protected int getMax(int a, int b) {
        return (a > b ? a : b);
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    public boolean isObject(int x, int y) {
        int isX = Math.abs(x - this.x);
        int isY = Math.abs(y - this.y);
        return isX <= getMax(this.objectWidth, this.width) / 2 && isY <= getMax(this.objectHeight, this.height) / 2;
    }
    
    /**
     * 
     * @param g
     * @param text 
     */
    protected void drawNoObjectString(Graphics2D g, String text)
    {
        if (this.assignedObject == null)
        {
            g.setFont(new Font("Arial", Font.ITALIC, this.textSize));
            FontMetrics fm = g.getFontMetrics(g.getFont());
            g.drawString(text, this.x-fm.stringWidth(text)/2, this.y+getMax(this.objectHeight, this.height)/2+fm.getHeight());
        }
    }
}
