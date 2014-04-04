/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BT.modules.ClassDiagram.mainContent;

import BT.BT;
import BT.managers.ClassTypeException;
import BT.models.CoordinateModel;
import BT.models.LineModel;
import BT.modules.ClassDiagram.places.CDClass;
import BT.modules.ClassDiagram.places.joinEdge.CDJoinEdgeController;
import BT.modules.UC.places.UCActor;
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
    private CDUseCaseConnector useCaseconnector;
    private ArrayList<CoordinateModel> allModels;
    
    public CDUseCaseReactivator(CDUseCaseConnector useCaseconnector)
    {
        this.useCaseconnector = useCaseconnector; 
    }

    public CDUseCaseConnector getUseCaseconnector() {
        return useCaseconnector;
    }

    public void setUseCaseconnector(CDUseCaseConnector useCaseconnector) {
        this.useCaseconnector = useCaseconnector;
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
                System.out.println();
                frame.dispose();
            }
        }); 
        JButton reactivateWith = new JButton("reactivate with");
        reactivateWith.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                reactivateWithButtonClickes();
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
        for (LineModel oneOutJoin : selectedClass.getOutJoins()) {
            useCaseconnector.setNewline(oneOutJoin);
            useCaseconnector.createNewUseCaseJoin();
        }
        
        for (LineModel oneInJoin : selectedClass.getInJoins()) {
            useCaseconnector.setNewline(oneInJoin);
            useCaseconnector.createNewUseCaseJoin();
        }
        
        if (selectedClass.getTypeOfClass() == BT.ClassType.ACTIVITY)
        {
            useCaseconnector.createNewUseCaseObject(selectedClass.getName());
        }
        else if (selectedClass.getTypeOfClass() == BT.ClassType.ACTOR)
        {
            useCaseconnector.createNewUseCaseObject(selectedClass.getName());
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
            this.allModels = getActorsFromUseCase();
            this.allModels.addAll(getUsecasesFromUseCase());
        }
        else if (selectedClassType == BT.ClassType.ACTIVITY)
        {
            this.allModels = getUsecasesFromUseCase();
        }
        else if (selectedClassType == BT.ClassType.ACTOR)
        {
            this.allModels = getActorsFromUseCase();
        }
    }

    private CoordinateModel createOptionPaneWithSelectBox()
    {
        JPanel dialogPanel = new JPanel(new BorderLayout());
//        JComboBox petList = new JComboBox()
//        for (CoordinateModel coordinateModel : allModels) {
//            JComboBox petList = new JComboBox(coordinateModel.getName());
//        }
        JOptionPane.showConfirmDialog(null, dialogPanel,
                    "Please Select object you want to reactivate from", JOptionPane.OK_CANCEL_OPTION);
        return null;
    }
    
    /**
     * Method for fetching all use case actors.
     * @return ArrayList<CoordinateModel> of actors
     */
    private ArrayList<CoordinateModel> getActorsFromUseCase()
    {
        ArrayList<CoordinateModel> useCasemodels = new ArrayList<>();
        for (CoordinateModel oneObject : useCaseconnector.getUcPlaces().getObjects()) {
            if (oneObject instanceof UCActor)
            {
                useCasemodels.add(oneObject);
            }
        }
        return useCasemodels;
    }
    
    
    /**
     * Method for fetching all use case useCases.
     * @return ArrayList<CoordinateModel> of useCases
     */
    private ArrayList<CoordinateModel> getUsecasesFromUseCase()
    {
        ArrayList<CoordinateModel> useCasemodels = new ArrayList<>();
        for (CoordinateModel oneObject : useCaseconnector.getUcPlaces().getObjects()) {
            if (oneObject instanceof UCUseCase)
            {
                useCasemodels.add(oneObject);
            }
        }
        return useCasemodels;
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
