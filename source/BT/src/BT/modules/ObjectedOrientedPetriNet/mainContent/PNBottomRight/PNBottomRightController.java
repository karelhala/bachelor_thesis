/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ObjectedOrientedPetriNet.mainContent.PNBottomRight;

import static BT.BT.elementWithLabelAbove;
import BT.managers.CD.Attribute;
import BT.managers.CD.Method;
import BT.models.ActionModel;
import BT.models.LineModel;
import BT.modules.ObjectedOrientedPetriNet.places.PNPlace;
import BT.modules.ObjectedOrientedPetriNet.places.PNTransition;
import BT.modules.ObjectedOrientedPetriNet.places.joinEdge.PNJoinEdgeController;
import GUI.BasicPetrinetPanel;
import GUI.BottomRightContentModel;
import GUI.PetrinetGuardActionPanel;
import GUI.PetrinetPlacePanel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Class for creating panels, which will controll Action or Guard.
 *
 * @author Karel Hala
 */
public class PNBottomRightController extends PNBottomRightModel {

    /**
     * Basic constructor that will call parent's constructor with each argument.
     *
     * @param bottomRightModel to be specified in PNBottomRightModel.
     * @param petrinetPanel to be specified in PNBottomRightModel.
     * @param petrinetGuardAction to be specified in PNBottomRightModel.
     * @param petrinetPlace to be specified in PNBottomRightModel.
     */
    public PNBottomRightController(BottomRightContentModel bottomRightModel, BasicPetrinetPanel petrinetPanel, PetrinetGuardActionPanel petrinetGuardAction, PetrinetPlacePanel petrinetPlace) {
        super(bottomRightModel, petrinetPanel, petrinetGuardAction, petrinetPlace);

    }

    /**
     * Load class attributes to combobox.
     *
     * @return this object.
     */
    public PNBottomRightController loadAttributesToComboBox() {
        this.basicPetrinetPanel.getClassAttributes().removeAllItems();
        if (this.selectedClass != null) {
            for (Attribute oneAttribute : this.selectedClass.loadClassAttributes()) {
                this.basicPetrinetPanel.getClassAttributes().addItem(oneAttribute);
            }
            if (this.selectedMethod != null && this.selectedMethod.getObjectMethod() != null) {
                Method clickedMethod = this.selectedMethod.getObjectMethod();
                for (String methodsAttribute : clickedMethod.getAttributes()) {
                    this.basicPetrinetPanel.getClassAttributes().addItem(methodsAttribute);
                }
            }
        }
        if (this.selectedClass.loadClassAttributes().size() != 0
                || (this.selectedMethod != null && this.selectedMethod.getObjectMethod() != null && this.selectedMethod.getObjectMethod().getAttributes().size() != 0)) {
            this.basicPetrinetPanel.getClassAttributes().setEnabled(true);
        } else {
            this.basicPetrinetPanel.getClassAttributes().setEnabled(false);
        }

        return this;
    }

