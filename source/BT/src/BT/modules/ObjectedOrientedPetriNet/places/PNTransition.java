/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ObjectedOrientedPetriNet.places;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

/**
 *
 * @author Karel
 */
public class PNTransition extends PNTransitionModel {

    /**
     * Creates transition at coordinates x=0, y=0
     */
    public PNTransition() {
        this(0,0);
    }

    /**
     * Creates transition at passed x and y coordinates.
     * @param x
     * @param y
     */
    public PNTransition(int x, int y) {
        super(x, y);
        this.guard = "";
        this.action = "";
    }

    /**
     * Draws transition on graphics.
     * Helpfull when exporting, saving or just drawing.
     * @param g
     */
    public void drawTransition(Graphics2D g) {
        Color placeColor = this.color;
        g.setFont(new Font("Arial", Font.BOLD, this.textSize));
        FontMetrics fm = g.getFontMetrics(g.getFont());
        if (getSelected()) {
            placeColor = this.selectedColor;
        }
        g.setStroke((this.inJoins.isEmpty() && this.outJoins.isEmpty()) ? this.dashedStroke : new BasicStroke(2));
        g.setColor(Color.BLACK);
        g.drawString(name, x - this.width / 2, y + this.height / 2 + fm.getHeight() + 2);

        g.setColor(placeColor);
        g.drawRect(x - this.width / 2, y - this.height / 2, this.width, this.height);
        
        setObjectHeight(fm);
        drawLine(g, fm);
        drawGuard(g, fm);
        drawAction(g, fm);
    }
    
    /**
     * Draw line between guard and action.
     * @param g drawing plate.
     * @param fm font metrics.
     * @return 
     */
    private PNTransition drawLine(Graphics2D g, FontMetrics fm)
    {
        int yCoord;
        if ("".equals(this.guard))
        {
             yCoord = y-height/2+5;
        }
        else
        {
            yCoord = y-height/2+fm.getHeight()+2;
        }
        g.drawLine(x-width/2+5, yCoord, x+width/2-5, yCoord);
        return this;
    }
    
    /**
     * Add string heights so transition can be taller.
     * @param fm 
     */
    private PNTransition setObjectHeight(FontMetrics fm)
    {
        int objectTall = 5;
        if (!"".equals(this.guard))
        {
            objectTall += fm.getHeight() + 5;
        }
        
        if (!"".equals(this.action))
        {
            objectTall += fm.getHeight();
        }
        this.height = objectTall;
        return this;
    }
    
    private PNTransition drawGuard(Graphics2D g, FontMetrics fm) 
    {
        g.drawString(this.guard, x-fm.stringWidth(this.guard)/2, y-this.height/2+fm.getHeight()-5);
        return this;
    }
    
    private PNTransition drawAction(Graphics2D g, FontMetrics fm) 
    {
        g.drawString(this.action, x-fm.stringWidth(this.action)/2, y+this.height/2-fm.getHeight()+12);
        return this;
    }
}
