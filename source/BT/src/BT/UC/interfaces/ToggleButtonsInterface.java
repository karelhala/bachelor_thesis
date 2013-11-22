/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.UC.interfaces;

import javax.swing.JToggleButton;

/**
 *
 * @author Karel Hala
 */
public interface ToggleButtonsInterface {
    public JToggleButton getSelectedButton();
    public JToggleButton getButtonByName(String name);
}
