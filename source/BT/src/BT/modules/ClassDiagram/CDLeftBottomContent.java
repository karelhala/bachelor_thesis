/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ClassDiagram;

import BT.BT.CDLineType;
import BT.BT.UCLineType;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import BT.models.ButtonPaneModel;
import BT.modules.ClassDiagram.mainContent.CDMainContentController;

/**
 * Class for creating LeftBottom pane that holds 3 jtoggle buttons
 *
 * @author Karel Hala
 */
public final class CDLeftBottomContent extends ButtonPaneModel {

    /**
     * contructor, that creates grid of 3 rows and 1 column a fill them with
     * jtogglebuttons
     */
    public CDLeftBottomContent() {
        this(null);
    }

    /**
     * contructor, that creates grid of 3 rows and 1 column a fill them with
     * jtogglebuttons
     *
     * @param cdMain
     */
    public CDLeftBottomContent(CDMainContentController cdMain) {
        super(cdMain);
        this.mainContentPane = new JPanel(new GridLayout(5, 1));
        createMainPane();
    }

    /**
     * Method that will create main content pane. It will add to this pane 3
     * jtoggle buttons, these buttons are specified byt lineType enum.
     */
    public void createMainPane() {
        JToggleButton association = new JToggleButton("Association");
        association.setName(CDLineType.ASSOCIATION.name());

        JToggleButton agregation = new JToggleButton("Aggregation");
        agregation.setName(CDLineType.AGGREGATION.name());

        JToggleButton composition = new JToggleButton("Composition");
        composition.setName(CDLineType.COMPOSITION.name());

        JToggleButton generalization = new JToggleButton("Generalization");
        generalization.setName(CDLineType.GENERALIZATION.name());

        JToggleButton realization = new JToggleButton("Realization");
        realization.setName(CDLineType.REALIZATION.name());

        this.mainContentPane.add(association);
        this.mainContentPane.add(agregation);
        this.mainContentPane.add(composition);
        this.mainContentPane.add(generalization);
        this.mainContentPane.add(realization);
    }
}
