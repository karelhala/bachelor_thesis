/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.UC.places;

import BT.models.CoordinateModel;
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
public class UCUseCase extends CoordinateModel{

    private int textSize;
    private int gap;
    private UUID id;
    
    /**
     * TODO: make model
     */
    public UCUseCase (int x, int y)
    {
        super();
        this.x = x;
        this.y = y;
        this.selectedColor = Color.GRAY;
        this.width = 60;
        this.height = 30;
        this.basicColor = Color.ORANGE;
        this.color = this.basicColor;
        this.name = "Default";
        this.textSize = 15;
        this.gap = 2;
        this.howerColor = Color.green;
        this.id = UUID.randomUUID();
    }
    
    /**
     * TODO: refactor
     * @param g 
     */
    public void drawUseCase(Graphics2D g)
    {
        Color useCaseColor = this.color;
        g.setStroke(new BasicStroke(2));
        g.setFont(new Font("Arial", Font.BOLD, this.textSize));
        FontMetrics fm = g.getFontMetrics(g.getFont());
        this.width = fm.stringWidth(name)+this.textSize*2;
        this.objectHeight = this.height + gap;
        this.objectWidth = this.width + gap;
        if (this.selected)
        {
            useCaseColor = selectedColor;
            drawRectArroundUseCase(g);
        }
        g.setColor(useCaseColor);
        g.fillOval(this.x-width/2, y-height/2, this.width, this.height);
        g.setColor(Color.red);
        g.drawOval(this.x-width/2, y-height/2, this.width, this.height);
        g.setColor(Color.black);
        g.drawString(this.name, this.x-width/2+this.textSize, y+this.textSize/2);
    }
    
    /**
     * 
     * @param g 
     */
    public void drawRectArroundUseCase(Graphics2D g)
    {
        int borderWidth = getMax(this.objectWidth, this.width);
        int borderHeight = this.objectHeight;
        g.setStroke(new BasicStroke(1));
        g.setColor(Color.black);
        g.drawRect(this.x-borderWidth/2-this.gap, this.y-this.height/2-this.gap, borderWidth+this.gap, borderHeight+this.gap);
    }
    
    /**
     * 
     * @param other
     * @return 
     */
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

    /**
     * 
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.id);
        return hash;
    }
}
