/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BT.modules.UC.mainContent;

import BT.BT;
import BT.BT.CDLineType;
import BT.BT.UCLineType;
import BT.managers.PlaceManager;
import BT.models.CoordinateModel;
import BT.models.LineModel;
import BT.modules.ClassDiagram.places.CDClass;
import BT.modules.ClassDiagram.places.joinEdge.CDJoinEdgeController;
import BT.modules.UC.places.UCActor;
import BT.modules.UC.places.UCJoinEdge.UCJoinEdgeController;
import BT.modules.UC.places.UCUseCase;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Karel
 */
public class UCClassDiagramConnector {
    private CoordinateModel selectedObject;
    private LineModel newLine;
    private final PlaceManager cdPlaces;
    private final PlaceManager ucPlaces;
    
    public UCClassDiagramConnector(PlaceManager cdPlaces, PlaceManager ucPlaces)
    {
        this.cdPlaces = cdPlaces;
        this.ucPlaces = ucPlaces;
    }

    public void setNewLine(LineModel newLine) {
        this.newLine = newLine;
    }

    public LineModel getNewLine() {
        return newLine;
    }

    public PlaceManager getCdPlaces() {
        return cdPlaces;
    }

    public void setSelectedObject(CoordinateModel selectedObject) {
        this.selectedObject = selectedObject;
    }

    public CoordinateModel getSelectedObject() {
        return selectedObject;
    }
    
    /**
     * Method for creating join edge between propriete classes.
     */
    public void createNewClassJoinEdge()
    {
        CDJoinEdgeController newClassJoin = new CDJoinEdgeController(this.newLine.getFirstObject().getAssignedObject(), this.newLine.getSecondObject().getAssignedObject());
        UCJoinEdgeController useCaseJoin = (UCJoinEdgeController) this.newLine;
        BT.UCLineType useCaseLineType = useCaseJoin.getJoinEdgeType();
        if (useCaseLineType == BT.UCLineType.ASSOCIATION)
        {
            newClassJoin.setJoinEdgeType(BT.CDLineType.ASSOCIATION);
        }
        else if (useCaseLineType == BT.UCLineType.GENERALIZATION)
        {
            newClassJoin.setJoinEdgeType(BT.CDLineType.GENERALIZATION);
        }
        else if (useCaseLineType == BT.UCLineType.EXTENDS)
        {
            System.out.println("extends");
        }
        else if (useCaseLineType == BT.UCLineType.INCLUDE)
        {
            System.out.println("include");
        }
        this.newLine.getFirstObject().getAssignedObject().addOutJoins(newClassJoin);
        this.newLine.getSecondObject().getAssignedObject().addInJoin(newClassJoin);
        newClassJoin.setAssignedObject(useCaseJoin);
        useCaseJoin.setAssignedObject(newClassJoin);
        this.cdPlaces.addObject(newClassJoin);
    }
    
    public void createNewClassdiagramObject()
    {
        createNewClassdiagramObject(this.selectedObject.getName());
    }
    