    /**
     * Initialize action listeners for top and bottom button.
     *
     * @return this object.
     */
    public PNBottomRightController initializeButtonListeners() {
        bottomRightModel.getTopButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (selectedObject instanceof PNTransition) {
                    PNTransition selectedTransition = (PNTransition) selectedObject;
                    String guardString = createGuardPanel(selectedTransition.getGuard());
                    if (guardString != null) {
                        selectedTransition.setGuard(guardString);
                        petrinetGuardAction.getGuardField().setText(selectedTransition.getGuard());
                    }
                }
            }
        });

        bottomRightModel.getBottomButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (selectedObject != null && selectedObject instanceof PNTransition) {
                    PNTransition selectedTransition = (PNTransition) selectedObject;
                    createActionPanel(selectedTransition.getAction());
                    petrinetGuardAction.getActionField().setText(selectedTransition.getAction().getActionAsString());
                }
            }
        });

        this.basicPetrinetPanel.getAddClassVariable().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (basicPetrinetPanel.getClassAttributes().getSelectedItem() != null) {
                    PNPlace newPlace = new PNPlace(30, 30);
                    newPlace.setEditable(Boolean.FALSE);
                    newPlace.addVariable(basicPetrinetPanel.getClassAttributes().getSelectedItem().toString());
                    newPlace.setName(basicPetrinetPanel.getClassAttributes().getSelectedItem().toString());
                    petrinetPlaces.addObject(newPlace);
                }
                petrinetDrawingPane.getDrawing().repaint();
            }
        });

        this.petrinetPlace.getConfirmButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setConstantClicked();
                petrinetDrawingPane.getDrawing().repaint();
            }
        });
        return this;
    }

    /**
     * Method for handeling when button for setting constant clicked.
     */
    public void setConstantClicked() {
        String constant = this.petrinetPlace.getConstantField().getText();
        if (constant != null && !constant.equals("") && this.selectedObject instanceof PNPlace) {
            ((PNPlace) this.selectedObject).setConstant(constant);
        }
    }

    /**
     * Change guard and action fields for selected transition.
     */
    public void changeGuardAndAction() {
        if (this.selectedObject != null && this.selectedObject instanceof PNTransition) {
            PNTransition selectedTransition = (PNTransition) selectedObject;
            this.petrinetGuardAction.getActionField().setText(selectedTransition.getAction().getActionAsString());
            this.petrinetGuardAction.getGuardField().setText(selectedTransition.getGuard());
        } else if (this.selectedObject != null && this.selectedObject instanceof PNPlace) {
            this.petrinetPlace.getConstantField().setText(((PNPlace) selectedObject).getConstant());
        } else {
            this.petrinetGuardAction.getActionField().setText("");
            this.petrinetGuardAction.getGuardField().setText("");
        }
    }

    /**
     * Crates guard panel to set guard in PNTransition.
     * <pre>
     * <code>It will create new dialog which will have:
     * String field: for direct changing of guard.
     * And button.
     * Or button.
     * Set to new guard button.
     * Left variable input, operator and right variable input.
     * </code>
     * </pre>
     *
     * @param guardString o;d guard string.
     * @return new guard string.
     */
    private String createGuardPanel(String guardString) {
        PNTransition selectedTransition = (PNTransition) selectedObject;
        JPanel dialogPanel = new JPanel(new BorderLayout());
        final JTextField resultString = new JTextField(guardString);

        JPanel variablesPanel = new JPanel(new BorderLayout());
        final JComboBox leftVariable = new JComboBox();
        final JComboBox rightVariable = new JComboBox();
        for (String oneVariable : selectedTransition.getVariables()) {
            leftVariable.addItem(oneVariable);
        }
        for (String oneVariable : selectedTransition.getVariables()) {
            rightVariable.addItem(oneVariable);
        }
        leftVariable.setEditable(true);
        leftVariable.setSelectedIndex(-1);
        rightVariable.setEditable(true);
        rightVariable.setSelectedIndex(-1);
        final JTextField operator = new JTextField();
        variablesPanel.add(elementWithLabelAbove(leftVariable, new JLabel("Left")), BorderLayout.LINE_START);
        variablesPanel.add(elementWithLabelAbove(operator, new JLabel("Operator")), BorderLayout.CENTER);
        variablesPanel.add(elementWithLabelAbove(rightVariable, new JLabel("Right")), BorderLayout.LINE_END);

        JButton andButton = new JButton("&&");
        andButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                resultString.setText(joinGuardString(
                        resultString.getText(), "&&", (String) leftVariable.getSelectedItem(), (String) rightVariable.getSelectedItem(), operator.getText()
                ));
            }
        });
        JButton orButton = new JButton("||");
        orButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                resultString.setText(joinGuardString(
                        resultString.getText(), "||", (String) leftVariable.getSelectedItem(), (String) rightVariable.getSelectedItem(), operator.getText()
                ));
            }
        });
        JButton basicButton = new JButton("set to this");
        basicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                resultString.setText(joinGuardString(
                        "", null, (String) leftVariable.getSelectedItem(), (String) rightVariable.getSelectedItem(), operator.getText()
                ));
            }
        });

        dialogPanel.add(elementWithLabelAbove(resultString, new JLabel("Guard")), BorderLayout.PAGE_START);
        dialogPanel.add(andButton, BorderLayout.LINE_START);
        dialogPanel.add(basicButton, BorderLayout.CENTER);
        dialogPanel.add(orButton, BorderLayout.LINE_END);
        dialogPanel.add(variablesPanel, BorderLayout.PAGE_END);
        int result = JOptionPane.showConfirmDialog(null, dialogPanel,
                "Please Enter Guard for this transition.", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            if ("".equals(resultString.getText())) {
                return joinVariablesWithOperator(
                        (String) leftVariable.getSelectedItem(), operator.getText(), (String) rightVariable.getSelectedItem()
                );
            }
            return resultString.getText();
        }
        return null;
    }

    /**
     * Join left variable and right variable over operator.
     *
     * @param leftVariable string from jcombobox.
     * @param operator string from operator input.
     * @param rightVariable string from jcombobox.
     * @return joined strings.
     */
    public String joinVariablesWithOperator(String leftVariable, String operator, String rightVariable) {
        if (leftVariable != null && rightVariable != null) {
            return leftVariable + " " + operator + " " + rightVariable;
        }
        return "";
    }

    /**
     * Join variables over operator to guard string over glue. If glue is null, replace guard string.
     *
     * @param guardString old guard string.
     * @param glue glue wchich will be used to join old guard with new settings [&&,||].
     * @param leftVariable variable or constant on left side.
     * @param rightVariable variable or constant on right side.
     * @param operator this will be used to join left and right variable. Basic operator, if new one is empty, is "==".
     * @return new guard string joined by glue string.
     */
    public String joinGuardString(String guardString, String glue, String leftVariable, String rightVariable, String operator) {
        operator = ("".equals(operator)) ? "==" : operator;
        if (glue == null) {
            return joinVariablesWithOperator(leftVariable, operator, rightVariable);
        }
        return guardString + " " + glue + " " + joinVariablesWithOperator(leftVariable, operator, rightVariable);
    }

    /**
     * Create panel with action to be set. Panel is shown in dialog window, it containt variable JcomboBox and Jtext
     * field.
     *
     * @param oldAction ActionModel to be shown as default value.
     */
    public void createActionPanel(ActionModel oldAction) {
        PNTransition selectedTransition = (PNTransition) selectedObject;
        JPanel dialogPanel = new JPanel(new BorderLayout());
        JPanel contentPanel = new JPanel(new BorderLayout());
        JComboBox variableField = new JComboBox();
        JTextArea actionArea = new JTextArea(selectedTransition.getAction().getBasicAction(), 5, 20);
        JScrollPane scrollPane = new JScrollPane(actionArea);
        variableField.setEditable(true);

        for (String oneVariable : selectedTransition.getVariables()) {
            variableField.addItem(oneVariable);
        }
        variableField.setSelectedIndex(-1);
        variableField.setSelectedItem(oldAction.getVariable());
        if (oldAction.getVariable() != null && this.selectedClass.getVariableByName(oldAction.getVariable()) != null) {
            variableField.setSelectedItem(this.selectedClass.getVariableByName(oldAction.getVariable()));
        }

        contentPanel.add(elementWithLabelAbove(variableField, new JLabel("variable"), Font.ITALIC), BorderLayout.LINE_START);
        contentPanel.add(elementWithLabelAbove(scrollPane, new JLabel("action"), Font.BOLD), BorderLayout.PAGE_END);
        dialogPanel.add(contentPanel, BorderLayout.PAGE_END);
        int result = JOptionPane.showConfirmDialog(null, dialogPanel,
                "Please Enter Action for this transition.", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            selectedTransition.getAction().setBasicAction(actionArea.getText());
            if (variableField.getSelectedItem() != null && !variableField.getSelectedItem().toString().equals("")) {
                selectedTransition.getAction().setVariable(variableField.getSelectedItem().toString());
                for (LineModel oneLine : selectedTransition.getOutJoins()) {
                    ((PNJoinEdgeController) oneLine).addVariable(selectedTransition.getActionVariable());
                    ((PNPlace) oneLine.getSecondObject()).addVariable(selectedTransition.getActionVariable());
                }
            } else {
                selectedTransition.getAction().setVariable(null);
            }
        }
    }
}
