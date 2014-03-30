/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ClassDiagram;

import BT.BT;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import BT.models.ButtonPaneModel;
import BT.modules.ClassDiagram.mainContent.CDMainContentController;

/**
 * Class for sotring and managing left top panel, that consists of buttons
 *
 * @author Karel Hala
 */
public final class CDLeftTopContent extends ButtonPaneModel {

    /**
     * Constructor, creates gridlayour and calls to create left Top pane
     */
    public CDLeftTopContent() {
        this(null);
    }

    /**
     * creates gridlayour and calls to create left Top pane
     *
     * @param cdMain
     */
    public CDLeftTopContent(CDMainContentController cdMain) {
        super(cdMain);
        this.mainContentPane = new JPanel(new GridLayout(2, 1));
        createMainPane();
    }

    /**
     * Method for creating main page. It will add 2 jtoggleButtons to main pane.
     */
    public void createMainPane() {
        JToggleButton newClass = new JToggleButton("New Class");
        newClass.setName(BT.CDObjectType.CLASS.name());
        
        JToggleButton newInterface = new JToggleButton("New Interface");
        newClass.setName(BT.CDObjectType.INTERFACE.name());

        this.mainContentPane.add(newClass);
        
        this.mainContentPane.add(newInterface);
        
    }
}
