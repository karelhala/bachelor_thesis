/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BT.managers.CD;

import BT.BT;
import BT.BT.AttributeType;
import java.util.ArrayList;

/**
 *
 * @author Karel
 */
public class Method extends Attribute{
    private final ArrayList<Attribute> classAttributes;

    public Method(BT.AttributeType visibility, String name, String type) {
        super(visibility, name, type);
        this.classAttributes = new ArrayList<>();
    }
    
    public Method(String name, String type)
    {
        this(AttributeType.PRIVATE, name, type);
    }
        
    public Method addNewAttribute(Attribute newAttribute)
    {
        classAttributes.add(newAttribute);
        return this;
    }
    
    public ArrayList<Attribute> getClassAttributes() {
        return classAttributes;
    }
}
