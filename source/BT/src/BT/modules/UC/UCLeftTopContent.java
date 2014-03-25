/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.UC;

import BT.BT;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import BT.models.ButtonPaneModel;
import BT.modules.UC.mainContent.UCMainContentController;

/**
 * Class for sotring and managing left top panel, that consists of buttons
 *
 * @author Karel Hala
 */
public final class UCLeftTopContent extends ButtonPaneModel {

    /**
     * Constructor, creates gridlayour and calls to create left Top pane
     */
    public UCLeftTopContent() {
        this(null);
    }

    /**
     * creates gridlayour and calls to create left Top pane
     *
     * @param UCMainContent UCMain it will react to button changes
     */
    public UCLeftTopContent(UCMainContentController UCMain) {
        super(UCMain);
        this.mainContentPane = new JPanel(new GridLayout(2, 1));
        createMainPane();
    }

    /**
     * Method for creating main page. It will add 2 jtoggleButtons to main pane.
     */
    public void createMainPane() {
        JToggleButton actor = new JToggleButton("Actor");
        actor.setName(BT.UCObjectType.ACTOR.name());

        JToggleButton useCase = new JToggleButton("Use case");
        useCase.setName(BT.UCObjectType.USECASE.name());

        this.mainContentPane.add(actor);
        this.mainContentPane.add(useCase);
    }
}
