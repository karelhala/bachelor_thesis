/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BT.modules.UC.mainContent;

import BT.BT;
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
    
    public UCClassDiagramConnector(PlaceManager cdPlaces)
    {
        this.cdPlaces = cdPlaces;
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
    
    /**
     * Method for creating new object in class diagram based on use case.
     */
    public void createNewClassdiagramObject()
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
                reactivateWithButtonClickes();
                JDialog frame = (JDialog)dialogPanel.getRootPane().getParent();
                frame.setDefaultCloseOperation(JOptionPane.OK_OPTION);
                frame.dispose();
            }
        }); 
        if (this.selectedObject.getAssignedObject() == null)
        {
            if (!this.selectedObject.getInJoins().isEmpty() || !this.selectedObject.getOutJoins().isEmpty())
            {
                dialogPanel.add(reactivate, BorderLayout.LINE_START);
                dialogPanel.add(reactivateWith, BorderLayout.LINE_END);
            }
        }
        else
        {
            dialogPanel.add(reactivateWith, BorderLayout.LINE_END);
        }
    }
    
    private void reactivateButtonclicked() {
        System.out.println("Generated method"); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void reactivateWithButtonClickes() {
        System.out.println("Generated method"); //To change body of generated methods, choose Tools | Templates.
    }
}
