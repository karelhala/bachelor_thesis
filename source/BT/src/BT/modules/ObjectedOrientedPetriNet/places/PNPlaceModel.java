/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ObjectedOrientedPetriNet.places;

import BT.models.MyArrayList;
import java.awt.Color;

/**
 *
 * @author Karel Hala
 */
public class PNPlaceModel extends PetriNetModel {

    /**
     * Contstant that is in place object on drawing panel.
     */
    protected String constant;

    /**
     * Basic constructor. It sets X,Y, width, height, selected color, basic color, color, hower color. it creates new
     * inJoins MyArrayList and out joins MyArrayList plus variables MyArrayList.
     *
     * @param x coordinate X of object.
     * @param y coordinate Y of object.
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
        this.inJoins = new MyArrayList<>();
        this.outJoins = new MyArrayList<>();
        this.variables = new MyArrayList<>();
    }

    /**
     * Get constant string.
     *
     * @return String of constant.
     */
    public String getConstant() {
        return constant;
    }

    /**
     * Set constant string.
     *
     * @param constant
     */
    public void setConstant(String constant) {
        this.constant = constant;
    }
}
