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
 * @author Karel Hala
 */
public final class UCLeftTopContent {
    private JPanel mainContentPane;
    public UCLeftTopContent()
    {
        this.mainContentPane = new JPanel(new GridLayout(2, 1));
        createMainPane();
    }
    
    public JPanel getMainContentPane ()
    {
        return this.mainContentPane;
    }
    
    public void createMainPane()
    {
        JToggleButton actor = new JToggleButton("Actor");
        actor.setName("actor");
        
        JToggleButton useCase = new JToggleButton("Use case");
        useCase.setName("useCase");
        
        this.mainContentPane.add(actor);
        this.mainContentPane.add(useCase);
    }
}
