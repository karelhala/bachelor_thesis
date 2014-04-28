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
     *
     * @param variable
     */
    public ActionModel(String variable) {
        this.variable = variable;
    }

    /**
     * Basic constructor, that lets you assign change attributes later on.
     */
    public ActionModel() {
    }

    /**
     * Contructor for creating variable and basic action.
     *
     * @param variable name of action variable.
     * @param basicAction string that contains action.
     */
    public ActionModel(String variable, String basicAction) {
        this(variable);
        this.basicAction = basicAction;
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
     * Get action as joined String.
     *
     * @return action as String.
     */
    public String getActionAsString() {
        String actionString = "";
        if (this.variable != null) {
            actionString = this.variable;
        }
        if (this.assignedClass != null && this.assignedMethod != null) {
            actionString += " := " + this.assignedClass.getName();
            actionString += "." + this.assignedMethod.getName();
        } else if (this.basicAction != null) {
            if (this.variable != null) {
                actionString += " := ";
            }
            actionString += this.basicAction;
        }
        return actionString;
    }

    /**
     * Check if any part of action is empty.
     *
     * @return true or false.
     */
    public Boolean isEmpty() {
        Boolean empty = false;
        if (this.variable == null || this.variable.equals("")) {
            empty = true;
        }

        if (empty && this.assignedClass != null) {
            empty = false;
        }

        if (empty && (this.basicAction != null && !this.basicAction.equals(""))) {
            empty = false;
        }
        return empty;
    }
}
