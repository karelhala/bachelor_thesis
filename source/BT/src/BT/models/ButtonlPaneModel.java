/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.models;

import BT.UC.interfaces.ToggleButtonsInterface;
import java.awt.Component;
import javax.swing.JToggleButton;

/**
 *
 * @author Karel Hala
 */
public class ButtonlPaneModel extends ContentPaneModel implements ToggleButtonsInterface{
    
    public ButtonlPaneModel()
    {
        super();
    }

    @Override
    public JToggleButton getSelectedButton() {
        for (Component comp : this.mainContentPane.getComponents())
        {
            JToggleButton toggleButton = (JToggleButton) comp;
            if (toggleButton.isSelected())
            {
                return toggleButton;
            }
        }
        return null;
    }

    /**
     * Method for getting button specified by it's name.
     * @param String name of button that will be returned
     * @return JToggleButton specified by it's name
     * @return null if no button is found
     */
    @Override
    public JToggleButton getButtonWithName(String name) {
        for (Component comp : this.mainContentPane.getComponents())
        {
            JToggleButton toggleButton = (JToggleButton) comp;
            if (toggleButton.getName() == null ? name == null : toggleButton.getName().equals(name))
            {
                return toggleButton;
            }
        }
        return null;
    }
    
}
