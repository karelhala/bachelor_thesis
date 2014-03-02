/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BT.modules.ObjectedOrientedPetriNet;

import BT.BT;
import BT.models.ButtonPaneModel;
import BT.modules.ObjectedOrientedPetriNet.mainContent.PNMainContentController;
import java.awt.GridLayout;
import javax.swing.JPanel;

/**
 *
 * @author Karel
 */
public class PNLeftTopContent extends ButtonPaneModel{
    
    /**
     * Constructor, creates gridlayour and calls to create left Top pane
     */
    public PNLeftTopContent()
    {
        this(null);
    }
    
    /**
     * creates gridlayour and calls to create left Top pane
     * @param pnMain
     */
    public PNLeftTopContent(PNMainContentController pnMain)
    {
        super(pnMain);
        this.mainContentPane = new JPanel(new GridLayout(1, 1));
        createMainPane();
    }
    
    /**
     * Method for creating main page. It will add 2 jtoggleButtons to main pane.
     */
    public void createMainPane()
    {
    }
    
}
