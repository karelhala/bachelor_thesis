/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ClassDiagram.places;

import BT.BT;
import BT.managers.CD.Attribute;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

/**
 * Drawer for classes in class diagram. It draws class, it's variables and methods and it's name.
 *
 * @author Karel Hala
 */
public class CDClassDrawer extends CDClassModel {

    /**
     * Basic constructor, calls parent with x and y.
     * @param x coordinate X.
     * @param y coordinate Y.
     */
    public CDClassDrawer(int x, int y) {
        super(x, y);
    }

    /**
     * Draw class on drawing panel. Set font to Arial, bold and text size. If no in or out join is found set stroke to
     * dashed. Draw filled class rect. Draw line above methods and below variables. Draw variables and methods. Resize
     * the size of class based on size of variables and methods.
     *
     * @param g Graphics2D which will handle the drawing.
     */
    public void drawClass(Graphics2D g) {
        Color classColor = this.color;
        g.setFont(new Font("Arial", Font.BOLD, this.textSize));
        FontMetrics fm = g.getFontMetrics(g.getFont());
        if (getSelected()) {
            classColor = this.selectedColor;
        }
        g.setStroke((this.inJoins.isEmpty() && this.outJoins.isEmpty()) ? this.dashedStroke : new BasicStroke(1));
        g.setColor(this.background);
        this.width = this.getMaximumWidth(fm);
        this.height = this.getMaximumHeight(fm);
        g.fillRect(x - this.width / 2, this.y - this.height / 2, this.width, this.height);
        g.setColor(classColor);

        Point nameLinePoint = new Point(this.width / 2, this.height / 2 - fm.getHeight());

        g.drawLine(x - nameLinePoint.x, y - nameLinePoint.y, x + nameLinePoint.x, y - nameLinePoint.y);
        g.drawLine(
                x - nameLinePoint.x,
                y - nameLinePoint.y + (this.variables.size() * fm.getHeight()) + 5,
                x + nameLinePoint.x,
                y - nameLinePoint.y + (this.variables.size() * fm.getHeight()) + 5
        );
        g.drawRect(x - this.width / 2, this.y - this.height / 2, this.width, this.height);
        g.setColor(Color.BLACK);
        if (typeOfClass != BT.ClassType.NONE && typeOfClass != BT.ClassType.INTERFACE) {
            String stringType = (typeOfClass == BT.ClassType.ACTIVITY) ? "<Activity>" : "<Actor>";
            g.drawString(stringType, x - fm.stringWidth(stringType) / 2, y - height / 2 - 5);
            drawNoObjectString(g, "/no Use Case/");
        }
        g.drawString(name, x - (int) fm.getStringBounds(name, g).getWidth() / 2, y - this.height / 2 + fm.getHeight() - 2);
        Point variablesPlace = new Point(nameLinePoint.x, nameLinePoint.y - fm.getHeight());
        drawAttributes(g, this.variables, variablesPlace, fm);
        Point methodsPlace = new Point(variablesPlace.x, variablesPlace.y - (fm.getHeight() * this.variables.size()) - 5);
        drawAttributes(g, this.methods, methodsPlace, fm);
    }

    /**
     * Method for drawing attributes, either variables or methods.
     *
     * @param g Graphics2D that will have these attributes.
     * @param attribues either variables or methods.
     * @param nameLinePoint startPoint.
     * @param fm fontMetrics.
     */
    protected void drawAttributes(Graphics2D g, ArrayList<Attribute> attribues, Point nameLinePoint, FontMetrics fm) {
        for (Attribute attribute : attribues) {
            g.drawString(attribute.toString(), x - nameLinePoint.x + 5, y - nameLinePoint.y + (attribues.indexOf(attribute) * fm.getHeight()));
        }
    }

    /**
     * Method for fetching height based on name, variables and methods.
     *
     * @param fm FontMetrics.
     * @return int prefered height.
     */
    protected int getMaximumHeight(FontMetrics fm) {
        int textheight = fm.getHeight();
        int shapeHeight = textheight + (textheight * this.variables.size()) + (textheight * this.methods.size());
        return shapeHeight + 10;
    }

    /**
     * Method for fetching width based on name, variables and methods.
     *
     * @param fm FontMetrics.
     * @return int prefered width.
     */
    protected int getMaximumWidth(FontMetrics fm) {
        int shapewidth = fm.stringWidth(this.name);
        int variableMaxWidth = getMaximumStringLengthOfAttribute(this.variables, fm);
        shapewidth = (variableMaxWidth > shapewidth) ? variableMaxWidth : shapewidth;
        int methodWidth = getMaximumStringLengthOfAttribute(this.methods, fm);
        shapewidth = (methodWidth > shapewidth) ? methodWidth : shapewidth;
        return shapewidth + 10;
    }

    /**
     * Method for fetching length of longest attribute.
     *
     * @param attribues variables or methods.
     * @param fm FontMetrics.
     * @return int as maximum length.
     */
    protected int getMaximumStringLengthOfAttribute(ArrayList<Attribute> attribues, FontMetrics fm) {
        int maxWidth = 0;
        for (Attribute attribute : attribues) {
            int attributeWidth = fm.stringWidth(attribute.toString());
            if (attributeWidth > maxWidth) {
                maxWidth = attributeWidth;
            }
        }
        return maxWidth;
    }
}
