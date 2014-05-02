/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BT.modules.ClassDiagram.places;

import BT.BT;
import BT.BT.AttributeType;
import BT.managers.CD.Attribute;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Karel Hala
 */
public class CDClassDrawer extends CDClassModel{
    
    public CDClassDrawer(int x, int y)       
    {
        super(x,y);
    }
    /**
     *
     * @param g
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
        if (typeOfClass != BT.ClassType.NONE && typeOfClass != BT.ClassType.INTERFACE)
        {
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
     *
     * @param g
     * @param attribues
     * @param nameLinePoint
     * @param fm
     */
    protected void drawAttributes(Graphics2D g, ArrayList<Attribute> attribues, Point nameLinePoint, FontMetrics fm) {
        for (Attribute attribute : attribues) {
            g.drawString(attribute.toString(), x - nameLinePoint.x + 5, y - nameLinePoint.y + (attribues.indexOf(attribute) * fm.getHeight()));
        }
    }

    /**
     *
     * @param fm
     * @return
     */
    protected int getMaximumHeight(FontMetrics fm) {
        int textheight = fm.getHeight();
        int shapeHeight = textheight + (textheight * this.variables.size()) + (textheight * this.methods.size());
        return shapeHeight + 10;
    }

    /**
     *
     * @param fm
     * @return
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
     *
     * @param attribues
     * @param fm
     * @return
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
