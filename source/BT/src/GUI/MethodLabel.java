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
import javax.swing.JLabel;

/**
 * Class which holds labels describing class and method. It is used in OOPN bottom left part.
 * 
 * @author Karel Hala
 */
public class MethodLabel extends JLabel {

    /**
     * Method that is controlled by this class.
     */
    private final Method objectMethod;

    /**
     * CDClass that is controlled by this class.
     */
    private final CDClass selectedClass;

    /**
     * Defines if label is selected or not.
     */
    private Boolean selected;

    /**
     * Creates label with method assigned.
     * @param name string of label.
     * @param objectMethod method that will be assigned.
     */
    public MethodLabel(String name, Method objectMethod) {
        super(name);
        this.objectMethod = objectMethod;
        selectedClass = null;
        this.selected = false;
    }

    /**
     * Creates label with class assigned.
     * @param name string of label.
     * @param selectedClass class that will be assigned.
     */
    public MethodLabel(String name, CDClass selectedClass) {
        super(name);
        this.selectedClass = selectedClass;
        objectMethod = null;
        this.selected = false;
    }

    /**
     * Returns assigned method.
     * @return objectMethod as Method.
     */
    public Method getObjectMethod() {
        return objectMethod;
    }

    /**
     * Returns assigned CDclass.
     * @return selectedClass as CDClass.
     */
    public CDClass getSelectedClass() {
        return selectedClass;
    }

    /**
     * informs if label is selected.
     * @return true or false.
     */
    public Boolean isSelected() {
        return selected;
    }

    /**
     * Select or disselect label.
     * @param selected true or false.
     */
    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    /**
     * Method for selecting label and changing it color.
     * @param selected true or false.
     */
    public void selectAndChangeColor(Boolean selected) {
        setSelected(selected);
        setBasicColor();
    }

    /**
     * Method for drawing basic color, if selected then true, if not then black.
     */
    public void setBasicColor() {
        if (this.selected) {
            this.setForeground(Color.red);
        } else {
            this.setForeground(Color.black);
        }
    }

    /**
     * Method that will return either pn network from class or method. It will check if class is null, if so return
     * method's pn network if not, return class's network.
     *
     * @return PlaceManager of class or method.
     */
    public PlaceManager getPetriNetFromClassOrMethod() {
        if (this.selectedClass == null) {
            return objectMethod.getPetriNet();
        }
        return selectedClass.getPnNetwork();
    }
}
