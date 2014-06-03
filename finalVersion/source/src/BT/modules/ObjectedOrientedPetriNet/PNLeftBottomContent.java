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
import javax.swing.JToggleButton;

/**
 * Class for storing button for creating join edge. This controller controls only one button.
 * 
 * @author Karel Hala
 */
public final class PNLeftBottomContent extends ButtonPaneModel {

    /**
     * Basic constructor. It creates panel with grid 1,1 and insert button with join.
     * @param PNMain
     */
    public PNLeftBottomContent(PNMainContentController PNMain) {
        super(PNMain);
        this.mainContentPane = new JPanel(new GridLayout(1, 1));
        createMainPane();
    }

    /**
     * Method that will create main content pane. It will add to this pane 3 jtoggle buttons, these buttons are
     * specified byt lineType enum.
     */
    public void createMainPane() {
        JToggleButton join = new JToggleButton("Join");
        join.setName(BT.OOPNLineType.JOIN.name());

        this.mainContentPane.add(join);
    }

}
