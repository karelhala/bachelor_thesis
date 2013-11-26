/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.models;

import BT.interfaces.ToggleButtonsInterface;
import BT.modules.UC.mainContent.UCMainContentController;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JToggleButton;

/**
 *
 * @author Karel Hala
 */
public class ButtonPaneModel extends ContentPaneModel implements ToggleButtonsInterface{
    
    public ButtonPaneModel()
    {
        this(null);
    }
    
    public ButtonPaneModel(UCMainContentController UCMain)
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
    
    /**
     * 
     */
    public void setListeners()
    {
        for (Component comp : this.mainContentPane.getComponents())
        {
            final JToggleButton toggleButton = (JToggleButton) comp;
            ItemListener changeListener = new ItemListener(){
                @Override
                public void itemStateChanged(ItemEvent ev) {
                    if(ev.getStateChange()==ItemEvent.SELECTED){
                        toggleButtonSelected(toggleButton);
                    }
//                    UCMain.buttonsChanged();
                }
            };
            toggleButton.addItemListener(changeListener);
        }
    }
    
    /**
     * 
     * @param selectedButton
     * @param allComponents 
     */
    private void toggleButtonSelected(JToggleButton selectedButton) {
        for (Component comp : this.mainContentPane.getComponents())
        {
            JToggleButton toggleButton = (JToggleButton) comp;
            if (selectedButton != toggleButton)
            {
                toggleButton.setSelected(false);
            }
        }
    }
    
}
