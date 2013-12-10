/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ClassDiagram.places;

import BT.models.CoordinateModel;
import BT.modules.UC.places.UCActor;
import BT.modules.UC.places.UCJoinEdge.UCJoinEdgeController;
import BT.modules.UC.places.UCUseCase;
import java.util.ArrayList;

/**
 *
 * @author Karel Hala
 */
public class CDPlaceModel {
    /**
    * Arraylist that contains all actors in UC
    * @var ArrayList<CDClass>
    */
    protected ArrayList<CDClass> classes = new ArrayList<>();
    
    /**
    * Arraylist that contains all join edges in UC
    * @var ArrayList<UCJoinEdge>
    */
    protected ArrayList<UCJoinEdgeController> joinEdges = new ArrayList<>();

    /**
    * Method for add new join edge to array list.
    * @param UCJoinEdgeController joinEdge object to be added
    */
    private void addJoinEdge(UCJoinEdgeController joinEdge)
    {
        if (!lineExists(joinEdge))
        {
            this.joinEdges.add(joinEdge);
        }
    }
    
    /**
    * Method for returning all join edges in array list.
    * @return ArrayList<UCJoinEdge>
    */
    public ArrayList<UCJoinEdgeController> getJoinEdges()
    {
        return this.joinEdges;
    }
    
    /**
    * Method for returning all actors in array list.
    * @return ArrayList<UCActor>
    */
    public ArrayList<CDClass> getClasses()
    {
        return this.classes;
    }
    
    /**
    * Method for adding new class to array list.
    * @param CDClass place object to be added
    */
    private void addClass(CDClass newClass)
    {
        this.classes.add(newClass);
    }
    
    /**
     * 
     * @param coordModel 
     */
    public void addObject (CoordinateModel coordModel)
    {
        if (coordModel instanceof CDClass)
        {
            addClass((CDClass)coordModel);
        }
        else if (coordModel instanceof UCJoinEdgeController)
        {
            addJoinEdge((UCJoinEdgeController)coordModel);
        }
    }
    
    /**
     * Method that checks if line allrady exists, either in way first object --> second object or
     * second object --> first object.
     * Resolving issue with multiple lines connected to same objects.
     * Also checks if line isn't same
     * @param UCJoinEdgeController newLine
     * @return true when line exist
     * @return false when line doesn't exist
     */
    public Boolean lineExists(UCJoinEdgeController newLine)
    {
        for (UCJoinEdgeController oneEdge : this.joinEdges)
        {
            if (oneEdge.getSecondObject().equals(newLine.getSecondObject()) || oneEdge.getSecondObject().equals(newLine.getFirstObject()))
            {
                if (oneEdge.getFirstObject().equals(newLine.getSecondObject()) || oneEdge.getFirstObject().equals(newLine.getFirstObject()))
                {
                    return true;
                }
            }
        }
        return false;
    }
}
