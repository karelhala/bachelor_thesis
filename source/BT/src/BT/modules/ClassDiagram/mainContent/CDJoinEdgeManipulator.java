/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BT.modules.ClassDiagram.mainContent;

import BT.BT.CDLineType;
import BT.models.CoordinateModel;
import BT.modules.ClassDiagram.places.joinEdge.CDJoinEdgeController;
import javax.swing.JToggleButton;

/**
 *
 * @author Karel
 */

public class CDJoinEdgeManipulator {
    
    public CDJoinEdgeManipulator()
    {
        super();
    }
    
    /**
     * 
     * @param selectedButton
     * @param joinEdge
     */
    public static void changeLineTypeByButton(JToggleButton selectedButton, CDJoinEdgeController joinEdge)
    {
        switch (selectedButton.getName())
        {
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
     * 
     * @param joinEdge
     * @param clickedObject 
     * @return  
     */
    public static CDJoinEdgeController createJoinEdge(CDJoinEdgeController joinEdge, CoordinateModel clickedObject)
    {   
        if (joinEdge == null)
        {
            joinEdge = new CDJoinEdgeController();
        }
        if (joinEdge.getFirstObject() == null)
        {
            joinEdge.setFirstObject(clickedObject);
        }
        else if (joinEdge.getSecondObject() == null)
        {
            joinEdge.setSecondObject(clickedObject);
        }       
        return joinEdge;
    }
}
