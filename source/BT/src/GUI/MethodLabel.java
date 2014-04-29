/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import BT.managers.CD.Method;
import BT.managers.PlaceManager;
import BT.modules.ClassDiagram.places.CDClass;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import org.w3c.dom.events.MouseEvent;

/**
 *
 * @author Karel
 */
public class MethodLabel extends JLabel{
    
    /**
     * 
     */
    private final Method objectMethod;
    
    /**
     * 
     */
    private final CDClass selectedClass;
    
    /**
     * 
     */
    private Boolean selected;
    
    /**
     * 
     * @param name
     * @param objectMethod 
     */
    public MethodLabel(String name, Method objectMethod)
    {
        super(name);
        this.objectMethod = objectMethod;
        selectedClass = null;
        this.selected = false;
    }
    
    /**
     * 
     * @param name
     * @param selectedClass 
     */
    public MethodLabel(String name, CDClass selectedClass)
    {
        super(name);
        this.selectedClass = selectedClass;
        objectMethod = null;
        this.selected = false;
    }

    public Method getObjectMethod() {
        return objectMethod;
    }

    public CDClass getSelectedClass() {
        return selectedClass;
    }

    public Boolean isSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
    
    public void selectAndChangeColor(Boolean selected)
    {
        setSelected(selected);
        setBasicColor();
    }
    
    /**
     * 
     */
    public void setBasicColor()
    {
        if (this.selected)
        {
            this.setForeground(Color.yellow);
        }
        else
        {
            this.setForeground(Color.black);
        }
    }
    
    /**
     * Method that will return either pn network from class or method.
     * It will check if class is null, if so return method's pn network
     * if not, return class's network.
     * @return PlaceManager of class or method.
     */
    public PlaceManager getPetriNetFromClassOrMethod()
    {
        if (this.selectedClass == null)
        {
            return objectMethod.getPetriNet();
        }
        return selectedClass.getPnNetwork();
    }
}
