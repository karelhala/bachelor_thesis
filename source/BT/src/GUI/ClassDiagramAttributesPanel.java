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
 * Class which has panel for creating new class variables and methods.
 *
 * @author Karel Hala
 */
public class ClassDiagramAttributesPanel {

    /**
     * Content panel which holds each element of this class.
     */
    private final JPanel contentPanel;
    /**
     * Variable text field, which is used to describe variable's name.
     */
    private final JTextField variableName;
    /**
     * Tethod text field, which is used to describe method's name.
     */
    private final JTextField methodName;
    /**
     * Select box for choosing visibility of variable.
     */
    private final JComboBox visibilityVariable;
    /**
     * Select box for choosing visibility of method.
     */
    private final JComboBox visibilityMethod;
    /**
     * Select box fot choosing type of variable.
     */
    private final JComboBox attributeTypeVariable;
    /**
     * Select box fot choosing return type of method.
     */
    private final JComboBox attributeTypeMethod;
    /**
     * Button for confirming variable and adding it to class.
     */
    private final JButton addVariable;
    /**
     * Button for confirming method and adding it to class.
     */
    private final JButton addMethod;

    /**
     * Masic constructor. It creates contentPanel, variableName, methodName, visibilityVariable, visibilityMethod,
     * attributeTypeVariable, attributeTypeMethod, addMethod, addVariable and insert each component to right position.
     */
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

    /**
     * Returns content panel which holds each component.
     * @return contentPanel as JPanel.
     */
    public JPanel getContentPanel() {
        return contentPanel;
    }

    /**
     * Returns variable's name.
     * @return variableName as JTextField
     */
    public JTextField getVariableName() {
        return variableName;
    }

    /**
     * Returns method's name.
     * @return methodName as JTextField.
     */
    public JTextField getMethodName() {
        return methodName;
    }

    /**
     * Returns button for adding variable.
     * @return addVariable as JButton.
     */
    public JButton getAddVariable() {
        return addVariable;
    }

    /**
     * Returns button for adding method.
     * @return addMethod as JButton
     */
    public JButton getAddMethod() {
        return addMethod;
    }

    /**
     * Returns visibility of class variable.
     * @return visibilityVariable as JComboBox.
     */
    public JComboBox getVisibilityVariable() {
        return visibilityVariable;
    }

    /**
     * Returns visibility of method.
     * @return visibilityMethod as JComboBox.
     */
    public JComboBox getVisibilityMethod() {
        return visibilityMethod;
    }

    /**
     * Returns type of variable.
     * @return attributeTypeVariable as JComboBox.
     */
    public JComboBox getAttributeTypeVariable() {
        return attributeTypeVariable;
    }

    /**
     * Returns return type of method.
     * @return attributeTypeMethod as JComboBox.
     */
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
