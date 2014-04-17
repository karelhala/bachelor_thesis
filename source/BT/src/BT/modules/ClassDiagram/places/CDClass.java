/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ClassDiagram.places;

import BT.BT;
import BT.BT.CDLineType;
import BT.models.LineModel;
import BT.modules.ClassDiagram.places.joinEdge.CDJoinEdgeController;

/**
 *
 * @author Karel Hala
 */
public class CDClass extends CDClassDrawer {
    
    /**
     * Contructor for creating new class at coordinates x and y
     * @param x
     * @param y
     */
    public CDClass(int x, int y) {
        super(x, y);
    }
    
    /**
     * Method that checks if class allready has parent class.
     * @return true if class has join to other class with type aggregation.
     */
    public boolean hasParent()
    {
        for (LineModel lineModel : outJoins) {
            CDJoinEdgeController joinEdge = (CDJoinEdgeController) lineModel;
            if (joinEdge.getJoinEdgeType() == CDLineType.GENERALIZATION)
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Method that will return parent class.
     * It will be returned only if class has parent.
     * @return 
     */
    public CDClass getParent() 
    {
        if (hasParent())
        {
            for (LineModel lineModel : outJoins) {
                CDJoinEdgeController joinEdge = (CDJoinEdgeController) lineModel;
                if (joinEdge.getJoinEdgeType() == CDLineType.GENERALIZATION)
                {
                    return (CDClass) lineModel.getSecondObject();
                }
            }
        }
        return null;
    }
    
//    /**
//     * Method that will loop through every object and check if there is no loop in generalization.
//     * @return true or false
//     */
//    public boolean canGeneralize()
//    {
//        if (hasParent())
//        {
//            for(CDClass newClass = getParent(); newClass!= null; newClass = newClass.getParent())
//            {
//                if (newClass.equals(this))
//                {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
}
