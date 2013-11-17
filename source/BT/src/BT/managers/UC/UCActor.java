/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.managers.UC;

import BT.managers.CoordinateManager;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.Objects;
import java.util.UUID;

/**
 *
 * @author Karel Hala
 */
public class UCActor extends CoordinateManager{
    
    private int objectWidth;
    private int objectHeight;
    private int textSize;
    private int gap;
    private UUID id;
    
    public UCActor ()
    {
        super();
        this.selectedColor = Color.GREEN;
        this.width = 30;
        this.height = 60;
        this.basicColor = Color.BLUE;
        this.name = "Default";
        this.textSize = 15;
        this.gap = 2;
        this.id = UUID.randomUUID();
    }
    
    public void drawActor(Graphics2D g, Color actorColor)
    {
        g.setFont(new Font("Arial", Font.BOLD, this.textSize));
        FontMetrics fm = g.getFontMetrics(g.getFont());
        this.objectHeight = this.getHeight() + textSize + gap;
        this.objectWidth = this.getWidth() + fm.stringWidth(this.name) + gap - textSize;
        g.setColor(Color.WHITE);
        g.fillRect(this.x-getMax(this.objectWidth, this.width)/2+this.gap, this.y-this.height/2+this.gap, getMax(this.objectWidth, this.width)-this.gap*2, this.height+this.textSize-this.gap);
        int actorX = this.getX();
        int actorY = this.getY();
        g.setColor(actorColor);
        g.setStroke(new BasicStroke(2));
        int middle = this.getHeight()/2;
        int bottom = middle/2-this.gap;
        int neck = bottom/2;
        int arm = getWidth()/2-this.gap;
        int headSize = arm/2;
        g.drawLine(actorX, actorY, actorX, actorY-neck);
        g.drawLine(actorX, actorY, actorX-arm, actorY);
        g.drawLine(actorX, actorY, actorX+arm, actorY);
        g.drawLine(actorX, actorY, actorX, actorY+bottom);
        g.drawLine(actorX, actorY+bottom, actorX-arm, actorY+bottom+bottom);
        g.drawLine(actorX, actorY+bottom, actorX+arm, actorY+bottom+bottom);
        g.drawOval(actorX-neck, actorY-bottom-headSize, headSize*2, headSize*2);       
        g.setColor(Color.black);
        g.drawString(this.name, actorX-fm.stringWidth(this.name)/2, actorY+bottom+bottom+this.textSize);

    }
    
    public void drawSelectedActor(Graphics2D g, Color color)
    {
        int borderWidth = getMax(this.objectWidth, this.getWidth());
        int borderHeight = this.objectHeight;
        g.setStroke(new BasicStroke(1));
        g.setColor(Color.black);
        g.drawRect(this.x-borderWidth/2, this.y-this.getHeight()/2, borderWidth, borderHeight);
        drawActor(g, color);
    }
    
    public boolean isActor(int x, int y)
    {
        int isX=Math.abs(x-this.x);
        int isY=Math.abs(y-this.y);
        if (isX<=getMax(this.objectWidth, this.getWidth())/2 && isY<=getMax(this.objectHeight, this.getHeight())/2){
            return true;
        }
        return false;
    }

    public void setBasicColor() {
        this.basicColor = Color.blue;
    }
    
    private int getMax(int a, int b) {
        return (a>b?a:b);
    }
    
    @Override
    public boolean equals(Object other)
    {
        if (other instanceof UCActor)
        {
            UCActor object = (UCActor) other;
            if (this.hashCode()==object.hashCode())
                return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.id);
        return hash;
    }
    
}
