/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BT.modules.ClassDiagram.places;

import BT.BT;
import BT.BT.ClassType;
import BT.managers.CD.Attribute;
import BT.managers.PlaceManager;
import BT.models.CoordinateModel;
import BT.models.MyArrayList;
import BT.modules.ObjectedOrientedPetriNet.PetriNetPlaceManager;
import java.awt.Color;
import java.util.ArrayList;

/**
 * This class stores all data about class model in class diagram.
 * @author Karel Hala
 */
public class CDClassModel extends CoordinateModel{
    /**
     * Color of class background.
     */
    protected Color background;

    /**
     * Class variables are stored in this.
     */
    protected ArrayList<Attribute> variables;

    /**
     * Methods for this class are here.
     */
    protected ArrayList<Attribute> methods;

    /**
     * Defines types of class.
     */
    protected BT.ClassType typeOfClass;
    
    /**
     * Every class has petri net network.
     */
    protected PetriNetPlaceManager pnNetwork;
    
    /**
     * Basic constructor.
     * Selected color : RED.
     * Basic color : BLACK.
     * Normal color : basicColor.
     * Name : "Default".
     * TextSize : 15.
     * Background : Color(240, 209, 136).
     * HowerColor : GREEN.
     * New variables, method, inJoins, outJoins, pnNetwork.
     * TypeOfClass : NONE.
     * 
     * @param x coordinate X.
     * @param y coordinate Y.
     */
    public CDClassModel(int x, int y) {
        super();
        this.x = x;
        this.y = y;
        this.selectedColor = Color.RED;
        this.basicColor = Color.BLACK;
        this.color = this.basicColor;
        this.name = "Default";
        this.textSize = 15;
        this.background = new Color(240, 209, 136);
        this.howerColor = Color.GREEN;
        this.variables = new ArrayList<>();
        this.methods = new ArrayList<>();
        this.typeOfClass = BT.ClassType.NONE;
        this.inJoins = new MyArrayList<>();
        this.outJoins = new MyArrayList<>();
        this.pnNetwork = new PetriNetPlaceManager();
    }
    
    /**
     * Get type of class [Actor, Activity, None, Interface].
     * 
     * @return ClassType
     */
    public ClassType getTypeOfClass() {
        return typeOfClass;
    }
    
    /**
     * Set class type [Actor, Activity, None, Interface].
     * 
     * @param typeOfClass ClassType
     */
    public void setTypeOfClass(ClassType typeOfClass) {
        this.typeOfClass = typeOfClass;
    }

    /**
     * Get petrinet network for this class.
     * 
     * @return PetriNetPlaceManager.
     */
    public PetriNetPlaceManager getPnNetwork()
    {
        return this.pnNetwork;
    }

    /**
     * Get class's variables (No parent's variables)
     * 
     * @return ArrayList<Attribute> of variables.
     */
    public ArrayList<Attribute> getVariables() {
        return variables;
    }

    /**
     *  Get class's methods (No parent's methods).
     * 
     * @return ArrayList<Attribute> of methods.
     */
    public ArrayList<Attribute> getMethods() {
        return methods;
    }
    
    /**
     * Add new class variable.
     * 
     * @param newVariable Attribute.
     */
    public void addNewVariable(Attribute newVariable) {
        this.variables.add(newVariable);
    }

    /**
     * Add new method for this class.
     * 
     * @param newMethod Attribute.
     */
    public void addNewMethod(Attribute newMethod) {
        this.methods.add(newMethod);
    }

    /**
     * Remove method from class.
     * Method is remover based on method argument.
     * 
     * @param Attribute this method will be removed.
     */
    public void removeMethod(Attribute method)
    {
        this.methods.remove(method);
    }

    /**
     * Remove variable from class.
     * Variable is remover based on method argument.
     * 
     * @param Attribute this variable will be removed.
     */
    public void removeVariable(Attribute variable)
    {
        this.variables.remove(variable);
    }

    /**
     * Remove either method or class variable from class.
     * It will search in variables and methods and if method is found with this name, remove it.
     * Else remove variable.
     * 
     * @param remmovingAttr method or variable to be removed.
     */
    public void removeAttribute(Attribute remmovingAttr)
    {
        if (this.methods.indexOf(remmovingAttr) != -1)
        {
            removeMethod(remmovingAttr);
        }
        else if (this.variables.indexOf(remmovingAttr) != -1)
        {
            removeVariable(remmovingAttr);
        }
    }
}
