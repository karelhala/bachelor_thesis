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
 *
 * @author Karel Hala
 */
public final class BasicPetrinetPanel {

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

    /**
     * Basic constructor. It will create new Jcombobox for attributes or method's arguments, content pane which will
     * have all of this stored, addClassVariable button.
     */
    public BasicPetrinetPanel() {
        this.classAttributes = new JComboBox();
        this.contentPane = new JPanel(new BorderLayout());
        this.addClassVariable = new JButton("Add new place");
        insertElementsToPanel();
    }

    /**
     * Insert combo box with attributes to content panel.
     */
    public void insertElementsToPanel() {
        this.contentPane.add(elementWithLabelAbove(classAttributes, new JLabel("Select variable")), BorderLayout.CENTER);
        this.contentPane.add(elementWithLabelAbove(addClassVariable, new JLabel("           ")), BorderLayout.LINE_START);
    }

    /**
     * Get combo box with attributes.
     *
     * @return JComboBox class attributes or method arguments.
     */
    public JComboBox getClassAttributes() {
        return classAttributes;
    }

    /**
     * Get main content panel.
     *
     * @return JPanel content pane.
     */
    public JPanel getContentPane() {
        return contentPane;
    }

    /**
     * Return button add class variable.
     *
     * @return JButton addClassVariable.
     */
    public JButton getAddClassVariable() {
        return addClassVariable;
    }
}
