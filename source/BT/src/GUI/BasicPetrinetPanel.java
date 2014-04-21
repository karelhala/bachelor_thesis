/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static BT.BT.elementWithLabelAbove;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class that holds panel with class variables and button for inserting them.
 * @author Karel Hala
 */
public class BasicPetrinetPanel {

    /**
     * Combo box with attributes of class.
     */
    private final JComboBox classAttributes;

    /**
     * Button for controlling adding variabless of class to main panel.
     */
    private final JButton addClassVariable;

    /**
     * Pane that holds guard combo box with class attributes.
     */
    private final JPanel contentPane;

    public BasicPetrinetPanel() {
        this.classAttributes = new JComboBox();
        this.contentPane = new JPanel(new BorderLayout());
        this.addClassVariable = new JButton("Add class variable");
    }

    /**
     * Insert combo box with attributes to content panel.
     */
    public void insertAttributesToPanel() {
        this.contentPane.add(elementWithLabelAbove(contentPane, new JLabel("Select variable")), BorderLayout.LINE_START);
        this.contentPane.add(addClassVariable, BorderLayout.CENTER);
    }

    /**
     * Get combo box with attributes.
     *
     * @return
     */
    public JComboBox getClassAttributes() {
        return classAttributes;
    }

    /**
     * Get main content panel.
     *
     * @return
     */
    public JPanel getContentPane() {
        return contentPane;
    }

    public JButton getAddClassVariable() {
        return addClassVariable;
    }
}
