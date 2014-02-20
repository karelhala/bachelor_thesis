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
        this.width = 100;
        this.height = 60;
        this.basicColor = Color.BLACK;
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
        Color classColor = this.color;
        g.setFont(new Font("Arial", Font.BOLD, this.textSize));
        FontMetrics fm = g.getFontMetrics(g.getFont());
        g.setColor(Color.white);
        
//        if (getSelected())
//        {
//            classColor = this.selectedColor;
//        }
        g.setStroke(new BasicStroke(1));
        g.setColor(classColor);
        g.drawRect(x-this.width/2, this.y-this.height/2, this.width, this.height);
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
}
