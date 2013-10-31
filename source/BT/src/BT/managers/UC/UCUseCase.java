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

/**
 *
 * @author Karel
 */
public class UCUseCase extends CoordinateManager{
    private Color useCaseColor;
    private int width;
    private int height;
    private String name;
    private int textSize;
    
    public UCUseCase (Color color)
    {
        super();
        this.useCaseColor = color;
    }
    
    public UCUseCase ()
    {
        super();
        this.width = 60;
        this.height = 30;
        this.useCaseColor = Color.ORANGE;
        this.name = "Default";
        this.textSize = 15;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void drawUseCase(Graphics2D g, int x, int y)
    {
        g.setFont(new Font("Arial", Font.BOLD, this.textSize));
        FontMetrics fm = g.getFontMetrics(g.getFont());
        System.out.println(fm.stringWidth(name));
        this.width = fm.stringWidth(name)+this.textSize*2;
        g.setColor(this.useCaseColor);
        g.fillOval(x-width/2, y-height/2, this.width, this.height);
        g.setColor(Color.red);
        g.drawOval(x-width/2, y-height/2, this.width, this.height);
        g.setColor(Color.black);
        g.drawString(this.name, x-width/2+this.textSize, y+this.textSize/2);
    }
    
    public void setColor(Color color)
    {
        this.useCaseColor = color;
    }
    
    public boolean isUseCase(int x, int y)
    {
        int isX=Math.abs(x-this.x);
        int isY=Math.abs(y-this.y);
        if (isX<=this.width/2 && isY<=this.height/2){
            return true;
        }
        return false;
    }

    public void setBasicColor() {
        this.useCaseColor = Color.ORANGE;
    }
}
