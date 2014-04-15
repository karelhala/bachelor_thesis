/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import BT.managers.CD.Method;
import javax.swing.JLabel;

/**
 *
 * @author Karel
 */
public class MethodLabel extends JLabel{
    private final Method objectMethod;    
    
    public MethodLabel(Method objectMethod)
    {
        this.objectMethod = objectMethod;
    }

    public Method getObjectMethod() {
        return objectMethod;
    }
}
