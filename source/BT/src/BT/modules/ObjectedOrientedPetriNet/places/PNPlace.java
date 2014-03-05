/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BT.modules.ObjectedOrientedPetriNet.places;

import BT.models.CoordinateModel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

/**
 *
 * @author Karel
 */
public class PNPlace extends CoordinateModel{
    private int textSize;
    
    public PNPlace ()
    {
        super();
    }
    
    /**
     * 
     * @param x
     * @param y 
     */
    public PNPlace (int x, int y)
    {
        super();
        this.x = x;
        this.y = y;
        this.height = 20;
        this.width = 20;
        this.selectedColor = Color.RED;
        this.basicColor = Color.BLACK;
        this.color = this.basicColor;
        this.name = "Default";
        this.textSize = 15;
        this.howerColor = Color.GREEN;
    }
    
    /**
     * TODO: refactor
     * @param g 
     */
    public void drawPlace(Graphics2D g)
    {
        Color placeColor = this.color;
        g.setFont(new Font("Arial", Font.BOLD, this.textSize));
        FontMetrics fm = g.getFontMetrics(g.getFont());
        if (getSelected())
        {
            placeColor = this.selectedColor;
        }
        g.setStroke(new BasicStroke(1));
        g.setColor(Color.BLACK);
        g.drawString(name, x-this.width/2, y+this.height/2+fm.getHeight()+2);
        
        g.setColor(placeColor);
        g.drawOval(x-this.width/2, y-this.height/2, this.width, this.height);
    }
    
}
