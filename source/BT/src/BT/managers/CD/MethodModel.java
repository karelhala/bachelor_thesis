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
     * Attributes loaded from class.
     */
    protected MyArrayList<Attribute> classAttributes;
    
    /**
     * Methods loaded from class.
     */
    protected MyArrayList<Attribute> classMethods;
    
    /**
     * CDClass that has this method.
     */
    protected final CDClass assignedClass;
    
    /**
     * PlaceManager for drawing places of petrinet for this method.
     */
    protected final PlaceManager petriNet;
    
    /**
     * ArrayList that holds every attribute.
     */
    protected final MyArrayList<String> attributes;

    /**
     * Contructor for creating basic method model.
     * @param visibility AttributeType public, private, protected.
     * @param name String name of this method.
     * @param type String type of this method.
     * @param assignedClass CDClass assigned class.
     */
    public MethodModel(AttributeType visibility, String name, String type, CDClass assignedClass) {
        super(visibility, name, type);
        this.classAttributes = new MyArrayList<>();
        this.petriNet = new PlaceManager();
        this.assignedClass = assignedClass;
        this.classMethods = new MyArrayList<>();
        this.attributes = new MyArrayList<>();
    }
    
    /**
     * Contructor for creating private method.
     * @param name String name of this method.
     * @param type String type of this method.
     * @param assignedClass CDClass assigned class.
     */
    public MethodModel(String name, String type, CDClass assignedClass)
    {
        this(BT.AttributeType.PRIVATE, name, type, assignedClass);
    }
    
    /**
     * Getter for fetching classAttributes as arrayList.
     * @return MyArrayList<Attribute>.
     */
    public MyArrayList<Attribute> getClassAttributes() {
        return classAttributes;
    }
    
    /**
     * Getter for fetching method's petrinet manager.
     * @return PlaceManager.
     */
    public PlaceManager getPetriNet() {
        return petriNet;
    }

    /**
     * Getter for fetching classMethods as arrayList.
     * @return MyArrayList<Attribute>.
     */
    public MyArrayList<Attribute> getClassMethods() {
        return classMethods;
    }

    /**
     * Getter for fetching assigned class to this method.
     * @return CDClass.
     */
    public CDClass getAssignedClass() {
        return assignedClass;
    }

    public MyArrayList<String> getAttributes() {
        return attributes;
    }
    
    /**
     * Add new unique attribute to method.
     * @param attribute String, name of attribute.
     */
    public void addNewAttribute(String attribute)
    {
        this.attributes.addUnique(attribute);
    }
    
    /**
     * Get methods attributes as joined string with columns as delimeter.
     * @return String of attribues delimed by columns.
     */
    public String getAttributesAsString()
    {
        String joinedAttribues = "";
        for (String attribute : attributes) {
            joinedAttribues += attribute;
            if (!attributes.isLast(attribute))
            {
                joinedAttribues += ", ";
            }
        }
        return joinedAttribues;
    }
}
