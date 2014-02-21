/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ClassDiagram.places;

import BT.models.CoordinateModel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.UUID;

/**
 *
 * @author Karel Hala
 */
public class CDClass extends CoordinateModel{
    private int textSize;
    private Color background;
    
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

        g.drawLine(x-this.width/2, y-this.height/2+fm.getHeight(), x+this.width/2, y-this.height/2+fm.getHeight());
        g.drawLine(x-this.width/2, y-getLineY(), x+this.width/2, y-getLineY());
        g.drawRect(x-this.width/2, this.y-this.height/2, this.width, this.height);
        g.setColor(Color.BLACK);
        g.drawString(name, x-(int) fm.getStringBounds(name, g).getWidth()/2, y-this.height/2+fm.getHeight()-2);
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
}
