/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ObjectedOrientedPetriNet.places;

import BT.models.CoordinateModel;
import BT.models.MyArrayList;

/**
 *
 * @author Karel Hala
 */
public class PetriNetModel extends CoordinateModel {

    /**
     * Variables that comes in place.
     */
    protected MyArrayList<String> variables;

    /**
     * Add new variable to place.
     *
     * @param variableName variable's name.
     */
    public void addVariable(String variableName) {
        this.variables.addUnique(variableName);
    }

    /**
     * Get all variable as MyArrayList<String>
     *
     * @return MyArrayList<String>
     */
    public MyArrayList<String> getVariables() {
        return this.variables;
    }

    /**
     * Remove desired variable from variables.
     *
     * @param variableName remove variable with this name.
     */
    public void removeVariable(String variableName) {
        this.variables.remove(variableName);
    }

}
