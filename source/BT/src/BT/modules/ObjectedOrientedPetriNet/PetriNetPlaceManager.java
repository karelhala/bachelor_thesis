/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ObjectedOrientedPetriNet;

import BT.managers.CD.Method;
import BT.managers.PlaceManager;
import BT.modules.ClassDiagram.places.CDClass;

/**
 * Class that manage class that is assigned to this petriNet and method.
 * @author Karel Hala
 */
public class PetriNetPlaceManager extends PlaceManager {

    /**
     * If petriNet is for class, thiw will be referenced class.
     */
    private CDClass assignedClass;

    /**
     * If petriNet is for method, thiw will be referenced method.
     */
    private Method assignedmethod;

    /**
     * Rerutns assigned class to this PetriNet.
     * @return CDClass assigned class.
     */
    public CDClass getAssignedClass() {
        return assignedClass;
    }

    /**
     * Set assigned class to desired class.
     * @param assignedClass CDClass that will be assigned to this petriNet.
     */
    public void setAssignedClass(CDClass assignedClass) {
        this.assignedClass = assignedClass;
    }

    /**
     * Get method that is assigned to this petriNet.
     * @return Method that is assigned.
     */
    public Method getAssignedmethod() {
        return assignedmethod;
    }

    /**
     * Assign method to assignedmethod.
     * @param assignedmethod this Method will be assigned to this PetriNet.
     */
    public void setAssignedmethod(Method assignedmethod) {
        this.assignedmethod = assignedmethod;
    }

    /**
     * Get either name of class or method.
     *
     * @return name of method if it is not null or class if it is not null.
     */
    public String getNameOfPetriNet() {
        if (this.assignedmethod != null) {
            return this.assignedmethod.getName() + "Method";
        }
        return this.assignedClass.getName() + "Class";
    }
}
