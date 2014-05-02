/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ObjectedOrientedPetriNet.places;

import BT.managers.CD.Attribute;
import BT.models.ActionModel;
import BT.models.LineModel;
import BT.models.MyArrayList;
import BT.modules.ClassDiagram.places.CDClass;
import BT.modules.ObjectedOrientedPetriNet.places.joinEdge.PNJoinEdgeController;
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
        this.action = new ActionModel();
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
        setObjectHeight(fm);
        setObjectWidth(fm);
        drawLine(g, fm);
        drawGuard(g, fm);
        drawAction(g, fm);
        g.drawString(name, x - this.width / 2, y + this.height / 2 + fm.getHeight() + 2);
        g.setColor(placeColor);
        g.drawRect(x - this.width / 2, y - this.height / 2, this.width, this.height);
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
     * Resize transition based on size of strings of guard and action.
     * First resize width by stringwidth of action, then compare stringwidth of guard and if it is higher, resize the 
     * whole transition.
     * @param fm FontMetrics of graphics2D.
     * @return this object.
     */
    protected PNTransition setObjectWidth(FontMetrics fm)
    {
        this.width = 10;
        if (this.action!=null)
        {
            this.width += fm.stringWidth(this.action.getActionAsString());
            this.width += 5;
        }
        if (this.guard!=null && !this.guard.equals(""))
        {
            if (fm.stringWidth(this.guard)>this.width)
            {
                this.width += fm.stringWidth(this.guard);
                this.width += 5;
            }
        }
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
        
        if (!this.action.isEmpty())
        {
            objectTall += fm.getHeight();
        }
        objectTall = (objectTall == 5)?objectTall+20:objectTall;
        this.height = objectTall;
        return this;
    }
    
    /**
     * 
     * @param g
     * @param fm
     * @return 
     */
    private PNTransition drawGuard(Graphics2D g, FontMetrics fm) 
    {
        g.drawString(this.guard, x-fm.stringWidth(this.guard)/2, y-this.height/2+fm.getHeight()-5);
        return this;
    }
    
    /**
     * 
     * @param g
     * @param fm
     * @return 
     */
    private PNTransition drawAction(Graphics2D g, FontMetrics fm) 
    {
        g.drawString(this.action.getActionAsString(), x-fm.stringWidth(this.action.getActionAsString())/2, y+this.height/2-fm.getHeight()+12);
        return this;
    }
    
    /**
     * Split action part of transition by := and first part (which is variable).
     * @return variable of action.
     */
    public String getActionVariable()
    {
        return (this.action.getVariable());
    }
    
    /**
     * Get all class variables and all variables fro each in join of transition.
     * @return MyArrayList<String>.
     */
    @Override
    public MyArrayList<String> getVariables()
    {
        MyArrayList<String> allVariables = new MyArrayList<>();
        allVariables.add(this.action.getVariable());
        for (LineModel oneInJoin : this.inJoins) {
            allVariables.addAllUnique(((PNPlace)oneInJoin.getFirstObject()).getVariables());
        }
        return allVariables;
    }
}
