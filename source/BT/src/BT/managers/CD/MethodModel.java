/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BT.managers.CD;

import BT.BT;
import BT.BT.AttributeType;
import BT.managers.PlaceManager;
import BT.models.MyArrayList;
import BT.modules.ClassDiagram.places.CDClass;

/**
 * Model for method class.
 * Contains all variables their getters and adders.
 * @author Karel Hala
 */
public class MethodModel extends Attribute{
    /**
     * 
     */
    protected MyArrayList<Attribute> classAttributes;
    
    /**
     * 
     */
    protected MyArrayList<Attribute> classMethods;
    
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
        this.classAttributes = new MyArrayList<>();
        this.petriNet = new PlaceManager();
        this.assignedClass = assignedClass;
        this.classMethods = new MyArrayList<>();
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
    
    /**
     * 
     * @return 
     */
    public MyArrayList<Attribute> getClassAttributes() {
        return classAttributes;
    }
    
    /**
     * 
     * @return 
     */
    public PlaceManager getPetriNet() {
        return petriNet;
    }

    /**
     * 
     * @return 
     */
    public MyArrayList<Attribute> getClassMethods() {
        return classMethods;
    }

    /**
     * 
     * @return 
     */
    public CDClass getAssignedClass() {
        return assignedClass;
    }
}
