/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.UC;

import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import BT.models.ButtonlPaneModel;

/**
 * Class for sotring and managing left top panel, that consists of buttons
 * @author Karel Hala
 */
public final class UCLeftTopContent extends ButtonlPaneModel{

    /**
     * Constructor, creates gridlayour and calls to create main pane
     */
    public UCLeftTopContent()
    {
        super();
        this.mainContentPane = new JPanel(new GridLayout(2, 1));
        createMainPane();
    }
    
    /**
     * Method for creating main page. It will add 2 jtoggleButtons to main pane.
     */
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
