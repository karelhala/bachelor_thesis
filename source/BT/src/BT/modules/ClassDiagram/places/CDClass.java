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
}
