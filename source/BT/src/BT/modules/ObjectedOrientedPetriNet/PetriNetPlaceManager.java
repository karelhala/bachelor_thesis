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
 *
 * @author Karel Hala
 */
public class PetriNetPlaceManager extends PlaceManager{
    /**
     * If petriNet is for class, thiw will be referenced class.
     */
    private CDClass assignedClass;
    
    /**
     * If petriNet is for method, thiw will be referenced method.
     */
    private Method assignedmethod;

    public CDClass getAssignedClass() {
        return assignedClass;
    }

    public void setAssignedClass(CDClass assignedClass) {
        this.assignedClass = assignedClass;
    }

    public Method getAssignedmethod() {
        return assignedmethod;
    }

    public void setAssignedmethod(Method assignedmethod) {
        this.assignedmethod = assignedmethod;
    }
    
    /**
     * Get either name of class or method.
     * @return name of method if it is not null or class if it is not null.
     */
    public String getNameOfPetriNet()
    {
        if (this.assignedmethod != null)
        {
            return this.assignedmethod.getName()+ "Method";
        }
        return this.assignedClass.getName()+ "Class";
    }
}
