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
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Karel Hala
 */
public class CDClassModel extends CoordinateModel{
    /**
     *
     */
    protected Color background;

    /**
     *
     */
    protected ArrayList<Attribute> variables;

    /**
     *
     */
    protected ArrayList<Attribute> methods;

    /**
     *
     */
    protected BT.ClassType typeOfClass;
    
    /**
     * 
     */
    private final PlaceManager pnNetwork;
    
    /**
     *
     * @param x
     * @param y
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
        this.inJoins = new ArrayList<>();
        this.outJoins = new ArrayList<>();
        this.pnNetwork = new PlaceManager();
    }
    
    public ClassType getTypeOfClass() {
        return typeOfClass;
    }

    public void setTypeOfClass(ClassType typeOfClass) {
        this.typeOfClass = typeOfClass;
    }
    
    public PlaceManager getPnNetwork()
    {
        return this.pnNetwork;
    }

    public ArrayList<Attribute> getVariables() {
        return variables;
    }

    public ArrayList<Attribute> getMethods() {
        return methods;
    }
    
    /**
     *
     * @param newVariable
     */
    public void addNewVariable(Attribute newVariable) {
        this.variables.add(newVariable);
    }

    /**
     *
     * @param newMethod
     */
    public void addNewMethod(Attribute newMethod) {
        this.methods.add(newMethod);
    }
    
    public void removeMethod(Attribute method)
    {
        this.methods.remove(method);
    }
    
    public void removeVariable(Attribute variable)
    {
        this.variables.remove(variable);
    }
    
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
