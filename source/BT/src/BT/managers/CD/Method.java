/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BT.managers.CD;

import BT.BT.AttributeType;
import BT.modules.ClassDiagram.places.CDClass;

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
    
    public Method removeAttribute(Attribute selectedAttribute)
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
        this.classMethods = this.assignedClass.loadClassMethods();
        return this;
    }
    
    /**
     * Add class variables from assigned class and it's parents.
     * @return working method.
     */
    public Method loadClassAttributes()
    {
        this.classAttributes = this.assignedClass.loadClassAttributes();
        return this;
    }
}
