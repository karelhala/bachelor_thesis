/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static BT.BT.elementWithLabelAbove;
import BT.models.CoordinateModel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Karel
 */
public class ClassDiagramAttributesPanel {

    private final JPanel contentPanel;
    private final JTextField variableName;
    private final JTextField methodName;
    private final JComboBox visibilityVariable;
    private final JComboBox visibilityMethod;
    private final JComboBox attributeTypeVariable;
    private final JComboBox attributeTypeMethod;
    private final JButton addVariable;
    private final JButton addMethod;

    public ClassDiagramAttributesPanel() {
        this.contentPanel = new JPanel(new GridBagLayout());
        this.variableName = new JTextField();
        this.methodName = new JTextField();
        String[] visibilityArray = {
            "-",
            "+",
            "#"
        };
        this.visibilityVariable = new JComboBox(visibilityArray);
        this.visibilityMethod = new JComboBox(visibilityArray);
        this.attributeTypeVariable = new JComboBox();
        this.attributeTypeVariable.setEditable(true);
        this.attributeTypeMethod = new JComboBox();
        this.attributeTypeMethod.setEditable(true);
        this.addMethod = new JButton("Add");
        this.addVariable = new JButton("Add");
        insertElemensToPanel();
    }

    /**
     * Method that inserts jcomponents to right order for class diagram.
     */
    private void insertElemensToPanel() {
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.gridx = 0;
        c.gridy = 0;
        this.contentPanel.add(elementWithLabelAbove(visibilityVariable, new JLabel("visibility")), c);

        JPanel nameAndTypeVariable = new JPanel(new GridLayout(1, 2));
        nameAndTypeVariable.add(elementWithLabelAbove(variableName, new JLabel("   variable name")));
        nameAndTypeVariable.add(elementWithLabelAbove(attributeTypeVariable, new JLabel("variable type")));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.PAGE_START;
        c.gridx = 1;
        c.gridy = 0;
        this.contentPanel.add(nameAndTypeVariable, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        c.gridx = 2;
        c.gridy = 0;
        this.contentPanel.add(elementWithLabelAbove(addVariable, new JLabel(" ")), c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_START;
        c.gridx = 0;
        c.gridy = 1;
        this.contentPanel.add(elementWithLabelAbove(visibilityMethod, new JLabel("visibility")), c);

        JPanel nameAndTypeMethod = new JPanel(new GridLayout(1, 2));
        nameAndTypeMethod.add(elementWithLabelAbove(methodName, new JLabel("   method name")));
        nameAndTypeMethod.add(elementWithLabelAbove(attributeTypeMethod, new JLabel("method type")));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 1;
        c.gridy = 1;
        this.contentPanel.add(nameAndTypeMethod, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_END;
        c.gridx = 2;
        c.gridy = 1;
        this.contentPanel.add(elementWithLabelAbove(addMethod, new JLabel(" ")), c);
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public JTextField getVariableName() {
        return variableName;
    }

    public JTextField getMethodName() {
        return methodName;
    }

    public JButton getAddVariable() {
        return addVariable;
    }

    public JButton getAddMethod() {
        return addMethod;
    }

    public JComboBox getVisibilityVariable() {
        return visibilityVariable;
    }

    public JComboBox getVisibilityMethod() {
        return visibilityMethod;
    }

    public JComboBox getAttributeTypeVariable() {
        return attributeTypeVariable;
    }

    public JComboBox getAttributeTypeMethod() {
        return attributeTypeMethod;
    }

    /**
     * Method that inserts every class name into selectboxes for attribute type.
     *
     * @param allClasses ArrayList<CoordinateModel>
     */
    public void addObjectsToAttributeType(ArrayList<CoordinateModel> allClasses) {
        for (CoordinateModel object : allClasses) {
            this.attributeTypeVariable.insertItemAt(object.getName(), allClasses.indexOf(object));
            this.attributeTypeMethod.insertItemAt(object.getName(), allClasses.indexOf(object));
        }
    }

    /**
     * Method for removing all objects from attribute type selectors.
     */
    public void removeAllObjectsFromAttributeType() {
        this.attributeTypeMethod.removeAllItems();
        this.attributeTypeVariable.removeAllItems();
    }
}
