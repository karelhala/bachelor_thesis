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
 * Model that describes each object on drawing pane. It stores basic data, that
 * are used in each object, even in join edges.
 *
 * @author Karel Hala
 */
public class CoordinateModel {

    /**
     * DashedStroke to be used if no lines are attached.
     */
    protected BasicStroke dashedStroke;
    /**
     * Width of whole object.
     */
    protected int objectWidth;
    /**
     * Height of whole object.
     */
    protected int objectHeight;
    /**
     * Coordinate X of object.
     */
    protected int x;
    /**
     * Coordinate Y of object.
     */
    protected int y;
    /**
     * Name of object.
     */
    protected String name;
    /**
     * basicColor of object.
     */
    protected Color basicColor;
    /**
     * Normal color of object.
     */
    protected Color color;
    /**
     * Color, that is shown if object is selected.
     */
    protected Color selectedColor;
    /**
     * Width of object.
     */
    protected int width;
    /**
     * Height of object.
     */
    protected int height;
    /**
     * If object is selected or not.
     */
    protected Boolean selected;
    /**
     * Color, to be displayed if mouse hower over object.
     */
    protected Color howerColor;
    /**
     * Object's in Joins.
     */
    protected MyArrayList<LineModel> inJoins;
    /**
     * Object's out Joins
     */
    protected MyArrayList<LineModel> outJoins;
    /**
     * Assigned object in other part of application.
     */
    protected CoordinateModel assignedObject;
    /**
     * Size of text in whole object.
     */
    protected int textSize;
    /**
     * Define if this place is editable.
     */
    protected Boolean editable;
    /**
     * Define if this place can be deleted.
     */
    protected Boolean removable;

    /**
     * Basic constructor, that sets object editable, removable and create
     * dashedStroke.
     */
    public CoordinateModel() {
        this.selected = false;
        float dash1[] = {10.0f};
        this.dashedStroke = new BasicStroke(2.0f,
                BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER,
                10.0f, dash1, 0.0f);
        this.removable = true;
        this.editable = true;
    }

    /**
     * Get in joins of object.
     *
     * @return ArrayList<LineModel>.
     */
    public ArrayList<LineModel> getInJoins() {
        return inJoins;
    }

    /**
     * Get out joins of object.
     *
     * @return ArrayList<LineModel>.
     */
    public ArrayList<LineModel> getOutJoins() {
        return outJoins;
    }

    /**
     * Set width of object.
     *
     * @param width int.
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Set height of object.
     *
     * @param height int.
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Get coordinate X of object.
     *
     * @return int X.
     */
    public int getX() {
        return this.x;
    }

    /**
     * Set object's coordinate X.
     *
     * @param X int.
     */
    public void setX(int X) {
        this.x = X;
    }

    /**
     * Get object's coordinate Y.
     *
     * @return Y as int.
     */
    public int getY() {
        return this.y;
    }

    /**
     * Set object's coordinate Y.
     *
     * @param Y int.
     */
    public void setY(int Y) {
        this.y = Y;
    }

    /**
     * Set name of object, this name will be printed arround object.
     *
     * @param name String.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get name of object, this name is printed arround object.
     *
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set normal color of object.
     *
     * @param color of object.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Get normal Color of object.
     *
     * @return Color.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Get width of object.
     *
     * @return int.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Get height of object.
     *
     * @return int.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Get color which is set when object is selected.
     *
     * @return Color.
     */
    public Color getSelectedColor() {
        return this.selectedColor;
    }

    /**
     * Set that object is selected.
     *
     * @param selected Boolean.
     */
    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    /**
     * Is object selected?
     *
     * @return True or false.
     */
    public Boolean getSelected() {
        return this.selected;
    }

    /**
     * Set basic color, this color is drawn when object is not selected or
     * howered.
     */
    public void setBasicColor() {
        this.color = this.basicColor;
    }

    /**
     * Set normal color to howerColor.
     */
    public void setHowerColor() {
        this.color = this.howerColor;
    }

    /**
     * Get width of whole object.
     *
     * @return
     */
    public int getObjectWidth() {
        return objectWidth;
    }

    /**
     * Get height of whole object.
     *
     * @return
     */
    public int getObjectHeight() {
        return objectHeight;
    }

    /**
     * Set width of whole object.
     *
     * @param objectWidth int.
     */
    public void setObjectWidth(int objectWidth) {
        this.objectWidth = objectWidth;
    }