    /**
     * Method for creating new object in class diagram based on use case.
     * @param className
     */
    public void createNewClassdiagramObject(String className)
    {
        CDClass newClass = new CDClass(this.selectedObject.getX(), this.selectedObject.getY());
        this.selectedObject.setAssignedObject(newClass);
        if (this.selectedObject instanceof UCActor)
        {
            newClass.setTypeOfClass(BT.ClassType.ACTOR);
        }
        else if (this.selectedObject instanceof UCUseCase)
        {
            newClass.setTypeOfClass(BT.ClassType.ACTIVITY);
        }
        newClass.setAssignedObject(this.selectedObject);
        newClass.setName(className);
        this.cdPlaces.addObject(newClass);
    }
    
    
    /**
     * Method that adds buttons based on whether user can reactivate or activate with selected use case object.
     * @param dialogPanel pane, that hold these buttons
     */
    public void addButtonsToDialog(final JPanel dialogPanel)
    {
        JButton reactivate = new JButton("reactivate");
        reactivate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                selectedObject.setName(((JTextField)dialogPanel.getComponent(0)).getText());
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
                int result = reactivateWithButtonClickes();
                if (result == JOptionPane.OK_OPTION)
                {
                    JDialog frame = (JDialog)dialogPanel.getRootPane().getParent();
                    frame.setDefaultCloseOperation(JOptionPane.OK_OPTION);
                    frame.dispose();
                }
            }
        }); 
        if (this.selectedObject.getAssignedObject() == null)
        {
            dialogPanel.add(reactivate, BorderLayout.LINE_START);
            dialogPanel.add(reactivateWith, BorderLayout.LINE_END);
        }
        else
        {
            dialogPanel.add(reactivateWith, BorderLayout.LINE_END);
        }
    }
    
    /**
     * Method that is called when reactivate button is clicked.
     * It creates new class and add join edges to it.
     */
    private void reactivateButtonclicked() {
        createNewClassdiagramObject();
        for (LineModel inJoin : this.selectedObject.getInJoins()) {
            this.newLine = inJoin;
            createNewClassJoinEdge();
        }
        
        for (LineModel outJoin : this.selectedObject.getOutJoins()) {
            this.newLine = outJoin;
            createNewClassJoinEdge();
        }
    }
    
    /**
     * Method thas is called when reactivate with button is called.
     */
    private int reactivateWithButtonClickes() {
        ArrayList<CoordinateModel> allModels = null;
        if (this.selectedObject instanceof UCActor)
        {
            allModels = this.cdPlaces.getActorsFromClassDiagram();
        }
        else if (this.selectedObject instanceof UCUseCase)
        {
            allModels = this.cdPlaces.getActivitiesFromClassDiagram();
        }
        if (allModels != null && !allModels.isEmpty())
        {
            String[] allClasses = new String[allModels.size()];
            for (int i=0; i<allModels.size();i++) {
                allClasses[i] = ((CDClass)allModels.get(i)).getTypeOfClass()+": "+ allModels.get(i).getName();
            }
            int result = this.cdPlaces.createOptionPaneWithSelectBox(allClasses);
            if (result != -1)
            {
                CoordinateModel selectedClass = this.cdPlaces.getObjects().get(result);
                if (!selectedClass.equals(this.selectedObject.getAssignedObject()))
                {
                    reactivateUseCaseWithSelectedClass((CDClass)selectedClass);
                }
                return JOptionPane.OK_OPTION;
            }
            return JOptionPane.CLOSED_OPTION;
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No suitable class to reactivate with");
            return JOptionPane.CLOSED_OPTION;
        }
    }
    
    /**
     * Method that lets you reactivate selected use case with class from class diagram.
     * First dismamber both assigned object to use case and dismeber use case off each other.
     * Then join selected class and selected use case together, also connect their in/out joins
     * @param selectedClass class that is being ractivated with.
     */
    private void reactivateUseCaseWithSelectedClass(CDClass selectedClass)
    {
        if (selectedClass.getAssignedObject() != null)
        {
            selectedClass.getAssignedObject().setAssignedObject(null);
        }
        this.selectedObject.disMemberObject();
        
        this.selectedObject.setAssignedObject(selectedClass);
        this.selectedObject.getAssignedObject().setAssignedObject(selectedObject);
        this.selectedObject.setName(selectedClass.getName());
        for (LineModel inJoin : selectedClass.getInJoins()) {
            if (!inJoin.isLineEmpty())
            {
                createNewUseCasejoinFromClassJoin((CDJoinEdgeController)inJoin);
            }
        }
        
        for (LineModel outJoin : selectedClass.getOutJoins()) {
            if (!outJoin.isLineEmpty())
            {
                createNewUseCasejoinFromClassJoin((CDJoinEdgeController)outJoin);
            }
        }
    }
    
    /**
     * Method that lets you make use case join from class join.
     * Create new Use case line, if both objects from class diagram can be in use case diagram.
     * If similar line exist, delete it.
     * Set type of new line based on class line type and inesrt it in place manager.
     * @param classLine object that is used to determine the type of join.
     */
    private void createNewUseCasejoinFromClassJoin(CDJoinEdgeController classLine)
    {
        if (classLine.checkBothObjects())
        {
            UCJoinEdgeController newJoinEdge = new UCJoinEdgeController(classLine.getFirstObject().getAssignedObject(), classLine.getSecondObject().getAssignedObject());
            if (ucPlaces.lineExists(newJoinEdge))
            {
                ucPlaces.removeJoinEdge(ucPlaces.getExistingLine(newJoinEdge));
            }
            
            if (classLine.getJoinEdgeType() == CDLineType.ASSOCIATION)
            {
                newJoinEdge.setJoinEdgeType(UCLineType.ASSOCIATION);
            }
            else if (classLine.getJoinEdgeType() == CDLineType.GENERALIZATION)
            {
                newJoinEdge.setJoinEdgeType(UCLineType.GENERALIZATION);
            }
            if (classLine.getAssignedObject() != null)
            {
                classLine.getAssignedObject().setAssignedObject(null);
            }
            classLine.setAssignedObject(newJoinEdge);
            newJoinEdge.setAssignedObject(classLine);
            ucPlaces.addObject(newJoinEdge);
        }
    }
    
    /**
     * 
     */
    public void reactivateAllObjects()
    {
        for (CoordinateModel oneObject : ucPlaces.getObjects()) {
            if (oneObject.getAssignedObject() == null)
            {
                this.selectedObject = oneObject;
                createNewClassdiagramObject();
            }
        }
        
        for (LineModel oneJoin : ucPlaces.getJoinEdges()) {
            this.newLine = oneJoin;
            createNewClassJoinEdge();
        }
    }
}
