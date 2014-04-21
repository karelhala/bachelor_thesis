/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ClassDiagram.mainContent;

import BT.BT.CDLineType;
import BT.BT.ClassType;
import BT.models.CoordinateModel;
import BT.models.MyArrayList;
import BT.modules.ClassDiagram.places.CDClass;
import BT.modules.ClassDiagram.places.joinEdge.CDJoinEdgeController;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

/**
 * Class for manipulating with join edges in class diagram part.
 * @author Karel
 */
public class CDJoinEdgeManipulator {

    /**
     * Method for changing  type of join edge based on name of button
     * @param selectedButton
     * @param joinEdge
     */
    public static void changeLineTypeByButton(JToggleButton selectedButton, CDJoinEdgeController joinEdge) {
        switch (selectedButton.getName()) {
            case "AGGREGATION":
                joinEdge.setJoinEdgeType(CDLineType.AGGREGATION);
                break;
            case "ASSOCIATION":
                joinEdge.setJoinEdgeType(CDLineType.ASSOCIATION);
                break;
            case "COMPOSITION":
                joinEdge.setJoinEdgeType(CDLineType.COMPOSITION);
                break;
            case "GENERALIZATION":
                joinEdge.setJoinEdgeType(CDLineType.GENERALIZATION);
                break;
            case "REALIZATION":
                joinEdge.setJoinEdgeType(CDLineType.REALIZATION);
                break;
        }
    }

    /**
     * Method for creating join edge bwtween classes. It will create new join edge if no one exists
     * and add to it on first or second place new object. Based on which part is epmty.
     * @param joinEdge edge that is being created
     * @param clickedObject object that is inserted either on first or second place in join edge.
     * @param selectedButton
     * @return
     */
    public static CDJoinEdgeController createJoinEdge(CDJoinEdgeController joinEdge, CoordinateModel clickedObject, JToggleButton selectedButton) {
        if (joinEdge == null) {
            joinEdge = new CDJoinEdgeController();
        }
        
        changeLineTypeByButton(selectedButton, joinEdge);
        if (joinEdge.getFirstObject() == null) {
            joinEdge.setFirstObject(clickedObject);
            joinEdge = checkObjects(joinEdge);
        } else if (joinEdge.getSecondObject() == null) {
            joinEdge.setSecondObject(clickedObject);
            joinEdge = checkObjects(joinEdge);
        }
        return joinEdge;
    }
    
    /**
     * Method for checking objects and lines between them.
     * If you are joining interfaces, you can use only generalization. If you connect
     * to any class interface you can do it only with realization.
     * @param joinEdge that is under inspection
     * @return 
     */
    public static CDJoinEdgeController checkObjects(CDJoinEdgeController joinEdge)
    {
        String errorMessage = "";
        try
        {
            //check for connection between interface and any other class
            if (((CDClass)joinEdge.getFirstObject()).getTypeOfClass() == ClassType.INTERFACE && joinEdge.getJoinEdgeType() != CDLineType.GENERALIZATION && 
                   joinEdge.getSecondObject() != null && ((CDClass)joinEdge.getSecondObject()).getTypeOfClass() == ClassType.INTERFACE)
            {
                errorMessage = "You can't use any other join edge between interface and other classes than GENERALIZATION.";
                joinEdge.setFirstObject(null);
                joinEdge.setSecondObject(null);
            }
            
            //check for connection between any class and interface
            if (((CDClass)joinEdge.getFirstObject()).getTypeOfClass() != ClassType.INTERFACE && joinEdge.getSecondObject() != null && ((CDClass)joinEdge.getSecondObject()).getTypeOfClass() == ClassType.INTERFACE &&
                    joinEdge.getJoinEdgeType() != CDLineType.REALIZATION)
            {
                errorMessage = "You can't connect any other class with  interface, than with REALIZATION join.";
                joinEdge.setSecondObject(null);
            }
            
            //check if class can have another parent
            if (joinEdge.getJoinEdgeType() == CDLineType.GENERALIZATION)
            {
                if (joinEdge.getFirstObject()!= null && ((CDClass)joinEdge.getFirstObject()).hasParent())
                {
                    joinEdge.setFirstObject(null);
                    return joinEdge;
                }
                
                if (joinEdge.getSecondObject() != null)
                {
                    for (CDClass newParent = ((CDClass)joinEdge.getSecondObject()).getParent(); newParent != null; newParent = newParent.getParent())
                    {
                        if (newParent.equals(joinEdge.getFirstObject()))
                        {
                            joinEdge.setSecondObject(null);
                            return joinEdge;
                        }
                    }
                }
            }
            
            //check for connection bewteen activity and actor
            if (((CDClass)joinEdge.getFirstObject()).getTypeOfClass() == ClassType.ACTIVITY)
            {
                if (joinEdge.getSecondObject() != null)
                {
                    if (((CDClass)joinEdge.getSecondObject()).getTypeOfClass() == ClassType.ACTOR)
                    {
                        if (joinEdge.getJoinEdgeType() != CDLineType.ASSOCIATION)
                        {
                            errorMessage = "You can't use any other join between actor and action than ASSOCIATON.";
                            joinEdge.setSecondObject(null);
                        }
                        else
                        {
                            CoordinateModel firstObject = joinEdge.getFirstObject();
                            joinEdge.setFirstObject(joinEdge.getSecondObject());
                            joinEdge.setSecondObject(firstObject);
                            ((MyArrayList)joinEdge.getBreakPoints()).swapWholeArrayList();
                        }
                    }
                }
            }
            
            //check for connection bewteen actor and activity
            if (((CDClass)joinEdge.getFirstObject()).getTypeOfClass() == ClassType.ACTOR)
            {
                if (joinEdge.getSecondObject() != null)
                {
                    if (((CDClass)joinEdge.getSecondObject()).getTypeOfClass() == ClassType.ACTIVITY)
                    {
                        if (joinEdge.getJoinEdgeType() != CDLineType.ASSOCIATION)
                        {
                            errorMessage = "You can't use any other join between actor and action than ASSOCIATON.";
                            joinEdge.setSecondObject(null);
                        }
                    }
                }
            }
        }catch (NullPointerException exep){
            System.err.println(exep.getMessage());
        }
        
        //display error message if needed
        if (!errorMessage.equals(""))
        {
            JOptionPane.showMessageDialog(null, errorMessage);
        }
        return joinEdge;
    }
}
