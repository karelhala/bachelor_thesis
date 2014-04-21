/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BT.modules.ObjectedOrientedPetriNet.places;

import BT.models.CoordinateModel;
import BT.models.MyArrayList;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Karel Hala
 */
public class PNPlaceModel extends CoordinateModel{

    protected MyArrayList<String> variables;
    
    /**
     *
     * @param x
     * @param y
     */
    public PNPlaceModel(int x, int y) {
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
        this.inJoins = new ArrayList<>();
        this.outJoins = new ArrayList<>();
        this.variables = new MyArrayList<>();
    }
    
    /**
     * Add new variable to place.
     * @param variableName variable's name.
     */
    public void addVariable(String variableName)
    {
        this.variables.add(variableName);
    }
    
    /**
     * Get all variable as ArrayList<String>
     * @return ArrayList<String>
     */
    public ArrayList<String> getVariable()
    {
        return this.variables;
    }
    
    /**
     * Remove desired variable from variables.
     * @param variableName remove variable with this name.
     */
    public void removeVariable(String variableName)
    {
        this.variables.remove(variableName);
    }
}
