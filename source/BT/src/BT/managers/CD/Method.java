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
     * Contructor for creating new method with visibility, name, type and assignedClass.
     * @param visibility AttributeType public, private or protected.
     * @param name String with method name.
     * @param type String with type of class.
     * @param assignedClass CDCLass clsass that has this method.
     */
    public Method(AttributeType visibility, String name, String type, CDClass assignedClass) {
        super(visibility, name, type, assignedClass);
    }
    
    /**
     * Constructor for creating private method.
     * @param name String with method name.
     * @param type String with type of class.
     * @param assignedClass CDCLass clsass that has this method.
     */
    public Method(String name, String type, CDClass assignedClass)
    {
        this(AttributeType.PRIVATE, name, type, assignedClass);
    }
    
    /**
     * Remove class attribute (class variable).
     * @param selectedAttribute Attribute that you want to remove.
     */
    public void removeAttribute(Attribute selectedAttribute)
    {
        this.classAttributes.remove(selectedAttribute);
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
    
    /**
     * Method for fetching method as string.
     * @return String will be visibility name(arguments):type.
     */
    @Override
    public String toString()
    {
        return ((this.visibility == AttributeType.PRIVATE)?"-":(this.visibility == AttributeType.PUBLIC)?"+":"#") + " " + this.name + "(" + getAttributesAsString() + ")" + ":" + this.type;
    }
    
    /**
     * Get method of class styled for printing.
     * @return visibility name(attributes):type
     */
    @Override
    public String getAttributeStyled()
    {
        return this.visibility.name() + " " + this.name + "(" + this.getAttributesAsString() + ")" + ":" + this.type;
    }
}
