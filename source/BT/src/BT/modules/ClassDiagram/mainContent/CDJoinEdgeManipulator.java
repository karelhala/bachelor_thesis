/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BT.modules.ClassDiagram.mainContent;

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
           case "ASSOCIATION":
                    joinEdge.setJoinEdgeType(BT.BT.CDLineType.ASSOCIATION);
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
    
    /* Move this to propriete class */
    public static void setLineTypeBySecondObject(CDJoinEdgeController joinEdge)
    {
        
    }
}
