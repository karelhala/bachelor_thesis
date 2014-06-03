/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.interfaces;

import javax.swing.JToggleButton;

/**
 * Class for creating toggle buttons located in pane.
 *
 * @author Karel Hala
 */
public interface ToggleButtonsInterface {

    /**
     * Method for fetching selected button.
     *
     * @return JtoggleButton that is selected.
     */
    public JToggleButton getSelectedButton();

    /**
     * Method for fetching button with desired name.
     *
     * @param name of button to be returned.
     * @return JtoggleButton with name.
     */
    public JToggleButton getButtonWithName(String name);
}
