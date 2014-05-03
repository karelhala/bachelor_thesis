/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BT.modules.ObjectedOrientedPetriNet.mainContent.PNBottomRight;

import BT.BT.ClassType;
import static BT.BT.elementWithLabelAbove;
import BT.managers.CD.Attribute;
import BT.managers.CD.Method;
import BT.models.ActionModel;
import BT.models.CoordinateModel;
import BT.models.LineModel;
import BT.models.MyArrayList;
import BT.modules.ClassDiagram.places.CDClass;
import BT.modules.ObjectedOrientedPetriNet.places.PNPlace;
import GUI.BasicPetrinetPanel;
import GUI.BottomRightContentModel;
import GUI.PetrinetGuardActionPanel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Karel Hala
 */
public class PNBottomRightController extends PNBottomRightModel{
    
    /**
     * 
     * @param bottomRightModel
     * @param petrinetPanel
     * @param petrinetGuardAction 
     */
    public PNBottomRightController(BottomRightContentModel bottomRightModel, BasicPetrinetPanel petrinetPanel, PetrinetGuardActionPanel petrinetGuardAction) {
        super(bottomRightModel, petrinetPanel, petrinetGuardAction);
        
    }
    
    /**
     * Load class attributes to combobox.
     * @return this object.
     */
    public PNBottomRightController loadAttributesToComboBox()
    {
        this.basicPetrinetPanel.getClassAttributes().removeAllItems();
        if (this.selectedClass != null)
        {
            for (Attribute oneAttribute : this.selectedClass.loadClassAttributes()) {
                this.basicPetrinetPanel.getClassAttributes().addItem(oneAttribute);
            }
            if (this.selectedMethod != null && this.selectedMethod.getObjectMethod() != null)
            {
                Method clickedMethod = this.selectedMethod.getObjectMethod();
                for (String methodsAttribute : clickedMethod.getAttributes()) {
                    this.basicPetrinetPanel.getClassAttributes().addItem(methodsAttribute);
                }
            }
        }
        if (this.selectedClass.loadClassAttributes().size() == 0)
        {
            this.basicPetrinetPanel.getClassAttributes().setEnabled(false);
        }
        else
        {
            this.basicPetrinetPanel.getClassAttributes().setEnabled(true);
        }
        return this;
    }
    
