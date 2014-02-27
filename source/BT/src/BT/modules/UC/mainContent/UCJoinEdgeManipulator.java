/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.UC.mainContent;

import BT.BT;
import BT.models.CoordinateModel;
import BT.modules.UC.places.UCActor;
import BT.modules.UC.places.UCJoinEdge.UCJoinEdgeController;
import BT.modules.UC.places.UCUseCase;
import javax.swing.JToggleButton;

/**
 *
 * @author Karel Hala
 */
public class UCJoinEdgeManipulator {
    
    public UCJoinEdgeManipulator()
    {
        super();
    }
    
    /**
     * 
     * @param selectedButton
     * @param joinEdge
     */
    public static void changeLineTypeByButton(JToggleButton selectedButton, UCJoinEdgeController joinEdge)
    {
        System.out.println(selectedButton);
        switch (selectedButton.getName())
        {
           case "ASSOCIATION":
                    joinEdge.setJoinEdgeType(BT.UCLineType.ASSOCIATION);
                 break;

           case "INCLUDE":  
                    joinEdge.setJoinEdgeType(BT.UCLineType.INCLUDE);
                 break;

           case "EXTENDS":  
                    joinEdge.setJoinEdgeType(BT.UCLineType.EXTENDS);
                 break;
           case "GENERALIZATION":
                    joinEdge.setJoinEdgeType(BT.UCLineType.GENERALIZATION);
                 break;
        }
        setLineTypeBySecondObject(joinEdge);
    }
    
    /**
     * 
     * @param joinEdge
     * @param clickedObject 
     * @return  
     */
    public static UCJoinEdgeController createJoinEdge(UCJoinEdgeController joinEdge, CoordinateModel clickedObject)
    {   
        if (joinEdge == null)
        {
            joinEdge = new UCJoinEdgeController();
        }
        if (joinEdge.getFirstObject() == null)
        {
            joinEdge.setFirstObject(clickedObject);
        }
        else if (joinEdge.getSecondObject() == null)
        {
            if (clickedObject instanceof UCUseCase && joinEdge.getFirstObject() instanceof UCActor)
            {
                joinEdge.setSecondObject(joinEdge.getFirstObject());
                joinEdge.setFirstObject(clickedObject);
            }
            else
            {
                joinEdge.setSecondObject(clickedObject);
            }
        }       
        return joinEdge;
    }
    
    /**
     * 
     * @param joinEdge 
     */
    public static void setLineTypeBySecondObject(UCJoinEdgeController joinEdge)
    {
        if (joinEdge.getFirstObject() instanceof UCUseCase && joinEdge.getSecondObject() instanceof UCActor)
        {
            joinEdge.setJoinEdgeType(BT.UCLineType.ASSOCIATION);
        }
    }
}
