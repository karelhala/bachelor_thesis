/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.models;

import BT.managers.CD.Method;
import BT.modules.ClassDiagram.places.CDClass;

/**
 *
 * @author Karel Hala
 */
public class ActionModel {
    /**
     * Variable that has class for this action.
     */
    private CDClass assignedClass;
    
    /**
     * Variable that has method for this action.
     */
    private Method assignedMethod;
    
    /**
     * Result variable is stored in this.
     */
    private String variable;
    
    /**
     * If method is not found in assignedClass, store action in this.
     */
    private String basicAction;
    
    /**
     * Contructor for creating model with variable.
     * @param variable 
     */
    public ActionModel(String variable)
    {
        this.variable = variable;
    }
    
    /**
     * Basic constructor, that lets you assign change attributes later on.
     */
    public ActionModel()
    {
    }

    public CDClass getAssignedClass() {
        return assignedClass;
    }

    public void setAssignedClass(CDClass assignedClass) {
        this.assignedClass = assignedClass;
    }

    public Method getAssignedMethod() {
        return assignedMethod;
    }

    public void setAssignedMethod(Method assignedMethod) {
        this.assignedMethod = assignedMethod;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public String getBasicAction() {
        return basicAction;
    }

    public void setBasicAction(String basicAction) {
        this.basicAction = basicAction;
    }
    
    /**
     * Check if class was assigned and is not null.
     * Find method in class and if it is not null assign it to assignMethod,
     * if it is null set basicAction to stringMethod
     * @param stringAction name of assigned method or basicAction.
     */
    public void initializeCDClass(String stringAction)
    {
        if (this.assignedClass != null)
        {
            if (this.assignedMethod != null)
            {
                
            }
            else
            {
                this.assignedClass = null;
                this.basicAction = stringAction;
            }
        }
    }
    
    /**
     * Get action as joined String.
     * @return action as String.
     */
    public String getActionAsString()
    {
        String actionString = "";
        return actionString;
    }
}
