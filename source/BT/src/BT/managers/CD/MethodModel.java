/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BT.managers.CD;

import BT.BT;
import BT.BT.AttributeType;
import BT.managers.PlaceManager;
import BT.modules.ClassDiagram.places.CDClass;
import java.util.ArrayList;

/**
 * Model for method class.
 * Contains all variables their getters and adders.
 * @author Karel Hala
 */
public class MethodModel extends Attribute{
    /**
     * 
     */
    protected final ArrayList<String> classAttributes;
    
    /**
     * 
     */
    protected final ArrayList<String> classMethods;
    
    /**
     * 
     */
    protected final CDClass assignedClass;
    
    /**
     * 
     */
    protected final PlaceManager petriNet;

    /**
     *
     * @param visibility
     * @param name
     * @param type
     * @param assignedClass
     */
    public MethodModel(AttributeType visibility, String name, String type, CDClass assignedClass) {
        super(visibility, name, type);
        this.classAttributes = new ArrayList<>();
        this.petriNet = new PlaceManager();
        this.assignedClass = assignedClass;
        this.classMethods = new ArrayList<>();
    }
    
    /**
     * 
     * @param name
     * @param type
     * @param assignedClass 
     */
    public MethodModel(String name, String type, CDClass assignedClass)
    {
        this(BT.AttributeType.PRIVATE, name, type, assignedClass);
    }
    
    public ArrayList<String> getClassAttributes() {
        return classAttributes;
    }
    
    public PlaceManager getPetriNet() {
        return petriNet;
    }

    public ArrayList<String> getClassMethods() {
        return classMethods;
    }

    public CDClass getAssignedClass() {
        return assignedClass;
    }
}
