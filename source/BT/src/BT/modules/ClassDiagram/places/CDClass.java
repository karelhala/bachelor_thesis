/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ClassDiagram.places;

import BT.BT.ClassType;
import BT.managers.CD.Attribute;
import BT.managers.PlaceManager;
import BT.models.CoordinateModel;
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
public class CDClass extends CoordinateModel {
    /**
     *
     */
    protected Color background;

    /**
     *
     */
    protected ArrayList<Attribute> variables;

    /**
     *
     */
    protected ArrayList<Attribute> methods;

    /**
     *
     */
    protected ClassType typeOfClass;
    
    /**
     * 
     */
    private PlaceManager pnNetwork;

    public CDClass() {
        super();
    }
    
    /**
     *
     * @param x
     * @param y
     */
    public CDClass(int x, int y) {
        super();
        this.x = x;
        this.y = y;
        this.selectedColor = Color.RED;
        this.basicColor = Color.BLACK;
        this.color = this.basicColor;
        this.name = "Default";
        this.textSize = 15;
        this.background = new Color(240, 209, 136);
        this.howerColor = Color.GREEN;
        this.variables = new ArrayList<>();
        this.methods = new ArrayList<>();
        this.typeOfClass = ClassType.NONE;
        this.inJoins = new ArrayList<>();
        this.outJoins = new ArrayList<>();
        this.pnNetwork = new PlaceManager();
    }

    public ClassType getTypeOfClass() {
        return typeOfClass;
    }

    public void setTypeOfClass(ClassType typeOfClass) {
        this.typeOfClass = typeOfClass;
    }
    
    public PlaceManager getPnNetwork()
    {
        return this.pnNetwork;
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
        if (typeOfClass != ClassType.NONE && typeOfClass != ClassType.INTERFACE)
        {
            String stringType = (typeOfClass == ClassType.ACTIVITY) ? "<Activity>" : "<Actor>";
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
     * @param newVariable
     */
    public void addNewVariable(Attribute newVariable) {
        this.variables.add(newVariable);
    }

    /**
     *
     * @param newMethod
     */
    public void addNewMethod(Attribute newMethod) {
        this.methods.add(newMethod);
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
            String visibility = "";
            if (attribute.getVisibility() == BT.BT.AttributeType.PRIVATE) {
                visibility = "-";
            } else if (attribute.getVisibility() == BT.BT.AttributeType.PUBLIC) {
                visibility = "+";
            } else if (attribute.getVisibility() == BT.BT.AttributeType.PROTECTED) {
                visibility = "#";
            }
            g.drawString(visibility + attribute.getName() + ":" + attribute.getType(), x - nameLinePoint.x + 5, y - nameLinePoint.y + (attribues.indexOf(attribute) * fm.getHeight()));
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
        return ((shapeHeight + 10) > this.height) ? shapeHeight + 10 : this.height;
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
        return ((shapewidth + 10) > this.width) ? shapewidth + 10 : this.width;
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
            int attributeWidth = fm.stringWidth(" " + attribute.getName() + ":" + attribute.getType());
            if (attributeWidth > maxWidth) {
                maxWidth = attributeWidth;
            }
        }
        return maxWidth;
    }
}
