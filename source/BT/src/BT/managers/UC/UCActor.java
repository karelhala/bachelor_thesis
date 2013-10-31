/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.managers.UC;

import BT.managers.CoordinateManager;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

/**
 *
 * @author Karel
 */
public class UCActor extends CoordinateManager{
    private Color actorColor;
    private int width;
    private int height;
    private String name;
    private int textSize;
    
    public UCActor (Color color)
    {
        super();
        this.actorColor = color;
    }
    
    public UCActor ()
    {
        super();
        this.width = 30;
        this.height = 60;
        this.actorColor = Color.blue;
        this.name = "Default";
        this.textSize = 15;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void drawActor(Graphics2D g, int x, int y)
    {
        g.setColor(this.actorColor);
        int middle = this.height/2;
        int bottom = middle/2;
        int neck = bottom/2;
        int arm = width/2;
        int headSize = arm/2;
        g.drawLine(x, y, x, y-neck);
        g.drawLine(x, y, x-arm, y);
        g.drawLine(x, y, x+arm, y);
        g.drawLine(x, y, x, y+bottom);
        g.drawLine(x, y+bottom, x-arm, y+bottom+bottom);
        g.drawLine(x, y+bottom, x+arm, y+bottom+bottom);
        g.drawOval(x-neck, y-bottom-headSize, headSize*2, headSize*2);
        g.setFont(new Font("Arial", Font.BOLD, this.textSize));
        g.setColor(Color.black);
        g.drawString(this.name, x-arm-this.textSize, y+bottom+bottom+this.textSize);
    }
    
    public void setColor(Color color)
    {
        this.actorColor = color;
    }
    
    public boolean isActor(int x, int y)
    {
        int isX=Math.abs(x-this.x);
        int isY=Math.abs(y-this.y);
        if (isX<=this.width/2 && isY<=this.height/2){
            return true;
        }
        return false;
    }

    public void setBasicColor() {
        this.actorColor = Color.blue;
    }
}
