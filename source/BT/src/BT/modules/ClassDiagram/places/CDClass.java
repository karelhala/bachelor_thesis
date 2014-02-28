/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ClassDiagram.places;

import BT.managers.CD.Attribute;
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
public class CDClass extends CoordinateModel{
    private int textSize;
    private Color background;
    private ArrayList<Attribute> variables;
    private ArrayList<Attribute> methods;
    
    public CDClass ()
    {
        super();
    }
    
    /**
     * 
     * @param x
     * @param y 
     */
    public CDClass (int x, int y)
    {
        super();
        this.x = x;
        this.y = y;
        this.selectedColor = Color.RED;
        this.width = 120;
        this.height = 150;
        this.basicColor = Color.BLACK;
        this.color = this.basicColor;
        this.name = "Default";
        this.textSize = 15;
        this.background = new Color(240, 209, 136);
        this.howerColor = Color.GREEN;
        this.variables = new ArrayList<>();
        this.methods = new ArrayList<>();
    }
    
    /**
     * TODO: refactor
     * @param g 
     */
    public void drawClass(Graphics2D g)
    {
        Color classColor = this.color;
        g.setFont(new Font("Arial", Font.BOLD, this.textSize));
        FontMetrics fm = g.getFontMetrics(g.getFont());
        if (getSelected())
        {
            classColor = this.selectedColor;
        }
        g.setStroke(new BasicStroke(1));
        g.setColor(this.background);
        g.fillRect(x-this.width/2, this.y-this.height/2, this.width, this.height);
        g.setColor(classColor);

        Point nameLinePoint = new Point(this.width/2, this.height/2-fm.getHeight());
        
        g.drawLine(x-nameLinePoint.x, y-nameLinePoint.y, x+nameLinePoint.x, y-nameLinePoint.y);
        g.drawLine(
                x-nameLinePoint.x, 
                y-nameLinePoint.y+(this.variables.size() * fm.getHeight()) + 5,
                x+nameLinePoint.x, 
                y-nameLinePoint.y+(this.variables.size() * fm.getHeight()) + 5 
        );
        g.drawRect(x-this.width/2, this.y-this.height/2, this.width, this.height);
        g.setColor(Color.BLACK);
        g.drawString(name, x-(int) fm.getStringBounds(name, g).getWidth()/2, y-this.height/2+fm.getHeight()-2);
        Point variablesPlace = new Point(nameLinePoint.x, nameLinePoint.y - fm.getHeight());
        drawAttributes(g, this.variables, variablesPlace, fm);
        Point methodsPlace = new Point(variablesPlace.x, variablesPlace.y-(fm.getHeight() * this.variables.size())-5);
        drawAttributes(g, this.methods, methodsPlace, fm);
    }
    
    /**
     *
     * @return
     */
    @Override
    public int getHeight()
    {
        return this.height;
    }
    
    private int getLineY()
    {
        return -10;
    }
    
    public void addNewVariable(Attribute newVariable)
    {
        this.variables.add(newVariable);
    }
    
    public void addNewMethod(Attribute newMethod)
    {
        this.methods.add(newMethod);
    }

    private void drawAttributes(Graphics2D g, ArrayList<Attribute> attribues, Point nameLinePoint, FontMetrics fm) {
        for (Attribute attribute : attribues) {
            g.drawString(attribute.getName() + ":" + attribute.getType(), x-nameLinePoint.x + 5, y-nameLinePoint.y+(attribues.indexOf(attribute) * fm.getHeight()));
        }
    }
}
