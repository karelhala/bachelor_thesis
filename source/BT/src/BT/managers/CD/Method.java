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
 * Class that holds information about method.
 * It has petrinet describing it's behavior and attributes that are shared with 
 * object. These attributes are object's private, public and protected and
 * parent classes attributes.
 * @author Karel Hala
 */
public class Method extends MethodModel{

    /**
     *
     * @param visibility
     * @param name
     * @param type
     * @param assignedClass
     */
    public Method(AttributeType visibility, String name, String type, CDClass assignedClass) {
        super(visibility, name, type, assignedClass);
    }
    
    public Method(String name, String type, CDClass assignedClass)
    {
        this(AttributeType.PRIVATE, name, type, assignedClass);
    }
    
    public Method removeAttribute(String selectedAttribute)
    {
        this.classAttributes.remove(selectedAttribute);
        return this;
    }
    
    /**
     * Add methods from assigned class and it's parents.
     * @return working method.
     */
    public Method loadClassMethods()
    {
        for (Attribute method : assignedClass.getMethods()) {
            this.classMethods.add(method.getName());
        }
        
        if (assignedClass.hasParent())   
        {
            for(CDClass newClass = assignedClass.getParent();(newClass != null && !newClass.equals(assignedClass)); newClass = newClass.getParent())
            {
                for (Attribute method : newClass.getMethods()) {
                    if (method.getVisibility() != AttributeType.PRIVATE)
                    {
                        addUniqueMethod(method.getName());
                    }
                }
            }
        }
        return this;
    }
    
    /**
     * Add class variables from assigned class and it's parents.
     * @return working method.
     */
    public Method loadClassAttributes()
    {
        for (Attribute attribute : assignedClass.getVariables()) {
            this.classAttributes.add(attribute.getName());
        }
        
        if (assignedClass.hasParent())   
        {
            for(CDClass newClass = assignedClass.getParent();(newClass != null && !newClass.equals(assignedClass)); newClass = newClass.getParent())
            {
                for (Attribute attribute : newClass.getVariables()) {
                    if (attribute.getVisibility() != AttributeType.PRIVATE)
                    {
                        addUniqueAttribute(attribute.getName());
                    }
                }
            }
        }
        return this;
    }
    
    /**
     * Add only unique methods to array list of methods.
     * @param name 
     */
    private void addUniqueMethod(String name)
    {
        if (!this.classMethods.contains(name))
        {
            this.classMethods.add(name);
        }
    }
    
    /**
     * Add unique attribute to array list of attributes.
     * @param name 
     */
    public void addUniqueAttribute(String name)
    {
        if (!this.classAttributes.contains(name))
        {
            this.classAttributes.add(name);
        }
    }
}
