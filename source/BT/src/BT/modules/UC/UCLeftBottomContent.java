/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.UC;

import BT.BT.LineType;
import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import BT.models.ContentPaneModel;

/**
 * Class for creating LeftBottom pane that holds 3 jtoggle buttons
 * @author Karel Hala
 */
public final class UCLeftBottomContent extends ContentPaneModel {
    
    /**
     * contructor, that creates grid of 3 rows and 1 column a fill them with jtogglebuttons
     */
    public UCLeftBottomContent()
    {
        super();
        this.mainContentPane = new JPanel(new GridLayout(3, 1));
        createMainPane();
    }

    /**
     * Method that will create main content pane.
     * It will add to this pane 3 jtoggle buttons, these buttons are specified byt lineType enum.
     */
    public void createMainPane()
    {
        JToggleButton association = new JToggleButton("Association");
        association.setName(LineType.ASSOCIATION.name());
        
        JToggleButton include = new JToggleButton("Uses");
        include.setName(LineType.USES.name());
        
        JToggleButton extend = new JToggleButton("Extend");
        extend.setName(LineType.EXTENDS.name());
        
        this.mainContentPane.add(association);
        this.mainContentPane.add(include);
        this.mainContentPane.add(extend);
    }
    
    /**
     * Method for getting button specified by it's name.
     * @param String name of button that will be returned
     * @return JToggleButton specified by it's name
     * @return null if no button is found
     */
    public JToggleButton getButtonWithName(String name)
    {
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
