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
    private int objectWidth;
    private int objectHeight;
    private int textSize;
    private int gap;
    
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
        this.selectedColor = Color.GREEN;
        this.width = 30;
        this.height = 60;
        this.basicColor = Color.BLUE;
        this.color = this.basicColor;
        this.name = "Default";
        this.textSize = 15;
        this.gap = 2;
        this.howerColor = Color.red;
    }
    
    /**
     * TODO: refactor
     * @param g 
     */
    public void drawClass(Graphics2D g)
    {
        Color actorColor = this.color;
        g.setFont(new Font("Arial", Font.BOLD, this.textSize));
        FontMetrics fm = g.getFontMetrics(g.getFont());
        this.objectHeight = this.getHeight() +textSize;
        this.objectWidth = this.getWidth() + fm.stringWidth(this.name) + gap - textSize;
        g.setColor(Color.white);
        
        if (getSelected())
        {
            actorColor = this.selectedColor;
        }
        
        int actorX = this.getX();
        int actorY = this.getY();
        g.setColor(actorColor);
        g.setStroke(new BasicStroke(2));
        int middle = this.getHeight()/2;
        int bottom = (middle/2)-this.gap;
        int neck = bottom/2;
        int arm = getWidth()/2-this.gap;
        int headSize = arm/2;
        g.drawLine(actorX, actorY, actorX, actorY-neck-bottom);
        g.drawLine(actorX, actorY-bottom, actorX-arm, actorY-bottom);
        g.drawLine(actorX, actorY-bottom, actorX+arm, actorY-bottom);
        g.drawLine(actorX, actorY, actorX-arm, actorY+bottom);
        g.drawLine(actorX, actorY, actorX+arm, actorY+bottom);
        g.drawOval(actorX-neck, actorY-bottom-bottom-headSize, headSize*2, headSize*2);       
        g.setColor(Color.black);
        g.drawString(this.name, actorX-fm.stringWidth(this.name)/2, actorY+this.getHeight()/2);

    }
}
