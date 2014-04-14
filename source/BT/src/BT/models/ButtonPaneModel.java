/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.models;

import BT.interfaces.DrawingClicks;
import BT.interfaces.ToggleButtonsInterface;
import BT.modules.ClassDiagram.mainContent.CDMainContentController;
import BT.modules.ObjectedOrientedPetriNet.mainContent.PNMainContentController;
import BT.modules.UC.mainContent.UCMainContentController;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JToggleButton;

/**
 *
 * @author Karel Hala
 */
public class ButtonPaneModel extends ContentPaneModel implements ToggleButtonsInterface {

    DrawingClicks mainContentController;

    public ButtonPaneModel(UCMainContentController UCMain) {
        super();
        this.mainContentController = UCMain;
    }

    public ButtonPaneModel(CDMainContentController CDMain) {
        super();
        this.mainContentController = CDMain;
    }

    public ButtonPaneModel(PNMainContentController PNMain) {
        super();
        this.mainContentController = PNMain;
    }

    @Override
    public JToggleButton getSelectedButton() {
        for (Component comp : this.mainContentPane.getComponents()) {
            JToggleButton toggleButton = (JToggleButton) comp;
            if (toggleButton.isSelected()) {
                return toggleButton;
            }
        }
        return null;
    }

    /**
     * Method for getting button specified by it's name.
     *
     * @param name
     * @return JToggleButton specified by it's name
     */
    @Override
    public JToggleButton getButtonWithName(String name) {
        for (Component comp : this.mainContentPane.getComponents()) {
            JToggleButton toggleButton = (JToggleButton) comp;
            if (toggleButton.getName() == null ? name == null : toggleButton.getName().equals(name)) {
                return toggleButton;
            }
        }
        return null;
    }

    /**
     *
     */
    public void setListeners() {
        for (Component comp : this.mainContentPane.getComponents()) {
            final JToggleButton toggleButton = (JToggleButton) comp;
            ItemListener changeListener = new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent ev) {
                    if (ev.getStateChange() == ItemEvent.SELECTED) {
                        toggleButtonSelected(toggleButton);
                    }
                    mainContentController.buttonsChanged();
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
        for (Component comp : this.mainContentPane.getComponents()) {
            JToggleButton toggleButton = (JToggleButton) comp;
            if (selectedButton != toggleButton) {
                toggleButton.setSelected(false);
            }
        }
    }
}