    /**
     * Initialize action listeners for top and bottom button.
     * @return this object.
     */
    public PNBottomRightController initializeButtonListeners()
    {
        bottomRightModel.getTopButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String guardString = createGuardPanel(selectedTransition.getGuard());
                if (guardString != null)
                {
                    selectedTransition.setGuard(guardString);
                    petrinetGuardAction.getGuardField().setText(selectedTransition.getGuard());
                }
            }
        });
        
        bottomRightModel.getBottomButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (selectedTransition != null)
                {
                    createActionPanel(selectedTransition.getAction());
                    petrinetGuardAction.getActionField().setText(selectedTransition.getAction().getActionAsString());
                }
            }
        });
        
        this.basicPetrinetPanel.getAddClassVariable().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (basicPetrinetPanel.getClassAttributes().getSelectedItem() != null)
                {
                    PNPlace newPlace = new PNPlace(30,30);
                    newPlace.setEditable(Boolean.FALSE);
                    newPlace.addVariable(basicPetrinetPanel.getClassAttributes().getSelectedItem().toString());
                    newPlace.setName(basicPetrinetPanel.getClassAttributes().getSelectedItem().toString());
                    petrinetPlaces.addObject(newPlace);
                }
                petrinetDrawingPane.getDrawing().repaint();
            }
        });
        return this;
    }
    
    /**
     * Change guard and action fields for selected transition.
     */
    public void changeGuardAndAction()
    {
        if (this.selectedTransition != null)
        {
            this.petrinetGuardAction.getActionField().setText(this.selectedTransition.getAction().getActionAsString());
            this.petrinetGuardAction.getGuardField().setText(this.selectedTransition.getGuard());
        }
        else
        {
            this.petrinetGuardAction.getActionField().setText("");
            this.petrinetGuardAction.getGuardField().setText("");
        }
    }
    
    /**
     * 
     * @param guardString
     * @return 
     */
    private String createGuardPanel(String guardString)
    {
        MyArrayList<String> placesVariables = new MyArrayList<>();
        for (LineModel oneLine : this.selectedTransition.getInJoins()) {
            placesVariables.addAllUnique(((PNPlace)oneLine.getFirstObject()).getVariables());
        }
        JPanel dialogPanel = new JPanel(new BorderLayout());
        final JTextField resultString = new JTextField(guardString);
        
        JPanel variablesPanel = new JPanel(new BorderLayout());
        final JComboBox leftVariable = new JComboBox(placesVariables.toArray());
        final JComboBox rightVariable = new JComboBox(placesVariables.toArray());
        leftVariable.setEditable(true);
        rightVariable.setEditable(true);
        final JTextField operator = new JTextField();
        variablesPanel.add(elementWithLabelAbove(leftVariable, new JLabel("Left")), BorderLayout.LINE_START);
        variablesPanel.add(elementWithLabelAbove(operator, new JLabel("Operator")), BorderLayout.CENTER);
        variablesPanel.add(elementWithLabelAbove(rightVariable, new JLabel("Right")), BorderLayout.LINE_END);
        
        JButton andButton = new JButton("&&");
        andButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                resultString.setText(joinGuardString(
                        resultString.getText(), "&&", (String)leftVariable.getSelectedItem(), (String)rightVariable.getSelectedItem(), operator.getText()
                ));
            }
        });
        JButton orButton = new JButton("||");
        orButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                resultString.setText(joinGuardString(
                        resultString.getText(), "||", (String)leftVariable.getSelectedItem(), (String)rightVariable.getSelectedItem(), operator.getText()
                ));
            }
        });
        JButton basicButton = new JButton("set to this");
        basicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                resultString.setText(joinGuardString(
                        "", null, (String)leftVariable.getSelectedItem(), (String)rightVariable.getSelectedItem(), operator.getText()
                ));
            }
        });
        
        dialogPanel.add(elementWithLabelAbove(resultString, new JLabel("Guard")) , BorderLayout.PAGE_START);
        dialogPanel.add(andButton, BorderLayout.LINE_START);
        dialogPanel.add(basicButton, BorderLayout.CENTER);
        dialogPanel.add(orButton, BorderLayout.LINE_END);
        dialogPanel.add(variablesPanel, BorderLayout.PAGE_END);
        int result = JOptionPane.showConfirmDialog(null, dialogPanel,
                    "Please Enter Guard for this transition.", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION)
        {
            if ("".equals(resultString.getText()))
            {
                return joinVariablesWithOperator(
                        (String)leftVariable.getSelectedItem(), operator.getText(), (String)rightVariable.getSelectedItem()
                );
            }
            return resultString.getText();
        }
        return null;
    }
    
    /**
     * Join left variable and right variable over operator.
     * @param leftVariable string from jcombobox.
     * @param operator string from operator input.
     * @param rightVariable string from jcombobox.
     * @return joined strings.
     */
    public String joinVariablesWithOperator(String leftVariable, String operator, String rightVariable)
    {
        if (leftVariable != null && rightVariable != null)
        {
            return leftVariable + " " + operator + " " + rightVariable;
        }
        return "";
    }
    
    /**
     * Join variables over operator to guard string over glue.
     * If glue is null, replace guard string.
     * @param guardString
     * @param glue
     * @param leftVariable
     * @param rightVariable
     * @param operator
     * @return 
     */
    public String joinGuardString(String guardString, String glue, String leftVariable, String rightVariable, String operator)
    {
        operator = ("".equals(operator))?"==":operator;
        if (glue == null)
        {
            return joinVariablesWithOperator(leftVariable, operator, rightVariable);
        }
        return guardString + " " + glue + " " + joinVariablesWithOperator(leftVariable, operator, rightVariable);
    }
    
    /**
     * 
     * @param oldAction 
     */
    public void createActionPanel(ActionModel oldAction)
    {
        JPanel dialogPanel = new JPanel(new BorderLayout());
        JPanel contentPanel = new JPanel(new BorderLayout());
        JComboBox variableField = new JComboBox();
        JTextArea actionArea = new JTextArea(this.selectedTransition.getAction().getBasicAction(),5, 20);
        JScrollPane scrollPane = new JScrollPane(actionArea); 
        variableField.setEditable(true);
        
        for (String oneVariable : this.selectedTransition.getVariables()) {
            variableField.addItem(oneVariable);
        }
        variableField.setSelectedIndex(-1);
        variableField.setSelectedItem(oldAction.getVariable());
        if (oldAction.getVariable() != null && this.selectedClass.getVariableByName(oldAction.getVariable()) != null)
        {
            variableField.setSelectedItem(this.selectedClass.getVariableByName(oldAction.getVariable()));
        }

        contentPanel.add(elementWithLabelAbove(variableField, new JLabel("variable"), Font.ITALIC), BorderLayout.LINE_START);
        contentPanel.add(elementWithLabelAbove(scrollPane, new JLabel("action"), Font.BOLD), BorderLayout.PAGE_END);
        dialogPanel.add(contentPanel, BorderLayout.PAGE_END);
         int result = JOptionPane.showConfirmDialog(null, dialogPanel,
                    "Please Enter Action for this transition.", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION)
        {
            this.selectedTransition.getAction().setBasicAction(actionArea.getText());
            if (variableField.getSelectedItem() != null)
            {
                this.selectedTransition.getAction().setVariable(variableField.getSelectedItem().toString());
            }
        }
    }
}