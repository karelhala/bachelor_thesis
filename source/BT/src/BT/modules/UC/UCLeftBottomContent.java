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
     * contructor, that creates grid of 4 rows and 1 column a fill them with jtogglebuttons
     */
    public UCLeftBottomContent()
    {
        this(null);
    }

    /**
     * contructor, that creates grid of 4 rows and 1 column a fill them with jtogglebuttons
     * @param UCMain
     */
    public UCLeftBottomContent(UCMainContentController UCMain)
    {
        super(UCMain);
        this.mainContentPane = new JPanel(new GridLayout(4, 1));
        createMainPane();
    }
    
    /**
     * Method that will create main content pane.
     * It will add to this pane 4 jtoggle buttons, these buttons are specified byt lineType enum.
     */
    public void createMainPane()
    {
        JToggleButton association = new JToggleButton("Association");
        association.setName(UCLineType.ASSOCIATION.name());
        
        JToggleButton include = new JToggleButton("Include");
        include.setName(UCLineType.INCLUDE.name());
        
        JToggleButton extend = new JToggleButton("Extends");
        extend.setName(UCLineType.EXTENDS.name());
        
        JToggleButton generalization = new JToggleButton("Generalization");
        generalization.setName(UCLineType.GENERALIZATION.name());
        
        
        
        this.mainContentPane.add(association);
        this.mainContentPane.add(include);
        this.mainContentPane.add(extend);
        this.mainContentPane.add(generalization);
    }
}
