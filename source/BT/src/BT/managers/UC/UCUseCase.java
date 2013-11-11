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
public class UCUseCase extends CoordinateManager{
    private Color useCaseColor;
    private int width;
    private int height;
    private int textSize;
    private int objectWidth;
    private int objectHeight;
    private int gap;
    private UUID id;
    
    public UCUseCase ()
    {
        super();
        this.width = 60;
        this.height = 30;
        this.useCaseColor = Color.ORANGE;
        this.name = "Default";
        this.textSize = 15;
        this.gap = 2;
        this.id = UUID.randomUUID();
    }
    
    public void drawUseCase(Graphics2D g)
    {
        g.setStroke(new BasicStroke(2));
        g.setFont(new Font("Arial", Font.BOLD, this.textSize));
        FontMetrics fm = g.getFontMetrics(g.getFont());
        this.width = fm.stringWidth(name)+this.textSize*2;
        g.setColor(this.useCaseColor);
        g.fillOval(this.x-width/2, y-height/2, this.width, this.height);
        g.setColor(Color.red);
        g.drawOval(this.x-width/2, y-height/2, this.width, this.height);
        g.setColor(Color.black);
        g.drawString(this.name, this.x-width/2+this.textSize, y+this.textSize/2);
        this.objectHeight = this.height + gap;
        this.objectWidth = this.width + gap;
    }
    
    public boolean isUseCase(int x, int y)
    {
        int isX=Math.abs(x-this.x);
        int isY=Math.abs(y-this.y);
        if (isX<=getMax(this.objectWidth, this.width)/2 && isY<=getMax(this.objectHeight, this.height)/2){
            return true;
        }
        return false;
    }

    public void setBasicColor() {
        this.useCaseColor = Color.ORANGE;
    }
    
    public void drawSelectedUseCase(Graphics2D g, Color color)
    {
        this.useCaseColor = color;
        drawUseCase(g);
        int borderWidth = getMax(this.objectWidth, this.width);
        int borderHeight = this.objectHeight;
        g.setStroke(new BasicStroke(1));
        g.setColor(Color.black);
        g.drawRect(this.x-borderWidth/2-this.gap, this.y-this.height/2-this.gap, borderWidth+this.gap, borderHeight+this.gap);
    }
    
    private int getMax(int a, int b) {
        return (a>b?a:b);
    }
    
        @Override
    public boolean equals(Object other)
    {
        if (other instanceof UCUseCase)
        {
            UCUseCase object = (UCUseCase) other;
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
