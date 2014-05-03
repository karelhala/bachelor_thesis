/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BT.modules.ObjectedOrientedPetriNet.places;

import BT.models.ActionModel;
import BT.models.CoordinateModel;
import BT.models.MyArrayList;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Karel Hala
 */
public class PNTransitionModel extends PetriNetModel{
    
    /**
     * Stores guard string.
     * If you want to use it further more, change this to desired type, probably arrayList<object>
     */
    protected String guard;
    
    /**
     * Stores action as action Model.
     * If you want to use it further more, change this to desired type, probably arrayList<object>
     */
    protected ActionModel action;

    /**
     *
     * @param x
     * @param y
     */
    public PNTransitionModel(int x, int y) {
        super();
        this.x = x;
        this.y = y;
        this.height = 5;
        this.width = 100;
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

    public void setGuard(String guard) {
        this.guard = guard;
    }

    public void setAction(ActionModel action) {
        this.action = action;
    }
    
    public String getGuard() {
        return guard;
    }

    public ActionModel getAction() {
        return action;
    }
}
