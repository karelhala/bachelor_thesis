/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.managers.CD;

import BT.BT;
import BT.BT.AttributeType;

/**
 * Class for working with attributes of class on class diagram.
 * @author Karel
 */
public class Attribute {

    /**
     * Variable holding visibility of attribute.
     */
    private AttributeType visibility;
    
    /**
     * Variable holding name of attribute.
     */
    private String name;
    
    /**
     * Variable holding type of attribute
     */
    private String type;

    /**
     * Basic constructor
     * @param visibility set visibility to this value
     * @param name set name to this value
     * @param type set type to this value
     */ 
    public Attribute(AttributeType visibility, String name, String type) {
        this.visibility = visibility;
        this.name = name;
        this.type = type;
    }

    /**
     * Constructor that sets visibility to private.
     * @param name set name to this value
     * @param type set type to this value
     */
    public Attribute(String name, String type) {
	this(AttributeType.PRIVATE, name, type);
    }

    public void setVisibility(AttributeType visibility) {
        this.visibility = visibility;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BT.AttributeType getVisibility() {
        return visibility;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString()
    {
        return this.name;
    }
}
