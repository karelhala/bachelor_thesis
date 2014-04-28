/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ClassDiagram.places;

import BT.BT;
import BT.BT.CDLineType;
import BT.managers.CD.Attribute;
import BT.managers.CD.Method;
import BT.models.LineModel;
import BT.models.MyArrayList;
import BT.modules.ClassDiagram.places.joinEdge.CDJoinEdgeController;

/**
 *
 * @author Karel Hala
 */
public class CDClass extends CDClassDrawer {
    
    /**
     * Contructor for creating new class at coordinates x and y
     * @param x
     * @param y
     */
    public CDClass(int x, int y) {
        super(x, y);
    }
    
    /**
     * Method that checks if class allready has parent class.
     * @return true if class has join to other class with type aggregation.
     */
    public boolean hasParent()
    {
        for (LineModel lineModel : outJoins) {
            CDJoinEdgeController joinEdge = (CDJoinEdgeController) lineModel;
            if (joinEdge.getJoinEdgeType() == CDLineType.GENERALIZATION)
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Method that will return parent class.
     * It will be returned only if class has parent.
     * @return 
     */
    public CDClass getParent() 
    {
        if (hasParent())
        {
            for (LineModel lineModel : outJoins) {
                CDJoinEdgeController joinEdge = (CDJoinEdgeController) lineModel;
                if (joinEdge.getJoinEdgeType() == CDLineType.GENERALIZATION)
                {
                    return (CDClass) lineModel.getSecondObject();
                }
            }
        }
        return null;
    }
    /**
     * Load private and protected methods from assigned class and it's parents.
     * @return MyArrayList<Attribute>
     */
    public MyArrayList<Attribute> loadClassMethods()
    {
        MyArrayList<Attribute> classMethods = new MyArrayList<>();
        classMethods.addAll(this.methods);

        if (this.hasParent())   
        {
            for(CDClass newClass = this.getParent();(newClass != null && !newClass.equals(this)); newClass = newClass.getParent())
            {
                for (Attribute method : newClass.getMethods()) {
                    if (method.getVisibility() != BT.AttributeType.PRIVATE)
                    {
                        classMethods.addUnique(method);
                    }
                }
            }
        }
        return classMethods;
    }
    
    /**
     * Add class variables from assigned class and it's parents.
     * @return MyArrayList<Attribute>
     */
    public MyArrayList<Attribute> loadClassAttributes()
    {
        MyArrayList<Attribute>classAttributes = new MyArrayList<>();
        classAttributes.addAll(this.variables);
        
        if (this.hasParent())   
        {
            for(CDClass newClass = this.getParent();(newClass != null && !newClass.equals(this)); newClass = newClass.getParent())
            {
                for (Attribute attribute : newClass.getVariables()) {
                    if (attribute.getVisibility() != BT.AttributeType.PUBLIC)
                    {
                        classAttributes.addUnique(attribute);
                    }
                }
            }
        }
        return classAttributes;
    }
    
    /**
     * Find method of class by its name.
     * It will loop through every method and return first one.
     * @param methodName name of method that is being searched for.
     * @return found method.
     */
    public Method getMethodByName(String methodName)
    {
        Method foundMethod = null;
        for (Attribute oneMethod : this.methods) {
            if (oneMethod.getName().equals(methodName))
            {
                foundMethod = (Method) oneMethod;
                break;
            }
        }
        return foundMethod;
    }
    
    /**
     * Get All methods of this class and it's parrents that are public.
     * @return MyArrayList<Attribute> list of public methods.
     */
    public MyArrayList<Attribute> getAllPublicMethods()
    {
        MyArrayList<Attribute>publicMethods = new MyArrayList<>();
        for(CDClass newClass = this;newClass != null; newClass = newClass.getParent())
        {
            for (Attribute method : newClass.getMethods()) {
                if (method.getVisibility() == BT.AttributeType.PUBLIC)
                {
                    publicMethods.addUnique(method);
                }
            }
        }
        return publicMethods;
    }
}
