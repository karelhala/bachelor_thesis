/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BT.modules.ObjectedOrientedPetriNet.places;

import BT.models.CoordinateModel;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Karel
 */
public class PNTransition extends CoordinateModel{
    private int textSize;
    
    public PNTransition ()
    {
        super();
    }
    
    /**
     * 
     * @param x
     * @param y 
     */
    public PNTransition (int x, int y)
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
        
    }
}
