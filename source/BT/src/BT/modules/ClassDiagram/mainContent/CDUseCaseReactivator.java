/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BT.modules.ClassDiagram.mainContent;

import BT.BT;
import BT.BT.CDLineType;
import BT.BT.ClassType;
import BT.BT.UCLineType;
import BT.managers.ClassTypeException;
import BT.managers.PlaceManager;
import BT.models.CoordinateModel;
import BT.models.LineModel;
import BT.modules.ClassDiagram.places.CDClass;
import BT.modules.ClassDiagram.places.joinEdge.CDJoinEdgeController;
import BT.modules.UC.places.UCJoinEdge.UCJoinEdgeController;
import BT.modules.UC.places.UCUseCase;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Karel
 */
public class CDUseCaseReactivator {
    final private CDUseCaseConnector useCaseconnector;
    private ArrayList<CoordinateModel> allModels;
    final private PlaceManager cdModels;
    
    public CDUseCaseReactivator(CDUseCaseConnector useCaseconnector, PlaceManager cdModels)
    {
        this.useCaseconnector = useCaseconnector;
        this.cdModels = cdModels;
    }
    
    /**
     * Method that adds buttons based on whether user can reactivate or activate with selected class.
     * @param dialogPanel pane, that hold these buttons
     */
    public void addButtonsToDialog(final JPanel dialogPanel)
    {
        JButton reactivate = new JButton("reactivate");
        reactivate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                useCaseconnector.getSelectedModel().setName(((JTextField)dialogPanel.getComponent(0)).getText());
                reactivateButtonclicked();
                JDialog frame = (JDialog)dialogPanel.getRootPane().getParent();
                frame.setDefaultCloseOperation(JOptionPane.OK_OPTION);
                frame.dispose();
            }
        }); 
        JButton reactivateWith = new JButton("reactivate with");
        reactivateWith.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                reactivateWithButtonClickes();
                JDialog frame = (JDialog)dialogPanel.getRootPane().getParent();
                frame.setDefaultCloseOperation(JOptionPane.OK_OPTION);
                frame.dispose();
            }
        }); 
        if (useCaseconnector.getSelectedModel().getAssignedObject() == null)
        {
            if (!useCaseconnector.getSelectedModel().getInJoins().isEmpty() || !useCaseconnector.getSelectedModel().getOutJoins().isEmpty())
            {
                if (((CDClass)useCaseconnector.getSelectedModel()).getTypeOfClass() == BT.ClassType.ACTIVITY || ((CDClass)useCaseconnector.getSelectedModel()).getTypeOfClass() == BT.ClassType.ACTOR)
                {
                    dialogPanel.add(reactivate, BorderLayout.LINE_START);
                    dialogPanel.add(reactivateWith, BorderLayout.LINE_END);
                }
                else if (((CDClass)useCaseconnector.getSelectedModel()).getTypeOfClass() != BT.ClassType.INTERFACE)
                {
                    dialogPanel.add(reactivateWith, BorderLayout.LINE_END);
                }
            }
        }
    }
    
    /**
     * Method that is called when user clickes on reactivate button.
     */
    private void reactivateButtonclicked()
    {
        CDClass selectedClass = (CDClass) useCaseconnector.getSelectedModel();
        if (selectedClass.getTypeOfClass() == BT.ClassType.ACTIVITY)
        {
            useCaseconnector.createNewUseCaseObject(selectedClass.getName());
        }
        else if (selectedClass.getTypeOfClass() == BT.ClassType.ACTOR)
        {
            useCaseconnector.createNewUseCaseObject(selectedClass.getName());
        }
        
        for (LineModel oneOutJoin : selectedClass.getOutJoins()) {
            if (oneOutJoin.getFirstObject().getAssignedObject() !=null && oneOutJoin.getSecondObject().getAssignedObject() !=null)
            {
                useCaseconnector.setNewline(oneOutJoin);
                useCaseconnector.createNewUseCaseJoin();
            }
        }
        
        for (LineModel oneInJoin : selectedClass.getInJoins()) {
            if (oneInJoin.getFirstObject().getAssignedObject() !=null && oneInJoin.getSecondObject().getAssignedObject() !=null)
            {
                useCaseconnector.setNewline(oneInJoin);
                useCaseconnector.createNewUseCaseJoin();
            }
        }
    }
    
    /**
     * Method that is called when user clickes on reactivate button.
     */
    private void reactivateWithButtonClickes()
    {
        CDClass selectedClass = (CDClass) useCaseconnector.getSelectedModel();
        BT.ClassType selectedClassType = null;
        if (selectedClass.getTypeOfClass() == BT.ClassType.NONE)
        {
            try {
                selectedClassType = decideClassType(selectedClass);
            } catch (ClassTypeException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
        else
        {
            selectedClassType = selectedClass.getTypeOfClass();
        }
        if (selectedClassType == BT.ClassType.NONE)
        {
            this.allModels = this.useCaseconnector.getUcPlaces().getActorsFromUseCase();
            this.allModels.addAll(this.useCaseconnector.getUcPlaces().getUsecasesFromUseCase());
        }
        else if (selectedClassType == BT.ClassType.ACTIVITY)
        {
            this.allModels = this.useCaseconnector.getUcPlaces().getUsecasesFromUseCase();
        }
        else if (selectedClassType == BT.ClassType.ACTOR)
        {
            this.allModels = this.useCaseconnector.getUcPlaces().getActorsFromUseCase();
        }
        String[] modelsArray = new String[this.allModels.size()];
        for (int i = 0; i < this.allModels.size(); i++) {
            modelsArray[i] = ((this.allModels.get(i) instanceof UCUseCase)?"Use case":"Actor")+": "+this.allModels.get(i).getName();
        }
        int selectedId = createOptionPaneWithSelectBox(modelsArray);
        if (selectedId != -1)
        {
            this.useCaseconnector.getSelectedModel().setName(this.allModels.get(selectedId).getName());
            reactivateClassWithUseCase(this.allModels.get(selectedId));
        }
    }

    /**
     * Method for reactivating class with selected use case.
     * @param selectedUseCase selected class will be reactivated with this object
     */
    private void reactivateClassWithUseCase(CoordinateModel selectedUseCase)
    {
        CDClass selectedClass = (CDClass)this.useCaseconnector.getSelectedModel();
        selectedUseCase.getAssignedObject().setAssignedObject(null);
        selectedUseCase.setAssignedObject(selectedClass);
        selectedClass.setAssignedObject(selectedUseCase);
        selectedClass.setTypeOfClass((selectedUseCase instanceof UCUseCase)?ClassType.ACTIVITY:ClassType.ACTOR);
        for (LineModel inJoin : selectedUseCase.getInJoins()) {
            CoordinateModel firstObject = inJoin.getFirstObject();
            if (firstObject != null && firstObject.getAssignedObject() != null)
            {
                inJoin.getAssignedObject().setAssignedObject(null);
                CDJoinEdgeController newLine = new CDJoinEdgeController(firstObject.getAssignedObject(), selectedClass);
                LineModel existingLine = cdModels.getExistingLine(newLine);
                if  (existingLine !=null)
                {
                    cdModels.removeJoinEdge(existingLine);
                }
                inJoin.setAssignedObject(newLine);
                newLine.setAssignedObject(inJoin);
                newLine.setJoinEdgeType(getCDLineTypeByUCLineTyp(inJoin));
                cdModels.addObject(newLine);
            }
        }
        
        for (LineModel outJoin : selectedUseCase.getOutJoins()) {
            CoordinateModel secondObject = outJoin.getSecondObject();
            if (secondObject != null && secondObject.getAssignedObject() != null)
            {
                outJoin.getAssignedObject().setAssignedObject(null);
                CDJoinEdgeController newLine = new CDJoinEdgeController(selectedClass, secondObject.getAssignedObject());
                LineModel existingLine = cdModels.getExistingLine(newLine);
                if  (existingLine !=null)
                {
                    cdModels.removeJoinEdge(existingLine);
                }
                outJoin.setAssignedObject(newLine);
                newLine.setAssignedObject(outJoin);
                newLine.setJoinEdgeType(getCDLineTypeByUCLineTyp(outJoin));
                cdModels.addObject(newLine);
            }
        }
    }
    
    /**
     * Method for getting class line type by use case line type.
     * @param selectedUseCaseLine join edge in use case diagram
     * @return CDLineType by usecase line
     */
    private CDLineType getCDLineTypeByUCLineTyp(LineModel selectedUseCaseLine)
    {
        UCJoinEdgeController ucJoinEdge = (UCJoinEdgeController) selectedUseCaseLine;
        CDLineType selectedLineType = null;
        if (ucJoinEdge.getJoinEdgeType() == UCLineType.ASSOCIATION)
        {
            selectedLineType = CDLineType.ASSOCIATION;
        }
        else if (ucJoinEdge.getJoinEdgeType() == UCLineType.GENERALIZATION)
        {
            selectedLineType = CDLineType.GENERALIZATION;
        }
        return selectedLineType;
    }
    
    /**
     * Method that creates option pane, that lets you select which object should be connected.
     * @return int based on selected item
     */
    private int createOptionPaneWithSelectBox(String[] modelsArray)
    {
        JPanel dialogPanel = new JPanel(new BorderLayout());
        JComboBox petList = new JComboBox(modelsArray);
        dialogPanel.add(petList);
        int result = JOptionPane.showConfirmDialog(null, dialogPanel,
                    "Please Select object you want to reactivate from", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION)
        {   
            if ((JOptionPane.showConfirmDialog(null, "Are you sure you want to reactivate this object with selected object?",
                    "Please confirm", JOptionPane.YES_NO_OPTION)) == JOptionPane.OK_OPTION)
            {
                return petList.getSelectedIndex();
            }
            
        }
        return -1;
    }
    
    /**
     * Method for deciding what type of object can be reinitilize with
     * @param selectedClass
     * @return ClassType of reactivating model.
     * @throws ClassTypeException when object is connected to actors and activities
     */
    private BT.ClassType decideClassType(CDClass selectedClass) throws ClassTypeException
    {
        BT.ClassType decidedClassType = null;
        for (LineModel oneInjoin : selectedClass.getInJoins()) {
            if (((CDJoinEdgeController)oneInjoin).getJoinEdgeType() != BT.CDLineType.ASSOCIATION)
            {
                BT.ClassType actualType = ((CDClass)oneInjoin.getFirstObject()).getTypeOfClass();
                decidedClassType = (decidedClassType == null)?actualType:decidedClassType;
                if (actualType != BT.ClassType.NONE)
                {
                    if (decidedClassType!= actualType && decidedClassType != BT.ClassType.NONE)
                    {
                        throw new ClassTypeException("Class has connections to activities and actors. You can't assign it to any object without errors.");
                    }
                    decidedClassType = actualType;
                }
            }
        }
        
        for (LineModel oneOutJoin : selectedClass.getOutJoins()) {
            if (((CDJoinEdgeController)oneOutJoin).getJoinEdgeType() != BT.CDLineType.ASSOCIATION)
            {
                BT.ClassType actualType = ((CDClass)oneOutJoin.getSecondObject()).getTypeOfClass();
                decidedClassType = (decidedClassType == null)?actualType:decidedClassType;
                if (actualType != BT.ClassType.NONE)
                {
                    if (decidedClassType!= actualType && decidedClassType != BT.ClassType.NONE)
                    {
                        throw new ClassTypeException("Class has connections to activities and actors. You can't assign it to any object without errors.");
                    }
                    decidedClassType = actualType;
                }
            }
        }
        return decidedClassType;
    }
}
