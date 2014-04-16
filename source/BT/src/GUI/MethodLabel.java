/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import BT.managers.CD.Method;
import BT.managers.PlaceManager;
import BT.modules.ClassDiagram.places.CDClass;
import javax.swing.JLabel;

/**
 *
 * @author Karel
 */
public class MethodLabel extends JLabel{
    private final Method objectMethod;
    private final CDClass selectedClass;
    
    public MethodLabel(String name, Method objectMethod)
    {
        super(name);
        this.objectMethod = objectMethod;
        selectedClass = null;
    }
    
    public MethodLabel(String name, CDClass selectedClass)
    {
        super(name);
        this.selectedClass = selectedClass;
        objectMethod = null;
    }

    public Method getObjectMethod() {
        return objectMethod;
    }

    public CDClass getSelectedClass() {
        return selectedClass;
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