    /**
     * Set height of whole object.
     *
     * @param objectHeight int.
     */
    public void setObjectHeight(int objectHeight) {
        this.objectHeight = objectHeight;
    }

    /**
     * Add unique in join to object.
     *
     * @param inJoins LineModel.
     */
    public void addInJoin(LineModel inJoins) {
        if (this.inJoins != null) {
            this.inJoins.addUnique(inJoins);
        }
    }

    /**
     * Get assiged object of other part of application.
     *
     * @return
     */
    public CoordinateModel getAssignedObject() {
        return assignedObject;
    }

    /**
     * Set assigned object of other part of application.
     *
     * @param assignedObject CoordinateModel.
     */
    public void setAssignedObject(CoordinateModel assignedObject) {
        this.assignedObject = assignedObject;
    }

    /**
     * Add new unique out join to object.
     *
     * @param outJoins LineModel.
     */
    public void addOutJoins(LineModel outJoins) {
        if (this.outJoins != null) {
            this.outJoins.addUnique(outJoins);
        }
    }

    /**
     * Remove desired in join from object.
     *
     * @param inJoin LineModel to be removed.
     */
    public void removeInJoin(LineModel inJoin) {
        this.inJoins.remove(inJoin);
    }

    /**
     * Remove desired out join from object.
     *
     * @param outJoin to be removed.
     */
    public void removeOutJoin(LineModel outJoin) {
        this.outJoins.remove(outJoin);
    }

    /**
     * Small method for getting maximum from a and b.
     *
     * @param a int.
     * @param b int.
     * @return bigger of a or b.
     */
    protected int getMax(int a, int b) {
        return (a > b ? a : b);
    }

    /**
     * Check if X and Y is in object.
     *
     * @param x X coordinate of point.
     * @param y Y coordinate of point.
     * @return true if object lies in X and Y coordinates.
     */
    public boolean isObject(int x, int y) {
        int isX = Math.abs(x - this.x);
        int isY = Math.abs(y - this.y);
        return isX <= getMax(this.objectWidth, this.width) / 2 && isY <= getMax(this.objectHeight, this.height) / 2;
    }

    /**
     * If no object is assigned, wdraw string that indicates this.
     *
     * @param g Graphics2D.
     * @param text String.
     */
    protected void drawNoObjectString(Graphics2D g, String text) {
        if (this.assignedObject == null) {
            g.setFont(new Font("Arial", Font.ITALIC, this.textSize));
            FontMetrics fm = g.getFontMetrics(g.getFont());
            g.drawString(text, this.x - fm.stringWidth(text) / 2, this.y + getMax(this.objectHeight, this.height) / 2 + fm.getHeight());
        }
    }

    /**
     * Method for deleting all assigned objects with selected object. It will
     * also delete assigned objects to every line.
     */
    public void disMemberObject() {
        if (this.getAssignedObject() != null) {
            this.getAssignedObject().setAssignedObject(null);
        }
        this.setAssignedObject(null);
        if (this.getOutJoins() != null && !this.getOutJoins().isEmpty()) {
            for (CoordinateModel oneLine : this.getOutJoins()) {
                if (oneLine.getAssignedObject() != null && !oneLine.getAssignedObject().equals(oneLine)) {
                    oneLine.setAssignedObject(null);
                }
            }
        }
        if (this.getInJoins() != null && !this.getInJoins().isEmpty()) {
            for (CoordinateModel oneLine : this.getInJoins()) {
                if (oneLine.getAssignedObject() != null && !oneLine.getAssignedObject().equals(oneLine)) {
                    oneLine.setAssignedObject(null);
                }
            }
        }
    }

    /**
     * Get if object is editable.
     *
     * @return true or false.
     */
    public Boolean isEditable() {
        return editable;
    }

    /**
     * Set that object is editable.
     *
     * @param editable Boolean.
     */
    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    /**
     * Check if object is removable.
     *
     * @return true or false.
     */
    public Boolean isRemovable() {
        return removable;
    }

    /**
     * Set that object can be removed.
     *
     * @param removable true or false.
     */
    public void setRemovable(Boolean removable) {
        this.removable = removable;
    }

    /**
     * New toString.
     *
     * @return name as String.
     */
    @Override
    public String toString() {
        return this.name;
    }

    /**
     * Get originalToString.
     *
     * @return
     */
    protected String parentToString() {
        return super.toString();
    }
}
