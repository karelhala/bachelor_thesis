/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.UC;

import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

/**
 *
 * @author Karel
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
        association.setName("association");
        
        JToggleButton include = new JToggleButton("Include");
        include.setName("include");
        
        JToggleButton extend = new JToggleButton("Extend");
        extend.setName("extend");
        this.mainContentPane.add(association);
        this.mainContentPane.add(include);
        this.mainContentPane.add(extend);
    }
}
