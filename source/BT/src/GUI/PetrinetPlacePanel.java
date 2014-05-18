/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static BT.BT.elementWithLabelAbove;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Karel Hala
 */
public class PetrinetPlacePanel {

    /**
     * Text field that holds constant string.
     */
    private final JTextField constantField;

    /**
     * Pane that holds guard field and action field.
     */
    private final JPanel contentPane;

    /**
     * Button for confirming constant field.
     */
    private final JButton confirmButton;

    public PetrinetPlacePanel() {
        this.constantField = new JTextField();
        this.contentPane = new JPanel(new BorderLayout());
        this.confirmButton = new JButton("Change constant");
        this.confirmButton.setToolTipText(this.confirmButton.getText());
        insertIntoPane();
    }

    public JTextField getConstantField() {
        return constantField;
    }

    public JPanel getContentPane() {
        return contentPane;
    }

    public JButton getConfirmButton() {
        return confirmButton;
    }

    /**
     * Insert constant field and confirm button to content pane.
     *
     * @return this object.
     */
    private void insertIntoPane() {
        this.contentPane.add(this.confirmButton, BorderLayout.WEST);
        this.contentPane.add(elementWithLabelAbove(this.constantField, new JLabel("Costant's field")), BorderLayout.CENTER);
    }
}
