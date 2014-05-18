/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ObjectedOrientedPetriNet.places;

import BT.models.ActionModel;
import BT.models.MyArrayList;
import java.awt.Color;

/**
 *
 * @author Karel Hala
 */
public class PNTransitionModel extends PetriNetModel {

    /**
     * Stores guard string. If you want to use it further more, change this to desired type, probably arrayList<object>
     */
    protected String guard;

    /**
     * Stores action as action Model. If you want to use it further more, change this to desired type, probably
     * arrayList<object>
     */
    protected ActionModel action;

    /**
     * Basic constructor for creating model of petriNet transition. It will set X and Y based on arguments. It will set
     * width, height, selectecolor, basicColor, color, name, textSize, howerColor. It will create inJoin and outJoins
     * plus variables as MyArrayList.
     *
     * @param x coordinate X.
     * @param y coordinate Y.
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
        this.inJoins = new MyArrayList<>();
        this.outJoins = new MyArrayList<>();
        this.variables = new MyArrayList<>();
    }

    /**
     * Set guard string to guard.
     *
     * @param guard String that will be shown in guard field.
     */
    public void setGuard(String guard) {
        this.guard = guard;
    }

    /**
     * Set action string to action.
     *
     * @param action String that will be shown in action field.
     */
    public void setAction(ActionModel action) {
        this.action = action;
    }

    /**
     * Get guard string.
     *
     * @return string from guard field.
     */
    public String getGuard() {
        return guard;
    }

    /**
     * Returns action string.
     *
     * @return action string from action field.
     */
    public ActionModel getAction() {
        return action;
    }
}
