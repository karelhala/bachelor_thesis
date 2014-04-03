/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BT.modules.ClassDiagram.mainContent;

import BT.BT;
import BT.BT.ClassType;
import BT.managers.PlaceManager;
import BT.models.CoordinateModel;
import BT.models.LineModel;
import BT.modules.ClassDiagram.places.CDClass;
import BT.modules.ClassDiagram.places.joinEdge.CDJoinEdgeController;
import BT.modules.UC.places.UCActor;
import BT.modules.UC.places.UCJoinEdge.UCJoinEdgeController;
import BT.modules.UC.places.UCUseCase;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author Karel
 */
public class CDUseCaseConnector {
    private CoordinateModel selectedModel;
    private LineModel newline;
    private final PlaceManager ucPlaces;
    
    public CDUseCaseConnector(PlaceManager UCPlaces)
    {
        this.ucPlaces = UCPlaces;
    }

    public CoordinateModel getSelectedModel() {
        return selectedModel;
    }

    public void setSelectedModel(CoordinateModel selectedModel) {
        this.selectedModel = selectedModel;
    }

    public LineModel getNewline() {
        return newline;
    }

    public void setNewline(LineModel newline) {
        this.newline = newline;
    }
    
    /**
     * Method that creates new actor or use case from new class. 
     * It will check if type of class has been changed and if so, it will remove propriete use case from
     * use case diagram and create new one. If new class was created create appropriate object in use case.
     * @param useCaseName name of created use case
     */
    public void createNewUseCase(String useCaseName)
    {
        CDClass selectedClass = (CDClass) selectedModel;
        if (selectedClass.getTypeOfClass()!=BT.ClassType.NONE && selectedClass.getAssignedObject() != null)
        {
            if (selectedClass.getTypeOfClass()==BT.ClassType.ACTOR && !(selectedClass.getAssignedObject() instanceof UCActor))
            {
                ucPlaces.removePlace(selectedClass.getAssignedObject());
                createNewActor();
            }
            else if (selectedClass.getTypeOfClass()==BT.ClassType.ACTIVITY && !(selectedClass.getAssignedObject() instanceof UCUseCase))
            {
                ucPlaces.removePlace(selectedClass.getAssignedObject());
                createNewUseCase();
            }
            selectedClass.getAssignedObject().setName(useCaseName);
        }
        else if (selectedClass.getTypeOfClass()==BT.ClassType.ACTOR && selectedClass.getAssignedObject() == null)
        {
            createNewActor();
            selectedClass.getAssignedObject().setName(useCaseName);
        }
        else if (selectedClass.getTypeOfClass()==BT.ClassType.ACTIVITY && selectedClass.getAssignedObject() == null)
        {
            createNewUseCase();
            selectedClass.getAssignedObject().setName(useCaseName);
        }
        else if (selectedClass.getTypeOfClass()==BT.ClassType.NONE && selectedClass.getAssignedObject() != null)
        {
            selectedClass.getAssignedObject().setAssignedObject(selectedClass);
            selectedClass.setAssignedObject(selectedClass);
        }
    }
    
    /**
     * Method for creating new actor and inserting him in use case diagram.
     */
    private void createNewActor()
    {
        CDClass selectedClass = (CDClass) this.selectedModel;
        UCActor newActor = new UCActor(selectedClass.getX(), selectedClass.getY());
        newActor.setName(this.selectedModel.getName());
        ucPlaces.addObject(newActor);
        selectedClass.setAssignedObject(newActor);
        selectedClass.getAssignedObject().setAssignedObject(selectedClass);
    }
    
    /**
     * Method for creating new use case and inserting it in use case diagram.
     */
    private void createNewUseCase()
    {
        CDClass selectedClass = (CDClass) this.selectedModel;
        UCUseCase newUsecase = new UCUseCase(selectedClass.getX(), selectedClass.getY());
        newUsecase.setName(this.selectedModel.getName());
        ucPlaces.addObject(newUsecase);
        selectedClass.setAssignedObject(newUsecase);
        selectedClass.getAssignedObject().setAssignedObject(selectedClass);
    }
    
    /**
     * Method for creating new joins between associated objects in use case.
     */
    public void createNewUseCaseJoin()
    {
        UCJoinEdgeController newUseCaseLine = new UCJoinEdgeController(this.newline.getFirstObject().getAssignedObject(), this.newline.getSecondObject().getAssignedObject());
        CDJoinEdgeController cdJoin = (CDJoinEdgeController) this.newline;
        if (cdJoin.getJoinEdgeType() == BT.CDLineType.ASSOCIATION)
        {
            newUseCaseLine.setJoinEdgeType(BT.UCLineType.ASSOCIATION);
        }
        else if (cdJoin.getJoinEdgeType() == BT.CDLineType.GENERALIZATION)
        {
            newUseCaseLine.setJoinEdgeType(BT.UCLineType.GENERALIZATION);
        }
        else if (cdJoin.getJoinEdgeType() == BT.CDLineType.AGGREGATION)
        {
            System.out.println("aggregation");
        }
        else if (cdJoin.getJoinEdgeType() == BT.CDLineType.COMPOSITION)
        {
            System.out.println("composition");
        }
        newUseCaseLine.setAssignedObject(cdJoin);
        cdJoin.setAssignedObject(newUseCaseLine);
        this.ucPlaces.addObject(newUseCaseLine);
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
                selectedModel.setName(((JTextField)dialogPanel.getComponent(0)).getText());
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
        if (this.selectedModel.getAssignedObject() == null)
        {
            if (!this.selectedModel.getInJoins().isEmpty() || !this.selectedModel.getOutJoins().isEmpty())
            {
                if (((CDClass)this.selectedModel).getTypeOfClass() == ClassType.ACTIVITY || ((CDClass)this.selectedModel).getTypeOfClass() == ClassType.ACTOR)
                {
                    dialogPanel.add(reactivate, BorderLayout.LINE_START);
                    dialogPanel.add(reactivateWith, BorderLayout.LINE_END);
                }
                else
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
        CDClass selectedClass = (CDClass) this.selectedModel;
        for (LineModel oneOutJoin : selectedClass.getOutJoins()) {
            this.newline = oneOutJoin;
            createNewUseCaseJoin();
        }
        
        for (LineModel oneInJoin : selectedClass.getInJoins()) {
            this.newline = oneInJoin;
            createNewUseCaseJoin();
        }
        
        if (selectedClass.getTypeOfClass() == ClassType.ACTIVITY)
        {
            createNewUseCase();
        }
        else if (selectedClass.getTypeOfClass() == ClassType.ACTOR)
        {
            createNewActor();
        }
    }
    
    /**
     * Method that is called when user clickes on reactivate button.
     */
    private void reactivateWithButtonClickes()
    {
        System.out.println("reactivate with clicked");
    }
}
