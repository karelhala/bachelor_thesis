/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import static BT.BT.elementWithLabelAbove;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Class for holding guard and action text fields.
 * These fields are disabled, if you wish to edit guard and action, go to propriete class.
 * @author Karel Hala
 */
public class PetrinetGuardActionPanel {
    /**
     * Text field that holds guard string.
     */
    private final JTextField guardField;
    
    /**
     * Text field that holds action string.
     */
    private final JTextField actionField;
    
    /**
     * Pane that holds guard field and action field.
     */
    private final JPanel contentPane;
    /**
     * Initialize guard field and action field, insert them in content Pane.
     * Make them disable.
     */
    public PetrinetGuardActionPanel()
    {
        this.guardField = new JTextField();
        this.actionField = new JTextField();
        this.contentPane = new JPanel(new GridLayout(1, 2));
        disableFields().insetIntoPane();
    }

    /**
     * Get JTextField containign guard of transition.
     * @return JTextField guardField.
     */
    public JTextField getGuardField() {
        return guardField;
    }

    /**
     * Get JTextField containign action of transition.
     * @return JTextField actionField.
     */
    public JTextField getActionField() {
        return actionField;
    }

    /**
     * Get content pane that holds JTextFields.
     * @return jpanel contentPane.
     */
    public JPanel getContentPane() {
        return contentPane;
    }
    
    /**
     * Make guard field and action field disabled.
     * @return this object.
     */
    private PetrinetGuardActionPanel disableFields()
    {
        this.guardField.setEnabled(false);
        this.actionField.setEnabled(false);
        return this;
    }
    
    /**
     * Insert guard field and action field to content pane.
     * @return this object.
     */
    private PetrinetGuardActionPanel insetIntoPane()
    {
        this.contentPane.add(elementWithLabelAbove(this.guardField, new JLabel("Transition's guard field")));
        this.contentPane.add(elementWithLabelAbove(this.actionField, new JLabel("Transition's action field")));
        return this;
    }
}
