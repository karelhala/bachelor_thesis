/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BT.managers.CD;

import BT.BT;
import BT.BT.AttributeType;
import BT.managers.PlaceManager;
import java.util.ArrayList;

/**
 * Class that holds information about method.
 * It has petrinet describing it's behavior and attributes that are shared with 
 * object. These attributes are object's private, public and protected and
 * parent classes attributes.
 * @author Karel Hala
 */
public class Method extends Attribute{
    private final ArrayList<Attribute> classAttributes;
    
    private final PlaceManager petriNet;

    public Method(BT.AttributeType visibility, String name, String type) {
        super(visibility, name, type);
        this.classAttributes = new ArrayList<>();
        this.petriNet = new PlaceManager();
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
    
    public Method removeAttribute(Attribute selectedAttribute)
    {
        this.classAttributes.remove(selectedAttribute);
        return this;
    }
    
    public ArrayList<Attribute> getClassAttributes() {
        return classAttributes;
    }
    
    public PlaceManager getPetriNet() {
        return petriNet;
    }
}
