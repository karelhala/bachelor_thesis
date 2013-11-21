/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.UC;

import BT.BT;
import BT.BT.LineType;
import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

/**
 *
 * @author Karel Hala
 */
public final class UCLeftBottomContent {
        private JPanel mainContentPane;
    public UCLeftBottomContent()
    {
        this.mainContentPane = new JPanel(new GridLayout(3, 1));
        createMainPane();
    }
    
    public JPanel getMainContentPane ()
    {
        return this.mainContentPane;
    }
    
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
