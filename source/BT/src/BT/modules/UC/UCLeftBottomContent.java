/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.UC;

import BT.BT.UCLineType;
import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import BT.models.ButtonPaneModel;
import BT.modules.UC.mainContent.UCMainContentController;

/**
 * Class for creating LeftBottom pane that holds 3 jtoggle buttons
 * @author Karel Hala
 */
public final class UCLeftBottomContent extends ButtonPaneModel{
    
    /**
     * contructor, that creates grid of 3 rows and 1 column a fill them with jtogglebuttons
     */
    public UCLeftBottomContent()
    {
        this(null);
    }

    /**
     * contructor, that creates grid of 3 rows and 1 column a fill them with jtogglebuttons
     * @param UCMainContent UCMain maincontent that will react to when buttons are selected
     */
    public UCLeftBottomContent(UCMainContentController UCMain)
    {
        super(UCMain);
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
        association.setName(UCLineType.ASSOCIATION.name());
        
        JToggleButton include = new JToggleButton("Uses");
        include.setName(UCLineType.USES.name());
        
        JToggleButton extend = new JToggleButton("Emplements");
        extend.setName(UCLineType.IMPLEMENTS.name());
        
        this.mainContentPane.add(association);
        this.mainContentPane.add(include);
        this.mainContentPane.add(extend);
    }
}
